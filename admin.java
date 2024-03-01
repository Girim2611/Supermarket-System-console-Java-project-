import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
public class admin {
public static void adminlogin(String email,String pass) throws Exception{
	 login loginid=new login();
	 Clientinfo client =new Clientinfo();
	 adminfunctions available=new adminfunctions();
	 adminfunctions admin=new adminfunctions();
     Scanner sc=new Scanner(System.in);
		Connection con= dbconnection.getConnection();
       String query="SELECT email,password from admin ";
	 PreparedStatement st = con.prepareStatement(query);
     ResultSet rs=st.executeQuery(query);
boolean flag = false;
while (rs.next()) {
	String Email=rs.getString("email");
	String pwd=rs.getString("password");
	if(email.equals(Email)&& pass.equals(pwd))
	{
		flag=true;
 System.out.println("Login Successfully!!!!");
 System.out.println("Welcome Admin!!");
 available.InventoryManagement();
 admin.adminwelcome();
 
 break;
}
	else if(email.equals(Email)&& !pass.equals(pwd))
	{
		flag=true;
	 System.out.println("Enter the Password Correctly");
	 loginid.login();
	      break; 
	}
}
if(!flag)
{
	System.out.println("Invalid email and password");
	loginid.login();
}
con.close();        
}

}

