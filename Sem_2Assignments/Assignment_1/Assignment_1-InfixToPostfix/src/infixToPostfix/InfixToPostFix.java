/**
 * @author Killian O'Dálaigh
 * @version 01/02/2019
 * Description:
 */
package infixToPostfix;

// Converts infix notation to postfix
public class InfixToPostFix {
	
	// Constructor
	public InfixToPostFix() {}// end constructor

	/**
	 * @param char c
	 * @return true if operator
	 */
	private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')';
    }

	/**
	 * @param char ch
	 * @return numerical value for operator
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
     * @param ch
     * @return true if is operand
     */
    private static boolean isOperand(char ch) {
    	return (ch >= '0' && ch <= '9');
    }

    /**
     * @param infix
     * @return infix string as postfix
     */
    public static String convertToPostfix(String infix) {
    	
    	ArrayStack stack = new ArrayStack();
        StringBuffer postfix = new StringBuffer(infix.length());
        char c;

        for (int i = 0; i < infix.length(); i++) {
            c = infix.charAt(i);

            if (isOperand(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            }
            // If the scanned character is an ‘)’, pop and output from the stack
            // until an ‘(‘ is encountered.
            else if (c == ')') {

                while (!stack.isEmpty() && (char)stack.top() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty() && (char)stack.top() != '(')
                    return null;
                else if(!stack.isEmpty())
                    stack.pop();
            }
            else if (isOperator(c)) // operator encountered
            {
                if (!stack.isEmpty() && getPrecedence(c) <= getPrecedence((char)stack.top())) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
            
        }// end for

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
    
        return postfix.toString();
    
    }// end convertToPostfix
    
}// end class InfixToPostfix