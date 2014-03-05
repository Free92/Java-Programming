import javax.swing.JOptionPane;


public class Knight{
	private String name;
	private int health, battles, age;
	private float gold;
	
	public Knight(){
		setName(JOptionPane.showInputDialog("What is your knight's name?"));
		setAge(Integer.parseInt(JOptionPane.showInputDialog("How old is your knight?")));
		setHealth(Integer.parseInt(JOptionPane.showInputDialog("How much health does he have?")));
		setBattles(Integer.parseInt(JOptionPane.showInputDialog("How many battles has your knight been in?")));
		setGold(Float.parseFloat(JOptionPane.showInputDialog("And how much gold has he accumulated through his plunders?")));
	};
	
	public String display(){
		String message = String.format("Knight Name: %s\n" +
				"Knight Health: %s\n" +
				"Knight Battles: %s\n" +
				"Knight Age: %s\n" +
				"Knight Gold: $%s\n",
				getName(), getHealth(), getBattles(), getAge(), getGold());
		return message;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public void setBattles(int battles){
		this.battles = battles;
	}
	public void setAge(int age){
		this.age = age;
	}
	public void setGold(float gold){
		this.gold = gold;
	}
	
	public String getName(){
		return name;
	}
	public int getHealth(){
		return health;
	}
	public int getBattles(){
		return battles;
	}
	public int getAge(){
		return age;
	}
	public float getGold(){
		return gold;
	}
}

