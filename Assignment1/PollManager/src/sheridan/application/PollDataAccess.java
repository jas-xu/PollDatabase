package sheridan.application;

import java.sql.*;
import java.util.*;

/**
 * PROG32758: Assignment 1 Controls the database information for "polls" table.
 * Used to insert, delete, update and view rows.
 * 
 * @author Jason Xu - 991545529
 *
 */
public class PollDataAccess {

	// fields
	private Connection connection = null;
	private PreparedStatement insertPollS;
	private PreparedStatement deletePollS;
	private PreparedStatement updatePollS;
	private PreparedStatement selectPollsIDTitleS;
	private PreparedStatement selectInfoS;

	// constructor
	public PollDataAccess() {

	}

	/**
	 * creates the connection to the database
	 * 
	 * @return true connected, false otherwise
	 */
	public boolean connect() {
		String databaseURL = "jdbc:mysql://localhost/assignment1";
		String username = "root";
		String password = "Mbdi0302";

		// load driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException driverErr) {
			System.err.println("ERROR: Could not load driver. " + driverErr);
			return false;
		}

		// connect to driver
		try {
			System.out.println("Connection established.");
			this.connection = DriverManager.getConnection(databaseURL, username, password);
			try { // creates statement objects for data processing
				insertPollS = this.connection.prepareStatement("INSERT INTO polls (UserID, Title, "
						+ "Question, Answer1, Answer2, Answer3, Answer4, StartDate, EndDate) VALUES "
						+ "(?, ?, ?, ?, ?, ?, ?, ?, ?);");
				deletePollS = this.connection.prepareStatement("DELETE FROM polls WHERE PollID=?;");
				updatePollS = this.connection.prepareStatement("UPDATE polls SET Question=?, "
						+ "Answer1=?, Answer2=?, Answer3=?, Answer4=?, Votes1=0, Votes2=0, Votes3=0, "
						+ "Votes4=0 WHERE PollID=?;");
				selectPollsIDTitleS = this.connection.prepareStatement("SELECT PollID, Title from polls;");
				selectInfoS = this.connection.prepareStatement("SELECT Title, Answer1, Answer2, Answer3, "
						+ "Answer4, Votes1, Votes2, Votes3, Votes4 FROM polls WHERE PollID=?;");
			} catch (SQLException statementErr) {
				System.err.println("ERROR: Could not create prepared statement(s). " + statementErr);
			}
		} catch (SQLException connectErr) {
			try { // tries to close connection if error occurs
				this.connection.close();
			} catch (SQLException closeErr) {
				System.err.println("ERROR: Could not close connection. " + closeErr);
				return false;
			}
			System.err.println("ERROR: Could not establish connection to databse. " + connectErr);
			return false;
		}
		return true;
	} // end of connect

	/**
	 * closes the connection to the database
	 * 
	 * @return true if closed, false otherwise
	 */
	public boolean closeConnection() {
		try { // attempts to close statement objects
			this.insertPollS.close();
			this.deletePollS.close();
			this.updatePollS.close();
			this.selectPollsIDTitleS.close();
			this.selectInfoS.close();
		} catch (SQLException statementCloseErr) {
			System.err.println("ERROR: Could not close prepared statement(s). " + statementCloseErr);
			return false;
		}

		try { // attempts to close connection to database
			this.connection.close();
			System.out.println("Connection closed.");
		} catch (SQLException closeErr) {
			System.out.println("ERROR: Could not close connection. " + closeErr);
			return false;
		}
		return true;
	} // end of closeConnection

	/**
	 * inserts a new entry into the "polls" database
	 * 
	 * @param userID:    String
	 * @param title:     String
	 * @param question:  String
	 * @param answer1:   String
	 * @param answer2:   String
	 * @param answer3:   String
	 * @param answer4:   String
	 * @param startDate: String
	 * @param endDate:   String
	 * @return true if entry inserted, false otherwise
	 */
	public boolean insertPoll(String userID, String title, String question, String answer1, String answer2,
			String answer3, String answer4, String startDate, String endDate) {
		try { // attempts to insert parameter values into SQL statement
			this.insertPollS.setString(1, userID);
			this.insertPollS.setString(2, title);
			this.insertPollS.setString(3, question);
			this.insertPollS.setString(4, answer1);
			this.insertPollS.setString(5, answer2);
			this.insertPollS.setString(6, answer3);
			this.insertPollS.setString(7, answer4);
			this.insertPollS.setString(8, startDate);
			this.insertPollS.setString(9, endDate);
			this.insertPollS.executeUpdate();
			System.out.printf("Inserted new question with UserID: %s into assignment1.polls.\n", userID);
		} catch (SQLException exeErr) {
			System.err.println("ERROR: Could not execute INSERT statement. " + exeErr);
			return false;
		}
		return true;
	} // end of insertPoll

	/**
	 * deletes an existing poll from the "polls" database
	 * 
	 * @param pollID: int
	 * @return true if deleted successfully, false otherwise
	 */
	public boolean deletePoll(int pollID) {
		try { // attempts to insert parameter value into SQL statement
			this.deletePollS.setInt(1, pollID);
			this.deletePollS.executeUpdate();
			System.out.printf("Deleted poll question ID: %d from assignment1.polls.\n", pollID);
		} catch (SQLException exeErr) {
			System.err.println("ERROR: Could not execute DELETE statement. " + exeErr);
			return false;
		}
		return true;
	} // end of deletePoll

	/**
	 * updates an existing poll in the "polls" database
	 * 
	 * @param question: String
	 * @param answer1:  String
	 * @param answer2:  String
	 * @param answer3:  String
	 * @param answer4:  String
	 * @param pollID:   int
	 * @return true if updated, false otherwise
	 */
	public boolean updatePoll(String question, String answer1, String answer2, String answer3, String answer4,
			int pollID) {
		try { // attempts to insert parameter values into SQL statement
			this.updatePollS.setString(1, question);
			this.updatePollS.setString(2, answer1);
			this.updatePollS.setString(3, answer2);
			this.updatePollS.setString(4, answer3);
			this.updatePollS.setString(5, answer4);
			this.updatePollS.setInt(6, pollID);
			this.updatePollS.executeUpdate();
			System.out.printf("Updated poll question ID: %d in assignment1.polls.\n", pollID);
		} catch (SQLException exeErr) {
			System.err.println("ERROR: Could not execute UPDATE statement. " + exeErr);
			return false;
		}
		return true;
	} // end of updatePoll

	/**
	 * creates a list of poll IDs and titles
	 * 
	 * @return an ArrayList<PollsIDTitle> of all rows in polls
	 */
	public ArrayList<PollsIDTitle> retrievePoll() {
		ArrayList<PollsIDTitle> pollsIDTitle = new ArrayList<PollsIDTitle>();
		ResultSet allIDTitle;
		try { // attempts to create a result set to store values inside of array list
			allIDTitle = this.selectPollsIDTitleS.executeQuery(); // creates result set
			try {
				while (allIDTitle.next()) { // as long as result set has values, loop runs
					pollsIDTitle.add(new PollsIDTitle(allIDTitle.getInt(1), allIDTitle.getString(2))); // populates
																										// array with
																										// values from
																										// result set
				}
			} catch (SQLException resultSetErr) {
				System.err.println("ERROR: Could not retrieve record from result set. " + resultSetErr);
				return null;
			}
		} catch (SQLException exeErr) {
			System.err.println("ERROR: Could not execute SELECT statement. " + exeErr);
			return null;
		}
		return pollsIDTitle;
	} // end of retrievePoll

	/**
	 * creates a poll object which holds all the user viewable information of the
	 * given poll
	 * 
	 * @param pollID: int
	 * @return a poll object which holds data to be output
	 */
	public Poll selectInfo(int pollID) {
		Poll poll = null;
		ResultSet pollByID;

		try { // attempts to create a result set to use for creating poll object
			this.selectInfoS.setInt(1, pollID);
			pollByID = this.selectInfoS.executeQuery();
			try { // attempts to create poll object by using data from result set
				pollByID.beforeFirst();
				if (pollByID.next()) { // if poll exists based on user provided poll ID
					poll = new Poll(pollID, pollByID.getString(1), pollByID.getString(2), pollByID.getString(3),
							pollByID.getString(4), pollByID.getString(5), pollByID.getInt(6), pollByID.getInt(7),
							pollByID.getInt(8), pollByID.getInt(9));
				} else { // if poll ID does not exist
					System.out.printf("Poll ID: %d does not exist\n", pollID);
					return null;
				}
			} catch (SQLException Err) {
				System.err.println("");
			}
		} catch (SQLException selectErr) {
			System.err.println("ERROR: Could not execute SELECT statement. " + selectErr);
			return null;
		}
		return poll;
	} // end of selectInfo
}
