package View;

import Model.Employee;
import Model.TableModel.EmployeeTableModel;
import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmployeeList extends JPanel implements ActionListener {
    private Title title;
    private MyButton createEmployeeButton;

    // TODO: this can be part of a wrapper;
    private MyButton buttonBack;
    private EmployeeTableModel employeeTaleModel;
    private JTable employeeTable;
    private JScrollPane scrollToTable;
    private Manager manager;

    public EmployeeList(Manager manager) {
        this.manager = manager;
        armar();
    }

    public void armar() {

        Employee e = new Employee("FIrst", "Last", 12.4, 123123);
        List<Employee> listEmployee = new ArrayList<>();
        listEmployee.add(e);
        employeeTaleModel = new EmployeeTableModel(listEmployee);
        employeeTable = new JTable(employeeTaleModel);
        scrollToTable = new JScrollPane(employeeTable);
        this.add(scrollToTable);

        this.title = new Title("Employee");
        this.createEmployeeButton = new MyButton("Create Employee");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(this.title);
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
}
