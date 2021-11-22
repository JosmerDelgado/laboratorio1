package View;

import Model.Employee;
import Model.Exceptions.ServiceException;
import Model.Service.TaskService;
import Model.TableModel.TaskTableModel;
import Model.Task;
import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        Employee employee = new Employee("name", "Last", 12.123,123,0);
        e.setAssigned(employee);
        List<Task> listTask = new ArrayList<>();
        TaskService taskService = new TaskService();

        try {
            listTask = taskService.list();
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }

        listTask.add(e);
        taskTableModel = new TaskTableModel(listTask);
        taskTable = new JTable(taskTableModel);
        scrollToTable = new JScrollPane(taskTable);

        taskTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    JTable target = (JTable)e.getSource();
                    int row=taskTable.rowAtPoint(e.getPoint());
                    int selectedId = (int)taskTable.getValueAt(row, 0);
                    System.out.println(selectedId);
                    manager.redirectToTaskEdit(selectedId);
                }
            }
        });

        this.add(scrollToTable);

        this.title = new Title("Task List");
        this.createTaskButton = new MyButton("Create Task");
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
