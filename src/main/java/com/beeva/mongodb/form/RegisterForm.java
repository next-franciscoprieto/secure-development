package com.beeva.mongodb.form;

import com.beeva.validation.Name;
import org.hibernate.validator.constraints.Email;

import java.io.Serializable;

public class RegisterForm implements Serializable {
	@Name
	private String name;
	@Email
	private String email;

	private String pass;

	private String pass1;

	public RegisterForm() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}
}
