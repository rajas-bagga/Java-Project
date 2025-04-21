
import java.sql.*;
import javax.swing.*;

public class ViewServerLogs {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.out.println("Windows look and feel not available.");
        }

        JFrame frame = new JFrame("Server Logs");
        frame.setSize(690, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 640, 320);
        frame.add(scrollPane);

        try {
            Connection conn = Databse.initializeDatabase();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM logs");

            StringBuilder logs = new StringBuilder();
            boolean hasLogs = false;

            while (rs.next()) {
                hasLogs = true;
                logs.append("ID: ").append(rs.getInt("id"))
                        .append(", Date: ").append(rs.getString("date"))
                        .append(", Time: ").append(rs.getString("time"))
                        .append(", IP: ").append(rs.getString("ip"))
                        .append(", Port: ").append(rs.getInt("port"))
                        .append("\n");
            }

            if (!hasLogs) {
                JOptionPane.showMessageDialog(null, "No server logs found.");
                return;
            }

            textArea.setText(logs.toString());

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }

        frame.setVisible(true);
    }
}
