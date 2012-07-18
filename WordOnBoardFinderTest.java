import java.util.List;

import junit.framework.TestCase;

public class WordOnBoardFinderTest extends TestCase {
  /* TODO: Write extensive test cases for EVERY non-trivial piece of code.
   * Private helper methods should have associated public static tester methods
   * in WordOnBoardFinder.java. */
  private BoardMaker myMaker;
  private WordOnBoardFinder myFinder;

  /**
   * Code that is run before each test method (automatically)
   */
  public void setUp() {
    myMaker = new BoardMaker();
    myFinder = new WordOnBoardFinder();
  }

  /**
   * Given a board and list of board cells, return a string of the concatenated
   * cell characters.
   * 
   * @param board
   *          The board to look at
   * @param list
   *          The list of cells to combine
   * @return A string comibining the cells
   */
  private String getWord(BoggleBoard board, List<BoardCell> list) {
    String word = "";
    for (BoardCell cell : list) {
      word += board.getFace(cell.row, cell.col);
    }
    return word;
  }

  /**
   * This tests that we walk along the corners successfully. It is a very basic
   * test- if you pass it, you should not assume that your code will work in all
   * cases.
   */
  public void testGoodCorners() {
    // This is how you can create a test board:
    String[] boardContents = { "atruq", "seana", "niotc", "bdenk", "armin" };
    BoggleBoard board = myMaker.makeBoard(boardContents);

    String[] cornerWords = { "ate", "noted", "net", "urn", "bind", "aside",
                            "noise","quack","armin"  };
    for (String s : cornerWords) {
      List<BoardCell> list = myFinder.cellsForWord(board, s);
      String word = getWord(board, list);
      assertEquals("fail for " + s, s, word);
    }
  }
  
  /*
   * Tests that no cell is used twice when verifying that a word is in the board.
   * Also tests words that do appear in the board.
   */
  
  public void testRepeatingLetters(){
    String[] boardContents = { "abwrj", "sojrt", "luqie", "teotl", "armin" };
	    BoggleBoard board = myMaker.makeBoard(boardContents);

	    String[] invalidWords = { "lul","mill","tell"};
	    for (String s : invalidWords) {
	      List<BoardCell> list = myFinder.cellsForWord(board, s);
	      String word = getWord(board, list);
	      assertEquals("", word);
	    }
	    
	    

	    String[] validWords = { "absolute","term","quilt","lute"};
	    for (String s : validWords) {
	      List<BoardCell> list = myFinder.cellsForWord(board, s);
	      String word = getWord(board, list);
	      assertEquals("fail for " + s, s, word);
	    }
  }
 
  /*
   * Tests that words that are formed by non-adjacent cells are not verified. 
   * Also tests words that do appear in the board. 
   */
  
  public void testInvalidWords(){
	  String[] boardContents = { "ratqw", "sojrt", "pirws", "aqerd", "peudw" };
	    BoggleBoard board = myMaker.makeBoard(boardContents);

	    String[] invalidWords = { "sort","rats","reds","piss"};
	    for (String s : invalidWords) {
	      List<BoardCell> list = myFinder.cellsForWord(board, s);
	      String word = getWord(board, list);
	      assertEquals("", word);
	    }
	    
	    String[] validWords = { "rat","sat","raj","queue","pore"};
	    for (String s : validWords) {
	      List<BoardCell> list = myFinder.cellsForWord(board, s);
	      String word = getWord(board, list);
	      assertEquals("fail for " + s, s, word);
	    }
	  
  }
  
  
}
