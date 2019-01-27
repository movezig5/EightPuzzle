package searchAlgorithms;

import java.util.LinkedList;
import dataStructures.Node;
import enums.Result;
import searchAlgorithms.DepthFirstAgent;

public class IterativeDeepeningAgent extends DepthFirstAgent {
	public Result IDSearch(Node init, Node goal, LinkedList<Node> solution, int cutoff)
	{
		Result result;
		for(int i = 0; i < cutoff; i++)
		{
			result = Search(init, goal, solution, i);
			if(result == Result.SUCCESS)
				return result;
		}
		return Result.MAXDEPTH;
	}
}
