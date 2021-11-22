package View;

import Model.Employee;
import Model.Exceptions.ServiceException;
import Model.Service.EmployeeService;
import Model.TableModel.EmployeeTableModel;
import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        Employee e = new Employee("FIrst", "Last", 12.4, 123123,0);
        EmployeeService employeeService = new EmployeeService();

        List<Employee> listEmployee = new ArrayList<>();
        try {
            listEmployee = employeeService.list();
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }
        listEmployee.add(e);
        employeeTaleModel = new EmployeeTableModel(listEmployee);
        employeeTable = new JTable(employeeTaleModel);
        employeeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    JTable target = (JTable)e.getSource();
                    int row=employeeTable.rowAtPoint(e.getPoint());
                    int selectedId = (int)employeeTable.getValueAt(row, 0);
                    System.out.println(selectedId);
                    manager.redirectToEmployeeEdit(selectedId);
                }
            }
        });
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
