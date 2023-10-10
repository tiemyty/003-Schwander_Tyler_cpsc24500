import java.util.ArrayList;
import java.util.Scanner;

public class cellPhoneOrder {
    public static void main(String[] args){
        //Heading
        System.out.print("*************************************\nCORPORATE CELL PHONE ORDERING SYSTEM\n*************************************\n");
        System.out.print("Welcome to the corporate cell phone ordering system.\nHere you can order multiple phones of different kinds\nwith various add-ons for your employees.\n");

        phoneConfigure();
    }


    //Function for phone configuration
    public static void phoneConfigure(){
        //Creating an array for each phone and their details
        ArrayList<String> phoneConfigurations = new ArrayList<>();
        Scanner myobj = new Scanner(System.in);

        System.out.println("How many phones will you purchase? ");
        int phoneAmt = myobj.nextInt();
        myobj.nextLine();
        //I have to declare this outside of the loop or it doesn't work.
        double bigPrice = 0;
        //For loop to gather all information on phones, resetting the stringbuilder and adding +1 phone count until the order requested is reached
        for (int phoneCount = 1; phoneCount <= phoneAmt; phoneCount++){
            StringBuilder phoneAdds = new StringBuilder();
            double phonePrice = 0;
            int storagePrice = 0;

            //Displays which phone we're on and asks for type
            System.out.println("Lets configure phone #" + phoneCount + "... ");
            System.out.println("Enter A for Android or I for IPhone: ");
            String phoneType = myobj.nextLine();
            if (phoneType.equals("A")){
                phoneType = "Android";
                phonePrice = 799;
            }else if (phoneType.equals("I")){
                phoneType = "IPhone";
                phonePrice = 849;
            }
            //Screen type
            System.out.print("What size screen? Enter 1 for 5.6, 2 for 6.2, or 3 for 6.7: ");
            String screenType = myobj.nextLine();
            if (screenType.equals("1")){
                screenType = "5.6\"";
            }else if (screenType.equals("2")){
                screenType = "6.2\"";
                phonePrice = phonePrice + (phonePrice*.20);
            }else if (screenType.equals("3")){
                screenType = "6.7\"";
                phonePrice = phonePrice + (phonePrice*.40);
            }
            //Storage type
            System.out.print("How much storage? Enter 64, 128, or 256: ");
            String storageType = myobj.nextLine();
            if (storageType.equals("64")){
                storageType = "64GB";
            }else if (storageType.equals("128")){
                storageType = "128GB";
                storagePrice = 100;
            }else if (storageType.equals("256")){
                storageType = "256GB";
                storagePrice = 250;
            }

            //declaring total price
            double totalPrice = 0;
            totalPrice = phonePrice + storagePrice;
            //This while loop utilizes switch to continously add each add-on to the stringbuilder until user finishes order.
            //Then it will add all the information to the array and reset to the beginning of the for loop, where it continues here again until phone count is reached.
            while (true){
                System.out.print("What add-on do you want?\n[C]ase ($49)\n[S]creen protector ($15)\n[E]ar buds ($99)\n[W]ireless charger ($59)\n[F]inish order\n");
                String choice = myobj.nextLine();

                switch (choice.toUpperCase()) {
                    case "C":
                        phoneAdds.append("/ Case ($49)");
                        totalPrice += 49;
                        break;
                    case "S":
                        phoneAdds.append("/ Screen protector ($15)");
                        totalPrice += 15;
                        break;
                    case "E":
                        phoneAdds.append("/ Ear buds ($99)");
                        totalPrice += 99;
                        break;
                    case "W":
                        phoneAdds.append("/ Wireless charger ($59)");
                        totalPrice += 59;
                        break;
                    case "F":
                        String configuration = "Phone #" + phoneCount + " " + phoneType + " " + screenType + " " + storageType + " " + phoneAdds.toString() + " " + "($" + totalPrice + ")\n";
                        phoneConfigurations.add(configuration);
                        bigPrice += totalPrice;
                        //breaks the while loop and moves to the if statement.
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                      }
                //This is where the user resets back to the for loop, +1 to phoneCount and moving on.      
                if (choice.equalsIgnoreCase("F"))
                        break;

            }
        }
        myobj.close();
        //Summarizing order
        bigPrice = Double.parseDouble(String.format("%.2f", bigPrice));
        System.out.println("Here is a summary of your order:\n" + phoneConfigurations);
        System.out.println("Your total cost is: $" + bigPrice + ".\n" + "Thank you for your order.");

        
    }
}
                

    
    
    
