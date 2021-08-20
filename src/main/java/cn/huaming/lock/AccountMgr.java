package cn.huaming.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private Lock lock = new ReentrantLock();
    private volatile String id;
    private volatile double money;

    public Account(String id, double initialMoney) {
        this.id = id;
        this.money = initialMoney;
    }

    public void add(double money) {
        lock.lock();
        try {
            this.money += money;
        } finally {
            lock.unlock();
        }
    }

    public void reduce(double money) {
        lock.lock();
        try {
            this.money -= money;
        } finally {
            lock.unlock();
        }
    }

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    void lock() {
        lock.lock();
    }

    void unlock() {
        lock.unlock();
    }

    boolean tryLock() {
        return lock.tryLock();
    }
}

public class AccountMgr {

    public static class NoEnoughMoneyException extends Exception {
    }

    public static void transfer(Account from, Account to, double money)
            throws NoEnoughMoneyException {

        from.lock();
        System.out.println("from:"+Thread.currentThread().getName()+"-->"+from.getId()+"被锁了");
//       T1 from:Thread-0-->A1.lock
//       T2 from:Thread-1-->A2.lock
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("before to:"+Thread.currentThread().getName()+"-->"+to.getId()+"准备获取锁");
            to.lock();
            System.out.println("after to:"+Thread.currentThread().getName()+"-->"+to.getId()+"被锁了");
            try {
                if (from.getMoney() >= money) {
                    from.reduce(money);
                    to.add(money);
                } else {
                    throw new NoEnoughMoneyException();
                }
            } finally {
                System.out.println("to:"+Thread.currentThread().getName()+"-->"+to.getId()+"准备解锁");
                to.unlock();
                System.out.println("to:"+Thread.currentThread().getName()+"-->"+to.getId()+"解锁");
            }
        } finally {
            System.out.println("from:"+Thread.currentThread().getName()+"-->"+from.getId()+"准备解锁");
            from.unlock();
            System.out.println("to:"+Thread.currentThread().getName()+"-->"+from.getId()+"解锁");
        }
    }

    public static void main(String[] args) {
//        simulateDeadLock();
        Account a1 = new Account("1",100);
        Account a2 = new Account("2",200);
        Account a3 = new Account("3",300);
        new Thread(()->{
            try {
                transfer(a1,a2,50);
            } catch (NoEnoughMoneyException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                transfer(a2,a1,50);
            } catch (NoEnoughMoneyException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void simulateDeadLock() {
        final int accountNum = 10;
        final Account[] accounts = new Account[accountNum];
        final Random rnd = new Random();
        for (int i = 0; i < accountNum; i++) {
            accounts[i] = new Account(String.valueOf(i),rnd.nextInt(10000));
        }
        int threadNum = 100;
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread(() -> {
                int loopNum = 100;
                for (int k = 0; k < loopNum; k++) {
                    int i1 = rnd.nextInt(accountNum);
                    int j = rnd.nextInt(accountNum);
                    int money = rnd.nextInt(10);
                    if (i1 != j){
                        try {
                            transfer(accounts[i1], accounts[j], money);
                        } catch (NoEnoughMoneyException e) {
                        }
                    }
                }
            });
            threads[i].start();
        }
    }
}
//不同线程都想获得相同的对象锁，但一直得到不的一种状态  -----死锁
        
        