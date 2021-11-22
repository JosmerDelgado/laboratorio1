package View.Components;

import Model.Employee;
import Model.Project;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import java.awt.*;
import java.util.Vector;

class ProjectBoxEditor extends BasicComboBoxEditor {
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private Project selectedItem;

    public ProjectBoxEditor() {

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
        return this.selectedItem.getId();
    }

    public void setItem(Project item) {
        this.selectedItem = item;
        label.setText(item.toString());
    }

}

class ProjectBoxRenderer extends JLabel implements ListCellRenderer<Project> {

    public ProjectBoxRenderer() {
        setOpaque(true);
    }


    @Override
    public Component getListCellRendererComponent(JList list, Project value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.getName());
        return this;
    }
}

public class ProjectComboBox extends JComboBox<Project> {

    public ProjectComboBox(){
        super();
        this.setRenderer(new ProjectBoxRenderer());
        this.setEditor(new ProjectBoxEditor());
    }

    public ProjectComboBox(Vector<Project> projects){
        super(projects);
        this.setRenderer(new ProjectBoxRenderer());
        this.setEditor(new ProjectBoxEditor());
    }

    public ProjectComboBox(Vector<Project> projects, Project project){
        super(projects);
        super.setSelectedItem(project);
        this.setRenderer(new ProjectBoxRenderer());
        this.setEditor(new ProjectBoxEditor());
    }

    @Override
    public Object getSelectedItem() {
        return super.getSelectedItem();
    }
}
