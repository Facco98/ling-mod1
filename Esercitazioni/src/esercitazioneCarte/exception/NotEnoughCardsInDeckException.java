package esercitazioneCarte.exception;

public class NotEnoughCardsInDeckException extends Exception {

    private String message;

    public NotEnoughCardsInDeckException(String message){
        super(message);
    }

}
