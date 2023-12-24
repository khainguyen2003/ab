package library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.javatuples.Pair;

import objects.*;

public class Utilities_data_type {

	public static ArrayList<Object> getArrayListObject(ArrayList<UserObject> datas, UserObject filter){
		ArrayList<Object> convertData = new ArrayList<Object>();
		
		Iterator<UserObject> iterator = datas.iterator();
		while (iterator.hasNext()) {
			UserObject cursor = iterator.next();
			
			if (filter.getUser_name()!=null) {
				cursor.setUser_name(null);
			}
			if (filter.getUser_pass()!=null) {
				cursor.setUser_pass(null);
			}
			if (filter.getUser_nickname()!=null) {
				cursor.setUser_nickname(null);
			}
			if (filter.getUser_fullname()!=null) {
				cursor.setUser_fullname(null);
			}
			if (filter.getUser_images()!=null) {
				cursor.setUser_images(null);
			}
			if (filter.getUser_email()!=null) {
				cursor.setUser_email(null);
			}
			if (filter.getUser_notes()!=null) {
				cursor.setUser_notes(null);
			}
			if (filter.getUser_permission()!=(byte)0) {
				cursor.setUser_permission((byte) (0));
			}
			if (filter.getUser_last_modified_id()!=0) {				
				cursor.setUser_last_modified_id(0);
			}
			if (filter.getUser_last_modified_date()!=null) {
				cursor.setUser_last_modified_date(null);
			}
			if (filter.getUser_gender()!=0) {
				cursor.setUser_gender((byte) 0);
			}
			if (filter.getUser_address()!= null) {
				cursor.setUser_address(null);
			}			
			if (filter.getUser_created_date()!= null) {
				cursor.setUser_created_date(null);
			}					
			if (filter.isUser_deleted()!=false) {
				cursor.setUser_deleted(false);
			}
			

//			uitem.setUser_mobile_phone(row.getCell(15).getStringCellValue());
//			uitem.setUser_office_phone(row.getCell(16).getStringCellValue());
//			uitem.setUser_social_links(row.getCell(17).getStringCellValue());
//			uitem.setUser_parent_id((int) row.getCell(18).getNumericCellValue());
//			uitem.setUser_logined((int) row.getCell(19).getNumericCellValue());

			
			convertData.add(cursor);
		}
		
		return convertData;
	}
	
	public static Pair<ArrayList<Object>,Integer> getPair(Pair<ArrayList<UserObject>,Integer> datas, UserObject filter){
		 ArrayList<Object> convertData = library.Utilities_data_type.getArrayListObject(datas.getValue0(), filter);   	
	     return new Pair<>(convertData,datas.getValue1());
	}
	
	public static ArrayList<Object> getArrayListObject(ArrayList<EmployeeObject> datas, EmployeeObject filter){
	ArrayList<Object> convertData = new ArrayList<Object>();
		
		Iterator<EmployeeObject> iterator = datas.iterator();
		
		if (	filter.getUser_name()!=null || 
				filter.getUser_fullname()!=null) {
		
			
			while (iterator.hasNext()) {
				if (	iterator.next().getUser_name().contains(filter.getUser_name()) || 
						iterator.next().getUser_name().contains(filter.getUser_fullname())
						) {
					convertData.add(iterator.next());
				}
			}
			
			return convertData;
			
		} else {
			 if (convertData.addAll(datas)) {
				 return convertData;
			 } else {
				 return null;
			 }
		}			
	}
	
	public static Pair<ArrayList<Object>,Integer> getPair(Pair<ArrayList<EmployeeObject>,Integer> datas, EmployeeObject filter){
		 ArrayList<Object> convertData = library.Utilities_data_type.getArrayListObject(datas.getValue0(), filter);   	
	     return new Pair<>(convertData,datas.getValue1());
	}
	
	public static ArrayList<Object> getArrayListObject(ArrayList<BillObject> datas, BillObject filter){
		ArrayList<Object> convertData = new ArrayList<Object>();
		if (filter == null) {
			 if (convertData.addAll(datas)) {
				 return convertData;
			 } else {
				 return null;
			 }
		} else {			
			return null;
		}
	}
}
