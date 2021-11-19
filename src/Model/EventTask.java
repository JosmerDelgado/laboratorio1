package Model;

import Enums.EventType;

public class EventTask {
    private Employee actor;
    private EventType eventType;

    public EventTask(String eventType, Employee actor) {
//        Employee user = UserSingleton.getInstance();

//        this.actor = user;
        this.actor = actor;
        this.eventType = Enum.valueOf(EventType.class, eventType);
    }



    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Employee getActor() {
        return actor;
    }

    public void setActor(Employee actor) {
        this.actor = actor;
    }
}
