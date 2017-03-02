package DAO;

/**
 * Created by Piotr Urban on 23.02.2017.
 */
//Klasa fabryki umozliwiajaca w razie potrzeby przepiecie silnika bazy danych
public abstract class DAOFactory {

    public static final int MySQL = 1;

    public abstract ContactDAO getContactDAO();

    public abstract UserDAO getUserDAO();

    public static DAOFactory getDAOFactory() {

        DAOFactory daoFactory = null;
        daoFactory = getDaoFactory(MySQL);

        return daoFactory;
    }

    private static DAOFactory getDaoFactory (int type) {

        switch (type) {
            case MySQL: return new MySqlDAOFactory();
        default: throw new NullPointerException();
        }
    }
}
