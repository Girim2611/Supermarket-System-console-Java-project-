import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class adminfunctions {
	public static void main(String args[])throws Exception {
		//InventoryManagement();
		adminwelcome();
	}
	public static void adminwelcome() throws Exception {
		Scanner sc=new Scanner(System.in);
		specialoffer offer=new specialoffer();
		System.out.println();
			System.out.println("*******************************************************************************");
			System.out.println("                    1.Products  ");
			System.out.println("                    2.Special offers   ");
			System.out.println("                    3.Inventory Management  ");
			System.out.println("                    4.Total Amount saled per certain date             ");
			System.out.println("                    5.Exit             ");
			System.out.println("*******************************************************************************");
			String s="yes";
		    while(s.equals("yes")) {
			System.out.println("Enter the number to view ");
			boolean b1=true;
			while(b1==true) {
			int number=sc.nextInt();
			switch (number)
			{
			case 1:
			{
				productitems();
				b1=false;
				break;
			}
			case 2:
			{
				offer.special();
				b1=false;
				break;
			}
			case 3:
			{
				InventoryManagement();
				b1=false;
				break;
			}
			case 4:
			{
				totalamount();
				b1=false;
				break;
			}
			case 5:
			{
				System.out.println("Exited Successfully!!!!!!!!!!!!!!!!!!!");
				b1=false;
				break;
			}
			default:{
				System.out.println("Enter the correct number to modify");
				b1=true;
				break;
			}
			}
		}
			System.out.println("Do you want go back to main menu:(yes|no)");
			sc.nextLine();
			s=sc.next();
		    }
	}
	
	
	//*************************************************************************************************************************************
	
	
		public static void productitems() throws Exception {
		Scanner sc=new Scanner(System.in);
	System.out.println();
	String s="yes";
    while(s.equals("yes")) {
		System.out.println("*******************************************************************************");
		System.out.println("                    1.Insert new product     ");
		System.out.println("                    2.Delete product     ");
		System.out.println("                    3.Update the product     ");
		System.out.println("*******************************************************************************");
		
		System.out.println("Enter the number to modify");
		boolean b1=true;
		while(b1==true) {
		int number=sc.nextInt();
		switch (number)
		{
		case 1:
		{
			insertproduct();
			b1=false;
			break;
		}
		case 2:
		{
			deleteproduct();
			b1=false;

			break;
		}
		case 3:
		{
			updateproduct();
			b1=false;
			break;
		}
		default:{
			System.out.println("Enter the correct number to modify");
			b1=true;
			break;
		}
		}
		}
		System.out.println("Do you want to modify again in products:(yes|no)");
		s=sc.next();
		}
	}
	    
	

		
		//*************************************************************************************************************************************
		
		

	public static void insertproduct() throws Exception {
		Connection con= dbconnection.getConnection();
		Scanner sc=new Scanner(System.in);
        System.out.println("Enter the product id:");
		int p_id=sc.nextInt();
		System.out.println("Enter the product name:");
		String p_name=sc.next();
		System.out.println("Enter the productcategory:");
		String category=sc.next();
		System.out.println("Enter the price:");
		int price=sc.nextInt();
		System.out.println("Enter the product weight :");
		int weight =sc.nextInt();
		System.out.println("Enter the product weight is in gm|kg|l|ml:");
		String weightgm =sc.next();
		System.out.println("Enter the product availability :");
		int active=sc.nextInt();
		String query ="insert into product values(?,?,?,?,?,?,?);";
		
		 PreparedStatement pst =con.prepareStatement(query);
		 
		 pst.setInt(1,p_id);
		 pst.setString(2,p_name);
		 pst.setString(3,category);
		 pst.setInt(4,price);
		 pst.setInt(5,weight);
		 pst.setString(6,weightgm);
		 pst.setInt(7,active);
        int rows=pst.executeUpdate();
        if(rows==1) {
       System.out.println("Product "+p_name+" inserted  Successfully!!!!!!!");
        }
  con.close();  
	}
	
	
	//******************************************************************************************************************************************************
	
	public static void deleteproduct() throws Exception{
		Connection con =dbconnection.getConnection();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the product name:");
		
		boolean b1=true;
		while(b1==true) {
		String p_name=sc.next();
		String query ="delete from product where p_name= ?";
		
		 PreparedStatement pst =con.prepareStatement(query);
		 pst.setString(1,p_name);
		 int rs = pst.executeUpdate();
		 
		  if(rs>=1) {
		       System.out.println(p_name+" deleted Successfully!!!!!");
		       b1=false;
		  }
		        else {
		   System.out.println("Enter the correct product name");
		  b1=true;
	}
		}	        
        con.close();   
	}
	
	//*****************************************************************************************************************************************************
	
	public static void updateproduct() throws Exception{
		Connection con =dbconnection.getConnection();
		Scanner sc=new Scanner(System.in);
		Boolean b1=false;
		while(b1==false) {
		 System.out.println("Enter the product id:");
		int p_id=sc.nextInt();
		System.out.println("Enter the updated price:");
		int price=sc.nextInt();
		System.out.println("Enter the product availability:");
		int active=sc.nextInt();
		String query="UPDATE product SET price =?,active=? WHERE p_id=?;";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(3,p_id);
		pst.setInt(1,price);
		pst.setInt(2,active);
		 int rs = pst.executeUpdate();
		  if(rs>=1) {
		       System.out.println("Updated Successfully!!!!!");
		       b1=true;
		  }
		  else {
			  System.out.println("Enter the correctt product ID");
			  b1=false;
		  }
	}
		con.close();
	}
	
	
	//********************************************************************************************************************************************************************
	
	public static void totalamount() throws Exception{
		
			Connection con =dbconnection.getConnection();
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the date to view the total amount saled on that date:(yyyy-mm-date)");
			String date=sc.next();
			String query ="Select totalprice from orderitems where date= ?";
			
			 PreparedStatement pst =con.prepareStatement(query);
			 pst.setString(1,date);
			  ResultSet rs=pst.executeQuery();
			  int total=0;
			  while(rs.next()) {
				  total+=rs.getInt(1);
			  }
			  System.out.println("Rs:"+total);
			  con.close();
	}
	
	//**************************************************************************************************************************************************************************
	
	public static void InventoryManagement() throws Exception{
		
		Connection con =dbconnection.getConnection();
		String query="SELECT p_id,p_name,active from product where   active<30;";
		  Statement st = con.createStatement();
	        ResultSet rs=st.executeQuery(query);
	        System.out.println();
	        System.out.println("Less Availability products!!!!!!!!!!!");
	        System.out.println();
	        System.out.println("------------------------------------------------------");
        	System.out.printf("%-10s %-20s %-10s  \n", "p_id", "p_name", "Availability");
        	System.out.println("------------------------------------------------------");
        	 
	        while(rs.next())
	        {
	        	 System.out.printf("%-10s %-20s %-10s  \n", rs.getInt(1),rs.getString(2), rs.getInt(3));
	        	 }
	        con.close();    
	}
	
}
