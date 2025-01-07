import static javax.swing.JOptionPane.*;
import java.util.HashMap;

public class User {
    
    // Declare variables
    private String username;
    private int password;
    private int newPassword;
    private String newUsername;
    
    // Use to pair the variables
    private final static HashMap <String, Integer> CREDENTIALS = new HashMap<>();
    
    // Constructor
    public User(String username, int password){
        
        this.username = username;
        this.password = password;
        
    }
    
    // Setter method
    public void setUsername (String username){
        
        this.username = username;
        
    }
    
    public void setPassword (int password){
        
        this.password = password;
        
    }
    
    // Getter method
    public String getUsername(){
        
        return username;
        
    }
    
    public int getPassword(){
        
        return password;
        
    }
    
    // Method to add a user to the list
    public static void addUser(String username , int password) {
        
        CREDENTIALS.put(username, password);
        
    }
    
    // Method use to check if a username exists in the list of users
    private boolean usernameExists(String username) {
        
        return CREDENTIALS.containsKey(username); //Key is for String
        
    }
    
    // Method use to check if a password exists in the list of users
    public boolean passwordExists(int password) {
        
        return CREDENTIALS.containsValue(password); // Value is for digit
        
    }
    
    // Method use to reset the password
    private void resetPasswordForUsername(String username) {
    
        while (true) {

                    String stringNewPassword = showInputDialog(null, "Please enter your new password for username " + username + " (in digits)", "Password", QUESTION_MESSAGE);
            
                    if (stringNewPassword != null && stringNewPassword.matches("\\d+")) {
                        
                        newPassword = Integer.parseInt(stringNewPassword);
                        
                        if (!passwordExists(newPassword)){ // If the password is not same as previous password
                            
                                CREDENTIALS.put(username, newPassword);
                                showMessageDialog(null, "Password reset sucessfully", "Password", PLAIN_MESSAGE); 
                                return ; // Once password is reset, exit loop 

                        } else {
                            
                            showMessageDialog(null,"The password is SAME as old password. Please re-enter a new password", "Password", ERROR_MESSAGE);
                            
                        }
                        
                
                    } else {
                
                        showMessageDialog(null,"Please enter a VALID password (without alphabets)", "Error!", ERROR_MESSAGE);
                
                    }
                
            }
        }
      
    
    // Method for user to register the account
    public User RegisterUser(){
        
        boolean invalidInput = true;
        
        while(invalidInput){
            
            newUsername = showInputDialog(null, "Please enter your username for registering account", "Register", QUESTION_MESSAGE);
            
            if (newUsername == null) { 
            
                showMessageDialog(null, "Registration cancelled.", "Register", WARNING_MESSAGE);
                return null; // Exit the method
                
            } 

                if (newUsername.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")){
                
                    if(usernameExists(newUsername)){ // If the username is same as the previous username that already register
                    
                        showMessageDialog(null,"The username is already exists. Please re-enter a new username", "Username", WARNING_MESSAGE);
                        int confirmResult = showConfirmDialog(null,"Do you want to reset your password?","Reset Password", YES_NO_OPTION);
                        
                        if (confirmResult == YES_OPTION){
                            
                            resetPasswordForUsername(newUsername); // Reset password
                            return null;
                            
                        } else {
                            
                            return null;
                            
                        }

                    } else {

                       addUser(newUsername,newPassword); // add to user list
                       break;
                            
                    }
                
                
                } else {
                
                    showMessageDialog(null,"Invalid username. Please enter VALID username (without digits)", "Error!", ERROR_MESSAGE);
                
                }   
                        
        }
        
        
        do {
            
            String stringNewPassword = showInputDialog(null, "Please enter your desired password for registering account (in digits)", "Passowrd", QUESTION_MESSAGE);
            
            if(stringNewPassword != null){
                
                if (stringNewPassword.matches("\\d+")) { // If the password only contains digits
                    
                    newPassword = Integer.parseInt(stringNewPassword);
                        
                        addUser(newUsername,newPassword); // add to user list
                        showMessageDialog(null,"Registration Successful !", "Register", PLAIN_MESSAGE);
                        showMessageDialog(null,"Please login the account with your created username and password", "Register", PLAIN_MESSAGE);
                        return null;
                  
                } else {
                    
                    showMessageDialog(null,"Please enter an VALID password (without alphabet)", "Error!", ERROR_MESSAGE);
                    
                }    
                
            } else {
                
                showMessageDialog(null, "Registration cancelled.", "Register", ERROR_MESSAGE);
                return null; // Back to the Authentication menu
                
            }   
 
        } while(true);

    }
    
    // Method for user to login to the account
    public boolean Login(){
 
        while(true){
            
            username = showInputDialog(null, "Please enter your username to login your account", "Login", QUESTION_MESSAGE);
            
            if (username == null) { // User canceled input
                
                showMessageDialog(null, "Login cancelled.", "Login", WARNING_MESSAGE);
                return false; // Back to the Authentication menu
            
            }
            
            if (!username.matches(".*\\d.*")){ // If username doesnt contains digit
                
                if(!usernameExists(username)){ // If username doesnt same as the username that we register during registration
                
                    showMessageDialog(null,"Invalid username. Please enter a CORRECT username", "Error!", ERROR_MESSAGE);
                
                } else {
                
                    break;
                
                }
            
            } else {
                
                showMessageDialog(null,"Invalid username. Please enter a CORRECT username(without digits)", "Error!", ERROR_MESSAGE);
                
            }
        }
        

        while (true){
            
            String stringpassword = showInputDialog(null, "Please enter your password", "Login", QUESTION_MESSAGE);
                
                if (stringpassword == null){
                    
                    showMessageDialog(null, "Login cancelled.", "Login", ERROR_MESSAGE);
                    return false; // Back to the Authentication menu 
                    
                }    
                    
                    if (stringpassword.matches("\\d+")) { // If the password only contains digits
                            
                        password = Integer.parseInt(stringpassword);
                
                        if(passwordExists(password)){ // If the password is same with the password that we register during registration
                            
                            if (CREDENTIALS.get(username) == password) { // If the password is pair with the username that we register during registration
                                
                                showMessageDialog(null,"Login Successful !", "Login", PLAIN_MESSAGE);
                                showMessageDialog(null,"Welcome to Kawaguchi Bank Car Loan System", "Login", PLAIN_MESSAGE);
                                return true;
                                
                            } else {
                                
                                showMessageDialog(null,"Invalid password. Please enter a CORRECT password", "Error!", ERROR_MESSAGE);
                                
                            }
                            
                        } else {
                            
                            showMessageDialog(null,"Invalid password. Please enter a CORRECT password", "Error!", ERROR_MESSAGE);
                            
                        }

                    } else {
                        
                        showMessageDialog(null,"Please enter a VALID password (without alphabet)", "Error!", WARNING_MESSAGE);
                        
                    }

                }  
     
        }
    
}