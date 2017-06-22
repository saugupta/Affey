package com.affey.util;

public class AffeyException extends RuntimeException {

	    /**
	     * Constructs a new exception with the given exception.
	     * @param e The causing exception.
	     * @see java.lang.RuntimeException#RuntimeException
	     */
	    public AffeyException(Exception e) {
	        this(e.getMessage(), e);
	    }

	    /**
	     * Constructs a new exception with a given message
	     * @param message The detail message.
	     * @see java.lang.RuntimeException#RuntimeException(java.lang.String)
	     */
	    public AffeyException(String message) {
	        super(message);
	    }

	    /**
	     * Constructs a new exception with a given message and exception.
	     * @param message The detail message.
	     * @param e The causing exception.
	     * @see java.lang.RuntimeException#RuntimeException
	     */
	    public AffeyException(String message, Exception e) {
	        super(message, e);
	    }
}
