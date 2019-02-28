package grader;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Storage {
	final int CLASS_SIZE = 16; 
	Grade[] grades; 
	HashMap<Integer, String> comments; 
	int index; 
	String assignmentTitle; 
	int assignmentTotal; 
	
	Storage() { 
		grades = new Grade[CLASS_SIZE]; 
		comments = new HashMap<Integer, String>(); 
	}
	
	public void enterGrades(Assignment a, Console c) {
		assignmentTotal = a.getTotalPoints(); 
		assignmentTitle = a.getTitle();
		for (int i = 0; i < grades.length; i++) {
			grades[i] = new Grade(a, this, c);
			generateOutput(i); 
		}
	}
	
	public void generateOutput(int student) { 
		try { 
			PrintWriter writer = new PrintWriter("../../Grades/" + assignmentTitle + "-" + grades[student].getUsername(), "UTF-8"); 
			writer.println(getDate());
			writer.println("Graded by Eli Min"); 
			LinkedHashMap<String, Integer> map = grades[student].getReferences(); 
			for (String key : map.keySet()) { 
				writer.println(key + " | " + comments.get(map.get(key)) + "\n");
			}
			writer.println("\n" + grades[student].getPoints() + "/" + assignmentTotal); 
			writer.close(); 
			System.out.println("Grade for " + grades[student].getUsername() + " successfully generated.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public int addComment(String comment) {
		comments.put(index, comment); 
		index++; 
		return index-1; 
	}
	
	public void printAllComments() {
		for (Map.Entry<Integer, String> entry : comments.entrySet()) { 
			System.out.println(entry.getValue() + " (" + entry.getKey() + ")");
		}
	}
}
