package grader;

import java.util.Scanner; 

public class main {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in); 
		Assignment a = new Assignment(console); 
		Storage s = new Storage(); 
		s.enterGrades(a); 
	}

}
