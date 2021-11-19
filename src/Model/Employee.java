package Model;

public class Employee {
    private String name;
    private String lastName;
    private Double ratePerHour;
    private Integer identityNumber;


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ratePerHour=" + ratePerHour +
                ", identityNumber=" + identityNumber +
                '}';
    }

    public Employee(String name, String lastName, Double ratePerHour, Integer identityNumber) {
        this.name = name;
        this.lastName = lastName;
        this.ratePerHour = ratePerHour;
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
}
