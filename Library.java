// specify the package

// system imports
import java.util.Scanner;
import java.util.Vector;
import java.util.Properties;
import java.util.Locale;
import java.util.ResourceBundle;
import java.io.FileOutputStream;
import java.io.File;

//- GUI IMPORTS - Don't need most of these right now
import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
import javafx.stage.Stage;


//project imports
import event.Event;
import event.EventLog;
import common.PropertyFile;

import model.*;
import userinterface.MainStageContainer;
import userinterface.WindowPosition;

public class Library extends Application
{
	@SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("Library Version 1.00");
		System.out.println("© 2016 Michael Carter and Caleb Butcher");

		// Test Case 1 - Book title search
		System.out.println();
		System.out.print("Search Books with title like [Enter search term]: ");
		String title = stdin.nextLine();

		System.out.println("Searching for books with '"+title+"' in title...");
		BookCollection books = new BookCollection();
		try
		{
			books.findBooksWithTitleLike(title);

			Vector<Book> returnedBooks = (Vector<Book>)books.getState("Books");

			System.out.println("Results ("+returnedBooks.size()+" books returned):" );
			
			for (int cnt = 0; cnt < returnedBooks.size(); cnt++)
			{
				Book nextBookData = returnedBooks.elementAt(cnt);
				System.out.println(String.format("[%s] | '%s' | %s | %s | %s",
												 nextBookData.getState("bookId"),
												 nextBookData.getState("title"),
												 nextBookData.getState("author"),
												 nextBookData.getState("pubYear"),
												 nextBookData.getState("status")));
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		// Test Case 2 - Lookup books published before a give year
		System.out.println();
		System.out.print("Search books published before [Enter Year]: ");
		String year = stdin.nextLine();
		
		System.out.println("Searching for books published before '"+year+"'...");
		books = new BookCollection();
		try
		{
			books.findBooksNewerThanDate(year);

			Vector<Book> returnedBooks = (Vector<Book>)books.getState("Books");

			System.out.println("Results ("+returnedBooks.size()+" books returned):" );
			
			for (int cnt = 0; cnt < returnedBooks.size(); cnt++)
			{
				Book nextBookData = returnedBooks.elementAt(cnt);
				System.out.println(String.format("[%s] | '%s' | %s | %s | %s",
												 nextBookData.getState("bookId"),
												 nextBookData.getState("title"),
												 nextBookData.getState("author"),
												 nextBookData.getState("pubYear"),
												 nextBookData.getState("bookStatus")));
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		// Test Case 3 - Get patrons younger than specified year
		System.out.println();
		System.out.print("Search Patrons younger than [Enter year]: ");
		year = stdin.nextLine();
		
		System.out.println("Searching for patrons younger than '"+year+"'...");
		PatronCollection patrons = new PatronCollection();
		try
		{
			patrons.findPatronsYoungerThan(year);

			Vector<Patron> returnedPatrons = (Vector<Patron>)patrons.getState("Patrons");

			System.out.println("Results ("+returnedPatrons.size()+" patrons returned):" );
			
			for (int cnt = 0; cnt < returnedPatrons.size(); cnt++)
			{
				Patron nextPatronData = returnedPatrons.elementAt(cnt);
				System.out.println(String.format("[%s] | %s | %s %s, %s %s | %s | %s | %s",
												 nextPatronData.getState("patronId"),
												 nextPatronData.getState("name"),
												 nextPatronData.getState("address"),
												 nextPatronData.getState("city"),
												 nextPatronData.getState("stateCode"),
												 nextPatronData.getState("zipcode"),
												 nextPatronData.getState("dateOfBirth"),
												 nextPatronData.getState("email"),
												 nextPatronData.getState("patronStatus")));
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		// Test Case 4 - Search Patrons who live at a zip code
		System.out.println();
		System.out.print("Search Patrons who live at [Enter 5-digit Zip Code]: ");
		String zipCode = stdin.nextLine();
		
		System.out.println("Searching for patrons who live at '"+zipCode+"'...");
		patrons = new PatronCollection();
		try
		{
			patrons.findPatronsAtZipCode(zipCode);

			Vector<Patron> returnedPatrons = (Vector<Patron>)patrons.getState("Patrons");

			System.out.println("Results ("+returnedPatrons.size()+" patrons returned):" );
			
			for (int cnt = 0; cnt < returnedPatrons.size(); cnt++)
			{
				Patron nextPatronData = returnedPatrons.elementAt(cnt);
				System.out.println(String.format("[%s] | %s | %s %s, %s %s | %s | %s | %s",
												 nextPatronData.getState("patronId"),
												 nextPatronData.getState("name"),
												 nextPatronData.getState("address"),
												 nextPatronData.getState("city"),
												 nextPatronData.getState("stateCode"),
												 nextPatronData.getState("zipcode"),
												 nextPatronData.getState("dateOfBirth"),
												 nextPatronData.getState("email"),
												 nextPatronData.getState("patronStatus")));
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		// Test Class 5 - Lookup Transaction
		/*
		System.out.println();
		System.out.print("Search Transactions for Book [Enter a Book ID]: ");
		String bookId = stdin.nextLine();
		System.out.print("Enter a date [yyyy-mm-dd]: ");
		String date = stdin.nextLine();
		
		System.out.println("Searching for transactions for BookId: '"+bookId+"' on date '"+date+"'...");
		TransactionCollection transactions = new TransactionCollection();
		try
		{
			transactions.findMatchingTransactions(bookId, null, date);

			Vector<Transaction> returnedTrans = (Vector<Transaction>)transactions.getState("Transactions");

			if (returnedTrans == null)
				throw new Exception("The returned collection was empty");

			System.out.println("Results ("+returnedTrans.size()+" transactions returned):" );
			
			for (int cnt = 0; cnt < returnedTrans.size(); cnt++)
			{
				Transaction nextTransData = returnedTrans.elementAt(cnt);
				System.out.println(String.format("[%s] Book: %s | Patron: %s | %s | %s",
												 nextTransData.getState("transId"),
												 nextTransData.getState("bookId"),
												 nextTransData.getState("patronId"),
												 nextTransData.getState("dateOfTrans"),
												 nextTransData.getState("transType")));
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		*/

		//Test Class 5 - Add Book to the Collection
		System.out.print("Add book to collection [Enter Title]: ");
		title = stdin.nextLine();
		System.out.print("Enter Author: ");
		String author = stdin.nextLine();
		System.out.print("Enter Publication Year: ");
		String pubYear = stdin.nextLine();

		Properties bookProps = new Properties();
		bookProps.setProperty("title", title);
		bookProps.setProperty("author", author);
		bookProps.setProperty("pubYear", pubYear);
		bookProps.setProperty("bookStatus", "in");

		try {
			Book newBook = new Book(bookProps);
			newBook.update();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.exit(0);
    }

	/**
	 * 
	 */
	//-----------------------------------------------------------
	@Override
	public void start(Stage primaryStage)
	{
		
	}
}
