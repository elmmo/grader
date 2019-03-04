package grader;
 
import java.util.LinkedHashMap;
import java.util.Map; 

public class Assignment {
	final String GEN_FLAG = "- GENERAL"; 
	Console console; 
	String title; 
	int totalPoints; 
	String[] generalGrading = { "Style", "Comments" }; 
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
		populateSection(GEN_FLAG); 
		
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
		
		if (name.equals(GEN_FLAG)) { 
			numOfQuestions = generalGrading.length; 
			for (int i = 0; i < generalGrading.length; i++) {
				storeQuestions(generalGrading[i], manualAnswers, 1, sectionTotal, true, name); 
			}
		} else { 
			numOfQuestions = console.getIntAnswer("How many questions are in this section?"); 
			storeQuestions("Please enter a question.", manualAnswers, numOfQuestions, sectionTotal, false, name); 
		}
		System.out.println(); 
	}
	
	/**
	 * Stores information on the question and the number of points alloted to that question 
	 * @param prompt			The string that will prompt the user for the question
	 * @param manualAnswers		Whether points will be inputed manually or automatically generated 
	 * @param numOfQuestions	The number of questions in the current section 
	 * @param sectionTotal		The total number of points possible in the current section 
	 * @param responseBool		If the response will be a boolean or not
	 * @param section			The name of the current section (ensures unique key entries)
	 */
	private void storeQuestions(String prompt, boolean manualAnswers, int numOfQuestions, int sectionTotal, boolean responseBool, String section) {
		int questionValue = 0; 
		if (!manualAnswers) questionValue = sectionTotal/numOfQuestions; 
		
		for (int j = 1; j <= numOfQuestions; j++) {
			String q = "Section " + section + " - "; 
			if (responseBool) {
				String includePrompt = String.format("Will this assignment grade %s?", prompt.toLowerCase()); 
				boolean include = console.getBoolAnswer(includePrompt); 
				if (include) q += prompt; 
			} else {
				q += console.getStrAnswer(prompt); 
			}
			if (manualAnswers) {
				questionValue = console.getIntAnswer("How should this question be weighted?"); 
				totalPoints = questionValue; 
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
