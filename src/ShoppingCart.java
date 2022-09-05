import java.util.Arrays;
/**
 * A class that implements a bag(shopping cart) of objects using an array.
 * Allows you to add, or remove, Item objects to the shoppingCart.
 * @author Adam Winders
 */
public final class ShoppingCart<Item> implements BagInterface<Item> {
	
	/*
	 * Instance fields
	 */
	private Item [] shoppingCart;
	private int currentSize;
	private boolean integrityOK = false;
	private double totalPrice;
	private int totalNumberItems;
	private String paymentMethod;
	private static final int DEFAULT_CAPACITY = 25; // Initial capacity of shopping cart
	private static final int MAX_CAPACITY = 10000; // Max. number of Items the shopping cart object can hold
	
	/*
	 * Creates an empty shopping cart (default constructor), whose initial capacity is the default value of 25 items
	 */
	public ShoppingCart() 
	{
		this(DEFAULT_CAPACITY);
	}	// end default constructor
	
	/**
	 * Creates an empty shopping cart with a given initial capacity (preferred constructor).
	 * Default shopping cart will be created with a currentSize of 0 (empty), so that we can see which Items are added and removed from the beginning.
	 * @param initialCapacity  The integer capacity desired.
	 */
	public ShoppingCart(int initialCapacity) 
	{
		checkCapacity(initialCapacity);
		
		// New array will contain null entries
		@SuppressWarnings("unchecked")
		Item [] tempCart = (Item[]) new Object[initialCapacity];	// unchecked cast
		shoppingCart = tempCart;
		currentSize = 0;
		integrityOK = true;
	}	// end preferred constructor
	
	/**
	 * Creates a shoppingCart containing given entries
	 * @param contents  An array of objects.
	 */
	public ShoppingCart(Item[] contents) {
		checkCapacity(contents.length); // looks for the number of items in the shopping cart array
		shoppingCart = Arrays.copyOf(contents, contents.length);
		currentSize = contents.length; // instantiates numberOfEntries to return the number of items in the shopping cart array
		integrityOK = true;
	}	// end constructor
	
	/**
	 * This method adds a new entry to the shopping cart
	 * @param newEntry  The object to be added as a new entry.
	 * @return True
	 */
	public boolean add(Item newEntry) 
	{
		checkintegrity();
	 if (isArrayFull())
	 {
		doubleCapacity();
	 }	// end if
		
		shoppingCart[currentSize] = newEntry;
		currentSize ++;
		
		return true;
	}	// end add method
	
	/**
	 * Retrieves all entries that are in the shopping cart.
	 * @return A new array that contains all entries in this shopping cart.
	 */
	public Item[] toArray()
	{
		checkintegrity();
		
		// This new array will contain null entries.
		@SuppressWarnings("unchecked")
		Item [] results = (Item[]) new Object[currentSize]; //unchecked cast
		for (int index = 0; index < currentSize; index++)
		{
			results[index] = shoppingCart[index];
		}	// end for
		return results;
	}	//end toArray
	
	/**
	 * Checks if the bag is empty.
	 * @return True if the bag is empty, false if not.
	 */
	public boolean isEmpty()
	{
		return currentSize == 0;
	}	// end isEmpty
	
	/**
	 * Gets the current number of items in the shoppingCart
	 * @return The integer number of items currently in this shopping cart.
	 */
	public int getCurrentSize()
	{
		return currentSize;
	}	// getCurrentSize
	
	/**
	 * Counts the number of times a given entry  appears in this shopping cart.
	 * @param anEntry  The entry to be counted.
	 * @return The number of times anEntry appears in this shopping cart.
	 */
	public int getFrequencyOf(Item anEntry)
	{
		checkintegrity();
		int counter = 0;
		
		for (int index = 0; index < currentSize; index++)
		{
			if (anEntry.equals(shoppingCart[index]))
			{
				counter++;
			}	// end if
		}	// end for
		
		return counter;
	}	// end getFrequencyOf
	
	/**
	 * Tests whether this shopping cart contains a given Item.
	 * @param anEntry  The entry to locate.
	 * @return True if this bag contains anEntry, or false otherwise.
	 */
	public boolean contains (Item anEntry)
	{
		checkintegrity();
		return getIndexOf(anEntry) > -1; // or >= 0
	}	// end contains
	
	/**
	 * Removes all additions to the shopping cart
	 */
	public void clear ()
	{
		while (!isEmpty())
			remove();
	}	// end clear
	
	/**
	 * Removes one unspecified entry from this shopping cart, if possible.
	 * @return Either the removed Item, if the removal was successful, or null if not.
	 */
	public Item remove()
	{
		checkintegrity();
	 Item result = removeEntry(currentSize - 1);
	 return result;
	}	// end remove
	
	/**
	 * Removes one occurence of a given Item from this shopping cart.
	 * @param anEntry  The entry to be removed.
	 * @return True if the removal was successful, or false if not.
	 */
	public boolean remove (Item anEntry)
	{
		checkintegrity();
	 int index = getIndexOf(anEntry);	
	 Item result = removeEntry(index);
	 return anEntry.equals(result);
	}	// end remove
	
