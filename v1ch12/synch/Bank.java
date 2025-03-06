package synch;

import java.util.concurrent.locks.Condition;

/**
 * A bank with a number of bank accounts that uses condition object for serializing access.
 */
public class Bank extends lock.Bank {
    protected Condition sufficientFunds = bankLock.newCondition();

    public Bank(int n, double initialBalance) {
        super(n, initialBalance);
    }

    @Override
    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amount)
                sufficientFunds.await();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
            sufficientFunds.signalAll();
        }
        finally {
            bankLock.unlock();
        }
    }
}
