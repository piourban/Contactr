package DAO;

/**
 * Created by Piotr Urban on 23.02.2017.
 */

public class MySqlDAOFactory extends DAOFactory {

    @Override
    public ContactDAO getContactDAO() {return new ContactDAOImpl();}

    @Override
    public UserDAO getUserDAO() { return new UserDAOImpl(); }


}
