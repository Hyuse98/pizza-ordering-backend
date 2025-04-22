package com.hyuse.pizzaOrderingBackend.user.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String country;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String houseNumber;
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(country, address.country) &&
                Objects.equals(state, address.state) &&
                Objects.equals(city, address.city) &&
                Objects.equals(neighborhood, address.neighborhood) &&
                Objects.equals(street, address.street) &&
                Objects.equals(houseNumber, address.houseNumber) &&
                Objects.equals(zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, state, city, neighborhood, street, houseNumber, zipCode);
    }

    @Override
    public String toString() {
        return street + ", " + houseNumber + " - " + neighborhood + ", " +
                city + " - " + state + ", " + country + ", " + zipCode;
    }

}
