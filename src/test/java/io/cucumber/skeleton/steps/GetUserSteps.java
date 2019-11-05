package io.cucumber.skeleton.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.DataGenerator;
import io.cucumber.skeleton.RestClientHelper;
import io.cucumber.skeleton.User;

public class GetUserSteps {

	private String existingUserId;
	private User user;

	@Given("an existing user")
	public void an_existing_user() throws Exception {
		existingUserId = RestClientHelper.createTestUser().getId();
	}

	@When("I get a user with valid ID")
	public void i_get_a_user_with_valid_ID() throws Exception {
		user = RestClientHelper.getUser(existingUserId);
	}

	@Then("the user should exist")
	public void the_user_should_exist() throws Exception {
		assertNotNull(user);
		assertEquals(DataGenerator.TEST_USER_EMAIL, user.getEmail());
		RestClientHelper.deleteUser(user.getId());
	}

}
