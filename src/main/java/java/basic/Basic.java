package basic;

import java.sql.*;
import java.util.*;
import connection.*;

//Thua ke tu ShareControl, thay doi ShareControl o BasicImpl
public interface Basic extends ShareControl{
	
	//PreparedStatement pre - da duoc bien dich, da truyen gia tri
	public boolean add(PreparedStatement pre);
	public boolean edit(PreparedStatement pre);
	public boolean del(PreparedStatement pre);
	
	public ResultSet get(String sql, int id);
	public ResultSet get(ArrayList<String> sql, String name, String pass);
	public ResultSet gets(String sql);
	
	public ArrayList<ResultSet> getReList(String multiSelect);
	
	public boolean addListV1(PreparedStatement pre, int add_number);
	
	public boolean addList(PreparedStatement pre);	
	public boolean editList(PreparedStatement pre);	
	public boolean delList(PreparedStatement pre);	

}
