package employee;

import java.util.*;
import objects.*;
import user.USER_SORT_TYPE;
import user.UserLibrary;

import org.javatuples.*;

public class EmployeeLibrary {
	public static ArrayList<String> viewEmployeeList(Pair<ArrayList<EmployeeObject>,Integer> datas, Quartet<EmployeeObject, Short, Byte, EMPLOYEE_SORT_TYPE> infors){
		ArrayList<String> view = new ArrayList<String>();
		StringBuilder tmp = new StringBuilder();
		
		ArrayList<EmployeeObject> eItem = datas.getValue0(); 
		int eTotal = datas.getValue1();
		
		
		short ePage = infors.getValue1();
		byte ePerPage = infors.getValue2();
		
		eItem.forEach(item -> {
			tmp.append("<tr>");		
			tmp.append("<td class=\"text-center\">");
			tmp.append("<a href=\"/home/user/user-profile.jsp\">");
			tmp.append("<img class=\"rounded img-fluid avatar-40\"");
			tmp.append("src=\"../images/user/0"+eItem.indexOf(item)+".jpg\" alt=\"profile\">");
			tmp.append("</a>");
			tmp.append("</td>");	
			tmp.append("<td class=\"col-2\">"+item.getUser_fullname()+"</td>");
			tmp.append("<td>(760) 756 7568</td>");
			tmp.append("<td>"+item.getUser_email()+"</td>");
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
		
		view.add(tmp.toString());
		
		view.add((EmployeeLibrary.viewEmployeeListPage(ePage,ePerPage,eTotal).toString()));
		
		return view;
	}
	
	private static StringBuilder viewEmployeeListPage(short ePage, byte ePerPage ,int eTotal){
		StringBuilder tmp = new StringBuilder();
		
		int eTotalPage= eTotal/ePerPage;
			
		if ((eTotal%ePerPage!=0)) {
			eTotalPage++;
		}
		
		tmp.append("<div class=\"row justify-content-between my-3\">");
		tmp.append("<div id=\"user-list-page-info\" class=\"col-md-6\">");
		tmp.append("<span>Hiển thị "+ePage+" bản ghi trên "+eTotal+" bản ghi</span>");
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
		
		for (int i = ePage-1; i>0; i--) {
			leftCurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\"/home/employee/employee-list.jsp?page="+i+"\">"+i+"</a></li>" + leftCurrent;
			if (++count>=2) {
				break;
			}
		}
		
		if (ePage>=4) {
			leftCurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">...</a></li>" + leftCurrent;
			//		if (page>1) {
			//			leftCurrent = ("<li class=\"page-item\"><a class=\"page-link\" href=\""+()+"\"><span aria-hidden=\"true\">&laquo;</span></a></li>") + leftCurrent;
			//		}
		}
		tmp.append(leftCurrent);
		
		tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">"+ePage+"</a></li>");
		
		//Right Current
		String rightCurrent = "";
		count=0;
		for (int i = ePage+1; i<=eTotalPage; i++) {
			rightCurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\"/home/employee/employee-list.jsp?page="+i+"\">"+i+"</a></li>";
			if (++count>=2) {
				break;
			}
		}
		
		if (eTotalPage> ePage+4) {
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
