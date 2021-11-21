package View;

import Model.Employee;
import Model.TableModel.TaskTableModel;
import Model.Task;
import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskList extends JPanel implements ActionListener {
    private Title title;
    private MyButton createTaskButton;

    // TODO: this can be part of a wrapper;
    private MyButton buttonBack;
    private TaskTableModel taskTableModel;
    private JTable taskTable;
    private JScrollPane scrollToTable;
    private Manager manager;

    public TaskList(Manager manager) {
        this.manager = manager;
        armar();
    }

    public void armar() {

        Task e = new Task(123, "Title");
        Employee employee = new Employee("name", "Last", 12.123,123);
        e.setAssigned(employee);
        List<Task> listEmployee = new ArrayList<>();
        listEmployee.add(e);
        taskTableModel = new TaskTableModel(listEmployee);
        taskTable = new JTable(taskTableModel);
        scrollToTable = new JScrollPane(taskTable);
        this.add(scrollToTable);

        this.title = new Title("Task List");
        this.createTaskButton = new MyButton("Create Employee");
        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(this.title);
        this.add(this.createTaskButton);
        // TODO: this can be part of a wrapper;
        this.add(this.buttonBack);

        this.createTaskButton.addActionListener(this);

        this.buttonBack.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToMain();
        }
        if(actionEvent.getSource() == this.createTaskButton){
            this.manager.redirectToTaskCreate();
        }
    }
}
