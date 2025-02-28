package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Geo
{
    private String lat;
    private String lng;


    @Override
    public boolean equals(Object other)
    {
        if (other == null || other.getClass() != this.getClass())
        {
            return false;
        }

        final Geo geo = (Geo)other;

        return this.lat.equals(geo.lat) &&
            this.lng.equals(geo.lng);
    }


    @Override
    public int hashCode()
    {
        return this.lat.hashCode()
            + this.lng.hashCode();
    }
}
