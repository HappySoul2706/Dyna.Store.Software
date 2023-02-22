public class merch {

    // Merch Properties
    //price (double)
    //name (String)
    //quantity (int)

    String name;
    double price;
    int quantityInStock;

    //Constructor
    merch(String name, double price, int quantityInStock){

        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;

    }

    //Restock() Method
    void restock(int NewStock){

        //adds the new stock to the old stock amount
        quantityInStock += NewStock;

    }

    //Sell() Method
    double Sell(int QauntitySold){


        if(QauntitySold>quantityInStock){

            System.out.println("ERROR: There are only" + quantityInStock + "in Stock");
            System.out.println("Sale Cancelled");

            return 0d;
        }

        else{

            quantityInStock -= QauntitySold;
            return(price * QauntitySold);



        }
    }

}
