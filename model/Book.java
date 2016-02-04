// Package Specification
package model;

// System imports
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

// Project imports
import exception.InvalidPrimaryKeyException;
import database.*;


/**
 * @author Caleb Butcher, Michael Carter
 * @since 2016-02-02
 */
//===============================================================
public class Book extends EntityBase 
{
	private static final String myTableName = "Book";

	protected Properties dependencies;

	/**
	 * Book class constructor: Primary key instantiation
	 */
	public Book(String bookId) throws InvalidPrimaryKeyException
	{
		super(myTableName);

		setDependencies();

		String query = String.format("SELECT * FROM %s WHERE (bookId = %s)", myTableName, bookId);

		Vector<Properties> allDataRetrieved = getSelectQueryResult(query);

		// We must get at least one book
		if (allDataRetrieved != null) 
		{
			int size = allDataRetrieved.size();

			// There should be exactly one book. Any more will be an error.
			if (size != 1)
			{
				throw new InvalidPrimaryKeyException(String.format("Multiple Books matching id : %s found", bookId));
			}
			else 
			{
				// Copy all retrived data into persistant state.
				Properties retrievedBookData = allDataRetrieved.elementAt(0);
				persistantState = new Properties();

				Enumeration allKeys = retrievedBookData.propertyNames();
				while (allKeys.hasMoreElements()) 
				{
					String nextKey = (String)allKeys.nextElement();
					String nextValue = retrievedBookData.getProperty(nextKey);

					if (nextValue != null) 
					{
						persistentState.setProperty(nextKey, nextValue);
					}
				}
			}
		}
		else // If no book was found at this Id, throw an exception
		{
			throw new InvalidPrimaryKeyException(String.format("No Book matching Id : %s found", bookId));
		}
	}

	/**
	 * Book class constructor: Create new instance
	 */
	public Book(Properties props) 
	{
		super(myTableName);
		
		setDependencies();
		
		persistantState = new Properties();

		Enumeration allKeys = retrievedBookData.propertyNames();
		while (allKeys.hasMoreElements()) 
		{
			String nextKey = (String)allKeys.nextElement();
			String nextValue = retrievedBookData.getProperty(nextKey);

			if (nextValue != null) 
			{
				persistentState.setProperty(nextKey, nextValue);
			}
		}
	}
	
	private void setDependencies() 
	{
		dependencies = new Properties();

		myRegistry.setDependencies(dependencies);
	}
	
	/**
	 * Update Book information in the database
	 */
	//----------------------------------------------------------
	public void update() 
	{
		updateStateInDatabase();
	}
	
	/**	*/
	//----------------------------------------------------------
	private void updateStateInDatabase() 
	{
		try
		{
			if (persistentState.getProperty("bookId") != null)
			{
				Properties whereClause = new Properties();
				
				whereClause.setProperty("bookId", persistentState.getProperty("bookId"));
				
				updatePersistentState(mySchema, persistentState, whereClause);
				
				updateStatusMessage = String.format("Data for Book Id : %s updated successfully in database!",
						persistentState.getProperty("AccountNumber"));
			}
			else
			{
				Integer bookId = insertAutoIncrementalPersistentState(mySchema, persistentState);
				persistentState.setProperty("bookId", bookId.toString());
				updateStatusMessage = String.format("Data for new Book : %s installed successfully in database!", 
						persistentState.getProperty("bookId"));
			}
		}
		catch (SQLException ex)
		{
			updateStatusMessage = "Error in installing book data in database!";
		}
	}

	/**
	 * Gets the value of a specified property
	 */
	//----------------------------------------------------------
	public Object getState(String key)
	{
		if (key.equals("UpdateStatusMessage") == true)
			return updateStatusMessage;

		return persistentState.getProperty(key);
	}
	
	/**
	 * 
	 */
	public boolean isBookAvailable() 
	{
		String status = persistantState.getProperty("status");
		status = status.toLowerCase();
		
		if (status.equals("in")) 
			return true;
		
		return false;
	}
	
	/**
	 * 
	 */
	//----------------------------------------------------------
	public void checkoutBook() 
	{
		String status = persistantState.getProperty("status");
		status = status.toLowerCase();
		
		if (status.equals("in"))
			persistantState.setProperty("status", "out");
	}
	
	/**
	 * 
	 */
	//----------------------------------------------------------
	public void returnBook() 
	{
		String status = persistantState.getProperty("status");
		status = status.toLowerCase();
		
		if (status.equals("out"))
			persistantState.setProperty("status", "in");
	}
}
