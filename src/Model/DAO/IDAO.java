package Model.DAO;

import Model.Exceptions.DAOException;

import java.util.List;

public interface IDAO<T> {
    public void create(T t) throws DAOException;
    public void delete(int id) throws DAOException;
    public void update(T persona) throws DAOException;
    public List<T> list() throws DAOException;
    public T search(int id) throws DAOException;

}

