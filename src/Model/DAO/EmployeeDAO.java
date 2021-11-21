package Model.DAO;

import Model.Employee;
import Model.Exceptions.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

public class EmployeeDAO implements IDAO<Employee> {

    @Override
    public void create(Employee persona) throws DAOException {
        Connection connection = null;

        PreparedStatement sentenciaPS;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();
            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("INSERT INTO EMPLOYEE (NAME, ID, LAST_NAME, RATE_PER_HOUR) VALUES(?,?,?,?)");
            sentenciaPS.setString(1,persona.getName());
            sentenciaPS.setInt(2,persona.getIdentityNumber());
            sentenciaPS.setString(3,persona.getLastName());
            sentenciaPS.setDouble(4,persona.getRatePerHour());


            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros agregados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Employee DAO Error");

        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Employee DAO Error on close");

            }
        }
    }

    public void createAndAssign(Employee persona, int projectId) throws DAOException {
        Connection connection = null;

        PreparedStatement sentenciaPS;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();
            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("INSERT INTO EMPLOYEE (NAME, ID, LAST_NAME, RATE_PER_HOUR, PROJECT_ID) VALUES(?,?,?,?,?)");
            sentenciaPS.setString(1,persona.getName());
            sentenciaPS.setInt(2,persona.getIdentityNumber());
            sentenciaPS.setString(3,persona.getLastName());
            sentenciaPS.setDouble(4,persona.getRatePerHour());
            sentenciaPS.setInt(5,projectId);


            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros agregados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Employee DAO Error");

        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Employee DAO Error on close");

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

            sentenciaPS = connection.prepareStatement("DELETE FROM EMPLOYEE WHERE ID=?");
            sentenciaPS.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros borrados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Employee DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Employee DAO Error on close");

            }
        }
    }

    @Override
    public void update(Employee persona) throws DAOException {

        Connection connection = null;
        PreparedStatement sentenciaPS = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("UPDATE EMPLOYEE SET NAME=? WHERE ID=?");
            sentenciaPS.setString(1,persona.getName());
            sentenciaPS.setInt(2,persona.getIdentityNumber());

            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros modificados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Employee DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Employee DAO Error on close");

            }
        }
    }

    @Override
    public List<Employee> list() throws DAOException {

        List<Employee> listaPersonas = new ArrayList<>();
        Connection connection = null;
        Statement sentencia = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia
            sentencia = connection.createStatement();
            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentencia.executeQuery("SELECT * FROM EMPLOYEE");
            //4 Evaluar resultados
            while(resultados.next()){

                Employee persona = new Employee(resultados.getString("NAME"),
                        resultados.getString("LAST_NAME"),
                        resultados.getDouble("RATE_PER_HOUR"),
                        resultados.getInt("ID"));


                listaPersonas.add(persona);
            }
            resultados.close();
            sentencia.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Employee DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Employee DAO Error on close");

            }
        }

        return listaPersonas;
    }

    @Override
    public Employee search(int id) throws DAOException {
        Employee persona = null;
        PreparedStatement sentenciaPS = null;
        Connection connection = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE ID=?");
            sentenciaPS.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentenciaPS.executeQuery();
            //4 Evaluar resultados
            while(resultados.next()){
                persona = new Employee(resultados.getString("NAME"),
                        resultados.getString("LAST_NAME"),
                        resultados.getDouble("RATE_PER_HOUR"),
                        resultados.getInt("ID"));
            }
            resultados.close();
            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Employee DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Employee DAO Error on close");
            }
        }
        return persona;
    }
}
