package workplace;

import java.util.ArrayList;
import java.util.HashMap;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Septet;
import org.javatuples.Sextet;
import org.javatuples.Triplet;

import connection.ConnectionPool;
import objects.EmployeeObject;
import objects.ProductObject;
import objects.WorkplaceObject;
import objects.WorkplaceStorageDetail;

public class WorkplaceControl {
	private WorkplaceModel wm;
	
	public WorkplaceControl(ConnectionPool cp) {
		this.wm = new WorkplaceModel(cp);
		
	}
	
	public ConnectionPool getCP() {
		return this.wm.getCP();
	}
	
	public void releaseConnection() {
		this.wm.releaseConnection();
	}

	
	public boolean addWorkplace(ArrayList<WorkplaceObject> wItem, ArrayList<WorkplaceStorageDetail> pItem) {
		return this.wm.addWorkplace(wItem, pItem);
	}
	
	public boolean editWorkplace(ArrayList<WorkplaceObject> wItem, ArrayList<WorkplaceStorageDetail> pItem, WORKPLACE_EDIT_TYPE et) {
		return this.wm.editWorkplace(wItem, pItem, et);
	}
	
	public boolean delWorkplace(ArrayList<WorkplaceObject> item) {
		return this.wm.delWorkplace(item);
	}
	

	public WorkplaceObject getWorkplaceObject(int id) {
		return this.wm.getWorkplaceObject(id);
	}
	
	public Septet<	ArrayList<WorkplaceObject>,
					Integer, 
					HashMap<Integer,Integer>, 
					HashMap<Integer,Integer>, 
					HashMap<Pair<String,Integer>,Integer> ,
					HashMap<Pair<String,Integer>,Integer>,
					HashMap<Integer, Pair<String,Integer>>> 
		getWorkplaceObjects(
			Sextet<EmployeeObject, 
			WorkplaceObject, 
			Short, 
			Byte ,
			WORKPLACE_SORT_TYPE,
			Boolean> infors){
				
		return this.wm.getWorkplaceObjects(infors);
	}
	

	public ArrayList<String> viewWorkplacesList(Sextet<EmployeeObject, 
			WorkplaceObject, 
			Short, 
			Byte ,
			WORKPLACE_SORT_TYPE,
			Boolean> infors, byte isOpenModal , String url){
		
		Septet<	ArrayList<WorkplaceObject>,
		Integer, 
		HashMap<Integer,Integer>, 
		HashMap<Integer,Integer>, 
		HashMap<Pair<String,Integer>,Integer> ,
		HashMap<Pair<String,Integer>,Integer>,
		HashMap<Integer, Pair<String,Integer>>> datas = this.wm.getWorkplaceObjects(infors);
		
		return WorkplaceLibrary.viewWorkplaceList(datas, infors, isOpenModal, url);
	}
	
	
	
//	public static void main(String[] args) {
//		WorkplaceControl uc = new WorkplaceControl(null);
//		
//		Quartet<WorkplaceObject, Short, Byte, WORKPLACE_SORT_TYPE> infors = new Quartet<>(null, (short) 1, (byte) 10, WORKPLACE_SORT_TYPE.NAME);
//		
//		ArrayList<String> view = uc.viewWorkplacesList(infors);
//		
//		uc.releaseConnection();//Tra ve ket noi
//		
//		System.out.println(view);
//	}
}
