package user;

import java.sql.*;
import java.util.*;

import connection.ShareControl;
import objects.*;

//extends ShareControl thay vi extend Basic de gioi han luong thong tin can import
public interface User extends ShareControl{
	//Cac phuong thuc/ chuc nang cap nhat thong tin Nguoi su dung
	public boolean addUser(UserObject item);
	public boolean editUser(UserObject item, USER_EDIT_TYPE et);
	public boolean delUser(UserObject item);
	
	//Cac phuong thuc/ chuc nang lay thong tin Nguoi su dung
	public ResultSet getUser(int id);
	public ResultSet getUser(String username, String userpass);
	public ArrayList<ResultSet> getUsers(UserObject similar, int at, byte total, USER_SORT_TYPE type);
	
}