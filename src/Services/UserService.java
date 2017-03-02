package Services;

import DAO.DAOFactory;
import DAO.UserDAO;
import Models.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Piotr Urban on 25.02.2017.
 */
// Warstwa laczaca klase UserDAO a operacje jakie moga byc realizowane w kontrolerze
public class UserService {

    // Metoda dodajaca uzytkownika do bazy danych
    public void addUser(String username, String email, String password) {

        String md5Pass = encryptPassword(password);

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(md5Pass);
        user.setActive(true);

        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        userDao.create(user);
    }

    //Metoda hashowania hasla algorytmem MD5
    private String encryptPassword(String password) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(password.getBytes());
        String md5Password = new BigInteger(1, digest.digest()).toString(16);
        return md5Password;
    }

    // Metoda zwracajaca uzytkownika po podstawie ID
    public User getUserById(long userId) {

        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.read(userId);

        return user;
    }

    // Metoda zwracajaca uzytkownika po podstawie username'a
    public User getUserByUsername(String username) {

        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDao = factory.getUserDAO();
        User user = userDao.getUserByUsername(username);

        return user;
    }

}
