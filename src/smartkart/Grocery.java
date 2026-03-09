package smartkart;

/**Grocery
 * (Iris)
 * 
 * A grocery product at SmartKart
 * 
 * Attributes:
 * 	-LocalDate expirationDate The date that the product expires on
 * 	-int timeTillExpiration The number of days till a product expires 
 * 
 * Methods:
 *  -calculateTax(int count) Called to calculate the tax on a item of clothing during checkout
 * 	-Boolean isExpired() Called to check if a product is Expired or not
 */

import java.time.LocalDate;

public class Grocery extends Product{
	private LocalDate expirationDate;
	private int timeTillExpiration;

	/**Constructor
	 * (Iris)
	 * 
	 * @param String id		The items unique identifier
	 * @param String name	The human-readable name of the item
	 * @param double price	The price of the item
	 * @param int stock		The number of the item currently held in stock
	 * @param LocalDate expirationDate the day of expiration of the product(Found by taking todays date and adding timeTillExpiration)
	 * @param int timeTillExpiration	The number of days till a product expires
	 */
	public Grocery(String id, String name, double price, int stock, LocalDate expirationDate, int timeTillExpiration) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.expirationDate = expirationDate.plusDays(timeTillExpiration);
	}
	/**Calculate Tax
	 * (Iris)
	 * 
	 * Calculates the tax to be added based on the number of products and the category they belong to
	 * (0% tax for groceries) always returns 0
	 * 
	 * @param int count		The number of items to be purchased
	 * @return double tax	The tax to be added to the final purchase price
	 */
	@Override
	public double calculateTax(int count) {
		return 0;
	}
	
	/**Is expired
	 * (Iris)
	 * 
	 * Figures out if the product is expired by checking the expirationDate and comparing it to current date
	 * 
	 * @return returns true if the product is expired else returns false
	 */
	public Boolean isExpired() {
		if(expirationDate.getDayOfYear() > LocalDate.now().getDayOfYear() ) {
			return true;
		}else {
			return false;	
		}
	}

	//Getters and Setters
	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getTimeTillExpiration() {
		return timeTillExpiration;
	}
	public void setTimeTillExpiration(int timeTillExpiration) {
		this.timeTillExpiration = timeTillExpiration;
	}

}
