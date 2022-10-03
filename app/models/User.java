package models;

import play.data.validation.*;
import play.db.jpa.Model;
import javax.persistence.*;
import java.util.Calendar;
import java.sql.Date;

@Entity
public class User extends Model {

    @Required
    @Column(name = "Name")
    public String fullname;

    @Required
    @Column(name = "Gender")
    public String gender;

    @Required
    @Column(name = "Birthdate")
    public String DOB;

    @Required
    @Email
    @Column(name = "Email")
    public String email;

    @Required
    @Column(name = "Password")
    @Min(5)
    public String password;

    public User(String fullname, String gender, String DOB, String email, String password) {
        this.fullname = fullname;
        this.gender = gender;
        this.DOB = DOB;
        this.email = email;
        this.password = password;
        create();
    }
}
