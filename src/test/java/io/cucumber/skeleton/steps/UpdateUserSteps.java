package io.cucumber.skeleton.steps;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.RestClientHelper;
import io.cucumber.skeleton.User;

public class UpdateUserSteps {

	private static final String NEW_ADDRESS = "Pencho Slaveikov 20";

	private User user;

	@Given("an User with wrong address")
	public void an_User_with_wrong_address() throws Exception {
		user = RestClientHelper.createTestUser();
	}

	@When("the address is updated")
	public void the_address_is_updated() throws Exception {
		Map<String, String> updateData = new HashMap<>();
		updateData.put("address", NEW_ADDRESS);

		RestClientHelper.updateUser(user.getId(), updateData);
	}

	@Then("the User should have the new address")
	public void the_User_should_have_the_new_address() throws Exception {
		User updatedUser = RestClientHelper.getUser(user.getId());
		assertEquals(NEW_ADDRESS, updatedUser.getAddress());
		RestClientHelper.deleteUser(user.getId());
	}
}
