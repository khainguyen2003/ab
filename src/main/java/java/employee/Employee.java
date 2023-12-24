package employee;

import java.sql.*;
import java.util.*;

import connection.*;
import objects.*;

//extends ShareControl thay vi extend Basic de gioi han luong thong tin can import
public interface Employee extends ShareControl{
	//Cac phuong thuc/ chuc nang cap nhat thong tin Nguoi su dung
	public boolean addEmployee(EmployeeObject item);
	public boolean editEmployee(EmployeeObject item, EMPLOYEE_EDIT_TYPE et);
	public boolean delEmployee(EmployeeObject item);
	
	//Cac phuong thuc/ chuc nang lay thong tin Nguoi su dung
	public ResultSet getEmployee(int id);
	public ResultSet getEmployee(String username, String userpass);
	public ArrayList<ResultSet> getEmployees(EmployeeObject similar, int at, byte total, EMPLOYEE_SORT_TYPE type);
	
}