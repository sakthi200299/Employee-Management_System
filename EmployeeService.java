import java.util.Map;
interface EmployeeService {            //EmployeeService will be interface
	
	    void add(Map<String, Object> employeeData);                //insert method for employeeDetails
	    void modify(Map<String, Object> modifiedData);             //modify method for employeeDetails
	    void delete(int deleteData);                               //delete method for employeeDetails
		void displayEmployeeById(int employeeIdToDisplay);          //display method for employeeDetails using EmployeeID
		void displayEmployeesByName(String employeeNameToDisplay);   //display method for employeeDetails using EmployeeName
	    void displayEmployees();                                     //display the all employee delails 
}
