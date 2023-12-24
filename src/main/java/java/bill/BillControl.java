package bill;

import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Quintet;
import org.javatuples.Sextet;
import org.javatuples.Triplet;

import connection.ConnectionPool;
import objects.EmployeeObject;
import objects.BillObject;

public class BillControl {
	private BillModel bm;
	
	public BillControl(ConnectionPool cp) {
		this.bm = new BillModel(cp);
		
	}
	
	public ConnectionPool getCP() {
		return this.bm.getCP();
	}
	
	public void releaseConnection() {
		this.bm.releaseConnection();
	}

	
	public boolean addBill(BillObject item) {
		return this.bm.addBill(item);
	}
	
	public boolean editBill(BillObject item, BILL_EDIT_TYPE et) {
		return this.bm.editBill(item, et);
	}
	
	public boolean delBill(BillObject item) {
		return this.bm.delBill(item);
	}
	

	public BillObject getBillObject(int id) {
		return this.bm.getBillObject(id);
	}
	
	public Quintet<ArrayList<BillObject>, Integer, HashMap<String,Integer>, HashMap<String,Integer>,ArrayList<String>> getBillObjects(Sextet<EmployeeObject, 
			BillObject, 
			Short, 
			Byte ,
			BILL_SORT_TYPE,
			Boolean> infors){
				
		return this.bm.getBillObjects(infors);
	}
	

	public ArrayList<String> viewBillsList(Sextet<EmployeeObject, 
			BillObject, 
			Short, 
			Byte ,
			BILL_SORT_TYPE,
			Boolean> infors){
		
		Quintet<ArrayList<BillObject>, Integer, HashMap<String,Integer>, HashMap<String,Integer>, ArrayList<String>> datas = this.bm.getBillObjects(infors);
		
		return BillLibrary.viewBillList(datas, infors);
	}
	
	
	public ArrayList<String> viewMain(Sextet<EmployeeObject, 
			BillObject, 
			Short, 
			Byte ,
			BILL_SORT_TYPE,
			Boolean> infors){
		
		Quintet<ArrayList<BillObject>, Integer, HashMap<String,Integer>, HashMap<String,Integer>, ArrayList<String>> datas = this.bm.getBillObjects(infors);
		
		return BillLibrary.viewMain(datas);
	}
	
	
	
//	public static void main(String[] args) {
//		BillControl uc = new BillControl(null);
//		
//		Quartet<BillObject, Short, Byte, BILL_SORT_TYPE> infors = new Quartet<>(null, (short) 1, (byte) 10, BILL_SORT_TYPE.NAME);
//		
//		ArrayList<String> view = uc.viewBillsList(infors);
//		
//		uc.releaseConnection();//Tra ve ket noi
//		
//		System.out.println(view);
//	}
}
