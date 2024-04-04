import java.util.Map;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
class EmployeeServiceImpl implements EmployeeService {          
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();   //create the employeeDAO object in EmployeeServiceImpl

    @Override
    public void add(Map<String, Object> employeeData) {      //insert method for employeeDetails
        try{       
	   if (employeeData == null) {
            System.out.println("Error: employeeData is null");  //check the employeeData is null or not
            return;
        }
        LocalDateTime currentDateTime = LocalDateTime.now();   //local date time for create the current Date Time
        Timestamp createtimedate = Timestamp.valueOf(currentDateTime);  //using the time stamp for current data time 
        Timestamp modifytimedate = createtimedate;  //assign the createdDateTime to modifiedDateTime
        Integer employeeId = Integer.parseInt(String.valueOf(employeeData.get("employeeId")));  //separate the employeeid from Map
        String employeeName = String.valueOf(employeeData.get("employeeName"));   //separate the employeename from Map
        String employeeEmail = String.valueOf(employeeData.get("employeeEmail"));  //separate the employeemail from Map
        Integer employeeExperience = Integer.parseInt(String.valueOf(employeeData.get("employeeExperience"))); //separate the employee experience from Map
        String createdBy = String.valueOf(employeeData.get("createdBy"));  //separate the createdBy from Map
        String modifiedName = createdBy; // assign the createdBy to modifiedName
        Boolean isActive = Boolean.parseBoolean(String.valueOf(employeeData.get("isActive")));  

        int addresult1 = employeeDAO.add(employeeId, employeeName, employeeEmail, employeeExperience, createdBy, createtimedate, modifiedName, modifytimedate, isActive);
        if (addresult1 > 0) {
            System.out.println("Insert the Employee Details successfully");
        } else {
            System.out.println(" Employee Details not Insert");
        }
		}
		catch(SQLException e)
		{
			System.out.println("Error on insert the Employee details"+e.getMessage());
			e.printStackTrace();
		}
    }


