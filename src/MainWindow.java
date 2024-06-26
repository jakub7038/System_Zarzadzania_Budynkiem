import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    protected JPanel panel1;
    protected JButton buttonExit;
    protected JButton Issuebutton;
    protected JButton IssueListbutton;
    protected JLabel Lightslabel;
    protected JLabel Powerlabel;
    protected JLabel OpenLabel;
    protected JLabel WaterLabel;
    protected JLabel AirLabel;
    protected JButton lightsbutton;
    protected JButton powerbutton;
    protected JButton openbutton;
    protected JButton waterbutton;
    protected JButton airbutton;
    protected JButton logOutButton;
    protected JButton LogList;
    Run run = new Run();

    public MainWindow() {
        super("System zarządzania budynkiem");
        SetValues();
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        setButtonColors(buttonExit);
        setButtonColors(Issuebutton);
        setButtonColors(IssueListbutton);
        setButtonColors(logOutButton);
        setButtonColors(LogList);

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run.Save(run.building.toString());
                System.exit(0);
            }
        });
        Issuebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run.Save(run.building.toString());
                dispose();
                run.IssueReport(false);
            }
        });

        IssueListbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run.Save(run.building.toString());
                dispose();
                run.IssueList();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run.Save(run.building.toString());
                dispose();
                run.EntryForm();
            }
        });
    }

    public void SetValues() {
        run.Load();

        if (run.building.getLightStatus()) {
            Lightslabel.setText("ON");
        } else {
            Lightslabel.setText("OFF");
        }

        if (run.building.getPowerStatus()) {
            Powerlabel.setText("ON");
        } else {
            Powerlabel.setText("OFF");
        }

        if (run.building.getOpen()) {
            OpenLabel.setText("Otwarta");
        } else {
            OpenLabel.setText("Zamknięta");
        }

        WaterLabel.setText(String.valueOf(run.building.getWaterTemp() + "°C"));
        AirLabel.setText(String.valueOf(run.building.getAirTemp() + "°C"));
        run.Save(run.building.toString());
    }

    private void setButtonColors(JButton button) {
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
    }

}
