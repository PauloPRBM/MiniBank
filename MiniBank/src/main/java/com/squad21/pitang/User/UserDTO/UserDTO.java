package com.squad21.pitang.User.UserDTO;
import jakarta.validation.constraints.*;

public record UserDTO(
        String name,
        @NotBlank(message = "Full name can't be empty")
        @Size(min = 3, max = 100, message = "Full name must have about 3 and 100 digits")

        @NotBlank(message = "Cpf can't be empty")
        @Pattern(regexp = "\\d{11}", message = "CPF must have 11 digits")
        // Validation of CPF
        String cpf,

        @NotBlank(message = "The e-mail can't be empty")
        @Email(message = "Invalid format of e-mail")
        String email,

        @NotBlank(message = "Password can't be empty")
        @Size(min = 6, message = "Password must have at least 6 digits")
        String password,

        @NotBlank(message = "Address can't be empty")
        String address
) {
}