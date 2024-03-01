import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.Statement;
import java.util.Scanner;

public  class Cart {
	
	public static void main(String args[])throws Exception {
		addtocart(1009);
	}
	public static void addtocart(int id) throws Exception {
		Scanner sc=new Scanner(System.in);
		Connection con= dbconnection.getConnection();
		 int count=0;
		 int p_id=0;
         int price=0;
         int stock=0;
         String query2;
         String p_name="";
        
         System.out.println("Enter the p_name:"); 
		 while(count<=0) {
	      p_name=sc.next();
	           query2="SELECT p_id,price,weight,active from product where Lower(p_name)=Lower(?);";
	   
	            PreparedStatement statement = con.prepareStatement(query2);
	                statement.setString(1, p_name);
	               
	                ResultSet res = statement.executeQuery();
	               
	                   while( res.next()) 
	                	   count++;
	                        
	                   if(count==0)
	                	   System.out.println("Enter the correct product_name:");
	                   
		 }  
		 System.out.println("Enter the needed quantity:");
	       int weight=sc.nextInt();
	      
		 String query ="insert into addtocart values(?,?,?,?,?,?);"; 
		 query2="SELECT p_id,price,weight,active from product where Lower(p_name)=Lower(?);";
		   
         PreparedStatement statement = con.prepareStatement(query2);
             statement.setString(1, p_name);
            
             ResultSet res = statement.executeQuery();
            
                while( res.next()) {
             	   count++;
                      p_id=res.getInt(1);
                      price=res.getInt(2);
                      stock=res.getInt(4);
             }
		 int total=price*weight;
	                   PreparedStatement pst =con.prepareStatement(query);
	          		 pst.setInt(1,id);
	          		 pst.setInt(2, p_id);
	          		 pst.setString(3, p_name);
	          		 pst.setInt(4, price);
	          		 pst.setInt(5, weight);
	          		 pst.setInt(6,total);
	          		 int result=pst.executeUpdate();
	          		 if (result==1) {
	          		 System.out.println(p_name +" added to cart");
	          		 viewcart(id);
	          		 }
	          		con.close();    
	}

	
	
	//*****************************************************************************************************************************************************************
	
	

