package Model.Service;

import Model.DAO.CompanyDAO;
import Model.DAO.ProjectDAO;
import Model.Exceptions.DAOException;
import Model.Exceptions.ServiceException;
import Model.Project;

import java.util.List;

public class ProjectService implements IService<Project> {
    @Override
    public void create(Project project) throws ServiceException {
        ProjectDAO projectDAO = new ProjectDAO();
        try {
            projectDAO.create(project);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        ProjectDAO projectDAO = new ProjectDAO();
        try {
            projectDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public void update(Project persona) throws ServiceException {
        ProjectDAO projectDAO = new ProjectDAO();
        try {
            projectDAO.update(persona);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public List<Project> list() throws ServiceException {
        ProjectDAO projectDAO = new ProjectDAO();
        try {
            return projectDAO.list();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public Project search(int id) throws ServiceException {
        ProjectDAO projectDAO = new ProjectDAO();
        try {
            return projectDAO.search(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }
}
