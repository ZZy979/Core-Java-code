package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A bank using lock to protect transfer method.
 */
public class Bank extends threads.Bank {
    protected Lock bankLock = new ReentrantLock();

    public Bank(int n, double initialBalance) {
        super(n, initialBalance);
    }

    @Override
    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            super.transfer(from, to, amount);
        }
        finally {
            bankLock.unlock();
        }
    }

    @Override
    public double getTotalBalance() {
        bankLock.lock();
        try {
            return super.getTotalBalance();
        }
        finally {
            bankLock.unlock();
        }
    }
}
