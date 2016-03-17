package wdsr.exercise2.procon;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Task: implement Exchange interface without using any *Queue classes from java.util.concurrent package.
 * Any combination of "synchronized", *Lock, *Semaphore, *Condition, *Barrier, *Latch is allowed.
 */
public class BufferManualImpl implements Buffer {

	Lock lock = new ReentrantLock();
	Condition notFull = lock.newCondition();///jezeli lista jest pelna
	Condition notEmpty = lock.newCondition();///jezeli lista pusta
	
	int maxAmountOfOrders = 10000;
	List<Order> orderList = new ArrayList<Order>();

	public void submitOrder(Order order) throws InterruptedException {	
		lock.lock();
		try {
			while(orderList.size() == maxAmountOfOrders)
			{
				notFull.await();///producer czeka az sie zwolni miejsce na nowe zamowienia
			}
			orderList.add(order);///dodajemy zamowienie bo lista nie jest pelna
								///oraz notFull otrzymal signal
			notEmpty.signal();///Lista nie jest pusta wiec konsument moze odebrac zamowienie
		} finally {
			lock.unlock();
		}
	}
	
	public Order consumeNextOrder() throws InterruptedException {
		lock.lock();
		try {
			while(orderList.size()==0)notEmpty.await();///Konsument czeka z odbiorem zamowienia bo lista zamowien jest pusta
			if(orderList.size() < maxAmountOfOrders)notFull.signal();///Lista zamowien nie jest pusta wiec 
			///konsument moze odebrac zamowienia a producent przyjac kolejne
			return orderList.remove(0);
		} finally {
			lock.unlock();
		}
	}
}
