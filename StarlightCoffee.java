import java.util.Scanner;

public class StarlightCoffee {
    public static void printHeading() {
        // Heading for customer
        System.out.print("*************************************\nSTARLIGHT COFFEE POINT-OF-SALE SYSTEM\n*************************************\n");
    }

    public static void main(String[] args) {
        printHeading();
        // input for name
        Scanner name = new Scanner(System.in);
        String userName;
        // Assigning name to variable userName
        System.out.println("What is your name? ");
        userName = name.nextLine();

        // Assigning coffee integer to coffeeType variable
        int coffee;
        System.out.println("What kind of coffee do you want? \n 1. Americano \n 2. Italiano \n 3. Espresso \n 4. Cappuccino \nEnter the number of your choice: ");
        Scanner coffeeType = new Scanner(System.in);
        coffee = coffeeType.nextInt();
        
        // Assigning Size
        int size;
        System.out.println("What size do you want? \n 1. Tall \n 2. Grande \n 3. Venti \nEnter the number of your choice:  ");
        Scanner sizeType = new Scanner(System.in);
        size = sizeType.nextInt();

        // Espresso Shots
        int shot;
        System.out.println("How many extra shots of espresso would you like? ");
        Scanner shotnumber = new Scanner(System.in);
        shot = shotnumber.nextInt();

        //Membership input
        System.out.println("Are you a member of Starlight Stars (y or n)? ");
        Scanner member = new Scanner(System.in); 
        String membership;
        membership = member.nextLine();
        
        //Tip decision
        int tip;
        System.out.println("What size tip would you like to leave?\n 1. Good Service - 10%\n 2. Great Service - 15%\n 3. Outstanding Service - 20%\nEnter the number of your choice: ");
        Scanner tipPercent = new Scanner(System.in);
        tip = tipPercent.nextInt();
        double discount;
        discount = 0;
    
        printBill(coffee, userName, size, shot, membership, tip, discount);
    }

 
    public static void printBill(double coffee, String userName, int size, double shot, String membership, double tip, double discount){
        // Determining coffee price with each option
        if (coffee == 1) {
            coffee = 2.25;
        } else if (coffee == 2) {
            coffee = 2.75;
        } else if (coffee == 3) {
            coffee = 3.50;
        } else if (coffee == 4) {
            coffee = 3.75;
        }
        
        // Determining size upcharge
        if (size ==  1){
            size = 0;
        } else if (size == 2){
            coffee = coffee + (coffee*.20);
        } else if (size ==  3){
            coffee = coffee + (coffee*.40);
        }
        //Determining espresso shots
        shot = .50*shot;

        //membership discount
        if (membership.equals("y")){
            coffee = coffee + shot;
            discount = (coffee*.10);
            coffee = coffee - discount;
        } else if(membership.equals("n")){
            discount = 0;
        }
            
        
        //tip percentage
        if (tip == 1){
            tip = coffee * .10;
        }else if (tip == 2){
            tip = coffee *.15;
        }else if (tip == 3){
            tip = coffee *.20;
        }
        //tax
        double tax;
        tax = (coffee / 8.75);
        tax = Double.parseDouble(String.format("%.2f", tax));

        //formatting decimals
        tip = Double.parseDouble(String.format("%.2f", tip));
        coffee = Double.parseDouble(String.format("%.2f", coffee));
        discount = Double.parseDouble(String.format("%.2f", discount));

        //calculating total
        double total;
        total = coffee + tip + tax;
        total = Double.parseDouble(String.format("%.2f", total));

        
        



        
        System.out.println("Here is your bill " + userName + ":");
        System.out.println("Coffee            $" + coffee);
        System.out.println("Club Discount     $" + discount);
        System.out.println("Tip Amount        $" + tip);
        System.out.println("Taxes             $" + tax);
        System.out.println("Total             $" + total);

        }
}
