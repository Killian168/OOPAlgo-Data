package palindromes;

/**
 * Class ruling over the operations and checking of palidromes
 * 
 * @author Killian O'Dálaigh
 * @version 15/Feb/2019
 * 
 */
public class Palidrome {

	public static int reverseCheckCount = 0; // Holds operation count for reverseCheck
	public static int compareCheckCount = 0; // Holds operation count for compareCheck
	public static int stackCheckCount = 0; // Holds operation count for stackCheck

	/**
	 * Class constructor
	 */
	public Palidrome() {
	}// end constructor

	/************************** Public Methods **************************/

	/**
	 * @param String
	 * @return Boolean Returns true if s is a palindrome
	 */
	public static boolean reverseCheck(String s) {

		// Method data
		char[] str = s.toCharArray();
		char temp = ' ';
		int upper = 0;// holds index of end chars to be swapped
		reverseCheckCount += 4; // 3 assignment operations, and get s.toCharArray()

		// Reverses string
		for (int i = 0; i < str.length / 2; i++) {

			reverseCheckCount += 4; // i=n, check i < .lenght, getting .lenght, /2

			// Get index of next swapping position from the
			// back
			upper = (str.length - 1 - i);
			reverseCheckCount += 4; // 4 operations, getting .length, -1, -i, and assignment

			// Switches around characters
			temp = str[i];
			reverseCheckCount += 3; // 3 operations, getting str[i], temp and assignment
			str[i] = str[upper];
			reverseCheckCount += 3; // 3 operations, getting str[i], str[upper] and assignment
			str[upper] = temp;
			reverseCheckCount += 3; // 3 operations, getting str[upper], temp value, and assignment

		} // end for

		// Always and end check not counted inside the for loop
		reverseCheckCount += 3; // i=n, check i < .lenght, getting .lenght

		// Check if the strings match up in reverse and original
		// order
		for (int i = 0; i < str.length - 1; i++) {
			reverseCheckCount += 4; // i=n, check i < .lenght, -1, getting .lenght
			reverseCheckCount += 4; // get str[i], get i, get s.charAt(i), compare
			if (str[i] != s.charAt(i)) {
				reverseCheckCount += 1; // return false
				return false; // False if any position doesn't match
			}
		} // end for
		reverseCheckCount += 3; // i=n, check i < .lenght, getting .lenght

		reverseCheckCount += 1; // return true
		return true;

	}// end reverseCheck

	/**
	 * 
	 * @param String
	 * @return boolean Returns true is the string is a palidrome
	 */
	public static boolean compareCheck(String s) {

		// Method data
		char[] str = s.toCharArray();
		compareCheckCount += 2; // Create char array and get s.toCharArray

		// Check if the strings match up in reverse and original
		// order
		for (int i = 0; i < str.length - 1; i++) {
			compareCheckCount += 4; // i = n, get str.length, -1, compare
			// Holds operation count for reverseCheck

			compareCheckCount += 7; // get str[i], get str.lenght, -1, get i, -i, get s.charAt(), compare
			if (str[i] != s.charAt((str.length - 1 - i))) {
				compareCheckCount += 1; // return false
				return false; // False if any position doesn't match
			}
		} // end for

		// Always assignment and check that ends the for loop
		compareCheckCount += 3; // i = n, get str.length, compare

		compareCheckCount += 1; // return true
		return true;

	}// end compareCheck

	public static boolean stackCheck(String s) {

		char[] str = s.toCharArray();
		ArrayQueue q = new ArrayQueue();
		ArrayStack stack = new ArrayStack();
		stackCheckCount += 4; // create char array, get s.toCharArray(), create arrayqueue, create arraystack

		for (char c : str) {
			stackCheckCount += 4; // assignment, get str[position], compare, get str.lenght
			stackCheckCount++; // push c to queue
			q.enqueue(c);
			stackCheckCount++; // push c to stack
			stack.push(c);
		} // end for
		stackCheckCount += 3; // get str[position], compare, get str.lenght

		for (int i = 0; i < str.length - 1; i++) {
			stackCheckCount += 4; // i = n, get str.length, -1, compare

			stackCheckCount += 5; // dequeue, pop stack, convert to char, convert to char, compare
			if (((char) q.dequeue()) != ((char) stack.pop())) {
				stackCheckCount += 1; // return false
				return false;
			}
		} // end for

		stackCheckCount += 4; // i = n, get str.length, -1, compare
		stackCheckCount += 1; // return true
		return true;

	}// end stackCheck

	public static int myAtoi(String str) {
		int res = 0; // Initialize result

		// Iterate through all characters of input string and
		// update result
		for (int i = 0; i < str.length(); i++)
			res = res * 10 + str.charAt(i) - '0';

		// return result.
		return res;
	}

	public static String decToBinary(int n) {
		// array to store binary number
		String binaryNum = "";

		// counter for binary array
		int v = 0;
		while (n > 0) {
			// storing remainder in binary array
			binaryNum += (n % 2);
			n = n / 2;
			v++;
		}

		char[] swapped = binaryNum.toCharArray();

		char temp = ' ';
		int upper = 0;// holds index of end chars to be swapped

		// Reverses string
		for (int i = 0; i < swapped.length / 2; i++) {

			// Get index of next swapping position from the
			// back
			upper = (swapped.length - 1 - i);

			// Switches around characters
			temp = swapped[i];
			swapped[i] = swapped[upper];
			swapped[upper] = temp;

		} // end for

		return new String(swapped);
	}

	/************************** Private Methods **************************/

}// end class Palidrome
