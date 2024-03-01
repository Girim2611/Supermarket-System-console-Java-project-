import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class search {
	public static void main(String args[]) throws Exception{
		searchitem(1001);
	}
	public static void searchitem(int user_id) throws Exception {
		Connection con= dbconnection.getConnection();
		Cart cartclass=new Cart();
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the item to Search");
	String s=sc.next();
	String query="Select p_id,p_name,price,weight,active from product where LOWER(p_name)=LOWER(?);";
	PreparedStatement statement = con.prepareStatement(query);
    statement.setString(1, s);
    ResultSet rs=statement.executeQuery();
    String search="yes";
    while(search.equals("yes")) {
  while(rs.next())
  {
	  int available=rs.getInt(5);
	  if(available > 20) {
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" item="+rs.getInt(4)+" = Rs."+rs.getInt(3));
		System.out.println("Do you want to add the item to cart:(yes/no)");
		String cart=sc.next();
		if(cart.equals("yes"))
		{
			cartclass.addtocart(user_id);
		}
	  }
	  else
		  System.out.println("Product is out of stock");
  }
    System.out.println("Do you want to search Again:(yes/no)");
    search=sc.next();
	}
    con.close();	
	}
	
}
