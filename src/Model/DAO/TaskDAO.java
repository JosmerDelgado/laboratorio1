package Model.DAO;

import Model.Company;
import Model.Employee;
import Model.EventTask;
import Model.Exceptions.DAOException;
import Model.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaskDAO implements IDAO<Task>{

    @Override
    public void create(Task task) throws DAOException {
        Connection connection = null;

        PreparedStatement sentenciaPS;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();
            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("INSERT INTO TASK (TITLE, DESCRIPTION, ASSIGNED, ESTIMATION) VALUES(?,?,?,?)");
            sentenciaPS.setString(1,task.getTitle());
            sentenciaPS.setString(2,task.getDescription());

            sentenciaPS.setInt(3,task.getAssigned().getIdentityNumber());
            sentenciaPS.setInt(4,task.getEstimation());


            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros agregados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Task DAO Error");

        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Task DAO Error on close");

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

            sentenciaPS = connection.prepareStatement("DELETE FROM TASK WHERE ID=?");
            sentenciaPS.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros borrados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Task DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Task DAO Error on close");

            }
        }
    }

    @Override
    public void update(Task task) throws DAOException {
        Connection connection = null;
        PreparedStatement sentenciaPS = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("UPDATE TASK SET TITLE=?, DESCRIPTION=?, ASSIGNED=?, ESTIMATION=? WHERE ID=?");
            sentenciaPS.setString(1,task.getTitle());
            sentenciaPS.setString(2,task.getDescription());
            sentenciaPS.setInt(2,task.getAssigned().getIdentityNumber());
            sentenciaPS.setInt(2,task.getEstimation());

            //3 Ejecutar una sentencia SQL
            int registrosModificados = sentenciaPS.executeUpdate();
            System.out.println("Registros modificados: " + registrosModificados);

            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Task DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Task DAO Error on close");

            }
        }
    }

    @Override
    public List<Task> list() throws DAOException {
        List<Task> listaTask = new ArrayList<>();
        Connection connection = null;
        Statement sentencia = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia
            sentencia = connection.createStatement();
            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentencia.executeQuery("SELECT * FROM TASK T JOIN EMPLOYEE E on E.ID = T.ASSIGNED " +
                    "JOIN EVENT_TASK ET on T.ID = ET.TASK_ID " +
                    "JOIN EMPLOYEE E2 on E2.ID = ET.EMPLOYEE_ID");
            //4 Evaluar resultados
            while(resultados.next()){

                int position = getTaskListed(listaTask, resultados.getInt("T.ID"));
                Employee employeeTask = new Employee(resultados.getString("E2.NAME"),
                        resultados.getString("E2.LAST_NAME"),
                        resultados.getDouble("E2.RATE_PER_HOUR"),
                        resultados.getInt("E2.ID"));
                EventTask event = new EventTask(resultados.getString("ET.ACTION"),employeeTask);
                if(position == -1){
                    Employee persona = new Employee(resultados.getString("E.NAME"),
                            resultados.getString("E.LAST_NAME"),
                            resultados.getDouble("E.RATE_PER_HOUR"),
                            resultados.getInt("E.ID"));
                    List<EventTask> eventList = new ArrayList<>();
                    eventList.add(event);
                    Task task = new Task(resultados.getInt("T.ID"),
                            resultados.getString("TITLE")
                            );
                    task.setDescription(resultados.getString("DESCRIPTION"));
                    task.setEstimation(resultados.getInt("ESTIMATION"));
                    task.setAssigned(persona);
                    task.setEventHistory(eventList);

                    listaTask.add(task);
                } else {
                    listaTask.get(position).addEventHistory(event);
                }




            }
            resultados.close();
            sentencia.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Task DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Task DAO Error on close");

            }
        }

        return listaTask;
    }

    @Override
    public Task search(int id) throws DAOException {
        Task task = null;
        PreparedStatement sentenciaPS = null;
        Connection connection = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("SELECT * FROM TASK T JOIN EMPLOYEE E on E.ID = T.ASSIGNED " +
                    "JOIN EVENT_TASK ET on T.ID = ET.TASK_ID " +
                    "JOIN EMPLOYEE E2 on E2.ID = ET.EMPLOYEE_ID WHERE T.ID=?");
            sentenciaPS.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentenciaPS.executeQuery();
            //4 Evaluar resultados
            while(resultados.next()){
                Employee persona = new Employee(resultados.getString("NAME"),
                        resultados.getString("LAST_NAME"),
                        resultados.getDouble("RATE_PER_HOUR"),
                        resultados.getInt("E.ID"));
                Employee employeeTask = new Employee(resultados.getString("E2.NAME"),
                        resultados.getString("E2.LAST_NAME"),
                        resultados.getDouble("E2.RATE_PER_HOUR"),
                        resultados.getInt("E2.ID"));
                EventTask event = new EventTask(resultados.getString("ET.ACTION"),employeeTask);
                if(task == null){
                    List<EventTask> eventList = new ArrayList<>();
                    eventList.add(event);
                    task = new Task(resultados.getInt("T.ID"),
                            resultados.getString("TITLE"));
                    task.setDescription(resultados.getString("DESCRIPTION"));
                    task.setEstimation(resultados.getInt("ESTIMATION"));
                    task.setAssigned(persona);
                    task.setEventHistory(eventList);
                } else {
                    task.addEventHistory(event);
                }

            }
            resultados.close();
            sentenciaPS.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException("SQL Task DAO Error");

        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new DAOException("SQL Task DAO Error on close");

            }
        }
        return task;
    }

    private int getTaskListed(List<Task> taskList, int companyId){

        for (int i = 0; i < taskList.size(); i++) {
            if(companyId == taskList.get(i).getId()){
                return i;
            }
        }
        return -1;
    }
}
