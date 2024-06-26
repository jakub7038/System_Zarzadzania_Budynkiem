import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogTableView extends JFrame{
    private JPanel panel1;
    private JButton button1;
    private JTable table1;

    public LogTableView(){
        super("Tabela logów");
        Run run = new Run();

        setButtonColors(this.button1);

        this.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                run.MainWindowAdmin();
            }
        });


        String[] columnNames = {"woda", "powietrze", "światło", "zasilanie", "otwarte", "data"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        try (BufferedReader br = new BufferedReader(new FileReader("logi.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    data[data.length - 1] = data[data.length - 1].substring(0, Math.min(data[data.length - 1].length(), 22));
                }
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.table1.setModel(model);

        this.table1.getColumnModel().getColumn(0).setPreferredWidth(40);
        this.table1.getColumnModel().getColumn(1).setPreferredWidth(60);
        this.table1.getColumnModel().getColumn(2).setPreferredWidth(60);
        this.table1.getColumnModel().getColumn(3).setPreferredWidth(60);
        this.table1.getColumnModel().getColumn(4).setPreferredWidth(60);
        this.table1.getColumnModel().getColumn(5).setPreferredWidth(160);

        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    private void setButtonColors(JButton button) {
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
    }


}
