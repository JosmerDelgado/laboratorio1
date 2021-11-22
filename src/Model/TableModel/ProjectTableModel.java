package Model.TableModel;

import Model.Project;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProjectTableModel extends AbstractTableModel {

    /**
     * INDICES DE MIS COLUMNAS
     */
    private static final int COLUMNA_ID = 0;
    private static final int COLUMNA_NAME = 1;
    private static final int COLUMNA_DESCRIPTION = 2;

    /**
     * NOMBRES DE LOS ENCABEZADOS
     */
    private String[] nombresColumnas = {"Id", "Name", "Description"};
    /**
     * TIPOS DE CADA COLUMNA (EN EL MISMO ORDEN DE LOS ENCABEZADOS)
     */
    private Class[] tiposColumnas = {String.class, String.class, String.class, Integer.class};

    private List<Project> contenido;

    /**
     * CONSTRUCTOR VACIO
     */
    public ProjectTableModel() {
        contenido = new ArrayList<Project>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public ProjectTableModel(List<Project> contenidoInicial) {
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

        Project u = contenido.get(rowIndex);

        Object result = null;
        switch(columnIndex) {
            case COLUMNA_ID:
                result = u.getId();
                break;
            case COLUMNA_NAME:
                result = u.getName();
                break;
            case COLUMNA_DESCRIPTION:
                result = u.getDescription();
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
    public List<Project> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Project> contenido) {
        this.contenido = contenido;
    }
}
