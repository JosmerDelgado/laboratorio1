package Model.Service;

import Model.Company;
import Model.DAO.CompanyDAO;
import Model.Exceptions.DAOException;
import Model.Exceptions.ServiceException;

import java.util.List;

public class CompanyService implements IService<Company> {
    @Override
    public void create(Company company) throws ServiceException {
        CompanyDAO companyDAO = new CompanyDAO();
        try {
            companyDAO.create(company);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        CompanyDAO companyDAO = new CompanyDAO();
        try {
            companyDAO.delete(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public void update(Company company) throws ServiceException {
        CompanyDAO companyDAO = new CompanyDAO();
        try {
            companyDAO.update(company);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public List<Company> list() throws ServiceException {
        CompanyDAO companyDAO = new CompanyDAO();
        try {
            return companyDAO.list();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }

    @Override
    public Company search(int id) throws ServiceException {
        CompanyDAO companyDAO = new CompanyDAO();
        try {
            return companyDAO.search(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Error Service");
        }
    }
}
