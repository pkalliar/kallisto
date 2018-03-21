package gr.mfa.affair;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/affairs")
@CrossOrigin("*")
public class AffairController {

	private AffairRepository affairRepository;
	private static ObjectMapper m = null;

	private static final Logger log = LoggerFactory.getLogger(AffairController.class);

	public AffairController(AffairRepository affairRepository) {
		this.affairRepository = affairRepository;
		m = new ObjectMapper();
	}



	@GetMapping
	public List<Affair> getAffairs() {
		log.info("in getAffairs");
		PageRequest request = new PageRequest(0,40);
		Page page = affairRepository.findAll(request);

		return page.getContent();

//		return affairRepository.findAll(request);
	}

	@GetMapping("/search/{name}")
	public List<Affair> searchAffairs(@PathVariable String name) {
		log.info("in getAffairs search for " + name);

//		return null;
		return affairRepository.findByNameOrCode(name);

	}

	@GetMapping("/filter/{criteria}")
	@Transactional(readOnly = true)
	public List<Affair> loadContacts(@PathVariable String criteria) {
		log.info("in getContacts filter for " + criteria);
		JsonNode crit;
		List<Affair> results = new ArrayList<>();
		try {
			crit = m.readTree(criteria);
			if(crit.isArray()) {
				if(((ArrayNode)crit).size() > 0) {
					Iterator<JsonNode> it = ((ArrayNode) crit).elements();
//				int ind = 0;

					while (it.hasNext()) {
						ObjectNode tdata = (ObjectNode) it.next();
						log.info("filter: " + tdata);
						Stream<Affair> stream = affairRepository.findByCriteria(tdata.get("name").textValue());
						stream.forEach(c -> results.add(c));
					}
					return results;
				}else{
					PageRequest request = new PageRequest(0, 40);
					Page page = affairRepository.findAll(request);
					return page.getContent();
				}


			}

		} catch (IOException e) {
			e.printStackTrace();
		}



//		return null;
		return affairRepository.findByNameOrCode(criteria);
	}

	@GetMapping("/{id}")
	public Affair getAffair(@PathVariable String id) {

		log.info("in getAffair with id " + id);
		try {
			UUID uuid = UUID.fromString(id);
			PageRequest request = new PageRequest(0,40);
//		Page page = affairRepository.findAll(request);

			Example<Affair> example = Example.of(new Affair(uuid));
			Optional<Affair> res = affairRepository.findOne(example);
			if(res.isPresent())
				return res.get();
			else
				return example.getProbe();
		}catch (IllegalArgumentException e){
			Affair res = new Affair();
			return res;
		}

	}

	@PostMapping
	public Affair generateContactUUID(@RequestBody Affair affair) {
		log.info("Received POST with affair " + affair);

		Affair res = new Affair(UUID.randomUUID());

//		Folder res = affairRepository.save(affair);
//		ObjectNode res = JsonNodeFactory.instance.objectNode().put("response", "test resp 34");
		return res;
	}

	@PutMapping("/{id}")
	public Affair editContact(@PathVariable String id, @RequestBody Affair affair) {

//		Folder existingContact = (Folder) affairRepository.findOne(id);
//		Assert.notNull(existingContact, "Folder not found");
//		existingContact.setLegal_name(affair.getLegal_name());
//		affairRepository.save(existingContact);

		log.info("in putContact with id " + id);
		try {
			UUID uuid = UUID.fromString(id);
		}catch (IllegalArgumentException e){
			log.info("Saving new affair.");
		}

		log.info("Received PUT with affair " + affair);

		Affair res = affairRepository.save(affair);
		return res;
	}

	@DeleteMapping("/{id}")
	public JsonNode deleteTask(@PathVariable UUID id) {

		Example<Affair> example = Example.of(new Affair(id));
		Optional<Affair> res = affairRepository.findOne(example);
		ObjectNode response = JsonNodeFactory.instance.objectNode();
		if(res.isPresent()) {
			affairRepository.delete(res.get());
			return response.put("response", "success");
		}else
			return response.put("response", "failure");


	}
}
