import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class specialoffer {
	public static void main(String args[])throws Exception {
	insertspecialoffers();
	}
	public static void special() throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.println();
		String s="yes";
	    while(s.equals("yes")) {
			System.out.println("*******************************************************************************");
			System.out.println("                    1.Insert new specialoffer     ");
			System.out.println("                    2.Delete Specialoffers    ");
			System.out.println("                    3.View the Specialoffers    ");
		System.out.println("*******************************************************************************");
			
			System.out.println("Enter the number to modify in specialoffer:");
			boolean b1=true;
			while(b1==true) {
			int number=sc.nextInt();
			switch (number)
			{
			case 1:
			{
				insertspecialoffers();
				b1=false;
				break;
			}
			case 2:
			{
				deletespecialoffers();
				b1=false;
				break;
			}
			case 3:
			{
			viewspecialoffersbyadmin();
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
			System.out.println("Do you want to modify again in specialoffers:(yes|no)");
			s=sc.next();
		    }
		     
	}
	
	//*********************************************************************************************************************************************
	
	public static void viewspecialoffers(int id) throws Exception {
		Scanner sc=new Scanner(System.in);
		Cart cartclass =new Cart();
		Connection con= dbconnection.getConnection();
		System.out.println("Special Offers:");
		 String query="Select * from specialoffer;";
		
		  Statement st = con.createStatement();
	        ResultSet rs=st.executeQuery(query);
	        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        	System.out.printf("%-10s %-20s %-10s %-10s %-10s %-15s %-20s %-20s\n", "p_id", "p_name", "weight", "gm|kg|L", "price", "DiscountPrice","validfrom","valodupto");
        	System.out.println("-----------------------------------------------------------------------------------------------------------------");

	        while(rs.next())
	        {
	        	int p_id=rs.getInt(1);
	        	String p_name="";
	        	int weight=0,price=0,discount=0;
	        	
	        	String weightgm="";
	        			Date validfrom,validto;
	        	String query2="Select p_name,price,weight,weightgm from product where p_id=?;";
	        	
	        	PreparedStatement statement = con.prepareStatement(query2);
	            statement.setInt(1, p_id);
	            ResultSet result=statement.executeQuery();
	            
	            while(result.next())
	            {
	            	p_name=result.getString(1);
	            	weight=result.getInt(3);
	            	weightgm=result.getString(4);
	            	price=result.getInt(2);
	            }
	            discount=rs.getInt(4);
	            validfrom=rs.getDate(2);
	            validto=rs.getDate(3);
	            System.out.printf("%-10s %-20s %-10s %-10s %-10s %-15s %-20s %-20s\n", p_id, p_name, weight, weightgm, price,discount,validfrom,validto);

	        System.out.println("");
	        System.out.println("Do you want to add products to cart:(yse|no)");
	        String addcart=sc.next();
	        if(addcart.equals("yes")) {
	        	cartclass.addtocart(id);
	        }
	        	
	        }
	        
	}
	
	//*****************************************************************************************************************************************
	
	
	public static void deletespecialoffers() throws Exception{
		Connection con =dbconnection.getConnection();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the product id:");
		int p_id=sc.nextInt();
		String query ="delete from specialoffer where p_id= ?;";
		
		 PreparedStatement pst =con.prepareStatement(query);
		 pst.setInt(1,p_id);
		 int rs = pst.executeUpdate();
		 
		  if(rs>=1) 
		       System.out.println(" deleted Successfully!!!!!");
		        else
		   System.out.println(p_id+"not present.");
		        
        con.close();   
	}
	
	//*****************************************************************************************************************************************
	
	public static void insertspecialoffers() throws Exception{
		Scanner sc=new Scanner(System.in);
		Connection con= dbconnection.getConnection();
		boolean b1=true;
		
		System.out.println("Enter the p_id:");
		int p_id=0;
		while(b1==true) {
	      p_id=sc.nextInt();
	     String query2="Select p_name from product where p_id=?;";
     	int count=0;
     	PreparedStatement statement = con.prepareStatement(query2);
         statement.setInt(1, p_id);
         ResultSet res=statement.executeQuery();
         while(res.next()) {
        	count++; 
         }
		
         if(count==0) {
        	 System.out.println("Enter the correct product_id:");
         b1=true;
         }
         else
        	 b1=false;
		}
         	    System.out.println("Enter the offer start date:(year/month/date)");
	    String validfrom=sc.next();
	    System.out.println("Enter the offer end date:(year/month/date)");
	    String validupto=sc.next();
	    System.out.println("Enter the discount price:");
	    int discount=sc.nextInt();
	    String query ="insert into specialoffer values(?,?,?,?);";
	 
	                   PreparedStatement pst =con.prepareStatement(query);
	          		 pst.setInt(1,p_id);
	          		 pst.setString(2, validfrom);
	          		 pst.setString(3, validupto);
	          		 pst.setInt(4, discount);
	          		
	          		 int result=pst.executeUpdate();
	          		 if (result==1) {
	          		 System.out.println("Product Added to specialoffers!!");
	          		
	          		 }
	          		
				 
	          		con.close();  	
	}
	
	//************************************************************************************************************************************************************************
	
	
	public static void viewspecialoffersbyadmin() throws Exception {
		Scanner sc=new Scanner(System.in);
		Cart cartclass =new Cart();
		Connection con= dbconnection.getConnection();
		System.out.println("Special Offers:");
		 String query="Select * from specialoffer;";
		
		  Statement st = con.createStatement();
	        ResultSet rs=st.executeQuery(query);
	        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        	System.out.printf("%-10s %-20s %-10s %-10s %-10s %-15s %-20s %-20s\n", "p_id", "p_name", "weight", "gm|kg|L", "price", "DiscountPrice","validfrom","valodupto");
        	System.out.println("-----------------------------------------------------------------------------------------------------------------");

	        while(rs.next())
	        {
	        	int p_id=rs.getInt(1);
	        	String p_name="";
	        	int weight=0,price=0,discount=0;
	        	
	        	String weightgm="";
	        			Date validfrom,validto;
	        	String query2="Select p_name,price,weight,weightgm from product where p_id=?;";
	        	
	        	PreparedStatement statement = con.prepareStatement(query2);
	            statement.setInt(1, p_id);
	            ResultSet result=statement.executeQuery();
	            
	            while(result.next())
	            {
	            	p_name=result.getString(1);
	            	weight=result.getInt(3);
	            	weightgm=result.getString(4);
	            	price=result.getInt(2);
	            }
	            discount=rs.getInt(4);
	            validfrom=rs.getDate(2);
	            validto=rs.getDate(3);
	            System.out.printf("%-10s %-20s %-10s %-10s %-10s %-15s %-20s %-20s\n", p_id, p_name, weight, weightgm, price,discount,validfrom,validto);

	        
	        	
	        }
	        con.close();  	    
	}
	
}