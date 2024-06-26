import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureSlider extends JFrame{
    Run run = new Run();
    private JSlider slider1;
    private JPanel panel1;
    private JButton buttonreturn;
    private JTextArea temperaturedisplay;
    private double value = 0.0;

    public TemperatureSlider(Double temp,Boolean type){
        super("Temperatura");
        run.Load();
        if(type) {
            slider1.setMaximum(1000);
            slider1.setMinimum(0);
        }
        else{
            slider1.setMaximum(350);
            slider1.setMinimum(50);
        }

        slider1.setValue((int)(temp*10));
        temperaturedisplay.setText(String.valueOf(temp));
        this.setResizable(false);
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        buttonreturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Double temp = Double.parseDouble(temperaturedisplay.getText());
                    if((type&&( temp<0 || temp >100) )||(!type&&(temp<5 || temp>35))){
                        run.showErrorDialog("Złe dane","Taki zakres temperatur nie jest możliwy!");
                    }else {
                        value = temp;
                        if(type)run.building.setWaterTemp(value);
                        else run.building.setAirTemp(value);
                        run.Save();
                        dispose();
                        run.MainWindowAdmin();
                    }
                }catch (NumberFormatException x) {
                    run.showErrorDialog("Złe dane", "Proszę podać dane numeryczne!");
                }
            }
        });

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                temperaturedisplay.setText(String.valueOf((double)(slider1.getValue())/10));
            }
        });

    }


}
