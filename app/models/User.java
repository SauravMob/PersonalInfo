package models;

import play.data.validation.*;
import play.db.jpa.GenericModel;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends GenericModel
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;

    @Required
    @Column(name = "Name")
    public String fullname;

    @Required
    @Column(name = "Gender")
    public String gender;

    @Required
    @Column(name = "Continent")
    public String continent;

    @Required
    @Column(name = "Birthdate")
    public String DOB;

    @Required
    @Email
    @Column(unique = true)
    public String email;

    @Required
    @Column(name = "Password")
    public String password;

    public User(Long Id, String fullname, String gender, String continent, String DOB, String email, String password)
    {
        this.fullname = fullname;
        this.gender = gender;
        this.continent=continent;
        this.DOB = DOB;
        this.email = email;
        this.password = password;
    }

    public String toString() {
        return fullname;
    }
}
