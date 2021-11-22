package View.Components;

import Model.Employee;
import Model.Project;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.*;
import java.util.Vector;

class EmployeeBoxEditor extends BasicComboBoxEditor {
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private Employee selectedItem;

    public EmployeeBoxEditor() {

        label.setOpaque(false);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.BLACK);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
        panel.add(label);
        panel.setBackground(Color.GREEN);
    }

    public Component getEditorComponent() {
        return this.panel;
    }

    public Object getItem() {
        return this.selectedItem.getIdentityNumber();
    }

    public void setItem(Employee item) {
        this.selectedItem = item;
        label.setText(item.toString());
    }

}

class EmployeeBoxRenderer extends JLabel implements ListCellRenderer<Employee> {

    public EmployeeBoxRenderer() {
        setOpaque(true);
    }


    @Override
    public Component getListCellRendererComponent(JList list, Employee value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getName());
        return this;
    }
}

public class EmployeeComboBox extends JComboBox<Employee> {

    public EmployeeComboBox(){
        super();
        this.setRenderer(new EmployeeBoxRenderer());
        this.setEditor(new EmployeeBoxEditor());
    }

    public EmployeeComboBox(Vector<Employee> employees){
        super(employees);
        this.setRenderer(new EmployeeBoxRenderer());
        this.setEditor(new EmployeeBoxEditor());
    }

    public EmployeeComboBox(Vector<Employee> employees, Employee employee){
        super(employees);
        super.setSelectedItem(employee);
        this.setRenderer(new EmployeeBoxRenderer());
        this.setEditor(new EmployeeBoxEditor());
    }

    @Override
    public Employee getSelectedItem() {
        return (Employee) super.getSelectedItem();
    }
}
