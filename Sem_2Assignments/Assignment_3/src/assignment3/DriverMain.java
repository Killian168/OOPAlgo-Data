package assignment3;

/**
 * A guessing game implementin gthe BinaryNodeInterface
 * and the BinaryTreeInterface
 * 
 * @author Killian O'Dálaigh
 * @version 29/03/2019
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

/**
 * @author Killian O'Dálaigh
 * @version 29 Mar 2019
 * Description: Main driving class of the program
 * 
 * Includes the entry point for the program
 */
public class DriverMain {

	// Member data
	BinaryTree<String> tree = new BinaryTree<String>();
	private static String workingDir = "_";

	/**
	 * Class constructor
	 */
	public DriverMain() {
		createTree();
		displayStats(tree);
		showMenu();
	}// end constructor

	
	/**
	 * @param args
	 * Entry point for the program
	 */
	public static void main(String[] args) {
		workingDir = System.getProperty("user.dir");
		System.out.println(workingDir);
		DriverMain d = new DriverMain();
	} // end of main

	
	/**
	 * Creates a default tree for the game to guess based on
	 * 
	 * Tree has 5 levels
	 * 17 nodes
	 * and the root node is Are you thinking of an Animal?
	 */
	public void createTree() 
	{

		System.out.println("\nCreating a default tree\n");

		// Creates the tree
		BinaryTree<String> pTree = new BinaryTree<String>("Is it an Eagle?");
		BinaryTree<String> qTree = new BinaryTree<String>("Is it a Penguin?");
		BinaryTree<String> hTree = new BinaryTree<String>("Is it a Bear");
		BinaryTree<String> iTree = new BinaryTree<String>("Is it covered in fur?");
		BinaryTree<String> jTree = new BinaryTree<String>("Can it fly?", pTree, qTree);
		BinaryTree<String> kTree = new BinaryTree<String>("Is it a reptile?");
		BinaryTree<String> lTree = new BinaryTree<String>("Is its manufacturer Ford");
		BinaryTree<String> mTree = new BinaryTree<String>("Is it a Toyota Supra");
		BinaryTree<String> nTree = new BinaryTree<String>("Is it situated in America?");
		BinaryTree<String> oTree = new BinaryTree<String>("Are you thinking of a Celebrity?");
		BinaryTree<String> dTree = new BinaryTree<String>("Does it have claws?", hTree, iTree);
		BinaryTree<String> eTree = new BinaryTree<String>("Is it a bird?", jTree, kTree);
		BinaryTree<String> fTree = new BinaryTree<String>("Is its manufacturer Toyota", lTree, mTree);
		BinaryTree<String> gTree = new BinaryTree<String>("Are you thining of a Landmark", nTree, oTree);
		BinaryTree<String> bTree = new BinaryTree<String>("Is it a Mammal?", dTree, eTree);
		BinaryTree<String> cTree = new BinaryTree<String>("Are you thining of a car", fTree, gTree);
		this.tree.setTree("Are you thinking of an animal?", bTree, cTree);
	} // end createTree

	/**
	 * Shows the menu to the user and does error checking on the input
	 */
	private void showMenu() 
	{

		// Method data
		boolean error = false;
		String ans = "_";

		// Menu string for the user
		String menu = " Enter 1 to play\n Enter 2 to save the tree\n "
				+ "Enter 3 to load a previously saved tree\n Enter q to quit the game";

		// Loop for the display
		do {
			error = false;
			ans = JOptionPane.showInputDialog(menu);
			switch (ans) {
			case "1":
				playGame();
				break;
			case "2":
				saveTree();
				break;
			case "3":
				loadTree();
				break;
			case "q":
				System.exit(0);
			default:
				error = true;
				JOptionPane.showMessageDialog(null, "Error in input please try again");
				break;
			}
		} while (error);

	}// end showMenu()

