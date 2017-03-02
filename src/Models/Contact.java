package Models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr Urban on 23.02.2017.
 */

//Klasa przechowujaca informacje o kontakcie
public class Contact {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String category;
    private String subcategory;
    private String phone;
    private User user;
    private Timestamp data;
    private Date birthday;

    public Contact() {   }

    public Contact(long id, String name, String surname, String email, String category, String subcategory, String phone, User user, Date birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.category = category;
        this.subcategory = subcategory;
        this.phone = phone;
        this.user = user;
        this.data = data;
        this.birthday = birthday;
    }

    public Contact(String name, String surname, String email, String category, String subcategory, String phone, Date birthday, User user) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.category = category;
        this.subcategory = subcategory;
        this.phone = phone;
        this.user = user;
        this.birthday = birthday;
        //this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", category='" + category + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", phone='" + phone + '\'' +
                ", bithday=" + birthday +
                '}';
    }

    public static List<String> getCategoryList() {

        List<String> categoryList = new ArrayList<>();
        categoryList.add("służbowy");
        categoryList.add("prywatny");
        categoryList.add("inny");

        return categoryList;
    }

    public static List<String> getSubcategoryList() {

        List<String> subcategoryList = new ArrayList<>();
        subcategoryList.add("szef");
        subcategoryList.add("klient");
        subcategoryList.add("współpracownik");

        return subcategoryList;
    }
}
