package user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

import connection.*;
import library.Utilities;
import basic.*;
import objects.UserObject;

public class UserImpl extends BasicImpl implements User {
	
	public UserImpl(ConnectionPool cp) {
		super(cp, "User");
	}
	
	public UserImpl(ConnectionPool cp, String objecname) {
		super(cp, objecname);
	}

	@Override
	public boolean addUser(UserObject item) {
		// TODO Auto-generated method stub
		
		if (this.isExisting(item)) {
			return false;
		}
		
		String sql ="INSERT INTO tbluser(";
		sql += "user_name, user_pass, ";
		sql += "user_nickname, user_fullname, user_images, ";
		sql += "user_email, user_notes, user_permission, ";
		sql += "user_last_modified_id, user_gender, ";
		sql += "user_address,";
		sql += "user_mobile_phone, user_office_phone, user_social_links, user_parent_id";
		sql += ")";
		sql += "VALUES(?,md5(?),?,?,?,?,?,?,?,?,?,?,?,?,?)";	
		
		//Bien dich
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setString(1, item.getUser_name());
			pre.setString(2, item.getUser_pass());
			pre.setString(3, item.getUser_nickname());
			pre.setString(4, Utilities.encode(item.getUser_fullname()));
			pre.setString(5, item.getUser_images());
			pre.setString(6, item.getUser_email());
			pre.setString(7, item.getUser_notes());		
			pre.setByte(8, item.getUser_permission());	
			pre.setInt(9, item.getUser_last_modified_id());
			pre.setByte(10, item.getUser_gender());
			pre.setString(11, Utilities.encode(item.getUser_address()));
			pre.setString(12, item.getUser_mobile_phone());	
			pre.setString(13, item.getUser_office_phone());
			pre.setString(14, item.getUser_social_links());
		
			pre.setInt(15, item.getUser_parent_id());

			
			return this.add(pre);
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

	private boolean isExisting(UserObject item) {
		boolean flag = false;
		String sql = "SELECT user_id FROM tbluser WHERE (user_name='"+item.getUser_name()+"'); ";
		ResultSet rs = this.gets(sql);
		try {
			if (rs.next()) {
				flag = true;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	
	@Override
	public boolean editUser(UserObject item, USER_EDIT_TYPE et) {
		// TODO Auto-generated method stub
		
		String sql ="UPDATE tbluser SET ";
		switch (et) {
			case GENERAL:
				sql += "(";
				sql += "user_nickname=?, user_fullname=?, user_images=?, ";
				sql += "user_email=?, user_notes=?, ";
				sql += "user_last_modified_id=?, user_gender=?, ";
				sql += "user_address=?,";
				sql += "user_mobile_phone=?, user_office_phone=?, user_social_links=?";
				sql += ") ";
			break;
			
			case SETTINGS:
				sql+="user_permission=? ";
			break;
			
			case TRASH:
				sql+="user_deleted=1, user_last_modified_date=? ";
			break;
		}
		
		sql += "WHERE user_id=?; ";
		
		
		//Bien dich
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			switch(et) {
				case GENERAL:
					pre.setString(1, item.getUser_nickname());
					pre.setString(2, item.getUser_fullname());
					pre.setString(3, item.getUser_images());
					pre.setString(4, item.getUser_email());
					pre.setString(5, item.getUser_notes());		
					pre.setInt(6, item.getUser_last_modified_id());
					pre.setByte(7, item.getUser_gender());
					pre.setString(8, item.getUser_mobile_phone());	
					pre.setString(9, item.getUser_office_phone());
					pre.setString(10, item.getUser_email());
					pre.setString(11, item.getUser_address());

					pre.setInt(14, item.getUser_id());
					break;
				
				case SETTINGS:
					pre.setByte(1, item.getUser_permission());
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
	public boolean delUser(UserObject item) {
		// TODO Auto-generated method stub
		
		if (!this.isEmpty(item)) {
			return false;
		}
		
		String sql = "DELETE FROM tbluser WHERE (user_id=?);";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getUser_id());
			
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
	
	private boolean isEmpty(UserObject item) {
		boolean flag = true;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT export_bill_id FROM tblexportbill WHERE (export_bill_customer_id="+ item.getUser_id()+"); ");
		sql.append("SELECT user_id FROM tbluser WHERE (user_parent_id="+item.getUser_id()+"); ");
		sql.append("SELECT employee_id FROM tblemployee WHERE (employee_id="+item.getUser_id()+"); ");
		
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
	public ResultSet getUser(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbluser WHERE (user_id=?) AND (user_deleted=0); ";
		return this.get(sql, id);
	}

	@Override
	public ResultSet getUser(String username, String userpass) {
		// TODO Auto-generated method stub
		String sqlSelect = "SELECT * FROM tbluser WHERE (user_name=?) AND (user_pass=md5(?)) AND (user_deleted=0); ";
		System.out.println(sqlSelect);
		String sqlUpdate = "UPDATE tbluser SET user_logined = user_logined+1 WHERE (user_name=?) AND (user_pass=md5(?)); ";
		
		ArrayList<String> sql = new ArrayList<>();
		sql.add(sqlSelect);
		sql.add(sqlUpdate);
		
		return this.get(sql, username, userpass);
	}

	@Override
	public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte uPerPage, USER_SORT_TYPE type) {
		// TODO Auto-generated method stub
		
		String sql ="SELECT * FROM tbluser ";
		sql+= this.createCondition(similar);
		
		switch (type) {
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
				sql+= "ORDER BY user_id ASC ";
		}
		
		sql+= "LIMIT "+at+"," +uPerPage+";";
		sql+= " ";
		
		StringBuilder multiSelect = new StringBuilder();
		multiSelect.append(sql);
		multiSelect.append("SELECT COUNT(user_id) AS total FROM tbluser ");
		multiSelect.append(this.createCondition(similar));
		multiSelect.append(";");
		
		return this.getReList(multiSelect.toString());
	}
	
	private String createCondition(UserObject similar) {
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
			 String key = similar.getUser_fullname();
			 if (key!=null && !key.equalsIgnoreCase("")) {
				 conds.append(" AND (");
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
		User u=new UserImpl(cp);
		
		//Them mot nguoi su dung
		UserObject new_user = new UserObject();
		new_user.setUser_name("Admin6");
		new_user.setUser_pass("Admin1@");
		new_user.setUser_nickname("Huong");
		new_user.setUser_fullname("Tran The Huong");
		new_user.setUser_email("admin1@gmail.com");
		new_user.setUser_address("Ha Noi");
		new_user.setUser_created_date("29/12/2003");
		new_user.setUser_parent_id(20);
		
		new_user.setUser_id(17);
		boolean result = u.delUser(new_user);
		
		if (!result) {
			System.out.print("\n---------------------Khong thanh cong---------------------\n");
		}
		
		//Lay tap ban ghi nguoi su dung
		ArrayList<ResultSet> res = u.getUsers(null,0,(byte)25, USER_SORT_TYPE.FULLNAME);
		
		ResultSet rs = res.get(0);
		String row;
		//Duyen va hien thi danh sach nguoi su dung
		if (rs!=null) {
			try {
				while (rs.next()) {
					row = "ID: "+rs.getInt("user_id");
					row += "\tNAME: "+rs.getString("user_name");
					row += "\tNICKNAME: "+rs.getString("user_nickname");
					row += "\tFULLNAME: "+rs.getString("user_fullname");
					row += "\tPARENT: "+rs.getString("user_parent_id");
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
