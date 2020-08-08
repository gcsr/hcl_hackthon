package hcl.hackthon.interview.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hcl.hackthon.interview.model.Role;

@Entity
@Table
public class Librarian {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private Integer librarianId;
	private String fName;
	private String lName;
	private String phone;
	private String email;
	private String address;
	private Role loginRole;
	private String userId;
	private String password;
	public Integer getLibrarianId() {
		return librarianId;
	}
	public void setLibrarianId(Integer librarianId) {
		this.librarianId = librarianId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Role getLoginRole() {
		return loginRole;
	}
	public void setLoginRole(Role loginRole) {
		this.loginRole = loginRole;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Librarian [librarianId=" + librarianId + ", fName=" + fName + ", lName=" + lName + ", phone=" + phone
				+ ", email=" + email + ", address=" + address + ", loginRole=" + loginRole + ", userId=" + userId
				+ ", password=" + password + "]";
	}

	



}

