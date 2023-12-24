package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.Utilities_const;

public class StorageService {
	private final String FOLDER_PATH = library.Utilities_const.UPLOAD_PATH.label;
	private final String PRODUCT_FOLDER = "product";
	private Path productPath;
	
	public StorageService() {
		try {
			productPath = Path.of(FOLDER_PATH + "/" + PRODUCT_FOLDER);
			if(Files.notExists(productPath)) {
				Files.createDirectories(productPath);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public byte[] getFileFromSystem(String fileName) {

        try {
            byte[] file = Files.readAllBytes(Path.of(FOLDER_PATH + fileName));
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	
	public boolean deleteFileFromFileSystem(String path) {
        boolean isDeleted = false;
        try {
            Files.delete(Path.of(path));
            isDeleted = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return isDeleted;
        }
    }
	
	public boolean uploadFileToSystem(Part file, String filePath) {

        boolean isSaveToFileSystem = false;

        try {
        	if (Files.notExists(Path.of(filePath))) {
				file.write(filePath);
				isSaveToFileSystem = true;
			}
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            return isSaveToFileSystem;
        }
    }
	
	public String getProductFolder() {
        return FOLDER_PATH + "/" + PRODUCT_FOLDER;
    }
	public String getProductPath(String filename) {
		return getProductFolder() + "/" + filename;
	}
	
	public void delFiles(String oldFiles, String updatePath, String realPath) {
		
		if (oldFiles != null && !oldFiles.equalsIgnoreCase("")) {
			// lấy danh sách các đường dẫn file cũ
			String[] listOldFiles = oldFiles.split(";");
			for (String path : listOldFiles) {
				if (path != null) {
					if(!updatePath.contains(path)) {
						path = realPath + path.substring(path.indexOf("/images"));
						boolean delSuccess = deleteFileFromFileSystem(updatePath);
						if(delSuccess) {
							System.out.println("Xoa thanh cong");
						} else {
							System.out.println("Xoa thanh cong");
						}
					}
				}
			}
		}
	}
	
	/* ================= upload file function v2 ================= */
//	public String uploadFileV2(HttpServletRequest request, HttpServletResponse response, String folder,
//			String oldFiles) throws FileNotFoundException {
//		// đường dẫn trên server
//		String uploadPath = request.getServletContext().getRealPath("/img") + File.separator + folder;
//		// đường dẫn đến thư mục cụ thể
//		String specPath = Utilities_const.UPLOAD_PATH.label + "/" + folder;
//		// đường dẫn của các file đã upload
//		String fileUploadedPath = "";
//		// file copy cho file upload để ghi vào đường dẫn cụ thể
//		byte[] fileData;
//
//		File uploadDir = new File(uploadPath);
//		if (!uploadDir.exists()) {
//			uploadDir.mkdir();
//		}
//
//		if (!Files.exists(Path.of(specPath))) {
//			try {
//				Files.createDirectory(Path.of(specPath));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		FileOutputStream fos = null;
//		try {
//			String fileName = null;
//			for (Part part : request.getParts()) {
//				fileName = part.getSubmittedFileName();
//				if (fileName != null && !fileName.equalsIgnoreCase("") && !fileName.isBlank()) {
////					if (oldFiles == null || oldFiles.indexOf(fileName) == -1) {
//					Date date = new Date();
//					long time = date.getTime();
//					String fileType = fileName.substring(fileName.lastIndexOf("."));
//					String newName = time + fileType;
//					String filePath = uploadPath + File.separator + newName;
//					fileUploadedPath += newName + ",";
//					// copy file
//					byte[] fileCopy = part.getInputStream().readAllBytes();
//					fos = new FileOutputStream(new File(specPath + File.separator + newName));
//					part.write(filePath);
//					fos.write(fileCopy);
////					}
//				}
//			}
//
//			if (!fileUploadedPath.equalsIgnoreCase("")) {
//				System.out.println(fileUploadedPath);
//				// Xóa file cũ trong hệ thống và cập nhật đường dẫn mới
//				delFiles(oldFiles, specPath, uploadPath);
//				return fileUploadedPath.substring(0, fileUploadedPath.length() - 1);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (fos != null) {
//				try {
//					fos.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return null;
//
//	}
	
	public static void main(String[] args) {
		String path = "/home/images/product/acer-aspire-3-a315-510p-32ef-i3-nxkdhsv001-thumb-600x600.jpg";
		path = path.substring(path.indexOf("/images"));
		System.out.println(path);
	}
}
