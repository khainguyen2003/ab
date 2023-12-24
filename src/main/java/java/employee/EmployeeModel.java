package employee;

import connection.*;
import objects.*;

import java.io.*;
import java.util.*;

import org.javatuples.Pair;

import java.sql.*;

public class EmployeeModel {
	private Employee u;
	
	public EmployeeModel(ConnectionPool cp) {
		this.u= new EmployeeImpl(cp);
	}
	
	protected void finalize()throws Throwable{
		this.u=null;
	}
	
	public ConnectionPool getCP() {
		return this.u.getCP();
	}
	
	public void releaseConnection() {
		this.u.releaseConnection();
	}

	//***********************Chuyen huong dieu khien tu Employee Impl*****************************************
	public boolean addEmployee(EmployeeObject item) {
		return this.u.addEmployee(item);
	}
	
	public boolean editEmployee(EmployeeObject item, EMPLOYEE_EDIT_TYPE et) {
		return this.u.editEmployee(item, et);
	}
	
	public boolean delEmployee(EmployeeObject item) {
		return this.u.delEmployee(item);
	}
	
	
	//****************************************************************
	
	public EmployeeObject getEmployeeObject(int id) {
		//Gan gia tri khoi tao cho doi tuong EmployeeObject
		EmployeeObject item = null ;
		
		//Lay ban ghi 
		ResultSet rs = this.u.getEmployee(id);
		
		
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				if (rs.next()) {
					item = new EmployeeObject();
					item.setEmployee_id(rs.getInt("user_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_office_phone(rs.getString("user_office_phone"));
					item.setUser_mobile_phone(rs.getString("user_mobile_phone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_notes(rs.getString("user_notes"));
					item.setUser_last_modified_date(rs.getString("user_last_modified"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					
					if (rs.getBlob("user_images")!=null) {
						item.setUser_images(rs.getString("user_images"));
					}
					item.setUser_deleted(rs.getBoolean("user_deleted"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public EmployeeObject getEmployeeObject(String Employeename, String Employeepass) {
		//Gan gia tri khoi tao cho doi tuong EmployeeObject
		EmployeeObject item = null ;
		
		//Lay ban ghi 
		ResultSet rs = this.u.getEmployee(Employeename, Employeepass);
		
		
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				if (rs.next()) {
					item = new EmployeeObject();
					item.setEmployee_id(rs.getInt("employee_id"));
					item.setUser_name(rs.getString("user_name"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
//					item.setuser_home_phone(rs.getString("user_homephone"));
					item.setUser_office_phone(rs.getString("user_office_phone"));
					item.setUser_mobile_phone(rs.getString("user_mobile_phone"));
					item.setUser_permission(rs.getByte("user_permission"));		
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}
	
	public Pair<ArrayList<EmployeeObject>,Integer> getEmployeeObjects(EmployeeObject similar, short page, byte uPerPage, EMPLOYEE_SORT_TYPE type) {
		
		//Gan gia tri khoi tao cho doi tuong EmployeeObject
		ArrayList<EmployeeObject> items = new ArrayList<>();
		EmployeeObject item = null ;
		
		//Lay ban ghi 
		int at = (page-1)*uPerPage;
		ArrayList<ResultSet> res = this.u.getEmployees(similar, at, uPerPage, type);
		
		ResultSet rs = res.get(0);
		
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				while (rs.next()) {
					item = new EmployeeObject();
					item.setEmployee_id(rs.getInt("user_id"));
					item.setUser_fullname(rs.getString("user_fullname"));
					item.setUser_email(rs.getString("user_email"));
					item.setUser_address(rs.getString("user_address"));
					item.setUser_office_phone(rs.getString("user_office_phone"));
					item.setUser_mobile_phone(rs.getString("user_mobile_phone"));
					item.setUser_permission(rs.getByte("user_permission"));
					item.setUser_notes(rs.getString("user_notes"));
					item.setUser_last_modified_date(rs.getString("user_last_modified_date"));
					item.setUser_last_modified_date(rs.getString("user_last_modified_id"));
					item.setUser_parent_id(rs.getInt("user_parent_id"));
					item.setUser_deleted(rs.getBoolean("user_deleted"));
					
					if (rs.getBlob("user_images")!=null) {
						item.setUser_images(rs.getString("user_images"));
					}
					
					// Dua doi tuong vao tap hop
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Lay tong so ban ghi
		int totalGlobal = 0;
		rs = res.get(1);
		if (rs!=null) {
			try {
				if (rs.next()) {
					totalGlobal = rs.getInt("total");
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new Pair<>(items,totalGlobal);
	}
	
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		
		EmployeeModel um = new EmployeeModel(cp);
	
		Pair<ArrayList<EmployeeObject>,Integer> items = um.getEmployeeObjects(null, (short) 1, (byte) 10, EMPLOYEE_SORT_TYPE.FULLNAME);
		
		items.getValue0().forEach(item -> System.out.println(item.getUser_fullname()));
		
	};
	
}

