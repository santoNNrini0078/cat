package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DAO.HospitalDAO;
import DTO.HospitalDTO;


@WebServlet("/Hospital")
public class HospitalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
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
		        JSONObject jsonObj = (JSONObject)parser.parse(buffer);
		        
		        String name = ((String)jsonObj.get("name"));
		        String tel = ((String)jsonObj.get("tel"));
		        String addr = ((String)jsonObj.get("add"));
		        String addr2 = ((String)jsonObj.get("add2"));
		        String x = ((String)jsonObj.get("x"));
		        String y = ((String)jsonObj.get("y"));
		        String stat = ((String)jsonObj.get("stat"));		        
		        
		        HospitalDTO h = new HospitalDTO();
		        h.setName(name);
		        h.setTel(tel);
		        h.setAdd(addr);
		        h.setAdd2(addr2);
		        h.setX(x);
		        h.setY(y);
		        h.setStat(stat);
		        
		        HospitalDAO hDAO = HospitalDAO.getInstance();
		        hDAO.insertHospital(h);
	        
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	    }
	}

}
