package Model.TableModel;

import Model.Employee;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class EmployeeTableModel extends AbstractTableModel {

    /**
     * INDICES DE MIS COLUMNAS
     */
    private static final int COLUMNA_ID = 0;
    private static final int COLUMNA_NAME = 1;
    private static final int COLUMNA_LAST_NAME = 2;
    private static final int COLUMNA_RATE_PER_HOUR = 3;

    /**
     * NOMBRES DE LOS ENCABEZADOS
     */
    private String[] nombresColumnas = {"ID", "Name", "Last Name", "Rate"};
    /**
     * TIPOS DE CADA COLUMNA (EN EL MISMO ORDEN DE LOS ENCABEZADOS)
     */
    private Class[] tiposColumnas = {String.class, String.class, String.class, Integer.class};

    private List<Employee> contenido;

    /**
     * CONSTRUCTOR VACIO
     */
    public EmployeeTableModel() {
        contenido = new ArrayList<Employee>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public EmployeeTableModel(List<Employee> contenidoInicial) {
        contenido = contenidoInicial;
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    /**
     * OTRO METODO QUE HAY QUE PISAR
     */
    public int getRowCount() {
        return contenido.size();
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public Object getValueAt(int rowIndex, int columnIndex) {

        Employee u = contenido.get(rowIndex);

        Object result = null;
        switch(columnIndex) {
            case COLUMNA_ID:
                result = u.getIdentityNumber();
                break;
            case COLUMNA_NAME:
                result = u.getName();
                break;
            case COLUMNA_LAST_NAME:
                result = u.getLastName();
                break;
            case COLUMNA_RATE_PER_HOUR:
                result = u.getRatePerHour();
                break;
            default:
                result = new String("");
        }

        return result;
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public String getColumnName(int col) {
        return nombresColumnas[col];
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public Class getColumnClass(int col) {
        return tiposColumnas[col];
    }




    /**
     * GETTER DE MIS FILAS
     * @return
     */
    public List<Employee> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Employee> contenido) {
        this.contenido = contenido;
    }
}
