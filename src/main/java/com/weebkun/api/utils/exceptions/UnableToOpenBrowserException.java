package com.weebkun.api.utils.exceptions;

/**
 * thrown when the OS cannot be found when opening the browser.
 */
public class UnableToOpenBrowserException extends RuntimeException {

    public UnableToOpenBrowserException(String message) {
        super(message);
    }

    public UnableToOpenBrowserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnableToOpenBrowserException(Throwable cause) {
        super("OS not found. thus could not open browser.", cause);
    }

    public UnableToOpenBrowserException() {
        super("OS not found. thus could not open browser.");
    }
}
