package io.cucumber.skeleton.steps;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.RestClientHelper;
import io.cucumber.skeleton.RestClientHelper.SimpleResponse;
import io.cucumber.skeleton.User;

public class DeleteUserSteps {

	private User user;

	@Given("an User to delete")
	public void an_User_to_delete() throws Exception {
		user = RestClientHelper.createTestUser();
	}

	@When("I deleted the user")
	public void i_deleted_the_user() throws Exception {
		RestClientHelper.deleteUser(user.getId());
	}

	@Then("the user shouldn't exist")
	public void the_user_shouldn_t_exist() throws Exception {
		SimpleResponse userResponse = RestClientHelper.getUserResponse(user.getId());
		assertEquals(HttpURLConnection.HTTP_NOT_FOUND, userResponse.meta.code);
	}
}
