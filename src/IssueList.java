import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IssueList extends JFrame {
    Run run = new Run();
    protected JPanel panel1;
    protected JTable Issuetable;
    protected JButton Exitbutton;
    protected JTextArea opisArea;
    protected JPanel IssuePanel;
    protected JButton deletebutton;

    public IssueList() {
        super("Lista zgłoszeń");
        setButtonColors(Exitbutton);
        setButtonColors(deletebutton);

        deletebutton.setVisible(false);



        String[] columnNames = {"Data", "Typ", "Nagłówek", "Opis"};
        String[][] data = IssueFunctions.displayIssuesData();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        Issuetable.setModel(model);

        Exitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                run.MainWindow();
            }
        });

        Issuetable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = Issuetable.getSelectedRow();
                if (selectedRow != -1) {
                    String text = (String)Issuetable.getValueAt(selectedRow,3);
                    opisArea.setText(text);
                }
            }
        });

        this.Issuetable.getColumnModel().getColumn(0).setPreferredWidth(95);
        this.Issuetable.getColumnModel().getColumn(1).setPreferredWidth(30);
        this.Issuetable.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.Issuetable.getColumnModel().getColumn(3).setPreferredWidth(60);

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
