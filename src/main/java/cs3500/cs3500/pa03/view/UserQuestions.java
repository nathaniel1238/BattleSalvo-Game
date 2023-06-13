package cs3500.cs3500.pa03.view;

import java.io.IOException;

/**
 * represents the different questions that the user will be asked, its one purpose it to display
 * questions/instructions for the game
 */
public class UserQuestions {

  /**
   * asks the user for the height and width of the board and instructs them to make sure that
   * the values fall within the given range
   * @param output the value that helps create the messages in the console
   * @throws IOException
   */
  public static void displaySize(Appendable output) throws IOException {
    output.append("Hello! Welcome to BattleSalvo!")
        .append(System.lineSeparator());
    output.append("Please enter a valid height and " +
        "width of the game board.").append(System.lineSeparator());
    output.append("NOTE: The values of the height and width have " +
        "to fall in the range of [6, 15]!").append(System.lineSeparator());
  }

  /**
   * asks the user for the number of ships for each type
   * and instructs them that there is a maximum
   * number of ships and there needs to be atleast one of every shiptype
   *
   * @param output the value that helps create the messages in the console
   * @param max the lower value of the dimensions, the
   *            logic is handled in the SalvoGame Class
   *
   * @throws IOException
   */
  public static void displayFleet(Appendable output, int max) throws IOException {
    output.append("Please enter a valid number of ships for each type in the order[Carrier, BattleShip, Destroyer, Submarine]").append(System.lineSeparator());
    output.append("NOTE: Each shiptype has to have at least one value and may not exceed the size ").append(String.valueOf(max)).append(System.lineSeparator());
  }

  /**
   * asks the user to type in the number of shots that they need to shoot and the coordinates for those
   * shots
   * @param output the value that helps create the messages in the console
   * @param shots the number of shots that the user has
   * @throws IOException
   */
  public static void displayShots(Appendable output, int shots) throws IOException {
    output.append("Please enter ").append(String.valueOf(shots)).append(" shots")
        .append(System.lineSeparator());
    output.append("Enter the coordinates here").append(System.lineSeparator());
  }
}
