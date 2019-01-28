package enums;

/* ************
 * Enum: Result
 * ************
 * Description:	An enum representing the result of an operation.
 * Values:
 * 		SUCCESS:	The operation was successful
 * 		FAILURE:	The operation failed
 * 		MAXDEPTH:	Used only in search functions;
 * 					indicates that the search function reached
 * 					the maximum allowed depth without finding
 * 					a solution
 * 		TIMEUP:		The operation took too long.
 */
public enum Result {
	SUCCESS,
	FAILURE,
	MAXDEPTH,
	TIMEUP
}