    public void modify(Map<String, Object> modifiedData) {
		try{
        if (modifiedData == null) {
            System.out.println("Error: modifiedData is null");
            return;
        }

        Integer modifiedId = Integer.parseInt(String.valueOf(modifiedData.get("employeeId")));
        if (modifiedId == null) {
            System.out.println("Error: modifiedId is null");
            return;
        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        Timestamp modifieddatatime = Timestamp.valueOf(currentDateTime);
        String modifiedName = String.valueOf(modifiedData.get("employeeName"));
        String modifiedEmail = String.valueOf(modifiedData.get("employeeEmail"));
        int modifiedExperience = Integer.parseInt(String.valueOf(modifiedData.get("employeeExperience")));
        String modifiedBy = String.valueOf(modifiedData.get("modifiedBy"));
        Boolean isActive = Boolean.parseBoolean(String.valueOf(modifiedData.get("isActive")));
        int modifyresult1 = employeeDAO.modify(modifiedId, modifiedName, modifiedEmail, modifiedExperience, modifiedBy, modifieddatatime, isActive);
        if (modifyresult1 > 0) {
            System.out.println("Modified the Employee Details successfully");
        } else {
            System.out.println(" Employee Details not Modified");
        }
		}
		catch(SQLException e)
		{
			System.out.println("Error on modified the Employee details"+e.getMessage());
			e.printStackTrace();
		}
    }

    @Override
    public void delete(int deleteData) {
		try{
        if (deleteData <= 0) {
            System.out.println("Error: deleteData is invalid");
            return;
        }

        Integer deleteId = deleteData;

        int deleteresult1 = employeeDAO.delete(deleteId);
        if (deleteresult1 > 0) {
            System.out.println("Delete the Employee Details successfully");
        } else {
            System.out.println(" Employee Details not Delete");
        }
		}
		catch(SQLException e)
		{
			System.out.println("Error on delete the Employee details"+e.getMessage());
			e.printStackTrace();
		}
    }

    public void displayEmployeeById(int employeeIdToDisplay) {
		try {
        List<Map<String, Object>> employeesList = employeeDAO.displayEmployees();

        for (Map<String, Object> employeeDetails : employeesList) {
            int employeeId = Integer.parseInt(String.valueOf(employeeDetails.get("Employee ID")));

            if (employeeId == employeeIdToDisplay) {
                String employeeName = String.valueOf(employeeDetails.get("Employee Name"));
                String employeeEmailid = String.valueOf(employeeDetails.get("Employee EmailID"));
                String employeeExperience = String.valueOf(employeeDetails.get("Employee Experience"));
                String createdBy = String.valueOf(employeeDetails.get("Created By"));
                String modifiedBy = String.valueOf(employeeDetails.get("Modified By"));
                Timestamp createdDateTime = (Timestamp) employeeDetails.get("Created Date Time");
                Timestamp modifiedDateTime = (Timestamp) employeeDetails.get("Modified Date Time");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
                String createdDateTimeStr = createdDateTime.toLocalDateTime().format(formatter);
                String modifiedDateTimeStr = modifiedDateTime.toLocalDateTime().format(formatter);
                Boolean isActive = Boolean.parseBoolean(String.valueOf(employeeDetails.get("Is Active")));

                System.out.println("Employee ID           : " + employeeId);
                System.out.println("Employee Name         : " + employeeName);
                System.out.println("Employee Email        : " + employeeEmailid);
                System.out.println("Employee Experience   : " + employeeExperience);
                System.out.println("Created By            : " + createdBy);
                System.out.println("Created Date Time     : " + createdDateTimeStr);
                System.out.println("Modified By           : " + modifiedBy);
                System.out.println("Modified Date Time    : " + modifiedDateTimeStr);
                System.out.println("Is Active             : " + isActive);
                System.out.println();
                return;                                           // Exit the loop once the specific employee is found and displayed
            }
        }
		}
				catch(SQLException e)
		{
			System.out.println("Error on display the Employee Id"+e.getMessage());
			e.printStackTrace();
		}
		
    }

    public void displayEmployeesByName(String employeeNameToDisplay) {
		try{
        List<Map<String, Object>> employeesList = employeeDAO.displayEmployees();

        boolean found = false;

        for (Map<String, Object> employeeDetails : employeesList) {
            String employeeName = String.valueOf(employeeDetails.get("Employee Name"));

            if (employeeName.equalsIgnoreCase(employeeNameToDisplay)) {
                found = true;

                String employeeId = String.valueOf(employeeDetails.get("Employee ID"));
                String employeeEmailid = String.valueOf(employeeDetails.get("Employee EmailID"));
                String employeeExperience = String.valueOf(employeeDetails.get("Employee Experience"));
                String createdBy = String.valueOf(employeeDetails.get("Created By"));
                String modifiedBy = String.valueOf(employeeDetails.get("Modified By"));
                Timestamp createdDateTime = (Timestamp) employeeDetails.get("Created Date Time");
                Timestamp modifiedDateTime = (Timestamp) employeeDetails.get("Modified Date Time");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
                String createdDateTimeStr = createdDateTime.toLocalDateTime().format(formatter);
                String modifiedDateTimeStr = modifiedDateTime.toLocalDateTime().format(formatter);
                Boolean isActive = Boolean.parseBoolean(String.valueOf(employeeDetails.get("Is Active")));

                System.out.println("Employee ID           : " + employeeId);
                System.out.println("Employee Name         : " + employeeName);
                System.out.println("Employee Email        : " + employeeEmailid);
                System.out.println("Employee Experience   : " + employeeExperience);
                System.out.println("Created By            : " + createdBy);
                System.out.println("Created Date Time     : " + createdDateTimeStr);
                System.out.println("Modified By           : " + modifiedBy);
                System.out.println("Modified Date Time    : " + modifiedDateTimeStr);
                System.out.println("Is Active             : " + isActive);
                System.out.println();
            }
        }
		}
		catch(SQLException e)
		{
			System.out.println("Error on display the Employee name"+e.getMessage());
			e.printStackTrace();
		}
		
    }

    public void displayEmployees() {
		try{
        List<Map<String, Object>> employeesList = employeeDAO.displayEmployees();

        for (Map<String, Object> employeeDetails : employeesList)
			{
            String employeeId = String.valueOf(employeeDetails.get("Employee ID"));
            String employeeName = String.valueOf(employeeDetails.get("Employee Name"));
            String employeeEmailid = String.valueOf(employeeDetails.get("Employee EmailID"));
            String employeeExperience = String.valueOf(employeeDetails.get("Employee Experience"));
            String createdBy = String.valueOf(employeeDetails.get("Created By"));
            String modifiedBy = String.valueOf(employeeDetails.get("Modified By"));
            Timestamp createdDateTime = (Timestamp) employeeDetails.get("Created Date Time");
            Timestamp modifiedDateTime = (Timestamp) employeeDetails.get("Modified Date Time");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
            String createdDateTimeStr = createdDateTime.toLocalDateTime().format(formatter);
            String modifiedDateTimeStr = modifiedDateTime.toLocalDateTime().format(formatter);
            Boolean isActive = Boolean.parseBoolean(String.valueOf(employeeDetails.get("Is Active")));
            System.out.println("Employee ID           : " + employeeId);
            System.out.println("Employee Name         : " + employeeName);
            System.out.println("Employee Email        : " + employeeEmailid);
            System.out.println("Employee Experience   : " + employeeExperience);
            System.out.println("Created By            : " + createdBy);
            System.out.println("Created Date Time     : " + createdDateTimeStr);
            System.out.println("Modified By           : " + modifiedBy);
            System.out.println("Modified Date Time    : " + modifiedDateTimeStr);
            System.out.println("Is Active             : " + isActive);
            System.out.println();
        }
		}
		catch(SQLException e)
		{
			System.out.println("Error on display the Employee details"+e.getMessage());
			e.printStackTrace();
		}
    }
}
