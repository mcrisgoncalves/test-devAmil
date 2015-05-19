package br.com.projeto.exception;

public class GameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5450291167878885721L;

	/**
     * Construtor da Classe.
     */
    public GameException() {
        super();
    }

    /**
     * Construtor que recebe a mensagem.
     * @param message
     */
    public GameException(String message) {
        super(message);
    }

    /**
     * Construtor que recebe a throwable.
     * @param throwable
     */
    public GameException(Throwable throwable) {
        super(throwable);
    }
    
    /**
     * @param exception
     */
    public GameException(Exception exception) {
        super(exception);
    }

    /**
     * Construtor que recebe a mensagem e a exception.
     * @param message
     * @param throwable
     */
    public GameException(String message, Throwable exception) {
        super(message, exception);
    }
}