	/**
	 * Saves the tree to disk
	 */
	private void saveTree() 
	{
		// Method data
		boolean error = false;
		String treeName = "_";
		boolean write = true;

		// Loop for the save
		do {

			write = true;

			// Asks user to name the tree to save
			do {
				error = false;
				treeName = JOptionPane.showInputDialog("Please name this tree");
				if (treeName.contains(".")) {
					error = true;
					JOptionPane.showMessageDialog(null, "Can not contain .");
				} else {
					treeName += ".txt";
				}
			} while (error);

			File tmpFile = new File(treeName);
			boolean exists = tmpFile.exists();

			// If the name already exsists then the user is asked to overwrite to file or not
			if (exists) {
				String ans = "_";
				ans = JOptionPane
						.showInputDialog("A file exists with this name do you want to overwrite it? enter y/n");
				switch (ans) {
				case "n":
					write = false;
				}
			}

			// Write the file to disk
			if (write) {
				try {

					FileOutputStream f = new FileOutputStream(treeName);
					ObjectOutputStream o = new ObjectOutputStream(f);

					// Write objects to file
					o.writeObject(this.tree);

					o.close();
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} while (!write);

	}// end saveTree()

	
	/**
	 * Loads a tree from disk into the current game
	 */
	private void loadTree() 
	{
		// Member data
		File directoryPath = new File(workingDir);
		String fileNames = "_";
		String ans = "_";
		boolean error = false;
		boolean loaded = false;
		
		// Retrieves all the names of trees in the disk directory
		File[] files = directoryPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});

		// Concatenates the names of the trees for displaying to the user
		for (File file : files) {
			fileNames += file.getName() + "\n";
		}

		// Loop for loading the tree
		do {
			loaded = false;
			ans = JOptionPane.showInputDialog("Enter the tree you wish to choose:\n" + fileNames);

			if (!ans.contains(".txt")) {
				ans += ".txt";
			}
			
			// Loop through the files until the right file matching the user input is found
			for (File file : files) {
				if (file.getName().equals(ans)) {
					try {
						FileInputStream Fi = new FileInputStream(ans);
						ObjectInputStream Oi = new ObjectInputStream(Fi);
						this.tree = (BinaryTree<String>) Oi.readObject();

						Oi.close();
						Fi.close();
						displayStats(this.tree);
						loaded = true;
					} catch (FileNotFoundException e) {
						System.out.println("File not found");
					} catch (IOException e) {
						System.out.println("Error initializing stream");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			
			if(!loaded) {
				ans = JOptionPane.showInputDialog("There was an error and your file could not be found"
						+ "Please enter anything to try again");
			}

		} while (!loaded);

	}// end loadTree()

	/**
	 * @param currentNode
	 * 
	 * Asks the user to improve the current tree after guessing wrong
	 */
	private void incorrectAnswer(BinaryNodeInterface<String> currentNode) 
	{

		// Member data
		String ans = "_";
		String question = "_";

		ans = JOptionPane.showInputDialog("I dont know! What is the correct answer?");

		question = JOptionPane.showInputDialog("Please give a question that would diffrenciate between"
				+ "the correct answer and the one I gave, where the correct answer would be given by a yes"
				+ "answer to the question");

		// Inserts the new question and answer
		BinaryNode<String> newNode = new BinaryNode<String>(currentNode.getData());
		currentNode.setData(question);
		currentNode.setLeftChild(new BinaryNode<String>(ans));
		currentNode.setRightChild(newNode);

		JOptionPane.showMessageDialog(null, "Thank you for improving this tree!!");

		showMenu();

	}// end incorrectAnswer()

	/**
	 * The game logic for the user to play the game
	 */
	private void playGame() 
	{

		// Variable to store user answer here
		String ans = "";
		while (true) {
			BinaryNodeInterface<String> currentNode = tree.getRootNode();
			while (!currentNode.isLeaf()) {
				// Ask the question and update current node
				ans = JOptionPane.showInputDialog(currentNode.getData());
				switch (ans) {
				case "yes":
				case "YES":
				case "Yes":
				case "YEs":
				case "YeS":
				case "yEs":
				case "yES":
				case "yeS":
				case "y":
					currentNode = currentNode.getLeftChild();
					break;
				case "No":
				case "no":
				case "nO":
				case "n":
					currentNode = currentNode.getRightChild();
					break;
				default:
					JOptionPane.showMessageDialog(null, "Error in input please try again");
					break;
				}

			}
			// At the leaf: got an answer that is either right or wrong
			// Present guess to user, and store their answer
			boolean error = false;
			do {
				error = false;
				ans = JOptionPane.showInputDialog(currentNode.getData());
				switch (ans) {
				case "yes":
				case "YES":
				case "Yes":
				case "YEs":
				case "YeS":
				case "yEs":
				case "yES":
				case "yeS":
				case "y":
					// 1. Answer is correct. Display options for user to continue
					showMenu();
					break;
				case "No":
				case "no":
				case "nO":
				case "n":
					incorrectAnswer(currentNode);
					break;
				// 2. The answer was wrong. We need to expand the tree.
				// Get new question from user
				// Replace the current node with this question, and add //left & right children
				// with the answers
				default:
					JOptionPane.showMessageDialog(null, "Error in input please try again");
					break;
				}
			} while (error);
		}

	}// end playGame()

	/**
	 * @param tree
	 * 
	 * Displays the stats of a tree in the console
	 */
	public static void displayStats(BinaryTree<String> tree) {
		if (tree.isEmpty())
			System.out.println("The tree is empty");
		else
			System.out.println("The tree is not empty");

		System.out.println("Root of tree is " + tree.getRootData());
		System.out.println("Height of tree is " + tree.getHeight());
		System.out.println("No. of nodes in tree is " + tree.getNumberOfNodes());
	} // end displayStats()

}// end class DriverMain
