package bill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Sextet;

import basic.BasicImpl;
import connection.ConnectionPool;
import connection.ConnectionPoolImpl;
import employee.EMPLOYEE_SORT_TYPE;
import employee.Employee;
import employee.EmployeeImpl;
import library.Utilities;
import objects.BillObject;
import objects.EmployeeObject;
import objects.ImportBillObject;
import objects.UserObject;
import bill.BILL_SORT_TYPE;

public class BillImpl extends BasicImpl implements Bill {

	public BillImpl(ConnectionPool cp) {
		super(cp, "Bill");
		// TODO Auto-generated constructor stub
	}
	
	public BillImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	private boolean isExisting(BillObject item) {
		boolean flag = false;
		String sql = "SELECT bill_id FROM tblbill WHERE (bill_id='"+item.getBill_id()+"'); ";
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
	public boolean addBill(BillObject item) {
		
		if (this.isExisting(item)) {
			return false;
		}
		
		StringBuilder getIdSql = new StringBuilder();
		getIdSql.append("SELECT AUTO_INCREMENT ");
		getIdSql.append("FROM information_schema.TABLES ");
		getIdSql.append("WHERE TABLE_SCHEMA=\"test\" ");
		getIdSql.append("AND TABLE_NAME=\"tblbill\"; ");
		System.out.println(getIdSql.toString());		
		ResultSet billId = this.getReList(getIdSql.toString()).get(0);
		
		if (billId==null) {
			return false;
		}
		
		try {
			if (billId.next()) {
				int id = billId.getInt("AUTO_INCREMENT");
				billId.close();			
				
				StringBuilder sql = new StringBuilder();
				
				sql.append("INSERT INTO tblbill(");
				sql.append("bill_status, bill_created_date, ");
				sql.append("bill_last_modified_date, bill_last_modified_id, bill_creator_id, ");
				sql.append("bill_transporter_id, bill_type ");
				sql.append(")");
				sql.append("VALUES(?,?,?,?,?,?,?); ");	
				
				switch (item.getBill_type()) {
					case 1:				
						sql.append("INSERT INTO tblimportbill(");
						sql.append("import_bill_id, import_bill_target_workplace_id");
						sql.append("import_bill_provider_id");				
						sql.append(")");
						sql.append("VALUES(?,?,?);");	
						break;
					
					case 2:
						sql.append("INSERT INTO tblexportbill(");
						sql.append(")");
					
						sql.append("VALUES(?,?,?,?,?,?,?,?);");	
						break;
					
					case 3:
						sql.append("INSERT INTO tbltransferbill(");
						sql.append(")");
						sql.append("VALUES(?,?,?,?,?,?,?,?);");	
						break;
						
					default:
						
						break;
				}
				
				sql.append("INSERT INTO tblbilldetail(");
				sql.append("billdetail_id, billdetail_price, ");
				sql.append("billdetail_product_id, billdetail_product_quantity");				
				sql.append(")");
				sql.append("VALUES(?,?,?,?);");	
				
				PreparedStatement pre = this.con.prepareStatement(sql.toString());
				
				pre.setByte(1,item.getBill_status());
				pre.setString(2, item.getBill_created_date());		
				pre.setString(3, item.getBill_last_modified_date());
				pre.setInt(4, item.getBill_last_modified_id());
				pre.setInt(5, item.getBill_creator_id());
				pre.setInt(6, item.getBill_transporter_id());
				pre.setByte(7, item.getBill_type());
				
				switch (item.getBill_type()) {
				case 1:				
					ImportBillObject ibo = (ImportBillObject) item;
					pre.setInt(8, id);
					pre.setInt(9, ibo.getImport_bill_target_workplace_id());
					pre.setInt(11, ibo.getImport_bill_provider_id());
					
					break;
				
				case 2:
					break;
				
				case 3:

					break;
					
				default:
					
					break;
				}
				
				pre.setInt(12, id);
				pre.setInt(13, id);
				pre.setInt(14, id);
				pre.setInt(15, id);
				
				return this.addList(pre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

	@Override
	public boolean editBill(BillObject item, BILL_EDIT_TYPE et) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblbill SET ");
		switch (et) {
			case GENERAL:
				sql.append("bill_status, ");
				sql.append("bill_last_modified_date, bill_last_modified_id, ");
				sql.append("bill_transporter_id,");
			break;
			
		}
		
		sql.append("WHERE (bill_id=?); ");
		
		
		//Bien dich
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			switch(et) {
				case GENERAL:
					pre.setByte(1,item.getBill_status());
					pre.setString(2, item.getBill_last_modified_date());
					pre.setInt(3, item.getBill_last_modified_id());
					pre.setInt(4, item.getBill_transporter_id());
					pre.setInt(6, item.getBill_id());
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

	//Phương thức kiểm tra tồn tại
	private boolean isEmpty(BillObject item) {
		boolean flag = true;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user_id FROM tbluser WHERE (user_id="+item.getBill_creator_id()+") ;");
		sql.append("SELECT user_id FROM tbluser WHERE (user_id="+item.getBill_last_modified_id()+") ;");
		
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
	public boolean delBill(BillObject item) {
		
		if (!this.isEmpty(item)) {
			return false;
		}
		
		String sql = "DELETE FROM tblbill WHERE (bill_id=?);";
		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, item.getBill_id());
			
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

	@Override
	public ResultSet getBill(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblbill WHERE (bill_id=?) AND (bill_deleted=0); ";
		return this.get(sql, id);
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
	
	@Override
	public ArrayList<ResultSet> getBills(Sextet<EmployeeObject, BillObject, Integer, Byte, BILL_SORT_TYPE, Boolean> infors) {
		EmployeeObject currentUser = infors.getValue0();
		BillObject similar = infors.getValue1();
		int at = infors.getValue2();
		byte bPerPage = infors.getValue3(); 
		BILL_SORT_TYPE type = infors.getValue4();
		boolean isDetail = infors.getValue5();
		
		StringBuilder sql = new StringBuilder();
		
		if (isDetail) {
			sql.append("SELECT * FROM tblbill ");
			sql.append("WHERE (bill_id="+similar.getBill_id()+"); ");
			sql.append("LEFT JOIN tblemployee ON tblbill.bill_creator_id=tblemployee.employee_id ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id");
		} else {
			sql.append("SELECT * FROM tblbill ");	 
			sql.append("LEFT JOIN tblemployee ON tblbill.bill_creator_id=tblemployee.employee_id ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id");
	
//			sql.append(this.createCondition(currentUser));
			switch (type) {	
				case NAME:
					sql.append(" ORDER BY bill_id ASC ");
				break;
				case ADDRESS:
					sql.append(" ORDER BY bill_address ASC ");
					break;
				case CREATED:
					sql.append(" ORDER BY bill_created_date DESC ");
					break;
				case MODIFIED:
					sql.append(" ORDER BY bill_last_modified_date DESC ");
					break;
				default:
					sql.append(" ORDER BY bill_id ASC ");
			}	
			sql.append("LIMIT "+at+"," +bPerPage+"; ") ;
			sql.append(" ");					
			
		}		
		
		sql.append("SELECT tblbill.bill_created_date, SUM(bill_detail_product_price*bill_detail_product_quantity) as export_price FROM tblbill ");
		sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
		sql.append("WHERE bill_type=1 ");
		sql.append("GROUP BY tblbill.bill_created_date ");
		sql.append("ORDER BY STR_TO_DATE(bill_created_date, '%e/%c/%Y') ASC ");
		sql.append("LIMIT 50; ");

		sql.append("SELECT tblbill.bill_created_date, SUM(bill_detail_product_price*bill_detail_product_quantity) as import_price FROM tblbill ");
		sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
		sql.append("WHERE bill_type=0 ");
		sql.append("GROUP BY tblbill.bill_created_date ");
		sql.append("ORDER BY STR_TO_DATE(bill_created_date, '%e/%c/%Y') ASC ");
		sql.append("LIMIT 50; ");
		
		
		sql.append("SELECT COUNT(bill_id) AS total FROM tblbill");
//		sql.append(this.createCondition(currentUser));
		sql.append("; ");
		System.out.print(sql.toString());
		return this.getReList(sql.toString());
	}

	public static void main(String[] args) {
		//Khoi tao bo quan li ket noi
		ConnectionPool cp = new ConnectionPoolImpl();
		
		//Tao doi tuong thuc thi chuc nang muc User
		BillImpl u = new BillImpl(cp);
		
		//Them mot nguoi su dung
		ImportBillObject bill = new ImportBillObject();
		
		bill.setBill_creator_id(40);
		bill.setBill_created_date("29/12/2003");
		bill.setBill_type((byte)1);
		bill.setImport_bill_product_id(5);
		bill.setImport_bill_product_quantity(10);
		bill.setImport_bill_price(100);

		
		
//		boolean result = u.addBill(bill);
//		
//		if (!result) {
//			System.out.print("\n---------------------Khong thanh cong-------------------\n");
//		}
		
		//Lay tap ban ghi nguoi su dung
		EmployeeObject employee = new EmployeeObject();
		ArrayList<ResultSet> res = u.getBills(new Sextet<EmployeeObject, BillObject, Integer, Byte, BILL_SORT_TYPE, Boolean>(employee,null,1,(byte)10,BILL_SORT_TYPE.CREATED,false));
		
		ResultSet rs = res.get(2);
		String row;
		//Duyen va hien thi danh sach nguoi su dung
		if (rs!=null) {
			try {
				while (rs.next()) {
					row = "ID: "+rs.getString("bill_created_date");
					row += "\tNAME: "+rs.getString("import_price");
					
					System.out.println(row);
				}			
				rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		rs=res.get(4);
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
