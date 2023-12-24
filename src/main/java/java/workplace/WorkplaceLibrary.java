package workplace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.javatuples.*;

import com.ibm.icu.impl.UResource.Array;

import library.Utilities;
import library.Utilities_date;
import objects.BillObject;
import objects.EmployeeObject;
import objects.WorkplaceObject;

public class WorkplaceLibrary {
	public static ArrayList<String> viewWorkplaceList(
			Septet<	ArrayList<WorkplaceObject>,
			Integer, 
			HashMap<Integer,Integer>, 
			HashMap<Integer,Integer>, 
			HashMap<Pair<String,Integer>,Integer> ,
			HashMap<Pair<String,Integer>,Integer>,
			HashMap<Integer, Pair<String,Integer>>> datas, 
			Sextet<EmployeeObject, 
			WorkplaceObject, 
			Short, 
			Byte ,
			WORKPLACE_SORT_TYPE,
			Boolean> infors, byte isOpenModal , String url){
		ArrayList<String> view = new ArrayList<String>();
		StringBuilder tmp = new StringBuilder();
		
		ArrayList<WorkplaceObject> wpItems = datas.getValue0(); 
		int wpTotal = datas.getValue1();
		
		WorkplaceObject similar = infors.getValue1();
		short wpPage = infors.getValue2(); 
		byte wpPerPage = infors.getValue3();
		tmp.append("<form action=\"WorkplaceList\" method=\"post\">");
		if (wpPerPage>3) {				
			tmp.append("<table id=\"workplace-list-table\" class=\"table table-striped dataTable mt-4\" role=\"grid\" aria-describedby=\"user-list-page-info\">");
			tmp.append("<thead>");
			tmp.append("<tr class=\"ligth\" role=\"row\">");
			tmp.append("<th>");
			tmp.append("Tên");
			tmp.append("</th>");
			tmp.append("<th>Số điện thoại</th>");
			tmp.append("<th>Ngày khởi tạo</th>");
			tmp.append("<th>Địa chỉ</th>");
			tmp.append("<th>Trạng thái</th>");
			tmp.append("<th style=\"min-width: 120px\">Hành động</th>");
			tmp.append("</tr>");
			tmp.append("</thead>");
			tmp.append("<tbody>"); 
			
			wpItems.forEach(item -> {
				tmp.append("<tr>");		
				tmp.append("<td>"+item.getWorkplace_name()+"</td>");
				tmp.append("<td>(760) 756 7568</td>");
				tmp.append("<td>"+item.getWorkplace_created_date()+"</td>");
				tmp.append("<td>"+item.getWorkplace_address()+"</td>");
				tmp.append("<td><span class=\"badge bg-primary\">Active</span></td>");
				tmp.append("<td style=\"min-width: 120px\">");
				tmp.append("<div class=\"d-flex align-items-center justify-content-around list-user-action\">");
				tmp.append("<a class=\"btn btn-sm bg-primary\" href=\""+url+"?Modal=1&page="+wpPage+"&id="+item.getWorkplace_id()+"\"> ");
				tmp.append("<i class=\"fa-regular fa-eye\"></i></a>");
				tmp.append("<a class=\"btn btn-sm bg-secondary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\" ");
				tmp.append("data-original-title=\"Edit\" href=\"/home/user/user-profile-edit.jsp\"><i class=\"ri-pencil-line mr-0\"></i></a>");
				tmp.append("<a class=\"btn btn-sm bg-danger\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\"");
				tmp.append("data-original-title=\"Delete\" href=\"#\"><i class=\"ri-delete-bin-line mr-0\"></i></a>");
				tmp.append("</div>");
				tmp.append("</td>");
				tmp.append("</tr>");
				
				
			});		
			tmp.append("</tbody>");
			tmp.append("</table>");//end-table
			
			
			switch (isOpenModal) {
				case 1:
					System.out.println("Id:"+similar.getWorkplace_id());
					if (similar.getWorkplace_id()<=0) {
						tmp.append(WorkplaceLibrary.viewWorkplaceDetailModal(wpItems.get(wpItems.size()-1), datas, wpPage, wpPerPage, wpTotal, url));
						tmp.append("<script>");
						tmp.append("window.onload = function() {");
						tmp.append("$(\"#Workplace"+wpItems.get(wpItems.size()-1).getWorkplace_id()+"\").modal('show');");
						tmp.append("};");
						tmp.append("</script>");
					} else {
						wpItems.forEach(item -> {	
							if (item.getWorkplace_id()==similar.getWorkplace_id()) {
//								tmp.append("<button id=\"Workplace"+item.getWorkplace_id()+"\" type=\"button\" data-toggle=\"modal\"  data-target=\"#Workplace"+item.getWorkplace_id()+"\" style=\"height:95%\" hidden>");
//								tmp.append("</button>");
								tmp.append(WorkplaceLibrary.viewWorkplaceDetailModal(item, datas, wpPage, wpPerPage, wpTotal, url));
								tmp.append("<script>");
								tmp.append("window.onload = function() {");
								tmp.append("$(\"#Workplace"+similar.getWorkplace_id()+"\").modal('show');");
								tmp.append("};");
								tmp.append("</script>");
							}
						});
					}	
					break;
				case 2:
					System.out.println("Id:"+similar.getWorkplace_id());
					if (similar.getWorkplace_id()<=0) {
						tmp.append(WorkplaceLibrary.viewWorkplaceDetailModal(wpItems.get(0), datas, wpPage, wpPerPage, wpTotal, url));
						tmp.append("<script>");
						tmp.append("window.onload = function() {");
						tmp.append("$(\"#Workplace"+wpItems.get(0).getWorkplace_id()+"\").modal('show');");
						tmp.append("};");
						tmp.append("</script>");
					} else {
						wpItems.forEach(item -> {	
							if (item.getWorkplace_id()==similar.getWorkplace_id()) {
//								tmp.append("<button id=\"Workplace"+item.getWorkplace_id()+"\" type=\"button\" data-toggle=\"modal\"  data-target=\"#Workplace"+item.getWorkplace_id()+"\" style=\"height:95%\" hidden>");
//								tmp.append("</button>");
								tmp.append(WorkplaceLibrary.viewWorkplaceDetailModal(item, datas, wpPage, wpPerPage, wpTotal, url));
								tmp.append("<script>");
								tmp.append("window.onload = function() {");
								tmp.append("$(\"#Workplace"+similar.getWorkplace_id()+"\").modal('show');");
								tmp.append("};");
								tmp.append("</script>");
							}
						});
					}
				default:
					break;
			}
			
		} else {
			tmp.append("<div class=\"col-sm-12 px-3 pt-3 row\">");
//			tmp.append("<div class=\"d-flex\" style=\"align-items: stretch;\">");
			
			wpItems.forEach(item->{
				tmp.append("<div class=\"col-sm-4 mb-3\">");
				tmp.append("<a class=\"card border-primary top-right shadow-showcase \" href=\""+url+"?Modal=true&page="+wpPage+"&id="+item.getWorkplace_id()+"\">");
				tmp.append("<img src=\""+item.getWorkplace_images()+"\" class=\"card-img-top\" alt=\"#\" height=\"200px\" width=\"400px\">");
				tmp.append("<div class=\"card-body\">");
				tmp.append("<h4 class=\"card-title\">"+item.getWorkplace_name()+"</h4>");
				tmp.append("<p class=\"card-text\">Địa chỉ: "+item.getWorkplace_address()+"</p>");	
//				tmp.append("<div class=\"card-text\" id=\"chart"+item.getWorkplace_id()+"\"></div>");
//				tmp.append(WorkplaceLibrary.viewChartDetail(item,datas,"chart"+item.getWorkplace_id()).toString());		
				tmp.append("</div>");
				tmp.append("</a>");//card
				tmp.append("</div>");
			});
			
//			tmp.append("</div>");//card-columns
			tmp.append("</div>");//col-sm-12	
			
			
			switch (isOpenModal) {
			case 1:
				System.out.println("Id:"+similar.getWorkplace_id());
				if (similar.getWorkplace_id()<=0) {
					tmp.append(WorkplaceLibrary.viewWorkplaceDetailModal(wpItems.get(wpItems.size()-1), datas, wpPage, wpPerPage, wpTotal, url));
					tmp.append("<script>");
					tmp.append("window.onload = function() {");
					tmp.append("$(\"#Workplace"+wpItems.get(0).getWorkplace_id()+"\").modal('show');");
					tmp.append("};");
					tmp.append("</script>");
				} else {
					wpItems.forEach(item -> {	
						if (item.getWorkplace_id()==similar.getWorkplace_id()) {
//							tmp.append("<button id=\"Workplace"+item.getWorkplace_id()+"\" type=\"button\" data-toggle=\"modal\"  data-target=\"#Workplace"+item.getWorkplace_id()+"\" style=\"height:95%\" hidden>");
//							tmp.append("</button>");
							tmp.append(WorkplaceLibrary.viewWorkplaceDetailModal(item, datas, wpPage, wpPerPage, wpTotal, url));
							tmp.append("<script>");
							tmp.append("window.onload = function() {");
							tmp.append("$(\"#Workplace"+similar.getWorkplace_id()+"\").modal('show');");
							tmp.append("};");
							tmp.append("</script>");
						}
					});
				}	
				break;
			case 2:
				System.out.println("Id:"+similar.getWorkplace_id());
				if (similar.getWorkplace_id()<=0) {
					tmp.append(WorkplaceLibrary.viewWorkplaceDetailModal(wpItems.get(0), datas, wpPage, wpPerPage, wpTotal, url));
					tmp.append("<script>");
					tmp.append("window.onload = function() {");
					tmp.append("$(\"#Workplace"+wpItems.get(0).getWorkplace_id()+"\").modal('show');");
					tmp.append("};");
					tmp.append("</script>");
				} else {
					wpItems.forEach(item -> {	
						if (item.getWorkplace_id()==similar.getWorkplace_id()) {
//							tmp.append("<button id=\"Workplace"+item.getWorkplace_id()+"\" type=\"button\" data-toggle=\"modal\"  data-target=\"#Workplace"+item.getWorkplace_id()+"\" style=\"height:95%\" hidden>");
//							tmp.append("</button>");
							tmp.append(WorkplaceLibrary.viewWorkplaceDetailModal(item, datas, wpPage, wpPerPage, wpTotal, url));
							tmp.append("<script>");
							tmp.append("window.onload = function() {");
							tmp.append("$(\"#Workplace"+similar.getWorkplace_id()+"\").modal('show');");
							tmp.append("};");
							tmp.append("</script>");
						}
					});
				}	
			default:
				break;
		}
		}
		tmp.append("</form>");
		
		view.add(tmp.toString());
		view.add((WorkplaceLibrary.viewWorkplaceListPage(url,wpPage,wpPerPage,wpTotal).toString()));		
		view.add(WorkplaceLibrary.viewWorkplaceAddingModal(datas.getValue6()).toString());
		return view;
	}
	
//	private static ArrayList<String> createWorkplaceChartList(Sextet<	ArrayList<WorkplaceObject>,
//			Integer, 
//			HashMap<Integer,Integer>, 
//			HashMap<Integer,Integer>, 
//			HashMap<Integer, Pair<String,Integer>> ,
//			HashMap<Integer, Pair<String,Integer>>> datas){	
//		ArrayList<String> view = new ArrayList<String>();
//		
//		return view;		
//	}
	
