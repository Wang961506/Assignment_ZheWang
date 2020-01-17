package assignment1;

import java.util.Scanner;

public class Start {
	public static boolean flag1 = true;

	public void display() {
		Scanner stdin = new Scanner(System.in);
		System.out.println("************************************");
		System.out.println("     Welcome To Play This Game");
		System.out.println("     1.Start Game");
		System.out.println("     2.Quit ");
		System.out.println("************************************");
		System.out.print("Please Enter Your Choice:");
		int myChoice = stdin.nextInt();
		System.out.println();
		switch (myChoice) {
		case 1:
			System.out.println("************Start Game***************");
			System.out.println();
			break;
		case 2: {
			System.out.println("************End   Game***************");
			flag1 = false;
			break;
		}
		default:
			System.out.println("The Instruction Is Wrong");
		}
	}

	public static void main(String[] args) {
		Start start = new Start();
		boardDisplay boarddisplay = new boardDisplay();
		process process1 = new process();
		while (flag1) {
			start.display();
			while (flag1) {
				process1.startGame();
				if (process1.gameover) {
					process1.gameover = false;
				}
				break;
			}
		}
	}
}

class player {
	String name;
	String shape;
	int column;
	char blitz;
	char timeBomb;
	

	public player() {
		super();
	}

	
	@Override
	public boolean equals(Object obj) {
	   if(this == obj)
		   return true;
	   if(obj instanceof player)
	   {
		   player play=(player)obj;
		   if(this.name.equals(play.name) && this.shape.equals(play.shape))
		   return true;
		   else
			   return false;
	   }
	   else 
		   return false;
	}


	public player(String name, String shape) {
		this.name = name;
		this.shape = shape;
	}

	public void getLocation() { // 落子
		Scanner stdin = new Scanner(System.in);
		boolean flag2 = true;
		while (flag2) {
			if (blitz == 'B')
				System.out.print("Blitz Please Select Column >");
			else if (timeBomb == 'T')
				System.out.print("Time Bomb Please Select Column >");
			else
				System.out.print("Player " + name + " Select Column>");
			// please player "+name+" enter the column coordinates:
			// column=stdin.nextInt();
			String inputString = stdin.nextLine();
			if (inputString.length() > 1) {
				System.out.println("the column is invalid, Please enter the column again");
				continue;
			}
			char inputchar = inputString.charAt(0);
			if ((inputchar < 48 || inputchar > 57) && inputchar != 'B' && inputchar != 'T') {
				System.out.println("the column is invalid, Please enter the column again");
			} else {
				if (inputchar >= 48 && inputchar <= 57) {
					column = inputchar - '0';
					flag2 = false;
				} else {
					if (inputchar == 'B') {
						blitz = inputchar;
						flag2 = false;
					} else {
						timeBomb = inputchar;
						flag2 = false;
					}
				}

			}
		}
	}
}

class boardDisplay {
	public String[][] board = new String[10][9];
	int columnofbomb = 0;
	int rowofbomb = 0;

	// process process1=new process();
	public void printchessboard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == 0) {
					System.out.print("| " + (j + 1) + " ");
				} else {
					if (board[i][j] == null) {
						board[i][j] = "_";
					}
					System.out.print("| " + board[i][j] + " ");
				}
			}
			System.out.println("|");

		}
		System.out.print("   Player 1 X");
		System.out.println("       Player 2 O");
	}
}

class process {
	player player1 = new player("1", "X");
	player player2 = new player("2", "O");
	player turn = null;
	static boolean gameover = false;
	int time = 0;
	int time1 = 0;
	int[] count = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	boardDisplay boarddisplay = new boardDisplay();
	Start start1 = new Start();

	public void whobegin() {
		int name;
		Scanner stdin = new Scanner(System.in);
		boolean Isselect = true;
		while (Isselect) {
			System.out.println();
			System.out.println("*********************************************************************************");
			System.out.println("Please Choose Who Starts Playing First:1.Player 1 OR 2.Player 2");
			System.out.print("Enter the number:");
			name = stdin.nextInt();
			System.out.println("*********************************************************************************");
			switch (name) {
			case 1:
				turn = player1;
				return;
			case 2:
				turn = player2;
				return;
			default:
				System.out.println("the player is not exits! please select again");
			}
		}
	}

