package sheridan.application;

/**
 * PROG32758: Assignment1 Holds only ID and title of poll objects.
 * 
 * @author Jason Xu - 991545529
 *
 */
public class PollsIDTitle extends PollDataAccess {

	// fields
	private int pollID;
	private String title;

	/**
	 * constructor
	 * 
	 * @param pollID: int
	 * @param title:  String
	 */
	public PollsIDTitle(int pollID, String title) {
		this.pollID = pollID;
		this.title = title;
	} // end of pollsIDTitle

	/**
	 * pollID getter
	 * 
	 * @return pollID
	 */
	public int getPollID() {
		return pollID;
	} // end of getPollID

	/**
	 * title getter
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	} // end of getTitle

}
