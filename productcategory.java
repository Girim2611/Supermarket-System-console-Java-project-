import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class productcategory {
	public static void main(String args[])throws Exception {
		product(1006);
	}
	public static void product(int user_id) throws Exception {
	
	Cart add=new Cart();
		Scanner sc=new Scanner(System.in);
		Connection con= dbconnection.getConnection();
		 String s="yes";
		    while(s.equals("yes")) {
		System.out.println();
		System.out.println("*******************************************************************************");
		System.out.println("                    1.DryFruits                               ");
		System.out.println("                    2.Beverages                          ");
		System.out.println("                    3.Grocery                                ");
		System.out.println("                    4.Snakes & Candy                              ");
		System.out.println("                    5.Household                             ");
		System.out.println("                    6.PersonalCare                   ");
		System.out.println("                    7.Stationary                              ");
		System.out.println("                    8.DairyProducts                             ");
	    System.out.println("*******************************************************************************");
	   
		System.out.println("Enter the number to view the products:");
		boolean b1=true;
		while(b1==true) {
		int number=sc.nextInt();
		switch (number)
		{
		case 1:
		{
			  String query="SELECT p_id,p_name,price,weight,weightgm from product where productcategory='DryFruits' and active>0;";
			  Statement st = con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        while(rs.next())
		        {
		        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" - "+rs.getInt(4)+""+rs.getString(5)+"  =  Rs."+rs.getInt(3));
		        }
		        b1=false;
			break;
		}
		case 2:
		{
			  String query="SELECT p_id,p_name,price,weight ,weightgm from product where productcategory='Beverages' and active>0;";
			  Statement st = con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        while(rs.next())
		        {
		        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" - "+rs.getInt(4)+""+rs.getString(5)+"  =  Rs."+rs.getInt(3));
		        }
		        b1=false;
			break;
		}
		case 3:
		{
			  String query="SELECT p_id,p_name,price,weight ,weightgm from product where productcategory='Grocery' and active>0;";
			  Statement st = con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        while(rs.next())
		        {
		        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" - "+rs.getInt(4)+""+rs.getString(5)+"  =  Rs."+rs.getInt(3));
		        }
		        b1=false;
			break;
		}
		case 4:
		{
			  String query="SELECT p_id,p_name,price,weight ,weightgm from product where productcategory='Snacks&Candy' and active>0;";
			  Statement st = con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        while(rs.next())
		        {
		        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" - "+rs.getInt(4)+""+rs.getString(5)+"  =  Rs."+rs.getInt(3));
		        }
		        b1=false;
			break;
		}
		case 5:
		{
			  String query="SELECT p_id,p_name,price,weight ,weightgm from product where productcategory='Household' and active>0;";
			  Statement st = con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        while(rs.next())
		        {
		        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" - "+rs.getInt(4)+""+rs.getString(5)+"  =  Rs."+rs.getInt(3));
		        }
		        b1=false;
			break;
		}
		case 6:
		{
			  String query="SELECT p_id,p_name,price,weight ,weightgm from product where productcategory='Personalcare' and active>0;";
			  Statement st = con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        while(rs.next())
		        {
		        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" - "+rs.getInt(4)+""+rs.getString(5)+"  =  Rs."+rs.getInt(3));
		        }
		        b1=false;
			break;
		}
		case 7:
		{
			  String query="SELECT p_id,p_name,price,weight ,weightgm from product where productcategory='Stationery' and active>0;";
			  Statement st = con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        while(rs.next())
		        {
		        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" - "+rs.getInt(4)+""+rs.getString(5)+"  =  Rs."+rs.getInt(3));
		        }
		        b1=false;
			break;
		}
		case 8:
		{
			  String query="SELECT p_id,p_name,price,weight ,weightgm from product where productcategory='Dairyproducts' and active>0;";
			  Statement st = con.createStatement();
		        ResultSet rs=st.executeQuery(query);
		        while(rs.next())
		        {
		        	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" - "+rs.getInt(4)+""+rs.getString(5)+"  =  Rs."+rs.getInt(3));
		        }
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
		System.out.println("Do you want to add items to cart:(yes/no)");
		String item=sc.next();
		
		while(item.equals("yes")) {
			add.addtocart(user_id);
			System.out.println("Do you want to add another items to cart:(yes/no)");
		    item=sc.next();
		}
		System.out.println("Do you want to view the products again:(yes/no)");
		s=sc.next();
	    }
		
	
}
}
