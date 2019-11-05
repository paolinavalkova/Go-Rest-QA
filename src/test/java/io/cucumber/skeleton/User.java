package io.cucumber.skeleton;

import com.google.gson.annotations.SerializedName;

public class User {

	private String id;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("last_name")
	private String lastName;
	private String gender;
	private String dob;
	private String email;
	private String phone;
	private String website;
	private String address;
	private String status;

	public User(String firstName, String lastName, String gender, String dob, String email, String phone,
			String website, String address, String status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.address = address;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getWebsite() {
		return website;
	}

	public String getAddress() {
		return address;
	}

	public String getStatus() {
		return status;
	}

}