package bill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import org.javatuples.*;

import library.Utilities;
import library.Utilities_date;
import objects.EmployeeObject;
import objects.WorkplaceObject;
import objects.BillObject;

public class BillLibrary {
	public static ArrayList<String> viewBillList(
			Quintet<ArrayList<BillObject>, Integer, HashMap<String,Integer>, HashMap<String,Integer>, ArrayList<String>> datas, 
			Sextet<EmployeeObject, 
			BillObject, 
			Short, 
			Byte ,
			BILL_SORT_TYPE,
			Boolean> infors){
		ArrayList<String> view = new ArrayList<String>();
		StringBuilder tmp = new StringBuilder();
		
		ArrayList<BillObject> wpItems = datas.getValue0(); 
		int wpTotal = datas.getValue1();
		
		
		short wpPage = infors.getValue2();
		byte wpPerPage = infors.getValue3();
		
		if (wpPerPage>9) {
			wpItems.forEach(item -> {
				tmp.append("<tr>");		
				tmp.append("<td class=\"text-center\">");
				tmp.append("<a href=\"/home/user/user-profile.jsp\">");
				tmp.append("<img class=\"rounded img-fluid avatar-40\"");
				tmp.append("src=\"../images/workplace/storage0"+wpItems.indexOf(item)+".jpg\" alt=\"profile\">");
				tmp.append("</a>");
				tmp.append("</td>");	
				tmp.append("<td class=\"col-2\">"+item.getBill_code()+"</td>");
				tmp.append("<td>(760) 756 7568</td>");
				tmp.append("<td>"+item.getBill_created_date()+"</td>");
				tmp.append("<td>"+item.getBill_creator_id()+"</td>");
				tmp.append("<td><span class=\"badge bg-primary\">Active</span></td>");
				tmp.append("<td>Acme Corporation</td>");
				tmp.append("<td>2019/12/01</td>");
				tmp.append("<td>");
				tmp.append("<div class=\"d-flex align-items-center justify-content-around list-user-action\">");
				tmp.append("<a class=\"btn btn-sm bg-primary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\" ");
				tmp.append("data-original-title=\"Add\" href=\"#\"><i class=\"ri-user-add-line mr-0\"></i></a>");
				tmp.append("<a class=\"btn btn-sm bg-primary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\" ");
				tmp.append("data-original-title=\"Edit\" href=\"/home/user/user-profile-edit.jsp\"><i class=\"ri-pencil-line mr-0\"></i></a>");
				tmp.append("<a class=\"btn btn-sm bg-primary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\"");
				tmp.append("data-original-title=\"Delete\" href=\"#\"><i class=\"ri-delete-bin-line mr-0\"></i></a>");
				tmp.append("</div>");
				tmp.append("</td>");
				tmp.append("</tr>");
			});
		}	
		
		view.add(tmp.toString());
		String url = "/home/workplace/workplace-list.jsp";
		view.add((BillLibrary.viewBillListPage(url,wpPage,wpPerPage,wpTotal).toString()));
		
		view.add(BillLibrary.createBillChart(new Triplet<ArrayList<BillObject>, HashMap<String,Integer>, String>(datas.getValue0(),datas.getValue2(), "importChart")).toString());
		view.add(BillLibrary.createBillChart(new Triplet<ArrayList<BillObject>, HashMap<String,Integer>, String>(datas.getValue0(),datas.getValue3(), "exportChart")).toString());
		return view;
	}
	
	private static StringBuilder createBillChart(Triplet<ArrayList<BillObject>, HashMap<String,Integer>, String> datas){
		StringBuilder tmp = new StringBuilder();		
		
		StringBuilder workplaceName = new StringBuilder();
		StringBuilder workplaceIncome = new StringBuilder();		
		
		HashMap<String,Integer> map = datas.getValue1();
		Iterator<BillObject> iterator = datas.getValue0().iterator();			
		while (iterator.hasNext()) {
			BillObject item = iterator.next();
			workplaceIncome.append(map.get(item.getBill_id()));
			workplaceName.append("'"+item.getBill_code()).append(" (").append(item.getBill_created_date()).append(")'");
			
			if (iterator.hasNext()) {
				workplaceIncome.append(",");
				workplaceName.append(",");
			}
		}
		
		String chartID = datas.getValue2();
		System.out.println(datas.getValue0());
		System.out.println(map);
		System.out.println(workplaceIncome.toString());
		tmp.append("<div id=\""+chartID+"\"></div>");
		tmp.append("");
		tmp.append("<script>");
		tmp.append("");
		tmp.append("var options = {");
		tmp.append("series: [{data: ["+workplaceIncome.toString()+"], name:'VND'}],");
		tmp.append("chart: {type: 'bar', height: 350},");
		tmp.append("plotOptions: {");
		tmp.append("bar: { borderRadius: 4, borderRadiusApplication: 'end', horizontal: true,}");
		tmp.append("},");
		tmp.append("dataLabels: {enabled: false},");
		tmp.append("xaxis: {categories: ["+workplaceName.toString()+"],}");
		tmp.append("};");
		tmp.append("");
		tmp.append("var chart = new ApexCharts(document.querySelector('#"+chartID+"'), options);");
		tmp.append("chart.render();");
		tmp.append("");
		tmp.append("</script>");
		tmp.append("");
		tmp.append("");
		return tmp;
	}
	
