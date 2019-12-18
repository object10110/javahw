package com.samarskii;

public class User{
    private String login;
    private String password;
    private Gender gender;
    private String phone;
    private String email;
    private boolean isSubscribe;

    public User(String login, String password, Gender gender, String phone, String email, boolean isSubscribe) {
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.isSubscribe = isSubscribe;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSubscribe() {
        return isSubscribe;
    }

    public String toHtmlTableRow(int number){
        return "<tr>" +
                "<td>" + number+"</td>" +
                "<td>" + login+"</td>" +
                "<td>" + password+"</td>" +
                "<td>" + gender+"</td>" +
                "<td>" + phone+"</td>" +
                "<td>" + email+"</td>" +
                "<td>" + isSubscribe+"</td>" +
               "</tr>";
    }
}
