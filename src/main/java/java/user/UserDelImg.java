package user;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionPool;
import library.Utilities_const;
import objects.UserObject;

/**
 * Servlet implementation class DeleteImage
 */
@WebServlet("/user/del/file")
public class UserDelImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDelImg() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserObject user = (UserObject) request.getSession().getAttribute("userLogined");
		if (user != null) {
			delFile(request, response, user);
		} else {
			response.sendRedirect("/ogn/user/login");
		}
	}

	private void delFile(HttpServletRequest request, HttpServletResponse response, UserObject user) throws ServletException, IOException {
		// Lấy tên của file muốn xóa
		String imgName = request.getParameter("img-name");
		int id = library.Utilities.getIntParam(request, "id");
		// khai báo đối tượng được chỉnh sửa (edituser)
		UserObject euser = null;
		if(id > 0) {
			// Tìm bộ quản lý kết nối
			ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("CPool");
			// Tạo đối tượng thực thi chức năng
			UserControl uc = new UserControl(cp);
			if (cp == null) {
				getServletContext().setAttribute("CPool", uc.getCP());
			}

			euser = uc.getUserObject(id);

			// Trả về kết nối
			uc.releaseConnection();
			
			if(euser != null) {
				// Thực hiện kiểm tra quyền và xóa ảnh
				if (canUserDeleteImage(user, euser, imgName)) {
					String imgPath = euser.getUser_images();
					// Kiểm tra imgpath có chứa image name hay không
					if(imgPath != null && !imgPath.equals("") && imgPath.contains(imgName)) {
						// đường dẫn trên server
						String realPath = request.getServletContext().getRealPath("/img") + File.separator + "user" + File.separator + imgName;
						// đường dẫn đến thư mục cụ thể
						String specPath = Utilities_const.UPLOAD_PATH.label + "/" + "user/" + imgName;
						if(library.Utilities_file.delFile(realPath)) {
							if(library.Utilities_file.delFile(specPath)) {
								int idx = imgPath.indexOf(imgName);
								// Nếu img name có tồn tại trong csdl trước đó
								if(idx != -1) {
									// Nếu img name ở vị trí thứ 2 trở đi
									if(idx > imgPath.indexOf(",")) {
										imgPath.replaceFirst("," + imgName, "");
									} else {
										imgPath.replaceFirst(imgName, "");
									}
									euser.setUser_images(imgPath);
									if(uc.editUser(euser, USER_EDIT_TYPE.GENERAL)) {
										// Xóa ảnh thành công
										response.sendRedirect("/ogn/user/profile");
									} else {
										// xóa ảnh không thành công
										response.sendRedirect("/ogn/user/profile?id="+id+"&err=del");
									}
								}
							} else {
								// xóa ảnh không thành công
								response.sendRedirect("/ogn/user/profile?id="+id+"&err=del");
							}
						} else {
							// xóa ảnh không thành công
							response.sendRedirect("/ogn/user/profile?id="+id+"&err=del");
						}
					} else {
						// xóa ảnh không thành công
						response.sendRedirect("/ogn/user/profile?id="+id+"&err=del");
					}
				} else {
					// Người dùng không có quyền xóa ảnh
					response.sendRedirect("/ogn/user/profile?id="+id+"&err=nopermis");
				}
			} else {
				// Không tồn tại người dùng trong hệ thống
				response.sendRedirect("/ogn/user/list?err=notok");
			}
		}
	}

	private boolean canUserDeleteImage(UserObject user, UserObject euser, String imgName) {
		
		if(user.getUser_id() == euser.getUser_id()) {
			return true;
		}
		if(user.getUser_permission() > euser.getUser_permission()) {
			return true;
		}
		
		
		return false;
	}

}
