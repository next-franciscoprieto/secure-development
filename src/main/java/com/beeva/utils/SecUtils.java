package com.beeva.utils;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.EncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * @author BEEVA
 * 
 */
public class SecUtils implements Serializable {

	private static final long serialVersionUID = 7691041577858767792L;

	private static Logger logger = LoggerFactory.getLogger(SecUtils.class);

	/**
	 * 
	 */
	protected SecUtils() {
		super();
	}


	/**
	 * Encodes the data for use in HTML
	 * 
	 * @param data
	 * @return Encoded data
	 */
	public static String encodeForHTML(String data) {

		return ESAPI.encoder().encodeForHTML(data);

	}

	/**
	 * Encodes the data for use in HTML attributes
	 * 
	 * @param data
	 * @return Encoded data
	 */
	public static String encodeForHTMLAttribute(String data) {

		return ESAPI.encoder().encodeForHTMLAttribute(data);

	}

	/**
	 * Encodes the data for use in JavaScript
	 * 
	 * @param data
	 * @return Encoded data
	 */
	public static String encodeForJavaScript(String data) {

		return ESAPI.encoder().encodeForJavaScript(data);

	}

	/**
	 * Encodes the data for use in XML
	 * 
	 * @param data
	 * @return Encoded data
	 */
	public static String encodeForXML(String data) {

		return ESAPI.encoder().encodeForXML(data);

	}

	/**
	 * Encodes the data for use in XML attributes
	 * 
	 * @param data
	 * @return Encoded data
	 */
	public static String encodeForXMLAttribute(String data) {

		return ESAPI.encoder().encodeForXMLAttribute(data);

	}

	/**
	 * Encodes the data for use in CSS
	 * 
	 * @param data
	 * @return Encoded data
	 */
	public static String encodeForCSS(String data) {

		return ESAPI.encoder().encodeForCSS(data);

	}

	/**
	 * Encodes the data for use in URL
	 * 
	 * @param data
	 * @return Encoded data
	 * @throws SecException
	 */
	public static String encodeForURL(String data) throws SecException {

		try {
			return ESAPI.encoder().encodeForURL(data);
		} catch (EncodingException ee) {
			throw new SecException(ee);
		}
	}

	/**
	 * Función HASH con algoritmo SHA1. La salida son 40 caracteres.
	 * 
	 * @param str
	 * @return
	 */
	public static String encryptSHA1(String str) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(str.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			logger.error("SHA-1 Hashing exception: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error(" SHA-1 Hashing exception: " + e.getMessage());
		}
		return sha1;
	}

	/**
	 * @param hash
	 * @return
	 */
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
