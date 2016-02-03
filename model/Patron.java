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
public class Patron extends EntityBase 
{
	private static final String myTableName = "Patron";
	
	protected Properties dependencies;
	
	/**
	 * Patron class constructor: Primary key instantiation
	 */
	public Patron(String patronId) throws InvalidPrimaryKeyException
	{
		
	}
	
	/**
	 * Patron class constructor: Create new instance
	 */
	public Patron(Properties props) 
	{
		
	}
	
	
}