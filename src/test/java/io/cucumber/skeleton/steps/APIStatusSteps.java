package io.cucumber.skeleton.steps;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;

import com.google.gson.Gson;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.skeleton.RestClientHelper;
import io.cucumber.skeleton.RestClientHelper.SimpleResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIStatusSteps {

	private OkHttpClient client;
	private Response response;
	private SimpleResponse errorResponse;

	@Given("I have a REST client")
	public void I_have_REST_client() throws Throwable {
		client = RestClientHelper.createRestClient();
	}

	@When("make a request")
	public void make_a_request() throws Throwable {
		HttpUrl url = RestClientHelper.createUrlBuilder().build();
		Request req = new Request.Builder().url(url).get().build();
		try (Response r = client.newCall(req).execute()) {
			response = r;
		}
	}

	@Then("the API response should be ok")
	public void the_API_response_should_be_ok() {
		assertEquals(HttpURLConnection.HTTP_OK, response.code());
	}

	@When("I make a request without access token")
	public void i_make_a_request_without_access_token() throws Exception {
		HttpUrl url = RestClientHelper.createUrlBuilder().build();

		Request req = new Request.Builder().url(url).get().build();
		try (Response r = client.newCall(req).execute()) {
			String responseBody = r.body().string();
			Gson gson = new Gson();
			errorResponse = gson.fromJson(responseBody,
					RestClientHelper.SimpleResponse.class);
		}
	}

	@Then("the request should fail")
	public void the_request_should_fail() throws Exception {
		assertEquals(HttpURLConnection.HTTP_UNAUTHORIZED, errorResponse.meta.code);
	}
}
