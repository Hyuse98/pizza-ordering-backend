package com.hyuse.pizzaOrderingBackend.domain.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
    private String houseNumber;

    @NotBlank(message = "Cep Cannot be Blank")
    private String cep;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) &&
                Objects.equals(province, address.province) &&
                Objects.equals(city, address.city) &&
                Objects.equals(neighborhood, address.neighborhood) &&
                Objects.equals(street, address.street) &&
                Objects.equals(houseNumber, address.houseNumber) &&
                Objects.equals(cep, address.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, province, city, neighborhood, street, houseNumber, cep);
    }

    @Override
    public String toString() {
        return street + ", " + houseNumber + " - " + neighborhood + ", " +
                city + " - " + province + ", " + country + ", " + cep;
    }

}
