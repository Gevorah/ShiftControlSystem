package ui;
import java.util.*;

public class Main{
	
	private Scanner reader;
	
	public Main() {
		reader = new Scanner(System.in);
	}
	
	public void init() {
		
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
		main.reader.close();
	}
	
}