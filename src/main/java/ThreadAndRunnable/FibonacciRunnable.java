package ThreadAndRunnable;

import wdsr.exercise2.startthread.FibonacciCallback;
import wdsr.exercise2.startthread.NumericHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FibonacciRunnable implements Runnable {
	int n;
	FibonacciCallback callback;
	private NumericHelper helper;
	private static final Logger log = LogManager.getLogger();
	
	public FibonacciRunnable(int n, FibonacciCallback callback,NumericHelper helper){
		this.n = n;
		this.callback = callback;
		this.helper = helper;
	}
	
	@Override
	public void run() {
		try {
			long value = helper.findFibonacciValue(n);
			callback.fibonacciComputed(value);
		} catch (Exception e) {
			log.error("FibonacciRunnable method Run() -> Throwed exception: "+e+"with mssg: \n"+e.getMessage());
		}
	}

}
