package com.zkksa.ems.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.zkksa.ems.service.security.VisitorDetailsImplementation;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {

	private static final Logger theLogger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${zkksa.app.jwtSecret}")
	private String jwtSecret;

	@Value("${zkksa.app.jwtExpirationMs}")
	private int jwtExperationMs;

	public String generateToken(Authentication theAuthentication) {

		VisitorDetailsImplementation theVisitorPrincibal = (VisitorDetailsImplementation) theAuthentication.getPrincipal();

		return Jwts.builder().setSubject(theVisitorPrincibal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExperationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String theToken) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(theToken).getBody().getSubject();
	}

	public boolean validateToken(String theAuthToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(theAuthToken);
			return true;
		} catch (SignatureException excep) {
			theLogger.error("Invalid JWT signature: {}", excep.getMessage());
		} catch (MalformedJwtException e) {
			theLogger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			theLogger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			theLogger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			theLogger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
