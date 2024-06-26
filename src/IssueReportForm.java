import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;


public class IssueReportForm extends JFrame{
    Run run = new Run();
    private JPanel panel1;
    private JRadioButton usterkaRadioButton;
    private JRadioButton próbaWłamaniaRadioButton;
    private JTextField headerField;
    private JTextArea textArea;
    private JButton dodajButton;
    private JButton zamknijButton;
    private JRadioButton Innebutton;


    public IssueReportForm(Boolean admin){
        super("Zgłaszanie problemów");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setResizable(false);

        setButtonColors(dodajButton);
        setButtonColors(zamknijButton);

        AbstractDocument document = (AbstractDocument) textArea.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {

                int currentLength = fb.getDocument().getLength();
                int insertLength = text.length();

                if (currentLength + insertLength - length <= 251) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        zamknijButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                if(admin){run.MainWindowAdmin();}
                else {run.MainWindow();}
            }
        });

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textArea.getText().isEmpty()&&!headerField.getText().isEmpty()) {
                    String rodzaj;
                    if(usterkaRadioButton.isSelected())rodzaj="Usterka";
                    else{
                        if(próbaWłamaniaRadioButton.isSelected())rodzaj="włamanie";
                        else rodzaj="inne";
                    }
                    String header = headerField.getText();
                    String text = textArea.getText();

                    run.appendIssue(rodzaj, header, text);

                    dispose();
                    if(admin){run.MainWindowAdmin();}
                    else {run.MainWindow();}
                }else{
                    run.showErrorDialog("Niepoprawne dane!","Nagłówek i treść zgłoszenia nie mogą być puste!!!");
                }
            }
        });
    }

    private void setButtonColors(JButton button) {
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
    }


}
