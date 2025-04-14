package com.hyuse.pizzaOrderingBackend.user.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

//    @NotBlank(message = "Nome é obrigatório")
    private String firstName;

//    @NotBlank(message = "Sobrenome é obrigatório")
    private String lastName;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String emailAddress;

//    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "Invalid phone number format (e.g., 10 or 11 digits)")
    private String phoneNumber;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, message = "Senha deve ter pelo menos 8 caracteres")
    private String passwordValue;

//    @NotBlank(message = "País é obrigatório")
    private String country;

//    @NotBlank(message = "Estado é obrigatório")
    private String state;

//    @NotBlank(message = "Cidade é obrigatória")
    private String city;

//    @NotBlank(message = "Bairro é obrigatório")
    private String neighborhood;

//    @NotBlank(message = "Endereço é obrigatório")
    private String street;

//    @NotBlank(message = "Número é obrigatório")
    private String houseNumber;

//    @NotBlank(message = "CEP é obrigatório")
    private String zipCode;


}