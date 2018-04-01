package com.pankal.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static ObjectMapper m = null;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);


	public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		m = new ObjectMapper();
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

	@GetMapping("/filter/{criteria}")
	@Transactional(readOnly = true)
	public List<User> load(@PathVariable String criteria) {
		log.info("in getContacts filter for " + criteria);
		JsonNode crit;
		List<User> results = new ArrayList<>();
		try {
			crit = m.readTree(criteria);
			if(crit.isArray()) {
				if(((ArrayNode)crit).size() > 0) {
					Iterator<JsonNode> it = ((ArrayNode) crit).elements();
//				int ind = 0;

					while (it.hasNext()) {
						ObjectNode tdata = (ObjectNode) it.next();
						log.info("filter: " + tdata);
						Stream<User> stream = userRepository.findByCriteria(tdata.get("name").textValue());
						stream.forEach(c -> results.add(c));
					}
					return results;
				}else{
					PageRequest request = new PageRequest(0, 40);
					Page page = userRepository.findAll(request);
					return page.getContent();
				}


			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
		//return userRepository.findByCriteria(criteria);
	}

	@GetMapping("/{id}")
	public User get(@PathVariable String id) {

		log.info("in getAffair with id " + id);
		try {
			UUID uuid = UUID.fromString(id);
			PageRequest request = new PageRequest(0,40);
//		Page page = affairRepository.findAll(request);

			Example<User> example = Example.of(new User(uuid));
			Optional<User> res = userRepository.findOne(example);
			if(res.isPresent())
				return res.get();
			else
				return example.getProbe();
		}catch (IllegalArgumentException e){
			User res = new User();
			return res;
		}

	}

	@PostMapping
	public User generateUUID(@RequestBody User affair) {
		log.info("Received POST with User " + affair);

		User res = new User(UUID.randomUUID());

//		Folder res = affairRepository.save(affair);
//		ObjectNode res = JsonNodeFactory.instance.objectNode().put("response", "test resp 34");
		return res;
	}

	@PutMapping("/{id}")
	public User edit(@PathVariable String id, @RequestBody User entity) {

//		Folder existingContact = (Folder) affairRepository.findOne(id);
//		Assert.notNull(existingContact, "Folder not found");
//		existingContact.setLegal_name(affair.getLegal_name());
//		affairRepository.save(existingContact);

		log.info("in put with id " + id);
		try {
			UUID uuid = UUID.fromString(id);
		}catch (IllegalArgumentException e){
			log.info("Saving new User.");
		}

		log.info("Received PUT with user " + entity);

		User res = userRepository.save(entity);
		return res;
	}

	@DeleteMapping("/{id}")
	public JsonNode delete(@PathVariable UUID id) {

		Example<User> example = Example.of(new User(id));
		Optional<User> res = userRepository.findOne(example);
		ObjectNode response = JsonNodeFactory.instance.objectNode();
		if(res.isPresent()) {
			userRepository.delete(res.get());
			return response.put("response", "success");
		}else
			return response.put("response", "failure");


	}
}
