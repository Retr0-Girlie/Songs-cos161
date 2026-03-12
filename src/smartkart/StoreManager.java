package smartkart;
/**StoreManager 
 * (Iris and Maddy)
 * 
 * The users menu driven console  
 *
 * Attributes:
 * 	-private ArrayList<CartItem> cart;	contains all items in the cart
 * 	-private ArrayList<Product> inventory; Contains all items in the stores inventory
 * 
 * Methods:
 * 	-viewCart()	The method for printing all the items in cart
 *  -viewInventory() The method for printing all the items in inventory
 *  -addToCart() The method for adding a product to cart returns false if fails
 *  -FileReader() Used to read the tsv file and set inventory to have values
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class StoreManager {
	public static ArrayList<CartItem> cart = new ArrayList<CartItem>();
	public static ArrayList<Product> inventory = new ArrayList<Product>();
	
	/**For displaying Cart
	 * (Iris)
	 * 
	 * Prints out all items in the cart
	 */
	public static void viewCart() {
		System.out.println("Items in cart \n------------");
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getProducts().getInfo());
		}
	}
	/**For displaying Inventory
	 * (Iris)
	 * 
	 * Prints out all items in inventory
	 */
	public static void viewInventory() {
		System.out.println("Items in inventory \n------------");
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(inventory.get(i).getInfo());
		}
	}
	/**For adding products to cart
	 * (Iris)
	 *
	 *@return	 returns false if it fails due to an invalid entry along the process else returns true
	 */
	public static boolean addToCart(Scanner scanner) {
		System.out.println("Please enter product ID:");
		String userIdInput = scanner.nextLine();
		String error = "";
		boolean success = false;
		for(int i = 0; i < inventory.size(); i++) {
			//checking if product is real
			if(userIdInput.equals(inventory.get(i).getId())) {
				System.out.println("Please enter quantity of " + inventory.get(i).getName() + ":");
				int userQuantityInput = scanner.nextInt();
				error = "";
				//checking stock and making sure a valid quantity is entered
				if(userQuantityInput <= inventory.get(i).getStock() && userQuantityInput > 0) {
					error = "";
					//checking if a grocery product and not expired
					if(inventory.get(i).isExpired() == false) {
						error = "";
						inventory.get(i).purchase(userQuantityInput);
						success = true;
						CartItem newItem = new CartItem(inventory.get(i), userQuantityInput);
						cart.add(newItem);
						System.out.println(userQuantityInput + " " + inventory.get(i).getName() + " added to cart. :3 \n ------------ \n");
						break;
					}else {
						error = ("Error:Item expired");
						break;
					}
					
				}else {
					error = ("Error:Invalid user input. Enter a positive number or not enough stock");
					break;
				}
			}else {
				error = ("Error:Invalid user input. ID not found.");
			}
		}
		System.out.println(error);
		return success;
	}
	
    /**Reading tsv file
     * (Iris)
     * 
     * Takes the file and adds it's content to the inventory
     * 
     * @param filePath The string of the files location or path
     */
    public static void FileReader(String filePath) {
//        int itemCount = 0;
        
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            
            // Skip the header line
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            
            // Read each line and create appropriate objects
            while (scanner.hasNextLine()) {/*May need to add "&& itemCount < 20" back in idk*/
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                
                if (parts.length >= 8) {
                    String type = parts[0];
                    String itemId = parts[1];
                    String name = parts[2];
                    String price = parts[3];
                    String quantityInStock = parts[4];
                    String daysTillExpired = parts[5];
                    String brand = parts[6];
                    String size = parts[7];
                    String material = parts[8];
                    double priceD = Double.valueOf(price);
                    int quantity = Integer.valueOf(quantityInStock);
                    
                    //Added this check because it was throwing a null pointer exception and breaking the loop when it hit an item with no exp date ~Maddy
                    int days = 0;
                    if (type.equals("Grocery"))
                    {
                    	days = Integer.valueOf(daysTillExpired);
                    }
                    
                    // Create the appropriate type of item
                    switch (type) {
                        case "Grocery":
                            
                            Product Groc = new Grocery(itemId, name, priceD, quantity, days);
                            inventory.add(Groc);
                            break;
                            
                        case "Electronic":
                            
                            Product Elect = new Electronic(itemId, name, priceD, quantity, brand);
                            inventory.add(Elect);
                            break;
                            
                        case "Clothing":
                            Product cloth = new Clothing(itemId, name, priceD, quantity, size, material);
                            inventory.add(cloth);
                            break;
                    }
//                    itemCount++;
                }
            }
            scanner.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from file");
            e.printStackTrace();
        }
    }
    /**prints out the options menu
     * (Iris)
     */
    public static void options() {
	    System.out.println("Options:");
		System.out.println("Enter 1 for view inventory");
		System.out.println("Enter 2 to add an item to cart");
		System.out.println("Enter 3 for view cart");
		System.out.println("Enter 4 for returns");
		System.out.println("Enter 5 to checkout");
    }
    /**for the main part of our menu-acts as home page
     * (Iris)
     * 
     */
    public static void selection(Scanner scanner) {
        StoreManager.options();
        String attempt = scanner.nextLine();
        int selection1 = Integer.parseInt(attempt);

        if(selection1 == 1) {
            StoreManager.viewInventory();
        } else if(selection1 == 2) {
            StoreManager.addToCart(scanner);
        } else if(selection1 == 3) {
            StoreManager.viewCart();
        } else if(selection1 == 4) {
            StoreManager.returnItem(scanner);
        } else if(selection1 == 5) {
            StoreManager.checkout();
        }
    }
    /**for selecting whether to continue in the main menu or not
     * (Iris)
     * 
     * @return 1 to continue or 2 to quit
     */
    public static int selection2(Scanner scanner) {
        System.out.println("Enter 1 to return to options or enter 2 to quit:");
        int number = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return number;
    }
    
    /**Calculates total/tax and prints a receipt
     * (Maddy)
     * 
     */
    public static void checkout()
    {
    	double total = 0;
    	double taxTotal = 0;
    	double price = 0;
    	double tax = 0;
    	
    	for (CartItem item : cart)
    	{
    		System.out.println(item.products.getStock()-item.quantityOfProducts);
    		item.products.setStock(item.products.getStock()-item.quantityOfProducts);
    		price = item.products.getPrice() * item.quantityOfProducts;
    		tax = item.products.calculateTax(item.quantityOfProducts);
    		
    		total += price; 
    		taxTotal += tax;
    		
    		System.out.println(item.products.getName()+"	"+item.quantityOfProducts+"	"+price+"	"+tax);
    		System.out.println("--------------------------------------------------------------");
    	}
    	cart.clear();
    	System.out.println();
    	System.out.println("Subtotal: "+total);
    	System.out.println("Total Tax: "+taxTotal);
    	System.out.println("Total: "+(total+taxTotal));
    }
    
    /**r proccesses a return 
     * (Maddy and Iris)
     * 
     * 
     * @param scn taking in a scanner for use
     */
    public static void returnItem(Scanner scn)
    {	
		String error = "";
		System.out.println("Enter the ID of the item you'd like to return: ");
    		String id = scn.nextLine();
	    	for (int i = 0; i < inventory.size(); i++)
	    	{
	    		if (inventory.get(i).getId().equals(id))
	    		{
	    			System.out.println("How many days ago did you buy it?");
	    	    		int daysPassed = scn.nextInt();
	    	    		scn.nextLine();
	    			//scn.nextLine();
	    	    		if(daysPassed > 0) {
		    		if (inventory.get(i) instanceof Returnable && ((Returnable) inventory.get(i)).isEligible(daysPassed))
	 			{
		        		System.out.println("What condition is the item in? (Worn/Open Box/New In Box)");
		        		String condition = scn.nextLine();
		        		error = "";
		        		if(condition.toLowerCase().equals("worn")||condition.toLowerCase().equals("open box") ||condition.toLowerCase().equals("new in box")) {
		        			System.out.println("How many of them are you returning?");
		        	    		int quantity = scn.nextInt();
		        	    		scn.nextLine();
		        	    		error = "";
		        	    		if(quantity >0) {
				    			System.out.println("Your return has been accepted. Your refund amount is $"+((Returnable) inventory.get(i)).processRefund(quantity, condition)+".");
				    			inventory.get(i).restock(quantity);
				    			System.out.println("What is your name?");
				    			String name = scn.nextLine();
				    			System.out.println("What is your address?");
				    			String address = scn.nextLine();
				    			System.out.println(((Returnable) inventory.get(i)).getReturnLabel(name, address));
				    			error = "";
				    			break;
		        	    		}else {
		        	    			error =("Apologies, that is not a postivive quantity");
		        	    			break;
		        	    		}
		        		} else {
		        			error = ("Apologies, that is not an accepted condition");
		        			break;
		        		}
		    		}else {
		    			error = ("I'm sorry, that item is not eligible for return...");
		    			break;
		    		}
	    	    		}else {
	    	    			error = ("Apologies, that is not a postivive quantity");
	    	    			break;
	    	    		}
	    		}else {
	    			error = ("Sorry, invalid item ID");
	    		}
	    	}
	    	System.out.println(error);
    }
    
    //Getters
    public static ArrayList<Product> getInventory() {
    		return inventory;
    }
    public static ArrayList<CartItem> getCart() {
		return cart;
    }
}
