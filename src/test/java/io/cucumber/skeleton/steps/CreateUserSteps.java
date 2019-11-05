package io.cucumber.skeleton.steps;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.DataGenerator;
import io.cucumber.skeleton.RestClientHelper;
import io.cucumber.skeleton.User;

public class CreateUserSteps {

	private RestClientHelper.UserCreatedResponse response;
	private User user;

	@Given("a new User data")
	public void a_new_User_data() {
		user = DataGenerator.createUser();
	}

	@When("the User is added")
	public void the_User_is_added() throws Exception {
		response = RestClientHelper.createUser(user);
	}

	@Then("the User should be created")
	public void the_User_should_be_created() throws Exception {
		assertEquals(HttpURLConnection.HTTP_OK, response.meta.code);
		RestClientHelper.deleteUser(response.result.getId());
	}
}
