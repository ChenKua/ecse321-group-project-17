package ca.mcgill.ecse321.cooperator.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public abstract class Profile {
	private Set<Notification> received;

	@OneToMany(fetch = FetchType.EAGER)
	public Set<Notification> getReceived() {
		return this.received;
	}

	public void setReceived(Set<Notification> received) {
		this.received = received;
	}

	private Set<Notification> sent;

	@OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
	public Set<Notification> getSent() {
		return this.sent;
	}

	public void setSent(Set<Notification> sent) {
		this.sent = sent;
	}

	private String email;

	public void setEmail(String value) {
		this.email = value;
	}

	@Id
	public String getEmail() {
		return this.email;
	}

	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	private String password;

	public void setPassword(String value) {
		this.password = value;
	}

	@NotNull
	@NotEmpty
	public String getPassword() {
		return this.password;
	}

	private String phone;

	public void setPhone(String value) {
		this.phone = value;
	}

	public String getPhone() {
		return this.phone;
	}

}
