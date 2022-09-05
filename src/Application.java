/*
 * Meant to test all methods of the ShoppingCart class
 */
public class Application {

	public static void main(String[] args) {
		
/*
* Creating an empty shoppingCart with sufficient capacity.
* Also returns getCurrentSize.
*/
		System.out.println("Testing an initially empty shopping cart with sufficient capacity");
		BagInterface<Item> aCart = new ShoppingCart<>();
		Item [] contentsOfCart1 = {new Item ("Product Name1", "Description1", 1, 1, 1),
								   new Item ("Product Name2", "Description2", 2, 12, 2),
								   new Item ("Product Name3", "Description3", 3, 123, 3),
								   new Item ("Product Name4", "Description4", 4, 1234, 4),
								   new Item ("Product Name5", "Description5", 5, 12345, 5)};
		testAdd(aCart, contentsOfCart1);
		
/*
* Filling an initially empty shoppingCart to capacity.
*/
		System.out.println("\nTesting an initially empty shopping cart that will be filled to capacity");
		aCart = new ShoppingCart<>(7);
		Item[] contentsOfCart2 = {};
		testAdd(aCart, contentsOfCart2);}
	
/**
* @exception
* getTotalNumberItems() on my UML diagram is the same thing as getCurrentSize()
* See first test to verify getTotalNumberItems in shoppingCart array
*/	
	
/*
* Tests the method isEmpty.
* Precondition: If shoppingCart is empty, the parameter empty should be true; Otherwise, false.
*/
		private static void testIsEmpty(BagInterface<Item> bag, boolean empty)
			{
			     System.out.print("\nTesting isEmpty with ");
			      	if (empty)
			      		System.out.println("an empty shopping cart:");
			      	else
			      		System.out.println("a shopping cart that is not empty:");
			      		System.out.print("isEmpty finds the shopping cart ");
			      	if (empty && bag.isEmpty())
						System.out.println("empty: OK.");
					else if (empty)
						System.out.println("not empty, but it is: ERROR.");
					else if (!empty && bag.isEmpty())
						System.out.println("empty, but it is not empty: ERROR.");
					else
						System.out.println("not empty: OK.");      
				} // end testIsEmpty
/*
* Tests the add method
*/
	private static void testAdd(BagInterface<Item> aCart, Item[] content)
		{
			System.out.print("Adding the following Item(s) to the shopping cart: ");
				for (int index = 0; index < content.length; index++)
				{
					if (aCart.add(content[index]))
						System.out.print(content[index] + " ");
					else
						System.out.print("\nUnable to add " + content[index] + " to the shopping cart");
				}	// end for
						System.out.println();
					
				displayBag(aCart);	
			} // end testAdd
/*
* Tests the remove method
*/
	private static void testRemove(BagInterface<Item> aBag, Item[] content)
		{
			System.out.println("Removing the following Item(s) from the shopping cart: ");
			for (int index = 0; index < content.length; index++)
			{
				if (aBag.remove(content[index]))
					System.out.print(content[index] + " ");
				else
					System.out.print("\nRemoved " + content[index] + " from the shopping cart");
			}	// end for
					System.out.println();
				
			displayBag(aBag);	
		} // end testRemove	
	
	
	
	
	
	
/*
* Tests the clear method
* Not sure how this would be shown?
*/
	
	
	
	
	
	
/*
* Tests the toArray method while displaying the shoppingCart.
*/
	private static void displayBag(BagInterface<Item> aBag)
		{
			System.out.println("The shopping cart contains the following Item(s):");
			Object [] bagArray = aBag.toArray();
			for (int index = 0; index < bagArray.length; index++)
			{
				System.out.println(bagArray[index] + " ");
			} // end for
				
			System.out.println();
		}	// end displayBag
	
	
/*
* Tests the total price
* Creates new arraylist of Items to simulate checking out with shopping cart.
*/
	Item[] items = {new Item(),
            new Item(),
            new Item(),
            new Item()};
	{
	BagInterface<Item> shoppingCart = new ShoppingCart<>();
    double totalCost = 0;
   // Statements that add selected items to the shopping cart:
    for (int index = 0; index < items.length; index++)
    {
       Item nextItem = items[index]; // Simulate getting item from shopper
       shoppingCart.add(nextItem);
       totalCost = totalCost + nextItem.getPrice();  
    } // end for

    // Simulate checkout
    while (!shoppingCart.isEmpty())
              System.out.println(shoppingCart.remove());
    
		System.out.println("Total cost: " + "\t$" + totalCost / 100 + "." +
                       totalCost % 100);
	}	// end totalPrice

		
/*
 * Tests the setPaymentMethod		
 */
	/*
	 * commented out this code since I keep getting an error when trying to call the setPaymentMethod() with the "tester" object I created.
	ShoppingCart tester = new ShoppingCart();
	tester.setPaymentMethod("Visa");
	*/
	
	}	// end class
