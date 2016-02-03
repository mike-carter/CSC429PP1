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
public class TransactionCollection extends EntityBase 
{
	private static final String myTableName = "Transaction";
	
	private Vector<Transaction> transactions;
	
	/**
	 * TransactionCollection class constructor
	 */
	public TransactionCollection()
	{
		transactions = new Vector<Transaction>();
	}
	
	public void findMatchingTransactions(String bookId, String patronId, String dateOfTrans) 
	{
		
	}
}
