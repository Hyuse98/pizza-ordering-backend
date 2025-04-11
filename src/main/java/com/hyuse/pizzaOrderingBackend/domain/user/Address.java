package com.hyuse.pizzaOrderingBackend.domain.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NotBlank(message = "Country Cannot be Blank")
    private String country;

    @NotBlank(message = "Province Cannot be Blank")
    private String province;

    @NotBlank(message = "City Cannot be Blank")
    private String city;

    @NotBlank(message = "Neighborhood Cannot be Blank")
    private String neighborhood;

    @NotBlank(message = "Street Cannot be Blank")
    private String street;

    @NotBlank(message = "Number Cannot be Blank")
    private String number;

    @NotBlank(message = "Cep Cannot be Blank")
    private String cep;

}