	private static StringBuilder createBillModal(BillObject item) {
		StringBuilder tmp = new StringBuilder();
		
		tmp.append("<div id=\"Bill"+item.getBill_id()+"\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"gridModalLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg modal-dialog-centered\" role=\"document\">");		
		tmp.append("<div class=\"modal-content\">");	
		tmp.append("<form method=\"post\" action=\"/home/UserImport\" enctype=\"multipart/form-data\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h3 class=\"modal-title\" >Thông tin chi tiết</h3>");
		tmp.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button>");
		tmp.append("</div><!--  Modal header -->");
		tmp.append("<div class=\"modal-body\">");
		tmp.append("<div class=\"container-fluid\">");
		tmp.append("<div class=\"row\">");
		tmp.append("<div class=\"col-lg-12\">");
		tmp.append("<h1 class=\"text-center py-3 my-3 text-primary\">Kho hàng 12</h1>");
		tmp.append("<div class=\"row align-items-start justify-content-around\">");
		tmp.append("<div class=\"col-lg-4\">");
		tmp.append("<img class=\"w-100 mb-2\" src=\"\" alt=\"...\">");
		tmp.append("</div> <!--  end col-lg-4 -->");
		tmp.append("<div class=\"col-lg-4\">");
		tmp.append("<table class=\"table d-table\">");
		tmp.append("<tbody class=\"w-100\">");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-6\">Mã đơn vị</th>");
		tmp.append("<td>12</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th>Tên kho hàng</th>");
		tmp.append("<td>Kho hàng 12</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th>Phân loại</th>");
		tmp.append("<td>Kho hàng</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th>Địa chỉ</th>");
		tmp.append("<td>12</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th>Liên kết Website</th>");
		tmp.append("<td>Trắng.com</td>");
		tmp.append("</tr>");
		tmp.append("</tbody>");
		tmp.append("</table>");
		tmp.append("</div> <!--  end col-lg-4 -->");
		tmp.append("<div class=\"col-lg-4\">");
		tmp.append("<table class=\"table d-table\">");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-6\">Ngày khởi tạo</th>");
		tmp.append("<td>01/01/1930</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-6\">Tình trạng</th>");
		tmp.append("<td>Hoạt động</td>");
		tmp.append("</tr>");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-6\">Người quản lý</th>");
		tmp.append("<td>Thế Hưởng</td>");
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
		tmp.append("</div> <!--  end row align-items-start justify-content-around -->");
		tmp.append("<div class=\"row justify-content-end\">");
		tmp.append("<div class=\"col-lg-8\">");
		tmp.append("<table class=\"table d-table\">");
		tmp.append("<tr>");
		tmp.append("<th class=\"col-lg-2 col-6\">Ghi chú</th>");
		tmp.append("<td>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ratione");
		tmp.append("animi");
		tmp.append("accusamus beatae assumenda temporibus error natus saepe voluptate, sit dolorum.");
		tmp.append("Veritatis incidunt placeat eum natus deleniti illum magni similique consequatur?");
		tmp.append("</td>");
		tmp.append("</tr>");
		tmp.append("</table>");
		tmp.append("</div> <!--  end col-lg-8 -->");
		tmp.append("</div><!--  end justify-content-end -->");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div><!--  Modal body -->");
		tmp.append("<div class=\"modal-footer\">");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>");
		tmp.append("<button type=\"submit\" class=\"btn btn-primary\">Save changes</button>");
		tmp.append("</div><!--  Modal footer -->");
		tmp.append("</form>");
		tmp.append("</div><!--  Modal content -->");
		tmp.append("</div><!--  Modal dialog -->");
		tmp.append("</div> <!--  Modal -->");
		return tmp;
	}
	
	
	//
	private static StringBuilder viewBillListPage(String url, short wpPage, byte wpPerPage ,int wpTotal){
		StringBuilder tmp = new StringBuilder();
		
		int wpTotalPage= wpTotal/wpPerPage;
			
		if ((wpTotal%wpPerPage!=0)) {
			wpTotalPage++;
		}
		
		tmp.append("<div class=\"row justify-content-between my-3\">");
		tmp.append("<div id=\"user-list-page-info\" class=\"col-md-6\">");
		tmp.append("<span>Hiển thị "+wpPage+" bản ghi trên "+wpTotal+" bản ghi</span>");
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
	
	
	public static ArrayList<String> viewMain(
			Quintet<ArrayList<BillObject>, Integer, HashMap<String,Integer>, HashMap<String,Integer>,ArrayList<String>> datas 
			){
		
		StringBuilder tmp = new StringBuilder();		
		
		StringBuilder ex = new StringBuilder();
		StringBuilder im = new StringBuilder();
		StringBuilder date = new StringBuilder();	
		
		HashMap<String,Integer> importData = datas.getValue2();
		HashMap<String,Integer> exportData = datas.getValue3();
		ArrayList<String> dateData = datas.getValue4();

//		dateData.sort((o1, o2) -> Utilities_date.getDate(o1).compareTo(Utilities_date.getDate(o2)));
		dateData.forEach((item)->{
			if (importData.get(item)!=null) {
				im.append(importData.get(item));			
			} else {
				im.append(0);
			}
			
			if (exportData.get(item)!=null) {
				ex.append(exportData.get(item));			
			} else {
				ex.append(0);
			}
			
			date.append("'").append(item).append("'");
			if (dateData.indexOf(item)<dateData.size()) {
				ex.append(",");
				im.append(",");
				date.append(",");
			}
		});
		
		ArrayList<String> view = new ArrayList<String>();

		tmp.append("<script>   ");
		tmp.append("	var options = {");
		tmp.append("	  series: [{");
		tmp.append("	  name: 'Import value',");
		tmp.append("	  type: 'area',");
		tmp.append("	  data: ["+im.toString()+"]");
		tmp.append("	}, {");
		tmp.append("	  name: 'Export value',");
		tmp.append("	  type: 'line',");
		tmp.append("	  data: ["+ex.toString()+"]");
		tmp.append("	}],");
		tmp.append("	  chart: {");
		tmp.append("	  height: 350,");
		tmp.append("	  type: 'line',");
		tmp.append("	},");
		tmp.append("	stroke: {");
		tmp.append("	  curve: 'smooth'");
		tmp.append("	},");
		tmp.append("	fill: {");
		tmp.append("	  type:'solid',");
		tmp.append("	  opacity: [0.35, 1],");
		tmp.append("	},");
		tmp.append("	labels: ["+date.toString()+"],");
		tmp.append("	markers: {");
		tmp.append("	  size: 0");
		tmp.append("	},");
		tmp.append("	yaxis: [");
		tmp.append("	  {");
		tmp.append("		title: {");
		tmp.append("		  text: 'VND',");
		tmp.append("		},");
		tmp.append("	  },");
		tmp.append("	  {");
		tmp.append("		opposite: true,");
		tmp.append("		title: {");
		tmp.append("		  text: 'VND',");
		tmp.append("		},");
		tmp.append("	  },");
		tmp.append("	],");
		tmp.append("	tooltip: {");
		tmp.append("	  shared: true,");
		tmp.append("	  intersect: false,");
		tmp.append("	  y: {");
		tmp.append("		formatter: function (y) {");
		tmp.append("		  if(typeof y !== \"undefined\") {");
		tmp.append("			return  y.toFixed(0) + \" VND\";");
		tmp.append("		  }");
		tmp.append("		  return y;");
		tmp.append("		}");
		tmp.append("	  }");
		tmp.append("	}");
		tmp.append("	};");
		tmp.append("	var chart = new ApexCharts(document.querySelector(\"#bill_statistic\"), options);");
		tmp.append("	chart.render();");
		tmp.append("</script>");
		
		view.add(tmp.toString());
		return view;
	}
}
