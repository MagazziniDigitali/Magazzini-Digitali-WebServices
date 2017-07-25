package it.bncf.magazziniDigitali.utils.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	private ElabGoogleAuthenticator authenticator = null;

	private String from = null;

	public SendEmail(String login, String password) {
		this.from = login;
		this.authenticator = new ElabGoogleAuthenticator(login, password);
	}

	public void sendMsg(String to, String subject, String msg) throws MessagingException {
		Properties properties = null;
		Session session = null;
		MimeMessage message = null;


		// Get system properties
		properties = System.getProperties();

		// Setup mail server

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		session = Session.getInstance(properties, authenticator);

		try {
			// Create a default MimeMessage object.
			message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Send the actual HTML message, as big as you like
			message.setContent(msg, "text/html");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
			throw mex;
		}
	}

	public String corpoMsg(String msg){
		return "<p align=\"right\">"
				+ "<img src=\"http://www.depositolegale.it/wp-content/uploads/2010/10/logo.png\" height=\"30px\"/>"
				+ "</p>"
				+ "<br/>"+
				msg+
				"Lo staff di Magazzini Digitali<br/>";
	}
}

class ElabGoogleAuthenticator extends Authenticator {

	private String login = null;

	private String password = null;

	public ElabGoogleAuthenticator(String login, String password){
		this.login = login;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(login, password);
	}
}