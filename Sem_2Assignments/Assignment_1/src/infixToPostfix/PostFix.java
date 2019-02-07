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
		
		// Create an ArrayStack
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
		char c;

		// Loop through string given
		for (int i = 0; i < infix.length(); i++) {
			c = infix.charAt(i); // take the next charachter in the string

			if (isOperand(c)) {
				postfix.append(c);
			} else if (c == '(') {
				stack.push(c);
			}
			// If the scanned character is an ‘)’, pop and output from the stack
			// until an ‘(‘ is encountered.
			else if (c == ')') {

				while (!stack.isEmpty() && (char) stack.top() != '(') {
					postfix.append(stack.pop());
				}
				if (!stack.isEmpty() && (char) stack.top() != '(')
					return null;
				else if (!stack.isEmpty())
					stack.pop();
			} else if (isOperator(c)) // operator encountered
			{
				if (!stack.isEmpty() && getPrecedence(c) <= getPrecedence((char) stack.top())) {
					postfix.append(stack.pop());
				}
				stack.push(c);
			}

		} // end for

		while (!stack.isEmpty()) {
			postfix.append(stack.pop());
		}

		return postfix.toString();

	}// end convertToPostfix
	
	
	/**
	 * @param String
	 * @return Evaluates a postfix notational equation
	 */
	public double evaluatePostfix(String s) {
		
		char[] charArray = s.toCharArray();
		
		for(char c:charArray) {
			if(isOperand(c)) {
				double a = Character.getNumericValue(c);
				stack.push(a);
			}
			else if(isOperator(c)) {
				double b = (double)stack.pop();
				double a = (double)stack.pop();
				stack.push(evaluateOperator(a,b, c));
			}
		}

		return (double) stack.pop();
		
	}
	
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
	 * @return numerical value for operator Returns a numerical value for precedence
	 *         of an operator
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
