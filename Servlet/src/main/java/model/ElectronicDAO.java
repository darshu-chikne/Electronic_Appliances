package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ElectronicDAO {

	static Connection con = null;
	static{
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlettest?user=root&password=sql123");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	public double displayBill(WElectronic dto) {
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int quan=dto.getQuantity();
		int finalQuantity;
		boolean status = false;
		double bill =0.0;
		double cgst=0.0;
		double sgst=0.0;
		double price;
		double totalbill =0.0;
		int id;
		int quantity;
		String q= "select * from electronic where name=? and company=?";

		try {
			pstmt = con.prepareStatement(q);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getCompany());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				 id = rs.getInt(1);
				 price=rs.getDouble(5);
				 quantity=rs.getInt(4);
				if(quantity>=quan) {
					
					 bill= price*quan;
					cgst=bill+bill*0.09+bill*0.09;
//					double sgst=bill+bill*0.09;
			 	       totalbill=cgst+bill;
			 	      System.out.println(quantity); 
			 	     System.out.println("======================"); 
			 	     System.out.println(price); 
			 	      System.out.println(bill);
					System.out.println(totalbill);
					finalQuantity=quantity-quan;
					System.out.println(finalQuantity);
					String q1="update electronic set quantity=? where id=? ";
					pstmt = con.prepareStatement(q1);
					pstmt.setInt(1,finalQuantity);
					pstmt.setInt(2,id);
					pstmt.executeUpdate();
					status=true;
				}
//				else if(quantity<0){
//					
//					totalbill=0.0;
//					
//				}
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status==false) {
			totalbill=0.0;
		}
		
		return totalbill;
	}

}
