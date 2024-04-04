import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.SQLException;
class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public int add(int employeeId, String employeeName, String employeeEmail, int employeeExperience, String createdBy, Timestamp createDateTime, String modifiedName, Timestamp modifyDateTime, boolean isActive) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        int addResult = 0;
        try {
            connection = new DBConnection().getConnection();
            statement = connection.createStatement();
            String query = "INSERT INTO employees " +
                "(employee_id, employee_name, employee_mailId, employee_experience, created_by, created_date_time, modified_by, modified_date_time, is_active) " +
                "VALUES (" + employeeId + ", '" + employeeName + "', '" + employeeEmail + "', " + employeeExperience + ", '" +
                createdBy + "', '" + createDateTime + "', '" + modifiedName + "', '" + modifyDateTime + "', " + isActive + ")";
            addResult = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return addResult;
    }

    @Override
    public int modify(int modifiedId, String modifiedName, String modifiedEmail, int modifiedExperience, String modifiedBy, Timestamp modifiedDateTime, boolean isActive)throws SQLException {
        Connection connection = null;
        Statement statement = null;
        int modifiedResult = 0;
        try {
            connection = new DBConnection().getConnection();
            statement = connection.createStatement();
            String query = "UPDATE employees SET modified_by = '" + modifiedBy + "', " +
                    "employee_name = '" + modifiedName + "', " +
                    "employee_mailId = '" + modifiedEmail + "', " +
                    "employee_experience = " + modifiedExperience + ",  " +
                    "modified_date_time = '" + modifiedDateTime + "', " +
                    "is_active = " + isActive + " " +
                    "WHERE employee_id = " + modifiedId;
            modifiedResult = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return modifiedResult;
    }

    @Override
    public int delete(int deleteId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        int deleteResult = 0;
        try {
            connection = new DBConnection().getConnection();
            statement = connection.createStatement();
            String query = "DELETE FROM employees WHERE employee_id = " + deleteId;
            deleteResult = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return deleteResult;
    }

    @Override
    public List<Map<String, Object>> displayEmployees()throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> employeesList = new ArrayList<>();

        try {
            connection = new DBConnection().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employees");
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String employeeName = resultSet.getString("employee_name");
                String employeeEmail = resultSet.getString("employee_mailId");
                int employeeExperience = resultSet.getInt("employee_experience");
                String createdBy = resultSet.getString("created_by");
                Timestamp createdDateTime = resultSet.getTimestamp("created_date_time");
                String modifiedBy = resultSet.getString("modified_by");
                Timestamp modifiedDateTime = resultSet.getTimestamp("modified_date_time");
                int isActive = resultSet.getInt("is_active");
                Map<String, Object> employeeDetails = new HashMap<>();
                employeeDetails.put("Employee ID", employeeId);
                employeeDetails.put("Employee Name", employeeName);
                employeeDetails.put("Employee EmailID", employeeEmail);
                employeeDetails.put("Employee Experience", employeeExperience);
                employeeDetails.put("Created By", createdBy);
                employeeDetails.put("Created Date Time", createdDateTime);
                employeeDetails.put("Modified By", modifiedBy);
                employeeDetails.put("Modified Date Time", modifiedDateTime);
                employeeDetails.put("Is Active", isActive);
                employeesList.add(employeeDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeesList;
    }
}
