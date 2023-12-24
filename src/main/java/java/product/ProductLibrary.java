package product;

import java.util.ArrayList;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import objects.ProductObject;

public class ProductLibrary {
	public static ArrayList<String> viewProductList(Pair<ArrayList<ProductObject>, Integer> datas,
			Quartet<ProductObject, Short, Byte, PRODUCT_SORT_TYPE> infors) {
		ArrayList<String> view = new ArrayList<String>();
		StringBuilder tmp = new StringBuilder();
		StringBuilder editModal = new StringBuilder();
		StringBuilder stopCellModal = new StringBuilder();
		StringBuilder delModal = new StringBuilder();
		
		ArrayList<ProductObject> pList = datas.getValue0();
		int total = datas.getValue1();
		
		ProductObject similar = infors.getValue0();
		short page = infors.getValue1();
		byte pPerPage = infors.getValue2();
		int count = 1;
		for(ProductObject item : pList) {
			if(count % 2 == 1) {
				tmp.append("<tr data-bs-target=\"#details"+item.getProduct_id()+"\" class=\"table-light\" data-bs-toggle=\"collapse\" aria-expanded=\"false\" aria-controls=\"details"+item.getProduct_id()+"\">");	
			}else {
				tmp.append("<tr data-bs-target=\"#details"+item.getProduct_id()+"\" class=\"\" data-bs-toggle=\"collapse\" aria-expanded=\"false\" aria-controls=\"details"+item.getProduct_id()+"\">");
			}
			tmp.append("<td ><input type=\"checkbox\" class=\"chkProduct\" /></td>");
			tmp.append("<td>" + item.getProduct_name() + "</td>");
			tmp.append("<td>" + item.getPc_name() + "</td>");
			tmp.append("<td>" + item.getProduct_import_price() + "</td>");
			tmp.append("<td>" + item.getProduct_status() + "</td>");
			
			tmp.append("</tr>");
			tmp.append(viewProductDetail(item));
			delModal.append(viewDelProduct(item));
			editModal.append(viewEditModal(item));
			stopCellModal.append(viewStopSellProduct(item));
			count++;
		}

		view.add(tmp.toString());
		String url = "/home/product/product-list.jsp";
		if(similar != null && similar.getProduct_name() != null && !similar.getProduct_name().equalsIgnoreCase("")) {
			url += "?key=" + similar.getProduct_name();
		}
		view.add(ProductLibrary.viewProductListPage(url, page, pPerPage, total).toString());
		view.add(editModal.toString());
		view.add(delModal.toString());
		view.add(stopCellModal.toString());

		return view;
	}

	private static StringBuilder viewProductListPage(String url, short page, byte pPerPage, int total) {
		StringBuilder tmp = new StringBuilder();

		int totalPage = total / pPerPage;

		if ((total % pPerPage != 0)) {
			totalPage++;
		}

		tmp.append("<div class=\"row justify-content-between my-3\">");
		tmp.append("<div id=\"user-list-page-info\" class=\"col-md-6\">");
		tmp.append("<span>Hiển thị " + pPerPage + " bản ghi trên " + total + " bản ghi</span>");
		tmp.append("</div>");
		tmp.append("<div class=\"col-md-6\">");
		tmp.append("<nav aria-label=\"Page navigation example\">");
		tmp.append("<ul class=\"pagination justify-content-end mb-0\">");
		tmp.append("<li class=\"page-item disabled\">");
		if(url.indexOf("=") == -1) {
			tmp.append("<a class=\"page-link\" href=\""+url+"?page="+(page - 1)+"\">Previous</a>");
		} else {
			tmp.append("<a class=\"page-link\" href=\""+url+"&page="+(page - 1)+"\">Previous</a>");
		}
		tmp.append("</li>");

		// Left Current
		String leftCurrent = "";
		int count = 0;

		for (int i = page - 1; i > 0; i--) {
			if(url.indexOf("=") == -1) {
				leftCurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"?page=" + i
						+ "\">" + i + "</a></li>" + leftCurrent;
			} else {
				leftCurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"&page=" + i
						+ "\">" + i + "</a></li>" + leftCurrent;
			}
			
			if (++count >= 2) {
				break;
			}
		}

		if (page >= 4) {
			leftCurrent = "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">...</a></li>" + leftCurrent;
			// if (page>1) {
			// leftCurrent = ("<li class=\"page-item\"><a class=\"page-link\"
			// href=\""+()+"\"><span aria-hidden=\"true\">&laquo;</span></a></li>") +
			// leftCurrent;
			// }
		}
		tmp.append(leftCurrent);

		tmp.append("<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">" + page
				+ "</a></li>");

		// Right Current
		String rightCurrent = "";
		count = 0;
		for (int i = page + 1; i <= totalPage; i++) {
			if(url.indexOf("=") == -1) {
				rightCurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"?page=" + i
						+ "\">" + i + "</a></li>";
			} else {
				rightCurrent += "<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"&page=" + i
						+ "\">" + i + "</a></li>";
			}
			if (++count >= 2) {
				break;
			}
		}

		if (totalPage > page + 4) {
			rightCurrent += "<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\">...</a></li>";
		}
		tmp.append(rightCurrent);

		tmp.append("<li class=\"page-item\">");
		if(url.indexOf("=") == -1) {
			tmp.append("<a class=\"page-link\" tabindex=\"1\" href=\""+url+"?page=" + (page + 1)
					+ "\">Next</a>");
		} else {
			tmp.append("<a class=\"page-link\" tabindex=\"1\" href=\""+url+"&page=" + (page + 1)
					+ "\">Next</a>");
		}
		tmp.append("</li>");
		tmp.append("</ul>");
		tmp.append("</nav>");
		tmp.append("</div>");
		tmp.append("</div>");

		return tmp;
	}
	
