package exportbill;

import java.sql.ResultSet;
import java.util.*;

import org.javatuples.Quartet;

import connection.ShareControl;
import objects.BillObject;
import objects.ExportBillObject;

public interface ExportBill extends ShareControl{
	public boolean addExportBill(ExportBillObject item);
	public boolean editExportBill(ExportBillObject item);
	public boolean delExportBill(ExportBillObject item);
	
	public ResultSet getExportBill(int id);
	public ArrayList<ResultSet> getExportBill(Quartet<ExportBillObject, Integer, Short, BILL_SORT_TYPE> infors);
}
