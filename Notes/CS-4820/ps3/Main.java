import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
  public static void main(String[] args) throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // BufferedReader reader = new BufferedReader(
    // new
    // FileReader("C:/Users/ikmuj/Documents/Programming/4820/ps3/test_cases/highway_pub0.in"));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    try {
      // Read input
      String[] introVars = reader.readLine().split(" ");
      int numOptions = Integer.valueOf(introVars[0]);
      int highwayLength = Integer.valueOf(introVars[1]);
      int distanceConstraint = Integer.valueOf(introVars[2]);

      // Create Variables
      long[] optArr = new long[numOptions];
      int[][] optionInfo = new int[numOptions][2];
      // optionInfo First Elem is position, second is cost
      int[] lastPossibleOption = new int[numOptions];
      // lastPossibleOption If no possible options, set to -1
      int[] previousOptOption = new int[numOptions];
      Queue<Integer> solutionSet = new PriorityQueue<Integer>();

      // Read in costs
      int lastOptionIndex = 0;
      for (int i = 0; i < numOptions; i++) {
        String[] optionVars = reader.readLine().split(" ");
        optionInfo[i][0] = Integer.valueOf(optionVars[0]); // Mile Position
        optionInfo[i][1] = Integer.valueOf(optionVars[1]); // Cost
        // Fill out lastPossibleOption line
        lastPossibleOption[i] = -2;
        while (lastPossibleOption[i] == -2) {
          if (optionInfo[i][0] < distanceConstraint) {
            lastPossibleOption[i] = -1;
          } else if (optionInfo[i][0] - optionInfo[lastOptionIndex][0] > distanceConstraint) {
            lastOptionIndex++; // If the two options are too far away, move on
          } else {
            lastPossibleOption[i] = lastOptionIndex;
          }
        }
      }

      // Fill out Optimal Array
      long minCost = -1; // cost of optimal solution
      int endingPosition = -1; // Last option of optimal solution
      Queue<StationOption> minOptQueue = new PriorityQueue<StationOption>(numOptions,
          StationOption.STATIONOPTION_COMPARATOR);
      for (int i = 0; i < numOptions; i++) {
        previousOptOption[i] = -1;
        // Remove non neccesary from prioqueue
        while (minOptQueue.peek() != null && optionInfo[i][0] - minOptQueue.peek().milePos > distanceConstraint) {
          minOptQueue.poll();
        }
        // Pick Top From Prioqueue
        if (optionInfo[i][0] < distanceConstraint || minOptQueue.peek() == null) {
          optArr[i] = optionInfo[i][1];
        } else {
          optArr[i] = optionInfo[i][1] + minOptQueue.peek().optCost;
          previousOptOption[i] = minOptQueue.peek().number;
        }
        // Add option to prioqueue
        minOptQueue.add(new StationOption(optArr[i], i, optionInfo[i][0]));
        // Find optimal solution while going through array
        if (highwayLength - optionInfo[i][0] < distanceConstraint && (minCost == -1 || minCost > optArr[i])) {
          minCost = optArr[i];
          endingPosition = i;
        }
      }

      // Find Optimal solution path
      int tempPosition = endingPosition;
      while (tempPosition != -1) {
        solutionSet.add(optionInfo[tempPosition][0]);
        tempPosition = previousOptOption[tempPosition];
      }

      // Print answer
      writer.write(optArr[endingPosition] + "");
      writer.write("\n");
      writer.write(solutionSet.poll() + "");
      while (solutionSet.peek() != null) {
        writer.write(" ");
        writer.write(solutionSet.poll() + "");
      }
      writer.write("\n");
      writer.flush();

      // Close reader and writer
      reader.close();
      writer.close();
    } catch (IOException e) {
      System.out.println("Something went wrong with either reading or writing.");
    }
  }
}

class StationOption {
  long optCost;
  int number;
  int milePos;

  public static final Comparator<StationOption> STATIONOPTION_COMPARATOR = new Comparator<StationOption>() {
    public int compare(StationOption s1, StationOption s2) {
      return (int) (s1.optCost - s2.optCost);
    }
  };

  StationOption(long optCost, int number, int milePos) {
    this.optCost = optCost;
    this.number = number;
    this.milePos = milePos;
  }
}