	private static StringBuilder viewDelProduct(ProductObject item) {
		StringBuilder tmp = new StringBuilder();
		String title;
		
		if(item.isProduct_deleted()) {
			title = "Xóa vĩnh viễn";
		} else {
			title = "Xóa sản phẩm";
		}
		tmp.append("<div class=\"modal fade\" id=\"delPro"+item.getProduct_id()+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"productLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\" role=\"document\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h5 class=\"modal-title text-danger\" id=\"productLabel\">"+title+"</h5>");
		tmp.append("<button type=\"button\" class=\"close\" data-bs-dismiss=\"modal\" aria-label=\"Close\">");
		tmp.append("<span aria-hidden=\"true\">&times;</span>");
		tmp.append("</button>");
		tmp.append("</div>");
		tmp.append("<div class=\"modal-body\">");
		if(item.isProduct_deleted()) {
			tmp.append("<p>Bạn có chắc chắn muốn xóa vĩnh viễn sản phẩm <b>").append(item.getProduct_name()).append(" ("+item.getPc_name()+")</b></p>");
			tmp.append("<p class=\"txt-alert\">Sản phẩm không thể phục hồi được nữa</p>");
		} else {
			tmp.append("Bạn có chắc chắn muốn xóa sản phẩm <b>").append(item.getProduct_name()).append(" ("+item.getPc_name()+")</b></br>");
			tmp.append("<p class=\"text-danger\">Hệ thống tạm thời lưu vào thùng rác, sản phẩm có thể phục hồi trong vòng 30 ngày</p>");	
		}
		tmp.append("</div>");
		tmp.append("<div class=\"modal-footer\">");
		if(item.isProduct_deleted()) {
			tmp.append("<a href=\"/home/product/dr?id="+item.getProduct_id() + "\" class=\"btn btn-danger\">Xóa vĩnh viễn</a>");
		} else {
			// dr: delete + restore
			tmp.append("<a href=\"/home/product/dr?id="+item.getProduct_id()+"&t\" class=\"btn btn-danger\">Xóa</a>");		
		}
		tmp.append("<button type=\"button\" class=\"btn btn-light\" data-bs-dismiss=\"modal\">Hủy</button>");
		tmp.append("</div>");
		
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		return tmp;
	}
	private static StringBuilder viewEditModal(ProductObject item) {
		StringBuilder tmp = new StringBuilder();
		
		// edit Modal
		tmp.append("<div class=\"modal fade\" id=\"editModal"+item.getProduct_id()+"\">");
		tmp.append("<div class=\"modal-dialog modal-xl\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<form method=\"post\" action=\"/home/product/edit\" enctype=\"multipart/form-data\">");
		
		tmp.append("<div class=\"modal-header pb-0\" style=\"border-bottom: none;\">");
		tmp.append("<h5 class=\"modal-title px-2\" style=\"font-weight: bold; font-size: 18px;\">Cập nhật thông tin</h5>");
		tmp.append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"modal-body px-0 pt-1\">");
		
