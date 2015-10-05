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

	/** Default context name for security logging purposes **/
	private static final String DEFAULT_CONTEXT = "defaultContext";
	/** Default string validation kind (see validation.properties) **/
	private static final String DEFAULT_VALIDATION_KIND = "SafeString";

	private static final int INPUT_MAX_LENGTH = 9999;
	public static final int INPUT_MAX_PASSWORD = 20;

	public static final String ACCESS_TOKEN_GENERAL = "access_token";

	public static final String USERNAME = "username";
	public static final String ACCESS_TOKEN_USUARIO = ACCESS_TOKEN_GENERAL;
	public static final String ACCESS_TOKEN_APP = ACCESS_TOKEN_GENERAL;
	public static final String HTTP_PARAM_IV_USER = "iv_user";
	public static final String HTTP_PARAM_IV_APP = "iv_application";

	// CODIGO DE COMERCIO EN REDSYS PARA CTT ASTURIAS (via TELEFONICA)
	public static final String IV_APP_TFN_CTA = "1";

	public static final String REGISTER_USER_ATTR_AT = ACCESS_TOKEN_GENERAL;
	public static final String REGISTER_USER_ATTR_BU = "back_url";

	public static final String AUTHORIZE_PARAM_RESPONSE_TYPE = "response_type";
	public static final String AUTHORIZE_PARAM_CLIENT_ID = "client_id";
	public static final String AUTHORIZE_PARAM_REDIRECT_URI = "redirect_uri";
	public static final String AUTHORIZE_PARAM_SCOPE = "scope";
	public static final String AUTHORIZE_PARAM_STATE = "state";

	public static final String AT_PARAM_GRANT_TYPE = "grant_type";
	public static final String AT_PARAM_GRANT_TYPE_VALUE = "client_credentials";
	public static final String AUTHORIZE_PARAM_SCOPE_VALUE = "read";
	public static final String AT_PARAM_CODE = "code";
	public static final String AT_PARAM_REDIRECT_URI = "redirect_uri";
	public static final String AT_PARAM_BEARER = "bearer";

	public static final String USER_SESSION_ATTR_AT = ACCESS_TOKEN_GENERAL;
	public static final String USER_SESSION_ATTR_BU = "back_url";

	public static final String CHANGE_PASS_USERID = "userid";
	public static final String CHANGE_PASS_NEWPASS = "newpass";
	public static final String CHANGE_PASSWORD_FIELDS_RESULT_OK = "CHANGE_PASS_OK";
	public static final String CHANGE_PASSWORD_FIELDS_RESULT_KO = "CHANGE_PASS_KO";

	public static final String LOGOUT_ATTR_BU = "back_url";

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
			logger.error("PCI-COMMON SHA-1 Hashing exception: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("PCI-COMMON SHA-1 Hashing exception: " + e.getMessage());
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
