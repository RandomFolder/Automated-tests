package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Company
{
    private String name;
    private String catchPhrase;
    private String bs;


    @Override
    public boolean equals(Object other)
    {
        if (other == null || other.getClass() != this.getClass())
        {
            return false;
        }

        final Company company = (Company)other;

        return this.name.equals(company.name) &&
            this.catchPhrase.equals(company.catchPhrase) &&
            this.bs.equals(company.bs);
    }


    @Override
    public int hashCode()
    {
        return this.name.hashCode()
            + this.catchPhrase.hashCode()
            + this.bs.hashCode();
    }
}
