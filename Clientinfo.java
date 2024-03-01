import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Clientinfo {
	public static void delete() throws Exception{
		Connection con= dbconnection.getConnection();
		
		Scanner sc=new Scanner(System.in);
	    System.out.println("Enter the email to delete:");
		String email=sc.next();
		
		String query ="delete from client where Email= ?";
		
		 PreparedStatement pst =con.prepareStatement(query);
		 pst.setString(1,email);
		 int rs = pst.executeUpdate();
		 
		  if(rs>=1) 
		       System.out.println("Account deleted Successfully");
		        else
		   System.out.println("Account not deleted");
		        
         con.close();        
	}
	
	
	
//************************************************************************************************************************


public static void updatepassword() throws Exception{
	String url="jdbc:mysql://localhost:3306/supermarket";
	String username="root";
	String password="Girim2611devi$";
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the email:");
	String email=sc.next();
	System.out.println("Enter the new password");
	String pass=sc.next();

	String query ="update client set password= ? where email = ?";
	 Connection con =DriverManager.getConnection(url,username,password);
	 PreparedStatement pst =con.prepareStatement(query);
	 pst.setString(2,email);
	pst.setString(1,pass);
	 int rs = pst.executeUpdate();
	 if(rs==1) 
	       System.out.println("Password updated Successfully");
	        else
	        	 System.out.println("Password is not updated");
	          
     con.close();        
}


//****************************************************************************************************************

public static void client(int id) throws Exception{
	productcategory p=new productcategory();
	Cart view=new Cart();
	specialoffer offer=new specialoffer();
	search searchclass=new search();
	Scanner sc=new Scanner(System.in);
	String s="yes";
	while(s.equals("yes")) {
System.out.println();
System.out.println("*******************************************************************************");
System.out.println("                    1.View Products                                 ");
System.out.println("                    2.View Cart                                 ");
System.out.println("                    3.Search Product                                 ");
System.out.println("                    4.Ordered Items in cart                               ");
System.out.println("                    5.SpecialOffers                               ");
System.out.println("                    6.Order History                              ");
System.out.println("                    7.Exit                               ");
System.out.println("*******************************************************************************");
System.out.println("Enter the number to view");
boolean b1=true;
boolean b2=true;
while(b1==true) {
int number=sc.nextInt();
switch (number)
{
case 1:
{
p.product(id);
b1=false;
	break;
}
case 2:
{
view.viewcart(id);
b1=false;
break;
}
case 3:
{
searchclass.searchitem(id);
b1=false;
break;
}
case 4:
{
	view.orderitems(id);
	b1=false;
	break;
}
case 5:
{
	offer.viewspecialoffers(id);
	b1=false;
	break;
}
case 6:
{
	view.orderhistory( id);
	b1=false;
	break;
}

case 7:
{
b2=true;
System.out.println("Exited Successfully!!!!!!!!!!!");
break;
}
default:{
	System.out.println("Enter the correct number to view");
	b1=true;
	break;
}
}
}
if(b2==false) {
System.out.println("Do you want to go back to main menu:(yes/no)");
s=sc.next();
}
	}
}}
