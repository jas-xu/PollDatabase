package sheridan.application;

/**
 * PROG32758: Assignment1 Holds all user viewable information regarding poll
 * data.
 * 
 * @author Jason Xu - 991545529
 *
 */
public class Poll extends PollsIDTitle {

	// fields
	private String answer1, answer2, answer3, answer4;
	private int vote1, vote2, vote3, vote4;

	/**
	 * constructor
	 * 
	 * @param pollID:  int
	 * @param title:   String
	 * @param answer1: String
	 * @param answer2: String
	 * @param answer3: String
	 * @param answer4: String
	 * @param vote1:   String
	 * @param vote2:   String
	 * @param vote3:   String
	 * @param vote4:   String
	 */
	public Poll(int pollID, String title, String answer1, String answer2, String answer3, String answer4, int vote1,
			int vote2, int vote3, int vote4) {
		super(pollID, title);
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.vote1 = vote1;
		this.vote2 = vote2;
		this.vote3 = vote3;
		this.vote4 = vote4;
	}

	/**
	 * answer1 getter
	 * 
	 * @return answer1
	 */
	public String getAnswer1() {
		return answer1;
	} // end of getAnswer1

	/**
	 * answer2 getter
	 * 
	 * @return answer2
	 */
	public String getAnswer2() {
		return answer2;
	} // end of getAnswer2

	/**
	 * answer3 getter
	 * 
	 * @return answer3
	 */
	public String getAnswer3() {
		return answer3;
	} // end of getAnswer3

	/**
	 * answer4 getter
	 * 
	 * @return answer4
	 */
	public String getAnswer4() {
		return answer4;
	} // end of getAnswer4

	/**
	 * vote1 getter
	 * 
	 * @return vote1
	 */
	public int getVote1() {
		return vote1;
	} // end of getVote1

	/**
	 * vote2 getter
	 * 
	 * @return vote2
	 */
	public int getVote2() {
		return vote2;
	} // end of getVote2

	/**
	 * vote3 getter
	 * 
	 * @return vote3
	 */
	public int getVote3() {
		return vote3;
	} // end of getVote3

	/**
	 * vote4 getter
	 * 
	 * @return vote4
	 */
	public int getVote4() {
		return vote4;
	} // end of getVote4

	/**
	 * gets the total of all votes(1,2,3,4)
	 * 
	 * @return total votes
	 */
	public int getTotalVotes() {
		return vote1 + vote2 + vote3 + vote4;
	} // end of getTotalVotes

	/**
	 * gets the percentage votes for vote1
	 * 
	 * @return percent of vote1
	 */
	public int getPercent1() {
		return (int) (((double) vote1 / getTotalVotes()) * 100);
	} // end of getPercent1

	/**
	 * gets the percentage votes for vote2
	 * 
	 * @return percent of vote2
	 */
	public int getPercent2() {
		return (int) (((double) vote2 / getTotalVotes()) * 100);
	} // end of getPercent2

	/**
	 * gets the percentage votes for vote3
	 * 
	 * @return percent of vote3
	 */
	public int getPercent3() {
		return (int) (((double) vote3 / getTotalVotes()) * 100);
	} // end of getPercent3

	/**
	 * gets the percentage votes for vote4
	 * 
	 * @return percent of vote4
	 */
	public int getPercent4() {
		return (int) (((double) vote4 / getTotalVotes()) * 100);
	} // end of getPercent4

	/**
	 * outputs the poll object in easy way for users to read
	 * 
	 * @return String representation of poll object
	 */
	@Override
	public String toString() {
		// formats string representation
		String output = String.format("%s\n%s: %d (%d%%)\n%s: %d (%d%%)\n%s: %d (%d%%)\n%s: %d (%d%%)\n",
				this.getTitle(), this.getAnswer1(), this.getVote1(), this.getPercent1(), this.getAnswer2(),
				this.getVote2(), this.getPercent2(), this.getAnswer3(), this.getVote3(), this.getPercent3(),
				this.getAnswer4(), this.getVote4(), this.getPercent4());
		return output;
	} // end of toString

}
