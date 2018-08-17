import java.util.ArrayList;
import java.util.List;

public class Reviewer {

	private String name;
	private List<String> reviewees;
	
	public Reviewer(String name) {
		this.name = name;
		this.reviewees = new ArrayList<String>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the reviewees
	 */
	public List<String> getReviewees() {
		return reviewees;
	}

	/**
	 * @param reviewees the reviewees to set
	 */
	public void setReviewees(List<String> reviewees) {
		this.reviewees = reviewees;
	}
	
	
	
	
}
