package infixToPostfix;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Test {

	/**
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Test t = new Test();
	}// End main
	
	// constructor
	public Test() {

		// Create an instance of the 2 classes to convert infix to postfix
		InfixToPostFix in = new InfixToPostFix(); // Converts to Postfix
		PostFixEvaluation eval = new PostFixEvaluation(); // Evaluates a postfix equation

		// Create test array
		int correct = 0;
		int incorrect = 0;
		ArrayList<String> results = new ArrayList<String>();
		String s = "";
		s = "Equation solution PostfixSolution";
		results.add(s);

		// Test for plus operation: A+B
		for (int a = 0; a < 10; a++) {
			for (int b = 0; b < 10; b++) {
				s = "";
				s += Integer.toString(a);
				s += "+";
				s += Integer.toString(b);
				String Res = s + " " + " " + (double)(a + b) + " ";
				String postfix = in.convertToPostfix(s);
				double res = eval.evaluatePostfix(postfix);
				if (res == (double)(a + b)) {
					correct += 1;
				} else {
					incorrect += 1;
					Res += res + "\n";
					results.add(Res);
				}
			}
		}

		// Test for minus operation: A-B
		for (int a = 0; a < 10; a++) {
			for (int b = 0; b < 10; b++) {
				s = "";
				s += Integer.toString(a);
				s += "-";
				s += Integer.toString(b);
				String Res = s + " " + " " + (double)(a - b) + " ";
				String postfix = in.convertToPostfix(s);
				double res = eval.evaluatePostfix(postfix);
				if (res == (double)(a - b)) {
					correct += 1;
				} else {
					incorrect += 1;
					Res += res + "\n";
					results.add(Res);
				}
			}
		}

		// Test for multiplication operation: A*B
		for (int a = 0; a < 10; a++) {
			for (int b = 0; b < 10; b++) {
				s = "";
				s += Integer.toString(a);
				s += "*";
				s += Integer.toString(b);
				String Res = s + " " + " " + (double)(a * b) + " ";
				String postfix = in.convertToPostfix(s);
				double res = eval.evaluatePostfix(postfix);
				if (res == (double)(a * b)) {
					correct += 1;
				} else {
					incorrect += 1;
					Res += res + "\n";
					results.add(Res);
				}
			}
		}

		// Test for division operation: A/B
		for (int a = 0; a < 10; a++) {
			for (int b = 0; b < 10; b++) { // Can not divide by 0
				s = "";
				s += Integer.toString(a);
				s += "/";
				s += Integer.toString(b);
				String Res = s + " " + " " + (double)((double)a / (double)b) + " ";
				String postfix = in.convertToPostfix(s);
				double res = eval.evaluatePostfix(postfix);
				if (res == (double)((double)a / (double)b)) {
					correct += 1;
				} else {
					incorrect += 1;
					Res += res + "\n";
					results.add(Res);
				}
			}
		}

		// Test for power operation: A^B
		for (int a = 0; a < 10; a++) {
			for (int b = 0; b < 10; b++) {
				s = "";
				s += Integer.toString(a);
				s += "^";
				s += Integer.toString(b);
				String Res = s + " " + " " + (double)(Math.pow(a, b)) + " ";
				String postfix = in.convertToPostfix(s);
				double res = eval.evaluatePostfix(postfix);
				if (res == (double)(Math.pow(a, b))) {
					correct += 1;
				} else {
					incorrect += 1;
					Res += res + "\n";
					results.add(Res);
				}
			}
		}

		// Test for parentheses operation: (A+B)*(A-B)
		for (int a = 0; a < 10; a++) {
			for (int b = 0; b < 10; b++) {
				s = "(";
				s += Integer.toString(a);
				s += "+";
				s += Integer.toString(b);
				s += ")";
				s += "*";
				s += "(";
				s += Integer.toString(a);
				s += "-";
				s += Integer.toString(b);
				s += ")";
				String Res = s + " " + " " + (double)(((double)a + (double)b) * ((double)a - (double)b)) + " ";
				String postfix = in.convertToPostfix(s);
				double res = eval.evaluatePostfix(postfix);
				if (res == (double)(((double)a + (double)b) * ((double)a - (double)b))) {
					correct += 1;
				} else {
					incorrect += 1;
					Res += res + "\n";
					results.add(Res);
				}
			}
		}

		System.out.println("amount incorrect: " + incorrect);
		System.out.println("amount correct: " + correct);
		if (incorrect > 0) {
			for (int i = 0; i < results.size(); i++) {
				System.out.println(results.get(i));
			}
		}
		
	}// End constructor

}