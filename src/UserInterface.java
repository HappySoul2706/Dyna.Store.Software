import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    double Cash_On_Hand = 100.00;
    ArrayList<merch> inventory;
    Scanner input; //fixed memory leak

    UserInterface() {

        //array list for storing a list of objects.
        inventory = new ArrayList<>();
        input = new Scanner(System.in); //this plugs the memory leak down below

        //adds the default items to the inventor. Replace this with file input if we have time.
        merch i001 = new merch("Uranium - 238", 30.56, 2418);
        merch i002 = new merch("Thorium - 232", 42.68, 1272);
        merch i003 = new merch("Curium - 247", 88.12, 463);
        merch i004 = new merch("Plutonium - 244", 68.22, 8732);
        merch i005 = new merch("Protactinium - 231", 278.67, 23);
        merch i006 = new merch("Neptunium - 237", 688.71, 6);
        merch i007 = new merch("Americium - 243", 321.16,18);
        merch i008 = new merch("Berkelium - 247", 126.64, 88);
        merch i009 = new merch("californium - 251", 345.55, 56);
        merch i0010 = new merch("Einsteinium - 252", 661,78);

        inventory.add(i005);
        inventory.add(i002);
        inventory.add(i006);
        inventory.add(i001);
        inventory.add(i007);
        inventory.add(i004);
        inventory.add(i003);
        inventory.add(i008);
        inventory.add(i009);
        inventory.add(i0010);

    }

    void display() throws InterruptedException {

        //Out of stock warning-----------------------------------------------------------------
        //look through the list for any stock that is < 1
        //print one line for each item similar to the following:
        //WARNING - <ITEM NAME> is out of stock. please restock.

        for (merch item : inventory ) {
            if (item.quantityInStock < 5) {
                Thread.sleep(300);
                System.out.println("Warning: " + item.name + " is running low on stock.\r\n");
            }
            else {
                Thread.sleep(300);
                System.out.println( item.name + " Mineral quantity In Stock nominal.\r\n");
            }
    }
        //Use - ForEach loop. System.out and compare
        //-------------------------------------------------------------------------------------

    //speech thingy I figured out I can do, apparently.
        /**
         * hey it worked lets gooo
         */


        //Inventory load reminder
        System.out.println("Loading Inventory BootLoader:");

        for ( merch item : inventory) {
            Thread.sleep(300);
            System.out.println(item.name);
            Thread.sleep(300);
            System.out.println("Quantity (by the Pound): " + item.quantityInStock + "        $/Lbs  " + item.price + "\r\n" );

        }

    }

    void AddNewItem(){
        System.out.println("Welcome to the New Product Wizard");
        //*fixed memory leak*
        // input = new Scanner(System.in); //memory leak

        System.out.println("Please enter the name of the new product:");
        String TempName = input.nextLine();

        System.out.println("Please enter the price of the new product:");
        double TempPrice = input.nextDouble();
        input.nextLine(); // Purge the scanner. Avoid Bug

        System.out.println("Please enter how much stock there is for the new product:");
        int TempStock = input.nextInt();
        input.nextLine();

        //Create the object with these temp properties.

        merch tempMerch = new merch(TempName, TempPrice, TempStock);

        inventory.add(tempMerch);

    }

    void RemoveItem(){

        System.out.println("Welcome to the Product removal wizard");

        merch target = SelectItem("Remove");

        //Delete the target - this must be separate.
        if( target != null ) {
            inventory.remove(target);
        } else{
            System.out.println("Error: 6520 - Item Not Found.\r\n");
        }

    }

    void EditItem(){

        System.out.println("Welcome to the Edit Product wizard");

        merch target = SelectItem("edit");

        //Delete the target - this must be separate.
        if( target != null ) {
            inventory.remove(target);
            System.out.println("Please enter the changed name of the product:");
            String TempName = input.nextLine();

            System.out.println("Please enter the new price of the product:");
            double TempPrice = input.nextDouble();
            input.nextLine(); // Purge the scanner. Avoid Bug

            System.out.println("Please enter how much stock there is for the product:");
            int TempStock = input.nextInt();
            input.nextLine();

            //Create the object with these temp properties.

            merch tempMerch = new merch(TempName, TempPrice, TempStock);

            inventory.add(tempMerch);
        } else{
            System.out.println("Error: 6520 - Item Not Found.\r\n");
        }

    }

    //Reduce duplicate code using a selectItem() method.

    merch SelectItem(String word){
        System.out.println("Enter the name of the product you want to " + word + ".");
        String targetName = input.nextLine();
        merch target = null;

        //Find the target in list.
        for (merch item : inventory
        ) {
            if(item.name.equalsIgnoreCase(targetName)){
                target = item;  //Found it.
            }
        }

        return target;
    }

    void UILoop() throws InterruptedException {

        System.out.println("Welcome to Dyna Store.");
        System.out.println("Dyna Store Software developed and distributed by Anderson Computer Solutions.Co");
        System.out.println("Software Version - 1.0.1                                                                 2023 All right reserved ");
        System.out.println("No copies of this software are to be made by user or third parties. By using Dyna Store you agree to these terms.");
        System.out.println("Booting low stock warning.....Standby\r\n");
        Thread.sleep(600);


        while (true) {

            display();
            System.out.println("Cash on Hand: $" + Cash_On_Hand);
            System.out.println("Main Menu:");
            System.out.println("1. Sale\n2. Add a new product.\n3. Edit\n4. Remove\n5. Restock");
            String response = input.nextLine();

            if (response.toLowerCase().contains("sale") || response.contains("1")) {

                merch toBeSold = SelectItem("sell");

                if (toBeSold == null) {
                    System.out.println("Item is not found.");
                } else {
                    System.out.println("How much " + toBeSold.name + " do you want to sell?");
                    int number = input.nextInt();
                    input.nextLine(); //don't forget the bug.

                    Cash_On_Hand += toBeSold.Sell(number);

                }


            } else if (response.toLowerCase().contains("add") || response.contains("2")) {

                AddNewItem(); //Wizard takes care of the rest.
            } else if (response.toLowerCase().contains("remove") || response.contains("3")) {

                RemoveItem(); //Wizard takes care of the rest.
            } else if (response.toLowerCase().contains("edit") || response.contains("4")) {

                EditItem(); //Wizard takes care of the rest
            } else if (response.toLowerCase().contains("restock") || response.contains("5")) {

                merch toBeRestocked = SelectItem("restock");

                if (toBeRestocked == null) {
                    System.out.println("Item is not found.");
                } else {
                    System.out.println("How much " + toBeRestocked.name + " do you want to Restock?");
                    int numb = input.nextInt();
                    input.nextLine(); //don't forget the bug.

                    toBeRestocked.restock(numb);

                }
            }

            else {
                System.out.println("That command is invalid.");
            }
        }

        }
    }

