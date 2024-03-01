import java.util.Scanner;

public class login {
	public static void login() throws Exception{
		admin ad=new admin();
		Clientlogin client=new Clientlogin();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the email:");
		String email=sc.next();
		System.out.println("Enter the password");
		String pass=sc.next();
		System.out.println("Enter the role(admin/customer)");
		String role=sc.next();
		if(role.equalsIgnoreCase("admin"))
			ad.adminlogin(email,pass);
		else 
		client.clientlogin(email,pass);
	}	
}
