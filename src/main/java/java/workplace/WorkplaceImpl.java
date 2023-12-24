package workplace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

import org.javatuples.Sextet;

import connection.*;
import library.Utilities;
import basic.*;
import objects.*;


public class WorkplaceImpl extends BasicImpl implements Workplace {
	
	public WorkplaceImpl(ConnectionPool cp) {
		super(cp, "Workplace");
	}
	
	public WorkplaceImpl(ConnectionPool cp, String objecname) {
		super(cp, objecname);
	}

	@Override
	public synchronized boolean addWorkplace(ArrayList<WorkplaceObject> wItems, ArrayList<WorkplaceStorageDetail> wsdItem) {
		WorkplaceObject wItem = wItems.get(0);
		if (this.isExisting(wItem)) {
			return false;
		}
		
		StringBuilder getIdSql = new StringBuilder();
		getIdSql.append("SELECT AUTO_INCREMENT ");
		getIdSql.append("FROM information_schema.TABLES ");
		getIdSql.append("WHERE TABLE_SCHEMA=\"test\" ");
		getIdSql.append("AND TABLE_NAME=\"tblworkplace\"; ");
		System.out.println(getIdSql.toString());		
		ResultSet workplaceId = this.getReList(getIdSql.toString()).get(0);
		
		if (workplaceId==null) {
			return false;
		}
		
		try {
			if (workplaceId.next()) {
				int id = workplaceId.getInt("AUTO_INCREMENT");
				workplaceId.close();
				
				StringBuilder sql = new StringBuilder();			
				
				sql.append("INSERT INTO tblworkplace(");
				sql.append("workplace_name, workplace_type, workplace_address, ");
				sql.append("workplace_manager_id, workplace_created_date, workplace_website_link, ");
				sql.append("workplace_map_link, workplace_last_modified_id, ");
				sql.append("workplace_last_modified_date, workplace_images, ");
				sql.append("workplace_investment, workplace_expected_profit, workplace_notes, ");
				sql.append("workplace_phone, workplace_email, workplace_creator_id");
				sql.append(")");
				sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ");
				System.out.println(sql.toString());
				
				PreparedStatement pre1 = this.con.prepareStatement(sql.toString());
				
				pre1.setString(1,wItem.getWorkplace_name());
				pre1.setByte(2, wItem.getWorkplace_type());
				pre1.setString(3, wItem.getWorkplace_address());
				pre1.setInt(4, wItem.getWorkplace_manager_id());
				pre1.setString(5, wItem.getWorkplace_created_date());
				pre1.setString(6, wItem.getWorkplace_website_link());
				pre1.setString(7, wItem.getWorkplace_map_link());
				pre1.setInt(8, wItem.getWorkplace_last_modified_id());
				pre1.setString(9, wItem.getWorkplace_last_modified_date());
				pre1.setString(10, wItem.getWorkplace_images());
				pre1.setInt(11,wItem.getWorkplace_investment());
				pre1.setInt(12,wItem.getWorkplace_expected_profit());
				pre1.setString(13, wItem.getWorkplace_notes());
				pre1.setString(14, wItem.getWorkplace_phone());
				pre1.setString(15, wItem.getWorkplace_email());
				pre1.setInt(16, wItem.getWorkplace_creator_id());
				
				boolean result = this.add(pre1);
				
				if (result && wsdItem != null && wsdItem.size()>0) {
					sql.setLength(0);
					sql.append("INSERT INTO tblworkplacestoragedetail(");
					sql.append("workplace_id, product_id,");
					sql.append("product_quantity");
					sql.append(")");
					sql.append("VALUES");
					sql.append("(?,?,?)");		
					System.out.println(sql.toString());
					PreparedStatement pre2 = this.con.prepareStatement(sql.toString());									
					wsdItem.forEach(item->{
						try {
							pre2.setInt(1, id);
							pre2.setInt(2, item.getProduct_id());
							pre2.setInt(3, item.getProduct_quantity());
							pre2.addBatch();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
					});	
					return this.addList(pre2);							
				}					
				return result;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return false;
	}
	
	
	
//	public boolean addWorkplace(WorkplaceObject wItem, ArrayList<WorkplaceStorageDetail> wsdItem) {
//		if (this.isExisting(wItem)) {
//			return false;
//		}
//		
//		StringBuilder getIdSql = new StringBuilder();
//		getIdSql.append("SELECT AUTO_INCREMENT ");
//		getIdSql.append("FROM information_schema.TABLES ");
//		getIdSql.append("WHERE TABLE_SCHEMA=\"test\" ");
//		getIdSql.append("AND TABLE_NAME=\"tblworkplace\"; ");
//		System.out.println(getIdSql.toString());		
//		ResultSet workplaceId = this.getReList(getIdSql.toString()).get(0);
//		
//		if (workplaceId==null) {
//			return false;
//		}
//		
//		try {
//			if (workplaceId.next()) {
//				int id = workplaceId.getInt("AUTO_INCREMENT");
//				workplaceId.close();
//				StringBuilder sql = new StringBuilder();			
//				
//				sql.append("INSERT INTO tblworkplace(");
//				sql.append("workplace_name, workplace_type, workplace_address, ");
//				sql.append("workplace_manager_id, workplace_created_date, workplace_website_link, ");
//				sql.append("workplace_map_link, workplace_last_modified_id, ");
//				sql.append("workplace_last_modified_date, workplace_images");
//				sql.append(")");
//				sql.append("VALUES(?,?,?,?,?,?,?,?,?,?); ");
//				
//				
//				if (wsdItem != null && wsdItem.size()>0) {
//					sql.append("INSERT INTO tblworkplacestoragedetail(");
//					sql.append("workplace_id, product_id,");
//					sql.append("product_quantity");
//					sql.append(")");
//					sql.append("VALUES");
//					
//					wsdItem.forEach(item->{
//						sql.append("(?,?,?)");
//						
//						if (wsdItem.indexOf(item)<wsdItem.size()-1) {
//							sql.append(",");
//						}
//					});
//					sql.append(";");			
//				}
//				
//				System.out.println(sql.toString());
//				//Bien dich
//	
//				PreparedStatement pre = this.con.prepareStatement(sql.toString());
//				pre.setString(1,wItem.getWorkplace_name());
//				pre.setByte(2, wItem.getWorkplace_type());
//				pre.setString(3, wItem.getWorkplace_address());
//				pre.setInt(4, wItem.getWorkplace_manager_id());
//				pre.setString(5, wItem.getWorkplace_created_date());
//				pre.setString(6, wItem.getWorkplace_website_link());
//				pre.setString(7, wItem.getWorkplace_map_link());
//				pre.setInt(8, wItem.getWorkplace_last_modified_id());
//				pre.setString(9, wItem.getWorkplace_last_modified_date());
//				pre.setString(10, wItem.getWorkplace_images());
//				
//				if (wsdItem != null && wsdItem.size()>0) {
//					wsdItem.forEach(item->{
//						try {
//							pre.setInt(11+wsdItem.indexOf(item)*3, id);
//							pre.setInt(12+wsdItem.indexOf(item)*3, item.getProduct_id());
//							pre.setInt(13+wsdItem.indexOf(item)*3, item.getProduct_quantity());
//							
//						} catch (SQLException e) {
//							e.printStackTrace();
//						}
//					});
//				}
//				return this.addListV1(pre,1);
//			}
//		} catch (SQLException e2) {
//			e2.printStackTrace();
//		}
//		return false;
//	}
	
	

	private boolean isExisting(WorkplaceObject item) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT workplace_id FROM tblworkplace WHERE (tblworkplace.workplace_name='"+item.getWorkplace_name()+"');");

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
	

	
	/**
	 * Chỉnh sửa một Nơi làm việc
	 * Add: 25/10/2023 
	 * By Trần Thế Hưởng
	 * Param: item
	 * 
	 */
	@Override
	public boolean editWorkplace(ArrayList<WorkplaceObject> wItems, ArrayList<WorkplaceStorageDetail> pItem, WORKPLACE_EDIT_TYPE et) {
		// TODO Auto-generated method stub
		WorkplaceObject wItem = wItems.get(0);
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE tblworkplace SET ");
		switch (et) {
			case GENERAL:
				sql.append("workplace_name=?, workplace_type=?, workplace_address=?, ");
				sql.append("workplace_status=?, workplace_website_link=?, workplace_map_link=?, ");
				sql.append("workplace_last_modified_id=?, workplace_last_modified_date=? ");
				sql.append("workplace_image ");
			break;
			
			case MANAGER:
				sql.append("workplace_manager_id=?, workplace_last_modified_id=?, workplace_last_modified_date=?");
			break;
			
			case TRASH:
				sql.append("workplace_deleted=1, workplace_last_modified_id=?, workplace_last_modified_date=?");
			break;
		}
		
		sql.append("WHERE (workplace_id=?); ");
		
		
		//Bien dich
		try {
			PreparedStatement pre = this.con.prepareStatement(sql.toString());
			switch(et) {
				case GENERAL:
					pre.setString(1, wItem.getWorkplace_name());
					pre.setByte(2, wItem.getWorkplace_type());
					pre.setString(3, wItem.getWorkplace_address());
					pre.setByte(4, wItem.getWorkplace_status());
					pre.setString(5, wItem.getWorkplace_website_link());		
					pre.setString(6, wItem.getWorkplace_map_link());
					pre.setInt(7, wItem.getWorkplace_last_modified_id());
					pre.setString(8, wItem.getWorkplace_last_modified_date());	
					pre.setString(9, wItem.getWorkplace_images());	
					pre.setInt(10, wItem.getWorkplace_id());
					break;
				
				case MANAGER:
					pre.setInt(1, wItem.getWorkplace_manager_id());
					pre.setInt(2, wItem.getWorkplace_last_modified_id());
					pre.setString(3, wItem.getWorkplace_last_modified_date());	
					pre.setInt(4, wItem.getWorkplace_id());
					break;
				
				
				case TRASH:
					pre.setInt(1, wItem.getWorkplace_last_modified_id());
					pre.setString(2, wItem.getWorkplace_last_modified_date());	
					pre.setInt(3, wItem.getWorkplace_id());
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
	public boolean delWorkplace(ArrayList<WorkplaceObject> wItems) {
		// TODO Auto-generated method stub
		WorkplaceObject wItem = wItems.get(0);
		
		if (!this.isEmpty(wItem)) {
			return false;
		}		
		String sql = "DELETE FROM tblworkplace WHERE (workplace_id=?);";		
		try {
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.setInt(1, wItem.getWorkplace_id());
			
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
	
	private boolean isEmpty(WorkplaceObject item) {
		boolean flag = true;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT export_bill_id FROM tblexportbill WHERE "
				+ "(tblexportbill.export_bill_current_workplace_id="+ item.getWorkplace_id()+"); ");
		
		sql.append("SELECT transfer_bill_id FROM tbltransferbill "
				+ "WHERE ((tbltransferbill.transfer_bill_current_workplace_id="+item.getWorkplace_id()+") OR (transfer_bill_target_workplace_id="+item.getWorkplace_id()+")); ");
		
		sql.append("SELECT import_bill_id FROM tblimportbill "
				+ "WHERE ((import_bill_target_workplace_id="+item.getWorkplace_id()+"));");
		
		sql.append("SELECT employee_id FROM tblemployee "
				+ "WHERE (employee_workplace_id="+item.getWorkplace_id()+"); ");
		
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
	public ResultSet getWorkplace(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tblworkplace WHERE (workplace_id=?) AND (workplace_deleted=0); ";
		return this.get(sql, id);
	}


	@Override
	public synchronized ArrayList<ResultSet> getWorkplaces(
			Sextet<EmployeeObject, 
			WorkplaceObject, 
			Integer, 
			Byte ,
			WORKPLACE_SORT_TYPE,
			Boolean> infors) {
		// TODO Auto-generated method stub
		EmployeeObject currentUser = infors.getValue0();
		WorkplaceObject similar = infors.getValue1();
		int at = infors.getValue2();
		byte wpPerPage = infors.getValue3(); 
		WORKPLACE_SORT_TYPE type = infors.getValue4();
		boolean isDetail = infors.getValue5();
		
		StringBuilder sql = new StringBuilder();			
		
		if (isDetail) {
			//Lấy thông tin chi tiết
			sql.append("SELECT * FROM tblworkplace LEFT JOIN tblemployee ");
			sql.append("ON tblworkplace.workplace_manager_id=tblemployee.employee_id ");
			sql.append("WHERE workplace_id = "+similar.getWorkplace_id()+"; ");
			
			//Lấy thông tin tạo biểu đồ
			sql.append("SELECT SUM(bill_detail_product_price*bill_detail_product_quantity) as import_price FROM tblbill ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
			sql.append("WHERE workplace_id = "+similar.getWorkplace_id()+" AND bill_type=1");
			sql.append(";");
			
			sql.append("SELECT SUM(bill_detail_product_price*bill_detail_product_quantity) as export_price FROM tblbill ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
			sql.append("WHERE workplace_id = "+similar.getWorkplace_id()+" AND bill_type=0 ");
			sql.append(";");
			
			sql.append("SELECT tblbill.bill_created_date, SUM(bill_detail_product_price*bill_detail_product_quantity) as import_price FROM tblbill ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
			sql.append("WHERE workplace_id = "+similar.getWorkplace_id()+" AND bill_type=0 ");
			sql.append("GROUP BY bill_created_date ");
			sql.append("ORDER BY STR_TO_DATE(bill_created_date, '%e/%c/%Y') ASC ");		
			sql.append(";");
			
			sql.append("SELECT tblbill.bill_created_date, SUM(bill_detail_product_price*bill_detail_product_quantity) as export_price FROM tblbill ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
			sql.append("WHERE workplace_id = "+similar.getWorkplace_id()+" AND bill_type=1 ");
			sql.append("GROUP BY bill_created_date ");
			sql.append("ORDER BY STR_TO_DATE(bill_created_date, '%e/%c/%Y') ASC ");		
			sql.append(";");
		} else {
			sql.append("SELECT * FROM tblworkplace LEFT JOIN tblemployee ");	 
			sql.append("ON tblworkplace.workplace_manager_id=tblemployee.employee_id");
//			sql.append(this.createCondition(currentUser));
			switch (type) {	
				case NAME:
					sql.append(" ORDER BY workplace_name ASC ");
				break;
				case MANAGER:
					sql.append(" ORDER BY user_name ASC ");
					break;
				case ADDRESS:
					sql.append(" ORDER BY workplace_address ASC ");
					break;
				case CREATED:
					sql.append(" ORDER BY workplace_created_date DESC ");
					break;
				case MODIFIED:
					sql.append(" ORDER BY workplace_last_modified_date DESC ");
					break;
				default:
					sql.append(" ORDER BY workplace_id ASC ");
			}	
			sql.append("LIMIT "+at+"," +wpPerPage+";") ;
			sql.append(" ");
			
			//Lấy thông tin tạo biểu đồ
			sql.append("SELECT tblbill.bill_import_target_workplace_id,SUM(bill_detail_product_price*bill_detail_product_quantity/1000) as import_value FROM tblbill ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
			sql.append("WHERE bill_type=0 ");
			sql.append("GROUP BY bill_import_target_workplace_id ");			
			sql.append(";");
			
			sql.append("SELECT tblbill.bill_export_current_workplace_id,SUM(bill_detail_product_price*bill_detail_product_quantity/1000) as export_value FROM tblbill ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
			sql.append("WHERE bill_type=1 ");
			sql.append("GROUP BY bill_export_current_workplace_id ");	
			sql.append(";");
			
			sql.append("SELECT tblbill.bill_import_target_workplace_id, bill_created_date, SUM(bill_detail_product_price*bill_detail_product_quantity/1000) as import_value FROM tblbill ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
			sql.append("WHERE bill_type=0 ");
			sql.append("GROUP BY bill_import_target_workplace_id,bill_created_date ");
			sql.append("ORDER BY STR_TO_DATE(bill_created_date, '%e/%c/%Y') ASC ");		
			sql.append(";");
			
			sql.append("SELECT tblbill.bill_export_current_workplace_id, bill_created_date, SUM(bill_detail_product_price*bill_detail_product_quantity/1000) as export_value FROM tblbill ");
			sql.append("LEFT JOIN tblbilldetail ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
			sql.append("WHERE bill_type=1 ");
			sql.append("GROUP BY bill_export_current_workplace_id,bill_created_date ");
			sql.append("ORDER BY STR_TO_DATE(bill_created_date, '%e/%c/%Y') ASC ");		
			sql.append(";");
			
		}
			
		
		sql.append("SELECT COUNT(workplace_id) AS total FROM tblworkplace;");
		
		sql.append("SELECT tblproduct.product_id, product_name, AVG(tblbilldetail.bill_detail_product_price) as popularImport FROM tblproduct ");
		sql.append("LEFT JOIN tblbilldetail ON tblbilldetail.bill_detail_product_id=tblproduct.product_id ");
		sql.append("LEFT JOIN tblbill ON tblbill.bill_id=tblbilldetail.bill_detail_id ");
		sql.append("WHERE bill_type=0 ");
		sql.append("GROUP BY product_id, product_name ");
		sql.append("ORDER BY popularImport ");
		sql.append(";");
//		sql.append(this.createCondition(currentUser));
//		System.out.print(sql.toString());		
		
		return this.getReList(sql.toString());
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
			 String key = similar.getUser_fullname();
			 if (key!=null && !key.equalsIgnoreCase("")) {
				 conds.append(" AND (");
				 conds.append("(workplace_name LIKE '%"+key+"%') OR ");
				 conds.append("(workplace_address LIKE '%"+key+"%') OR ");
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
		
		//Tao doi tuong thuc thi chuc nang muc Workplace
		Workplace u=new WorkplaceImpl(cp);
		
		//Them mot nguoi su dung
		WorkplaceObject new_Workplace = new WorkplaceObject();
		new_Workplace.setWorkplace_name("Kho hàng Nguyên");
		new_Workplace.setWorkplace_manager_id(19);
		new_Workplace.setWorkplace_address("Lâm Đồng");
		new_Workplace.setWorkplace_created_date("29/12/2003");
		
		WorkplaceStorageDetail wsd1 = new WorkplaceStorageDetail();
		wsd1.setProduct_id(1);
		wsd1.setProduct_quantity(12);
		WorkplaceStorageDetail wsd2 = new WorkplaceStorageDetail();
		wsd2.setProduct_id(3);
		wsd2.setProduct_quantity(5);
		ArrayList<WorkplaceStorageDetail> sdl = new ArrayList<WorkplaceStorageDetail>();
		sdl.add(wsd1);
		sdl.add(wsd2);
//		new_Workplace.setWorkplace_id(2);
		
		ArrayList<WorkplaceObject> new_WorkplaceList = new ArrayList<WorkplaceObject>();
		boolean result = u.addWorkplace(new_WorkplaceList,sdl);
		
		if (!result) {
			System.out.print("\n---------------------Khong thanh cong---------------------\n");
		}
		
		Sextet<EmployeeObject, 
		WorkplaceObject, 
		Integer, 
		Byte ,
		WORKPLACE_SORT_TYPE,
		Boolean> infors = new Sextet<>(null,new_Workplace,0,(byte)25, WORKPLACE_SORT_TYPE.NAME,false);
		//Lay tap ban ghi nguoi su dung
		ArrayList<ResultSet> res = u.getWorkplaces(infors);
		
		ResultSet rs = res.get(4);
		String row = null;
		//Duyen va hien thi danh sach nguoi su dung
		if (rs!=null) {
			try {
				while (rs.next()) {
//					row = "ID: "+rs.getInt("workplace_id");
//					row += "\tNAME: "+rs.getString("workplace_name");
//					row += "\tNICKNAME: "+rs.getString("workplace_manager_id");
//					row += "\tADDRESS: "+rs.getString("workplace_address");
					row = "\tDATE: "+rs.getString("bill_created_date");
					row += "\tTOTAL: "+rs.getInt("export_value");
					System.out.println(row);
				}			
				rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		rs=res.get(5);
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
