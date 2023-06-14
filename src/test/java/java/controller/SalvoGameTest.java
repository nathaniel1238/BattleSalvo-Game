package java.controller;

import cs3500.cs3500.pa03.controller.SalvoGame;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SalvoGameTest {
  private Readable input;
  private StringWriter output;
  private Scanner scanner;
  private SalvoGame salvoGame;

  @Test
  void run() {
    StringBuilder builder = new StringBuilder("10 10\n 1 1 1 1\n 1 0 0 0 11 11");
    input = new StringReader(builder.toString());
    output = new StringWriter();
    salvoGame = new SalvoGame(input, output);
    assertThrows(NoSuchElementException.class, () -> salvoGame.run());
  }
}