package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DAO.ByeDAO;
import DTO.ByeDTO;

@WebServlet("/Test")

public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8"); //페이지에 넘기지 않기 때문에 리스폰스에 한글을 달아줌
		response.setContentType("text/xml");
		
//		System.out.println(request); //파사드어쩌구.. 주소값??
		
		BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));

	    String buffer;
	    	    
	    while ((buffer = input.readLine()) != null) {       
		    
	        //json parser 선언	
	        JSONParser parser = new JSONParser(); 
	        
			try {		        
		        //json으로 왜변경?
		        JSONObject jsonObj = (JSONObject)parser.parse(buffer);
		        
		        String name = ((String)jsonObj.get("name"));
		        String tel = ((String)jsonObj.get("tel"));
		        String addr = ((String)jsonObj.get("add"));
		        String addr2 = ((String)jsonObj.get("add2"));
		        String x = ((String)jsonObj.get("x"));
		        String y = ((String)jsonObj.get("y"));
		        String stat = ((String)jsonObj.get("stat"));		        
		        
		        ByeDTO bye = new ByeDTO();
		        bye.setName(name);
		        bye.setTel(tel);
		        bye.setAdd(addr);
		        bye.setAdd2(addr2);
		        bye.setX(x);
		        bye.setY(y);
		        bye.setStat(stat);
		        
		        ByeDAO bDAO = ByeDAO.getInstance();
		        bDAO.insertBye(bye);
	        
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	    }
	}
}