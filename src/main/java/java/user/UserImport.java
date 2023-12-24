package user;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.javatuples.Pair;
import org.javatuples.Quartet;

import connection.ConnectionPool;
import library.Utilities_file;
import objects.EmployeeObject;
import objects.UserObject;

/**
 * Servlet implementation class UserImport
 */
@WebServlet("/UserImport")
@MultipartConfig()
public class UserImport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserImport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//	// Initialize the BIRT Report Engine
//        EngineConfig config = new EngineConfig();
//        config.setEngineHome("/home/WEB-INF/lib/rg.eclipse.birt.report.engine_4.13.0.v202303021806.jar");
//        
//        try {
//            Platform.startup(config);
//            IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
//            IReportEngine reportEngine = factory.createReportEngine(config);
//            
//            // Load the report design file
//            IReportRunnable design = reportEngine.openReportDesign("/home/workplace/new_report.rptdesign");
//            
//            // Create a task to run and render the report
//            IRunAndRenderTask task = reportEngine.createRunAndRenderTask(design);
//            
//            // Set the render options
//            RenderOption options = new HTMLRenderOption();
//            options.setOutputFormat("HTML");
//            options.setOutputStream(response.getOutputStream());
//            task.setRenderOption(options);
//            
//            // Execute the report task
//            task.run();
//            task.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Platform.shutdown();
//        }
//	    
		
//		//Lấy dữ liệu xác nhận exportfile
//		String ekey = request.getParameter("ekey");
//		String saveKey = (ekey!=null && !ekey.equalsIgnoreCase("")) ? ekey.trim(): "";
//		
//		UserObject similar = new UserObject();
		
		ConnectionPool cp = (ConnectionPool)getServletContext().getAttribute("CPool");
		
		UserControl uc = new UserControl(cp);
		
		if (cp==null) {
			getServletContext().setAttribute("CPool", uc.getCP());		
		}
		
	    // Get the uploaded Excel file from the request
        Part filePart = request.getPart("import-excel");
        
        // Initialize an InputStream to read the uploaded file
        InputStream fileContent = filePart.getInputStream();
        String fileName = filePart.getName();
        BufferedInputStream buffer = new BufferedInputStream(fileContent);
		
		ArrayList<Object> result = Utilities_file.readExcelFile(buffer,"user");
		
		result.forEach(object->{
			UserObject user = (UserObject) object;
			uc.addUser(user);
		}); 
		
		fileContent.close();
		uc.releaseConnection();	 
		response.sendRedirect("/home/user/user-list.jsp");        
	}

}
