package importbill;

import java.sql.ResultSet;
import java.util.*;

import org.javatuples.Quartet;

import connection.ShareControl;
import objects.BillObject;

public interface Bill extends ShareControl{
	public boolean addBill(BillObject item);
	public boolean editBill(BillObject item);
	public boolean delBill(BillObject item);
	
	public ResultSet getBill(int id);
	public ArrayList<ResultSet> getBill(Quartet<BillObject, Integer, Short, BILL_SORT_TYPE> infors);
}
