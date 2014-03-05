import javax.swing.JOptionPane;

public class KnightFight {
	// Final values used throughout the class
	// Declared here, so if we want to make any changes to balance, we can do in just one place.
	final static String GAME_NAME = "KnightFight";
	final static String WELCOME = "Welcome to " + GAME_NAME+ "!\n";
	final static double LEATHER_MULTIPLIER = 1.5;
	final static double MAIL_MULTIPLIER = 1.25;
	final static double PLATE_MULTIPLIER = 1;
	final static double LEATHER_REDUCER = 0;
	final static double MAIL_REDUCER = .25;
	final static double PLATE_REDUCER = .5;
	final static double LONG_SWORD_MULTIPLIER = 1.5;
	final static double BATTLE_AXE_MULTIPLIER = 1;
	final static double SPEAR_MULTIPLIER = 1;
	final static double WARHAMMER_MULTIPLIER = 3;
	final static int BERSERKER_MAX = 3;
	final static double SPEAR_REDUCER = .1;
	final static int WARHAMMER_MISS = 2;
	final static int BASE_DAMAGE = 15;
	
	public static void main(String[] args){
		// Creates your Knight
		Knight character[] = new Knight[2];
		character[0] = createKnight(WELCOME, "your");
		// Prompts the user whether they would like to auto generate their knight or create it themselves
		int dialogueButton = JOptionPane.YES_NO_OPTION;
		int dialogueResult = JOptionPane.showConfirmDialog(null, "Would you like to auto generate your opponent?", GAME_NAME, dialogueButton);
		if(dialogueResult == JOptionPane.YES_OPTION)
			character[1] = new Knight();
		else
			character[1] = createKnight("", "your opponent's");
		// Displays a brief summary of each Knight
		JOptionPane.showMessageDialog(null, character[0].toString() + "\n\n" + character[1].toString(), GAME_NAME, JOptionPane.INFORMATION_MESSAGE);
		// Asks the user whether if they are ready.  If no exits program
		dialogueResult = JOptionPane.showConfirmDialog(null, "Are you ready for battle?", GAME_NAME, JOptionPane.YES_NO_OPTION);
		if(dialogueResult == JOptionPane.YES_OPTION){
				battle(character);
				dialogueResult = JOptionPane.showConfirmDialog(null,  "Do you want to play again?", GAME_NAME, JOptionPane.YES_NO_OPTION);
				if(dialogueResult == JOptionPane.YES_OPTION)
					main(null);
		}
	}
	private static Knight createKnight(String message, String usr){
		String name = JOptionPane.showInputDialog(null, message+"Enter the name of " + usr + " Knight: "	, GAME_NAME, 1);
		int weapon = Integer.parseInt(JOptionPane.showInputDialog(null,"Now select " + usr + " weapon! (Choose number)"
				+ "\n1) Long Sword"
				+ "\n2) Battle Axe"
				+ "\n3) Spear"
				+ "\n4) Warhammer"
				+ "\nYour choice my liege? :", GAME_NAME, 1)) - 1;
		int armor = Integer.parseInt(JOptionPane.showInputDialog(null, "Now select " + usr + " weapon! (Choose Number)"
				+ "\n1) Plate"
				+ "\n2) Mail"
				+ "\n3) Leather"
				+ "\nYour choice my liege? :", GAME_NAME, 1)) - 1;
		Knight character = new Knight(name, weapon, armor);
		return character;
	}
	
