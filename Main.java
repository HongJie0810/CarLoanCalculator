import static javax.swing.JOptionPane.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        
        // Declare and initialize variables
        int choice = 0;
        int selection = 0;
        String username = null;
        int password = 0;
        
        // Create array list object
        List <carLoanCalc> singleCalcList = new ArrayList<>();
        
        // Create class object
        userInput input = new userInput();
        displayScheme scheme = new displayScheme();
        User user = new User(username,password);
        
        while(true){
            
            // Authentication Menu
            String Authentication_Menu = """
                                              1. Register
                                              2. Login
                                              3. Exit programme
                          
                                              Please enter your selection:""";
            
            try{
                
                String selectionString = showInputDialog(null,Authentication_Menu,"User_Authentication_Menu",PLAIN_MESSAGE);
                
                if (selectionString != null){
                    
                    selection = Integer.parseInt(selectionString);
                    
                } else {
                    
                    showMessageDialog(null,"Error !");
                    continue; // continue looping
                    
                }
                
                switch (selection) {
                    
                    case 1:
                        
                        user.RegisterUser();
                        break;
                        
                    case 2:
                        
                       if(user.Login()){
                        
                        do{
                            // car loan main menu
                            String menu = "Username: " + user.getUsername() + "\n\n"
                                        + "1. Display Car Loan Scheme\n"
                                        + "2. Calculate Car Loan Installment\n"
                                        + "3. Generate Summary Report\n"
                                        + "4. Exit / Log out";
                                      
                            
                            try{
                                
                                String choiceString = showInputDialog(null, menu + "\nEnter your choice:", "Main Menu", PLAIN_MESSAGE);
                                
                                if (choiceString != null) {
                                    
                                    choice = Integer.parseInt(choiceString);
                                    
                                } else {
                                    
                                    showMessageDialog(null, "Error !", "Error", ERROR_MESSAGE);
                                    continue;
                                    
                                }
                                
                                switch (choice) {
                                    
                                    case 1:
                                        
                                        scheme.displayCarloanScheme();
                                        break;
                                        
                                    case 2:
                                        
                                        carLoanCalc calculator = input.getUserInput(scheme);
                                        
                                        if(calculator != null){
                                            
                                           singleCalcList.add(calculator); 
                                            
                                        }
                                        
                                        break;
                                        
                                    case 3:
                                        
                                        if (!singleCalcList.isEmpty()) { // if the summary report is empty

                                            scheme.displaySummaryReport(singleCalcList);

                                        } else {

                                            showMessageDialog(null, "Please input car loan details first.", "Error", ERROR_MESSAGE);
                                        
                                        }
                                        break;
                                        
                                    case 4:
                                        
                                        break;
                                        
                                    default:
                                        
                                        showMessageDialog(null, "Invalid choice. Please enter a VALID option.");
                                        
                                }
                                
                            } catch (NumberFormatException e){
                                
                                showMessageDialog(null,"Please enter a VALID integer (1 ~ 4) not alphabet ");
                                
                            }
                            
                        } while(choice != 4);
                        
                    }   
                        break;
                        
                    case 3:
                        
                        showMessageDialog(null, "Exiting the program. Goodbye!");
                        System.exit(0); // Exit the programme
                        
                    default:
                        
                        showMessageDialog(null,"Please enter a VALID integer (1 ~ 3) ");
                        break;
                }
                
            } catch (NumberFormatException e){
                
                showMessageDialog(null,"Please enter a VALID integer (1 ~ 3) not alphabet ");
                
            }

        } 
    
    }
    
}