		tmp.append("<!-- Bordered Tabs -->");
		tmp.append("<ul class=\"nav nav-tabs nav-underline\" id=\"borderedTab\" role=\"tablist\">");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<a class=\"nav-link px-4 py-1 active\" id=\"home-tab\" data-bs-toggle=\"tab\" href=\"#home-edit"+item.getProduct_id()+"-tab\"");
		tmp.append(" role=\"tab\" aria-controls=\"Thông tin\" aria-selected=\"true\">Thông tin</a>");
		tmp.append("</li>");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<a class=\"nav-link px-4 py-1\" id=\"home-tab\" data-bs-toggle=\"tab\" href=\"#desc-edit-"+item.getProduct_id()+"-tab\"");
		tmp.append(" role=\"tab\" aria-controls=\"Thông tin\" aria-selected=\"true\">Mô tả chi tiết</a>");
		tmp.append("</li>");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<a class=\"nav-link px-4 py-1\" id=\"home-tab\" data-bs-toggle=\"tab\" href=\"#ingredient-edit-"+item.getProduct_id()+"-tab\"");
		tmp.append(" role=\"tab\" aria-controls=\"Thông tin\" aria-selected=\"true\">Thành phần</a>");
		tmp.append("</li>");
		tmp.append("</ul>");
		
		tmp.append("<div class=\"tab-content px-3 pt-1\" id=\"borderedTabContent\">");
		
		tmp.append("<div class=\"tab-pane fade active show\" id=\"home-edit"+item.getProduct_id()+"-tab\" role=\"tabpanel\" aria-labelledby=\"home-tab\">");
		tmp.append("<div class=\"row\">");
		
		tmp.append("<div class=\"col-lg-6\">");
		
		tmp.append("<div class=\"row mt-2\">");
		tmp.append("<div class=\"col-3 fieldName\">Tên hàng</div>"); // col-3
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<input type=\"text\" class=\"\" name=\"txtProductName\" value=\""+item.getProduct_name()+"\">");
		tmp.append("<div class=\"invalid-feedback\">Hãy nhập tên hàng</div>");
		tmp.append("</div>");// col-9
		tmp.append("</div>"); // row
		
		tmp.append("<div class=\"row mt-2\">");
		tmp.append("<div class=\"col-3 fieldName\">Mã vạch</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<input type=\"text\" class=\"\" name=\"txtProductCode\" value=\""+item.getProduct_bar_code()+"\" placeholder=\"Mã hàng sinh tự động\">");
		tmp.append("</div>"); // col-9
		tmp.append("</div>"); // row
		
		tmp.append("<div class=\"row mt-2\">");
		tmp.append("<div class=\"col-3 fieldName\">Nhóm hàng</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<select name=\"slcProductCat\" id=\"\" class=\"select2\" data-type=\"cat\" data-parent=\".modal\" data-item-id=\""+item.getProduct_id()+"\">");
		tmp.append("<option value=\"0\">--Lựa chọn--</option>");
		if(item.getPc_name() != null && !item.getPc_name().isBlank()) {
			tmp.append("<option value=\""+item.getPc_id()+"\" selected>"+item.getPc_name()+"</option>");					
		}
		tmp.append("</select>");
		tmp.append("</div>"); // col-9
		tmp.append("</div>");// row
		
		tmp.append("<div class=\"row mt-2 mb-2 ps-2\">");
		tmp.append("<div class=\"col-10\">");
		tmp.append("<div class=\"row img-upload-section\">");
		int count = 0;
		if(item.getProduct_images() != null && !item.getProduct_images().isBlank()) {
			String[] imgPath = item.getProduct_images().trim().split(";");
			for (String path : imgPath) {
				path = path.trim();
				if(!path.isBlank()) {
					tmp.append("<div class=\"col-3 px-1\">");
					tmp.append("<img src=\""+path+"\" alt=\"\" class=\"product-img img-fluid\">");
					tmp.append("<input type=\"text\" name=\"oldImg"+count+"\" value=\""+path+"\" class=\"fileUpload\" data-gallery=\"productImgsUpGallery\" hidden/>");
					tmp.append("<button class=\"btn btn-danger rounded-circle btn-remove-img\" onclick=\"handlClickRemoveBtn\">-</button>");
					tmp.append("</div>"); // col-3
					
					count++;
				}
			}
		}
		if(count < 5) {
			for (int i = count; i < 4; i++) {
				tmp.append("<div class=\"col-3 px-1\">");
				tmp.append("<img src=\"/home/images/default-product-img.jpg\" alt=\"\" class=\"product-img\">");
				tmp.append("<input type=\"file\" name=\"uploadImg"+i+"\" class=\"fileUpload\" accept=\"image/*\" hidden/>");
				tmp.append("</div>"); // col-3
			}
		}
		
		tmp.append("<input type=\"text\" name=\"from-db\" value=\""+item.getProduct_images()+"\" class=\"fileUpload\" data-gallery=\"productImgsUpGallery\" hidden/>");
		
		tmp.append("</div>");// img-upload-section
		tmp.append("</div>");// col-10
		
		tmp.append("<div class=\"col-2 d-flex justify-content-center align-items-center\">");
		tmp.append("<button class=\"button-add-img btn btn-outline-secondary\">+</button>"); 
		tmp.append("</div>"); // col-2
		tmp.append("</div>"); // row

		tmp.append("</div>"); // col-lg-6
		
		tmp.append("<div class=\"col-lg-6\">");
		tmp.append("<div class=\"row mt-2\">");
		tmp.append("<div class=\"col-3 fieldName\">Giá nhập</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\"><input type=\"text\" class=\"\" name=\"txtProductImportPrice\" value=\""+item.getProduct_import_price()+"\">");
		tmp.append("<div class=\"invalid-feedback\"></div>");
		tmp.append("</div>");
		tmp.append("</div>");
		tmp.append("<div class=\"row mt-2\">");
		tmp.append("<div class=\"col-3 fieldName\">Giá bán</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\"><input type=\"text\" class=\"\" name=\"txtProductSellPrice\" value=\""+item.getProduct_sell_price()+"\">");
		tmp.append("<div class=\"invalid-feedback\">Hãy nhập hộp thư cho tài khoản</div>");
		tmp.append("</div>"); // col-9
		tmp.append("</div>"); // row
		
		tmp.append("</div>"); // col-lg-6
		tmp.append("</div>"); // row
		
		tmp.append("</div>"); // tab-pane
		
		tmp.append("<div class=\"tab-pane fade\" id=\"desc-edit-"+item.getProduct_id()+"-tab\" role=\"tabpanel\" aria-labelledby=\"profile-tab\">");
		/* card inventory */
