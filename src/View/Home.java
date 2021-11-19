package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginForm extends JPanel {

    private JLabel usernameLbl;
    private JLabel passwordLbl;
    private JTextField usernameTxt;
    private JTextField passwordTxt;

    public LoginForm() {
        armar();
    }

    public void armar() {
        this.setLayout(new GridLayout(2,2));

        this.usernameLbl = new JLabel("Username");
        this.passwordLbl = new JLabel("Contraseña");
        this.usernameTxt = new JTextField();
        this.passwordTxt = new JTextField();

        this.add(usernameLbl);
        this.add(usernameTxt);
        this.add(passwordLbl);
        this.add(passwordTxt);
    }

    public JLabel getUsernameLbl() {
        return usernameLbl;
    }

    public void setUsernameLbl(JLabel usernameLbl) {
        this.usernameLbl = usernameLbl;
    }

    public JLabel getPasswordLbl() {
        return passwordLbl;
    }

    public void setPasswordLbl(JLabel passwordLbl) {
        this.passwordLbl = passwordLbl;
    }

    public JTextField getUsernameTxt() {
        return usernameTxt;
    }

    public void setUsernameTxt(JTextField usernameTxt) {
        this.usernameTxt = usernameTxt;
    }

    public JTextField getPasswordTxt() {
        return passwordTxt;
    }

    public void setPasswordTxt(JTextField passwordTxt) {
        this.passwordTxt = passwordTxt;
    }
}

class OkCancelPanel extends JPanel {

    private JButton okBtn;
    private JButton cancelBtn;

    public OkCancelPanel() {
        armar();
    }

    private void armar() {
        this.okBtn = new JButton("ACEPTAR");
        this.cancelBtn = new JButton("CANCELAR");
        this.add(okBtn);
        this.add(cancelBtn);
    }

    public JButton getOkBtn() {
        return okBtn;
    }

    public void setOkBtn(JButton okBtn) {
        this.okBtn = okBtn;
    }

    public JButton getCancelBtn() {
        return cancelBtn;
    }

    public void setCancelBtn(JButton cancelBtn) {
        this.cancelBtn = cancelBtn;
    }
}

public class Home extends JPanel implements ActionListener {

    private LoginForm loginFormPanel;
    private OkCancelPanel botoneraPanel;


    public Home() {
        armar();
    }
    public void armar() {
        this.loginFormPanel = new LoginForm();
        this.botoneraPanel = new OkCancelPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(this.loginFormPanel);
        this.add(this.botoneraPanel);

        this.botoneraPanel.getOkBtn().addActionListener(this);
    }

    public static void main(String[] args) {
        JFrame prueba = new JFrame();
        prueba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prueba.pack();
        prueba.setVisible(true);
        prueba.getContentPane().add(new Home());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.botoneraPanel.getOkBtn()) {
//            UsuarioDAO d = new UsuarioDAoImpl();
//            String user = this.loginFormPanel.getUsernameTxt().getText();
//            String pass = this.loginFormPanel.getPasswordTxt().getText();
//            User u = d.findByUserAndPass(user, pass);
//            if(u != null) {
//                System.out.println("LOGIN EXITOSO");
//            }
        }
    }

}