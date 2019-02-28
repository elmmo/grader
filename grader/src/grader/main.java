package grader;

public class main {

	public static void main(String[] args) {
		Console console = new Console(); 
		Assignment a = new Assignment(console); 
		Storage s = new Storage(); 
		s.enterGrades(a, console); 
	}

}
