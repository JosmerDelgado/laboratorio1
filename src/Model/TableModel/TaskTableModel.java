package Model.TableModel;

import Model.Task;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TaskTableModel extends AbstractTableModel {

    /**
     * INDICES DE MIS COLUMNAS
     */
    private static final int COLUMNA_ID = 0;
    private static final int COLUMNA_NAME = 1;
    private static final int COLUMNA_DESCRIPTION = 2;
    private static final int COLUMNA_ASSIGNED = 3;

    /**
     * NOMBRES DE LOS ENCABEZADOS
     */
    private String[] nombresColumnas = {"Id", "Name", "Description", "Assigned"};
    /**
     * TIPOS DE CADA COLUMNA (EN EL MISMO ORDEN DE LOS ENCABEZADOS)
     */
    private Class[] tiposColumnas = {String.class, String.class, String.class, Integer.class};

    private List<Task> contenido;

    /**
     * CONSTRUCTOR VACIO
     */
    public TaskTableModel() {
        contenido = new ArrayList<Task>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public TaskTableModel(List<Task> contenidoInicial) {
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

        Task u = contenido.get(rowIndex);

        Object result = null;
        switch(columnIndex) {
            case COLUMNA_ID:
                result = u.getId();
                break;
            case COLUMNA_NAME:
                result = u.getTitle();
                break;
            case COLUMNA_DESCRIPTION:
                result = u.getDescription();
                break;
            case COLUMNA_ASSIGNED:
                result = u.getAssigned().getName();
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
    public List<Task> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Task> contenido) {
        this.contenido = contenido;
    }
}
