package crawl;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServlet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import connection.ConnectionPool;
import connection.ConnectionPoolImpl;
import library.Utilities;
import library.Utilities_date;
import library.Utilities_file;
import objects.ProductObject;
import product.ProductService;
import product.ProductControl;
import product.ProductServiceImpl;

public class Crawl extends HttpServlet {
	
	private static String pathStoreImgs = "D:\\learning\\ki 5\\1soCNPTPM\\BTL-1SOCNPTPM\\code\\src\\main\\webapp\\images\\product";
	private String ab;
	
	public Crawl() {
		super();
	}
	public static void main(String[] args) {
		ArrayList<ProductObject> productList = new ArrayList<>();
		System.setProperty("webdriver.chrome.driver", "D:\\setup\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.thegioididong.com/laptop"); // Điều chỉnh URL của trang web
		WebElement btnMore = driver.findElement(By.cssSelector(".view-more [href]"));
		btnMore.click();
		// Đợi để trang web tải dữ liệu mới
        try {
            Thread.sleep(1000); // Thời gian đợi tùy theo tốc độ tải dữ liệu
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> productElementLinks = driver.findElements(By.cssSelector(".__cate_44 > .main-contain "));
        
        FileOutputStream file;
        BufferedOutputStream os;
		try {
			Random rd = new Random();
			int count = 1;
			file = new FileOutputStream("C:\\Users\\pc\\Dropbox\\PC\\Downloads\\product_data.xlsx");
			os = new BufferedOutputStream(file);
			for(WebElement e : productElementLinks) {
	        	WebElement h3 = e.findElement(By.cssSelector("h3"));
	        	WebElement img = e.findElement(By.cssSelector(".thumb"));
	        	WebElement price = e.findElement(By.cssSelector(".price"));
	        	ProductObject product = new ProductObject();
	        	product.setProduct_name(h3.getText());
	        	product.setProduct_category_id((short)rd.nextInt(20));
	        	product.setProduct_price(convertPriceStringToInt(price.getText()));
	        	product.setProduct_images(downloadImg(img.getAttribute("data-src")));
	        	product.setProduct_last_modified(Utilities_date.getDate());
	        	product.setProduct_status((byte) (count % 2));
	        	if(count % 2 == 0) {
	        		product.setProduct_deleted(false);
	        	} else {
	        		product.setProduct_deleted(true);
	        	}
	        	
//	        	p.addProduct(product);
	        	productList.add(product);
	        	count++;
	        	System.out.println(product.toString());
	        }
	     // Đóng trình duyệt sau khi hoàn thành
	        Utilities_file.writeProductExcel(os, productList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        driver.quit();
	}
	public static String downloadImg(String src) {
		System.setProperty("webdriver.chrome.driver", "D:\\setup\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Chạy trình duyệt ẩn danh

        WebDriver driver1 = new ChromeDriver(options);
		driver1.get(src);
		String imgName = src.substring(src.lastIndexOf("/") + 1);
		// Tải ảnh từ đường dẫn và lưu vào máy tính
        try {
        	String imgpath = Crawl.pathStoreImgs + "\\"+imgName;
            java.io.BufferedInputStream in = new java.io.BufferedInputStream(new java.net.URL(src).openStream());
            java.io.FileOutputStream fos = new java.io.FileOutputStream(imgpath);

            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fos.write(dataBuffer, 0, bytesRead);
            }

            fos.close();
            in.close();
            
            System.out.println("Ảnh đã được tải và lưu thành công!");
            return imgpath;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi khi tải và lưu ảnh.");
        }
        driver1.quit();

        return null;
	}
	public static int convertPriceStringToInt(String priceString) {
		int idx = priceString.lastIndexOf("[0-9]");
		if(idx != -1) {
			priceString = priceString.substring(0, idx + 1);	
		}
        // Loại bỏ ký tự không phải số (nếu có)
        priceString = priceString.replaceAll("[^0-9]", "");
        
        // Chuyển đổi chuỗi thành số nguyên
        int priceInt = Integer.parseInt(priceString);
//        System.out.println(priceString);
        return priceInt;
    }
//	public void addProducts(ArrayList<ProductObject> list) {
//		ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
//		ProductControl pc = new ProductControl(cp);
//		if(cp == null) {
//			getServletContext().setAttribute("CPool", pc.getCP());		
//		}
//		for (ProductObject productObject : list) {
//			if(pc.addProduct(productObject)) {
//				System.out.println("Thêm thành công");
//			} else {
//				System.out.println("Thêm k thành công");
//			}
//		}
//		
//		pc.releaseConnection();	
//	}
}
