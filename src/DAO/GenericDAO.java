package DAO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Piotr Urban on 23.02.2017.
 */

// Interfejs udostępniajacy glownie metody CRUD oraz dodatkowa metode getAll() zwracajaca wszystkie wyniki z danej tabeli.
public interface GenericDAO <T, PK extends Serializable> {

    //CRUD
    T create(T newObject);
    T read(PK primaryKey);
    boolean update(T updateObject);
    boolean delete(PK key);
    List<T> getAll();
}
