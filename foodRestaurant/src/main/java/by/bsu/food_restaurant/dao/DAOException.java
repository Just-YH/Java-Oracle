package by.bsu.food_restaurant.dao;

/**
 * Created by evgeniy on 4.7.16.
 */
public class DAOException extends Exception {
    public DAOException(){}
    public DAOException(String message, Throwable exception) {
        super(message, exception);
    }
    public DAOException(String message) {
        super(message);
    }
    public DAOException(Throwable exception) {
        super(exception);
    }
}