	public static void viewcart(int id) throws Exception {
		productcategory p=new productcategory();
		Connection con= dbconnection.getConnection();
		Scanner sc=new Scanner(System.in);
		System.out.println("Items in cart:");
		System.out.println("userid:"+id);
		System.out.println();
		String query="SELECT p_id,p_name,price,weight,TotalPrice from addtocart where user_id=?;";

        PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
	        ResultSet rs=statement.executeQuery();
	        int total=0;
	        System.out.println("---------------------------------------------------------------------------");
        	System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", "p_id", "p_name", "price","weight", "TotalPrice");
        	System.out.println("----------------------------------------------------------------------------");

	        while(rs.next())
	        {
	         System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getInt(5));

	        	total+=rs.getInt(5);
	        }
	        System.out.println();
		System.out.println("Total Amount:"+total);
		System.out.println();
		System.out.println("******************************************************************************************************");
		System.out.println("                                 1.Delete the items from cart");
		System.out.println("                                 2.Add the items in cart      ");
		System.out.println("                                 3.View Products              ");
		System.out.println("                                 4.Order the items in cart       ");
		System.out.println("******************************************************************************************************");
		System.out.println("Do you want to modify the above given:(yes/no)");
		String s=sc.next();
		while(s.equals("yes")) {
			System.out.println("Enter the number to view:");
			boolean b1=true;
			while(b1==true) {
			int number=sc.nextInt();
			switch (number)
			{
			case 1:
			{
	           deletecart(id);
	           b1=false;
	           break;
			}
			case 2:
			{
				addtocart(id);
				b1=false;
				break;
			}
			case 3:
			{
				p.product(id);
				b1=false;
				break;
			}
			case 4:
			{
				orderitems(id); 
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
			System.out.println("Do you want to go back again:(yes/no)");
			 s=sc.next();
		}
	
			con.close();  
	}
	
	
	
	
	//*****************************************************************************************************************************************************************
	
	
	
	public static void deletecart(int id) throws Exception {
        Connection con= dbconnection.getConnection();
		Scanner sc = new Scanner(System.in);
	    System.out.println("Enter the product id to delete:");
		String p_id=sc.next();
		
		String query ="delete from addtocart where p_id= ?";
		
		 PreparedStatement pst =con.prepareStatement(query);
		 pst.setString(1,p_id);
		 int res = pst.executeUpdate();
		 if(res>=1) {
		       System.out.println("Deleted Successfully");
		 String query2="SELECT p_id,p_name,price,weight,TotalPrice from addtocart where user_id=?;";

	        PreparedStatement statement = con.prepareStatement(query2);
	            statement.setInt(1, id);
		        ResultSet rs=statement.executeQuery();
		        System.out.println("---------------------------------------------------------------------------");
	        	System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", "p_id", "p_name", "price","weight", "TotalPrice");
	        	System.out.println("----------------------------------------------------------------------------");

		        while(rs.next())
		        {
		         System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getInt(5));
		        }

		        
		 }
		 else
		   System.out.println("Enter the crct product id");
		        
       con.close();   
	}

	//*****************************************************************************************************************************************************************
	
	
	public static void orderitems(int id) throws Exception {
		Scanner sc=new Scanner(System.in);
		 Connection con= dbconnection.getConnection();
		 if(order(id)==true) {

    	 String query3="Select curdate();";
    	 Statement st = con.createStatement();
	        ResultSet res=st.executeQuery(query3);
	        String s="";
	        while(res.next())
	        {
              s=res.getString(1);
	       }
	        
	        
		System.out.println("Ordered items:");
		System.out.println("userid:"+id);
		
		String query="SELECT p_id,p_name,price,weight,TotalPrice from addtocart where user_id=?;";

        PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
        
	        ResultSet rees=statement.executeQuery();
	        int count=0;
	        while(rees.next()) {
	        	count++;
	        }
	        int itemslist=0;
	        int total=0;
	        ResultSet rs=statement.executeQuery();
	       if(count>0) {
	      
	        System.out.println("                                Supermarket                                ");
	        System.out.println("                     1/266,oppanakara street,Coimbatore                     ");
		    System.out.println("                                 ph.no:642109                               ");  
		    System.out.println("Date:"+s);
	        System.out.println("---------------------------------------------------------------------------");
        	System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", "p_id", "p_name", "price","items", "TotalPrice");
        	System.out.println("----------------------------------------------------------------------------");
        	 
	        while(rs.next())
	        {
	        	 System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getInt(4), rs.getInt(5));
	        	 
	        	int p_id= rs.getInt(1);
	        	String p_name=rs.getString(2);
	        	int price=rs.getInt(3);
	        	int weight=rs.getInt(4);
	        	int totalprice=rs.getInt(5);
	        	itemslist+=weight;
	        	
	        	String query7 ="delete from addtocart where user_id= ?";
				
				 PreparedStatement psts =con.prepareStatement(query7);
				 psts.setInt(1,id);
				 psts.executeUpdate();
				 
		       
	        	String query5="Select active from product  WHERE p_id=?;";
        		PreparedStatement pt = con.prepareStatement(query5);
	       		pt.setInt(1,p_id);
	       	ResultSet ress=pt.executeQuery();
	       	int active=0;
	       	while(ress.next())
	       		active=ress.getInt(1);
	       	 active-=weight;

	       		String query4="UPDATE product SET active=? WHERE p_id=?;";
	       		PreparedStatement p = con.prepareStatement(query4);
	       		p.setInt(2,p_id);
	       		
	       		p.setInt(1,active);
	       	 int rwes = p.executeUpdate();
	        

	       	 
	        	 String query2 ="insert into orderitems values(?,?,?,?,?,?,?);";
	        		PreparedStatement pst =con.prepareStatement(query2);
          		 pst.setInt(1,id);
          		 pst.setInt(2, p_id);
          		 pst.setString(3, p_name);
          		 pst.setInt(4, price);
          		 pst.setInt(5, weight);
          		 pst.setInt(6,totalprice);
          		 pst.setString(7,s);
          		 pst.executeUpdate();
          		
	        	total+=rs.getInt(5);
	        	
	        	
	        }
	 
	        System.out.println();
	        System.out.println("Total No Of Items:"+itemslist);
			System.out.println("Total Amount:Rs."+total);
			System.out.println("                                          Thank's for Purchasing!!!!!!!!!!!!!");
	       	
	        	
	       }
	       else {
	    	   System.out.println("Your Cart is Empty!!");
	       System.out.println("Add products in cart to place Order");
	       
	       }
		 }
	}
	
	
	//*********************************************************************************************************************************************
	
	
	public static void orderhistory(int id) throws Exception {
		 Connection con= dbconnection.getConnection();
		 String query="SELECT *from orderitems where user_id=?";
			PreparedStatement pst =con.prepareStatement(query);
     		 pst.setInt(1,id);
     		ResultSet rs=pst.executeQuery(); 
     		String date="2024-02-10";
     		int count=0;
     		while(rs.next())
     		{
     			count++;
     			String d=rs.getString(7);
     			if(date.equals(d)) {
     				 System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", rs.getInt(2),rs.getString(3), rs.getInt(4),rs.getInt(5), rs.getInt(6));
     	        	
     			}
     			else if(count!=0)
     			{
     				date=d;
     				System.out.println();
     				System.out.println("Date:"+d);
     				 System.out.println("-----------------------------------------------------------------");
     	         	System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", "p_id", "p_name", "price","items", "TotalPrice");
     	         	System.out.println("-----------------------------------------------------------------");
     	         	 
     				 System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", rs.getInt(2),rs.getString(3), rs.getInt(4),rs.getInt(5), rs.getInt(6));
      	        	
     			}
     		}
     		if(count==0)
     		{
     			System.out.println("No Order Placed");
     		}
	}
	
	//************************************************************************************
	public static boolean order(int id) throws Exception {
		 Connection con= dbconnection.getConnection();
			
			String query="SELECT p_id,p_name,weight from addtocart where user_id=?;";

	        PreparedStatement statement = con.prepareStatement(query);
	            statement.setInt(1, id);
	        
		        ResultSet rs=statement.executeQuery();
		       boolean b1=true;
		        while(rs.next())
		        {
		        	
		        	int p_id= rs.getInt(1);
		        String p_name=rs.getString(2);
		           int weight=rs.getInt(3);
		        
			      
		        	String query5="Select active from product  WHERE p_id=?;";
	        		PreparedStatement pt = con.prepareStatement(query5);
		       		pt.setInt(1,p_id);
		       	ResultSet ress=pt.executeQuery();
		       	int active=0;
		       	while(ress.next())
		       		active=ress.getInt(1);
		       
		       	active-=weight;
		       
		       	if(active< 0) {
		       		System.out.println(p_name+" is out of Stock.");
		       		b1= false;
		       		break;
		       	}
		       	else
		       	b1=true;	
		        }
		 
		       return b1;	
		       }
	}
	

