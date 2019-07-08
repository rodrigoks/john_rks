package br.com.johndeere.exceptions;

public class PeoplesException extends Exception {

	public PeoplesException() {
        super();
    }

    public PeoplesException(String arg0) {
        super(arg0);
    }

    public PeoplesException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public PeoplesException(Throwable arg0) {
        super(arg0);
    }
    
}
