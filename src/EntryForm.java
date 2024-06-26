import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntryForm extends JFrame{
    Run run = new Run();
    private JPanel panel1;
    private JButton button1;
    private JButton buttonErase;
    private JPasswordField PasswordField;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button0;
    private JButton OK;


    public EntryForm(){
        super("Logowanie");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
        PasswordField.setEditable(false);
        this.setResizable(false);
        PasswordField.setBorder(new LineBorder(Color.BLACK, 1));

        setButtonColors(button1);
        setButtonColors(button2);
        setButtonColors(button3);
        setButtonColors(button4);
        setButtonColors(button5);
        setButtonColors(button6);
        setButtonColors(button7);
        setButtonColors(button8);
        setButtonColors(button9);
        setButtonColors(button0);
        setButtonColors(buttonErase);
        setButtonColors(OK);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("1");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("2");
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("3");
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("4");
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("5");
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("6");
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("7");
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("8");
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("9");
            }
        });

        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("0");
            }
        });

        buttonErase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                append("-1");
            }
        });

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordCheck();
            }
        });
    }


    private void append(String x){
        String password = new String(PasswordField.getPassword());
        if(x!="-1") {
            if(password.length()<10)PasswordField.setText(password+x);
            else EraseAll();
        }else{
            password = delLast(password);
            PasswordField.setText(password);
        }
    }

    private void PasswordCheck(){
        String password = new String(PasswordField.getPassword());
        if(password.isEmpty()){
            run.showErrorDialog("nieporawne hasło","pole hasła nie może być puste!");
        }
        else {
            PasswordField.setText("");
            Run run = new Run();
            run.Load();
            boolean[] pass = run.PasswordCheck(password);
            if (pass[0]) {
                dispose();
                if(pass[1]) run.MainWindowAdmin();
                else run.MainWindow();
            } else EraseAll();
        }
    }

    private String delLast(String s){
        if(!s.isEmpty()){
            s=(s.substring(0, s.length() - 1));
        }
        return s;
    }

    private void setButtonColors(JButton button) {
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
    }


    private void EraseAll(){
        PasswordField.setText("");
    }
}
