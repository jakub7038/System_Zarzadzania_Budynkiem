import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Timestamp;

public class IssueListAdmin extends IssueList{
    public IssueListAdmin(){
        deletebutton.setVisible(true);
        deletebutton.setEnabled(true);

        Exitbutton.removeActionListener(Exitbutton.getActionListeners()[0]);
        Exitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                run.MainWindowAdmin();
            }
        });
        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = Issuetable.getSelectedRow();
                if (selectedRow != -1) {
                    String dateString = (String) Issuetable.getValueAt(selectedRow, 0);
                    Timestamp timestamp = Timestamp.valueOf(dateString);
                    IssueFunctions.deleteIssue(timestamp);
                    refreshTable();
                }
            }
        });

    }
    private void refreshTable() {
        String[] columnNames = {"Data", "Typ", "Nagłówek", "Opis"};
        String[][] data = IssueFunctions.displayIssuesData();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        Issuetable.setModel(model);
        model.fireTableDataChanged();
    }
}
