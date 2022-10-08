package controllers;

import play.cache.Cache;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.libs.*;
import play.mvc.*;
<<<<<<< HEAD
=======

import java.sql.Date;
import java.util.*;
import java.util.concurrent.ExecutionException;

>>>>>>> ba735bd26107ec1ac871a79ad3dd5a73151a5b20
import models.*;

import java.util.List;


public class Application extends Controller
{
    public static void index()
    {
        render();
    }
    public static void home()
    {
        String randomID = Codec.UUID();
        render("Application/home.html",randomID);
    }

    public static void signup()
    {
        render();
    }

    public static void register(
            Long Id,
            @Required(message = "Type Your full name") String fullname,
            @Required(message = "Mention your gender") String Gender,
            @Required(message = "Mention your Date of Birth") String DOB,
            @Required(message = "Email is required") String email,
            @MinSize(value = 5, message = "At least 5 character is required")
            @Required(message = "Password is required") String password,
<<<<<<< HEAD
            @Required(message = "Verify password") String password2)
    {
        validation.equals(password2, password).message("Password must be same");
        if (validation.hasErrors())
        {
            render("Application/signup.html");
        }
        else
        {
            try
            {
                User user = new User(Id, fullname, Gender, DOB, email, password);
                user.create();
                flash.success("Welcome!! " + user.fullname);
                home();
            }
            catch (Exception e)
            {
                flash.error("Email already exists");
                render("Application/signup.html");
            }
=======
            @Required(message = "Verify password") String password2) {
        validation.equals(password2, password).message("Password verified");

        if (validation.hasErrors()){
            render("Application/signup.html");
        }
        try {
            User user = new User(fullname, Gender, DOB, email, password);
            flash.success("Welcome!! " + user.fullname);
            home();
        }
        catch (Exception e) {
            flash.error("Email is duplicate");
            render("Application/signup.html");
>>>>>>> ba735bd26107ec1ac871a79ad3dd5a73151a5b20
        }
    }

    public static void login(
            @Required(message = "Email is required") String email,
<<<<<<< HEAD
            @Required(message = "Password is required") String password,
            @Required(message = "Enter the code") String code,
            String randomID)
    {
        if (validation.hasErrors())
        {
            render("Application/home.html");
        }
        validation.equals(code, Cache.get(randomID));
        if (validation.hasErrors())
        {
            flash.error("Incorrect captcha");
            home();
        }
=======
            @Required(message = "Password is required") String password) {

>>>>>>> ba735bd26107ec1ac871a79ad3dd5a73151a5b20
        User user = User.find("byEmailAndPassword", email, password).first();
        if (user != null)
        {
            session.put("user", user);
            flash.success("Welcome, "+ user.fullname);
            render(user);
        }
        else
        {
            flash.error("Invalid Credentials");
            home();
        }
    }

    public static void captcha(String id)
    {
        Images.Captcha captcha = Images.captcha();
        String code = captcha.getText();
        Cache.set(id, code, "10min");
        renderBinary(captcha);
    }

    public static void delete(Long Id)
    {
        User user = User.findById(Id);
        user.delete();
        flash.success("Your Account is successfully Deleted");
        home();
    }

    public static void secure()
    {
        render();
    }

    public static void authenticate(
            @Required(message = "Email is required") String email,
            @Required(message = "Password is required") String password)
    {
        if (validation.hasErrors())
        {
            render("Application/secure.html");
        }

        String ogemail = "saurav123@mobavenue.com";
        String ogpassword = "1234567";

        validation.equals(email, ogemail);
        if (validation.hasErrors())
        {
            flash.error("Admin email required");
            secure();
        }

        validation.equals(password, ogpassword).message("Admin password required");
        if (validation.hasErrors())
        {
            flash.error("Admin password required");
            secure();
        }

        render("Application/Admin.html");
    }

    public static void iterations()
    {
        List<User> user = User.find("order by Id desc").fetch();
        render(user);
    }
    
    public static void logout()
    {
        flash.success("You have been logged out successfully");
        session.clear();
        home();
    }
}