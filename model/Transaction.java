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
public class Transaction extends EntityBase 
{
	private static final String myTableName = "Transaction";
	
	protected Properties dependencies;
	
	/**
	 * Transaction class constructor: Primary key instantiation
	 */
	public Transaction(String transId) throws InvalidPrimaryKeyException
	{
		
	}
	
	/**
	 * Transaction class constructor: Create new instance
	 */
	public Transaction(Properties props) 
	{
		
	}
	
	
}