package smartkart;

/**Clothing
 * (Iris)
 * 
 * A clothing product at SmartKart
 * 
 * Attributes:
 * 	-String size		The size of the product
 * 	-String material		The material the product is made out of 
 * 
 * Methods:
 * 	-calculateTax(int count) Called to calculate the tax on a item of clothing during checkout
 * 	-boolean isEligible(int days) Called to check if an item is eligible for a return
 * 	-double processRefund(int count, String condition)  Called to do a refund
 *  	-String getReturnLabel(String name, String address) Called to get information about a return
 */

public class Clothing extends Product implements Returnable{
	private String size;
	private String material;
	
	/**Constructor
	 * (Iris)
	 * 
	 * @param String id		The items unique identifier
	 * @param String name	The human-readable name of the item
	 * @param double price	The price of the item
	 * @param int stock		The number of the item currently held in stock
	 * @param String size	The size of the clothing item
	 * @param String material	The material of the item of clothing
	 */
	public Clothing(String id, String name, double price, int stock, String size, String material) {
		this.id = id;
		this.size = size;
		this.name = name;
		this.material = material;
		this.price = price;
		this.stock = stock;
	}

	/**Calculate Tax
	 * (Iris)
	 * 
	 * Calculates the tax to be added based on the number of products and the category they belong to
	 * (5% tax for clothing)
	 * 
	 * @param int count		The number of items to be purchased
	 * @return double tax	The tax to be added to the final purchase price
	 */
	@Override
	public double calculateTax(int count) {
		double number = (price * count) * 0.05;
		return Math.round(number*100.0)/100.0;
	}

	/**Is Eligible For Return
	 * (Iris)
	 * 
	 * Checks if an item is eligible for return based on how many days have passed since it was purchased
	 * (Eligible up to 30 days)
	 * 
	 * @param int days	The number of days since the item was purchased
	 * @return boolean	The return eligibility (true = eligible for return, false = ineligible) 
	 */
	@Override
	public boolean isEligible(int days) {

		if (days <= 30){
			return true;
		} else {
			return false;
		}
	}

	/**Process Refund
	 * (Iris)
	 * 
	 * Calculates the refund amount to return to the customer based on the number of products and their condition
	 * (not able to refund if condition is worn)
	 * 
	 * @param int count			The number of items to be returned
	 * @param String condition	The condition of the item
	 * @return double refund	The amount of money to be refunded
	 */
	@Override
	public double processRefund(int count, String condition) {
		if (condition.equals("Worn")) {
			return  price * 0.9;
		}else {
			return price;
		}
	}

	/**Get Return Label
	 * (Iris)
	 * 
	 * Generates a return label based on the customers info
	 * 
	 * @param String name		The customers name
	 * @param String address	The customers delivery address
	 * @return String label		The return label for sending the product
	 */
	@Override
	public String getReturnLabel(String name, String address) {
		return name + "\n" + address + "\n\n	SmartKart" + "\n	123 Road rd" + "\n	Town, ST, 12345-6789";
	}
	
	/**getting info of a product
	 * (Iris)
	 * 
	 * @return String of information about the product
	 */
	public String getInfo(){
		return "Item ID:" + id + "\nItem name:" + name + "\nItem Price:" + price + "\nStock of item:" + stock + "\nItem material:" + material + "\nItem size:" + size + "\n \t ***";
	}
	
	//getters and setters
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}

}
