package project.weather_stock_api.service.cleanupdb;

import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseCleanupService {
    public static void cleanupDatabase() {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "ozlam2002")) {

            String deleteQuery = "DELETE FROM weatherdb.weather WHERE weather.update_time <?";
            LocalDateTime thresholdDate = LocalDateTime.now().minusDays(30);

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setObject(1, Timestamp.valueOf(thresholdDate));
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " rows is deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
