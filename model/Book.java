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
//=========================================================
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
				throw new InvalidPrimaryKeyException(
					String.format("Multiple Books matching id : %s found", bookId)
					);
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
			throw new InvalidPrimaryKeyException(
				String.format("No Book matching id : %s found", bookId)
				);
		}
	}
	
	/**
	 * Book class constructor: Create new instance
	 */
	public Book(Properties props) 
	{
		
	}
	
	private void setDependencies() 
	{
		dependencies = new Properties();
		
		myRegistry.setDependencies(dependencies);
	}
	
}
