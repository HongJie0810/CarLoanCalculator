import java.awt.Dimension;
import java.util.List;
import static javax.swing.JOptionPane.*;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;

public class displayScheme {
    
    // Method that use to display the car loan scheme
    public void displayCarloanScheme(){
        
        StringBuilder table = new StringBuilder();
        
        table.append("===========================================================\n");
        table.append("Car Type\t\tLoan Amount (RM)\tInterest Rate (%)\n");
        table.append("===========================================================\n");
        table.append("Imported\t\t      >300,000\t\t        2.35%\n");
        table.append("\t\t100,000 ~ 300,000\t        2.55%\n");
        table.append("\t\t      <100,000\t\t        2.75%\n");
        table.append("----------------------------------------------------------------------------------------------------------\n");
        table.append("Local\t\t      >100,000   \t\t        3.0%\n");
        table.append("\t\t50,000 ~ 100,000\t        3.1%\n");
        table.append("\t\t        <50,000\t\t        3.2%\n");

        JTextArea textArea = new JTextArea(table.toString());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200)); // Set your preferred size
        showMessageDialog(null, scrollPane, "Carloan Scheme", PLAIN_MESSAGE);
        
    }
     
    
    // The method takes a CarLoanCalculator object as a parameter, and we can directly call methods on that object without explicitly creating a new instance within the method.
    public void displayUserInput(carLoanCalc calculator){ 
        
        StringBuilder table = new StringBuilder();
        
        table.append("Car Type: ").append(calculator.getCarType().toUpperCase()).append("\n\n");
        table.append("Loan Amount: RM ").append(String.format("%.2f", calculator.getLoanAmount())).append("\n");
        table.append("Interest Rate: ").append(calculator.getInterestRate()).append("%").append("\n");
        table.append("Loan Term: ").append(calculator.getLoanTerm()).append(" years").append("\n\n");
        table.append("Insurance Status: ").append(calculator.hasInsurance() ? "Purchased" : "Not Purchased").append("\n");
       
        if (calculator.hasInsurance()) {
            table.append("Insurance Amount (").append(calculator.getLoanTerm()).append("*").append("200)").append(" : RM").append(calculator.getLoanTerm()*200).append("\n\n");
        }
        
        table.append("Monthly Installment : RM ").append(String.format("%.2f", calculator.calculateMonthlyInstallment())).append("\n");

        showMessageDialog(null, table.toString(), "Car Loan Information", INFORMATION_MESSAGE);
        
        
    }

    // Method for display the summary report by using array list
    public void displaySummaryReport(List <carLoanCalc> calculators) {
        
        // Declare and initiate the variables
        int numTransPurchasedLocal = 0;
        int numTransNPurchasedLocal = 0;
        int numTransPurchasedImported = 0;
        int numTransNPurchasedImported = 0;
        int totalTransactions = 0;
        double loanAmountPurchasedLocal = 0;
        double loanAmountNotPurchasedLocal = 0;
        double loanAmountPurchasedImported = 0;
        double loanAmountNotPurchasedImported = 0;
        double totalLoanAmount = 0;

       JFrame frame = new JFrame("Summary Report");
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   
       JTextArea textArea = new JTextArea();
       textArea.setEditable(false);
   
       JScrollPane scrollPane = new JScrollPane(textArea);
       frame.add(scrollPane);
   
       StringBuilder summary = new StringBuilder();
       summary.append("====================================================================================\n");
       summary.append("Car Type\t\tInsurance Status\tNumber of Transactions\tLoan Amount (RM)\n");
       summary.append("====================================================================================\n");
        

   
        for (carLoanCalc c : calculators) { 

        double totalLoan = c.getLoanAmount();

            if (c.hasInsurance()) {

                totalLoan += c.getLoanTerm() * 200;
            
            }

            if (c.getCarType().equalsIgnoreCase("Local")) {

                if (c.hasInsurance()) {

                   numTransPurchasedLocal++;
                   loanAmountPurchasedLocal += totalLoan;
                     

                } else {

                   numTransNPurchasedLocal++;
                   loanAmountNotPurchasedLocal += totalLoan;
                       
                }

            } else {

                if (c.hasInsurance()) {

                    numTransPurchasedImported++;
                    loanAmountPurchasedImported += totalLoan;
                   

                } else {

                    numTransNPurchasedImported++;
                    loanAmountNotPurchasedImported += totalLoan;
                  
                }

            }
           
        }

        // If the number transaction is greater than 0, only it will be display on the summary report
        if (numTransPurchasedLocal > 0){
           
            appendSummaryEntry(summary, numTransPurchasedLocal, "Local", "Purchased", loanAmountPurchasedLocal);
           
        }
       
        if (numTransNPurchasedLocal > 0){
           
            appendSummaryEntry(summary, numTransNPurchasedLocal, "Local", "Not Purchased", loanAmountNotPurchasedLocal); 
           
        }
       
        if (numTransPurchasedImported > 0){
           
            appendSummaryEntry(summary, numTransPurchasedImported, "Imported", "Purchased", loanAmountPurchasedImported);
           
        }
       
        if (numTransNPurchasedImported > 0){
           
            appendSummaryEntry(summary, numTransNPurchasedImported, "Imported", "Not Purchased", loanAmountNotPurchasedImported);
           
        }

        totalTransactions = numTransPurchasedLocal + numTransNPurchasedLocal + numTransPurchasedImported + numTransNPurchasedImported;
        totalLoanAmount = loanAmountPurchasedLocal + loanAmountNotPurchasedLocal + loanAmountPurchasedImported + loanAmountNotPurchasedImported;
       
        summary.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
        summary.append("-\t\t").append("-\t\t").append(totalTransactions).append("\t\t").append(String.format("%.2f", totalLoanAmount)).append("\n");
        summary.append("-----------------------------------------------------------------------------------------------------------------------------------------------------\n");
       
        JTextArea summaryR = new JTextArea(summary.toString());
        textArea.setEditable(false);

        JScrollPane disp = new JScrollPane(summaryR);
        disp.setPreferredSize(new Dimension(600, 400));

        showMessageDialog(null, summaryR, "Summary Report", PLAIN_MESSAGE);
       
    }

    // Helper Method
    private void appendSummaryEntry(StringBuilder summary, int numTransactions, String carType, String insuranceStatus, double loanAmount) {
        
        summary.append(carType).append("\t\t")
               .append(insuranceStatus).append("\t\t")
               .append(numTransactions).append("\t\t")
               .append(String.format("%.2f", loanAmount)).append("\n");

   }
   

}