package user;

import connection.*;
import objects.*;
import java.util.*;
import org.javatuples.*;

public class UserControl {
	private UserModel um;
	
	public UserControl(ConnectionPool cp) {
		this.um = new UserModel(cp);
		
	}
	
	public ConnectionPool getCP() {
		return this.um.getCP();
	}
	
	public void releaseConnection() {
		this.um.releaseConnection();
	}

	
	public boolean addUser(UserObject item) {
		return this.um.addUser(item);
	}
	
	public boolean editUser(UserObject item, USER_EDIT_TYPE et) {
		return this.um.editUser(item, et);
	}
	
	public boolean delUser(UserObject item) {
		return this.um.delUser(item);
	}
	
	//PhÆ°Æ¡ng thá»©c quáº£n trá»‹ ngÆ°á»�i dÃ¹ng
	public UserObject getUserObject(int id) {
		return this.um.getUserObject(id);
	}
	
	public UserObject getUserObject(String username, String userpass) {
		return this.um.getUserObject(username,userpass);
	}
	

	//PhÆ°Æ¡ng thá»©c láº¥y dá»¯ liá»‡u ngÆ°á»�i dÃ¹ng
	public Pair<ArrayList<UserObject>,Integer> getUserObjects(Quintet<UserObject, Short, Byte, USER_SORT_TYPE, Boolean> infors) {
		//Lay du lieu
		UserObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte total = infors.getValue2();
		USER_SORT_TYPE ust = infors.getValue3();
		
		boolean isExport = infors.getValue4();
				
		return this.um.getUserObjects(similar, page, total, ust, isExport);
	}
	
	//PhÆ°Æ¡ng thá»©c xuáº¥t chuá»—i
	public ArrayList<String> viewUsersList(Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors){
		//Lay du lieu
		UserObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte uPerPage = infors.getValue2();
		USER_SORT_TYPE ust = infors.getValue3();
		
		Pair<ArrayList<UserObject>,Integer> datas = this.um.getUserObjects(similar, page, uPerPage, ust, false);
		
		return UserLibrary.viewUserList(datas, infors);
	}
	
	
	
	public static void main(String[] args) {
		UserControl uc = new UserControl(null);
		
		Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors = new Quartet<>(null, (short) 1, (byte) 10, USER_SORT_TYPE.NAME);
		
		ArrayList<String> view = uc.viewUsersList(infors);
		
		uc.releaseConnection();//Tra ve ket noi
		
		System.out.println(view);
	}
}
