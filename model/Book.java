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