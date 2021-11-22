package Model;

import Enums.EventType;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Task {
    private Integer id;
    private String title;
    private String description;
    private Integer estimation;
    private Integer timeConsumed = 0;
    private Employee assigned;
    private List<EventTask> eventHistory;
    private Project project;


    public Task(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Employee getAssigned() {
        return assigned;
    }

    public void setEventHistory(List<EventTask> eventHistory){
        this.eventHistory = eventHistory;
    }

    public List<EventTask> getEventHistory() {
        return eventHistory;
    }

    public void addEventHistory(EventTask eventTask) {
        eventHistory.add(eventTask);
    }

    public void setAssigned(Employee assigned) {
//        EventTask event = new EventTask(this.assigned == null ? EventType.ASSIGNED : EventType.REASSIGNED);

//        this.eventHistory.add(event);


        this.assigned = assigned;
    }

    public Integer getTimeConsumed() {
        return timeConsumed;
    }

    public void setTimeConsumed(Integer timeConsumed) {
//        EventTask event = new EventTask( EventType.UPDATE_TIME_CONSUMED);

//        this.eventHistory.add(event);

        this.timeConsumed = timeConsumed;
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
//        EventTask event = new EventTask(EventType.UPDATE_ESTIMATION );

//        this.eventHistory.add(event);
        this.estimation = estimation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
//        EventTask event = new EventTask(EventType.UPDATE_DESCRIPTION );

//        this.eventHistory.add(event);
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
//        EventTask event = new EventTask(EventType.UPDATE_TITLE );

//        this.eventHistory.add(event);
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
