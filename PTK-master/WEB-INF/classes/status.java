

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.*;
import java.util.Date;



public class status extends HttpServlet implements java.io.Serializable{
	public HashMap<String , String[]> statushm = new HashMap<String , String[]>();
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {


HashMap<String , String[]> e = null;
File f = new File("statusfile.ser");
if(f.isFile()) { 
      try
      {
         FileInputStream fileIn = new FileInputStream("statusfile.ser");
         ObjectInputStream infil = new ObjectInputStream(fileIn);
         e = (HashMap<String , String[]>) infil.readObject();
         infil.close();
         fileIn.close();
		
      }catch(IOException i)
      {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c)
      {
       
         c.printStackTrace();
         return;
      }
if(e != null)
	  {
statushm = e;
	  }
}
String name = request.getParameter("name");
Date date = new Date();
String dates = " -at " + date;
String all = date + "!" +name;
String status = request.getParameter("status");
String[] data = { name, status, dates};
 statushm.put(all,data);
 
 

 try
      {
         FileOutputStream fileOut = new FileOutputStream("statusfile.ser");
         ObjectOutputStream outfil = new ObjectOutputStream(fileOut);
         outfil.writeObject(statushm);
         outfil.close();
         fileOut.close();
       
      }catch(IOException i)
      {
          i.printStackTrace();
      }
/* response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    String title = "Status Update";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY>\n" +
		        "<H3 >Status posted successfully.</H3>\n" +
	"<h4> User Name : " + request.getParameter("name") + "<h4>" +
	"<h4> Status : " + request.getParameter("status") + "<h4>" );

     String homeURL =
          response.encodeURL("status.jsp");
        
        out.println
          ("</TABLE>\n" +
           "<FORM ACTION=\"" + homeURL + "\">\n" +
           "<BIG><CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\"\n" +
           "       VALUE=\"Back to HOME\">\n" +
           "</CENTER></BIG></FORM>");

	out.println("</BODY></HTML>"); */
	response.sendRedirect("home.jsp");


  } 





  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
