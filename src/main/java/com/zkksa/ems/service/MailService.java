package com.zkksa.ems.service;


import com.zkksa.ems.model.Mail;

public interface MailService {
	
	public void send(Mail theMail, byte[] qrCode);

}
