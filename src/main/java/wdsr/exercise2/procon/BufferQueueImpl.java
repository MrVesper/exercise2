package wdsr.exercise2.procon;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Task: implement Exchange interface using one of *Queue classes from java.util.concurrent package.
 */
public class BufferQueueImpl implements Buffer {
	public static BlockingQueue <Order> queue = new ArrayBlockingQueue<Order>(200000);
	
	public void submitOrder(Order order) throws InterruptedException {
		queue.put(order);
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		// TODO
		return queue.take();
	}
}
