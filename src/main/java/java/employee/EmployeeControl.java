package employee;

import connection.*;
import objects.*;
import java.util.*;
import org.javatuples.*;

public class EmployeeControl {
	private EmployeeModel um;
	
	public EmployeeControl(ConnectionPool cp) {
		this.um = new EmployeeModel(cp);
		
	}
	
	public ConnectionPool getCP() {
		return this.um.getCP();
	}
	
	public void releaseConnection() {
		this.um.releaseConnection();
	}

	
	public boolean addEmployee(EmployeeObject item) {
		return this.um.addEmployee(item);
	}
	
	public boolean editEmployee(EmployeeObject item, EMPLOYEE_EDIT_TYPE et) {
		return this.um.editEmployee(item, et);
	}
	
	public boolean delEmployee(EmployeeObject item) {
		return this.um.delEmployee(item);
	}
	
	//PhÆ°Æ¡ng thá»©c quáº£n trá»‹ ngÆ°á»�i dÃ¹ng
	public EmployeeObject getEmployeeObject(int id) {
		return this.um.getEmployeeObject(id);
	}
	
	//PhÆ°Æ¡ng thá»©c Ä‘Äƒng nháº­p
	public EmployeeObject getEmployeeObject(String employeeName, String employeePass) {
		return this.um.getEmployeeObject(employeeName,employeePass);
	}

	//PhÆ°Æ¡ng thá»©c láº¥y dá»¯ liá»‡u ngÆ°á»�i dÃ¹ng
	public Pair<ArrayList<EmployeeObject>,Integer> getEmployeeObjects(Quartet<EmployeeObject, Short, Byte, EMPLOYEE_SORT_TYPE> infors) {
		//Lay du lieu
		EmployeeObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		EMPLOYEE_SORT_TYPE ust = infors.getValue3();
				
		return this.um.getEmployeeObjects(similar, page, total, ust);
	}
	
	//PhÆ°Æ¡ng thá»©c xuáº¥t chuá»—i
	public ArrayList<String> viewEmployeesList(Quartet<EmployeeObject, Short, Byte, EMPLOYEE_SORT_TYPE> infors){
		//Lay du lieu
		EmployeeObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte uPerPage = infors.getValue2();
		EMPLOYEE_SORT_TYPE ust = infors.getValue3();
		
		Pair<ArrayList<EmployeeObject>,Integer> datas = this.um.getEmployeeObjects(similar, page, uPerPage, ust);
		
		return EmployeeLibrary.viewEmployeeList(datas, infors);
	}
	
	
	
	public static void main(String[] args) {
		EmployeeControl uc = new EmployeeControl(null);
		
		Quartet<EmployeeObject, Short, Byte, EMPLOYEE_SORT_TYPE> infors = new Quartet<>(null, (short) 1, (byte) 10, EMPLOYEE_SORT_TYPE.NAME);
		
		ArrayList<String> view = uc.viewEmployeesList(infors);
		
		uc.releaseConnection();//Tra ve ket noi
		
		System.out.println(view);
	}
}
