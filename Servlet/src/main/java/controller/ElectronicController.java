package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ElectronicDAO;
import model.WElectronic;
@WebServlet("/controller")
public class ElectronicController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name =req.getParameter("name");
		String company=req.getParameter("company");
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		
		WElectronic dto = new WElectronic();
		dto.setName(name);
		dto.setCompany(company);
		dto.setQuantity(quantity);
		
		ElectronicDAO dao= new ElectronicDAO();
		double bill=dao.displayBill(dto);
		
		if(bill<=0.0) {
			RequestDispatcher rd = req.getRequestDispatcher("outofstock.jsp");
			rd.forward(req, resp);
		}
		else {
			
			req.setAttribute("Bill", bill);
			RequestDispatcher rd = req.getRequestDispatcher("bill.jsp");
			rd.forward(req, resp);
		}
		
	}
}
