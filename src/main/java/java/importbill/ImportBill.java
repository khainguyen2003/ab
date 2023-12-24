package importbill;

import java.sql.ResultSet;
import java.util.*;

import org.javatuples.Quartet;

import connection.ShareControl;
import objects.ImportBillObject;

public interface ImportBill extends ShareControl{
	public boolean addImportBill(ImportBillObject item);
	public boolean editImportBill(ImportBillObject item);
	public boolean delImportBill(ImportBillObject item);
	
	public ResultSet getImportBill(int id);
	public ArrayList<ResultSet> getImportBill(Quartet<ImportBillObject, Integer, Short, BILL_SORT_TYPE> infors);
}
