package assignment1;
import java.util.Scanner;

public class Start {
public static boolean flag1=true;
 public void display(){
	 Scanner stdin=new Scanner(System.in);
	 System.out.println("************************************");
	 System.out.println("     welcome to play this game");
	 System.out.println("     1.start game");
	 System.out.println("     2.quit ");
	 System.out.println("************************************");
	 System.out.print("please enter your choice:");
	 int myChoice=stdin.nextInt();
	 System.out.println();
	 switch(myChoice)
	 {
	 case 1: System.out.println("************Start Game***************");
	         System.out.println();
	         break;
	 case 2: System.out.println("end this game");
	         flag1=false;
	         break;
	 default:System.out.println("the instruction is wrong");
	 } 
	 }
public static void main(String[] args){
	Start start=new Start();
	boardDisplay boarddisplay=new boardDisplay();
//	while(flag1)
//	{
		start.display();
		boarddisplay.printchessboard();
//	}
}
}

class player{
	String name;
    String shape;
    int column;
    public void playerInf(String name,String shape){
    	this.name=name;
    	this.shape=shape;
    }
public void  getLocation(){    //落子
	Scanner stdin=new Scanner(System.in);
	boolean flag2=true;
	while(flag2)
	{
	System.out.print("please "+name+" enter the column coordinates:");
	column=stdin.nextInt();
	if(column<0 || column >9){
		System.out.println("the column is invalid, Please enter the column again");
	}else
		flag2=false;
}
}
}

class boardDisplay{
public String[][] board=new String[10][9];
public void printchessboard(){
	for(int i=0;i<10;i++)
	{
		for(int j=0;j<9;j++)
		{
		   	if(i==0)
		   	{
		   		System.out.print("| "+(j+1)+" ");
		   	}
		   	else
		   	{
		   		if(board[i][j]==null)
		   		{
		   			board[i][j]="_";
		   		}
		   		System.out.print("| "+board[i][j]+" ");
		   	}
		}
		System.out.println("|");

	}
	System.out.print("   Player 1 X");
	System.out.print("       Player 2 O");
	}
}







