package com.zkksa.ems.service.implementation;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.zkksa.ems.model.Mail;
import com.zkksa.ems.service.MailService;


@Service
public class MailServiceImpl implements MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	JavaMailSender mailSender;
	
	
	@Override
	public void send(Mail theMail, byte[] qrCode) {
	
		MimeMessage theMimeMessage = mailSender.createMimeMessage();
		try {
			
			logger.info(qrCode.toString());
			
            MimeMessageHelper theMimeHelper = new MimeMessageHelper(theMimeMessage,true);
			
			theMimeHelper.setSubject(theMail.getMailSubject());
			theMimeHelper.setFrom(theMail.getMailFrom());
			theMimeHelper.setTo(theMail.getMailTo());
            theMimeHelper.setText(theMail.getMailContent());
            
            theMimeHelper.addAttachment("QRCode", new ByteArrayResource(qrCode),"image/png");
    
            mailSender.send(theMimeHelper.getMimeMessage());
			
		}catch(MessagingException messageException){
			messageException.printStackTrace();
		} 
		
	}
	
}
	