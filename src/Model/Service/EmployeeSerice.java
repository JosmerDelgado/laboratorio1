package Model.Service;

import Model.DAO.EmployeeDAO;
import Model.Employee;
import Model.Exceptions.DAOException;
import Model.Exceptions.ServiceException;

import java.util.List;

public class EmployeeSerice implements IService<Employee> {

    @Override
    public void create(Employee employee) throws ServiceException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            employeeDAO.create(employee);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            employeeDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }

    }

    @Override
    public void update(Employee persona) throws ServiceException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            employeeDAO.update(persona);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public List<Employee> list() throws ServiceException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            return employeeDAO.list();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public Employee search(int id) throws ServiceException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        try {
            return employeeDAO.search(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }
}
