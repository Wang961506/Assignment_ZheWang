package assignment1;
import static org.junit.Assert.*;

import org.junit.Test;
public class JUnitTest {

	@Test
	public void testWhoBegin(){
		process pro2=new process();
		String expected = "1";
		pro2.whobegin();
		assertEquals(expected,pro2.turn.name);	
	}
	
	@Test  
	public void testgetLocation(){
		player player1=new player();
		int expectedcolumn=1;
		player1.getLocation();
		assertEquals(expectedcolumn,player1.column);
		
	}
	
	@Test         //Failure of the test 
	public void testprintchessboard(){
		String[][] expected=new String[10][9];
		for(int i=0;i<expected.length;i++)
			for(int j=0;j<expected[i].length;j++){
				if (i == 0) {
					expected[i][j]="| " + (j + 1) + " ";
				}
				expected[i][j]="_";
			}
		boardDisplay boarddisplay=new boardDisplay();
		boarddisplay.printchessboard();
		assertArrayEquals("",expected,boarddisplay.board);
	}
	
	@Test
	public void testchangePlayer(){
		player expected=new player("1","X");
		player player2=new player("2","O");
		process input=new process();
		input.turn=player2;
		input.changePlayer();
        assertEquals(expected,input.turn);
	}
	
	
}


