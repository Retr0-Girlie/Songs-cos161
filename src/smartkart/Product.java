package smartkart;

/**Product (Abstract)
 * (Maddy)
 * 
 * A non-descript product at SmartKart
 * 
 * Attributes:
 * 	-String id		A unique identifier for the product
 * 	-String name		The human readable name of the product
 * 	-double price	The price of the product
 * 	-int stock		The number of the product currently held in stock
 * 
 * Abstract Methods
 * 	-double calculateTax(int count)		Calculates the tax added to the items price, determined by the tax rate of the product category
 * 
 * Methods:
 * 	-boolean purchase(int count)	Called whenever an item is purchased to reduce the stock
 * 	-void restock(int count)		Adds a number of products to the current stock
 * 	-(Getters and setters for all attributes)
 * 
 */
public abstract class Product {
	
	protected String id;
	protected String name;
	protected double price;
	protected int stock;
	
	//Abstract Methods
	
	/**Calculate Tax
	 * (Maddy)
	 * 
	 * Calculates the tax to be added based on the number of products and the category they belong to
	 * 
	 * @param int count		The number of items to be purchased
	 * @return double tax	The tax to be added to the final purchase price
	 */
	public abstract double calculateTax(int count);
		
	//Concrete Methods
	
	/**Purchase
	 * (Maddy)
	 * 
	 * Called whenever an item is purchased to reduce the item's stock
	 * 
	 * @param int count		The number of items to be purchased
	 * @return boolean 		The completion status (true = success, false = fail)
	 */
	public boolean purchase(int count)
	{
		if (stock <= count) return false;
		
		else
		{
			stock -= count;
			return true;
		}
	}
	
	/**getting info of a product
	 * (Iris)
	 * 
	 * @return String of information about the product
	 */
	public String getInfo(){
		return "Item ID:" + id + "\nItem name:" + name + "\nItem Price:" + price + "\nQuantity of item:" + stock + "\n \t ***";
	}

	
	/**Restock
	 * (Maddy)
	 * 
	 * Adds a number of items to the current stock
	 * 
	 * @param int count		The number of items to be added to the stock
	 */
	public void restock(int count)
	{
		stock += count;
	}
	
	//Getters & Setters
	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}

	public int getStock() 
	{
		return stock;
	}

	public void setStock(int stock) 
	{
		this.stock = stock;
	}
}
