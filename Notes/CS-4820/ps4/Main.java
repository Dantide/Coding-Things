import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.*;


class Main {
  private final static boolean SUBMITTING = true;
  private final static boolean DEBUGGING = true && !SUBMITTING;
  private final static String TEST_CASE = "siginvpub1.in";
  private final static String CURRENT_DIR = System.getProperty("user.dir");
  public static void main(String[] args) throws FileNotFoundException {
    BufferedReader reader = SUBMITTING ?
        new BufferedReader(new InputStreamReader(System.in)) :
        new BufferedReader(new FileReader(CURRENT_DIR + "\\public_test_cases\\" + TEST_CASE));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    try {
      // Read length and elements
      int listLength = Integer.valueOf(reader.readLine());
      String[] listElems = reader.readLine().split(" ");

      // Create element list
      ArrayList<Integer> elems = new ArrayList<Integer>();
      for (int i = 0; i < listLength; i++) {
        elems.add(Integer.valueOf(listElems[i]));
      }

      // Algorithm Sort-And-Count
      SizeListTuple ans = sortAndCount(elems);

      // Print answer to System.out
      if (DEBUGGING) {
        writer.write("\n\n\n\n");
        for (Integer i : ans.list) {
          writer.write(i.toString() + " ");
        }
        writer.write("\n");
      }
      writer.write(ans.size + "\n");
      writer.flush();

      // Close reader and Writer
      reader.close();
      writer.close();
    } catch (IOException e) {
      System.out.println("Something went wrong with reading or writing.");
    }
  }

  /**
   * Divide and conquer recurrence.
   * 
   * @param elems The list.
   * 
   * @returns Tuple with num inversions and List of sorted elements of elems
   */
  private static SizeListTuple sortAndCount(List<Integer> elems) {
    // Base Case
    if (elems.size() == 1) {
      return new SizeListTuple(0, elems);
    } else {
      List<Integer>[] dividedLstLst = divide(elems);

      SizeListTuple sort1 = sortAndCount(dividedLstLst[0]);
      long invsA = sort1.size;
      SizeListTuple sort2 = sortAndCount(dividedLstLst[1]);
      long invsB = sort2.size;
      SizeListTuple merge = mergeAndCount(sort1.list, sort2.list);
      long invsMerge = merge.size;

      return new SizeListTuple((invsA + invsB + invsMerge), merge.list);
    }
  }

  /**
   * Merge step done in O(n log n) (hopefully).
   * 
   * @param A The first half of the list.
   * @param B The second half of the list.
   * @returns List of sorted elements of elems, with the first element of the
   * list as number of inversions in the list.
   */
  private static SizeListTuple mergeAndCount(List<Integer> A, List<Integer> B) {
    long count = 0;
    int i = 0; int j = 0; int pointer = 0;
    ArrayList<Integer> solution = new ArrayList<Integer>();
    while (i < A.size() && j < B.size()) {
      int a = A.get(i);
      int b = B.get(j);

      while (pointer <= j && B.get(pointer) + 1 < a) {
        // Printing Statements for debugging
        if (DEBUGGING) {
          System.out.print("inv: " + a + ", " + B.get(pointer) + ";     i: " + i + ", j: " + j + ", pointer: " + pointer + ";     A: [");
          for (int e : A) {System.out.print(e + " ");}
          System.out.print("] B: [");
          for (int e : B) {System.out.print(e + " ");}
          System.out.println("]");
        }
        // Debugging statements ended
        count += (A.size() - i);
        pointer += 1;
      }
      if (b < a) solution.add(B.get(j++));
      else solution.add(A.get(i++));
    }
    if (i == A.size()) {
      while (j < B.size()) {solution.add(B.get(j++));}
    } else {
      while (i < A.size()) {
        int a = A.get(i);
        while (pointer < B.size() && B.get(pointer) + 1 < a) {
          // Printing Statements for debugging
          if (DEBUGGING) {
            System.out.print("inv: " + a + ", " + B.get(pointer) + ";     i: " + i + ", j: " + j + ", pointer: " + pointer + ";     A: [");
            for (int e : A) {System.out.print(e + " ");}
            System.out.print("] B: [");
            for (int e : B) {System.out.print(e + " ");}
            System.out.println("]");
          }
          // Debugging statements ended
          count += (A.size() - i);
          pointer += 1;
        }
        solution.add(a);
        i += 1;
      }
    }

    return new SizeListTuple(count, solution);
  }

  /**
   * Pretty much at max effieciency
   */
  private static List<Integer>[] divide(List<Integer> lst) {
    /* ArrayList implementation
    ArrayList<Integer> firstHalf = new ArrayList<Integer>();
    for (int i = 0; i < lst.size() / 2; i++) {
      firstHalf.add(lst.remove(0));
    }
    ArrayList<Integer>[] temp = new ArrayList[2];
    temp[0] = firstHalf;
    temp[1] = lst;
    return temp;
    */
    List<Integer>[] temp = new List[2];
    temp[0] = lst.subList(0, lst.size() / 2);
    temp[1] = lst.subList(lst.size() / 2, lst.size());
    return temp;
  }
}



class SizeListTuple {
  public long size;
  public List<Integer> list;

  SizeListTuple(long size, List<Integer> list) {
    this.size = size;
    this.list = list;
  }
}