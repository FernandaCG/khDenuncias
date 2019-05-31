package com.helc.complain.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.helc.complain.entity.Mail;
import com.helc.complain.util.Constants;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailService {

	private static final Logger log = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private Configuration freemarkerConfig;

	/**
	 * 
	 * @param contact
	 * @param parameters
	 * @param template
	 * @param subject
	 * @throws MessagingException
	 */
	public void sendEmail(String contact, Map<String, String> parameters, String template, String subject)
			throws MessagingException {
		try {
			Mail mail = new Mail();
			mail.setFrom(Constants.EMAIL_ADDRESS);
			mail.setSubject(subject);
			mail.setTo(contact);
			mail.setModel(parameters);
			sendEmail(mail, template);
		} catch (IOException e) {
			log.error("Freemarker could not open the template");
		} catch (TemplateException e) {
			log.error("Template has errors");
		}
	}

	/**
	 * 
	 * @param mail
	 * @throws MessagingException
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void sendEmail(Mail mail, String templates) throws MessagingException, IOException, TemplateException {
		freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
		Template template = freemarkerConfig.getTemplate(templates);
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());

		MimeMessage msg = emailSender.createMimeMessage();
		msg.setFrom(new InternetAddress(Constants.EMAIL_ADDRESS, false));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo()));
		msg.setSubject(mail.getSubject());
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(html, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		emailSender.send(msg);
	}

}
