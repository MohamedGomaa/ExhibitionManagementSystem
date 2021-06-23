package com.zkksa.ems.service.implementation;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.zkksa.ems.model.Mail;
import com.zkksa.ems.model.Visit;
import com.zkksa.ems.repository.VisitRepository;
import com.zkksa.ems.service.MailService;
import com.zkksa.ems.service.VisitService;

@Service
@Transactional
public class VisitServiceImplementation implements VisitService {

	@Autowired
	private VisitRepository theVisitRepository;

	@Autowired
	private MailService theMailService;

	@Override
	public List<Visit> getAllVisits() {
		return theVisitRepository.findAll();
	}

	@Override
	public Visit getVisitById(UUID TheVisitId) {

		Optional<Visit> theOptionalVisit = theVisitRepository.findById(TheVisitId);
		Visit theVisit = null;
		if (theOptionalVisit.isPresent()) {
			theVisit = theOptionalVisit.get();
			return theVisit;
		} else {
			throw new RuntimeException("The visit wasn't created");
		}
	}

	@Override
	public void saveVisit(Visit theVisit) {
		theVisitRepository.save(theVisit);
	}

	@Override
	public void deleteVisitById(UUID theVisitId) {
		theVisitRepository.deleteById(theVisitId);
	}

	@Override
	public void sendMail(String emailDestination, UUID theVisitId) {
		Mail mail = new Mail();
		mail.setMailFrom("mohamed.gomaa.msp@gmail.com");
		mail.setMailTo(emailDestination);
		mail.setMailSubject("Visit - QRcode");
		mail.setMailContent(
				"Dear Mr./Mrs. \\nThank you for your registeration, \nUse this QR code for your invetation: ");

		theMailService.send(mail, getVisitQrCode(theVisitId));
	}

	private byte[] getVisitQrCode(UUID theVisitId) {

		try {
			QRCodeWriter theQrCodeWriter = new QRCodeWriter();
			BitMatrix theBitMatrix = theQrCodeWriter.encode(theVisitId.toString(), BarcodeFormat.QR_CODE, 300, 300);

			BufferedImage theBufferedImage = MatrixToImageWriter.toBufferedImage(theBitMatrix);
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(theBufferedImage  , "png", byteArrayOutputStream);
			
			return byteArrayOutputStream.toByteArray();
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		return null;
		
	}

}
