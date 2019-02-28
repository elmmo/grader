package grader;
 
import java.util.LinkedHashMap;
import java.util.Map; 

public class Assignment {
	Console console; 
	String title; 
	int totalPoints; 
	protected LinkedHashMap<String, Integer> questions; 
	
	Assignment(Console console) { 
		this.console = console; 
		this.questions = new LinkedHashMap<String, Integer>(); 
		this.totalPoints = 0; 
		populate(); 
	}
	
	private void populate() { 
		// takes in user input about the assignment generally
		title = console.getStrAnswer("Assignment Name:"); 
		int sections = console.getIntAnswer("How many sections does this assignment have?"); 
		
		for (int i = 1; i <= sections; i++) {
			populateSection(Integer.toString(i)); 
		}
		populateSection("GENERAL"); 
		
		// when done populating, updates on what's in storage
		printState(); 
	}
	
	// breaks the assignment into sections
	private void populateSection(String name) {
		int numOfQuestions = 0; 
		boolean manualAnswers; 
		
		System.out.println("SECTION " + name);
		int sectionTotal = console.getIntAnswer("How many points are possible in this section?"); 
		totalPoints += sectionTotal; 
		manualAnswers = !console.getBoolAnswer("Would you like to distribute points evenly across each question in this section?"); 
		
		if (name.equals("GENERAL")) { 
			numOfQuestions = 2; 
			storeQuestions("Style?", manualAnswers, 1, sectionTotal); 
			storeQuestions("Comments?", manualAnswers, 1, sectionTotal); 
			
		} else { 
			storeQuestions("Please enter a question.", manualAnswers, numOfQuestions, sectionTotal); 
		}
		System.out.println(); 
	}
	
	private void storeQuestions(String prompt, boolean manualAnswers, int numOfQuestions, int sectionTotal) {
		int questionValue = 0; 
		if (!manualAnswers) questionValue = sectionTotal/numOfQuestions; 
		
		for (int j = 1; j <= numOfQuestions; j++) {
			String q = console.getStrAnswer(prompt); 
			if (manualAnswers) {
				questionValue = console.getIntAnswer("How should this question be weighted?"); 
				totalPoints += questionValue; 
			}
			questions.put(q, questionValue); 
		}
	}
	
	// prints out details about the assignment 
	public void printState() { 
		System.out.println("\nASSIGNMENT");
		for (Map.Entry<String, Integer> entry : questions.entrySet()) { 
			System.out.println(entry.getKey() + " | " + entry.getValue() + " points");
		}
		System.out.println(); 
	}
	
	// get the title of the assignment 
	public String getTitle() {
		return title; 
	}
	
	// get the total number of points possible within the assignment 
	public int getTotalPoints() {
		return totalPoints; 
	}
}
