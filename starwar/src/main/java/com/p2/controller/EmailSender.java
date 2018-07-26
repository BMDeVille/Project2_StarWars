package com.p2.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public static void sendEmail(String toEmail, String fname, String lname, String username, 
			int allegiance, String frontLink, String backLink, String subject) {
		final String fromEmail[] = {"vader.no.escape@gmail.com", "gial.ackbar.trap@gmail.com"};
		final String password = "StarWars6";
		final int allied = allegiance - 1;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		System.out.println("In email sender");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail[allied], password);		
			}
		  });

		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail[allied]));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setText("Dear " + fname + " " + lname + ","
				+ "\n Please click the link below to access the password reset for your account."
					+ frontLink + username + backLink);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			System.out.println(e.getMessage() + " " + e.fillInStackTrace());
			throw new RuntimeException(e);
		}
	}
}