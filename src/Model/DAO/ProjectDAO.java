package Model.DAO;

import Model.Company;
import Model.Exceptions.DAOException;
import Model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements IDAO<Project>{

    @Override
    public void create(Project project) throws DAOException {
        Connection connection = null;

        PreparedStatement sentenciaPS;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();
            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("INSERT INTO PROJECT (NAME, COMPANY_ID, ID) VALUES(?,?,?)");
            sentenciaPS.setString(1,project.getName());
            sentenciaPS.setInt(2,project.getCompanyId());
            sentenciaPS.setInt(3,project.getId());


            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros agregados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Project DAO Error");

        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Project DAO Error on close");

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

            sentenciaPS = connection.prepareStatement("DELETE FROM PROJECT WHERE ID=?");
            sentenciaPS.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros borrados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Project DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Project DAO Error on close");

            }
        }
    }

    @Override
    public void update(Project persona) throws DAOException {
        Connection connection = null;
        PreparedStatement sentenciaPS = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("UPDATE PROJECT SET NAME=? WHERE ID=?");
            sentenciaPS.setString(1, persona.getName());
            sentenciaPS.setInt(2, persona.getId());

            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros modificados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Project DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Project DAO Error on close");

            }
        }
    }

    @Override
    public List<Project> list() throws DAOException {
        List<Project> projectList = new ArrayList<>();
        Connection connection = null;
        Statement sentencia = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia
            sentencia = connection.createStatement();
            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentencia.executeQuery("SELECT * FROM PROJECT");
            //4 Evaluar resultados
            while(resultados.next()){
                Project project = new Project(resultados.getInt("ID"), resultados.getString("NAME"));
                project.setCompanyId( resultados.getInt("ID"));
                projectList.add(project);
            }
            resultados.close();
            sentencia.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Project DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Project DAO Error on close");

            }
        }

        return projectList;
    }

    @Override
    public Project search(int id) throws DAOException {
        Project project = null;
        Connection connection = null;
        Statement sentencia = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia
            sentencia = connection.createStatement();
            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentencia.executeQuery("SELECT * FROM PROJECT");
            //4 Evaluar resultados
            while(resultados.next()){
                project = new Project(resultados.getInt("ID"), resultados.getString("NAME"));
                project.setCompanyId( resultados.getInt("ID"));
            }
            resultados.close();
            sentencia.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            throw new DAOException("SQL Project DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();

                throw new DAOException("SQL Project DAO Error on close");

            }
        }

        return project;
    }
}
