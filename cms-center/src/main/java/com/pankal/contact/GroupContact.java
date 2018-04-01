package com.pankal.contact;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Table(name="group_contacts", schema="comm")
@Entity
public class GroupContact{

	@EmbeddedId
	private GroupContactId groupContactId;

	public GroupContact() {
	}


	public GroupContact(GroupContactId groupContactId) {
		this.groupContactId = groupContactId;
	}

	public GroupContactId getGroupContactId() {
		return groupContactId;
	}

	public void setGroupContactId(GroupContactId groupContactId) {
		this.groupContactId = groupContactId;
	}
}
