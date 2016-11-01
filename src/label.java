import javax.swing.JLabel;

public class label extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public label(String text, int x, int y, int width, int height){
		this.setText(text);
		this.setBounds(x, y, width, height);
	}	
	
}
