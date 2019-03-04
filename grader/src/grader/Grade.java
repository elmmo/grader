package grader;

import java.util.LinkedHashMap;
import java.util.Map;

public class Grade {
	String username; 
	Console console; 
	LinkedHashMap<String, Integer> references; 
	int points; 
	
	Grade(Assignment a, Storage s, Console c) {
		this.console = c; 
		this.username = console.getStrAnswer("Enter username:"); 
		this.references = new LinkedHashMap<String, Integer>(); 
		this.points = a.getTotalPoints(); 
		grade(a, s); 
	}
	
	public void grade(Assignment a, Storage s) {
		s.printAllComments();
		for (Map.Entry<String, Integer> entry : a.questions.entrySet()) {
			// prints question to jog grader memory 
			System.out.println(entry.getKey() + " | " + entry.getValue() + " points");
			
			// lines for this problem, individual to the student 
			String lines = console.getStrAnswer("What lines does this problem concern?"); 
			if (lines.equals("none")) continue; 
			if ((lines.equals("") || !lines.toLowerCase().contains("lines"))) {
				lines = entry.getKey(); 
			}
			
			// gets deduction information and breaks gracefully if unexpected input is received 
			boolean invalid = true; 

			do {
				try {
					String deduction = console.getStrAnswer("How many points do you deduct?"); 
					if (deduction.contains("Use comment ")) {
						references.put(lines, Integer.parseInt(deduction.split("#")[1])); 
					} else { 
						String comments = console.getStrAnswer("Comments?"); 
						// in the case that any points are deducted
						if (!deduction.equals("0")) {
							int deductionValue = Integer.parseInt(deduction); 
							points -= deductionValue; 
							if (entry.getValue()-deductionValue > entry.getValue()) throw new ArithmeticException(); 
							comments = "-" + deduction + " | " + comments; 
						}
						int referenceInt = s.addComment(comments);
						
						// adds to the memory for this grade 
						references.put(lines, referenceInt); 
					}
					invalid = false; 
				} catch (NumberFormatException e) {
					// in the case that the deduction isn't able to be parsed as an integer
					System.out.println("Something went wrong. Please try again.");
				} catch (ArithmeticException e) {
					// in the case that deduction is greater than the question max
					System.out.println("A max of " + entry.getValue() + " points may be deducted for this section.");
				}
			} while (invalid); 
		}
	}
	
	public String getUsername() {
		return username; 
	}
	
	public LinkedHashMap getReferences() {
		return references; 
	}
	
	public int getPoints() {
		return points; 
	}
}
