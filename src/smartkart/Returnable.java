package smartkart;

/**Returnable (Interface)
 * (Maddy)
 * 
 * A product at SmartKart that can be returned
 * (Electronics or clothing)
 * 
 * Methods:
 * 	-boolean isEligible(int days)						Checks if an item is eligible for return based on how many days have passed since it was purchased
 * 	-double processRefund(int count, String condition)	Calculates the refund amount to return to the customer based on the number of products and their condition
 * 	-String getReturnLabel(String name, String address)	Generates a return label based on the customers info
 * 
 */
public interface Returnable {
	
	/**Is Eligible For Return
	 * (Maddy)
	 * 
	 * Checks if an item is eligible for return based on how many days have passed since it was purchased
	 * 
	 * @param int days	The number of days since the item was purchased
	 * @return boolean	The return eligibility (true = eligible for return, false = ineligible) 
	 */
	public boolean isEligible(int days);
	
	/**Process Refund
	 * (Maddy)
	 * 
	 * Calculates the refund amount to return to the customer based on the number of products and their condition
	 * 
	 * @param int count			The number of items to be returned
	 * @param String condition	The condition of the item
	 * @return double refund	The amount of money to be refunded
	 */
	public double processRefund(int count, String condition);
	
	/**Get Return Label
	 * (Maddy)
	 * 
	 * Generates a return label based on the customers info
	 * 
	 * @param String name		The customers name
	 * @param String address	The customers delivery address
	 * @return String label		The return label for sending the product
	 */
	public String getReturnLabel(String name, String address);

}
