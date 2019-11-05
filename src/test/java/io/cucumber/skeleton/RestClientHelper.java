package io.cucumber.skeleton;

import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClientHelper {

	public class Meta {
		public boolean success;
		public int code;
		public String message;
	}

	public class SimpleResponse {

		@SerializedName("_meta")
		public Meta meta;
	}

	public class UserCreatedResponse {
		@SerializedName("_meta")
		public Meta meta;

		public User result;
	}

	public static final String ACCESS_TOKEN = "_GVutA7errPDmXeKjLkCblyxpMUMMNtad4ik";

	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

	public static OkHttpClient createRestClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}
			} };

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
			builder.hostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.build();
	}

	public static HttpUrl.Builder createUrlBuilder() {
		return new HttpUrl.Builder()
				.scheme("https")
				.host("gorest.co.in")
				.addPathSegment("public-api")
				.addPathSegment("users")
				.addQueryParameter("_format", "json");
	}

	private static Builder requestBuilderForUrl(HttpUrl url) {
		return new Request.Builder()
				.addHeader("Authorization", "Bearer " + RestClientHelper.ACCESS_TOKEN)
				.url(url);
	}

	public static User createTestUser() throws Exception {
		return createUser(DataGenerator.createUser()).result;
	}

	public static UserCreatedResponse createUser(User user) throws Exception {
		HttpUrl url = RestClientHelper.createUrlBuilder()
				.build();
		Gson gson = new Gson();

		String json = gson.toJson(user);
		Request req = requestBuilderForUrl(url)
				.post(RestClientHelper.createPostBody(json))
				.build();
		try (Response r = createRestClient().newCall(req).execute()) {
			String responseBody = r.body().string();
			return gson.fromJson(responseBody,
					RestClientHelper.UserCreatedResponse.class);
		}
	}

	public static User getUser(String userId) throws Exception {
		HttpUrl url = RestClientHelper.createUrlBuilder()
				.addPathSegment(userId)
				.build();
		Gson gson = new Gson();

		Request req = requestBuilderForUrl(url)
				.get()
				.build();

		try (Response r = createRestClient().newCall(req).execute()) {
			String responseBody = r.body().string();
			RestClientHelper.UserCreatedResponse userResponse = gson.fromJson(responseBody,
					RestClientHelper.UserCreatedResponse.class);
			return userResponse.result;
		}
	}

	public static SimpleResponse getUserResponse(String userId) throws Exception {
		HttpUrl url = RestClientHelper.createUrlBuilder()
				.addPathSegment(userId)
				.build();
		Gson gson = new Gson();

		Request req = requestBuilderForUrl(url)
				.get()
				.build();

		try (Response r = createRestClient().newCall(req).execute()) {
			String responseBody = r.body().string();
			return gson.fromJson(
					responseBody,
					RestClientHelper.SimpleResponse.class);
		}
	}

	public static void updateUser(String userId, Map<String, String> data) throws Exception {
		HttpUrl url = RestClientHelper.createUrlBuilder()
				.addPathSegment(userId)
				.build();
		Gson gson = new Gson();

		String json = gson.toJson(data);
		Request req = requestBuilderForUrl(url)
				.put(RestClientHelper.createPostBody(json))
				.build();
		createRestClient().newCall(req).execute();
	}

	public static void deleteUser(String userId) throws Exception {
		HttpUrl url = createUrlBuilder()
				.addPathSegment(userId)
				.build();
		Request req = requestBuilderForUrl(url)
				.delete()
				.build();
		createRestClient().newCall(req).execute();
	}

	public static RequestBody createPostBody(String data) {
		return RequestBody.create(data, JSON);
	}

}
