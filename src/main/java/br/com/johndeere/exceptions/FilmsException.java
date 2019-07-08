package br.com.johndeere.exceptions;

public class FilmsException extends Exception {

	public FilmsException() {
        super();
    }

    public FilmsException(String arg0) {
        super(arg0);
    }

    public FilmsException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FilmsException(Throwable arg0) {
        super(arg0);
    }
    
}
