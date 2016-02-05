// Package Specification
package model;

// System imports
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
public class PatronCollection extends EntityBase
{
	private static final String myTableName = "Patron";

	private Vector<Patron> patrons;

	/**
	 * PatronCollection class constructor
	 */
	public PatronCollection()
	{
		super(myTableName);

		patrons = new Vector<Patron>();
	}

	public void findPatronsOlderThan(String year)
	{

	}

	public void findPatronsNewerThan(String year)
	{

	}

	public void findPatronsAtZipCode(String title)
	{

	}

	public void findPatronsWithNameLike(String author)
	{

	}

	//-----------------------------------------------------------------------------------
	protected void initializeSchema(String tableName)
	{
		if (mySchema == null)
		{
			mySchema = getSchemaInfo(tableName);
		}
	}
}
