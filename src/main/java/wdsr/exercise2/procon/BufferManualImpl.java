package wdsr.exercise2.procon;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task: implement Exchange interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {

	Object lock1 = new Object();

	int maxAmountOfOrders = 10000;
	List<Order> orderList = new ArrayList<Order>();

	public void submitOrder(Order order) throws InterruptedException {	
		synchronized (lock1) {
			orderList.add(order);
		}
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		synchronized (lock1) {
			return orderList.remove(0);
		}
	}
}
