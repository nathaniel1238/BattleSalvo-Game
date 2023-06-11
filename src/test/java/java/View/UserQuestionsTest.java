package View;
import cs3500.cs3500.pa03.View.UserQuestions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserQuestionsTest {

  /**
   * Tests the {@link UserQuestions#displaySize(Appendable)} method.
   *
   * @throws IOException if an I/O error occurs
   */
  @Test
  public void testDisplaySize() throws IOException {
    StringWriter output = new StringWriter();
    UserQuestions.displaySize(output);

    String expectedOutput = "Hello! Welcome to BattleSalvo!\n" +
        "Please enter a valid height and width of the game board.\n" +
        "NOTE: The values of the height and width have to fall in the range of [6, 15]!\n";

    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests the {@link UserQuestions#displayFleet(Appendable, int)} method.
   *
   * @throws IOException if an I/O error occurs
   */
  @Test
  public void testDisplayFleet() throws IOException {
    StringWriter output = new StringWriter();
    int max = 10;
    UserQuestions.displayFleet(output, max);

    String expectedOutput = "Please enter a valid number of ships for each type in the order[Carrier, BattleShip, Destroyer, Submarine]\n" +
        "NOTE: Each shiptype has to have atleast one value and may not exceed the size " + max + "\n";

    assertEquals(expectedOutput, output.toString());
  }

  /**
   * Tests the {@link UserQuestions#displayShots(Appendable, int)} method.
   *
   * @throws IOException if an I/O error occurs
   */
  @Test
  public void testDisplayShots() throws IOException {
    StringWriter output = new StringWriter();
    int shots = 5;
    UserQuestions.displayShots(output, shots);

    String expectedOutput = "Please enter " + shots + " shots\n" +
        "Enter the coordinates here\n";

    assertEquals(expectedOutput, output.toString());
  }
}
