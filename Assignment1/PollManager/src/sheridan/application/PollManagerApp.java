package sheridan.application;

import java.util.*;

/**
 * PROG32758: Assignment 1 Poll Manager console interface. Handles user input
 * and selection of SQL commands to execute on the "polls" database.
 * 
 * @author Jason Xu - 991545529
 *
 */
public class PollManagerApp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // creates new scanner for user input
		boolean exitConsole = false; // tells if the console should stop (true to exit, false to not)

		PollDataAccess polls = new PollDataAccess(); // creates new data access
		polls.connect(); // connects to database

		do { // displays and processes user's menu selections
			System.out.println("POLL MANAGER MENU\n");
			System.out.println("Available option(s):");
			System.out.println("1. Insert a new poll");
			System.out.println("2. Delete a poll");
			System.out.println("3. Update a poll");
			System.out.println("4. View a poll");
			System.out.println("5. Exit");

			// variables used to process user input
			String sPollID = "";
			String userID = "";
			String title = "";
			String question = "";
			String answer1 = "";
			String answer2 = "";
			String answer3 = "";
			String answer4 = "";
			String startDate = "";
			String endDate = "";

			String strInput; // stores user input as string so it can be manually error checked
			int userSelection = -13580; // number used to run console (0 can be accidentally input)

			System.out.print("Choose an option (1-5): ");
			try { // error checks user's menu selection
				strInput = input.next();
				userSelection = Integer.parseInt(strInput);
			} catch (NumberFormatException invalidIn) {
				System.err.println("ERROR: Invalid input. " + invalidIn);
			}

			switch (userSelection) { // processes user's menu selections
			case 1:
				System.out.println("Enter a user ID: ");
				userID = input.next();
				System.out.println("Enter a title: ");
				title = input.next();
				System.out.println("Enter a question: ");
				question = input.next();
				System.out.println("Enter 1st possible answer: ");
				answer1 = input.next();
				System.out.println("Enter 2nd possible answer: ");
				answer2 = input.next();
				System.out.println("Enter 3rd possible answer: ");
				answer3 = input.next();
				System.out.println("Enter 4th possible answer: ");
				answer4 = input.next();
				System.out.println("Enter start date (yyyy-mm-dd): ");
				startDate = input.next();
				System.out.println("Enter end date (yyyy-mm-dd): ");
				endDate = input.next();
				polls.insertPoll(userID, title, question, answer1, answer2, answer3, answer4, startDate, endDate); // attempts
																													// to
																													// insert
																													// a
																													// new
																													// poll
																													// entry
				break;
			case 2:
				try { // checks user input a valid integer for poll ID
					System.out.println("Enter a poll ID: ");
					sPollID = input.next();
					int pollID = Integer.parseInt(sPollID);
					polls.deletePoll(pollID); // attempts to delete an existing poll entry
				} catch (NumberFormatException invalidPollID) {
					System.err.println("ERROR: Invalid poll ID (must be integer). " + invalidPollID);
				}
				break;
			case 3:
				try { // checks user input a valid integer for poll ID
					System.out.println("Enter a poll ID: ");
					sPollID = input.next();
					int pollID = Integer.parseInt(sPollID);
					System.out.println("Enter a new question: ");
					question = input.next();
					System.out.println("Enter new 1st possible answer: ");
					answer1 = input.next();
					System.out.println("Enter new 2nd possible answer: ");
					answer2 = input.next();
					System.out.println("Enter new 3rd possible answer: ");
					answer3 = input.next();
					System.out.println("Enter new 4th possible answer: ");
					answer4 = input.next();
					polls.updatePoll(question, answer1, answer2, answer3, answer4, pollID); // attempts to update
																							// existing poll
				} catch (NumberFormatException invalidPollID) {
					System.err.println("ERROR: Invalid poll ID (must be integer). " + invalidPollID);
				}
				break;
			case 4:
				ArrayList<PollsIDTitle> pollsIDTitle = new ArrayList<PollsIDTitle>(); // new array list to store poll
																						// IDs and Titles
				pollsIDTitle = polls.retrievePoll(); // stores IDs and Titles from database into array list
				int pollNum = 0; // to display titles to user without showing actual ID
				System.out.println("AVAILABLE POLLS");
				for (PollsIDTitle pollInfo : pollsIDTitle) { // displays all titles in the database
					pollNum++;
					System.out.printf("%d. %s\n", pollNum, pollInfo.getTitle());
				}
				try { // checks user input is a valid integer
					System.out.println("Which Poll ID do you want to see?");
					String sPollTitleNum = input.next();
					int pollTitleNum = Integer.parseInt(sPollTitleNum);
					Poll poll = polls.selectInfo(pollsIDTitle.get(pollTitleNum - 1).getPollID()); // creates a new poll
																									// object using user
																									// input
					System.out.println(poll); // outputs the user selected poll information
				} catch (NumberFormatException invalidPoll) {
					System.err.println("ERROR: Invalid selection for poll. " + invalidPoll);
				}
				break;
			case 5:
				System.out.println("Exiting application.");
				exitConsole = true;
				break;
			case -13580:
				break;
			default:
				System.out.printf("Option %d does nothing.\n", userSelection);
			}

		} while (!exitConsole); // loops while not true

		input.close(); // close scanner
		polls.closeConnection(); // close connection to database
	}

}
