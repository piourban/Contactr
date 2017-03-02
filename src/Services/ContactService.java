package Services;

import DAO.ContactDAO;
import DAO.DAOFactory;
import Models.Contact;
import Models.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Piotr Urban on 27.02.2017.
 */
// Warstwa laczaca klase ContactDAO i operacje jakie moga byc realizowane w kontrolerze
public class ContactService {

    public void add(String name, String surname, String email, String category, String subcategory, String phone, Date bithday, User user) {
        Contact contact = new Contact(name, surname, email, category, subcategory, phone, bithday, user);
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        ContactDAO contactDAO = daoFactory.getContactDAO();
        contactDAO.create(contact);
    }

    // Metoda zwracajaca kontakt na podstawie ID
    public Contact getContactByID(long contactID) {

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        ContactDAO contactDAO = daoFactory.getContactDAO();
        Contact contact = contactDAO.read(contactID);

        return contact;
    }
    // Metoda wykonujaca update danego kontaktu i zwracajaca boolean'a w zaleznosci od zwroconego inta w metodzie update klasy ContactDAOImpl
    public boolean updateContact(Contact contact) {

        boolean result = false;
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        ContactDAO contactDAO = daoFactory.getContactDAO();
        result = contactDAO.update(contact);
        System.out.println(result);

        return result;
    }

    // Metoda zwracajaca kontakt na podstawie ID
    public List<Contact> getAllContact() {

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        ContactDAO contactDAO = daoFactory.getContactDAO();
        List<Contact> contacts = contactDAO.getAll();

        return contacts;
    }

    //Metoda zwracajaca liste kontaktow uzytkownika na podstawie ID uztkownika
    public List<Contact> getUserContacts(long userID) {

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        ContactDAO contactDAO = daoFactory.getContactDAO();
        List<Contact> contacts = contactDAO.getContactByUserID(userID);

        return contacts;
    }

    // Metoda usuwajaca kontakt na podstawie jego ID i zwracajaca boolean'a w zaleznosci od zwroconego inta w metodzie delete klasy ContactDAOImpl
    public boolean deleteContact(long contactID) {

        boolean result = false;

        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        ContactDAO contactDAO = daoFactory.getContactDAO();
        result = contactDAO.delete(contactID);

        return result;
    }

    // Metoda zwracajaca wybrana subkategorie, ktora moze byc wybrana z dropdown listy, ale tez wpisana w pole input.
    public static String setSubcategory(HttpServletRequest request) {

        String subcategoryFromSelect = request.getParameter("subcategory");
        String subcategoryFromInput = request.getParameter("inputSubcategory");
        String result = "-";

        if (subcategoryFromInput != null && subcategoryFromInput != "") {
            result = subcategoryFromInput;
            return result;
        } else if (subcategoryFromSelect != null && subcategoryFromSelect != "") {
            result = subcategoryFromSelect;
            return result;
        } else {return result;}
    }

}
