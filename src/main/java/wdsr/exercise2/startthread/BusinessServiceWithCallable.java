package wdsr.exercise2.startthread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BusinessServiceWithCallable {
	private final ExecutorService executorService;	
	private final NumericHelper helper;
	private static final Logger log = LogManager.getLogger();
	
	public BusinessServiceWithCallable(ExecutorService executorService, NumericHelper helper) {
		this.executorService = executorService;
		this.helper = helper;
	}
	/**
	 * Calculates a sum of 100 random numbers.
	 * Random numbers are returned by helper.nextRandom method.
	 * Each random number is calculated asynchronously.
	 * @return sum of 100 random numbers.
	 */
	public long sumOfRandomInts() throws InterruptedException, ExecutionException {	
		long result = 0;
		List<Callable<Integer>> callables = new ArrayList<Callable<Integer>>();
		
		for(int i=0; i<100;i++){
			callables.add(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return helper.nextRandom();
				}
			});
		}
		List<Future<Integer>> futures = executorService.invokeAll(callables);
		
		try {
			for(Future<Integer> future : futures){
				result+=future.get();
			}
		} catch (InterruptedException e) {
			log.error("BusinessServiceWithCallable method sumOfRandomInts() Throwed exception: "
					+e+"with mssg:\n"+e.getMessage());
		} catch (ExecutionException e) {
			log.error("BusinessServiceWithCallable method sumOfRandomInts() Throwed exception: "
					+e+"with mssg:\n"+e.getMessage());
		}
		executorService.shutdown();
		return result;
	}
}
