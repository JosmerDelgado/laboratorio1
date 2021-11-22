package View;

import View.Components.MyButton;
import View.Components.Title;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel implements ActionListener {
    private Title title;
    private MyButton buttonTaskList;
    private MyButton buttonEmployeeList;
    private MyButton buttonProjectList;
//    private MyButton buttonCreateCompany;

    // TODO: this can be part of a wrapper;
    private MyButton buttonBack;
    private Manager manager;

    public Main(Manager manager) {
        this.manager = manager;
        armar();
    }
    public void armar() {
        this.title = new Title("HOME");
        this.buttonTaskList = new MyButton("Task List");
        this.buttonEmployeeList = new MyButton("Employee List");
//        this.buttonCreateCompany = new MyButton("Create Company");
        this.buttonProjectList = new MyButton("Project List");

        // TODO: this can be part of a wrapper;
        this.buttonBack = new MyButton("Back");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(this.title);
        this.add(this.buttonTaskList);
        this.add(this.buttonEmployeeList);
//        this.add(this.buttonCreateCompany);

        this.add(this.buttonProjectList);
        // TODO: this can be part of a wrapper;
        this.add(this.buttonBack);

        this.buttonTaskList.addActionListener(this);
        this.buttonEmployeeList.addActionListener(this);
        this.buttonProjectList.addActionListener(this);
        this.buttonBack.addActionListener(this);
//        this.buttonCreateCompany.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource());
        if(actionEvent.getSource() == this.buttonEmployeeList){
            this.manager.redirectToEmployee();
        } else if(actionEvent.getSource() == this.buttonBack){
            this.manager.redirectToHome();
        } else if(actionEvent.getSource() == this.buttonTaskList){
            this.manager.redirectToTask();
        } else if(actionEvent.getSource() == this.buttonProjectList){
            this.manager.redirectToProjectList();
        }
//        else if(actionEvent.getSource() == this.buttonCreateCompany){
//            this.manager.redirectToCompanyCreate();
//        }
    }


}
