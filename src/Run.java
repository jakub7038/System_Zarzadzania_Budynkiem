import javax.swing.*;

public class Run {
    Building building = new Building();

    public void MainWindow() {
        MainWindow mainWindow = new MainWindow();
    }
    public void EntryForm() {
        EntryForm entryForm = new EntryForm();
    }
    public void IssueReport(Boolean admin) {
        IssueReportForm issueReportForm = new IssueReportForm(admin);
    }
    public void IssueList() {
        IssueList issueList = new IssueList();
    }
    public void IssueListAdmin() {
        IssueListAdmin issueListadmin = new IssueListAdmin();
    }

    public void MainWindowAdmin(){
        MainWindowAdmin mainwindowadmin = new MainWindowAdmin();
    }

    public void LogTableView() {LogTableView logtableview = new LogTableView();}

    public void Load() {
        String line = DataFunctions.getData();
        String[] sublines = line.split(";;",0);
        for(int i=0;i<sublines.length;i++){
            switch (i) {
            case 0:
                building.setWaterTemp(Double.parseDouble(sublines[i]));
                break;
            case 1:
                building.setAirTemp(Double.parseDouble(sublines[i]));
                break;
            case 2:
                building.setLightStatus(Boolean.parseBoolean(sublines[i]));
                break;
            case 3:
                building.setPowerStatus(Boolean.parseBoolean(sublines[i]));
                break;
            case 4:
                building.setOpen(Boolean.parseBoolean(sublines[i]));
                break;
            }
        }
    }

    public void appendIssue(String Type, String Title, String Text){
        IssueFunctions.appendIssue(Type,Title,Text);
    }


    public boolean[] PasswordCheck(String pass){
        int pass1 = Integer.parseInt(pass);
        return LoginFunction.login(pass1);
    }

    public void Save(){
        String line = building.toString();
        String[] sublines = line.split(";;",0);
        DataFunctions.setData(sublines[0],sublines[1],StrtoBool(sublines[2]),StrtoBool(sublines[3]),StrtoBool(sublines[4]));
    }

    public void Save(String line){
        String[] sublines = line.split(";;",0);
        DataFunctions.setData(sublines[0],sublines[1],StrtoBool(sublines[2]),StrtoBool(sublines[3]),StrtoBool(sublines[4]));
    }

    public static Boolean StrtoBool(String b) {
        if(b.equals("true"))return true;
        return false;
    }

    public void showErrorDialog(String title, String message) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }


}