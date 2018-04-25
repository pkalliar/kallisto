package com.pankal.protocol;

import com.pankal.commons.LocalDateTimeConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by com.pankal on 12/11/17.
 */


@Table(name="protocols", schema="comm")
@Entity
public class Protocol {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "prot_uuid", columnDefinition = "BINARY(16)")
	private UUID id;

	@Column(name = "creator_contact_uuid", columnDefinition = "BINARY(16)")
	private UUID creator_contact_uuid;

	private String theme, category;
	private Integer prot_num, prot_num_doc, priority, current_state;

//	@Column(name = "security_level")
	private Integer security_level;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column(name = "prot_date")
	private LocalDateTime protdate;

	public Protocol() {}

	public Protocol(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Integer getProt_num() {
		return prot_num;
	}

	public void setProt_num(Integer prot_num) {
		this.prot_num = prot_num;
	}

	public Integer getProt_num_doc() {
		return prot_num_doc;
	}

	public void setProt_num_doc(Integer prot_num_doc) {
		this.prot_num_doc = prot_num_doc;
	}

	public LocalDateTime getProtdate() {
		return protdate;
	}

	public void setProtdate(LocalDateTime prot_date) {
		this.protdate = prot_date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getCurrent_state() {
		return current_state;
	}

	public void setCurrent_state(Integer current_state) {
		this.current_state = current_state;
	}

	public Integer getSecurity_level() {
		return security_level;
	}

	public void setSecurity_level(Integer security_level) {
		this.security_level = security_level;
	}

	@Override
	public String toString() {
		return "Protocol{" +
				"id=" + id +
				", theme='" + theme + '\'' +
				", category='" + category + '\'' +
				", prot_num=" + prot_num +
				", prot_num_doc=" + prot_num_doc +
				", priority=" + priority +
				", current_state=" + current_state +
				", security_level=" + security_level +
				", protdate=" + protdate +
				'}';
	}
}
