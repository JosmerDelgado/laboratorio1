package Model;

public class Employee {
    private String name;
    private String lastName;
    private Double ratePerHour;
    private Integer identityNumber;
    private Integer projectId;


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ratePerHour=" + ratePerHour +
                ", identityNumber=" + identityNumber +
                '}';
    }

    public Employee(){
        name="";
        lastName="";
        ratePerHour=0.0;
        identityNumber=0;
        projectId=0;
    }

    public Employee(String name, String lastName, Double ratePerHour, Integer identityNumber, Integer projectId) {
        this.name = name;
        this.lastName = lastName;
        this.ratePerHour = ratePerHour;
        this.identityNumber = identityNumber;
        this.projectId = projectId;
    }

    public Employee(Integer identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Integer getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(Integer identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(Double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
