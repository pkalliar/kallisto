package gr.mfa.efsima.contact;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class GroupContactId implements Serializable{

	@NotNull
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "contact_id", columnDefinition = "BINARY(16)")
	private UUID contact_id;

	@NotNull
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "group_id", columnDefinition = "BINARY(16)")
	private UUID group_id;

	public GroupContactId() {
	}

	public GroupContactId(UUID group_id) {
		this.group_id = group_id;
	}

	public GroupContactId(UUID contact_id, UUID group_id) {
		this.contact_id = contact_id;
		this.group_id = group_id;
	}

	public UUID getContact_id() {
		return contact_id;
	}

	public void setContact_id(UUID contact_id) {
		this.contact_id = contact_id;
	}

	public UUID getGroup_id() {
		return group_id;
	}

	public void setGroup_id(UUID group_id) {
		this.group_id = group_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GroupContactId that = (GroupContactId) o;

		if (contact_id.compareTo(that.contact_id) != 0) return false;
		else if (group_id.compareTo(that.group_id) != 0) return false;
		else return true;
	}

	@Override
	public int hashCode() {
		int result = contact_id.hashCode();
		result = 31 * result + group_id.hashCode();
		return result;
	}


}
