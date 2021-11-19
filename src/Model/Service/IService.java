package Model.Service;

import Model.Exceptions.ServiceException;

import java.util.List;

public interface IService<T> {
    public void create(T t) throws ServiceException;
    public void delete(int id) throws ServiceException;
    public void update(T persona) throws ServiceException;
    public List<T> list() throws ServiceException;
    public T search(int id) throws ServiceException;
}
