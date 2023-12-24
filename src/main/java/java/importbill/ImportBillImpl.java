package importbill;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import basic.BasicImpl;
import bill.BillImpl;
import connection.ConnectionPool;
import objects.ImportBillObject;

public class ImportBillImpl extends BillImpl implements ImportBill {


	public ImportBillImpl(ConnectionPool cp) {
		super(cp, "ImportBill");
		// TODO Auto-generated constructor stub
	}
	
	public ImportBillImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addImportBill(ImportBillObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editImportBill(ImportBillObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delImportBill(ImportBillObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getImportBill(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultSet> getImportBill(Quartet<ImportBillObject, Integer, Short, BILL_SORT_TYPE> infors) {
		// TODO Auto-generated method stub
		return null;
	}

}
