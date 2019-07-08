package br.com.johndeere.exceptions;

public class MoviesException extends Exception {

	public MoviesException() {
        super();
    }

    public MoviesException(String arg0) {
        super(arg0);
    }

    public MoviesException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public MoviesException(Throwable arg0) {
        super(arg0);
    }
    
}
