/** A class of items for sale.
    @author Frank M. Carrano
    @author Timothy M. Henry
    @version 5.0
*/
public class Item
{
/*
 * Instance fields	
 */
   private String name;
   private String description;
   private int inventory;
   private int UPC;
   private int price;
   
/*
 * Empty-argument constructor
 * Establishes default info. for Item variables  
 */
   public Item() {
	   this.name = "DEFAULT ITEM NAME";
	   this.description = "DEFAULT ITEM DESCRIPTION";
	   this.inventory = 1;
	   this.UPC = 123456789;
	   this.price = 0;
   }
   
/*
 * Preferred constructor
 * Sets Item info. variables with passed in arguments.  
 */
	public Item(String productName, String productDescription, int productInventory, int productUPC, int productPrice) 
	{
	  this.name = productName;
      this.description = productDescription;
      this.inventory = productInventory;
      this.UPC = productUPC;
      this.price = productPrice;
	} // end constructor
	
/**
 * @return True if inventory >= 1, false if not.
 */
	public boolean isAvailable()
	{
		if (inventory >= 1)
			{
				System.out.println("Available");
				return true;
			}
		else
			{
				System.out.println("UNAVAILABLE");
				return false;
			}
	}	// end isAvailable
	
	
/**
* @return name of the Item
 */
	public String getName() {
		return name;
	}	// end getName
	
/*
 * sets name of the Item
 */
	public void setName(String name) {
		this.name = name;
	}	// end setName
	
/**
 * @return integer number of Item in inventory
 */
	public int getInventory() {
		return inventory;
	}	// end getInventory

/*
 * sets the number of Item(s) in inventory	
 */
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}	// end setInventory

/**
 *@return  UPC integer number for Item	
 */
	public int getUPC() {
		return UPC;
	}	// end getUPC

/*
 * Sets UPC (barcode) for Item	
 */
	public void setUPC(int uPC) {
		UPC = uPC;
	}	// end setUPC
	
/**
* @return Item description
*/
	public String getDescription() 
	{
		return description;
	} // end getDescription	
	
/*
 * Sets description about Item
 */
	public void setDescription(String description) {
		this.description = description;
	}	// end setDescription
	
/**
 * @return integer for the Item price	
 */
	public double getPrice() 
	{
      return price;
	} // end getPrice
	
/*
 * Sets Item price
 */
	public void setPrice(int price) {
		this.price = price;
	}	// end setPrice
	
	
/**
 * @return toString with rundown of item info.
 */
	public String toString() 
	{
      return name +"\n" + description + "\n"+inventory+"\n" + "$" + price / 100 + "." + price % 100;
	} // end toString
	
} // end Item
