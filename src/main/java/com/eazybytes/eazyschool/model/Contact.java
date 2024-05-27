package com.eazybytes.eazyschool.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Contact {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile Phone must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Name must be at least 10 digits long")
    private String mobileNum;

    @NotBlank
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subject;

    @NotBlank(message = "Message must not be blank")
    @Size(min = 10, message = "Message must be at least 10 characters long")
    private String message;

}
