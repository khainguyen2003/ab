package workplace;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Sextet;

import connection.ShareControl;
import objects.*;

public interface Workplace extends ShareControl{
	//Các phương thức, chức năng cập nhật thông tin nơi làm việc
	public boolean addWorkplace(ArrayList<WorkplaceObject> wItem, ArrayList<WorkplaceStorageDetail> pItem);
	public boolean editWorkplace(ArrayList<WorkplaceObject> wItem, ArrayList<WorkplaceStorageDetail> pItem, WORKPLACE_EDIT_TYPE et);
	public boolean delWorkplace(ArrayList<WorkplaceObject> item);
	
	//Các phương thức, chức năng lấy thông tin nơi làm việc
	public ResultSet getWorkplace(int id);
	public ArrayList<ResultSet> getWorkplaces(Sextet<EmployeeObject, 
			WorkplaceObject, 
			Integer, 
			Byte ,
			WORKPLACE_SORT_TYPE,
			Boolean> infors);
}
