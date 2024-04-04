import java.sql.Timestamp;
import java.util.Map;
import java.util.List;
import java.sql.SQLException;
interface EmployeeDAO {
    int add(int employeeId, String employeeName,String employeeEmail,int employeeExperience, String createdBy, Timestamp createtimedate,String modifiedname,Timestamp modifytimedate ,boolean isActive)throws SQLException ;
    int modify(int modifiedId, String modifiedName, String modifiedEmail, int modifiedExperience, String modifiedBy, Timestamp modifiedDateTime, boolean isActive)throws SQLException ;
    int delete(int deleteId)throws SQLException ;
    List<Map<String, Object>> displayEmployees()throws SQLException ;
}
