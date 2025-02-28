package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Address
{
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;


    @Override
    public boolean equals(Object other)
    {
        if (other == null || other.getClass() != this.getClass())
        {
            return false;
        }

        final Address address = (Address)other;

        return this.street.equals(address.street) &&
            this.suite.equals(address.suite) &&
            this.city.equals(address.city) &&
            this.zipcode.equals(address.zipcode) &&
            this.geo.equals(address.geo);
    }


    @Override
    public int hashCode()
    {
        return this.street.hashCode()
            + this.suite.hashCode()
            + this.city.hashCode()
            + this.zipcode.hashCode()
            + this.geo.hashCode();
    }
}
