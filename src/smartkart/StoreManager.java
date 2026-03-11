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

	

}
