package grader;

import java.util.Scanner; 
import java.util.LinkedHashMap;
import java.util.Map; 

public class Assignment {
	Scanner console; 
	int total; 
	int numOfQuestions; 
	protected LinkedHashMap<String, Integer> questions; 
	
	Assignment(Scanner console) { 
		this.console = console; 
		this.questions = new LinkedHashMap<String, Integer>(); 
		this.total = 0; 
		this.numOfQuestions = 0; 
		populate(); 
	}
	
	public void populate() { 
		boolean manualAnswers = false; 
		int questionValue = 0; 
		
		// allows for the option of dividing all points among the questions evenly 
		System.out.print("How many points is this assignment worth? ");
		total = console.nextInt(); 
		System.out.print("Would you like to distribute points evenly for all questions? (y/n) ");
		if (console.next().equals("y")) { 
			manualAnswers = false; 
			System.out.print("How many questions are there? ");
			numOfQuestions = console.nextInt(); 
			questionValue = total/numOfQuestions; 
			console.nextLine(); // for clearing buffer 
		} else if (console.next().equals("n")) {
			manualAnswers = true; 
		} else {
			System.out.println("Please try again.");
		}
		
		// prompts for the user to provide questions
		int count = 1; 
		while (count <= numOfQuestions) { 
			System.out.print("Please enter a question. ");
			String q = console.nextLine(); 
			if (manualAnswers) { 
				System.out.print("How should this question be weighted?"); 
				questionValue = console.nextInt();
				total += questionValue; 
			}
			count++;
			questions.put(q, questionValue); 
		}
		
		// when done populating, updates on what's in storage
		printState(); 
	}
	
	// prints out details about the assignment 
	public void printState() { 
		System.out.println("\nASSIGNMENT");
		System.out.println(numOfQuestions + " questions");
		for (Map.Entry<String, Integer> entry : questions.entrySet()) { 
			System.out.println(entry.getKey() + " | " + entry.getValue() + " points");
		}
	}
	
	// get the total number of points possible within the assignment 
	public int getTotal() {
		return total; 
	}
}
