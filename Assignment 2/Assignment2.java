import java.util.Scanner;

public class Assignment2{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String name;
		int health,
			battles,
			age;	
		float gold;
		
		System.out.printf("Enter in knight's name: ");
		name = input.nextLine();
		System.out.printf("How old is your knight?: ");
		age = input.nextInt();
		System.out.printf("How much health does he have?: ");
		health = input.nextInt();
		System.out.printf("How many battles has your knight been in?: ");
		battles = input.nextInt();
		System.out.printf("And how much gold has he accumulated through his plunders?: ");
		gold = input.nextFloat();
		
		System.out.printf("%s is %d years old and has %d health left.\n", name, age, health);
		System.out.printf("%s has been in %d battles and has made %.2f gold!\n", name, battles, gold);
		System.out.printf("That's an average of %.2f gold per battle!", (gold / battles));
		
	}
}