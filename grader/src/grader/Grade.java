package grader;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner; 

public class Grade {
	String username; 
	Scanner console; 
	LinkedHashMap<String, Integer> references; 
	int points; 
	
	Grade(Assignment a, Storage s) {
		this.console = new Scanner(System.in); 
		System.out.print("Enter username: ");
		this.username = console.next(); 
		console.nextLine(); 
		this.references = new LinkedHashMap<String, Integer>(); 
		this.points = a.getTotal(); 
		grade(a, s); 
	}
	
	public void grade(Assignment a, Storage s) {
		s.printAllComments();
		for (Map.Entry<String, Integer> entry : a.questions.entrySet()) {
			// prints question to jog grader memory 
			System.out.println(entry.getKey() + " | " + entry.getValue());
			
			// lines for this problem, individual to the student 
			System.out.print("What lines does this problem concern? ");
			String lines = console.nextLine(); 
			
			// grading 
			System.out.print("How many points do you deduct?");
			String deduction = console.nextLine(); 
			if (deduction.contains("Use comment ")) {
				references.put(lines, Integer.parseInt(deduction.split("#")[1])); 
			} else { 
				points -= Integer.parseInt(deduction); 
				console.nextLine(); 
				System.out.println("Comments?");
				String comments = "-" + deduction + " | " + console.nextLine(); 
				int referenceInt = s.addComment(comments);
				
				// adds to the memory for this grade 
				references.put(lines, referenceInt); 
			}
		}
	}
	
	public String getUsername() {
		return username; 
	}
	
	public LinkedHashMap getReferences() {
		return references; 
	}
}
