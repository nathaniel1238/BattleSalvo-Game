//package Controller;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//
//class DesiredSizeTest {
//
////  @Test
////  void testReadSizeWithValidInput() {
////    // Prepare
////    String input = "8 10";
////    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
////    Scanner scanner = new Scanner(byteArrayInputStream);
////    DesiredSize desiredSize = new DesiredSize(scanner, (Readable) byteArrayInputStream);
////
////    // Execute
////    List<Integer> dimensions = desiredSize.readSize();
////
////    // Verify
////    List<Integer> expectedDimensions = Arrays.asList(8, 10);
////    Assertions.assertEquals(expectedDimensions, dimensions);
////  }
//
////  @Test
////  void testReadSizeWithInvalidInput() {
////    // Prepare
////    String input = "4 20";
////    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
////    Scanner scanner = new Scanner(byteArrayInputStream);
////    DesiredSize desiredSize = new DesiredSize(scanner, (Readable) byteArrayInputStream);
////
////    // Execute and Verify
////    Assertions.assertThrows(IllegalArgumentException.class, desiredSize::readSize);
////  }
////}
