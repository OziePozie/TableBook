package org.beauty.tablebook.controllers.restaurant.exceptions;

import lombok.Getter;

@Getter
public class UserWithIDNotFoundException extends RuntimeException{
    private Long ID;
    public String text;

    public UserWithIDNotFoundException(Long ID) {

        this.text = String.format("User with ID %d Not Found", ID);
    }



}
