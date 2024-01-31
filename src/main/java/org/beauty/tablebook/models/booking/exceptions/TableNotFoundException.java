package org.beauty.tablebook.models.booking.exceptions;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException(Long tableId) {
    }
}
