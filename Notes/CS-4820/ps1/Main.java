import java.util.*;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Main {
  public static void main(String[] args) throws FileNotFoundException {
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // BufferedReader reader = new BufferedReader(
    // new FileReader("C:/Users/ikmuj/Documents/Programming/4820/ps1/Test4in.txt"));

    // Initializing data structures dependent on size
    try {
      int numPeople = Integer.valueOf(reader.readLine());
      int[][] manPref = new int[numPeople][numPeople];
      int[][] ranking = new int[numPeople][numPeople]; // Lower ranking = preferred
      int[] classicNext = new int[numPeople];
      int[] modifiedNext = new int[numPeople];
      modifiedNext[0] = 1;
      int[] classicCurrent = new int[numPeople];
      Arrays.fill(classicCurrent, -1); // -1 = Unmatched
      int[] modifiedCurrent = new int[numPeople];
      Arrays.fill(modifiedCurrent, -1); // -1 = Unmatched
      LinkedList<Integer> classicMen = new LinkedList<>();
      LinkedList<Integer> modifiedMen = new LinkedList<>();

      // Create iterable list of men
      for (int i = 0; i < numPeople; i++) {
        classicMen.add(i);
        modifiedMen.add(i);
      }

      // Read men preferences
      for (int i = 0; i < numPeople; i++) {
        String[] prefs = reader.readLine().split(" ");
        for (int j = 0; j < numPeople; j++) {
          manPref[i][j] = Integer.valueOf(prefs[j]);
        }
      }

      // Read women preferences
      for (int i = 0; i < numPeople; i++) {
        String[] womanPref = reader.readLine().split(" ");
        for (int j = 0; j < numPeople; j++) {
          ranking[i][Integer.valueOf(womanPref[j])] = j;
        }
      }

      // Classic: Match men with women
      int classicManZeroMatch = match(manPref, ranking, classicMen, classicNext, classicCurrent);

      // Modified: Match men with women
      int modifiedManZeroMatch = match(manPref, ranking, modifiedMen, modifiedNext, modifiedCurrent);

      // Print out matchings
      writer.write(classicCurrent[0] + "", 0, (classicCurrent[0] + "").length());
      writer.newLine();
      int w = classicManZeroMatch;
      int r2 = modifiedCurrent[w] == -1 ? 1 : (ranking[w][modifiedCurrent[w]] > ranking[w][0] ? 2 : 3);
      writer.write(r2 + "", 0, 1);
      writer.newLine();
      writer.flush();

      // Close Reader and Writer
      reader.close();
      writer.close();
    } catch (IOException e) {
      System.out.println("There was an issue with either reading or writing.");
    }
  }

  private static int match(int[][] manPref, int[][] ranking, LinkedList<Integer> men, int[] next, int[] current) {
    int manZeroMatch = -1;
    while (men.peek() != null) {
      int man = men.poll();
      boolean matched = false;

      while (!matched && next[man] < next.length) {
        int woman = manPref[man][next[man]];
        if (current[woman] == -1) {
          current[woman] = man;
          manZeroMatch = man == 0 ? woman : manZeroMatch;
          matched = true;
        } else if (ranking[woman][man] < ranking[woman][current[woman]]) {
          manZeroMatch = current[woman] == 0 ? -1 : (man == 0 ? woman : manZeroMatch);
          men.add(current[woman]);
          current[woman] = man;
          matched = true;
        }

        next[man]++;
      }
    }

    return manZeroMatch;
  }
}