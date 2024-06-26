import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;


public class DataFunctions {
    static Run run = new Run();
    public static String getData(){
        String water = "0.0", air = "0.0";
        boolean light = false, power = false, isopen = false;

        String sql = "SELECT * FROM data";
        try (Connection conn = DataConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                water = rs.getString("watertemp");
                air = rs.getString("airtemp");
                light = intToBool(rs.getInt("ligthstatus"));
                power = intToBool(rs.getInt("powerstatus"));
                isopen = intToBool(rs.getInt("isopen"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (water + ";;" + air + ";;" + light + ";;" + power + ";;" + isopen);
    }

    public static void setData(String water, String air, boolean light, boolean power, boolean isOpen) {
        addCSV(water,air,light,power,isOpen);

        int lightStatus = boolToInt(light);
        int powerStatus = boolToInt(power);
        int isOpenStatus = boolToInt(isOpen);

        String sql = "UPDATE data SET watertemp = ?, airtemp = ?, ligthstatus = ?, powerstatus = ?, isopen = ?";
        try (Connection conn = DataConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, water);
                pstmt.setString(2, air);
                pstmt.setInt(3, lightStatus);
                pstmt.setInt(4, powerStatus);
                pstmt.setInt(5, isOpenStatus);
                pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            run.showErrorDialog("Wystąpił nieoczekiwany błąd!","Brak połączenia z serwerem!");
        }
    }

    public static void addCSV(String water, String air, boolean light, boolean power, boolean isOpen) {
        LocalDateTime date = LocalDateTime.now();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logi.csv", true))) {

            String lightString = Boolean.toString(light);
            String powerString = Boolean.toString(power);
            String isOpenString = Boolean.toString(isOpen);

            String dateStr = date.toString();

            String csvLine = String.join(",", water, air, lightString, powerString, isOpenString, dateStr);

            writer.write(csvLine);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            run.showErrorDialog("Wystąpił nieoczekiwany błąd!","Brak połączenia z serwerem!");
        }
    }

    public static Boolean intToBool(int b) {
        return b == 1;
    }

    public static int boolToInt(Boolean b) {
        if(b)return 1;
        return 0;
    }


}