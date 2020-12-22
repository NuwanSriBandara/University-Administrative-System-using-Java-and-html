package school;

public class Student {
    String UserName="admin";
    String Password="123";
    boolean checklogin(String username,String password){
        boolean login=false;
        if (username.equals(UserName)&& password.equals(Password)){
            login=true;
        }
        else{
            login=false;
        }
        return login;
    }
}
    
    

