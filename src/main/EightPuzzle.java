package main;

import java.util.LinkedList;
import dataStructures.Node;
import enums.*;
import searchAlgorithms.*;

public class EightPuzzle {
	public static void main(String[] args)
	{
		LinkedList<Node> solution = new LinkedList<Node>();
		Result result = new IterativeDeepeningAgent().IDSearch(new Node(State.EASY), new Node(State.GOAL), solution, 100); 
		if(result == Result.SUCCESS) {
			for(Node n : solution)
			{
				n.PrintState();
			}
		} else if(result == Result.MAXDEPTH)
		{
			System.err.println("Depth limit reached - Search aborted.");
		} else {
			System.err.println("Search algorithm failed.");
		}
	}
}
