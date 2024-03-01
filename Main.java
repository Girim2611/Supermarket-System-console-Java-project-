import java.math.BigInteger;
import java.sql.*;
import java.util.*;
public class Main {

	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		Register client=new Register();
		 login loginid=new login();
		System.out.println("********Welcome to SuperMarket***********");
		 
		 System.out.println(" 1.Register");
		 System.out.println(" 2.Login");
		 System.out.println("Enter the number to modify");
		 int number=sc.nextInt();
			switch (number)
			  {
			case 1:
			{
           client.createaccount();
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
