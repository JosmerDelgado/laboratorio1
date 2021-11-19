package Model;

public class UserSingleton {

    private static UserSingleton instance;
    public Employee value;

    private UserSingleton(Employee value) {
        this.value = value;
    }

    public static Employee getInstance() {

        return instance.value;
    }
}