	private static void battle(Knight character[]){
		while(character[0].isAlive() && character[1].isAlive()){
			int random[][] = { {0,1}, {1,0} };
			int i = (int)(Math.random() * 100) % 2;
			System.out.println(character[random[i][0]].getName());
			System.out.println(character[random[i][1]].getName());
			// TODO: Random Pick Fighter
			character[random[i][0]].setHealth(character[random[i][0]].getHealth() - damageCalc(character[random[i][1]], character[random[i][0]]));
			if(!character[random[i][0]].isAlive()){
				JOptionPane.showMessageDialog(null, character[random[i][0]].getName() +" is defeated!\n" + character[random[i][1]].getName() + " is victorious!", GAME_NAME, 1);
				break;
			}
			character[random[i][1]].setHealth(character[random[i][1]].getHealth() - damageCalc(character[random[i][0]], character[random[i][1]]));
			if(!character[random[i][1]].isAlive()){
				JOptionPane.showMessageDialog(null, character[random[i][1]].getName() +" is defeated!\n" + character[random[i][0]].getName() + " is victorious!", GAME_NAME, 1);
				break;
			}
			JOptionPane.showMessageDialog(null, "At the end of the round the results are: \n" + character[0].toString() + "\n\n" + character[1].toString(), GAME_NAME, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private static int damageCalc(Knight character1, Knight character2){
		int damage = 0;
		int berserk = 0;
		switch(character1.getWeapon()){
			// Long Sword does LONG_SWORD_MULTIPLIER x BASE_DAMAGE
			case 0:
				damage += (int)((Math.random() * BASE_DAMAGE) * LONG_SWORD_MULTIPLIER);
				break;
			// Battle axe does BATTLE_AXE_MULTIPLIER x BASE_DAMAGE, but can attack 1-BERSERKER_MAX times per round
			case 1:
				berserk = (int)(Math.random() * BERSERKER_MAX);
				for(int i = 0; i < berserk; ++i)
					damage += (int)((Math.random()* BASE_DAMAGE) * BATTLE_AXE_MULTIPLIER);
				break;
			// Spear does SPEAR_MULTIPLIER x BASE_DAMAGE, but grants damage reduction by SPEAR_REDUCER
			case 2:
				damage += (int)((Math.random() * BASE_DAMAGE) * SPEAR_MULTIPLIER);
				break;
			// Warhammer does WARHAMMER_MULTIPLIER x BASE_DAMAGE, but has an added miss rate of 1 in (WARHAMMER_MISS + 1)
			case 3:
				if((int)(Math.random() * WARHAMMER_MISS) == 1)
					damage += (int)((Math.random() * BASE_DAMAGE) * WARHAMMER_MULTIPLIER);
				else
					damage = 0;
				break;
			default:
				damage = 0;
		}
		if(damage == 0){
			JOptionPane.showMessageDialog(null, character1.getName() + " misses with his attack!", GAME_NAME, 0);
		} else{
			switch(character1.getArmor()){
			// Plate deals PLATE_MULTIPLIER x extra damage
			case 0:
				damage = (int)(damage * PLATE_MULTIPLIER);
				break;
			// Mail deals MAIL_MULTIPLIER x extra damage
			case 1:
				damage = (int)(damage * MAIL_MULTIPLIER);
				break;
			// Leather deals LEATHER_MULTIPLIER x extra damage
			case 2:
				damage = (int)(damage * LEATHER_MULTIPLIER);
				break;
			}
			double reduction1 = 0;
			double reduction2 = 0;
			String message = "";
			switch(character2.getArmor()){
			// Plate takes PLATE_REDUCER x damage
			case 0:
				reduction1 = PLATE_REDUCER;
				message = "\nBut because of " + character2.getName() + "'s heavy armor, they take " + (int)(reduction1 * damage) + " less damage!";  
				break;
			// Mail take MAIL_REDUCER x damage
			case 1:
				reduction1 = MAIL_REDUCER;
				message = "\nBut because of " + character2.getName() + "'s medium armor, they take " + (int)(reduction1 * damage) + " less damage!";
				break;
			// Leather take LEATHER_REDUCER x damage
			case 2:
				reduction2 = LEATHER_REDUCER;
				message = "\nBecause of " + character2.getName() + "'s light armor, they take the full damage!";
				break;
			}
			if(character2.getWeapon() == 2){
				reduction2 += SPEAR_REDUCER;
				message += "\nAnd because of " + character2.getName() + "'s distance with their spear, they take " + (int)(reduction2 * damage) + " less damage!";
			}
			int tempDamage = damage;
			tempDamage -= (int)(damage * reduction1);
			tempDamage -= (int)(damage * reduction2);
			message += "\nTotal damage inflicted: " + tempDamage;
			// If not a battle axe or battle axe user did not go BEZERK!
			if(character1.getWeapon() != 1 || berserk == 1){
				JOptionPane.showMessageDialog(null, character1.getName() + " attacks for " + damage + " damage!" + message, GAME_NAME, 0);
			} else {
				JOptionPane.showMessageDialog(null, character1.getName() + " goes BEZERK with their battle axe and attacks " + berserk + " times for " + damage + " damage!" + message, GAME_NAME, 0);
			}
			damage = tempDamage;
		}
		return damage;
	}
}