	/**
	 * Finds a given entry within the array shoppingCart.
	 * Returns the index location of the entry (if located), or -1 if it cannot be found.
	 * Precondition: checkintegrity has been called.
	 */
	private int getIndexOf(Item anEntry)
	{
		int where = -1;
		boolean found = false;
		int index = 0;
		
	 while (!found && (index < currentSize))
	 {
		 if (anEntry.equals(shoppingCart[index]))
		 {
			 found = true;
			 where = index;
		 }	// end if
		index++;
	 }	// end while
	 
	 /*
	  *  Assertion: if where > -1, anEntry is in the shoppingCart, and it equals shoppingCart[where]; 
	  *  otherwise, anEntry is not in the array.
	  */
	 return where;
	}	// end getIndexOf
	
	/**
	 * Removes, and returns, the Item entry at a given index within the array.
	 * If no such entry exits, returns null.
	 * Precondition: 0 <= givenIndex < currentSize.
	 * Precondition: checkintegrity has been called.
	 */
	private Item removeEntry (int givenIndex)
	{
		Item result = null;
		
		if (!isEmpty() && (givenIndex >= 0))
		{
			result = shoppingCart[givenIndex];	// entry to remove
			int lastIndex = currentSize -1;	// "shrinks" the array by one index position
			shoppingCart[givenIndex] = shoppingCart[lastIndex];	// Swaps out the entry to be removed with the last entry in the array.
			shoppingCart[lastIndex] = null;	// remove reference to the last entry in the array
			currentSize--;	// decrements the array by 1
		}	// end if
		
		return result;
	}	// end removeEntry
	
	/*
	 * Returns true if the shoppingCart is full - default capacity of 25, or max size of 10,000 has been reached.
	 * False if not.
	 */
	private boolean isArrayFull()
	{
		return currentSize >= shoppingCart.length;
	}	// end isArrayFull
	
	/*
	 * Doubles the size of the shoppingCart (25 cap. ---> 50 cap.)
	 * Precondition: checkInitialization has been called.
	 */
	private void doubleCapacity()
	{
		int newLength = 2 * shoppingCart.length;
		checkCapacity(newLength);
		shoppingCart = Arrays.copyOf(shoppingCart, newLength);
	}	// end doubleCapacity
	
	/*
	 * Throws an exception if the user inputs a capacity that is greater than the max size (10,000)
	 */
	private void checkCapacity(int capacity)
	{
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a shoppingCart whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
	}	// end checkCapacity
	
	/*
	 * Throws an exception if receiving Item is not initialized.
	 */
	private void checkintegrity()
	{
		if (!integrityOK)
			throw new SecurityException ("ArrayBag Item object is corrupt.");
	}	// end checkintegrity

/**
 * @return double of total price of all Item(s) in shoppingCart	
 */
	private double getTotalPrice()
	{
		// double totalPrice = price * currentSize;
		// or would it be better to have this added up 
		double totalPrice = 0;
		for (int i = 0; i < shoppingCart.length; i++)
		{
		// commented out for now
		//	totalPrice += shoppingCart.length.getPrice();
	
		}
		
		return totalPrice;
	}	// end totalPrice
	
/*
 * Sets the paymentMethod
 */
	private void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}	// end setPaymentMethod

/**
 * @return String of paymentMethod	
 */
	public String getPaymentMethod()
	{
		return paymentMethod;
	}	// end getPaymentMethod
	
}	// end class

/*
Testing isEmpty with an empty bag:
isEmpty finds the bag empty: OK.

Adding to the bag more strings than its initial capacity.
Adding to the bag: A D B A C A D
The bag contains 7 string(s), as follows:
A D B A C A D
Testing isEmpty with a bag that is not empty:
isEmpty finds the bag not empty: OK.


Testing the method getFrequencyOf:
In this bag, the count of A is 3
In this bag, the count of B is 1
In this bag, the count of C is 1
In this bag, the count of D is 2
In this bag, the count of Z is 0

Testing the method contains:
Does this bag contain A? true
Does this bag contain B? true
Does this bag contain C? true
Does this bag contain D? true
Does this bag contain Z? false

Removing a string from the bag:
remove() returns D
The bag contains 6 string(s), as follows:
A D B A C A

Removing "B" from the bag:
remove("B") returns true
The bag contains 5 string(s), as follows:
A D A A C

Removing "A" from the bag:
remove("A") returns true
The bag contains 4 string(s), as follows:
C D A A

Removing "C" from the bag:
remove("C") returns true
The bag contains 3 string(s), as follows:
A D A

Removing "Z" from the bag:
remove("Z") returns false
The bag contains 3 string(s), as follows:
A D A

Clearing the bag:
Testing isEmpty with an empty bag:
isEmpty finds the bag empty: OK.

The bag contains 0 string(s), as follows:
*/

