package com.osama.lifeshare;

/**
 * Created by Khizer Mehmood on 12/6/2018.
 */

public class User {

    String ID;
    String Name;
    String Email;
    String Password;
    String PhotoURL;
    String Gender;
    String Status;
    String Blood_Group;
    String Last_Donated;
    String Phone;


    public User() {

    }

    public User(String ID, String name, String email, String password, String photoURL, String gender, String status, String blood_Group, String last_Donated, String phone) {
        this.ID = ID;
        Name = name;
        Email = email;
        Password = password;
        PhotoURL = photoURL;
        Gender = gender;
        Status = status;
        Blood_Group = blood_Group;
        Last_Donated = last_Donated;
        Phone = phone;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhotoURL() {
        return PhotoURL;
    }

    public String getGender() {
        return Gender;
    }

    public String getStatus() {
        return Status;
    }

    public String getBlood_Group() {
        return Blood_Group;
    }

    public String getLast_Donated() {
        return Last_Donated;
    }

    public String getPhone() {
        return Phone;
    }
}
