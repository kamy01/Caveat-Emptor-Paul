package email;

import java.util.Properties;

public class Email {
	public static final String SUBJECT = "Email activation link";
	public static final String VALIDATION_LINK = "http://localhost:8080/Web/activate.jsf" + "" + "";

	public static final String SENDER_USERNAME = "rofortechbidding@gmail.com";
	public static final String SENDER_PASSWORD = "rofortechbidding3";
	private Properties proprieties;

	public Email() {
		setProprieties();
	}

	public void setProprieties() {
		proprieties = new Properties();
		proprieties.put("mail.smtp.auth", "true");
		proprieties.put("mail.smtp.starttls.enable", "true");
		proprieties.put("mail.smtp.host", "smtp.gmail.com");
		proprieties.put("mail.smtp.port", "587");
	}

	public Properties getProprieties() {
		return proprieties;
	}

	public void setProprieties(Properties proprieties) {
		this.proprieties = proprieties;
	}

}
