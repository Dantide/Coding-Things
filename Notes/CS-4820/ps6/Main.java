import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.*;
import java.util.*;

class Main
{
	int n; // number of candidates
	int k; // number of recruiters

	// provided data structures (already filled in)
	ArrayList<ArrayList<Integer>> neighbors; // size n + k
	int[] recruiterCapacities;
	int[] preliminaryAssignment; // Empty for n-1

	// provided data structures (you need to fill these in)
	boolean existsValidAssignment;
	int[] validAssignment;
	int[] bottleneckRecruiters;

	//START=======================================================================
	// Other variables
	private final static boolean SUBMITTING = true;
	private final static boolean DEBUGGING = true && !SUBMITTING;
	private final static String TEST_CASE = "Test2in.txt";
	private final static String CURRENT_DIR = System.getProperty("user.dir");
	int[] realCapacities;
	int[] numberAssigned;
	
	//END=========================================================================

	// reading the input
	void input()
	{
		BufferedReader reader = null;

		try
		{
			//START==================================================================
			if (DEBUGGING) {System.out.println("\n\n\n");}

			reader = SUBMITTING ?
					new BufferedReader(new InputStreamReader(System.in)) :
					new BufferedReader(new FileReader(CURRENT_DIR + "\\public_test_cases\\" + TEST_CASE));
			//END=====================================================================

			String text = reader.readLine();
			String[] parts = text.split(" ");

			n = Integer.parseInt(parts[0]);
			k = Integer.parseInt(parts[1]);
			neighbors = new ArrayList<ArrayList<Integer>>(n+k);
			recruiterCapacities = new int[n+k];
			preliminaryAssignment = new int[n];

			for (int j = 0; j < n+k; j++) {
				neighbors.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < n; i++) {
				text = reader.readLine();
				parts = text.split(" ");
				int numRecruiters = Integer.parseInt(parts[0]);
				for (int j = 0; j < numRecruiters; j++) {
					int recruiter = Integer.parseInt(parts[j+1]);
					neighbors.get(i).add(recruiter);
					neighbors.get(recruiter).add(i);
				}
			}

			text = reader.readLine();
			parts = text.split(" ");
			for (int j = 0; j < k; j++) {
				recruiterCapacities[n+j] = Integer.parseInt(parts[j]);
			}

			text = reader.readLine();
			parts = text.split(" ");
			for (int i = 0; i < n-1; i++) {
				preliminaryAssignment[i] = Integer.parseInt(parts[i]);
			}

			reader.close();

			//START===================================================================

			// Get a better upper bound for the capacities of all the recruiters 
			//* O(n*m) where n=realCapacties.length and m=neighbors.get(i).size()
			realCapacities = new int[n+k];
			for (int i = n; i < realCapacities.length; i++) {
				realCapacities[i] = Math.min(recruiterCapacities[i], neighbors.get(i).size());
			}

			// Check if obvious that no valid perfect matching (i.e. exists a recruiter
			// not at max capacity)
			numberAssigned = new int[n+k];
			for (int i = 0; i < n - 1; i++) {
				numberAssigned[preliminaryAssignment[i]] += 1;
			}

			//END=====================================================================
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// writing the output
	void output()
	{
		try
		{
			PrintWriter writer = new PrintWriter(System.out);

			if (existsValidAssignment) {
				writer.println("Yes");
				for (int i = 0; i < n-1; i++) {
					writer.print(validAssignment[i] + " ");
				}
				writer.println(validAssignment[n-1]);
			} else {
				writer.println("No");
				for (int j = 0; j < n+k-1; j++) {
					writer.print(bottleneckRecruiters[j] + " ");
				}
				writer.println(bottleneckRecruiters[n+k-1]);
			}

			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//START=======================================================================

	// returns an array containing the index of the backwards reassignment, and the
	// recruiter index
	// backwards reassignment is -1 if can be pushed without reassignment, or -2
	// if cannot be pushed at all.
	int[] findPath(int n, int[] tempAssignment) {
		for (Integer i : neighbors.get(n)) {
			int node = findPathTo(n, i, tempAssignment);
			if (node >= -1) {
				return new int[] {node, i};
			}
		}
		return new int[] {-2, 0};
	}

	// returns the index of the node that can be pushed backwards flow through.
	// returns -1 if can be pushed without reassignment, or -2 if cannot be pushed
	// at all.
	int findPathTo(int n, int recruiter, int[] tempAssignment) {
		if (realCapacities[recruiter] == 0) {
			return -2;
		} else if (numberAssigned[recruiter] < realCapacities[recruiter]) {
			return -1;
		} else {
			for (int i = 0; i < n; i++) {
				if (tempAssignment[i] == recruiter) {return i;}
			}
		}
		return -2;
	}

	// Finds Bottlenecking Recruiters
	void findBottleneckingRecruiters() {
		
		boolean[] visited = new boolean[n + k];
		Queue<Integer> queue = new LinkedList<Integer>();
		existsValidAssignment = false;
		queue.add(n-1);

		while (queue.peek() != null) {
			int person = queue.poll();
			if (!visited[person]) {
				visited[person] = true;
				if (person < n) {
					for (Integer i : neighbors.get(person)) {
						if (!visited[i]) {
							queue.add(i);
						}
						bottleneckRecruiters[i] = 1;
					}
				} else {
					for (Integer i : neighbors.get(person)) {
						if (!visited[i] && neighbors.get(n-1).contains(preliminaryAssignment[i])) {
							queue.add(i);
						}
					}
				}
			}
		}/*
		for (Integer i : neighbors.get(n-1)) [
			bottleneckRecruiters[i] = 1;
		]*/
	}

	void redirectFlow(int[] tempAssignment, int backflowNode, int node, int recruiter) {
		if (DEBUGGING) {System.out.println("backflowNode = " + backflowNode);}
		tempAssignment[backflowNode] = -1;
		tempAssignment[node] = recruiter;
	}

	//END=========================================================================

	public Main()
	{
		input();

		// Fill these in as instructed in the problem statement.
		existsValidAssignment = false;
		validAssignment = new int[n];
		bottleneckRecruiters = new int[n+k];

		//YOUR CODE STARTS HERE=====================================================
		
		boolean moreCap = false;
		for (int i = n; i < n + k && !moreCap; i++) {
			if (numberAssigned[i] < realCapacities[i]) {moreCap = true;}
		}


		boolean answerFound = false;
		if (moreCap) {
			// Know that there is a recruiter with the ability to take someone else
			// instead of their current assignment, but not sure what to swap

			// Run Ford-Fulkerson Algorithm
			
			ArrayList possibleAssignments = neighbors.get(n-1);
			if (DEBUGGING) {System.out.println("Possible recruiters: " + possibleAssignments.size());}
			Queue toBeSearchedQueue = new LinkedList<Integer>();
			int[] tempAssignment;

			// try assigning leftover node to each possible recruiter
			for (int i = 0; i < possibleAssignments.size() && !answerFound; i++) {
				tempAssignment = new int[n];
				System.arraycopy(preliminaryAssignment, 0, tempAssignment, 0, n);
				int recruiter = (int) possibleAssignments.get(i);
				toBeSearchedQueue.clear();
				if (DEBUGGING) {System.out.println("Recruiter #" + i + ": " + recruiter);}

				// If recruiter can hold people,
				if (realCapacities[recruiter] != 0) {
					// Send node n to that recruiter
					int backflowNode = findPathTo(n-1, recruiter, tempAssignment);
					if (DEBUGGING) {
						System.out.println("Recruiter can hold more people");
						System.out.println("BackFlowNode: " + backflowNode);
					}
					// If had to reassign,
					if (backflowNode >= 0) {
						if (DEBUGGING) {System.out.println("Initial Reassignment");}
						redirectFlow(tempAssignment, backflowNode, n-1, recruiter);
						toBeSearchedQueue.add(backflowNode);
					} else if (backflowNode == -1) {
						if (DEBUGGING) {System.out.println("Initial Assignment, Answer found");}
						tempAssignment[n-1] = recruiter;
						answerFound = true;
					}
				}

				// Residual Graph Loop
				while (toBeSearchedQueue.peek() != null) {
					int node = (int) toBeSearchedQueue.poll();
					int backflowNode = findPath(node, tempAssignment)[0];
					switch (backflowNode) {
						case -1: // pushed, no reassignment
						answerFound = true;
						tempAssignment[backflowNode] = k;
						break;

						case -2: // cannot be pushed
						break;

						default: // push with reassignment
						redirectFlow(tempAssignment, backflowNode, node, recruiter);
						toBeSearchedQueue.add(backflowNode);
					}
				}

				if (answerFound) {
					if (DEBUGGING) {System.out.println("We Found an Answer");}
					existsValidAssignment = true;
					validAssignment = tempAssignment;
				}
				else {
					if (DEBUGGING) {System.out.println("No Answer found.");}
					findBottleneckingRecruiters();
				}
			}
		} else {
			// No valid matching, thus find Bottlenecking recruiters
			if (DEBUGGING) {System.out.println("moreCap is false");}
			findBottleneckingRecruiters();
		}

		//YOUR CODE ENDS HERE=======================================================

		output();
	}

    // Strings in Args are the name of the input file followed by
    // the name of the output file.
	public static void main(String [] Args) 
	{
		new Main();
	}
}