//		tmp.append("<div class=\"card mt-2 mb-3\">");
//		tmp.append("<div class=\"card-header bg-secondary-subtle px-2 py-2\">");
//		tmp.append("Định mức tồn kho");
//		tmp.append("</div>");
//		tmp.append("<div class=\"card-body\">");
//		tmp.append("<div class=\"row\">");
//		tmp.append("<div class=\"col-md-6\">");
//		tmp.append("<div class=\"row\">");
//		tmp.append("<div class=\"col-4\">");
//		tmp.append("<label class=\"fieldName\" for=\"minInven"+item.getProduct_id()+"\">Ít nhất</label>");
//		tmp.append("</div>"); // col-3
//		
//		tmp.append("<div class=\"col-8\">");
//		tmp.append("<input name=\"minInven\" class=\"field-value w-100\" id=\"minInven"+item.getProduct_id()+"\" value=\""+item.getMaxInventory()+"\"/>");
//		tmp.append("</div>"); // col-8
//		tmp.append("</div>"); // row
//		tmp.append("</div>"); // col-md-6
//		
//		tmp.append("<div class=\"col-md-6\">");
//		tmp.append("<div class=\"row\">");
//		tmp.append("<div class=\"col-4\">");
//		tmp.append("<label class=\"fieldName \" for=\"maxInven"+item.getProduct_id()+"\">Nhiều nhất</label>");
//		tmp.append("</div>"); // col-3
//		
//		tmp.append("<div class=\"col-8\">");
//		tmp.append("<input name=\"maxInven\" class=\"field-value w-100\" id=\"maxInven"+item.getProduct_id()+"\" value=\""+item.getMaxInventory()+"\"/>");
//		tmp.append("</div>"); // col-8
//		tmp.append("</div>"); //row
//		tmp.append("</div>"); // col-md-6
//		tmp.append("</div>"); // row
//		tmp.append("</div>"); // card-body
//		tmp.append("</div>"); // card
//		
		// Card description
		tmp.append("<div class=\"card mt-2 mb-3\">");
		tmp.append("<div class=\"card-header bg-secondary-subtle px-2 py-2 fieldName\">");
		tmp.append("Mô tả");
		tmp.append("</div>");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<textarea id=\"txtProductDesc"+item.getProduct_id()+"\" class=\"form-control\" height=\"60\" name=\"txtProductDesc\">"+library.Utilities.decode(item.getProduct_desc())+"</textarea>");
		tmp.append("</div>");// card-body
		tmp.append("</div>"); // card
		
		tmp.append("<div class=\"card mt-2 mb-3\">");
		tmp.append("<div class=\"card-header bg-secondary-subtle px-2 py-2 fieldName\">");
		tmp.append("Ghi chú");
		tmp.append("</div>");
		tmp.append("<div class=\"card-body\">");
		tmp.append("<textarea class=\"form-control\" height=\"60\" value=\"\" name=\"txtProductNote\">"+library.Utilities.decode(item.getProduct_notes())+"</textarea>");
		tmp.append("</div>");
		tmp.append("</div>"); // card
		
		tmp.append("</div>"); // tab-pane
		
		
		tmp.append("<div class=\"tab-pane fade\" id=\"ingredient-edit-"+item.getProduct_id()+"-tab\" role=\"tabpanel\" aria-labelledby=\"contact-tab\">");
		tmp.append("Saepe animi et soluta ad odit soluta sunt. Nihil quos omnis");
		tmp.append("animi debitis cumque. Accusantium quibusdam perspiciatis qui qui omnis magnam. Officiis");
		tmp.append("accusamus impedit molestias nostrum veniam. Qui amet ipsum iure. Dignissimos fuga tempore");
		tmp.append("dolor.");
		tmp.append("</div>");// tab-pane
		
		tmp.append("</div>"); // tab-content
		
		tmp.append("<input name=\"idForPost\" value=\""+item.getProduct_id()+"\" hidden/>");
		tmp.append("</div>"); // modal-body
		
		tmp.append("<div class=\"modal-footer border border-top border-secondary-subtle\">");
		tmp.append("<button type=\"submit\" class=\"btn btn-primary btnSave\"><i class=\"fa-solid fa-floppy-disk\"></i>Lưu</button>");
		tmp.append("<button type=\"button\" class=\"btn btn-secondary btnDiscard\" data-bs-dismiss=\"modal\"><i class=\"fa-solid fa-ban\"></i>Bỏ qua</button>");
		tmp.append("</div>"); // modal-footer
		
		tmp.append("</form>");
		tmp.append("</div>"); // modal-content
		tmp.append("</div>"); // modal-xl
		tmp.append("</div><!-- End Basic Modal-->");
		
		return tmp;
	}
	
	public static StringBuilder viewProductDetail(ProductObject item) {
		StringBuilder tmp = new StringBuilder();
		String btnConfirmLbl;
		String btnConfirmBG;
		if(item.isStoped_sell()) {
			btnConfirmLbl = "<i class=\"fa-solid fa-dollar-sign\"></i>Mở Kinh doanh";
			btnConfirmBG = "btn btn-secondary";
		} else {
			btnConfirmLbl = "<i class=\"fa-solid fa-ban\"></i> Ngừng kinh doanh";
			btnConfirmBG = "btn btn-danger";
		}
		
		tmp.append("<tr id=\"details"+item.getProduct_id()+"\" class=\"collapse smooth-collapse border border-primary border-top-0\">");
		tmp.append("<td colspan=\"99\" class=\"p-0 \">");
		tmp.append("<!-- Bordered Tabs -->");
		tmp.append("<div class=\"card shadow-none rounded-0 mb-0\">");
		tmp.append("<div class=\"card-header bg-primary-subtle px-3 py-0 \">");
		tmp.append("<h5 class=\"card-title pt-3 pb-2 text-primary\">Thông tin sản phẩm "+item.getProduct_name()+"</h5>");
		tmp.append("<ul class=\"nav nav-tabs bg-primary-subtle\" id=\"borderedTab\" role=\"tablist\">");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<button class=\"nav-link active px-4 py-1\" id=\"home-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#bordered-infors\" type=\"button\" role=\"tab\" aria-controls=\"home\" aria-selected=\"true\">Thông tin</button>");
		tmp.append("</li>");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<button class=\"nav-link px-4 py-1\" id=\"profile-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#store-card-tab\" type=\"button\" role=\"tab\" aria-controls=\"profile\" aria-selected=\"false\">Thẻ kho</button>");
		tmp.append("</li>");
		tmp.append("<li class=\"nav-item\" role=\"presentation\">");
		tmp.append("<button class=\"nav-link px-4 py-1\" id=\"contact-tab\" data-bs-toggle=\"tab\" data-bs-target=\"#inventory-tab\" type=\"button\" role=\"tab\" aria-controls=\"contact\" aria-selected=\"false\">Tồn kho</button>");
		tmp.append("</li>");
		tmp.append("</ul>"); // nav-tabs
		tmp.append("</div>"); //card-header-custom
		tmp.append("<div class=\"card-body px-0\">");
		
		tmp.append("<div class=\"tab-content px-4\" id=\"borderedTabContent\">");
		tmp.append("<div class=\"tab-pane fade show active\" id=\"bordered-infors\" role=\"tabpanel\" aria-labelledby=\"home-tab\">");
		tmp.append("<div class=\"row\">");
		
		tmp.append("<div class=\"col-md-4\">");
		tmp.append("<div class=\"row mt-3 mb-2\">");
		tmp.append("<div id=\"carouselProductImgs"+item.getProduct_id()+"\" class=\"carousel slide carousel-fade\" data-bs-ride=\"carousel\">");
		tmp.append("<div class=\"carousel-item active bg-lightblue\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_1.jpg\" alt=\"\" class=\"d-block w-100 glightbox \" data-gallery=\"productImgsGallery"+item.getProduct_id()+"\">");
		tmp.append("</div>");
		tmp.append("<div class=\"carousel-item bg-lightblue\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_2.jpg\" alt=\"\" class=\"d-block w-100 glightbox \" data-gallery=\"productImgsGallery"+item.getProduct_id()+"\">");
		tmp.append("</div>");
		tmp.append("<div class=\"carousel-item bg-lightblue\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_3.jpg\" alt=\"\" class=\"d-block w-100 glightbox \" data-gallery=\"productImgsGallery"+item.getProduct_id()+"\">");
		tmp.append("</div>");
		tmp.append("<div class=\"carousel-item bg-lightblue\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_4.jpg\" alt=\"\" class=\"d-block w-100 glightbox \" data-gallery=\"productImgsGallery"+item.getProduct_id()+"\">");
		tmp.append("</div>");
		tmp.append("<div class=\"carousel-item bg-lightblue\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_5.jpg\" alt=\"\" class=\"d-block w-100 glightbox \" data-gallery=\"productImgsGallery"+item.getProduct_id()+"\">");
		tmp.append("</div>");
		tmp.append("<div class=\"carousel-item bg-lightblue\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_6.jpg\" alt=\"\" class=\"d-block w-100 glightbox \" data-gallery=\"productImgsGallery"+item.getProduct_id()+"\">");
		tmp.append("</div>");
		tmp.append("</div>"); 
		
		tmp.append("<div class=\"d-flex ms-0 gap-1 ps-0 slide-control\">");
		tmp.append("<div class=\"slide-btn-img bg-lightblue\" data-bs-target=\"#carouselProductImgs"+item.getProduct_id()+"\" data-bs-slide-to=\"0\" aria-current=\"true\" aria-label=\"ảnh 1\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_1.jpg\" alt=\"\" class=\"w-100 slide-btn-img shadow\" >");			
		tmp.append("</div>");
		tmp.append("<div class=\"slide-btn-img bg-lightblue\" data-bs-target=\"#carouselProductImgs"+item.getProduct_id()+"\" data-bs-slide-to=\"1\" aria-label=\"ảnh 2\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_2.jpg\" alt=\"\" class=\"w-100 shadow\" >");			
		tmp.append("</div>");
		tmp.append("<div class=\"slide-btn-img bg-lightblue\" data-bs-target=\"#carouselProductImgs"+item.getProduct_id()+"\" data-bs-slide-to=\"2\" aria-label=\"ảnh 3\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_3.jpg\" alt=\"\" class=\" w-100 slide-btn-img shadow\" >");			
		tmp.append("</div>");
		tmp.append("<div class=\"slide-btn-img bg-lightblue\" data-bs-target=\"#carouselProductImgs"+item.getProduct_id()+"\"  data-bs-slide-to=\"3\" aria-label=\"ảnh 4\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_4.jpg\" alt=\"\" class=\" w-100 slide-btn-img shadow\" >");			
		tmp.append("</div>");
		tmp.append("<div class=\"slide-btn-img bg-lightblue\" data-bs-target=\"#carouselProductImgs"+item.getProduct_id()+"\" data-bs-slide-to=\"4\" aria-current=\"true\" aria-label=\"ảnh 1\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_5.jpg\" alt=\"\" class=\"w-100 slide-btn-img shadow\" >");			
		tmp.append("</div>");
		tmp.append("<div class=\"slide-btn-img bg-lightblue\" data-bs-target=\"#carouselProductImgs\" data-bs-slide-to=\"5\" aria-current=\"true\" aria-label=\"ảnh 1\">");
		tmp.append("<img src=\"/ogn/img/product/apple/Image_6.jpg\" alt=\"\" class=\"w-100 slide-btn-img shadow\" >");			
		tmp.append("</div>");
		tmp.append("</div>");  // slide-control
		tmp.append("</div>"); // row
		tmp.append("</div>");// col-lg-4
		
		tmp.append("<div class=\"col-md-8\">");
		tmp.append("<div class=\"row\">");
		tmp.append("<div class=\"col-md-6\">");
		tmp.append("<div class=\"row mt-2 border-secondary-subtle border-bottom pb-1 me-2\">");
		tmp.append("<div class=\"col-3 fieldName\">");
		tmp.append("Tên hàng");
		tmp.append("</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<p>"+item.getProduct_name()+"</p>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mt-3 border-secondary-subtle border-bottom pb-1 me-2\">");
		tmp.append("<div class=\"col-3 fieldName\">");
		tmp.append("Mã vạch");
		tmp.append("</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<p>"+item.getProduct_bar_code()+"</p>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mt-3 border-secondary-subtle border-bottom pb-1 me-2\">");
		tmp.append("<div class=\"col-3 fieldName\">");
		tmp.append("Nhóm hàng");
		tmp.append("</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<p>"+item.getPc_name()+"</p>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mt-3 border-secondary-subtle border-bottom pb-1 me-2\">");
		tmp.append("<div class=\"col-3 fieldName\">");
		tmp.append("Thương hiệu");
		tmp.append("</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<p>"+item.getPc_name()+"</p>");
		tmp.append("</div>");
		tmp.append("</div>"); // row
		tmp.append("</div>"); // col-md-6
		
		tmp.append("<div class=\"col-md-6\">");
		tmp.append("<div class=\"row mt-2 border-secondary-subtle border-bottom pb-1 me-2\">");
		tmp.append("<div class=\"col-3 fieldName\">");
		tmp.append("Giá vốn");
		tmp.append("</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<p>"+item.getProduct_import_price()+"</p>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mt-3 border-secondary-subtle border-bottom pb-1 me-2\">");
		tmp.append("<div class=\"col-3 fieldName\">");
		tmp.append("Giá bán");
		tmp.append("</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<p>"+item.getProduct_sell_price()+"</p>");
		tmp.append("</div>");
		tmp.append("</div>");
		
		tmp.append("<div class=\"row mt-3 border-secondary-subtle border-bottom pb-1 me-2\">");
		tmp.append("<div class=\"col-3 fieldName\">");
		tmp.append("Tồn kho");
		tmp.append("</div>");
		tmp.append("<div class=\"col-9 field-value ps-1 pe-3\">");
		tmp.append("<p>"+item.getPc_created_author_id()+"</p>");
		tmp.append("</div>"); // col-9
		tmp.append("</div>"); // row
		
		tmp.append("</div>"); // col-md-6
		
		tmp.append("</div>"); // row
		tmp.append("</div>"); // col-lg-8
		tmp.append("</div>"); // row
		tmp.append("</div>"); // tab-pane
		
		tmp.append("<div class=\"tab-pane fade\" id=\"store-card-tab\" role=\"tabpanel\" aria-labelledby=\"profile-tab\">");
		tmp.append("Nesciunt totam et. Consequuntur magnam aliquid eos nulla dolor iure eos quia. Accusantium distinctio omnis et atque fugiat. Itaque doloremque aliquid sint quasi quia distinctio similique. Voluptate nihil recusandae mollitia dolores. Ut laboriosam voluptatum dicta.");
		tmp.append("</div>"); // tab-pane
		tmp.append("<div class=\"tab-pane fade\" id=\"inventory-tab\" role=\"tabpanel\" aria-labelledby=\"contact-tab\">");
		tmp.append("Saepe animi et soluta ad odit soluta sunt. Nihil quos omnis animi debitis cumque. Accusantium quibusdam perspiciatis qui qui omnis magnam. Officiis accusamus impedit molestias nostrum veniam. Qui amet ipsum iure. Dignissimos fuga tempore dolor.");
		tmp.append("</div>"); // tab-pane
		tmp.append("</div>"); // tab-content
		
		tmp.append("<div class=\"modal-footer border-top mt-3 pt-2\">");
		tmp.append("<button type=\"button\" class=\"btn btn-primary me-2\" data-bs-toggle=\"modal\" data-bs-target=\"#editModal"+item.getProduct_id()+"\"><i class=\"fa-solid fa-floppy-disk\"></i>Cập nhật</button>");
		tmp.append("<button type=\"button\" class=\""+btnConfirmBG+" btnStopSell me-2\" data-bs-toggle=\"modal\" data-bs-target=\"#stopSell"+item.getProduct_id()+"\">"+btnConfirmLbl+"</button>");
		tmp.append("<button type=\"button\" class=\"btn btn-danger btnRemove me-4\" data-bs-toggle=\"modal\" data-bs-toggle=\"modal\" data-bs-target=\"#delPro"+item.getProduct_id()+"\"><i class=\"fa-solid fa-trash-can\"></i></i>Xóa</button>");
		tmp.append("</div>"); // modal-footer
		
		tmp.append("</div>"); // card-body
		tmp.append("</div>"); // card
		tmp.append("</td>");
		tmp.append("</tr>");
		
		return tmp;
	}
	private static StringBuilder viewStopSellProduct(ProductObject item) {
		StringBuilder tmp = new StringBuilder();
		String title;
		
		if(item.isStoped_sell()) {
			title = "Mở kinh doanh";
		} else {
			title = "Ngừng kinh doanh";
		}
		tmp.append("<div class=\"modal fade\" id=\"stopSell"+item.getProduct_id()+"\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"stopSellModalLabel\" aria-hidden=\"true\">");
		tmp.append("<div class=\"modal-dialog\">");
		tmp.append("<div class=\"modal-content\">");
		tmp.append("<div class=\"modal-header\">");
		tmp.append("<h5 class=\"modal-title text-danger\" id=\"stopSellModalLabel\">"+title+"</h5>");
		tmp.append("<button type=\"button\" class=\"close\" data-bs-dismiss=\"modal\" aria-label=\"Close\">");
		tmp.append("<span aria-hidden=\"true\">&times;</span>");
		tmp.append("</button>");
		tmp.append("</div>"); //modal-header
		tmp.append("<div class=\"modal-body\">");
			tmp.append("<p>Bạn có chắc chắn muốn ngừng kinh doanh sản phẩm "+item.getProduct_name()+" trên hệ thống?</p>");
			tmp.append("<p class=\"txt-alert\">Lưu ý:\r\n"
					+ "-Thông tin tồn kho và lịch sử giao dịch vẫn được giữ.\r\n"
					+ "-Hàng hóa quy đổi liên quan cũng sẽ ngừng kinh doanh.</p>");
		tmp.append("</div>"); //modal-body
		tmp.append("<div class=\"modal-footer\">");
			// sr: stop sell + restore
		if(item.isStoped_sell()) {
			tmp.append("<a href=\"/home/product/sr?id="+item.getProduct_id()+"&s&r\" class=\"btn btn-danger\">Xác nhận</a>");
		} else {
			tmp.append("<a href=\"/home/product/sr?id="+item.getProduct_id()+"&s\" class=\"btn btn-danger\">Xác nhận</a>");	
		}
			tmp.append("<button type=\"button\" class=\"btn btn-light\" data-bs-dismiss=\"modal\">Hủy</button>");
		tmp.append("</div>"); // modal-footer
		
		tmp.append("</div>");// modal-content
		tmp.append("</div>"); // modal-dialog
		tmp.append("</div>"); // modal
		 
		return tmp;
	}
}