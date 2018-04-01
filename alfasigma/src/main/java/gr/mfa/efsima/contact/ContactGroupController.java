package gr.mfa.efsima.contact;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.com.pankal.commons.DBUtilities;
import com.pankal.commons.DBUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/contact-groups")
@CrossOrigin("*")
public class ContactGroupController {

	private static  ContactRepository contactRepository;
	private ContactGroupRepository contactGroupRepository;
	private GroupContactRepository groupContactRepository;
	private static ObjectMapper m = null;

	private static final Logger log = LoggerFactory.getLogger(ContactGroupController.class);

	public ContactGroupController(ContactRepository contactRepository, ContactGroupRepository contactGroupRepository, GroupContactRepository groupContactRepository) {
		this.contactRepository = contactRepository;
		this.contactGroupRepository = contactGroupRepository;
		this.groupContactRepository = groupContactRepository;
		m = new ObjectMapper();
	}

	@GetMapping("/{id}")
	public ContactGroup getContactGroup(@PathVariable String id) {

		log.info("in getContact with id " + id);
		try {
			UUID uuid = UUID.fromString(id);
			PageRequest request = new PageRequest(0,40);
//		Page page = contactRepository.findAll(request);

			Example<ContactGroup> example = Example.of(new ContactGroup(uuid));
			Optional<ContactGroup> res = contactGroupRepository.findOne(example);
//			Example<ContactGroupView> example = Example.of(new ContactGroupView(uuid));
//			ContactGroupView res = cgvr.findOne(example);

			if(res.isPresent()) {
				Example<GroupContact> ex2 = Example.of(new GroupContact(new GroupContactId(res.get().getId())));

				ObjectNode criteria = JsonNodeFactory.instance.objectNode().put("group_id", res.get().getId().toString());
				log.info("looking for group " + res.get().getId().toString());
				JsonNode res3 = DBUtilities.getTableData("*", "comm", "group_contacts", criteria, "", 100, 0);

//				log.info("found group " + res3.toString());

//				ArrayNode contacts = JsonNodeFactory.instance.arrayNode();
//				List<GroupContact> list = groupContactRepository.findByGroupId(res.getId());
//				for(GroupContact gc : list){
//					Contact res2 = contactRepository.findOne(Example.of(new Contact(gc.getGroupContactId().getContact_id())));
//					log.info("res2 " + res2.toString());
//					contacts.add(res2.toJsonNode());
//				}
//
				ObjectNode res4 = JsonNodeFactory.instance.objectNode();

				res4.set("group", res.get().toJsonNode());

				res4.set("contacts", res3);

				return res.get();
			}else
				return example.getProbe();
		}catch (IllegalArgumentException e){
			return null;
		}

	}

	public static List<Contact> getContactsForGroup(String id) {

		List<Contact> results = new ArrayList<>();

		ObjectNode criteria = JsonNodeFactory.instance.objectNode().put("group_id", id.toString());
//		log.info("looking for group " + id);
		JsonNode res3 = DBUtilities.getTableData("*", "comm", "group_contacts", criteria, "", 100, 0);
		if(res3.isArray()){
			for(JsonNode item : (ArrayNode)res3){
				Example<Contact> example = Example.of(new Contact(UUID.fromString(item.get("contact_id").asText())));
				Optional<Contact> res = contactRepository.findOne(example);
				if(res.isPresent())
					results.add(res.get());
			}
		}
		return results;
	}

	@GetMapping("/{id}/contacts")
	public List<Contact> getContactGroupContacts(@PathVariable String id) {

		return getContactsForGroup(id);

//		List<Contact> results = new ArrayList<>();
//
//		ObjectNode criteria = JsonNodeFactory.instance.objectNode().put("group_id", id);
//		log.info("looking for group " + id);
//		JsonNode res3 = DBUtilities.getTableData("*", "comm", "group_contacts", criteria, "", 100, 0);
//		if(res3.isArray()){
//			for(JsonNode item : (ArrayNode)res3){
//				Example<Contact> example = Example.of(new Contact(UUID.fromString(item.get("contact_id").asText())));
//				Contact res = contactRepository.findOne(example);
//				if(res != null)
//					results.add(contactRepository.findOne(example));
//			}
//		}
//		return results;
	}

	@PostMapping
	public Contact generateContactUUID(@RequestBody Contact contact) {
		log.info("Received POST with contact " + contact);

		Contact res = new Contact(UUID.randomUUID());

//		Folder res = contactRepository.save(contact);
//		ObjectNode res = JsonNodeFactory.instance.objectNode().put("response", "test resp 34");
		return res;
	}

	@PutMapping("/{id}")
	public Contact editContact(@PathVariable String id, @RequestBody Contact contact) {

//		Folder existingContact = (Folder) contactRepository.findOne(id);
//		Assert.notNull(existingContact, "Folder not found");
//		existingContact.setLegal_name(contact.getLegal_name());
//		contactRepository.save(existingContact);

		log.info("in putContact with id " + id);
		try {
			UUID uuid = UUID.fromString(id);
		}catch (IllegalArgumentException e){
			log.info("Saving new contact.");
		}

		log.info("Received PUT with contact " + contact);

		Contact res = contactRepository.save(contact);
		return res;
	}

	@DeleteMapping("/{id}")
	public JsonNode deleteTask(@PathVariable UUID id) {

		Example<Contact> example = Example.of(new Contact(id));
		Optional<Contact> res = contactRepository.findOne(example);
		ObjectNode response = JsonNodeFactory.instance.objectNode();
		if(res.isPresent()) {
			contactRepository.delete(res.get());
			return response.put("response", "success");
		}else
			return response.put("response", "failure");


	}
}
