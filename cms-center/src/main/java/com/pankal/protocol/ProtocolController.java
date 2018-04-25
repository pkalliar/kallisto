package com.pankal.protocol;

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
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/protocols")
@CrossOrigin("*")
public class ProtocolController {

	private final static int PAGESIZE = 200;

	@PersistenceContext
	private EntityManager em;

	private ProtocolRepository protocolRepository;
	private static ObjectMapper m = null;

	private static final Logger log = LoggerFactory.getLogger(ProtocolController.class);

	public ProtocolController(ProtocolRepository protocolRepository) {
		this.protocolRepository = protocolRepository;
		m = new ObjectMapper();
	}



	@GetMapping
	public List<Protocol> getProtocols() {
		log.info("in getProtocols");
//		PageRequest request = new PageRequest(0,200);

		String jql = "Select b from Protocol as b order by b.protdate";
		Query barQuery = em.createQuery(jql);
		List<Protocol> barList = barQuery.getResultList();

		PageRequest request = new PageRequest(0, PAGESIZE, Sort.Direction.DESC, "protdate");

		return protocolRepository.findAll(request).getContent();
//		Page page = protocolRepository.findAll(request);

//		return protocolRepository.findByCategoryOrderByThemeDesc("incoming");


//		return page.getContent();

//		return protocolRepository.findAll(request);
	}

	@GetMapping("/search/{name}")
	public List<Protocol> searchAffairs(@PathVariable String name) {
		log.info("in getProtocols search for " + name);

		return new ArrayList<Protocol>();
//		return protocolRepository.(name);

	}

	@GetMapping("/filter/{criteria}")
	@Transactional(readOnly = true)
	public List<Protocol> loadProtocols(@PathVariable String criteria) {
		log.info("in loadProtocols filter for " + criteria);
		JsonNode crit;
		List<Protocol> results = new ArrayList<>();
		try {
			crit = m.readTree(criteria);
			if(crit.isArray()) {
				if(((ArrayNode)crit).size() > 0) {
					Iterator<JsonNode> it = ((ArrayNode) crit).elements();
//				int ind = 0;

					while (it.hasNext()) {
						ObjectNode tdata = (ObjectNode) it.next();
						log.info("filter: " + tdata);
						String val = "%" + tdata.get("name").textValue() + "%";
						Stream<Protocol> stream = protocolRepository.
								findByThemeLikeAndCategoryOrderByProtdateDesc(val, "incoming");
						stream.forEach(c -> results.add(c));
					}
					return results;
				}else{
//					PageRequest request = new PageRequest(0, 40);
//					Page page = protocolRepository.findAll(request);
//					return page.getContent();

					return protocolRepository.findByCategoryOrderByProtdateDesc("incoming");
				}


			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
//		return protocolRepository.findByNameOrCode(criteria);
	}

	@GetMapping("/{id}")
	public Protocol getProtocol(@PathVariable String id) {

		log.info("in getAffair with id " + id);
		try {
			UUID uuid = UUID.fromString(id);
			PageRequest request = new PageRequest(0,40);
//		Page page = protocolRepository.findAll(request);

			Example<Protocol> example = Example.of(new Protocol(uuid));
			Optional<Protocol> res = protocolRepository.findOne(example);
			if(res.isPresent())
				return res.get();
			else
				return example.getProbe();
		}catch (IllegalArgumentException e){
			Protocol res = new Protocol();
			return res;
		}

	}

	@PostMapping
	public Protocol generateContactUUID(@RequestBody Protocol protocol) {
		log.info("Received POST with protocol " + protocol);

		Protocol res = new Protocol(UUID.randomUUID());

//		Folder res = protocolRepository.save(protocol);
//		ObjectNode res = JsonNodeFactory.instance.objectNode().put("response", "test resp 34");
		return res;
	}

	@PutMapping("/{id}")
	public Protocol edit(@PathVariable String id, @RequestBody Protocol protocol) {

//		Folder existingContact = (Folder) protocolRepository.findOne(id);
//		Assert.notNull(existingContact, "Folder not found");
//		existingContact.setLegal_name(protocol.getLegal_name());
//		protocolRepository.save(existingContact);

		log.info("in putContact with id " + id);
		try {
			UUID uuid = UUID.fromString(id);
		}catch (IllegalArgumentException e){
			log.info("Saving new protocol.");
		}

		log.info("Received PUT with protocol " + protocol);

		Protocol res = protocolRepository.save(protocol);
		return res;
	}

	@DeleteMapping("/{id}")
	public JsonNode delete(@PathVariable UUID id) {

		Example<Protocol> example = Example.of(new Protocol(id));
		Optional<Protocol> res = protocolRepository.findOne(example);
		ObjectNode response = JsonNodeFactory.instance.objectNode();
		if(res.isPresent()) {
			protocolRepository.delete(res.get());
			return response.put("response", "success");
		}else
			return response.put("response", "failure");


	}
}
