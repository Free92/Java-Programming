import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
// Basic concepts taken from http://forum.codecall.net/topic/36472-javatutorial-tic-tac-toe/
// Mostly designed by myself, but I would refer to that for a few ideas.
// AI completely designed by my own logic

public class TicTacToe implements ActionListener{
	
	private JButton buttons[] = new JButton[9];
	private int turnCount = 0;
	// Creating a 2D int array with all of the possible winning conditions
	private int[][] winCombo = new int[][]{
			{0,1,2},{3,4,5},{6,7,8},
			{0,3,6},{1,4,7},{2,5,8},
			{0,4,8},{2,4,6}
	};
	private boolean win = false;
	private boolean ai = false;
	
	public TicTacToe(){
		JFrame window = new JFrame();
		setUpWindow(window);
		// Add's the action listeners to the buttons
		for(int i = 0; i < 9; ++i)
			buttons[i].addActionListener(this);
		int dialogueButton = JOptionPane.YES_NO_OPTION;
		int dialogueResult = JOptionPane.showConfirmDialog(null, "Would you like to play against a computer?",null, dialogueButton);
		if(dialogueResult == JOptionPane.YES_OPTION)
			ai = true;
	}
	
	private void setUpWindow(JFrame window){
		
		// Creates a panel to be used within the window that holds our 3x3 tic tac toe grid
		JPanel grid = new JPanel();
		// Header and Footer
		JLabel title = new JLabel("FSU");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel footer = new JLabel("By: Jesse Woodall");
		footer.setHorizontalAlignment(SwingConstants.CENTER);	
		// Set's up the rest of the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Establishes the grid
		grid.setLayout(new GridLayout(3,3));
		for(int i = 0; i < 9; ++i){
			buttons[i] = new JButton();
			grid.add(buttons[i]);
		}
		// Add's all of the elements to the window and makes it visible
		window.add(title, BorderLayout.PAGE_START);
		window.add(grid, BorderLayout.CENTER);
		window.add(footer, BorderLayout.PAGE_END);
		window.setSize(300,300);
		window.setVisible(true);
	}
	
	public static void main(String[] args){
		new TicTacToe();
	}

	public void actionPerformed(ActionEvent arg0) {
		String letter = "";
		if(turnCount % 2 == 0)
			letter = "X";
		else	
			letter = "O";
		// Gets which button is pressed and assigns the letter and then disables it
		JButton pressedButton = (JButton) arg0.getSource();
		pressedButton.setText(letter);
		pressedButton.setEnabled(false);
		// Checks to see if any of our win conditions are met
		// Win condition met if all elements in set are equal
		// i.e. [i][0]==[i][1]==[i][2] 
		// Also checks to make sure they aren't equal to the null value
		//  originally assigned
		for(int i = 0; i < 8; ++i){
			if(buttons[(winCombo[i][0])].getText() == buttons[(winCombo[i][1])].getText() &&
			   buttons[(winCombo[i][1])].getText() == buttons[(winCombo[i][2])].getText() &&
			   buttons[(winCombo[i][0])].getText() != ""){
				win = true;
			}
		}
		++turnCount;
		// Checks to see if win
		JFrame window = (JFrame) SwingUtilities.windowForComponent(pressedButton);
		if(win){
			// Exits out of JFrame
			playAgain(window, letter + " wins!");
		} else if(!win && turnCount == 9){
			playAgain(window, "Tie Game!");
		}
		// Checks to see if single player or not
		// If ai is enabled, it's the computer's turn, and the game has not already been won
		//  the ai will choose a button
		if(ai && (turnCount % 2) == 1 && !win){
			boolean clicked = false;
			// Checks to see if X is about to win and blocks it
			// Also if O is about to win and it is not blocked it will choose that button
			for(int index = 0; index < 8; ++index){
				if(buttons[(winCombo[index][0])].getText() == buttons[(winCombo[index][1])].getText() && buttons[(winCombo[index][0])].getText() != ""){
					if(buttons[(winCombo[index][2])].isEnabled()){
						buttons[(winCombo[index][2])].doClick();
						clicked = true;
					}
					break;
				} else if(buttons[(winCombo[index][1])].getText() == buttons[(winCombo[index][2])].getText() && buttons[(winCombo[index][1])].getText() != ""){
					if(buttons[(winCombo[index][0])].isEnabled()){
						buttons[(winCombo[index][0])].doClick();
						clicked = true;
					}
					break;
				} else if(buttons[(winCombo[index][0])].getText() == buttons[(winCombo[index][2])].getText() && buttons[(winCombo[index][0])].getText() != ""){
					if(buttons[(winCombo[index][1])].isEnabled()){
						buttons[(winCombo[index][1])].doClick();
						clicked = true;
					}
					break;
				} 
			}
			// If neither player is about to win, the computer picks a random button to pick.
			if(!clicked){
				// Chooses a random button
				int max = 8;
				int seed = (int)(Math.random() * max);
				for(int i = 0; i < 9; ++i){
					if(buttons[seed+i].isEnabled()){
						buttons[seed+i].doClick();
						clicked = true;
						break;
					}	
				}
			}
		}
	}
	
	public void playAgain(JFrame window, String message){
		int dialogueButton = JOptionPane.YES_NO_OPTION;
		int dialogueResult = JOptionPane.showConfirmDialog(null, message + "\nWould you like to play again?",null, dialogueButton);
		if(dialogueResult == JOptionPane.YES_OPTION){
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(false);
			window.dispose();
			new TicTacToe();
		} else {
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(false);
			window.dispose();
		}
	}
}
