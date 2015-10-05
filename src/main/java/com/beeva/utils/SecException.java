/**
 * 
 */
package com.beeva.utils;

/**
 * PCI Security Exception.
 * 
 * @author BEEVA
 * 
 */
public class SecException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public SecException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Message
	 * @param cause
	 *            Class that can be thrown.
	 */
	public SecException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * @param msg
	 *            Message
	 */
	public SecException(String msg) {
		super(msg);
	}

	/**
	 * @param paramThrowable
	 *            Class that can be thrown.
	 */
	public SecException(Throwable paramThrowable) {
		super(paramThrowable);
	}
}