	private static StringBuilder viewWorkplaceAddingModal(HashMap<Integer , Pair<String, Integer>> datas) {
		StringBuilder tmp = new StringBuilder();
		tmp.append("<button type=\"button\" id=\"add-button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#addWorkplace\">");
		tmp.append("Thêm chi nhánh");
		tmp.append("</button>");
		tmp.append("<div id=\"addWorkplace\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"gridModalLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-xl w-100\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header px-4 bg-primary\">");
		tmp.append("<h5 class=\"modal-title text-white\">Thêm đơn vị</h5>");
		tmp.append("<button type=\"button\" class=\"close btn-close-white\" data-dismiss=\"modal\" aria-label=\"Close\">");
		tmp.append("<span aria-hidden=\"true\">×</span>");
		tmp.append("</button>");
		tmp.append("</div>");//modal-header
		tmp.append("");
		tmp.append("<form action=\"/home/WorkplaceList\" method=\"post\">");
		tmp.append("<div class=\"modal-body mt-3 overflow-auto max-vh-70\">");
		
		//Input ảnh
		tmp.append("<div class=\"row mt-3 mb-3 align-items-center\">");
		tmp.append("<label for=\"workplaceImages\" class=\"col-md-3 col-lg-2 col-form-label text-lg-right\">Ảnh:</label>");
		tmp.append("<div class=\"col-md-9 col-lg-10\" id=\"workplaceImages\">");
		tmp.append("<input type=\"file\" class=\"d-none\" id=\"workplaceImagesInput\" accept=\"image/png, image/jpeg\" multiple=\"\" />");
		tmp.append("<div class=\"row align-items-start\">");
		tmp.append("<img class=\"col-lg-6 mb-1 w-75\" src=\"storagemart-on-214th-street-in-edmonton-drive-up-units.jpg\"");
		tmp.append("alt=\"...\" />");
		tmp.append("<div class=\"row col-lg-5 pl-4 flex-lg-wrap overflow-auto flex-nowrap align-items-start\">");
		tmp.append("<!-- Addtional images -->");
		tmp.append("<img class=\"col-4 h-100 mb-2 pl-0\" src=\"storagemart-on-214th-street-in-edmonton-drive-up-units.jpg\"");
		tmp.append("alt=\"...\" />");
		tmp.append("<img class=\"col-4 h-100 mb-2 pl-0\" src=\"storagemart-on-214th-street-in-edmonton-drive-up-units.jpg\"");
		tmp.append("alt=\"...\" />");
		tmp.append("<img class=\"col-4 h-100 mb-2 pl-0\" src=\"storagemart-on-214th-street-in-edmonton-drive-up-units.jpg\"");
		tmp.append("alt=\"...\" />");
		tmp.append("<img class=\"col-4 h-100 mb-2 pl-0\" src=\"storagemart-on-214th-street-in-edmonton-drive-up-units.jpg\"");
		tmp.append("alt=\"...\" />");
		tmp.append("<img class=\"col-4 h-100 mb-2 pl-0\" src=\"storagemart-on-214th-street-in-edmonton-drive-up-units.jpg\"");
		tmp.append("alt=\"...\" />");
		tmp.append("<img class=\"col-4 h-100 mb-2 pl-0\" src=\"storagemart-on-214th-street-in-edmonton-drive-up-units.jpg\"");
		tmp.append("alt=\"...\" />");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("");
		tmp.append("<div class=\"pt-2\">");
		tmp.append("<label for=\"workplaceImagesInput\" class=\"btn btn-primary btn-sm mb-0 \" id=\"btnUploadAvatar\"");
		tmp.append("title=\"Upload new profile image\">AddIcon</label>");
		tmp.append("<button type=\"button\" class=\"btn btn-danger btn-sm ms-2\" data-bs-toggle=\"modal\"");
		tmp.append("data-bs-target=\"#delImgUser\">\"RemoveIcon\"</button>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		//Input tên
		tmp.append("<div class=\"row mb-3 mr-lg-5 align-items-center\">");
		tmp.append("<label for=\"workplaceName\" class=\"col-md-3 col-lg-2 text-lg-right col-form-label \">Tên chi nhánh:</label>");
			tmp.append("<div class=\"col-md-9 col-lg-10\">");
			tmp.append("<input class=\" form-control\" type=\"text\" placeholder=\"VD: Đơn vị 12\" name=\"workplaceName\" id=\"workplaceName\">");
			tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("");
		
		//Input Phân loại và mã đơn vị
		tmp.append("<div class=\"row mb-3 mr-lg-5 align-items-center\">");
			tmp.append("<label for=\"workplaceType\" class=\"col-md-3 col-lg-2 text-lg-right mb-3 mb-lg-1 col-form-label \">Phân loại:");
			tmp.append("</label>");
			tmp.append("<div class=\"col-lg-3 col-md-9 mb-3 mb-lg-0\">");
			tmp.append("<select class=\"form-control\" name=\"workplaceType\" id=\"workplaceType\">");
			tmp.append("<option value=\"0\">Kho hàng</option>");
			tmp.append("<option value=\"1\">Cửa hàng</option>");
			tmp.append("</select>");
			tmp.append("</div>");

			tmp.append("<label for=\"workplaceCode\" class=\"col-md-3 col-lg-2 text-lg-right col-form-label \">Mã đơn vị:");
			tmp.append("</label>");
			tmp.append("<div class=\"col-lg-5 col-md-9 \">");
			tmp.append("<input class=\" form-control\" type=\"text\" placeholder=\"VD: Đơn vị 12\" id=\"workplaceCode\">");
			tmp.append("</div>");
		tmp.append("</div>");

		tmp.append("<div class=\"row mb-3 mr-lg-5 align-items-center\">");
		tmp.append("<label for=\"workplaceManager\" class=\"col-md-3 col-lg-2 text-lg-right col-form-label \">Người quản lý:</label>");
		tmp.append("<div class=\"col-lg-6 col-md-9\">");
		tmp.append("<select class=\"form-control\" id=\"workplaceManager\" name=\"workplaceManager\">");
		tmp.append("<option value=\"1\">The Huong</option>");
		tmp.append("<option value=\"2\">Van Khai</option>");
		tmp.append("</select>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("");
		tmp.append("<div class=\"row mb-3 mr-lg-5 align-items-center\">");
		tmp.append("<label for=\"workplaceAddress\" class=\"col-md-3 col-lg-2 text-lg-right col-form-label \">Địa chỉ:</label>");
		tmp.append("<div class=\"col-md-9 col-lg-10\">");
		tmp.append("<input class=\" form-control\" type=\"text\" placeholder=\"VD: Hai Bà Trưng, Hà Nội \" name=\"workplaceAddress\" id=\"workplaceAddress\">");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("");
		tmp.append("<div class=\"row mb-3 mr-lg-5 align-items-center\">");
		tmp.append("<label for=\"workplaceContact\" class=\"col-md-3 col-lg-2 text-lg-right mb-3 mb-lg-1 col-form-label \">Liên hệ:");
		tmp.append("</label>");
		tmp.append("<div class=\"col-lg-10 col-md-9\">");
		tmp.append("<div class=\"input-group\">");
		tmp.append("<input class=\" form-control\" type=\"text\" placeholder=\"SDT\" name=\"workplacePhoneNumber\" id=\"workplacePhoneNumber\">");
		tmp.append("<input class=\" form-control\" type=\"text\" placeholder=\"Email\" name=\"workplaceEmail\" id=\"workplaceEmail\">");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");

		tmp.append("<div class=\"row mb-3 mr-lg-5 align-items-center\">");
		tmp.append("<label for=\"workplaceNotes\"");
		tmp.append("class=\"col-md-3 col-lg-2 text-lg-right mb-3 mb-lg-1 col-form-label \">Ghi chú:</label>");
		tmp.append("<div class=\"col-lg-10 col-md-9\">");
		tmp.append("<textarea class=\"form-control\" type=\"text\" placeholder=\"VD: workplace@example.com\" name=\"workplaceNotes\" id=\"workplaceNotes\"></textarea>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("");
		tmp.append("");
		tmp.append("<div class=\"row align-items-center\">");
		tmp.append("<div class=\"w-100\">");
		tmp.append("<div class=\"card card-widget task-card mb-2\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<div class=\"d-flex flex-wrap align-items-center justify-content-between\">");
		tmp.append("<h5>Sản phẩm cho chi nhánh</h5>");
		tmp.append("<div class=\"media align-items-center\">");
		tmp.append("<a class=\"btn bg-primary-light\" data-toggle=\"collapse\" href=\"#collapseEdit3\" role=\"button\"");
		tmp.append("aria-expanded=\"false\" aria-controls=\"collapseEdit3\"><i class=\"ri-edit-box-line m-0\"></i></a>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("<div class=\"collapse\" id=\"collapseEdit3\">");
		tmp.append("<div class=\"card card-list task-card\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<div class=\"card mb-3\">");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<div class=\"w-100\">");
		tmp.append("<table id=\"myTable\" class=\"display table-striped data-table dataTable\">");
		tmp.append("<thead>");
		tmp.append("<tr>");
		tmp.append("<th><button id=\"chkAllBtn\" type=\"button\" style=\"border: none; background: transparent; font-size: 14px;\"><i class=\"far fa-square\"></i></button></th>");
		tmp.append("<th>Tên sản phẩm</th>");
		tmp.append("<th>Mã sản phẩm</th>");
		tmp.append("<th>Số lượng</th>");
		tmp.append("<th>Giá tiền</th>");
		tmp.append("</tr>");
		tmp.append("</thead>");
		tmp.append("<tbody>");
		datas.forEach((key,value)->{
			tmp.append("<tr>");
			tmp.append("<td>");
//			tmp.append("<input class=\"form-check-input\" type=\"checkbox\" value=\""+key+"\">");
			tmp.append("</td>");
			tmp.append("<td>"+value.getValue0()+"</td>");
			tmp.append("<td>"+key+"</td>");
			tmp.append("<td>");
			tmp.append("<input class=\"form-control\" type=\"number\">");
			tmp.append("</td>");
			tmp.append("<td>"+value.getValue1()+"</td>");
			tmp.append("</tr>");
		});
		tmp.append("</tbody>");
		tmp.append("</table>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");//modal-body
		tmp.append("<div class=\"modal-footer\">");
		tmp.append("<button type=\"submit\" class=\"btn btn-primary\">Xác nhận</button>");
		tmp.append("</div>");//modal-footer
		tmp.append("</form>");
		tmp.append("</div>");//modal-content
		tmp.append("</div>");//modal-dialog
		tmp.append("</div>");//modal
		return tmp;
	}
	
	private static StringBuilder viewWorkplaceStatisticChart(Sextet<	ArrayList<WorkplaceObject>,
			Integer, 
			HashMap<Integer,Integer>, 
			HashMap<Integer,Integer>, 
			HashMap<Pair<String,Integer>,Integer> ,
			HashMap<Pair<String,Integer>,Integer>> datas, String chartName){	
		
		ArrayList<WorkplaceObject> workplaceList = datas.getValue0();
		HashMap<Integer,Integer> workplaceIncome = datas.getValue2();
		StringBuilder tmp = new StringBuilder();			
		StringBuilder NameString = new StringBuilder();
		StringBuilder IncomeString = new StringBuilder();		

		Iterator<WorkplaceObject> iterator = workplaceList.iterator();			
		while (iterator.hasNext()) {

			WorkplaceObject item = iterator.next();
			IncomeString.append(workplaceIncome.get(item.getWorkplace_id()));
			NameString.append("'"+Utilities.decode(item.getWorkplace_name())).append(" (").append(item.getWorkplace_created_date()).append(")'");
			
			if (iterator.hasNext()) {
				IncomeString.append(",");
				NameString.append(",");
			}
		}
		
		tmp.append("<div id=\""+chartName+"\"></div>");
		tmp.append("");
		tmp.append("<script>");
		tmp.append("");
		tmp.append("var options = {");
		tmp.append("series: [{data: ["+IncomeString.toString()+"], name:'VND'}],");
		tmp.append("chart: {type: 'bar', height: 350},");
		tmp.append("plotOptions: {");
		tmp.append("bar: { borderRadius: 4, borderRadiusApplication: 'end', horizontal: true,}");
		tmp.append("},");
		tmp.append("dataLabels: {enabled: false},");
		tmp.append("xaxis: {categories: ["+NameString.toString()+"],}");
		tmp.append("};");
		tmp.append("");
		tmp.append("var chart = new ApexCharts(document.querySelector('#"+chartName+"'), options);");
		tmp.append("chart.render();");
		tmp.append("");
		tmp.append("</script>");
		tmp.append("");
		tmp.append("");
		return tmp;
	}
	
	private static StringBuilder viewChartDetail(
			WorkplaceObject detail,
			Septet<	ArrayList<WorkplaceObject>,
			Integer, 
			HashMap<Integer,Integer>, 
			HashMap<Integer,Integer>, 
			HashMap<Pair<String,Integer>,Integer> ,
			HashMap<Pair<String,Integer>,Integer>,
			HashMap<Integer, Pair<String,Integer>>> datas
			,String chartname){
		
		StringBuilder tmp = new StringBuilder();		
		
		StringBuilder ex = new StringBuilder();
		StringBuilder im = new StringBuilder();
		StringBuilder date = new StringBuilder();			
		HashMap<Pair<String,Integer>,Integer> importData = datas.getValue4();
		HashMap<Pair<String,Integer>,Integer> exportData = datas.getValue5();
		ArrayList<String> dateData = new ArrayList<String>();
		
		importData.forEach((key, value)->{
			if (key.getValue0()!=null) {
				if (!dateData.contains(key.getValue0())) {
					dateData.add(key.getValue0());
				}				
			}		
		});
		
		exportData.forEach((key, value)->{
			if (key.getValue0()!=null) {
				if (!dateData.contains(key.getValue0())) {
					dateData.add(key.getValue0());
				}				
			}			
		});
		

		dateData.sort((o1, o2) -> Utilities_date.getDate(o1).compareTo(Utilities_date.getDate(o2)));
		
		dateData.forEach((item)->{		
			Pair<String,Integer> key = new Pair<>(item,detail.getWorkplace_id());
			if (importData.get(key)!=null) {
				im.append(importData.get(key));			
			} else {
				im.append(0);
			}
			
			if (exportData.get(key)!=null) {
				ex.append(exportData.get(key));			
			} else {
				ex.append(0);
			}
			
			date.append("'").append(item).append("'");
			if (dateData.indexOf(item)<dateData.size()-1) {
				ex.append(",");
				im.append(",");
				date.append(",");
			}
		});
		
//		System.out.println(ex.toString());
//		System.out.println(im.toString());
		System.out.println(date.toString());

//		tmp.append("<script>   ");
//		tmp.append("	var options = {");
//		tmp.append("	  series: [{");
//		tmp.append("	  name: 'Import value',");
//		tmp.append("	  type: 'area',");
//		tmp.append("	  data: ["+im.toString()+"]");
//		tmp.append("	}, {");
//		tmp.append("	  name: 'Export value',");
//		tmp.append("	  type: 'line',");
//		tmp.append("	  data: ["+ex.toString()+"]");
//		tmp.append("	}],");
//		tmp.append("	  chart: {");
//		tmp.append("	  height: 350,");
//		tmp.append("	  type: 'line',");
//		tmp.append("	},");
//		tmp.append("	stroke: {");
//		tmp.append("	  curve: 'smooth'");
//		tmp.append("	},");
//		tmp.append("	fill: {");
//		tmp.append("	  type:'solid',");
//		tmp.append("	  opacity: [0.35, 1],");
//		tmp.append("	},");
//		tmp.append("	labels: ["+date.toString()+"],");
//		tmp.append("	markers: {");
//		tmp.append("	  size: 0");
//		tmp.append("	},");
//		tmp.append("	yaxis: [");
//		tmp.append("	  {");
//		tmp.append("		title: {");
//		tmp.append("		  text: 'VND',");
//		tmp.append("		},");
//		tmp.append("	  },");
//		tmp.append("	  {");
//		tmp.append("		opposite: true,");
//		tmp.append("		title: {");
//		tmp.append("		  text: 'VND',");
//		tmp.append("		},");
//		tmp.append("	  },");
//		tmp.append("	],");
//		tmp.append("	tooltip: {");
//		tmp.append("	  shared: true,");
//		tmp.append("	  intersect: false,");
//		tmp.append("	  y: {");
//		tmp.append("		formatter: function (y) {");
//		tmp.append("		  if(typeof y !== \"undefined\") {");
//		tmp.append("			return  y.toFixed(0) + \" VND\";");
//		tmp.append("		  }");
//		tmp.append("		  return y;");
//		tmp.append("		}");
//		tmp.append("	  }");
//		tmp.append("	}");
//		tmp.append("	};");
//		tmp.append("	var chart = new ApexCharts(document.querySelector(\"#"+chartname+"\"), options);");
//		tmp.append("	chart.render();");
//		tmp.append("</script>");
		
		
		
		
		tmp.append("<script>");		
		tmp.append("if (document.querySelector(\"#"+chartname+"\")!=null) {");
		tmp.append("const options = {");
		tmp.append("chart: {");
		tmp.append("height: 260,");
		tmp.append("type: \"area\"");
		tmp.append("},");
		tmp.append("dataLabels: {");
		tmp.append("enabled: !1");
		tmp.append("},");
		tmp.append("stroke: {");
		tmp.append("curve: \"smooth\"");
		tmp.append("},");
		tmp.append("colors: [\"#4788ff\", \"#ff4b4b\"],");
		tmp.append("series: [{");
		tmp.append("name: \"series1\",");
		tmp.append("data: ["+im.toString()+"]");
		tmp.append("}, {");
		tmp.append("name: \"series2\",");
		tmp.append("data: ["+ex.toString()+"]");
		tmp.append("}],");
		tmp.append("xaxis: {");
		tmp.append("type: \"date\",");
		tmp.append("categories: ["+date.toString()+"]");
		tmp.append("},");
		tmp.append("tooltip: {");
		tmp.append("x: {");
		tmp.append("format: \"dd/MM/yy\"");
		tmp.append("}");
		tmp.append("}");
		tmp.append("};");
		tmp.append("const chart = new ApexCharts(document.querySelector(\"#"+chartname+"\"), options); ");
		tmp.append("chart.render(); ");
		tmp.append("const body = document.querySelector('body'); ");
		tmp.append("if (body.classList.contains('dark')) {");
		tmp.append("apexChartUpdate(chart, {");
		tmp.append("dark: true");
		tmp.append("});");
		tmp.append("}");
		tmp.append("");
		tmp.append("document.addEventListener('ChangeColorMode', function (e) {");
		tmp.append("apexChartUpdate(chart, e.detail);");
		tmp.append("});");
		tmp.append("}");
		tmp.append("</script>");
		return tmp;
	}
	
	
	
	private static StringBuilder viewWorkplaceDetailModal(WorkplaceObject item,
			Septet<	ArrayList<WorkplaceObject>,
			Integer, 
			HashMap<Integer,Integer>, 
			HashMap<Integer,Integer>, 
			HashMap<Pair<String,Integer>,Integer> ,
			HashMap<Pair<String,Integer>,Integer>,
			HashMap<Integer, Pair<String,Integer>>> datas, short wpPage, byte wpPerPage ,int wpTotal, String url) {
		StringBuilder tmp = new StringBuilder();		
		tmp.append("<div id=\"Workplace"+item.getWorkplace_id()+"\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"gridModalLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-xl w-100 modal-dialog-centered\" role=\"document\">");		
		tmp.append("<div class=\"modal-content\">");	
		tmp.append("<div class=\"modal-header align-items-center\">");
		tmp.append("<h3 class=\"modal-title\" >"+item.getWorkplace_name()+"</h3>");
		tmp.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button>");
		tmp.append("</div><!--  Modal header -->");
		tmp.append("<div class=\"modal-body\">");
		tmp.append("<div class=\"container-fluid\">"); 
		tmp.append("<div class=\"row\">");
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("<div class=\"row align-items-start justify-content-around\">");
		tmp.append("<div class=\"col-lg-5\">");
		tmp.append("<img class=\"w-100 mb-5\" src=\""+item.getWorkplace_images()+"\" height=\"200\" width=\"250\" alt=\"...\">");
		tmp.append("<div id=\"detailchart"+item.getWorkplace_id()+"\"></div>");
		tmp.append(WorkplaceLibrary.viewChartDetail(item,datas,"detailchart"+item.getWorkplace_id()).toString());	
		tmp.append("</div> <!--  end col-lg-4 -->");
		tmp.append("<div class=\"col-lg-7 row\">");
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<table class=\"table d-table\">");
		tmp.append("<tbody class=\"w-100\">");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-4\">Mã đơn vị</th>");
		tmp.append("<td>"+item.getWorkplace_id()+"</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-4\">Tên kho hàng</th>");
		tmp.append("<td>"+item.getWorkplace_name()+"</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-5\">Phân loại</th>");
		if (item.getWorkplace_type()==0) {
			tmp.append("<td>Kho hàng</td>");
		} else {
			tmp.append("<td>Cửa hàng</td>");
		}
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<tr>");
		tmp.append("<th>Liên kết Website</th>");
		tmp.append("<td>"+item.getWorkplace_website_link()+"</td>");
		tmp.append("</tr>");
		tmp.append("</tbody>");
		tmp.append("</table>");
		tmp.append("</div> <!--  end col-lg-4 -->");
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<table class=\"table d-table\">");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-6\">Ngày khởi tạo</th>");
		tmp.append("<td>"+item.getWorkplace_created_date()+"</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-6\">Tình trạng</th>");
		if (item.getWorkplace_status()==0) {
			tmp.append("<td>Đang hoạt động</td>");
		} else {
			tmp.append("<td>Đã ngừng hoạt động</td>");
		}
		
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-6\">Người quản lý</th>");
		tmp.append("<td>"+item.getWorkplace_manager_id()+"</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-6\">Khoản đầu tư</th>");
		tmp.append("<td>12</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th>Lợi nhuận</th>");
		tmp.append("<td>12</td>");
		tmp.append("</tr>");
		tmp.append("");
		tmp.append("</table>");
		tmp.append("</div> <!--  end col-lg-4 -->");
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("<table class=\"table d-table\">");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-lg-2 col-6\">Địa chỉ</th>");
		tmp.append("<td>"+item.getWorkplace_address()+"</td>");
		tmp.append("</tr>");
		tmp.append("</table>");
		tmp.append("</div> <!--  end col-lg-12 -->");
		
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("<table class=\"table d-table\">");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-lg-2 col-6\">Ghi chú</th>");
		tmp.append("<td>");
		tmp.append("<textarea class=\"form-control\">");
		tmp.append("Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione");
		tmp.append("animi");
		tmp.append("accusamus beatae assumenda temporibus error natus saepe voluptate, sit dolorum.");
		tmp.append("Veritatis incidunt placeat eum natus deleniti illum magni similique consequatur?");
		tmp.append("</textarea>");
		tmp.append("</td>");
		tmp.append("</tr>");
		tmp.append("</table>");
		tmp.append("</div> <!--  end col-lg-12 -->");
		tmp.append("</div>");
		tmp.append("</div> <!--  end row align-items-start justify-content-around -->");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div><!--  Modal body -->");
		tmp.append("<div class=\"modal-footer justify-content-between\">");
		
		int current = datas.getValue0().indexOf(item);
		int next = 0;
		int before = 0;
		int wpTotalPage= wpTotal/wpPerPage;
		
		
		short changedPage = wpPage;
		
		if ((wpTotal%wpPerPage!=0)) {
			wpTotalPage++;
		}
		
		if (current >= 1 && current<datas.getValue0().size()-1) {
			next = datas.getValue0().get(current+1).getWorkplace_id();
			before =  datas.getValue0().get(current-1).getWorkplace_id();
			
			tmp.append("<a href=\""+url+"?Modal=1&page="+wpPage+"&id="+before+"\">");
			tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-left\"></i></button>");
			tmp.append("</a>");
			
			tmp.append("<a href=\""+url+"?Modal=2&page="+wpPage+"&id="+next+"\">");
			tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-right\"></i></button>");
			tmp.append("</a>");
		} else {					
			if (current<1) {				
				if ((current+wpPerPage*(wpPage-1)+1)==wpTotal) {		
					if (wpPage==1) {
						changedPage = 1;
						tmp.append("<a href=\""+url+"?Modal=1&page="+changedPage+"\">");
						tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-left\"></i></button>");
						tmp.append("</a>");
						
						tmp.append("<a href=\""+url+"?Modal=2&page="+changedPage+"\">");
						tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-right\"></i></button>");
						tmp.append("</a>");
					} else {
						--changedPage ;
						tmp.append("<a href=\""+url+"?Modal=1&page="+changedPage+"\">");
						tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-left\"></i></button>");
						tmp.append("</a>");
						
						changedPage=1;
						tmp.append("<a href=\""+url+"?Modal=2&page="+changedPage+"\">");
						tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-right\"></i></button>");
						tmp.append("</a>");
					}					
				} else {								
					if (wpPage==1) {
						changedPage = (short) wpTotalPage;
						tmp.append("<a href=\""+url+"?Modal=1&page="+changedPage+"\">");
						tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-left\"></i></button>");
						tmp.append("</a>");
					} else {
						--changedPage;
						tmp.append("<a href=\""+url+"?Modal=1&page="+changedPage+"\">");
						tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-left\"></i></button>");
						tmp.append("</a>");
					}
					
					next = datas.getValue0().get(current+1).getWorkplace_id();
					tmp.append("<a href=\""+url+"?Modal=2&page="+wpPage+"&id="+next+"\">");
					tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-right\"></i></button>");
					tmp.append("</a>");
				}											
				
			} else {
				before =  datas.getValue0().get(current-1).getWorkplace_id();
				tmp.append("<a href=\""+url+"?Modal=1&page="+wpPage+"&id="+before+"\">");
				tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-left\"></i></button>");
				tmp.append("</a>");			
				
				if (current == datas.getValue0().size()-1){
					if ((current+wpPerPage*(wpPage-1)+1)==wpTotal) {
						changedPage = 1;
						tmp.append("<a href=\""+url+"?Modal=2&page="+changedPage+"\">");
						tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-right\"></i></button>");
						tmp.append("</a>");
					} else {
						++changedPage;
						tmp.append("<a href=\""+url+"?Modal=2&page="+changedPage+"\">");
						tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-right\"></i></button>");
						tmp.append("</a>");
					}	
				}
			}			
		}
		
