import javax.swing.JOptionPane;

public class KnightDriver{
	public static void main(String[] args){
		Knight knight = new Knight();
		Stars stars = new Stars();
		String message = String.format("%s%s", knight.display(), stars.display());
		JOptionPane.showMessageDialog(null, message);
	}
	
}
