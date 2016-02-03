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
public class BookCollection extends EntityBase 
{
	private static final String myTableName = "Book";
	
	private Vector<Book> books;
	
	/**
	 * BookCollection class constructor
	 */
	public BookCollection()
	{
		books = new Vector<Book>();
	}
	
	public void findBooksOlderThanDate(String year) 
	{
		
	}
	
	public void findBooksNewerThanDate(String year) 
	{
		
	}
	
	public void findBooksWithTitleLike(String title) 
	{
		
	}
	
	public void findBooksWithAuthorLike(String author) 
	{
		
	}
}