// Package Specification
package model;

// System imports
import java.util.Properties;
import java.util.Vector;
import javafx.scene.Scene;

// Project imports
import exception.InvalidPrimaryKeyException;
import event.Event;
import database.*;
import impresario.IView;
import userinterface.View;
import userinterface.ViewFactory;

/**
 * @author Caleb Butcher, Michael Carter
 * @since 2016-02-02
 */
//=========================================================
public class BookCollection extends EntityBase
{
	private static final String myTableName = "Book";

	private Vector<Book> books;

	/**
	 * BookCollection class constructor
	 */
	public BookCollection()
	{
		super(myTableName);
		books = new Vector<Book>();
	}

	/** */
	//-----------------------------------------------------------
	public void findBooksOlderThanDate(String year)
	{
		System.out.println("Please enter a Date to search for older books");
		Scanner sc = new Scanner(System.in);
		String oYear = sc.next();
		String query = "SELECT * FROM Book WHERE (pubYear < " +oYear+ ")";

		dbAcc.setSQLStatement(query);
		Vector returnedValues = dbAcc.executeSQLSelectStatement();
		if (returnedValues == null)
		{
			System.out.println("No values returned from database for query");
		}
		else
		{
			int numResults = returnedValues.size();
			for (int cnt = 0; cnt < numResults; cnt++)
			{
				Properties nextRow = (Properties)returnedValues.elementAt(cnt);

				Enumeration columnNames = nextRow.propertyNames();
				while (columnNames.hasMoreElements() == true)
				{
					String columnName = (String)columnNames.nextElement();
					String columnValue = nextRow.getProperty(columnName);

					System.out.println(columnName + " = " + columnValue);
				}
			}
		}
	}

	/** */
	//-----------------------------------------------------------
	public void findBooksNewerThanDate(String year)
	{
		System.out.println("Please enter a Date to search for newer books");
		Scanner sc = new Scanner(System.in);
		String yYear = sc.next();
		String query = "SELECT * FROM Book WHERE (pubYear > " +yYear+ ")";

		dbAcc.setSQLStatement(query);
		Vector returnedValues = dbAcc.executeSQLSelectStatement();
		if (returnedValues == null)
		{
			System.out.println("No values returned from database for query");
		}
		else
		{
			int numResults = returnedValues.size();
			for (int cnt = 0; cnt < numResults; cnt++)
			{
				Properties nextRow = (Properties)returnedValues.elementAt(cnt);

				Enumeration columnNames = nextRow.propertyNames();
				while (columnNames.hasMoreElements() == true)
				{
					String columnName = (String)columnNames.nextElement();
					String columnValue = nextRow.getProperty(columnName);

					System.out.println(columnName + " = " + columnValue);
				}
			}
		}
	}

	/** */
	//-----------------------------------------------------------
	public void findBooksWithTitleLike(String title)
	{

	}

	/** */
	//-----------------------------------------------------------
	public void findBooksWithAuthorLike(String author)
	{

	}

	//-----------------------------------------------------------
	protected void initializeSchema(String tableName)
	{
		if (mySchema == null)
		{
			mySchema = getSchemaInfo(tableName);
		}
	}
}
