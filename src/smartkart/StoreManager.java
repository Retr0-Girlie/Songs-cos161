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

	

}
