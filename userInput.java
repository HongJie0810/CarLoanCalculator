import static javax.swing.JOptionPane.*;

public class userInput {
    
          // returntype //          // pass variables value //
    public carLoanCalc getUserInput(displayScheme scheme){ // return type return the value like loanAmount, carType and more
        
        // Declare and initiate variables
        String carType = null;
        double loanAmount = 0;
        double loanTerm = 0;
        double interestRate = 0;

        while(true){
            
            carType = showInputDialog(null, "Please selected your car type (Imported / Local)", "Car Type", QUESTION_MESSAGE );
            
            if(carType == null){
            
                showMessageDialog(null,"Calculation Cancelled","Car Type", WARNING_MESSAGE);
                return null;
            
            }
        
            if (carType.equalsIgnoreCase("Imported") || carType.equalsIgnoreCase("Local")) {
            
                showMessageDialog(null,"Your selected car type : " + carType.toUpperCase(), "Car Type", QUESTION_MESSAGE);
                break;
            
            }  else {
         
                showMessageDialog(null,"Invalid input. Please re-enter an VALID input (Imported / Local)", "Errpr!", WARNING_MESSAGE);

            }
            
        }
        
        
        do{
           
            String stringloanAmount = showInputDialog(null, "Please enter your desired car loan amount (RM) ", "Car Loan", QUESTION_MESSAGE);
            
            if(stringloanAmount != null){    
            
                try{
                
                    loanAmount = Double.parseDouble(stringloanAmount);
                
                    if (loanAmount > 0){
                    
                        showMessageDialog(null,"Your selected loan amount : RM " + String.format("%.2f", loanAmount), "Car Loan", PLAIN_MESSAGE);
                        break;
                    
                    } else {
                    
                        showMessageDialog(null,"Invalid input. Please enter a POSITIVE amount", "Error!", WARNING_MESSAGE);
                    
                    }
                  
                } catch (NumberFormatException e){
                
                    showMessageDialog(null,"Invalid input. Please enter VALID amount (without alphabet)", "Error!", WARNING_MESSAGE);
                
                }
            
            } else {
            
                showMessageDialog(null,"Calculation Cancelled.", "Car Loan", WARNING_MESSAGE);
                return null;
            
            }
            
        } while (loanAmount <= 0);    

              
        do{
            
            String stringloanTerm = showInputDialog(null, "Please enter the loan term (years)", "Loan Term", QUESTION_MESSAGE);
            
            if(stringloanTerm != null){
                
                try{
         
                    loanTerm = Double.parseDouble(stringloanTerm);

                    if (loanTerm > 0 && loanTerm <= 9){

                        showMessageDialog(null,"Your selected loan amount : " + loanTerm + " years", "Loan Term", PLAIN_MESSAGE);
                        break;

                    } else {

                        showMessageDialog(null,"Invalid input.Please enter a VALID loan term (1 ~ 9)", "Error!", WARNING_MESSAGE);

                    }

                } catch (NumberFormatException e){

                    showMessageDialog(null,"Invalid input. Please enter VALID value (without alphabet)", "Error!", WARNING_MESSAGE);

                }

            } else {
                
                showMessageDialog(null,"Calculation Cancelled.", "Loan Term", WARNING_MESSAGE);
                return null;
                
            }
            
        } while (loanTerm <= 0 || loanTerm > 9);
        
                                                                                             //title//
        int insuranceChoice = showConfirmDialog(null, "Do you want to purchase insurance?", "Insurance", YES_NO_OPTION);

        if (insuranceChoice == CLOSED_OPTION) { // User closed the dialog window
            
            showMessageDialog(null, "Calculation Cancelled", "Insurance", WARNING_MESSAGE);
            return null; // Return to the main menu
            
        }

        boolean hasInsurance = insuranceChoice == YES_OPTION;
        
        carLoanCalc loan = new carLoanCalc(carType, loanAmount, interestRate, loanTerm, hasInsurance); // Creating class object
        scheme.displayUserInput(loan); // Display Car loan Information
        
         
        return loan;
    
    }
     
}