package main;

import java.util.LinkedList;
import java.util.Scanner;
import dataStructures.Node;
import enums.*;
import searchAlgorithms.*;

/* ******************
 * Class: EightPuzzle
 * ******************
 * Description:	The main class of the program. Displays menus,
 * 				receives user input, executes search algorithms,
 * 				and displays the resulting solution.
 */

public class EightPuzzle {
	// main
	// Welcomes the user, then displays the main menu.
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Eight Puzzle Solving AI.");
		System.out.println("Made by Daniel Ziegler for CSC 480");
		MainMenu(scanner);
		scanner.close();
	}
	
	// MainMenu
	// Prompts the user to choose an option. The function accepts
	// both upper-case and lower-case characters for selections.
	private static void MainMenu(Scanner s)
	{
		char selection = 0;
		while(selection == 0)
		{
			System.out.println("Please choose an option from the following menu:");
			System.out.println("S - Solve a puzzle");
			System.out.println("V - View starting configurations");
			System.out.println("Q - Quit program");
			System.out.print("Selection: ");
			selection = s.nextLine().charAt(0);
			System.out.print("\n");
			if(
					selection == 'S' || selection == 's' ||
					selection == 'V' || selection == 'v' ||
					selection == 'Q' || selection == 'q'
			) {
				if (selection == 'S' || selection == 's')
				{
					SolveMenu(s);
					selection = 0;
				}
				if (selection == 'V' || selection == 'v')
				{
					ViewMenu(s);
					selection = 0;
				}
				if (selection == 'Q' || selection == 'q')
				{
					System.out.println("Goodbye!");
				}
			} else {
				System.out.println("Invalid selection. Please try again.\n");
				selection = 0;
			}
		}
	}
	
	// SolveMenu
	// Prompts the user to choose which configuration to solve.
	// The user can choose the easy, medium, or hard configuration.
	// The program also lets the user enter a custom starting configuration,
	// view the configurations, or return to the top menu.
	private static void SolveMenu(Scanner s)
	{
		char selection = 0;
		while(selection == 0)
		{
			System.out.println("Please choose a starting configuration for the puzzle:");
			System.out.println("E - Easy");
			System.out.println("M - Medium");
			System.out.println("H - Hard");
			System.out.println("C - Custom configuration");
			System.out.println("V - View configurations");
			System.out.println("B - Go back");
			System.out.print("Selection: ");
			selection = s.nextLine().charAt(0);
			System.out.print("\n");
			if (
				selection == 'E' || selection == 'e' ||
				selection == 'M' || selection == 'm' ||
				selection == 'H' || selection == 'h' ||
				selection == 'C' || selection == 'c' ||
				selection == 'V' || selection == 'v' ||
				selection == 'B' || selection == 'b'
			) {
				if(selection == 'E' || selection == 'e')
					AlgorithmMenu(s, new Node(State.EASY));
				if(selection == 'M' || selection == 'm')
					AlgorithmMenu(s, new Node(State.MEDIUM));
				if(selection == 'H' || selection == 'h')
					AlgorithmMenu(s, new Node(State.HARD));
				if(selection == 'C' || selection == 'c')
					AlgorithmMenu(s, CustomNode(s));
				if(selection == 'V' || selection == 'v')
					ViewMenu(s);
			} else {
				System.out.println("Invalid selection. Please try again.");
				selection = 0;
			}
			System.out.print("\n");
		}
	}
	
	// Algorithm menu
	// After the user has selected a starting configuration,
	// this function prompts them to choose which algorithm to use,
	// then executes the algorithm on the configuration and displays
	// the results. If the search reaches the maximum depth, the
	// program displays an error instead. The user can also view
	// the configuration they chose, or return to the top menu.
	private static void AlgorithmMenu(Scanner s, Node config)
	{
		char selection = 0;
		Agent agent = null;
		LinkedList<Node> solution = new LinkedList<Node>();
		Result result;
		String depthString;
		int maxDepth;
		int[] data = new int[2];
		long startTime;
		long stopTime;
		while(selection == 0)
		{
			System.out.println("Choose which algorithm you'd like to use to solve the puzzle:");
			System.out.println("B - Breadth-first search");
			System.out.println("D - Depth-first search");
			System.out.println("I - Iterative deepening");
			System.out.println("U - Uniform cost");
			System.out.println("G - Best-first");
			System.out.println("1 - A*1 (h = number of tiles not in correct position");
			System.out.println("2 - A*2 (h = sum of Manhattan distances between all tiles and their correct positions");
			System.out.println("3 - A*3 (h = sum of costs for all potential moves)");
			System.out.println("V - View chosen configuration");
			System.out.println("R - Restart");
			selection = s.nextLine().charAt(0);
			System.out.print("\n");
			if (
				selection == 'B' || selection == 'b' ||
				selection == 'D' || selection == 'd' ||
				selection == 'I' || selection == 'i' ||
				selection == 'U' || selection == 'u' ||
				selection == 'G' || selection == 'g' ||
				selection == '1' || selection == '2' || selection == '3'
			) {
				if(selection == 'B' || selection == 'b')
					agent = new BreadthFirstAgent();
				if(selection == 'D' || selection == 'd')
					agent = new DepthFirstAgent();
				if(selection == 'I' || selection == 'i')
					agent = new IterativeDeepeningAgent();
				if(selection == 'U' || selection == 'u')
					agent = new UniformCostAgent();
				if(selection == 'G' || selection == 'g')
					agent = new BestFirstAgent();
				if(selection == '1')
					agent = new A1Agent();
				if(selection == '2')
					agent = new A2Agent();
				if(selection == '3')
					agent = new A3Agent();
				
				System.out.print("Choose a maximum search depth (leave blank for no maximum): ");
				depthString = s.nextLine();
				if(depthString.isEmpty())
				{
					depthString = "-1";
				}
				maxDepth = Integer.parseInt(depthString);
				
				if(agent != null)
				{
					startTime = System.currentTimeMillis();
					result = agent.Search(config, new Node(State.GOAL), solution, maxDepth, data);
					stopTime = System.currentTimeMillis();
					if(result == Result.SUCCESS)
					{
						long timeMillis = (stopTime - startTime);
						float timeSecs = (float)timeMillis / 1000.0f;
						System.out.println("Solution found:");
						for(Node n : solution)
							n.PrintState();
						System.out.println("--------------");
						System.out.println("Final cost: " + solution.getLast().PathCost);
						if(timeMillis > 1000)
						{
							System.out.println(String.format("Time: %.3f seconds", timeSecs));
						}
						else
						{
							System.out.println("Time: " + timeMillis + " milliseconds");
						}
						System.out.println("Number of nodes popped: " + data[0]);
						System.out.println("Peak queue length: " + data[1]);
					}
					if(result == Result.FAILURE)
					{
						System.out.println("An error occurred while running the search algorithm.");
					}
					if(result == Result.MAXDEPTH)
					{
						System.out.println("The search algorithm has reached the maximum search depth.");
					}
					if(result == Result.TIMEUP)
					{
						System.out.println("The search algorithm could not find a solution within 5 minutes; aborting.");
					}
				}
					
			} else if (selection == 'V' || selection == 'v')
			{
				System.out.println("Starting configuration:");
				config.PrintState();
				selection = 0;
			} else if (selection == 'R' || selection == 'r')
			{
				System.out.println("Returning to top menu.");
			}
			else {
				System.out.println("Invalid selection. Please try again.");
				selection = 0;
			}
			System.out.print("\n");
		}
	}
	
	// ViewMenu
	// Dieplays the view menu. This menu prompts the user to select
	// a configuration to view--easy, medium, hard, or goal. The user
	// can also return to the previous menu.
	private static void ViewMenu(Scanner s)
	{
		char selection = 0;
		while(selection == 0)
		{
			System.out.println("Choose which configuration you'd like to view:");
			System.out.println("E - Easy");
			System.out.println("M - Medium");
			System.out.println("H - Hard");
			System.out.println("G - Goal state");
			System.out.println("B - Back");
			System.out.print("Selection: ");
			selection = s.nextLine().charAt(0);
			System.out.print("\n");
			if (
				selection == 'E' || selection == 'e' ||
				selection == 'M' || selection == 'm' ||
				selection == 'H' || selection == 'h' ||
				selection == 'G' || selection == 'g'
			) {
				if(selection == 'E' || selection == 'e')
				{
					System.out.println("Easy configuration:");
					new Node(State.EASY).PrintState();
				}
				if(selection == 'M' || selection == 'm')
				{
					System.out.println("Medium configuration:");
					new Node(State.MEDIUM).PrintState();
				}
				if(selection == 'H' || selection == 'h')
				{
					System.out.println("Hard configuration:");
					new Node(State.HARD).PrintState();
				}
				if(selection == 'G' || selection == 'g')
				{
					System.out.println("Goal state:");
					new Node(State.GOAL).PrintState();
				}
			} else if(!(selection == 'B' || selection == 'b'))
			{
				System.out.println("Invalid selection. Please try again.");
				selection = 0;
			}
			System.out.print("\n");
		}
	}
	
	// CustomNode
	// Lets the user enter a custom starting node. The function
	// prompts the user to enter the value for each tile, one at a time,
	// and checks to make sure it's from 0-8 and isn't a duplicate.
	// Returns the custom node afer creating it.
	private static Node CustomNode(Scanner s)
	{
		System.out.println("Please input the numbers for each position (0 for empty space).");
		int[] customState = new int[9];
		for(int i = 0; i < 9; i++)
		{
			int input = -1;
			while(input == -1)
			{
				System.out.print("Position " + i + ": ");
				input = Integer.parseInt(s.nextLine());
				if(input > 8 || input < -1)
					input = -1;
				for(int j = 0; j < i; j++)
				{
					if(input == customState[j])
						input = -1;
				}
				if(input == -1)
					System.out.println("Invalid input. Please try again.");
				else
					customState[i] = input;
			}
		}
		Node result = new Node(customState);
		System.out.println("Your custom configuration is as follows:");
		result.PrintState();
		return result;
	}
}
