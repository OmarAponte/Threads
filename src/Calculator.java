import java.util.concurrent.Semaphore;

public class Calculator extends Thread {

	double number1;
	double number2;
	double sum;
	static int threadsProcessed;
	int threadid;
	static Semaphore semaphore = new Semaphore(1);

	@Override
	public void run() {
		
			//sum = (((number1*number2)*2)/0.78)-1000*89/0.000004+sum;
			sum = number1+number2;
			//sum = Math.sqrt(sum);
			Main.result(sum, threadid);
		//System.out.println("Thread ID " + threadid+" Partial:" + sum);
		
	/*	try {
			System.out.println("Thread " + threadid);
			Thread.sleep(2000);
			semaphore.acquire();
			sum += number1+number2;
			semaphore.release();
			System.out.println("threadid: "+ threadid + " number1: "+number1 +"  number2: "+ number2+" sum  "+sum);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	public double getSum(){
		return sum;
	}

	public int getIDP(){
		return threadid;
	}
}
