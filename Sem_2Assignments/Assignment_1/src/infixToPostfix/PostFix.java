/**
 * @author Killian O'Dálaigh
 * @version 01/02/2019
 * Description: Class for all postfix
 * notional operations, inlcuding:
 * 		Transposing Infix to POstfix notation
 * 		Evaluating a Postfix equation
 */
package infixToPostfix;

public class PostFix {

	// Member Data
	private static ArrayStack stack; // Stack for conversion and evaluation
	
	public PostFix() {
		
		// Create an ArrayStack for usage
		stack = new ArrayStack();
		
	}// end Constructor
	
/****************************************************************************************************************/
	
	/*************************** Public functions ***************************/
	
	/**
	 * @param String
	 * @return String Converts an infix notation equation into a postfix notation
	 *         equation and returns that string
	 */
	public static String convertToPostfix(String infix) {

		// Create a stringBuffer for resulting string
		StringBuffer postfix = new StringBuffer(infix.length());
		char c; // holds character being operated on

		// Loop through string given
		for (int i = 0; i < infix.length(); i++) {
			c = infix.charAt(i); // take the next character in the string init=0

			if (isOperand(c)) { // Check is c an operand
				postfix.append(c); // Append c to the postfix string
			} else if (c == '(') { // Check if c is a (
				stack.push(c); // Push to stack
			}// end else
			
			// If the scanned character is an ‘)’, pop and output from the stack
			// until an ‘(‘ is encountered.
			else if (c == ')') {
				
				// loop while the stack isn't empty and ( isn't at the top of the stack 
				while (!stack.isEmpty() && (char) stack.top() != '(') {
					postfix.append(stack.pop()); // Append all items to stack
				}// end while
				
				// If there is something left in the stack and the top isn't (
				if (!stack.isEmpty() && (char) stack.top() != '(')
					return null;
				else if (!stack.isEmpty())
					stack.pop(); // pop the ( character because it is no longer needed
				
			} else if (isOperator(c)) // operator encountered
			{
				
				// Add things from the stack to the postfix string while the precedence of c
				// isn't less than or equal to the item at the top of the stack
				if (!stack.isEmpty() && getPrecedence(c) <= getPrecedence((char) stack.top())) {
					postfix.append(stack.pop());
				}
				
				// Once the placement for the operator is found push it to the stack
				stack.push(c);
			}

		} // end for

		// Pop all from the stack to the postfix string
		while (!stack.isEmpty()) {
			postfix.append(stack.pop());
		}

		return postfix.toString(); // converts StringBuffer to string

	}// end convertToPostfix
	
	
	/**
	 * @param String
	 * @return Evaluates a postfix notational equation
	 */
	public double evaluatePostfix(String s) {
		
		// Turn string into character array
		char[] charArray = s.toCharArray();
		
		// for every char in the array
		for(char c:charArray) {
			
			// Check is c an operand
			if(isOperand(c)) {
				
				// Converts the character to a numerical value
				double a = Character.getNumericValue(c);
				
				// Pushes value to the stack
				stack.push(a);
			}
			else if(isOperator(c)) {
				
				// Get the two values from the stack
				double b = (double)stack.pop(); // B is always first because
												// of the LIFO operation of the stack
				double a = (double)stack.pop(); // A is always second out
				stack.push(evaluateOperator(a,b, c)); // Evaluates the sum
			}// end else
		}// end for

		// return the value left on the stack which is the total of the sum
		return (double) stack.pop();
		
	}// end evaluatePostfix
	
/****************************************************************************************************************/
	
	/*************************** Helper functions ***************************/
	
	/**
	 * @param char
	 * @return true if operator Returns true if c is an operator
	 */
	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')';
	}

	/**
	 * @param char
	 * @return numerical value for operator 
	 * Assigns a numerical value to each operation for comparison
	 */
	private static int getPrecedence(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;

		case '*':
		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}

	/**
	 * @param char
	 * @return boolean Returns true if ch is between 0 and 9 including 0 and 9
	 */
	private static boolean isOperand(char ch) {
		return (ch >= '0' && ch <= '9');
	}
	
	/**
	 * @param Double, Double, char
	 * @return returns the value of the operation on the
	 * two operands a and b
	 */
	private double evaluateOperator(double a, double b, char c) {
		switch(c) {
			case '+':
				return a+b;
			case '-':
				return a-b;
			case '*':
				return a*b;
			case '/':
				if (a == 0 && b == 0) {
					return Double.NaN;
				}
				if (b == 0) {
					return Double.POSITIVE_INFINITY;
				}
				return a/b;
			case '^':
				return (Math.pow(a, b));
			default:
				return a;
		}// end switch
	}// end evaluateOperator

}// End class Postfix
