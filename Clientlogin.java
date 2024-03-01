import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Clientlogin {

public static void clientlogin(String email,String pass) throws Exception{
	 login loginid=new login();
	 Clientinfo clientclass =new Clientinfo();
    Scanner sc=new Scanner(System.in);
		Connection con= dbconnection.getConnection();
     
      String query="Select Email,password,user_id from client where Email=?;";
      
	 PreparedStatement st = con.prepareStatement(query);
	 st.setString(1, email);
    ResultSet rs=st.executeQuery();
boolean flag = false;
while (rs.next()) {
	String Email=rs.getString("Email");
	String pwd=rs.getString("password");
     int id=rs.getInt("user_id");
	if(email.equals(Email)&& pass.equals(pwd))
	{
		flag=true;
System.out.println("Login Successfully!!!!");
System.out.println("Welcome client!!");
clientclass.client(id);
break;
}
	else if(email.equals(Email)&& !pass.equals(pwd))
	{
		flag=true;
	 System.out.println("Enter the Password Correctly");
	 System.out.println(" 1.Forgot Password");
	 System.out.println(" 2.Login Again");
	 System.out.println("Enter the number to modify");
	 int number=sc.nextInt();
		switch (number)
		  {
		case 1:
		{
			clientclass.updatepassword();
			break;
		}
		  
		  case 2:
		    {
	          loginid.login();
	          break;
	         }
}
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
