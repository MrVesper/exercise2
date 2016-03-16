package ThreadAndRunnable;

import wdsr.exercise2.startthread.FibonacciCallback;
import wdsr.exercise2.startthread.NumericHelper;

public class FibonacciRunnable implements Runnable {
	int n;
	FibonacciCallback callback;
	private NumericHelper helper;
	
	public FibonacciRunnable(int n, FibonacciCallback callback,NumericHelper helper){
		this.n = n;
		this.callback = callback;
		this.helper = helper;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			long value = helper.findFibonacciValue(n);
			callback.fibonacciComputed(value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
