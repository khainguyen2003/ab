package library;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;

import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javatuples.Pair;

import objects.*;

public class Utilities_file extends HttpServlet {


	public static ArrayList<Object> readExcelFile(InputStream input, String type) {	
		ArrayList<Object> data = new ArrayList<Object>();
		
		
		// Try block to check for exceptions
		try {

			try (// Create Workbook instance holding reference to
						// .xlsx file
			HSSFWorkbook workbook = new HSSFWorkbook(input)) {
				// Get first/desired sheet from the workbook
				HSSFSheet sheet = workbook.getSheetAt(0);
				sheet.removeRow(sheet.getRow(0));
				
				// Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();

				// Till there is an element condition holds true
				while (rowIterator.hasNext()) {
					
					Row row = rowIterator.next();	

//					Iterator<Cell> cellIterator = row.cellIterator();

					switch (type) {
						case "user":
							UserObject uitem = new UserObject();													
							if (row.getCell(1)!=null) {
								uitem.setUser_name(row.getCell(1).getStringCellValue().trim());
							}
							if (row.getCell(2)!=null) {
								uitem.setUser_pass(row.getCell(2).getStringCellValue().trim());
							}
							if (row.getCell(3)!=null) {
								uitem.setUser_nickname(row.getCell(3).getStringCellValue().trim());
							}
							if (row.getCell(4)!=null) {
								uitem.setUser_fullname(row.getCell(4).getStringCellValue().trim());
							}
							if (row.getCell(5)!=null) {
								uitem.setUser_images(row.getCell(5).getStringCellValue().trim());
							}
							if (row.getCell(6)!=null) {
								uitem.setUser_email(row.getCell(6).getStringCellValue().trim());
							}
							if (row.getCell(7)!=null) {
								uitem.setUser_notes(row.getCell(7).getStringCellValue().trim());
							}
							if (row.getCell(8)!=null) {
								uitem.setUser_permission((byte)row.getCell(8).getNumericCellValue());
							}
							if (row.getCell(9)!=null) {
								uitem.setUser_last_modified_id((int)row.getCell(9).getNumericCellValue());
							}						
							if (row.getCell(10)!=null) {
								uitem.setUser_last_modified_date(Utilities_date.getDate(row.getCell(10).getNumericCellValue()));
							}
							if (row.getCell(11)!=null) {
								uitem.setUser_gender((byte) row.getCell(11).getNumericCellValue());
							}							
							if (row.getCell(12)!=null) {
								uitem.setUser_address(row.getCell(12).getStringCellValue().trim());
							}
							if (row.getCell(13)!=null) {							
								uitem.setUser_created_date(Utilities_date.getDate(row.getCell(13).getNumericCellValue()));
							}		
							if (row.getCell(14)!=null) {
								uitem.setUser_deleted((boolean) row.getCell(14).getBooleanCellValue());
							}	
							if (row.getCell(15)!=null) {
								uitem.setUser_mobile_phone(row.getCell(15).getStringCellValue().trim());
							}		
							if (row.getCell(16)!=null) {
								uitem.setUser_office_phone(row.getCell(16).getStringCellValue().trim());
							}
							if (row.getCell(17)!=null) {
								uitem.setUser_social_links(row.getCell(17).getStringCellValue().trim());
							}
							if (row.getCell(18)!=null) {
								uitem.setUser_parent_id((int) row.getCell(18).getNumericCellValue());
							}
							if (row.getCell(19)!=null) {
								uitem.setUser_logined((int) row.getCell(19).getNumericCellValue());
							}
							data.add(uitem);
							
							System.out.print(data);
							break;
							
						case "employee":
							EmployeeObject eitem = new EmployeeObject();
							
							if (row.getCell(1)!=null) {
								eitem.setUser_name(row.getCell(1).getStringCellValue());
							}
							if (row.getCell(2)!=null) {
								eitem.setUser_pass(row.getCell(2).getStringCellValue());
							}
							if (row.getCell(3)!=null) {
								eitem.setUser_nickname(row.getCell(3).getStringCellValue());
							}
							if (row.getCell(4)!=null) {
								eitem.setUser_fullname(row.getCell(4).getStringCellValue());
							}
							if (row.getCell(5)!=null) {
								eitem.setUser_images(row.getCell(5).getStringCellValue());
							}
							if (row.getCell(6)!=null) {
								eitem.setUser_email(row.getCell(6).getStringCellValue());
							}
							if (row.getCell(7)!=null) {
								eitem.setUser_notes(row.getCell(7).getStringCellValue());
							}
							if (row.getCell(8)!=null) {
								eitem.setUser_permission((byte)row.getCell(8).getNumericCellValue());
							}
							if (row.getCell(9)!=null) {
								eitem.setUser_last_modified_id((int)row.getCell(9).getNumericCellValue());
							}						
							if (row.getCell(10)!=null) {
								eitem.setUser_last_modified_date(row.getCell(10).getDateCellValue().toString());
							}
							if (row.getCell(11)!=null) {
								eitem.setUser_gender((byte) row.getCell(11).getNumericCellValue());
							}							
							if (row.getCell(12)!=null) {
								eitem.setUser_address(row.getCell(12).getStringCellValue());
							}
							if (row.getCell(13)!=null) {
								eitem.setUser_created_date(row.getCell(13).getStringCellValue());
							}		
							if (row.getCell(14)!=null) {
								eitem.setUser_deleted((boolean) row.getCell(14).getBooleanCellValue());
							}	
							if (row.getCell(15)!=null) {
								eitem.setUser_mobile_phone(row.getCell(15).getStringCellValue());
							}		
							if (row.getCell(16)!=null) {
								eitem.setUser_office_phone(row.getCell(16).getStringCellValue());
							}
							if (row.getCell(17)!=null) {
								eitem.setUser_social_links(row.getCell(17).getStringCellValue());
							}
							if (row.getCell(18)!=null) {
								eitem.setUser_parent_id((int) row.getCell(18).getNumericCellValue());
							}
							if (row.getCell(19)!=null) {
								eitem.setUser_logined((int) row.getCell(19).getNumericCellValue());
							}
							eitem.setEmployee_role((byte)row.getCell(20).getNumericCellValue());
							if (row.getCell(21)!=null) {
								eitem.setEmployee_contract_expired_date(row.getCell(21).getStringCellValue());
							}
							if (row.getCell(22)!=null) {
								eitem.setEmployee_status(row.getCell(22).getBooleanCellValue());
							}
							if (row.getCell(23)!=null) {
								eitem.setEmployee_work_time_length((byte) row.getCell(23).getNumericCellValue());
							}						
							if (row.getCell(24)!=null) {
								eitem.setEmployee_workplace_id((int) row.getCell(24).getNumericCellValue());
							}						
							if (row.getCell(25)!=null) {
								eitem.setEmployee_work_finished_day((int) row.getCell(25).getNumericCellValue());;
							}
							data.add(eitem);
							break;
						case "product":
							ProductObject pitem = new ProductObject();
							
							data.add(pitem);
							break;
					}							
				}
				// Closing file output streams
				workbook.close();
				
				return data;
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	public static boolean writeUserExcelFile(OutputStream os, ArrayList<UserObject> datas){        
        
		try (// Blank workbook
		HSSFWorkbook workbook = new HSSFWorkbook();	
				) {
			// Creating a blank Excel sheet
			HSSFSheet sheet
				= workbook.createSheet("User Information");
			
			
			List<String> propertiesName = Arrays.asList(					
					"ID", "Tài khoản" , "Mật khẩu" , "Bí danh", "Tên đầy đủ", "Đường dẫn ảnh",
					"Email", "Ghi chú", "Quyền thực thi",
					"Người chỉnh sửa lần cuối", "Ngày chỉnh sửa lần cuối", "Giới tính",
					"Địa chỉ", "Ngày khởi tạo", "Trạng thái xóa", "Số điện thoại di động",
					"Số điện thoại công ty", "Đường dẫn liên hệ", "Người tạo", "Số lần đăng nhập","Vai trò"
				);
			
//			List<Integer> cellWidth = Arrays.asList(					
//					20, 50, 40, 30, 70, 90,
//					100, 50, 20,
//					20, 20, 20,
//					100, 20, 20, 40,
//					40, 100, 20, 30, 40
//			);
			
			
			//Xuất tên cột
			Row firstRow = sheet.createRow(0);
			propertiesName.forEach(name->{
				int index = propertiesName.indexOf(name);
//				sheet.setColumnWidth(index, (int) (cellWidth.indexOf(index)*256));	
				firstRow.createCell(index).setCellValue(name);
							
			});
			System.out.println(propertiesName);
			
			//Xuất danh sách người dùng
			datas.forEach(user->{
				
				Row row = sheet.createRow((int) datas.indexOf(user)+1);	
				
				List<Object> propertiesValue = Arrays.asList(					
						(Integer) user.getUser_id(), user.getUser_name(), user.getUser_pass(),
						user.getUser_nickname(), user.getUser_fullname(),user.getUser_images(),
						user.getUser_notes(), user.getUser_permission(), 
						(Integer) user.getUser_last_modified_id(), user.getUser_last_modified_date(), 
						(Byte) user.getUser_gender(), user.getUser_address(),
						user.getUser_created_date(), (Boolean)user.isUser_deleted(), user.getUser_mobile_phone(), 
						user.getUser_office_phone(), user.getUser_social_links(), (Integer)user.getUser_logined(), (Integer) 1
				);
				System.out.println(propertiesValue);
//				System.out.println(propertiesValue);
				
				propertiesValue.forEach(property->{
					
					if (property!=null) {
						
						switch (property.getClass().getName()) {
						case "java.lang.String":
							row.createCell(propertiesValue.indexOf(property)).setCellValue((String)property);
							break;
							
						case "java.lang.Integer":
							row.createCell(propertiesValue.indexOf(property)).setCellValue((int)property);
							break;
							
						case "java.lang.Boolean":
							row.createCell(propertiesValue.indexOf(property)).setCellValue((boolean)property);
							break;
							
						case "java.lang.Byte":
							row.createCell(propertiesValue.indexOf(property)).setCellValue((byte)property);
							break;
						} 
					} else {
						row.createCell(propertiesValue.indexOf(property));
					}
				});
				System.out.println();
	
			});

			// Try block to check for exceptions
			try {
				// Closing file output connections
				workbook.write(os);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeEmployeeExcelFile(OutputStream os, ArrayList<EmployeeObject> datas, EmployeeObject filter){        
        
		try (// Blank workbook
		HSSFWorkbook workbook = new HSSFWorkbook();	
				) {
			// Creating a blank Excel sheet
			HSSFSheet sheet
				= workbook.createSheet("User Information");	

			List<String> propertiesName = Arrays.asList(					
					"ID","Tài khoản","Mật khẩu", "Bí danh", "Tên đầy đủ", "Đường dẫn ảnh",
					"Email", "Ghi chú", "Quyền thực thi",
					"Người chỉnh sửa lần cuối", "Ngày chỉnh sửa lần cuối", "Giới tính",
					"Địa chỉ", "Ngày khởi tạo", "Trạng thái xóa", "Số điện thoại di động",
					"Số điện thoại công ty", "Đường dẫn liên hệ", "Người tạo", "Số lần đăng nhập","Vai trò",
					"Ngày hết hạn hợp đồng","Trạng thái nhân viên","Thời gian làm việc trong 1 ngày",
					"Nơi làm việc", "Số ngày hoàn thành công việc"
				);
			
			Row firstRow = sheet.createRow(0);
			propertiesName.forEach(name->{
				firstRow.createCell(propertiesName.indexOf(name)).setCellValue(name);
			});
			
			datas.forEach(employee->{
				
				Row row = sheet.createRow((int) datas.indexOf(employee)+1);	
				
				List<Object> propertiesValue = Arrays.asList(					
						(Integer) employee.getEmployee_id(), employee.getUser_name(), employee.getUser_pass(),
						employee.getUser_nickname(), employee.getUser_fullname(),employee.getUser_images(),
						employee.getUser_notes(), employee.getUser_permission(), 
						(Integer) employee.getUser_last_modified_id(), employee.getUser_last_modified_date(), 
						(Byte) employee.getUser_gender(), employee.getUser_address(),
						employee.getUser_created_date(), (Boolean)employee.isUser_deleted(), employee.getUser_mobile_phone(), 
						employee.getUser_office_phone(), employee.getUser_social_links(), (Integer)employee.getUser_logined(),
						(Byte) employee.getEmployee_role(), employee.getEmployee_contract_expired_date(),
						(Boolean) employee.isEmployee_status(), (Byte) employee.getEmployee_work_time_length(),
						(Integer) employee.getEmployee_workplace_id(),(Integer) employee.getEmployee_work_finished_day()
						
						
				);
				System.out.println(propertiesValue);
//				System.out.println(propertiesValue);
				
				propertiesValue.forEach(property->{
					
					if (property!=null) {
						
						switch (property.getClass().getName()) {
						case "java.lang.String":
							row.createCell(propertiesValue.indexOf((String)property)).setCellValue((String)property);
							break;
							
						case "java.lang.Integer":
							row.createCell(propertiesValue.indexOf((Integer)property)).setCellValue((int)property);
							break;
							
						case "java.lang.Boolean":
							row.createCell(propertiesValue.indexOf((Boolean)property)).setCellValue((boolean)property);
							break;
							
						case "java.lang.Byte":
							row.createCell(propertiesValue.indexOf((Byte)property)).setCellValue((byte)property);
							break;
						} 
					} else {
						row.createCell(propertiesValue.indexOf(property));
					}
				});
				System.out.println();
			});
			
			// Try block to check for exceptions
			try {
				// Closing file output connections
				workbook.write(os);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	public static boolean writeExcelFile(OutputStream os, ArrayList<Object> datas){
        
		try (// Blank workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
				
				) {
			// Creating a blank Excel sheet
			HSSFSheet sheet
				= workbook.createSheet("User Information");
			
			Row firstRow = sheet.createRow(0);
			datas.forEach(object ->{
				Row row = sheet.createRow((int) datas.indexOf(object)+1);
				switch (object.getClass().getName()) {
					case "objects.EmployeeObject":
						EmployeeObject employee = (EmployeeObject) object;	
				
						List<String> propertiesName = Arrays.asList(					
								"ID","Tài khoản","Mật khẩu", "Bí danh", "Tên đầy đủ", "Đường dẫn ảnh",
								"Email", "Ghi chú", "Quyền thực thi",
								"Người chỉnh sửa lần cuối", "Ngày chỉnh sửa lần cuối", "Giới tính",
								"Địa chỉ", "Ngày khởi tạo", "Trạng thái xóa", "Số điện thoại di động",
								"Số điện thoại công ty", "Đường dẫn liên hệ", "Người tạo", "Số lần đăng nhập","Vai trò",
								"Ngày hết hạn hợp đồng","Trạng thái nhân viên","Thời gian làm việc trong 1 ngày",
								"Nơi làm việc", "Số ngày hoàn thành công việc"
							);
						
						propertiesName.forEach(name->{
							firstRow.createCell(propertiesName.indexOf(name)).setCellValue(name);
						});
						

							
					List<Object> propertiesValue = Arrays.asList(					
							(Integer) employee.getEmployee_id(), employee.getUser_name(), employee.getUser_pass(),
							employee.getUser_nickname(), employee.getUser_fullname(),employee.getUser_images(),
							employee.getUser_notes(), employee.getUser_permission(), 
							(Integer) employee.getUser_last_modified_id(), employee.getUser_last_modified_date(), 
							(Byte) employee.getUser_gender(), employee.getUser_address(),
							employee.getUser_created_date(), (Boolean)employee.isUser_deleted(), employee.getUser_mobile_phone(), 
							employee.getUser_office_phone(), employee.getUser_social_links(), (Integer)employee.getUser_logined(),
							(Byte) employee.getEmployee_role(), employee.getEmployee_contract_expired_date(),
							(Boolean) employee.isEmployee_status(), (Byte) employee.getEmployee_work_time_length(),
							(Integer) employee.getEmployee_workplace_id(),(Integer) employee.getEmployee_work_finished_day()
							
							
					);
					System.out.println(propertiesValue);
//							System.out.println(propertiesValue);
					
					propertiesValue.forEach(property->{
						
						if (property!=null) {
							
							switch (property.getClass().getName()) {
							case "java.lang.String":
								row.createCell(propertiesValue.indexOf((String)property)).setCellValue((String)property);
								break;
								
							case "java.lang.Integer":
								row.createCell(propertiesValue.indexOf((Integer)property)).setCellValue((int)property);
								break;
								
							case "java.lang.Boolean":
								row.createCell(propertiesValue.indexOf((Boolean)property)).setCellValue((boolean)property);
								break;
								
							case "java.lang.Byte":
								row.createCell(propertiesValue.indexOf((Byte)property)).setCellValue((byte)property);
								break;
							} 
						} else {
							row.createCell(propertiesValue.indexOf(property));
						}
					});
					System.out.println();

					break;
					
					case "objects.UserObject":
						firstRow.createCell(0).setCellValue("ID");
						firstRow.createCell(1).setCellValue("Tài khoản");
						firstRow.createCell(2).setCellValue("Mật khẩu");
						firstRow.createCell(3).setCellValue("Bí danh");
						firstRow.createCell(4).setCellValue("Tên đầy đủ");
						firstRow.createCell(5).setCellValue("Đường dẫn ảnh");
						firstRow.createCell(6).setCellValue("Emal");
						firstRow.createCell(7).setCellValue("Ghi chú");
						firstRow.createCell(8).setCellValue("Quyền thực thi");
						firstRow.createCell(9).setCellValue("Người chỉnh sửa lần cuối");
						firstRow.createCell(10).setCellValue("Ngày chỉnh sửa lần cuối");
						firstRow.createCell(11).setCellValue("Giới tính");
						firstRow.createCell(12).setCellValue("Địa chỉ");
						firstRow.createCell(13).setCellValue("Ngày khởi tạo");
						firstRow.createCell(14).setCellValue("Trạng thái xóa");
						firstRow.createCell(15).setCellValue("Số điện thoại di động");
						firstRow.createCell(16).setCellValue("Số điện thoại công ty");
						firstRow.createCell(17).setCellValue("Đường dẫn liên hệ");
						firstRow.createCell(18).setCellValue("Người tạo");
						firstRow.createCell(19).setCellValue("Số lần đăng nhập");
//						row.createCell(20).setCellValue("Khách hàng");
						firstRow.createCell(20).setCellValue("Vai trò");
						
						
						UserObject u = (UserObject) object;												
						row.createCell(0).setCellValue(u.getUser_id());
						row.createCell(1).setCellValue(u.getUser_name());
						row.createCell(2).setCellValue(u.getUser_pass());
						row.createCell(3).setCellValue(u.getUser_nickname());
						row.createCell(4).setCellValue(u.getUser_fullname());
						row.createCell(5).setCellValue(u.getUser_images());
						row.createCell(6).setCellValue(u.getUser_email());
						row.createCell(7).setCellValue(u.getUser_notes() );
						row.createCell(8).setCellValue(u.getUser_permission());
						row.createCell(9).setCellValue(u.getUser_last_modified_id());
						row.createCell(10).setCellValue(u.getUser_last_modified_date());
						row.createCell(11).setCellValue(u.getUser_gender());
						row.createCell(12).setCellValue(u.getUser_address());
						row.createCell(13).setCellValue(u.getUser_created_date());
						row.createCell(14).setCellValue(u.isUser_deleted() );
						row.createCell(15).setCellValue(u.getUser_mobile_phone());
						row.createCell(16).setCellValue(u.getUser_office_phone());
						row.createCell(17).setCellValue(u.getUser_social_links());
						row.createCell(18).setCellValue(u.getUser_parent_id());
						row.createCell(19).setCellValue(u.getUser_logined());
//						row.createCell(20).setCellValue("Khách hàng");
						row.createCell(20).setCellValue(0);
					break;
				}								
			});

			// Try block to check for exceptions
			try {
				// Closing file output connections
				workbook.write(os);
				return true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean writeProductExcel(OutputStream os, ArrayList<ProductObject> datas) {
		try (// Blank workbook
			XSSFWorkbook workbook = new XSSFWorkbook();	
					) {
				// Creating a blank Excel sheet
				XSSFSheet sheet
					= workbook.createSheet("productDetails");	
	
				List<String> propertiesName = Arrays.asList(					
						"ID","Tên sản phẩm","Thể loại", "Trạng thái", "deleted", "Giá",
						"Ảnh", "Bảo hành", "Ghi chú", "Ngày chỉnh sửa lần cuối"
					);
				
				Row firstRow = sheet.createRow(0);
				Iterator<String> iCellName = propertiesName.iterator();
				int idx = 0;
				while(iCellName.hasNext()) {
					firstRow.createCell(idx++).setCellValue(iCellName.next());
				}
//				propertiesName.forEach(name->{
//					firstRow.createCell(propertiesName.indexOf(name)).setCellValue(name);
//				});
				idx = 0;
				Iterator<ProductObject> iCellVal = datas.iterator();
				while(iCellVal.hasNext()) {
					ProductObject product = iCellVal.next();
					Row row = sheet.createRow((int) datas.indexOf(product)+1);	
					
					
					row.createCell(0).setCellValue(product.getProduct_id());
					row.createCell(1).setCellValue(product.getProduct_name());
					row.createCell(2).setCellValue(product.getProduct_category_id());
					row.createCell(3).setCellValue(product.getProduct_status());
					if(product.isProduct_deleted()) {
						row.createCell(4).setCellValue(1);
					} else {
						row.createCell(4).setCellValue(0);
					}
					row.createCell(5).setCellValue(product.getProduct_import_price());
					row.createCell(6).setCellValue(product.getProduct_images());
					row.createCell(7).setCellValue(product.getProduct_guarantee_id());
					row.createCell(8).setCellValue(product.getProduct_notes());
					row.createCell(9).setCellValue(product.getProduct_last_modified());
				}
				
				// Try block to check for exceptions
				try {
					// Closing file output connections
					workbook.write(os);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}
	
	/* ================= upload file function v2 ================= */
	public static String uploadFileV2(HttpServletRequest request, HttpServletResponse response, String folder,
			String oldFiles) throws FileNotFoundException {
		// đường dẫn trên server
		String uploadPath = request.getServletContext().getRealPath("/img") + File.separator + folder;
		// đường dẫn đến thư mục cụ thể
		String specPath = Utilities_const.UPLOAD_PATH.label + "/" + folder;
		// đường dẫn của các file đã upload
		String fileUploadedPath = "";
		// file copy cho file upload để ghi vào đường dẫn cụ thể
		byte[] fileData;

		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		if (!Files.exists(Path.of(specPath))) {
			try {
				Files.createDirectory(Path.of(specPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileOutputStream fos = null;
		try {
			String fileName = null;
			for (Part part : request.getParts()) {
				fileName = part.getSubmittedFileName();
				if (fileName != null && !fileName.equalsIgnoreCase("") && !fileName.isBlank()) {
//					if (oldFiles == null || oldFiles.indexOf(fileName) == -1) {
					Date date = new Date();
					long time = date.getTime();
					String fileType = fileName.substring(fileName.lastIndexOf("."));
					String newName = time + fileType;
					String filePath = uploadPath + File.separator + newName;
					fileUploadedPath += newName + ",";
					// copy file
					byte[] fileCopy = part.getInputStream().readAllBytes();
					fos = new FileOutputStream(new File(specPath + File.separator + newName));
					part.write(filePath);
					fos.write(fileCopy);
//					}
				}
			}

			if (!fileUploadedPath.equalsIgnoreCase("")) {
				System.out.println(fileUploadedPath);
				// Xóa file cũ trong hệ thống và cập nhật đường dẫn mới
				delFiles(oldFiles, specPath, uploadPath);
				return fileUploadedPath.substring(0, fileUploadedPath.length() - 1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;

	}

	public static String uploadFile(HttpServletRequest request, HttpServletResponse response, String folder, Part part,
			String oldImg) {
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		String realPath = request.getServletContext().getRealPath("/img/user");
		Date date = new Date();
		long time = date.getTime();
		String fileType = fileName.substring(fileName.lastIndexOf("."));
		String newName = time + fileType;
		String filePath = realPath + File.separator + newName;
		try {

			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectory(Path.of(realPath));
			}
			if (Files.notExists(Path.of(filePath))) {
				part.write(filePath);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

		return newName;
	}

	/**
	 * Phương thức lấy file name từ part request thông qua header
	 * 
	 * @param part
	 * @return Tên file lấy được </br>
	 * 
	 *         Cập nhật ngày 13/11/2023
	 */
	private static String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return null;
	}

	public static void delFiles(String oldFiles, String specPath, String realPath) {
		
		if (oldFiles != null && !oldFiles.equalsIgnoreCase("")) {
			// lấy danh sách các đường dẫn file cũ
			String[] listOldFiles = oldFiles.split(",");
			for (String name : listOldFiles) {
				if (name != null) {
					System.out.println(specPath + "/" + name);
					System.out.println(realPath + File.separator + name);
					File specFile = new File(specPath + "/" + name);
					if(specFile.exists()) {
						if(specFile.delete()) {
							System.out.println("Xóa spec file " + name + " thành công");
						} else {
							System.out.println("Xóa spec file " + name + " không thành công");
						}
					} else {
						System.out.println("File không tồn tại");
					}
					File realFile = new File(realPath + File.separator + name);
					if(realFile.exists()) {
						if(realFile.delete()) {
							System.out.println("Xóa real file " + name + " thành công");
						} else {
							System.out.println("Xóa real file " + name + " không thành công");
						}
					} else {
						System.out.println("real File không tồn tại");
					}
				}
			}
		}
	}
	public static boolean delFile(String path) {
		if(path != null && !path.equals("")) {
			File file = new File(path);
			if(file.exists()) {
				try {
					file.delete();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
	/* ================= upload file function end ================= */
}
