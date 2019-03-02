package grader;

import java.util.Scanner; 

public class Console {
	Scanner scanner; 
	
	Console() {
		this.scanner = new Scanner(System.in); 
	}
	
	// helper function to ask a question 
	private void ask(String q) {
		q = q.trim(); 
		System.out.print(q + " ");
	}
	
	// helper function to ask a question with a message appended at the end 
	private void ask(String q, String append) {
		q = q.trim(); 
		append = append.trim(); 
		System.out.print(q + " " + append + " ");
	}
	
//	private <T> boolean typeCheck(String type, T obj) { 
//		type = "java.lang." + type; 
//		if (obj instanceof String && type.equals(String.class.toString())) return true; 
//	}
	
	// gets a string user input
	public String getStrAnswer(String q) { 
		ask(q); 
		return scanner.nextLine(); 
	}
	
	// gets a boolean user input 
	public boolean getBoolAnswer(String q) {
		boolean askAgain = true; 
		boolean result = false; 
		
		// repeats question until user responds with a yes or no
		do {
			ask(q, "(y/n)"); 
			String response = scanner.next().toLowerCase(); 
			switch (response) {
			case "y":
			case "yes":
				result = true; 
				askAgain = false; 
				break; 
			case "n": 
			case "no":
				result = false; 
				askAgain = false; 
				break; 
			}
			scanner.nextLine(); // for clearing buffer 
		} while (askAgain); 
		
		return result; 
	}
	
	// get an int user input 
	public int getIntAnswer(String q) {
		ask(q); 
		int response = scanner.nextInt(); 
		scanner.nextLine(); // for clearing buffer 
		return response; 
	}
	
	// closes the scanner upon destruction of the class
	public void finalize() {
		scanner.close(); 
	}
}
