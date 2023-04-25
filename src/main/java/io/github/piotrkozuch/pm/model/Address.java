package io.github.piotrkozuch.pm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

import static io.github.piotrkozuch.pm.util.Checks.checkRequired;

@Embeddable
public class Address {

    @Column(nullable = false)
    private String city;

    @Column
    private String postcode;

    @Column
    private String street;

    public Address() {

    }

    private Address(AddressBuilder builder) {
        this.city = checkRequired("city", builder.city);
        this.postcode = builder.postcode;
        this.street = builder.street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = checkRequired("city", city);
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(postcode, address.postcode) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, postcode, street);
    }

    @Override
    public String toString() {
        return "Address{" +
            "city='" + city + '\'' +
            ", postcode='" + postcode + '\'' +
            ", street='" + street + '\'' +
            '}';
    }

    public static class AddressBuilder {

        private String city;
        private String postcode;
        private String street;

        private AddressBuilder() {

        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public Address build() {
            return new Address(this);
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder postcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }
    }
}
