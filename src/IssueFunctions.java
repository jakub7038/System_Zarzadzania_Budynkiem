import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class IssueFunctions {
    static Run run = new Run();
    public static void appendIssue(String type, String title, String text) {
        LocalDateTime date = LocalDateTime.now();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DataConnection.getConnection();
            String sql = "INSERT INTO issues (Data, Type, Title, Text) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1,Timestamp.valueOf(date));
            pstmt.setString(2, type);
            pstmt.setString(3, title);
            pstmt.setString(4, text);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                run.showErrorDialog("Wystąpił nieoczekiwany błąd!","Brak połączenia z serwerem!");
            }
        }
    }


    public static String[] displayIssues() {
        List<String> issuesList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataConnection.getConnection();
            String sql = "SELECT Data, Type, Title, Text FROM issues";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Timestamp data = rs.getTimestamp("Data");
                String type = rs.getString("Type");
                String title = rs.getString("Title");
                String text = rs.getString("Text");
                String issue = "Date: " + data + ", Type: " + type + ", Title: " + title + ", Text: " + text;
                issuesList.add(issue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                run.showErrorDialog("Wystąpił nieoczekiwany błąd!","Brak połączenia z serwerem!");
            }
        }
        return issuesList.toArray(new String[0]);
    }

    public static void deleteIssue(Timestamp timestamp) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DataConnection.getConnection();
            String sql = "DELETE FROM issues WHERE Data = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1, timestamp);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                run.showErrorDialog("Wystąpił nieoczekiwany błąd!","Brak połączenia z serwerem!");
            }
        }
    }

    public static String[][] displayIssuesData() {
        List<String[]> issuesList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DataConnection.getConnection();
            String sql = "SELECT Data, Type, Title, Text FROM issues";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Timestamp data = rs.getTimestamp("Data");
                String type = rs.getString("Type");
                String title = rs.getString("Title");
                String text = rs.getString("Text");

                String[] typeParts = type.split(": ", 2);
                String cleanedType = typeParts.length > 1 ? typeParts[1] : type;

                String[] issueData = {data.toString(), cleanedType, title, text};
                issuesList.add(issueData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            run.showErrorDialog("Wystąpił nieoczekiwany błąd!","Brak połączenia z serwerem!");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String[][] data = new String[issuesList.size()][];
        issuesList.toArray(data);

        return data;
    }
}
