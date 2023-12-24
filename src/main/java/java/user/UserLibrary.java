package user;

import java.util.*;
import objects.*;
import org.javatuples.*;

public class UserLibrary {
	public static ArrayList<String> viewUserList(Pair<ArrayList<UserObject>,Integer> datas, Quartet<UserObject, Short, Byte, USER_SORT_TYPE> infors){
		ArrayList<String> view = new ArrayList<String>();
		StringBuilder tmp = new StringBuilder();
		
		ArrayList<UserObject> uItem = datas.getValue0(); 
		int uTotal = datas.getValue1();
		uItem.size();
		
		UserObject currentUser = infors.getValue0();
		short uPage = infors.getValue1();
		byte uPerPage = infors.getValue2();
		
		uItem.forEach(item -> {
			tmp.append("<tr>");
			tmp.append("<td class=\"text-center col-1\">");
			tmp.append("<a href=\"/home/user/user-profile.jsp\">");
			tmp.append("<img class=\"rounded img-fluid avatar-40\"");
			tmp.append("src=\""+item.getUser_images()+"\" alt=\"profile\">");
			tmp.append("</a>");
			tmp.append("</td>");
			tmp.append("<td class=\"col-2\">"+item.getUser_fullname()+"</td>");
			tmp.append("<td class=\"col-1\">"+item.getUser_mobile_phone()+"</td>");
			tmp.append("<td class=\"col-2\">"+item.getUser_email()+"</td>");
			tmp.append("<td class=\"col-3\">"+item.getUser_address()+"</td>");
			tmp.append("<td class=\"col-2\">");
			tmp.append("<div class=\"d-flex align-items-center justify-content-around list-user-action\">");
			tmp.append("<a class=\"btn btn-sm bg-primary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\" ");
			tmp.append("data-original-title=\"Add\" href=\"#\"><i class=\"ri-user-add-line mr-0\"></i>");
			tmp.append("</a>");
			if (item.getUser_id()==currentUser.getUser_id()) {
				tmp.append("<a class=\"btn btn-sm btn-secondary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\" ");
				tmp.append("data-original-title=\"Edit\" href=\"/home/user/user-profile-edit.jsp\"><i class=\"ri-pencil-line mr-0\"></i>");
				tmp.append("</a>");
			} else {
				if (currentUser.getUser_permission()>=4) {
					tmp.append("<a class=\"btn btn-sm btn-secondary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\" ");
					tmp.append("data-original-title=\"Edit\" href=\"/home/user/user-profile-edit.jsp\"><i class=\"ri-pencil-line mr-0\"></i>");
					tmp.append("</a>");
					
					tmp.append("<button class=\"btn btn-sm btn-danger\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\"");
					tmp.append("data-original-title=\"Delete\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\"><i class=\"ri-delete-bin-line mr-0\"></i>");	
					tmp.append("</button>");
				} else {
					if (item.getUser_parent_id()==currentUser.getUser_id()) {
						tmp.append("<a class=\"btn btn-sm btn-secondary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\" ");
						tmp.append("data-original-title=\"Edit\" href=\"/home/user/user-profile-edit.jsp\"><i class=\"ri-pencil-line mr-0\"></i>");
						tmp.append("</a>");
					} else {
						tmp.append("<a class=\"btn btn-sm btn-secondary\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\" ");
						tmp.append("data-original-title=\"Edit\" href=\"/home/user/user-profile-edit.jsp\" disabled><i class=\"ri-pencil-line mr-0\"></i>");
						tmp.append("</a>");
					}
					
					tmp.append("<a class=\"btn btn-sm btn-danger\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"\"");
					tmp.append("data-original-title=\"Delete\" data-bs-toggle=\"modal\" data-bs-target=\"#delUser"+item.getUser_id()+"\" disabled><i class=\"ri-delete-bin-line mr-0\"></i>");	
					tmp.append("</a>");
				}
			}	
			tmp.append("</a>");
			tmp.append("</div>");
			tmp.append("</td>");
			tmp.append("</tr>");
			tmp.append(UserLibrary.viewDelUser(item));
		});
		
		view.add(tmp.toString());
		
		view.add((UserLibrary.viewUserListPage(uPage,uPerPage,uTotal).toString()));
		
		return view;
	}
	
	private static StringBuilder viewDelUser(UserObject item) {
		StringBuilder tmp = new StringBuilder();
		
		String title;
		if (item.isUser_deleted()) {
			title = "Xóa vĩnh viễn";
			
		} else {
			title = "Xóa tài khoản";
		}
		
		tmp.append("<div class=\"modal fade\" id=\"delUser"+item.getUser_id()+"\" tabindex=\"-1\" aria-labelledby=\"UserLabel"+item.getUser_id()+"\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog modal-lg\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h1 class=\"modal-title fs-5\" id=\"UserLabel"+item.getUser_id()+"\">"+title+"</h1>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		
		if (item.isUser_deleted()) {
			tmp.append("Bạn có chắc chắn xóa vĩnh viễn tài khoản <b>").append(item.getUser_fullname()).append("("+item.getUser_name()+")</b><br>");
			tmp.append("Tài khoản không thể phục hồi được nữa.");
		} else {
			tmp.append("Bạn có chắc chắn xóa tài khoản <b>").append(item.getUser_fullname()).append("("+item.getUser_name()+")</b><br>");
			tmp.append("Hệ thống sẽ tạm thời lưu vào thùng rác, bạn có thể phục hồi trong vòng 30 ngày.");
		}
	
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		
		if (item.isUser_deleted()) {
			tmp.append("<a href=\"/adv/user/dr?id="+item.getUser_id()+"&pid="+item.getUser_parent_id()+"\" class=\"btn btn-danger\">Xóa vĩnh viễn</a>");
		} else {
			tmp.append("<a href=\"/adv/user/dr?id="+item.getUser_id()+"&t&pid="+item.getUser_parent_id()+"\" class=\"btn btn-danger\">Xóa</a>");
		}
		
		tmp.append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Hủy</button>");		
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		return tmp;
	}
	
	
	//Phương thức hiện thị số trang
	private static StringBuilder viewUserListPage(short uPage, byte uPerPage ,int uTotal){
		StringBuilder tmp = new StringBuilder();
		
		int uTotalPage= uTotal/uPerPage;
			
		if ((uTotal%uPerPage!=0)) {
			uTotalPage++;
		}
		
		tmp.append("<div class=\"row justify-content-between my-3\">");
		tmp.append("<div id=\"user-list-page-info\" class=\"col-md-6\">");
		tmp.append("<span>Hiển thị "+uPerPage+" bản ghi trên "+uTotal+" bản ghi</span>");
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
		
		for (int i = uPage-1; i>0; i--) {
			leftCurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\"/home/user/user-list.jsp?page="+i+"\">"+i+"</a></li>" + leftCurrent;
			if (++count>=2) {
				break;
			}
		}
		
		if (uPage>=4) {
			leftCurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">...</a></li>" + leftCurrent;
			//		if (page>1) {
			//			leftCurrent = ("<li class=\"page-item\"><a class=\"page-link\" href=\""+()+"\"><span aria-hidden=\"true\">&laquo;</span></a></li>") + leftCurrent;
			//		}
		}
		tmp.append(leftCurrent);
		
		tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">"+uPage+"</a></li>");
		
		//Right Current
		String rightCurrent = "";
		count=0;
		for (int i = uPage+1; i<=uTotalPage; i++) {
			rightCurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\"/home/user/user-list.jsp?page="+i+"\">"+i+"</a></li>";
			if (++count>=2) {
				break;
			}
		}
		
		if (uTotalPage> uPage+4) {
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
