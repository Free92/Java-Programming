import javax.swing.JOptionPane;



public class Knight{
	// Final values used throughout the class
	final static int MAX_HEALTH = 100;
	final static int BASE_HEALTH = 90;
	final static int BONUS_HEALTH = MAX_HEALTH - BASE_HEALTH;
	
	private String name;
	private int health;
	private int weapon;
	private int armor;
	private String weaponName[] = {"Long Sword","Battle Axe", "Spear", "Warhammer"};
	private String armorName[] = {"Plate", "Mail", "Leather"};
	
	public Knight(String name, int weapon, int armor){
		setName(name);
		setWeapon(weapon);
		setHealth((int)(BASE_HEALTH + (Math.random() * BONUS_HEALTH)));
		setArmor(armor);
	};
	public Knight(){
		setName("Enemy Knight");
		setWeapon((int)(Math.random() * 3));
		setHealth((int)(BASE_HEALTH + (Math.random() * BONUS_HEALTH)));
		setArmor((int)(Math.random() * 2));
	}
	
	public String toString(){
		String message = String.format("Knight Name: %s\n" +
				"Knight Health: %s\n" +
				"Knight Weapon: %s\n" +
				"Knight Armor: %s\n",
				getName(), getHealth(), weaponName[getWeapon()], armorName[getArmor()]);
		return message;
	}
	public boolean isAlive(){
		return (health > 0);
	}
	public void setName(String name){
		this.name = name;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public void setWeapon(int weapon){
		this.weapon = weapon;
	}
	public void setArmor(int armor){
		this.armor = armor;
	}
	public String getName(){
		return name;
	}
	public int getHealth(){
		return health;
	}
	public int getWeapon(){
		return weapon;
	}
	public int getArmor(){
		return armor;
	}
}

