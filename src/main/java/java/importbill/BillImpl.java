package importbill;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.javatuples.Quartet;

import basic.BasicImpl;
import connection.ConnectionPool;
import objects.BillObject;

public class BillImpl extends BasicImpl implements Bill {

	public BillImpl(ConnectionPool cp) {
		super(cp, "Bill");
		// TODO Auto-generated constructor stub
	}
	
	public BillImpl(ConnectionPool cp, String objectName) {
		super(cp, objectName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addBill(BillObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editBill(BillObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delBill(BillObject item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getBill(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ResultSet> getBill(Quartet<BillObject, Integer, Short, BILL_SORT_TYPE> infors) {
		// TODO Auto-generated method stub
		return null;
	}

}
