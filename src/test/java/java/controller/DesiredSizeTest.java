package java.controller;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.cs3500.pa03.controller.DesiredSize;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Scanner;

class DesiredSizeTest {
  String size = "";
  Readable read = new StringReader(size);
  Scanner sc  = new Scanner(read);
  @Test
  void testReadSizeWithValidInput() {
    // Prepare
    String size = "8 10";
    Readable read = new StringReader(size);
    Scanner sc  = new Scanner(read);

    DesiredSize user_size = new DesiredSize(sc, read);
    List<Integer> list = user_size.readSize();
    List<Integer> integers = new ArrayList<>();
    integers.add(8);
    integers.add(10);
    assertEquals(list, integers);
  }

  @Test
  void testReadSizeWithInvalidInput() {
    // Prepare
    String input = "4 20\n";
    Readable read = new StringReader(input);
    Scanner sc = new Scanner(read);
    DesiredSize ds = new DesiredSize(sc, read);
    Exception exc = assertThrows(NoSuchElementException.class, () -> ds.readSize());
    assertEquals(null, exc.getMessage());
  }
}
