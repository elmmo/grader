package grader;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Storage {
	final int CLASS_SIZE = 17; 
	Grade[] grades; 
	HashMap<Integer, String> comments; 
	int index; 
	
	Storage() { 
		grades = new Grade[CLASS_SIZE]; 
		comments = new HashMap<Integer, String>(); 
		int index = 0; 
	}
	
	public void enterGrades(Assignment a) {
		for (int i = 0; i < grades.length; i++) {
			grades[i] = new Grade(a, this);
		}
		generateOutput(); 
	}
	
	public void generateOutput() { 
		for (int i = 0; i < grades.length; i++) {
			try { 
				PrintWriter writer = new PrintWriter("../../" + grades[i].getUsername(), "UTF-8"); 
				writer.println(getDate());
				writer.println("Graded by Eli Min"); 
				LinkedHashMap<String, Integer> map = grades[i].getReferences(); 
				for (String key : map.keySet()) { 
					writer.println(key + " | " + comments.get(map.get(key))); 
				}
				writer.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
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
