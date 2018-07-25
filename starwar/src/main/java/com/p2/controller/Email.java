package com.p2.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	public static void sendEmail(String toEmail, String fname, String lname, String username, 
			int allegiance, String frontLink, String backLink, String subject) {
		final String empireEmail = "vader.no.escape@gmail.com";
		final String rebelEmail = "gial.ackbar.trap@gmail.com";
		final String password = "StarWars6";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(int allegiance) {
				if(allegiance == 1) {
					return new PasswordAuthentication(rebelEmail, password);
				} else {
					return new PasswordAuthentication(empireEmail, password);
				}				
			}
		  });

		try {
			
			Message message = new MimeMessage(session);
			if(allegiance == 1) {
				message.setFrom(new InternetAddress(rebelEmail));
			} else {
				message.setFrom(new InternetAddress(empireEmail));
			}
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmail));
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