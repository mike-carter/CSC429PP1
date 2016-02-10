// specify the package

// system imports
import java.util.Scanner;
import java.util.Vector;
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
		String userInput;
		
		System.out.println("Library Version 1.00");
		System.out.println("© 2016 Michael Carter and Caleb Butcher");

		System.out.println();
		System.out.print("Enter a search term for book title: ");
		userInput = stdin.nextLine();

		System.out.println("Searching for books with '"+userInput+"' in title...");
		BookCollection books = new BookCollection();
		try
		{
			books.findBooksWithTitleLike(userInput);

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

		System.out.println();
		System.out.print("Enter a year [Search books published before]: ");
		userInput = stdin.nextLine();
		
		System.out.println("Searching for books published before '"+userInput+"'...");
		books = new BookCollection();
		try
		{
			books.findBooksNewerThanDate(userInput);

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
		

		System.out.println();
		System.out.print("Enter a year [Search patrons younger than]: ");
		userInput = stdin.nextLine();
		
		System.out.println("Searching for patrons younger than '"+userInput+"'...");
		PatronCollection patrons = new PatronCollection();
		try
		{
			patrons.findPatronsYoungerThan(userInput);

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
		

		System.out.println();
		System.out.print("Enter a zip code [Search Patrons who live at]: ");
		userInput = stdin.nextLine();
		
		System.out.println("Searching for patrons who live at '"+userInput+"'...");
		patrons = new PatronCollection();
		try
		{
			patrons.findPatronsAtZipCode(userInput);

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

		
		System.out.println();
		System.out.print("Transaction Lookup; Enter a Book ID: ");
		String bookId = stdin.nextLine();
		System.out.print("Enter a date [yyyy-mm-dd]: ");
		String date = stdin.nextLine();
		
		System.out.println("Searching for transactions for '"+bookId+"' on date '"+date+"'...");
		TransactionCollection transactions = new TransactionCollection();
		try
		{
			transactions.findMatchingTransactions(bookId, null, date);

			Vector<Transaction> returnedTrans = (Vector<Transaction>)patrons.getState("Transactions");

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
