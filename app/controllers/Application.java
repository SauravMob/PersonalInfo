package controllers;

import play.*;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.*;

import java.sql.Date;
import java.util.*;

import models.*;

import javax.validation.Valid;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void home() {
        render();
    }

    public static void signup() {
        render();
    }

    public static void register(
            @Required(message = "Type Your full name") String fullname,
            @Required(message = "Mention your gender") String Gender,
            @Required(message = "Mention your Date of Birth") String DOB,
            @Required(message = "Email is required") String email,
            @Required(message = "Password is required") String password,
            @Required(message = "Verify password") String password2) {
        validation.equals(password2, password).message("Password verified");
        if (validation.hasErrors()){
            render("Application/signup.html");
        }
        User user = new User(fullname, Gender, DOB, email, password);
        flash.success("Welcome!! " + user.fullname);
        home();
    }

    public static void login(
            @Required(message = "Email is required") String email,
            @Required(message = "Password is required") String password) {
        User user = User.find("byEmailAndPassword", email, password).first();
        if (user != null) {
            flash.success("Welcome, "+ user.fullname);
            render(user);
        }
        flash.error("Invalid Credentials");
        home();
    }
}