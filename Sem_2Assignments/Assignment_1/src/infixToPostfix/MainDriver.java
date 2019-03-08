/**
 * @author Killian O'Dálaigh
 * @version 01/02/2019
 * Description: Main driver class for the program,
 * holds entry point for the program and displays
 * the input menu to the user.
 */
package infixToPostfix;
import javax.swing.JOptionPane;

// Main driver class for program
public class MainDriver {

	// Member Data
	private String UserInput;

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		//Test t = new Test();
		MainDriver m = new MainDriver();
	}// End main

	// Main driver method
	public MainDriver() {
		
		// Create an instance of the class
		PostFix pos = new PostFix();

		// error variable for display
		boolean error = false; // true == error in input
							   // false == error in input

		
		// Do While for Program display
		do {

			// Gets users input
			UserInput = getInput(error);
			
			error = false;

			// Error checks input
			error = checkInput(UserInput);

		} while (error == true); // Re-show panel if error

		String postfix = pos.convertToPostfix(UserInput);
		double res = pos.evaluatePostfix(postfix);
		String showUser = "Equation entered -> " + UserInput + "\n" + "PostFix -> " + postfix + "\n" + "Evaluation = " + res; 
		JOptionPane.showMessageDialog(null, showUser);
		

	}// End constructor

	/**
	 * @param boolean error
	 * @return string
	 * Gets input from the user
	 */
	private String getInput(boolean error) {

		// Tell user what is wrong
		if (error == true) {
			JOptionPane.showMessageDialog(null, "You must enter an equation using single digits 0-9 "
					+ "and +, -, *, /, ^, (, )\nAnd it must be" + "between 3 and 20 charachter long");
		} // end if

		// Asks user for input
		return JOptionPane.showInputDialog("Please input your equation in infix notation");

	}// end getInput

	/**
	 * @param str
	 * @return boolean(error)
	 * Checks an input using restrictions
	 */
	private boolean checkInput(String str) {

		// If char length is out of bounds return error = true
		if ((str.length() < 3) || (str.length() > 20)) {
			return true;
		} else {

			// Transfers string to char array
			char[] charArray = str.toCharArray();

			// Checks for errors
			for (int i = 0; i < charArray.length; i++) {

				// Check input
				switch (charArray[i]) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '+':
				case '-':
				case '*':
				case '/':
				case '^':
				case '(':
				case ')':
					break;
				default:
					return true;
				}// End switch

			} // End for

		} // end else

		return false;

	}// End checkInput

}
