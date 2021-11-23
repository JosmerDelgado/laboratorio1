package Model.DAO;

import Model.*;
import Model.Exceptions.DAOException;

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

            sentenciaPS = connection.prepareStatement("INSERT INTO TASK (TITLE, DESCRIPTION, ASSIGNED, ESTIMATION, ID, PROJECT_ID) VALUES(?,?,?,?,?,?)");
            sentenciaPS.setString(1,task.getTitle());
            sentenciaPS.setString(2,task.getDescription());

            sentenciaPS.setInt(3,task.getAssigned().getIdentityNumber());
            sentenciaPS.setInt(4,task.getEstimation());
            sentenciaPS.setInt(5, task.getId());

            sentenciaPS.setInt(6, task.getProject().getId());


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

    public void create(Task task, int projectId) throws DAOException {
        Connection connection = null;

        PreparedStatement sentenciaPS;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();
            //2 Crear una sentencia

            sentenciaPS = connection.prepareStatement("INSERT INTO TASK (TITLE, DESCRIPTION, ASSIGNED, ESTIMATION, PROJECT_ID, ID) VALUES(?,?,?,?,?,?)");
            sentenciaPS.setString(1,task.getTitle());
            sentenciaPS.setString(2,task.getDescription());

            sentenciaPS.setInt(3,task.getAssigned().getIdentityNumber());
            sentenciaPS.setInt(4,task.getEstimation());
            sentenciaPS.setInt(5, projectId);
            sentenciaPS.setInt(6, task.getId());



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

            sentenciaPS = connection.prepareStatement("UPDATE TASK SET TITLE=?, DESCRIPTION=?, ASSIGNED=?, ESTIMATION=?, PROJECT_ID=? WHERE ID=?");
            sentenciaPS.setString(1,task.getTitle());
            sentenciaPS.setString(2,task.getDescription());
            sentenciaPS.setInt(3,task.getAssigned().getIdentityNumber());
            sentenciaPS.setInt(4,task.getEstimation());
            sentenciaPS.setInt(5, task.getProject().getId());
            sentenciaPS.setInt(6,task.getId());

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
            ResultSet resultados =  sentencia.executeQuery("SELECT T.ID ID, T.TITLE TITLE, T.DESCRIPTION DESCRIPTION, T.ESTIMATION ESTIMATION," +
                    "E.ID ID_EMPLOYEE, E.NAME NAME, E.LAST_NAME LAST_NAME, E.RATE_PER_HOUR RATE_PER_HOUR, E.PROJECT_ID EMPLOYEE_PROJECT FROM TASK T JOIN EMPLOYEE E on E.ID = T.ASSIGNED");
            //4 Evaluar resultados
            while(resultados.next()){

//                int position = getTaskListed(listaTask, resultados.getInt("T.ID"));
//                Employee employeeTask = new Employee(resultados.getString("E.NAME"),
//                        resultados.getString("E.LAST_NAME"),
//                        resultados.getDouble("E.RATE_PER_HOUR"),
//                        resultados.getInt("ID_EMPLOYEE"));
//                EventTask event = new EventTask(resultados.getString("ET.ACTION"),employeeTask);
//                if(position == -1){
                    Employee persona = new Employee(resultados.getString("NAME"),
                            resultados.getString("LAST_NAME"),
                            resultados.getDouble("RATE_PER_HOUR"),
                            resultados.getInt("ID_EMPLOYEE"),
                            resultados.getInt("EMPLOYEE_PROJECT"));
                    List<EventTask> eventList = new ArrayList<>();
//                    eventList.add(event);
                    Task task = new Task(resultados.getInt("ID"),
                            resultados.getString("TITLE")
                            );
                    task.setDescription(resultados.getString("DESCRIPTION"));
                    task.setEstimation(resultados.getInt("ESTIMATION"));
                    task.setAssigned(persona);
                    task.setEventHistory(eventList);

                    listaTask.add(task);
//                } else {
////                    listaTask.get(position).addEventHistory(event);
//                }




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

    public List<Task> list(int projectId) throws DAOException {
        List<Task> listaTask = new ArrayList<>();
        Connection connection = null;
        PreparedStatement sentencia = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionManager.conect();

            //2 Crear una sentencia
            sentencia = connection.prepareStatement("SELECT T.ID ID, T.TITLE TITLE, T.DESCRIPTION DESCRIPTION, T.ESTIMATION ESTIMATION," +
                    "E.ID ID_EMPLOYEE, E.NAME NAME, E.LAST_NAME LAST_NAME, E.RATE_PER_HOUR RATE_PER_HOUR, E.PROJECT_ID EMPLOYEE_PROJECT " +
                    "FROM TASK T JOIN EMPLOYEE E on E.ID = T.ASSIGNED WHERE T.PROJECT_ID = ?");
            sentencia.setInt(1,projectId);
            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentencia.executeQuery();
            //4 Evaluar resultados
            while(resultados.next()){

//                int position = getTaskListed(listaTask, resultados.getInt("T.ID"));
//                Employee employeeTask = new Employee(resultados.getString("E.NAME"),
//                        resultados.getString("E.LAST_NAME"),
//                        resultados.getDouble("E.RATE_PER_HOUR"),
//                        resultados.getInt("ID_EMPLOYEE"));
//                EventTask event = new EventTask(resultados.getString("ET.ACTION"),employeeTask);
//                if(position == -1){
                Employee persona = new Employee(resultados.getString("NAME"),
                        resultados.getString("LAST_NAME"),
                        resultados.getDouble("RATE_PER_HOUR"),
                        resultados.getInt("ID_EMPLOYEE"),
                        resultados.getInt("EMPLOYEE_PROJECT"));
                List<EventTask> eventList = new ArrayList<>();
//                    eventList.add(event);
                Task task = new Task(resultados.getInt("ID"),
                        resultados.getString("TITLE")
                );
                task.setDescription(resultados.getString("DESCRIPTION"));
                task.setEstimation(resultados.getInt("ESTIMATION"));
                task.setAssigned(persona);
                task.setEventHistory(eventList);

                listaTask.add(task);
//                } else {
////                    listaTask.get(position).addEventHistory(event);
//                }




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

            sentenciaPS = connection.prepareStatement("SELECT T.ID ID, T.TITLE TITLE, T.DESCRIPTION DESCRIPTION, " +
                    "T.PROJECT_ID TASK_PROJECT, " +
                    "T.ESTIMATION ESTIMATION, E.ID ID_EMPLOYEE, E.NAME NAME, E.LAST_NAME LAST_NAME, " +
                    "E.RATE_PER_HOUR RATE_PER_HOUR, E.PROJECT_ID EMPLOYEE_PROJECT " +
                    "FROM TASK T JOIN EMPLOYEE E on E.ID = T.ASSIGNED WHERE T.ID=?");

            sentenciaPS.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet resultados =  sentenciaPS.executeQuery();
            //4 Evaluar resultados
            while(resultados.next()){
                Employee persona = new Employee(resultados.getString("NAME"),
                        resultados.getString("LAST_NAME"),
                        resultados.getDouble("RATE_PER_HOUR"),
                        resultados.getInt("ID_EMPLOYEE"), resultados.getInt("EMPLOYEE_PROJECT"));
//                Employee employeeTask = new Employee(resultados.getString("E2.NAME"),
//                        resultados.getString("E2.LAST_NAME"),
//                        resultados.getDouble("E2.RATE_PER_HOUR"),
//                        resultados.getInt("E2.ID"), resultados.getInt("E2.PROJECT_ID"));
//                EventTask event = new EventTask(resultados.getString("ET.ACTION"),employeeTask);
//                if(task == null){
//                    List<EventTask> eventList = new ArrayList<>();
//                    eventList.add(event);
                    task = new Task(resultados.getInt("ID"),
                            resultados.getString("TITLE"));
                    task.setDescription(resultados.getString("DESCRIPTION"));
                    task.setEstimation(resultados.getInt("ESTIMATION"));
                    task.setAssigned(persona);
                    Project project = new Project(resultados.getInt("TASK_PROJECT"));
                    task.setProject(project);
//                    task.setEventHistory(eventList);
//                } else {
//                    task.addEventHistory(event);
//                }

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
