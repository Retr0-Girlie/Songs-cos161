package smartkart;
/**CartItem
 * (Iris)
 * 
 * Allows for the keeping of a Product and it's quantity
 * 
 * Attributes:
 * 	-Product products		The product being held in the cart
 * 	-int quanityOfProduct		The quantity of the product in cart 
 * 
 * Methods:
 * 	-None
 */

public class CartItem {
	Product products;
	int quantityOfProducts;
	/**Constructor
	 * (Iris)
	 * 
	 * @param Product products		The product being held in the cart
	 * @param int quantityOfProduct		The quantity of the product in cart
	 */
	public CartItem(Product p1, int quantity) {
		this.products = p1;
		this.quantityOfProducts = quantity;
	}
	//Getters
	public Product getProducts() {
		return products;
	}
	public int getQuantityOfProducts() {
		return quantityOfProducts;
	}

}
