package View.Components;

import javax.swing.*;
import java.awt.*;

public class InputWithLabel extends JPanel {
    private JLabel label;
    private JTextField input;

    public InputWithLabel(String text){
        armar(text);
    }

    public InputWithLabel(String text, String value){
        armar(text, value);
    }
    public void armar(String text) {
        armar(text,"");
    }

    public void armar(String text, String value) {
        this.setLayout(new GridLayout(1,2));
        this.label = new JLabel(text);
        this.input = new JTextField(value);
        this.add(label);
        this.add(input);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JTextField getInput() {
        return input;
    }

    public void setInput(JTextField input) {
        this.input = input;
    }
}
