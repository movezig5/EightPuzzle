package enums;

/* ***********
 * Enum: State
 * ***********
 * Description:	An enum representing a predefined
 * 				configuration for the puzzle; used
 * 				for constructing starting nodes.
 * Values:
 * 				EASY:	The easy configuration:
 * 						1 3 4 8 6 2 7 0 5
 * 				MEDIUM:	The medium configuration:
 * 						2 8 1 0 4 3 7 6 5
 * 				HARD:	The hard configuration:
 * 						5 6 7 4 0 8 3 2 1
 * 				GOAL:	The goal state:
 * 						1 2 3 8 0 4 7 6 5
 */
public enum State {
	EASY,
	MEDIUM,
	HARD,
	GOAL
}
