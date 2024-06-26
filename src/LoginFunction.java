import javax.swing.*;
import java.sql.*;

public class LoginFunction {
    static Run run = new Run();
    public static boolean[] login(int Password) {
        boolean[] data = new boolean[2];
        data[0]=false;
        data[1]=false;
        String sql = "SELECT * FROM passwords WHERE password =?";
        try (Connection conn = DataConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, Password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int dbPass = rs.getInt("password");
                if(dbPass==Password) {
                    data[0]=true;
                    data[1] = intToBool(rs.getInt("isAdmin"));
                    return data;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            run.showErrorDialog("Wystąpił nieoczekiwany błąd!","Brak połączenia z serwerem!");
        }
        return data;
    }

    public static Boolean intToBool(int b) {
        return b == 1;
    }

    public static int boolToInt(Boolean b) {
        if(b)return 1;
        return 0;
    }
}
