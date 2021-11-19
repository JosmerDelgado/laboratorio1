package Model.Service;

import Model.DAO.CompanyDAO;
import Model.DAO.TaskDAO;
import Model.Exceptions.DAOException;
import Model.Exceptions.ServiceException;
import Model.Task;

import java.util.List;

public class TaskService implements IService<Task>{
    @Override
    public void create(Task task) throws ServiceException {
        TaskDAO taskDAO = new TaskDAO();
        try {
            taskDAO.create(task);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        TaskDAO taskDAO = new TaskDAO();
        try {
            taskDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public void update(Task task) throws ServiceException {
        TaskDAO taskDAO = new TaskDAO();
        try {
            taskDAO.update(task);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public List<Task> list() throws ServiceException {
        TaskDAO taskDAO = new TaskDAO();
        try {
            return taskDAO.list();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public Task search(int id) throws ServiceException {
        TaskDAO taskDAO = new TaskDAO();
        try {
            return taskDAO.search(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }
}
