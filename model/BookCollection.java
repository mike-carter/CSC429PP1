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
		String query = "SELECT * FROM " + myTableName + " WHERE (pubYear < " + year + "";
		Vector allDataRetrieved = getSelectQueryResult;

		if (allDataRetrieved != null)
		{
			books = new Vector<Book>();

			for(int cnt = 0; cnt < allDataRetrieved.size(); cnt++)
			{
				Properties nextBookData = (Properties)allDataRetrieved.elementAt(cnt);

				Book book = new Book(nextBookData);

				if(book != null)
				{
					addBook(book);
				}
			}
		}
		else
		{
			throw new InvalidPrimaryKeyException("No books younger than: " + year + "found");
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
