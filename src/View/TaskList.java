package View;

import Model.Employee;
import Model.Exceptions.ServiceException;
import Model.Project;
import Model.Service.EmployeeService;
import Model.Service.ProjectService;
import Model.Service.TaskService;
import Model.TableModel.TaskTableModel;
import Model.Task;
import View.Components.MyButton;
import View.Components.ProjectComboBox;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TaskList extends JPanel implements ActionListener, ItemListener {
    private Title title;
    private MyButton createTaskButton;

    // TODO: this can be part of a wrapper;
    private MyButton buttonBack;
    private TaskTableModel taskTableModel;
    private JTable taskTable;
    private JScrollPane scrollToTable;
    private Manager manager;
    private ProjectComboBox projectComboBox;


    public TaskList(Manager manager) {
        this.manager = manager;
        armar();
    }

    public void armar() {

        List<Task> listTask = new ArrayList<>();
        TaskService taskService = new TaskService();
        ProjectService projectService = new ProjectService();

        Vector<Project> projectList= new Vector<>();


        try {
            listTask = taskService.list();
            projectList.addAll(projectService.list());
        } catch (ServiceException ex) {
            ex.printStackTrace();
        }

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

        projectComboBox = new ProjectComboBox(projectList);
        projectComboBox.addItemListener(this);
        this.add(projectComboBox);

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

    @Override
    public void itemStateChanged(ItemEvent e) {
        // if the state combobox is changed
        if(e.getSource() == this.projectComboBox){
            System.out.println(((Project)this.projectComboBox.getSelectedItem()).getId());
            TaskService employeeService = new TaskService();
            try {
                this.taskTableModel.setContenido(employeeService.list(((Project)this.projectComboBox.getSelectedItem()).getId()));

            } catch (ServiceException ex) {
                ex.printStackTrace();
            }
            this.taskTableModel.fireTableDataChanged();
        }
    }
}
