package employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

import connection.*;
import basic.*;
import objects.EmployeeObject;
import objects.UserObject;
import user.USER_EDIT_TYPE;
import user.USER_SORT_TYPE;
import user.UserImpl;



public class EmployeeImpl extends UserImpl implements Employee {
	
	public EmployeeImpl(ConnectionPool cp) {
		super(cp, "Employee");
	}
	
	public EmployeeImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
	}
	
	@Override
	public boolean addEmployee(EmployeeObject item) {
		// TODO Auto-generated method stub		
		//Biên dịch
		try {		
			boolean uresult = this.addUser(item);
			
			if (this.isExisting(item)) {
				return false;
			}
			System.out.print(uresult +"test");
			
			ResultSet rs = this.getUser(item.getUser_name(), item.getUser_pass());
			
			if (rs.next()) {				
				
				String esql ="INSERT INTO tblemployee(";
				esql += "employee_id, employee_contract_expired_date, employee_status, ";
				esql += "employee_work_time_length, employee_workplace_start_time, employee_workplace_id, ";
				esql += "employee_work_finished_day, employee_role";
				esql += ")";
				esql += "VALUES(?,?,?,?,?,?,?,?)";
				
				PreparedStatement pre = this.con.prepareStatement(esql);
				pre.setInt(1, rs.getInt("user_id"));
				pre.setString(2, item.getEmployee_contract_expired_date());
				pre.setBoolean(3, item.isEmployee_status());
				pre.setInt(4, item.getEmployee_work_time_length());
				pre.setString(5, item.getEmployee_workplace_start_time());
				pre.setInt(6, item.getEmployee_workplace_id());
				pre.setInt(7, item.getEmployee_work_finished_day());
				pre.setByte(8, item.getEmployee_role());
				
				return this.add(pre);
			}
				
			rs.close();
			
			return false;
			
		} catch (SQLException e) {
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	private boolean isExisting(EmployeeObject item) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT employee_id FROM tblemployee ");
		sql.append("LEFT JOIN tbluser ON tblemployee.employee_id = tbluser.user_id ");
		sql.append("WHERE (user_name='").append(item.getUser_name()).append("');");
		
		ArrayList<ResultSet> res = this.getReList(sql.toString());
		
		for (ResultSet rs: res) {
			try {
				if (rs!=null && rs.next()) {
					flag = false;
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	@Override
	public boolean editEmployee(EmployeeObject item, EMPLOYEE_EDIT_TYPE et) {
		// TODO Auto-generated method stub
		String sql ="UPDATE tbluser SET ";
		switch (et) {
			case GENERAL:						
				sql += "user_name, user_pass, ";
				sql += "user_nickname, user_fullname, user_images, ";
				sql += "user_email, user_notes, ";
				sql += "user_last_modified_id, user_gender, ";
				sql += "user_address,";
				sql += "user_mobile_phone, user_office_phone, user_social_links";
				sql += ")";
			break;
			
			case SETTINGS:
				sql+="user_permission=?";
			break;
			
			case PASS:
				sql+="user_pass=md5(?) ";
			break;
			
			case TRASH:
				sql+="user_deleted=1, user_last_modified_date=?";
			break;
		}
		
		sql += "WHERE user_id=?";
		
		
		//Bien dich
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			switch(et) {
				case GENERAL:
					
					pre.setString(1, item.getUser_name());
					pre.setString(2, item.getUser_pass());
					pre.setString(3, item.getUser_nickname());
					pre.setString(4, item.getUser_fullname());
					pre.setString(5, item.getUser_images());
					pre.setString(6, item.getUser_email());
					pre.setString(7, item.getUser_notes());		
					pre.setInt(8, item.getUser_last_modified_id());
					pre.setByte(9, item.getUser_gender());
					pre.setString(10, item.getUser_mobile_phone());	
					pre.setString(11, item.getUser_office_phone());
					pre.setString(12, item.getUser_email());
					pre.setString(13, item.getUser_address());

					pre.setInt(14, item.getEmployee_id());
					break;
				
				case SETTINGS:
					pre.setByte(1, item.getUser_permission());
					pre.setInt(2, item.getUser_id());
					break;
				
				case PASS:
					pre.setString(1, item.getUser_pass());
					pre.setInt(2, item.getUser_id());
					break;
				
				case TRASH:
					pre.setString(1, item.getUser_last_modified_date());
					pre.setInt(2, item.getUser_id());
					break;
			}
			
			return this.edit(pre);
		} catch (SQLException e) {
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delEmployee(EmployeeObject item) {
		// TODO Auto-generated method stub
		
		if (!this.isEmpty(item)) {
			return false;
		}
		
		String sql = "DELETE FROM tblemployee WHERE employee_id=?;";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getEmployee_id());
			
			return this.del(pre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	
	private boolean isEmpty(EmployeeObject item) {
		boolean flag = true;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT export_bill_id FROM tblexportbill LEFT JOIN tblbill ");
		sql.append("ON tblexportbill.export_bill_id = tblbill.bill_id ");
		sql.append("WHERE (bill_creator_id="+ item.getUser_id()+") ;");
		sql.append("SELECT user_id FROM tbluser WHERE (user_parent_id="+item.getUser_id()+") ;");
		
		ArrayList<ResultSet> res = this.getReList(sql.toString());
		
		for (ResultSet rs: res) {
			try {
				if (rs!=null && rs.next()) {
					flag = false;
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public ResultSet getEmployee(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblemployee LEFT JOIN tbluser ON tbluser.user_id=tblemployee.employee_id WHERE (employee_id=?) AND (user_deleted=0); ";
		return this.get(sql, id);
	}	
	
	@Override
	public ResultSet getEmployee(String username, String userpass) {
		// TODO Auto-generated method stub
		String sqlSelect = "SELECT * FROM tblemployee RIGHT JOIN tbluser ON tbluser.user_id=tblemployee.employee_id WHERE (user_name=?) AND (user_pass=md5(?)) AND (user_deleted=0); ";
		String sqlUpdate = "UPDATE tbluser SET user_logined = user_logined+1 WHERE (user_name=?) AND (user_pass=md5(?)); ";
		
		ArrayList<String> sql = new ArrayList<>();
		sql.add(sqlSelect);
		sql.add(sqlUpdate);
		
		return this.get(sql, username, userpass);
	}

	public ArrayList<ResultSet> getEmployees(EmployeeObject similar, int at, byte uPerPage, EMPLOYEE_SORT_TYPE type) {
		// TODO Auto-generated method stub
		
		String sql ="SELECT * FROM tblemployee LEFT JOIN tbluser ON tblemployee.employee_id = tbluser.user_id ";
		sql+= this.createCondition(similar);
		
		switch (type) {
			case NAME:
				sql+= " ORDER BY user_name ASC ";
				break;
			case FULLNAME:
				sql+= " ORDER BY user_fullname ASC ";
				break;
			case ADDRESS:
				sql+= " ORDER BY user_address ASC ";
				break;
			case CREATED:
				sql+= " ORDER BY user_created_date DESC ";
				break;
			case MODIFIED:
				sql+= " ORDER BY user_last_modified_date DESC ";
				break;
			default:
				sql+= "ORDER BY employee_id ASC ";
		}
		
		sql+= "LIMIT "+at+"," +uPerPage+";";
		sql+= " ";
		
		StringBuilder multiSelect = new StringBuilder();
		multiSelect.append(sql);
		multiSelect.append("SELECT COUNT(employee_id) AS total FROM tblemployee ");
		multiSelect.append(this.createCondition(similar));
		multiSelect.append(";");
		
		return this.getReList(multiSelect.toString());
	}
	
	private String createCondition(EmployeeObject similar) {
		 StringBuilder conds = new StringBuilder();
		 
		 if (similar!=null) {
			 byte permis = similar.getUser_permission();// Tài khoản đăng nhập truyên cho
			 
			 //Phân tầng quản trị
			 conds.append("(user_permission<=").append(permis).append(") ");
			 
			 if (permis<4) {
				 int id = similar.getUser_id();
				 
				 if (id>0) {
					 conds.append("AND ( (user_parent_id=").append(id).append(") OR (user_id=").append(id).append(") )") ;
				 }
			 }
			 
			 //Xử lí từ khóa tìm kiếm
			 String key = similar.getUser_name();
			 if (key!=null && !key.equalsIgnoreCase("")) {
				 conds.append(" AND (");
				 conds.append("(user_name LIKE '%"+key+"%') OR ");
				 conds.append("(user_fullname LIKE '%"+key+"%') OR ");
				 conds.append("(user_address LIKE '%"+key+"%') OR ");
				 conds.append("(user_email LIKE '%"+key+"%')");
				 conds.append(") ");
			 }
			 
			 //Kiểm tra tồn tại
			 if (similar.isUser_deleted()) {
				 conds.append("AND (user_deleted=1)");
			 } else {
				 conds.append("AND (user_deleted=0)");
			 }
		 }
		 
		 if (!conds.toString().equalsIgnoreCase("")) {
			 conds.insert(0,"WHERE ");
		 }
		 
		 return conds.toString();
	}
	
	public static void main(String[] args) {
		//Khoi tao bo quan li ket noi
		ConnectionPool cp = new ConnectionPoolImpl();
		
		//Tao doi tuong thuc thi chuc nang muc User
		Employee u=new EmployeeImpl(cp);
		
		//Them mot nguoi su dung
		EmployeeObject new_Employee = new EmployeeObject();
		new_Employee.setUser_name("Huong-tester2");
		new_Employee.setUser_pass("admin1");
		new_Employee.setUser_fullname("Tran The Huong");
		new_Employee.setUser_email("admin1@gmail.com");
		new_Employee.setUser_address("Ha Noi");
		new_Employee.setUser_created_date("29/12/2003");
		new_Employee.setUser_parent_id(20);
		new_Employee.setEmployee_id(15);
		
		boolean result = u.delEmployee(new_Employee);
		
		if (!result) {
			System.out.print("\n---------------------Khong thanh cong-------------------\n");
		}
		
		//Lay tap ban ghi nguoi su dung
		ArrayList<ResultSet> res = u.getEmployees(null,0,(byte)25, EMPLOYEE_SORT_TYPE.NAME);
		
		ResultSet rs = res.get(0);
		String row;
		//Duyen va hien thi danh sach nguoi su dung
		if (rs!=null) {
			try {
				while (rs.next()) {
					row = "ID: "+rs.getInt("user_id");
					row += "\tNAME: "+rs.getString("user_name");
					row += "\tFULLNAME: "+rs.getString("user_fullname");
					row += "\tPASS: "+rs.getString("user_pass");
					row += "\tLOGINED: "+rs.getShort("user_logined");
					
					System.out.println(row);
				}			
				rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		rs=res.get(1);
		if (rs!=null) {
			try {
				if (rs.next()) {
					System.out.print("Total:"+rs.getInt("total"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
