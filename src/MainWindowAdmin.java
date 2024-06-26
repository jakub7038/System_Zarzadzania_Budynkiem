import com.mysql.cj.log.Log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindowAdmin extends MainWindow{
    public MainWindowAdmin() {
        lightsbutton.setEnabled(true);
        powerbutton.setEnabled(true);
        openbutton.setEnabled(true);
        waterbutton.setEnabled(true);
        airbutton.setEnabled(true);
        LogList.setEnabled(true);
        LogList.setVisible(true);

        lightsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!run.building.getLightStatus()){
                    Lightslabel.setText("ON");
                }
                else {
                    Lightslabel.setText("OF");
                }
                run.building.setLightStatus(!run.building.getLightStatus());
            }
        });

        powerbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!run.building.getPowerStatus()){
                    Powerlabel.setText("ON");
                }
                else {
                    Powerlabel.setText("OFF");
                }
                run.building.setPowerStatus(!run.building.getPowerStatus());
            }
        });

        openbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!run.building.getOpen()){
                    OpenLabel.setText("Otwarta");
                }
                else {
                    OpenLabel.setText("Zamknięta");
                }
                run.building.setOpen(!run.building.getOpen());
            }
        });

        LogList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                run.LogTableView();
            }
        });



        waterbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TemperatureSlider(run.building.getWaterTemp(),true);
            }
        });

        airbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TemperatureSlider(run.building.getAirTemp(),false);
            }
        });

        Issuebutton.removeActionListener(Issuebutton.getActionListeners()[0]);
        Issuebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                run.Save(run.building.toString());
                run.IssueReport(true);
            }
        });

        IssueListbutton.removeActionListener(IssueListbutton.getActionListeners()[0]);
        IssueListbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                run.Save(run.building.toString());
                run.IssueListAdmin();
            }
        });

    }

}
