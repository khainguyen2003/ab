package workplace;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;

import org.javatuples.*;
import connection.*;
import library.Utilities;
import objects.*;

public class WorkplaceModel {
	
	private Workplace w;
	
	public WorkplaceModel(ConnectionPool cp) {
		this.w= new WorkplaceImpl(cp);
	}
	
	protected void finalize() throws Throwable{
		this.w=null;
	}
	
	public ConnectionPool getCP() {
		return this.w.getCP();
	}
	
	public void releaseConnection() {
		this.w.releaseConnection();
	}

	//***********************Chuyen huong dieu khien tu Workplace Impl*****************************************
	public boolean addWorkplace(ArrayList<WorkplaceObject> wItem, ArrayList<WorkplaceStorageDetail> pItem) {
		return this.w.addWorkplace(wItem, pItem);
	}
	
	public boolean editWorkplace(ArrayList<WorkplaceObject> wItem, ArrayList<WorkplaceStorageDetail> pItem, WORKPLACE_EDIT_TYPE et) {
		return this.w.editWorkplace(wItem, pItem, et);
	}
	
	public boolean delWorkplace(ArrayList<WorkplaceObject> item) {
		return this.w.delWorkplace(item);
	}
	
	
	//****************************************************************
	
	public WorkplaceObject getWorkplaceObject(int id) {
		//Gan gia tri khoi tao cho doi tuong WorkplaceObject
		WorkplaceObject item = null ;
		
		//Lay ban ghi 
		ResultSet rs = this.w.getWorkplace(id);
		
		
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				if (rs.next()) {
					item = new WorkplaceObject();
					item.setWorkplace_id(rs.getInt("workplace_id"));
					item.setWorkplace_name(rs.getString("workplace_name"));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}
	
	/**
	 * 
	 * @param infors
	 * @return
	 * Tham số thứ nhất là danh sách đối tượng workplace
	 */
	public Septet<	ArrayList<WorkplaceObject>,
					Integer, 
					HashMap<Integer,Integer>, 
					HashMap<Integer,Integer>, 
					HashMap<Pair<String,Integer>,Integer> ,
					HashMap<Pair<String,Integer>,Integer>,
					HashMap<Integer, Pair<String,Integer>>>
						getWorkplaceObjects(Sextet<	EmployeeObject, 
													WorkplaceObject, 
													Short, 
													Byte ,
													WORKPLACE_SORT_TYPE,
													Boolean> infors) {
		
		//GĂ¡n giĂ¡ trá»‹ khá»Ÿi táº¡o cho Ä‘á»‘i tÆ°á»£ng WorkplaceObject
		ArrayList<WorkplaceObject> items = new ArrayList<>();
		WorkplaceObject item = null ;
		
		short page = infors.getValue2();
		byte wPerPage = infors.getValue3();
		//Láº¥y báº£n ghi 
		int at = (page-1)*wPerPage;
		ArrayList<ResultSet> res = this.w.getWorkplaces(infors.setAt2(at));
		
		ResultSet rs = res.get(0);
		
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				while (rs.next()) {
					item = new WorkplaceObject();
					item.setWorkplace_id(rs.getInt("workplace_id"));
					item.setWorkplace_name(Utilities.decode(rs.getString("workplace_name")));
					item.setWorkplace_address(rs.getString("workplace_address"));
					item.setWorkplace_created_date(rs.getString("workplace_created_date"));
					item.setWorkplace_last_modified_date(rs.getString("workplace_last_modified_date"));
					item.setWorkplace_last_modified_id(rs.getInt("workplace_last_modified_id"));
					item.setWorkplace_deleted(rs.getBoolean("workplace_deleted"));
					item.setWorkplace_images(rs.getString("workplace_images"));

					
					// Dua doi tuong vao tap hop
					items.add(item);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//Láº¥y tá»•ng doanh sá»‘ nháº­p
		rs = res.get(1);
		HashMap<Integer, Integer> importTotal = new HashMap<Integer, Integer>();
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				while (rs.next()) {

					importTotal.put(rs.getInt("bill_import_target_workplace_id"), rs.getInt("import_value"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Láº¥y tá»•ng doanh sá»‘ xuáº¥t
		rs = res.get(2);
		HashMap<Integer, Integer> exportTotal = new HashMap<Integer, Integer>();
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				while (rs.next()) {

					exportTotal.put(rs.getInt("bill_export_current_workplace_id"), rs.getInt("export_value"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Láº¥y doanh sá»‘ nháº­p cá»§a má»—i kho hĂ ng
		rs = res.get(3);
		HashMap<Pair<String,Integer>,Integer> ieWorkplace = new HashMap<Pair<String,Integer>,Integer>();
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				while (rs.next()) {
					ieWorkplace.put(new Pair<>(rs.getString("bill_created_date"), rs.getInt("bill_import_target_workplace_id")), rs.getInt("import_value") );
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Láº¥y doanh sá»‘ xuáº¥t cá»§a má»—i kho hĂ ng
		rs = res.get(4);
		HashMap<Pair<String,Integer>,Integer> eeWorkplace = new HashMap<Pair<String,Integer>,Integer>();
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				while (rs.next()) {

					eeWorkplace.put(new Pair<>(rs.getString("bill_created_date"), rs.getInt("bill_export_current_workplace_id")), rs.getInt("export_value"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Láº¥y tá»•ng sá»‘ báº£n ghi
		int totalGlobal = 0;
		rs = res.get(5);
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
		
		
		//Láº¥y danh sĂ¡ch sáº£n pháº©m bĂ¡n cháº¡y nháº¥t
		rs = res.get(6);
		HashMap<Integer,Pair<String,Integer>> productList = new HashMap<Integer,Pair<String,Integer>>();
		//Chuyen doi ban ghi thanh doi tuong
		if (rs!=null) {
			try {
				while (rs.next()) {

					productList.put(rs.getInt("product_id"),new Pair<>(rs.getString("product_name"), rs.getInt("popularImport")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new Septet<	ArrayList<WorkplaceObject>, 
							Integer, 
							HashMap<Integer,Integer>, 
							HashMap<Integer,Integer>, 
							HashMap<Pair<String,Integer>,Integer>, 
							HashMap<Pair<String,Integer>,Integer>,
							HashMap<Integer,Pair<String,Integer>>>(items, totalGlobal, importTotal,exportTotal, ieWorkplace, eeWorkplace, productList);
	}
	
	public static void main(String[] args) {
		ConnectionPool cp = new ConnectionPoolImpl();
		
		WorkplaceModel wm = new WorkplaceModel(cp);
		
		WorkplaceObject similar = new WorkplaceObject();
	
		Sextet<EmployeeObject, WorkplaceObject, Short, Byte, WORKPLACE_SORT_TYPE, Boolean> infors = new 
		Sextet<EmployeeObject, WorkplaceObject, Short, Byte, WORKPLACE_SORT_TYPE, Boolean>
		(null, similar, (short) 1, (byte) 10, WORKPLACE_SORT_TYPE.NAME, false);
		Septet<	ArrayList<WorkplaceObject>, 
				Integer, 
				HashMap<Integer,Integer>, 
				HashMap<Integer,Integer>,
				HashMap<Pair<String,Integer>,Integer>,
				HashMap<Pair<String,Integer>,Integer>,HashMap<Integer,Pair<String,Integer>>> items = wm.getWorkplaceObjects(infors);
		
//		items.getValue2().forEach((key,value) -> System.out.println(key.getWorkplace_name()+":"+value));
		

		
	};
}
