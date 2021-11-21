
import View.*;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Manager manager = new Manager(frame);
        manager.redirectToHome();
    }
}