//		if (before == 0) {
//			tmp.append("<a href=\""+url+"?Modal=true&?page="+wpPage+"\">");
//		} else {
//			tmp.append("<a href=\""+url+"?Modal=true&?page="+wpPage+"&?id="+before+"\">");
//		}
		
//		tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-left\"></i></button>");
//		tmp.append("</a>");	
//		
//		if (next == 0) {
//			tmp.append("<a href=\""+url+"?Modal=true&?page="+wpPage+"\">");
//		} else {
//			tmp.append("<a href=\""+url+"?Modal=true&?page="+wpPage+"&?id="+next+"\">");
//		}
		
//		tmp.append("<button type=\"button\" class=\"btn btn-primary\"><i class=\"fa-solid fa-angle-right\"></i></button>");
//		tmp.append("</a>");
		tmp.append("</div><!--  Modal footer -->");
		tmp.append("</div><!--  Modal content -->");
		tmp.append("</div><!--  Modal dialog -->");
		tmp.append("</div> <!--  Modal -->");
		return tmp;
	}
	
	//
	private static StringBuilder viewWorkplaceListPage(String url, short wpPage, byte wpPerPage ,int wpTotal){
		StringBuilder tmp = new StringBuilder();
		
		int wpTotalPage= wpTotal/wpPerPage;
			
		if ((wpTotal%wpPerPage!=0)) {
			wpTotalPage++;
		}
		
		tmp.append("<div class=\"row justify-content-between my-3\">");
		tmp.append("<div id=\"user-list-page-info\" class=\"col-md-6\">");
		tmp.append("<span>Hiển thị "+wpPerPage+" bản ghi trên "+wpTotal+" bản ghi</span>");
		tmp.append("</div>");
		tmp.append("<div class=\"col-md-6\">");
		tmp.append("<nav aria-label=\"Page navigation example\">");
		tmp.append("<ul class=\"pagination justify-content-end mb-0\">");
		tmp.append("<li class=\"page-item disabled\">");
		tmp.append("<a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\">Previous</a>");
		tmp.append("</li>");
		
		//Left Current
		String leftCurrent = "";
		int count = 0;
		
		for (int i = wpPage-1; i>0; i--) {
			leftCurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"?page="+i+"\">"+i+"</a></li>" + leftCurrent;
			if (++count>=2) {
				break;
			}
		}
		
		if (wpPage>=4) {
			leftCurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">...</a></li>" + leftCurrent;
			//		if (page>1) {
			//			leftCurrent = ("<li class=\"page-item\"><a class=\"page-link\" href=\""+()+"\"><span aria-hidden=\"true\">&laquo;</span></a></li>") + leftCurrent;
			//		}
		}
		tmp.append(leftCurrent);
		
		tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">"+wpPage+"</a></li>");
		
		//Right Current
		String rightCurrent = "";
		count=0;
		for (int i = wpPage+1; i<=wpTotalPage; i++) {
			rightCurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"?page="+i+"\">"+i+"</a></li>";
			if (++count>=2) {
				break;
			}
		}
		
		if (wpTotalPage> wpPage+4) {
			rightCurrent += "<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">...</a></li>";
		}
		tmp.append(rightCurrent);

		tmp.append("<li class=\"page-item\">");
		tmp.append("<a class=\"page-link\" href=\"#\">Next</a>");
		tmp.append("</li>");
		tmp.append("</ul>");
		tmp.append("</nav>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}
	

}
