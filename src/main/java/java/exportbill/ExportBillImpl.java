package exportbill;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import basic.BasicImpl;
import bill.BillImpl;
import connection.ConnectionPool;
import objects.BillObject;
import objects.ExportBillObject;

public class ExportBillImpl extends BillImpl implements ExportBill {

	public ExportBillImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addExportBill(ExportBillObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editExportBill(ExportBillObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delExportBill(ExportBillObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getExportBill(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultSet> getExportBill(Quartet<ExportBillObject, Integer, Short, BILL_SORT_TYPE> infors) {
		// TODO Auto-generated method stub
		return null;
	}

}
