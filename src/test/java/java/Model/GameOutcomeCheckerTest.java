//package Model;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//class GameOutcomeCheckerTest {
//
//  @Test
//  public void testCheckGameOutcome_Draw() {
//    List<List<String>> playerBoard = createBoard(Arrays.asList("X", "X", "X"),
//        Arrays.asList("X", "X", "X"),
//        Arrays.asList("X", "X", "X"));
//
//    List<List<String>> opponentBoard = createBoard(Arrays.asList("X", "X", "X"),
//        Arrays.asList("X", "X", "X"),
//        Arrays.asList("X", "X", "X"));
//
//    GameResult result = GameOutcomeChecker.checkGameOutcome(playerBoard, opponentBoard);
//    Assertions.assertEquals(GameResult.DRAW, result);
//  }
//
//  @Test
//  public void testCheckGameOutcome_Win() {
//    List<List<String>> playerBoard = createBoard(Arrays.asList("X", "X", "X"),
//        Arrays.asList("X", "X", "X"),
//        Arrays.asList("X", "X", "X"));
//
//    List<List<String>> opponentBoard = createBoard(Arrays.asList("X", "O", "X"),
//        Arrays.asList("X", "O", "X"),
//        Arrays.asList("X", "O", "X"));
//
//    GameResult result = GameOutcomeChecker.checkGameOutcome(playerBoard, opponentBoard);
////    Assertions.assertEquals(GameResult.WIN, result);
//  }
//
//  @Test
//  public void testCheckGameOutcome_Lose() {
//    List<List<String>> playerBoard = createBoard(Arrays.asList("", "O", "X"),
//        Arrays.asList("X", "O", "X"),
//        Arrays.asList("X", "O", "X"));
//
//    List<List<String>> opponentBoard = createBoard(Arrays.asList("X", "X", "X"),
//        Arrays.asList("X", "X", "X"),
//        Arrays.asList("X", "X", "X"));
//
//    GameResult result = GameOutcomeChecker.checkGameOutcome(playerBoard, opponentBoard);
////    Assertions.assertEquals(GameResult.LOSE, result);
//  }
//
//  @Test
//  public void testCheckGameOutcome_InProgress() {
//    List<List<String>> playerBoard = createBoard(Arrays.asList("X", "O", "X"),
//        Arrays.asList("X", "X", "X"),
//        Arrays.asList("X", "X", "X"));
//
//    List<List<String>> opponentBoard = createBoard(Arrays.asList("X", "O", "X"),
//        Arrays.asList("X", "O", "X"),
//        Arrays.asList("X", "X", "X"));
//
//    GameResult result = GameOutcomeChecker.checkGameOutcome(playerBoard, opponentBoard);
////    Assertions.assertEquals(GameResult.IN_PROGRESS, result);
//  }
//
//  private List<List<String>> createBoard(List<String>... rows) {
//    List<List<String>> board = new ArrayList<>();
//    for (List<String> row : rows) {
//      board.add(new ArrayList<>(row));
//    }
//    return board;
//  }
//
//}