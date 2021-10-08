package application.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String userName;
    private String password;
    private String eMail;
    private Access access = Access.USER;


    private List<Blog> blogsList = new ArrayList<>();


    public User() {
    }

    public User(int id, String userName, String password, String eMail, Access access) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.eMail = eMail;
        this.access = access;
    }

    public User(String name, String pWord, String mail, Access user) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public List<Blog> getBlogsList() {
        return blogsList;
    }

    public void setBlogsList(List<Blog> blogsList) {
        this.blogsList = blogsList;
    }

    @Override
    public String toString() {
        return id + " - " +
                userName + ", " +
                password + ", " +
                eMail + ", " +
                access + "\n\t" +
                blogsList;
    }

}
