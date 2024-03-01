import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {
	
public static void createaccount() throws Exception{
		
		Connection con= dbconnection.getConnection();
		Scanner sc=new Scanner(System.in);
		 login loginid=new login();
		String quer ="select max(user_id) from client ";
		Statement st = con.createStatement();
        ResultSet rs=st.executeQuery(quer);
int id=0;
        while(rs.next())
        {
        	 id=Integer.parseInt(rs.getString("max(user_id)"));
        	id++;
        }
      
		String email;
		String pass;
		Pattern pattern;
        Matcher matcher;
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        do {
            System.out.print("Enter your Email: ");
            email = sc.next();
            pattern = Pattern.compile(emailRegex);
            matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                System.out.println("Invalid email address. Please enter a valid email.");
            }
        } while (!matcher.matches());
      String passregex=  "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
      do {
		System.out.println("Enter the password");
		 pass=sc.next();
		pattern = Pattern.compile(passregex);
        matcher = pattern.matcher(pass);
        if (!matcher.matches()) {

            System.out.println("Password must contain at least one number & one special character & one uppercase & lowercase letter & at least 8 Characters");
            
            System.out.println("Please enter a valid password.");
        }
    } while (!matcher.matches());
		String anotherphno;
		String Phonenumber;
		String phone1Regex ="^\\d{10}$";
		do {
		System.out.println("Enter the phone number");
		 Phonenumber=sc.next();
		 pattern = Pattern.compile(phone1Regex);
         matcher = pattern.matcher(Phonenumber);
         if (!matcher.matches()) {
             System.out.println("Invalid Phonenumber . Please enter a valid Phonenumber.");
             
         }
		}
		while(!matcher.matches());
			
		do {
		System.out.println("Enter the another phone number");
		 anotherphno=sc.next();
		 pattern = Pattern.compile(phone1Regex);
         matcher = pattern.matcher(anotherphno);
         if (!matcher.matches()) {
             System.out.println("Invalid Phonenumber . Please enter a valid Phonenumber.");
         }
		}
		while(!matcher.matches());
		
		
		System.out.println("Enter the address");
		String address=sc.next();
		
		String query ="insert into client values(?,?,?,?,?,?);";
		
		 PreparedStatement pst =con.prepareStatement(query);
		 
		 pst.setString(1,email);
		 pst.setString(2, pass);
		 pst.setInt(3, id);
		 pst.setString(4, Phonenumber);
		 pst.setString(5,anotherphno);
		 pst.setString(6, address);
		         int rows=pst.executeUpdate();
        if(rows==1) {
       System.out.println("email "+email+" inserted  Successfully");
       loginid.login();
        }
        else
        	 System.out.println("email"+email+" is not inserted");

        con.close();  
}



}
