package com.pankal.contact;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pankal.commons.DBUtilities;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by com.pankal on 12/11/17.
 */


@Table(name="contact_groups", schema="comm")
@Entity
public class ContactGroup {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;

	private String code;
	private String name;

	public ContactGroup() {}

	public ContactGroup(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "ContactGroup{" +
				"id=" + id +
				", code='" + code + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	public JsonNode toJsonNode(){
		return JsonNodeFactory.instance.objectNode()
				.put("id", id.toString())
				.put("code", code)
				.put("name", name);
	}


}
