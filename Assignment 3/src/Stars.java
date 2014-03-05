import javax.swing.JOptionPane;


public class Stars
{
	private int rows, columns;
	public Stars(){
		setRows(Integer.parseInt(JOptionPane.showInputDialog("How many rows of stars is your knight looking at?")));
		setColumns(Integer.parseInt(JOptionPane.showInputDialog("How many columns of stars is your knight looking at?")));
	}
	
	public String display(){
		String message = "";
		for(int iR = 0; iR < getRows(); ++iR){
			for(int iC = 0; iC < getColumns(); ++iC)
				message += "* ";
			message += "\n";
			if(iR % 2 == 0)
				message += " ";
		}
		
		return message;
	}
	
	public void setRows(int rows){
		this.rows = rows;
	}
	public void setColumns(int columns){
		this.columns = columns;
	}
	public int getRows(){
		return rows;
	}
	public int getColumns(){
		return columns;
	}
}
