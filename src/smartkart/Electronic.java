package smartkart;


/**Electronic
 * (Maddy)
 * 
 * An electronic product at SmartKart
 * 
 * Attributes:
 * 	-String brand	The brand the item was made by
 * 
 * Methods:
 * 	-boolean isEligible(int days)						Checks if an item is eligible for return based on how many days have passed since it was purchased (Eligible up to 15 days)
 * 	-double processRefund(int count, String condition)	Calculates the refund amount to return to the customer based on the number of products and their condition (10% fee for "Open Box" returns)
 * 	-String getReturnLabel(String name, String address)	Generates a return label based on the customers info
 * 	-String calculateTax(int count)						Calculates the tax to be added based on the number of products and the category they belong to (15% tax for electronics)
 * 	-(Getters & Setters for each attribute)
 */
public class Electronic extends Product implements Returnable {
	
	private String brand;
	
	/**Constructor
	 * (Maddy)
	 * 
	 * @param String id		The items unique identifier
	 * @param String name	The human-readable name of the item
	 * @param String brand	The brand the item was made by
	 * @param double price	The price of the item
	 * @param int stock		The number of the item currently held in stock
	 */
	public Electronic(String id, String name, double price, int stock, String brand)
	{
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
	}

	/**Is Eligible For Return
	 * (Maddy)
	 * 
	 * Checks if an item is eligible for return based on how many days have passed since it was purchased
	 * (Eligible up to 15 days)
	 * 
	 * @param int days	The number of days since the item was purchased
	 * @return boolean	The return eligibility (true = eligible for return, false = ineligible) 
	 */
	@Override
	public boolean isEligible(int days) {
		
		if (days <= 15) return true;
		else return false;
	}

	/**Process Refund
	 * (Maddy)
	 * 
	 * Calculates the refund amount to return to the customer based on the number of products and their condition
	 * (10% fee for "Open Box" returns)
	 * 
	 * @param int count			The number of items to be returned
	 * @param String condition	The condition of the item
	 * @return double refund	The amount of money to be refunded
	 */
	@Override
	public double processRefund(int count, String condition) 
	{	
		if (condition.equals("Open Box")) return (price * count) * 0.9;
		
		else return price * count;
	}

	/**Get Return Label
	 * (Maddy)
	 * 
	 * Generates a return label based on the customers info
	 * 
	 * @param String name		The customers name
	 * @param String address	The customers delivery address
	 * @return String label		The return label for sending the product
	 */
	@Override
	public String getReturnLabel(String name, String address) 
	{
		return name+"\n"+address+"\n	SmartKart"+"\n	123 Road rd"+"\n	Town, ST, 12345-6789";
	}

	/**getting info of a product
	 * (Iris)
	 * 
	 * @return String of information about the product
	 */
	public String getInfo(){
		return "Item ID:" + id + "\nItem name:" + name + "\nItem Price:" + price + "\nStock of item:" + stock + "\nItem brand:" + brand + "\n \t ***";
	}
	
	/**Calculate Tax
	 * (Maddy)
	 * 
	 * Calculates the tax to be added based on the number of products and the category they belong to
	 * (15% tax for electronics)
	 * 
	 * @param int count		The number of items to be purchased
	 * @return double tax	The tax to be added to the final purchase price
	 */
	@Override
	public double calculateTax(int count) 
	{	
		return (price * count) * 0.15;
	}

	//Getters & Setters
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
