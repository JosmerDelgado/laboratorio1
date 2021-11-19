package Model.DAO;

import Model.*;
import Model.Exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO implements IDAO<Company>{

    @Override
    public void create(Company company) throws DAOException {
        Connection connection = null;

        PreparedStatement sentenciaPS;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();
            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("INSERT INTO COMPANY (NAME) VALUES(?)");
            sentenciaPS.setString(1,company.getName());

            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros agregados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Company DAO Error");

        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Company DAO Error on close");

            }
        }
    }

    @Override
    public void delete(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement sentenciaPS = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("DELETE FROM COMPANY WHERE ID=?");
            sentenciaPS.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros borrados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Company DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Company DAO Error on close");

            }
        }
    }

    @Override
    public void update(Company company) throws DAOException {
        Connection connection = null;
        PreparedStatement sentenciaPS = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("UPDATE COMPANY SET NAME=? WHERE ID=?");
            sentenciaPS.setString(1, company.getName());
            sentenciaPS.setInt(2, company.getId());

            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros modificados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Company DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Company DAO Error on close");

            }
        }
    }

    @Override
    public List<Company> list() throws DAOException {
        List<Company> companyList = new ArrayList<>();
        Connection connection = null;
        Statement sentencia = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia
            sentencia = connection.createStatement();
            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentencia.executeQuery("SELECT C.ID ID, C.NAME COMPANY, P.NAME PROJECT, " +
                    "P.ID PROJECT_ID FROM COMPANY C JOIN PROJECT P on C.ID = P.COMPANY_ID join TASK T on P.ID = T.ID");
            //4 Evaluar resultados
            while(resultados.next()){
                int position = getCompanyListed(companyList, resultados.getInt("ID"));
                if(position != -1) {
                    List<Project> projects = new ArrayList<>();
                    Project project = new Project(resultados.getInt("PROJECT_ID"), resultados.getString("PROJECT"));
                    project.setCompanyId( resultados.getInt("ID"));
                    projects.add(project);
                    Company company = new Company(resultados.getString("NAME"), resultados.getInt("ID"), projects);
                    companyList.add(company);
                } else {
                    Project project = new Project(resultados.getInt("ID"), resultados.getString("PROJECT"));

                    companyList.get(position).addProject(project);
                }
            }
            resultados.close();
            sentencia.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Company DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Company DAO Error on close");

            }
        }

        return companyList;
    }

    @Override
    public Company search(int id) throws DAOException {
        Company company = null;
        PreparedStatement sentenciaPS = null;
        Connection connection = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("SELECT C.ID ID, C.NAME COMPANY, P.NAME PROJECT, " +
                    "P.ID PROJECT_ID FROM COMPANY C JOIN PROJECT P on C.ID = P.COMPANY_ID join TASK T on P.ID = T.ID WHERE C.ID=?");
            sentenciaPS.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentenciaPS.executeQuery();
            //4 Evaluar resultados
            List<Project> projects = new ArrayList<>();
            while(resultados.next()){

                Project project = new Project(resultados.getInt("PROJECT_ID"), resultados.getString("PROJECT"));
                project.setCompanyId(resultados.getInt("ID"));
                if(company == null) {
                    company = new Company(resultados.getString("NAME"), resultados.getInt("ID"), projects);
                } else {
                    company.addProject(project);
                }

            }
            resultados.close();
            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Company DAO Error");
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Company DAO Error on close");

            }
        }
        return company;
    }

    private int getCompanyListed(List<Company> companyList, int companyId){

        for (int i = 0; i < companyList.size(); i++) {
            if(companyId == companyList.get(i).getId()){
                return i;
            }
        }
        return -1;
    }
}
