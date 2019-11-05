package io.cucumber.skeleton;

public class DataGenerator {

	public static final String TEST_USER_EMAIL = "jane@thelamp.com";

	public static User createUser() {
		return new User(
				"Jane",
				"Doe",
				"female",
				"1981-06-15",
				TEST_USER_EMAIL,
				"+359883765821",
				"https://genka-shikerova.bg",
				"Kazanlak 12",
				"active");
	}
}
