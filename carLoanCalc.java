public class carLoanCalc {
    
    //Declare variables
    private String carType; // can put array 
    private double loanAmount;
    private double loanTerm;
    private boolean hasInsurance;
    private double interestRate;
    
    
    //Constructor
    public carLoanCalc(String carType, double loanAmount, double interestRate, double loanTerm, boolean hasInsurance){
    
        this.carType = carType;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.hasInsurance = hasInsurance;
        setInterestRate(loanAmount); // It is not user input so we cannot use return
        // other method parameters have interestRate so we need to declare interestRate in the constructor
    
    }
    
    
    //Setter method
    public void setCarType(String carType){
        
        this.carType = carType;
        
    }
    
    public void setLoanAmount(double loanAmount){
        
        this.loanAmount = loanAmount;
        
    }
    
    // Method for setting constant interest rate
    private void setInterestRate(double loanAmount) {
        
        if (carType.equalsIgnoreCase("Imported")) { 
            
            if (loanAmount > 300000) {
                
                interestRate = 2.35;
                
            } else if (loanAmount >= 100000 && loanAmount <= 300000) {
                
                interestRate = 2.55;
                
            } else if (loanAmount > 0 && loanAmount < 100000){
                
                interestRate = 2.75;
                
            }
            
        } else if (carType.equalsIgnoreCase("Local")) {
            
            if (loanAmount > 100000) {
                
                interestRate = 3.0;
                
            } else if (loanAmount >= 50000 && loanAmount <= 100000) {
                
                interestRate = 3.1;
                
            } else if (loanAmount > 0 && loanAmount < 50000){
                
                interestRate = 3.2;
                
            }
            
        }
    }
    
    public void setLoanTerm(double loanTerm){
        
        this.loanTerm = loanTerm;
        
    }
    
    public void setHasInsurance(boolean hasInsurance){
        
        this.hasInsurance = hasInsurance;
        
    }
    
    // Getter method
    public String getCarType(){
    
        return carType;
        
    }
    
    public double getLoanAmount(){
        
        return loanAmount;
        
    }
    
    public double getLoanTerm(){
        
        return loanTerm;
        
    }
    
    public boolean hasInsurance(){
        
        return hasInsurance;
        
    }
    
    public double getInterestRate(){
        
        return interestRate;
        
    }
    
    
    // Method for calculate the monthly installment
    public double calculateMonthlyInstallment(){
        
        double annualInterestRate = interestRate/100; //make the percentage into decimal
        double monthlyInterestRate = annualInterestRate/12;
        double totalMonth = loanTerm * 12;
        double totalLoanAmount = (hasInsurance == true) ? loanAmount + (200 * loanTerm) : loanAmount;
        double monthlyInstallment = (totalLoanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, - totalMonth));//formula
        
        return monthlyInstallment;
         
    }
    
}
