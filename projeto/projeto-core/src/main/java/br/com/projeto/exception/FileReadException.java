package br.com.projeto.exception;

public class FileReadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5450291167878885721L;

	/**
     * Construtor da Classe.
     */
    public FileReadException() {
        super();
    }

    /**
     * Construtor que recebe a mensagem.
     * @param message
     */
    public FileReadException(String message) {
        super(message);
    }

    /**
     * Construtor que recebe a throwable.
     * @param throwable
     */
    public FileReadException(Throwable throwable) {
        super(throwable);
    }
    
    /**
     * @param exception
     */
    public FileReadException(Exception exception) {
        super(exception);
    }

    /**
     * Construtor que recebe a mensagem e a exception.
     * @param message
     * @param throwable
     */
    public FileReadException(String message, Throwable exception) {
        super(message, exception);
    }
}
