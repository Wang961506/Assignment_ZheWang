package assignment1;

import java.util.Scanner;

public class test {
	String name;
	String shape;
	int column;
	char blitz;
	char timeBomb;

	public test() {
		super();
	}

	public test(String name, String shape) {
		this.name = name;
		this.shape = shape;
	}

	public void getLocation() { // 落子
		Scanner stdin = new Scanner(System.in);
		boolean flag2 = true;
		while (flag2) {
			String inputString = stdin.nextLine();
			if (inputString.length() > 1) {
				System.out.println("the column is invalid, Please enter the column again");
				continue;
			}
			char inputchar = inputString.charAt(0);
			if ((inputchar < 48 || inputchar > 57) && inputchar != 'B' && inputchar != 'T') {
				System.out.println("the column is invalid, Please enter the column again");
			} 
			else if (inputchar >= 48 && inputchar <= 57){
				column = inputchar - '0';
				flag2 = false;
			}
			else if(inputchar == 'B'){
				blitz = inputchar;
				
			}
			else{
				timeBomb = inputchar;
				
			}
			
			if (blitz == 'B')
			{
				System.out.print("Blitz Please Select Column >");
			}
		    else if (timeBomb == 'T')
		    {
				System.out.print("Time Bomb Please Select Column >");
		    }
            
		
		}
	}
}

	