package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class User
{
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;


    @Override
    public boolean equals(Object other)
    {
        if (other == null || other.getClass() != this.getClass())
        {
            return false;
        }

        final User user = (User)other;

        return this.id == user.id &&
            this.name.equals(user.name) &&
            this.username.equals(user.username) &&
            this.email.equals(user.email) &&
            this.address.equals(user.address) &&
            this.phone.equals(user.phone) &&
            this.website.equals(user.website) &&
            this.company.equals(user.company);
    }


    @Override
    public int hashCode()
    {
        return this.id
            + this.name.hashCode()
            + this.username.hashCode()
            + this.email.hashCode()
            + this.address.hashCode()
            + this.phone.hashCode()
            + this.website.hashCode()
            + this.company.hashCode();
    }
}