	public void putchesspieces(player players, boardDisplay boarddisplay) {
		players.getLocation();// 得到需要下的子的列数,或者炸弹和消列的命令
		if (players.timeBomb == 'T') {
			if (players.column != 0) {
				if (count[players.column - 1] > 9) {
					System.out.println("the column is full,please retype it");
				} else if (boarddisplay.board[9 - count[(players.column - 1)] + 1][players.column - 1] == "_") {
					boarddisplay.board[9 - count[(players.column - 1)] + 1][players.column - 1] = "*";
					boarddisplay.columnofbomb = players.column - 1;// 列
					boarddisplay.rowofbomb = 9 - count[(players.column - 1)] + 1;// 行
					count[players.column - 1]++;
					time1 = time;
					time++;
					players.column = 0;
					players.timeBomb = 0;
					// if (turn == player1)
					// turn = player2;
					// else
					// turn = player1;
				}
			}
		} else {
			if (players.column != 0) {
				if (count[players.column - 1] > 9) {
					System.out.println("the column is full,please retype it");
				} else if (boarddisplay.board[9 - count[(players.column - 1)] + 1][players.column - 1] == "_") {
					boarddisplay.board[9 - count[(players.column - 1)] + 1][players.column - 1] = players.shape;
					count[players.column - 1]++;
					time++;
					players.column = 0;
					// if (turn == player1)
					// turn = player2;
					// else
					// turn = player1;
				}
			}
		}
		if (players.blitz == 'B') {

			players.getLocation();
			for (int i = 1; i < 10; i++)
				boarddisplay.board[i][players.column - 1] = "_";
			count[players.column - 1] = 1;
			time++;
			players.blitz = 0;
			// if (turn == player1)
			// turn = player2;
			// else
			// turn = player1;
		}
	}

	public void changePlayer() {
		if (turn == player1)
			turn = player2;
		else
			turn = player1;
	}

	public void startGame() {
		System.out.println("Begin The Game");
		boarddisplay.printchessboard();// 打印棋盘
		whobegin();// 选择谁先开始
		while (start1.flag1) {

			putchesspieces(turn, boarddisplay);// 下棋子
			TimeBomb(boarddisplay);
			if (turn.timeBomb != 'T')
				boarddisplay.printchessboard();
			judge(boarddisplay);
			// if (turn.timeBomb != 'T')
			// boarddisplay.printchessboard();
			// judge(boarddisplay);
			if (gameover) {
				for (int i = 0; i < boarddisplay.board.length; i++)
					for (int j = 0; j < boarddisplay.board[i].length; j++) {
						if (boarddisplay.board[i][j] != "_")
							boarddisplay.board[i][j] = "_";
						count[j] = 1;
					}
				System.out.println("********************************************************");
				System.out.println("Game Over！Player " + turn.name + " win");
				System.out.println("********************************************************");
				break;
			}
			changePlayer();
		}
	}

	public void judge(boardDisplay boarddisplay) {

		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 6; j++) {
				if ((boarddisplay.board[i][j] == turn.shape && boarddisplay.board[i][j + 1] == turn.shape
						&& boarddisplay.board[i][j + 2] == turn.shape && boarddisplay.board[i][j + 3] == turn.shape)) {
					gameover = true;
					return;
				}
			}
		}
		for (int i = 4; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				if ((boarddisplay.board[i][j] == turn.shape && boarddisplay.board[i - 1][j] == turn.shape
						&& boarddisplay.board[i - 2][j] == turn.shape && boarddisplay.board[i - 3][j] == turn.shape)) {
					gameover = true;
					return;
				}
			}
		}
		for (int i = 4; i < 10; i++) {
			for (int j = 0; j < 6; j++) {
				if ((boarddisplay.board[i][j] == turn.shape && boarddisplay.board[i - 1][j + 1] == turn.shape
						&& boarddisplay.board[i - 2][j + 2] == turn.shape
						&& boarddisplay.board[i - 3][j + 3] == turn.shape)) {
					gameover = true;
					return;
				}
			}
		}
		
	}

	public void TimeBomb(boardDisplay boarddisplay) {// rowofbomb=players.column-1,columnofbomb=9-count[(players.column-1)]+1;
		if ((time - time1) == 5 && time1 != 0) {
			time1 = 0;
			for (int i = boarddisplay.rowofbomb - 1; i < boarddisplay.rowofbomb + 2; i++)// 对炸弹区域清空
			{
				for (int j = boarddisplay.columnofbomb - 1; j < boarddisplay.columnofbomb + 2; j++) {
					if (i > 0 && i < 10)
						if (j >= 0 && j < 9) {
							if (boarddisplay.board[i][j] != "_") {
								boarddisplay.board[i][j] = "_";
								count[j]--;
							}
						}
				}
			}

			for (int j = boarddisplay.columnofbomb - 1; j < boarddisplay.columnofbomb + 2; j++) {
				for (int i = boarddisplay.rowofbomb - 1; i < boarddisplay.rowofbomb + 2; i++) {
					if (j >= 0 && j < 9 && i >= 0 && i < 10) {
						if ((i - 3) >= 0) {
							if (boarddisplay.board[i - 3][j] != "_") {
								boarddisplay.board[i][j] = boarddisplay.board[i - 3][j];
								boarddisplay.board[i - 3][j] = "_";
							}
						}
					}
				}
			}
		}
	}
}
