package smartkart;
/**StoreManager (Interface)
 * (Iris and Maddy)
 * 
 * The users menu driven console  
 *
 * Attributes:
 * 	-private ArrayList<CartItem> cart;	contains all items in the cart
 * 	-private ArrayList<Product> inventory; Contains all items in the stores inventory
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;


public class StoreManager {
	private ArrayList<CartItem> cart;
	private ArrayList<Product> inventory;
	
	/**For displaying Cart
	 * (Iris)
	 * 
	 * Prints out all items in the cart
	 */
	public void viewCart() {
		for (int i = 0; i < cart.size(); i++) {
			System.out.println("Items in cart \n------------");
			System.out.println(cart.get(i).getProducts().getInfo());
		}
	}
	/**For displaying Inventory
	 * (Iris)
	 * 
	 * Prints out all items in inventory
	 */
	public void viewInventory() {
		System.out.println("Items in inventory \\n------------");
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(inventory.get(i).getInfo());
		}
	}
	/**For adding products to cart
	 * (Iris)
	 *
	 *@return	 returns false if it fails due to an invalid entry along the process else returns true
	 */
	public boolean addToCart() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter product ID:");
		String userIdInput = scanner.nextLine();
		boolean success = false;
		for(int i = 0; i < inventory.size(); i++) {
			//checking if product is real
			if(userIdInput == inventory.get(i).getId()) {
				System.out.println("Please enter quantity of " + inventory.get(i).getName() + ":");
				int userQuantityInput = scanner.nextInt();
				//checking stock and making sure a valid quantity is entered
				if(userQuantityInput <= inventory.get(i).getStock() && userQuantityInput > 0) {
					//checking if a grocery product and not expired
					if(inventory.get(i).isExpired() != null|| inventory.get(i).isExpired() == true) {
						inventory.get(i).purchase(userQuantityInput);
						success = true;
						CartItem newItem = new CartItem(inventory.get(i), userQuantityInput);
						cart.add(newItem);
					}else {
						System.out.println("Error:Item expired");
					}
				}else {
					System.out.println("Error:Invalid user input. Enter a positive number or not enough stock");
				}
			}else {
				System.out.println("Error:Invalid user input. ID not found.");
			}
		}
		scanner.close();
		return success;
	}
	
    /**Reading tsv file
     * (Iris)
     * 
     * Takes the file and adds it's content to the inventory
     * 
     * @param filePath The string of the files location or path
     */
    public void FileReader(String filePath) {
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
                    int days = Integer.valueOf(daysTillExpired);
                    int quantity = Integer.valueOf(quantityInStock);
                    
                    // Create the appropriate type of item
                    switch (type) {
                        case "Grocery":
                            
                            Product Groc = new Grocery(itemId, name, priceD, quantity, LocalDate.now(), days);
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


	

}
