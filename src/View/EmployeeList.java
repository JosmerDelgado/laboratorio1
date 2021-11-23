package View;

import Model.Employee;
import Model.Exceptions.ServiceException;
import Model.Project;
import Model.Service.EmployeeService;
import Model.Service.ProjectService;
import Model.TableModel.EmployeeTableModel;
import View.Components.MyButton;
import View.Components.ProjectComboBox;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class EmployeeList extends JPanel implements ActionListener, ItemListener {
    private Title title;
    private MyButton createEmployeeButton;

    // TODO: this can be part of a wrapper;
    private MyButton buttonBack;
    private EmployeeTableModel employeeTaleModel;
    private JTable employeeTable;
    private JScrollPane scrollToTable;
    private Manager manager;
    private ProjectComboBox projectComboBox;

    public EmployeeList(Manager manager) {
        this.manager = manager;
        armar();
    }

    public void armar() {

        EmployeeService employeeService = new EmployeeService();
        ProjectService projectService = new ProjectService();
        List<Employee> listEmployee = new ArrayList<>();
        Vector<Project> projectList= new Vector<>();

        try {
            List<Project> projects= projectService.list();
            projectList.addAll(projects);

            listEmployee = employeeService.list(projects.get(0).getId());
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
        this.title = new Title("Employee List");
        this.add(this.title);

        employeeTaleModel = new EmployeeTableModel(listEmployee);
        employeeTable = new JTable(employeeTaleModel);
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    int row=employeeTable.rowAtPoint(e.getPoint());
                    int selectedId = (int)employeeTable.getValueAt(row, 0);
                    System.out.println(selectedId);
                    manager.redirectToEmployeeEdit(selectedId);
                }
            }
        });
        scrollToTable = new JScrollPane(employeeTable);
        this.add(scrollToTable);

        Title comboTitle = new Title("Filter By Project");
        this.add(comboTitle);

        projectComboBox = new ProjectComboBox(projectList);
        projectComboBox.addItemListener(this);
        this.add(projectComboBox);


        this.createEmployeeButton = new MyButton("Create Employee");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(this.createEmployeeButton);
        // TODO: this can be part of a wrapper;
        this.add(this.buttonBack);

        this.createEmployeeButton.addActionListener(this);

        this.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToMain();
        }
        if(actionEvent.getSource() == this.createEmployeeButton){
            this.manager.redirectToEmployeeCreate();
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed
        if(e.getSource() == this.projectComboBox){
            System.out.println(((Project)this.projectComboBox.getSelectedItem()).getId());
            EmployeeService employeeService = new EmployeeService();
            try {
                this.employeeTaleModel.setContenido(employeeService.list(((Project)this.projectComboBox.getSelectedItem()).getId()));

            } catch (ServiceException ex) {
                ex.printStackTrace();
            }
            this.employeeTaleModel.fireTableDataChanged();
        }
    }
}
