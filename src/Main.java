import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;


@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener{
	public static JButton boton;
	public static JTextArea textArea1;
	public static JTextArea textArea2;
	public static long time_start, time_end;
	public static int times=0, length;
	public static double total=0;
	public static label lbl3;
	private static double [] array1, array2 , array3;
	public static String mode;
	public static JSpinner spinner1, spinner2;
	private static Calculator[] array4;
	private static double sum;
	public static void main(String[] args) {
		
		new Main();
	}
	public Main(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setTitle("Thread");
		label lbl1 =new label("Array length:",20, 20, 100, 20);
		label lbl2 =new label("Parallel Mode:",20, 50, 100, 20);
		lbl3 =new label("Results:",20, 100, 360, 100);
		Icon icon1 = new ImageIcon("/Users/copelabs/Documents/workspace/Threads/src/image.jpg");
		JLabel lbl4 = new JLabel(); 
		lbl4.setIcon(icon1);
		lbl4.setBounds(280, 20, 100, 100);

		String threads[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","200"};
		String modes[]={"Parallel", "Secuential"};
		
		SpinnerModel model1 = new SpinnerListModel(modes);
		spinner1 = new JSpinner(model1);
		spinner1.setBounds(120, 50, 100, 20);
		add(spinner1);
		
		SpinnerModel model2 = new SpinnerListModel(threads);
		spinner2 = new JSpinner(model2);
		spinner2.setBounds(120, 20, 40, 20);
		spinner2.setAutoscrolls(true);
		add(spinner2);
		
		add(lbl4);
		add(lbl1);
		add(lbl2);
		add(lbl3);
		
		boton = new JButton("Calculate");
		boton.addActionListener(this);
		boton.setBounds(135,200,130,50);
		add(boton);
		this.setVisible(true); 

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource()==boton) {
			
			String str = (String)spinner2.getValue();
			length=Integer.parseInt(str);
			mode = (String)spinner1.getValue();
			if (mode=="Parallel"){
				Calculate(length);
			}else{
			serial(length);
			}
		}
	}
	
	public static void fillArray(double []array){

		for(int i=0; i<array.length; i++){
			array[i]=i+1;
		}
	}
	
	public static void Calculate(int length){


		array1 =new double[length];
		array2 =new double[length];
		array3 =new double[length];

		fillArray(array1);
		fillArray(array2);

		time_start = System.currentTimeMillis();
		System.out.println(time_start);
		array4 = new Calculator[length];
		for(int i=0; i<length; i++){
			
			array4 [i]=new Calculator();
			array4 [i].number1=array1[i];
			array4 [i].number2=array1[i];
			array4 [i].threadid=i;
			array4 [i].start();
			
		}
		
		for (int i=0; i<length; i++){
			try {
				
				array4[i].join();
				sum = array4[i].getSum()+sum;
				array3[array4[i].getIDP()]= array4[i].getSum();	
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		time_end = System.currentTimeMillis();
		for(int i=0; i<length; i++){
			System.out.println(array1[i] + "," + array2[i] + ","+ array3[i]);
		}
		lbl3.setText("<html><body>"+"Array length: "+length+"<br>The task has taken"+ ( time_end - time_start )+" milliseconds <br> Total:"+sum+"  </body></html>");
		System.out.println("Total"+sum);
		System.out.println("The task has taken "+ ( time_end - time_start ) +" milliseconds "+ " in Parallel Mode: "+mode);
		sum=0;
	}
	
	public void serial(int length){
		array1 =new double[length];
		array2 =new double[length];
		array3 =new double[length];

		fillArray(array1);
		fillArray(array2);
		time_start = System.currentTimeMillis();
		System.out.println(time_start);
		double sum=0;
		for(int i=0; i<length; i++){
		
			sum=sum+array1[i]+array2[i];
			sum=Math.sqrt(sum);
			array3[i]=sum;
		}
		
		time_end = System.currentTimeMillis();
		System.out.println(time_end);
		System.out.println(sum + "time: "+ ( time_end - time_start ));
	}
	public synchronized static void result(double partial, int threadid){
		total=partial+total;
		System.out.println("Synchronized:"+total +"/"+threadid);
	}
	
}