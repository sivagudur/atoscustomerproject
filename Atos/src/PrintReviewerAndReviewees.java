import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class PrintReviewerAndReviewees {

	
	public static void main(String args[]) throws IOException {
	
		Map<String,Reviewer> reviewerMap = new LinkedHashMap<String,Reviewer>();		
		String fileName = "reviewers-and-reviewees.txt";
 

		String topLevelReivewer = null;		 
		List<String> strLines = Files.readAllLines(Paths.get(fileName));			
		
		for(String line : strLines) {
			String parts[] = line.split("reviews");
			String reviewerStr = parts[0].trim();
			String revieweeStr = parts[1].trim();
			
			if(topLevelReivewer == null) {
				topLevelReivewer = reviewerStr;
			}
			
			if(reviewerMap.containsKey(reviewerStr)) {
				reviewerMap.get(reviewerStr).getReviewees().add(revieweeStr); 
			} else {
				Reviewer reviewer = new Reviewer(reviewerStr);
				reviewer.getReviewees().add(revieweeStr);
				reviewerMap.put(reviewerStr, reviewer);
			}
		}
		print(topLevelReivewer,reviewerMap,0);
	}
	
	
	public static void print(String reviewerStr,Map<String,Reviewer> reviewerMap,int level) {
		
		for(int index=0;index<level;index++) {
			System.out.print("|");
		}

		Reviewer reviewer = reviewerMap.get(reviewerStr);	
		if(reviewer!=null) {
			if(reviewer.getReviewees().isEmpty()) {
				System.out.print("\\-");
			}
			System.out.println(reviewerStr);
		
			for(String reviewee : reviewer.getReviewees()) {
				print(reviewee,reviewerMap,level+1);
			}
		} else {
			System.out.println("\\-"+reviewerStr);
		}
	}
}
