/**
 * @author Killian O'Dálaigh
 * @version 01/02/2019
 * Description:
 */
package infixToPostfix;

public class PostFixEvaluation {
	
	public PostFixEvaluation() {}
	
	public double evaluatePostfix(String s) {
		ArrayStack stack = new ArrayStack();
		
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
	
	private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')';
    }
	
	private static boolean isOperand(char ch) {
    	return (ch >= '0' && ch <= '9');
    }
	
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
		}
	}
	
}