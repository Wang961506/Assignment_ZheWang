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
	process process1=new process();
    while(true)
	{

		start.display();
		process1.startGame();
	}
}
}

class player{
	String name;
    String shape;
    int column;
    char blitz;
    public player(String name,String shape){
    	this.name=name;
    	this.shape=shape;
    }
public void  getLocation(){    //落子
	Scanner stdin=new Scanner(System.in);
	boolean flag2=true;
	while(flag2)
	{
		if(blitz=='B')
			System.out.print("Blitz please select column >");
		else
        System.out.print("please player "+name+" enter the column coordinates:");
    //please player "+name+" enter the column coordinates:
//	column=stdin.nextInt();
	String inputString=stdin.nextLine();
	if(inputString.length()>1)
	{
		System.out.println("the column is invalid, Please enter the column again");
		continue;
	}
	char inputchar=inputString.charAt(0);
	if((inputchar<48 || inputchar >57) && inputchar !='B' && inputchar !='T' ){
		System.out.println("the column is invalid, Please enter the column again");
	}else
	{
		if(inputchar>=48 && inputchar <=57)
		{
			column=inputchar-'0';
			flag2=false;
		}
		else
		{
		   blitz=inputchar;
		   flag2=false;
		}
	}
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
	System.out.println("       Player 2 O");
	}
}


class process{
	player player1=new player("1","X");
	player player2=new player("2","O");
	player turn=null;
	boolean gameover=false;
	int[] count={1,1,1,1,1,1,1,1,1};
	boardDisplay boarddisplay=new boardDisplay();
	public void whobegin()
	{
		int name;
		Scanner stdin=new Scanner(System.in);
		boolean Isselect=true;
		while(Isselect){
		System.out.println("please choose the number of player who starts playing first:1.player1 OR 2.player2");
		System.out.print("enter the number:");
		name=stdin.nextInt();
		switch(name){
		case 1:turn=player1;
		       return;
		case 2:turn=player2;
			   return;
	    default:System.out.println("the player is not exits! please select again");
		}
		}
	}
	
    public void putchesspieces(player players,boardDisplay boarddisplay){
    	players.getLocation();//得到需要下的子的列数
    	if(players.column!=0)
    	{
    	if(count[players.column-1]>9){
    		System.out.println("the column is full,please retype it");
    	}
    	else if(boarddisplay.board[9-count[(players.column-1)]+1][players.column-1]=="_"){
    			boarddisplay.board[9-count[(players.column-1)]+1][players.column-1]=players.shape;
    			count[players.column-1]++;
    			players.column=0;
    			if(turn==player1)
    				turn=player2;
    			else
    				turn=player1;
    	}	
    	}
    	if(players.blitz=='B')
    	{
    		
    		players.getLocation();
    		for(int i=1;i<10;i++)
    		 boarddisplay.board[i][players.column-1]="_";
    		 count[players.column-1]=1;
    		players.blitz=0;
    	}
    }
    
    public void startGame(){
    	System.out.println("Begin The Game");
    	boarddisplay.printchessboard();//打印棋盘
    	whobegin();//选择谁先开始
    	while(true)
    	{ 
    		
    		putchesspieces(turn,boarddisplay);//下子
    		boarddisplay.printchessboard();
    		judge(boarddisplay);
    		if(gameover)
    		{
    			System.out.println("Game Over！Player "+turn.name+" win");
    			break;
    		}
    	}
    }
    
    public void judge(boardDisplay boarddisplay){
    	for(int i=1;i<10;i++)
    	{
    		for(int j=0;j<6;j++)	
    		{
    			if((boarddisplay.board[i][j]==turn.shape && boarddisplay.board[i][j+1]==turn.shape && boarddisplay.board[i][j+2]==turn.shape && boarddisplay.board[i][j+3]==turn.shape))
    			{
    				gameover=true;
    				return;
    			}
    		}
    	}
    	for(int i=4;i<10;i++)
    	{
    		for(int j=0;j<9;j++)
    		{
    			if((boarddisplay.board[i][j]==turn.shape && boarddisplay.board[i-1][j]==turn.shape && boarddisplay.board[i-2][j]==turn.shape && boarddisplay.board[i-3][j]==turn.shape))
    			{
    				gameover=true;
    				return;
    			}
    		}
    	}
    	for(int i=4;i<10;i++)
    	{
    		for(int j=0;j<6;j++)
    		{
    			if((boarddisplay.board[i][j]==turn.shape && boarddisplay.board[i-1][j+1]==turn.shape && boarddisplay.board[i-2][j+2]==turn.shape && boarddisplay.board[i-3][j+3]==turn.shape))
    			{
    				gameover=true;
    				return;
    			}
    		}
    	}
    }
}


class BlitzandBomb{
	
}





