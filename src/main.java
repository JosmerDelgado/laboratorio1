
import View.*;
import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setMinimumSize(new Dimension(500,500));

        Manager manager = new Manager(frame);
        manager.redirectToHome();
    }
}
