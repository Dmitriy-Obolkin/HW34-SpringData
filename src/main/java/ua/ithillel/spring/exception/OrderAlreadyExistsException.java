package ua.ithillel.spring.exception;

public class OrderAlreadyExistsException extends RuntimeException{
    public OrderAlreadyExistsException(int orderId) {
        super(String.format("Order with ID %s already exists!", orderId));
    }
}
