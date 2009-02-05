package no.yr.exceptions;

import java.io.IOException;

public class YrException extends Exception {
	public YrException(String string, Exception e) {
		super(string, e);
	}
}
