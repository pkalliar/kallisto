package gr.mfa.as.fakeloi;

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
@RequestMapping("/api/folders")
//@CrossOrigin("*")
public class FakeloiController {

	private static ObjectMapper m = null;

	private static final Logger log = LoggerFactory.getLogger(FakeloiController.class);

	public FakeloiController() {
		m = new ObjectMapper();
	}



	@GetMapping
	public List<gr.mfa.as.data.Fakeloi> getFakeloi() {
		List<gr.mfa.as.data.Fakeloi> fak = new ArrayList<gr.mfa.as.data.Fakeloi>();
		fak.add(new gr.mfa.as.data.Fakeloi("test1", 100));

		log.info("in getFakeloi");

		return fak;

//		return folderRepository.findAll(request);
	}

//	@GetMapping("/search/{name}")
//	public List<Folder> searchContacts(@PathVariable String name) {
//		log.info("in getContacts search for " + name);
//
////		return null;
//		return folderRepository.findByNameOrCode(name);
//
//	}
//
//	@GetMapping("/filter/{criteria}")
//	@Transactional(readOnly = true)
//	public List<Folder> loadContacts(@PathVariable String criteria) {
//		log.info("in getContacts filter for " + criteria);
//		JsonNode crit;
//		List<Folder> results = new ArrayList<>();
//		try {
//			crit = m.readTree(criteria);
//			if(crit.isArray()) {
//				if(((ArrayNode)crit).size() > 0) {
//					Iterator<JsonNode> it = ((ArrayNode) crit).elements();
////				int ind = 0;
//
//					while (it.hasNext()) {
//						ObjectNode tdata = (ObjectNode) it.next();
//						log.info("filter: " + tdata);
//						Stream<Folder> stream = folderRepository.findByCriteria(tdata.get("name").textValue());
//						stream.forEach(c -> results.add(c));
//					}
//					return results;
//				}else{
//					PageRequest request = new PageRequest(0, 40);
//					Page page = folderRepository.findAll(request);
//					return page.getContent();
//				}
//
//
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//
//
////		return null;
//		return folderRepository.findByNameOrCode(criteria);
//	}
//
//	@GetMapping("/{id}")
//	public Folder getContact(@PathVariable String id) {
//
//		log.info("in getContact with id " + id);
//		try {
//			UUID uuid = UUID.fromString(id);
//			PageRequest request = new PageRequest(0,40);
////		Page page = folderRepository.findAll(request);
//
//			Example<Folder> example = Example.of(new Folder(uuid));
//			Optional<Folder> res = folderRepository.findOne(example);
//			if(res.isPresent())
//				return res.get();
//			else
//				return example.getProbe();
//		}catch (IllegalArgumentException e){
//			Folder res = new Folder();
//			return res;
//		}
//
//	}
//
//	@PostMapping
//	public Folder generateContactUUID(@RequestBody Folder folder) {
//		log.info("Received POST with folder " + folder);
//
//		Folder res = new Folder(UUID.randomUUID());
//
////		Folder res = folderRepository.save(folder);
////		ObjectNode res = JsonNodeFactory.instance.objectNode().put("response", "test resp 34");
//		return res;
//	}
//
//	@PutMapping("/{id}")
//	public Folder editContact(@PathVariable String id, @RequestBody Folder folder) {
//
////		Folder existingContact = (Folder) folderRepository.findOne(id);
////		Assert.notNull(existingContact, "Folder not found");
////		existingContact.setLegal_name(folder.getLegal_name());
////		folderRepository.save(existingContact);
//
//		log.info("in putContact with id " + id);
//		try {
//			UUID uuid = UUID.fromString(id);
//		}catch (IllegalArgumentException e){
//			log.info("Saving new folder.");
//		}
//
//		log.info("Received PUT with folder " + folder);
//
//		Folder res = folderRepository.save(folder);
//		return res;
//	}
//
//	@DeleteMapping("/{id}")
//	public JsonNode deleteTask(@PathVariable UUID id) {
//
//		Example<Folder> example = Example.of(new Folder(id));
//		Optional<Folder> res = folderRepository.findOne(example);
//		ObjectNode response = JsonNodeFactory.instance.objectNode();
//		if(res.isPresent()) {
//			folderRepository.delete(res.get()
//			);
//			return response.put("response", "success");
//		}else
//			return response.put("response", "failure");
//
//
//	}
}
