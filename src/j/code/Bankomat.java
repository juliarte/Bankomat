package j.code;

public class Bankomat {
    static BankAccount bankAccount = new BankAccount("John Smith"); //new object is created
    public static volatile boolean isStopped; //isStopped = false

    public static void main(String[] args) throws InterruptedException {
        addMoney.start(); //adding money starts
        SpendThread spendThread = new SpendThread();
        SpendThread spendThread1 = new SpendThread();
        SpendThread spendThread2 = new SpendThread(); // 3 different streams to withdraw money
        spendThread.start();
        spendThread1.start();
        spendThread2.start();
        Thread.sleep(4000);
        isStopped = true;

    }

    private static Thread addMoney = new Thread() { // new object is created, anonymous class which extends Thread

        @Override
        public void run() {
            while(!isStopped) { // while isStopped = false
                bankAccount.deposit("1000");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    };

    public static class SpendThread extends Thread {

        @Override
        public void run() {
            while (!isStopped) { // while isStopped = false we are trying to withdraw money
                try {
                    bankAccount.withdraw("100");
                } catch(NotEnoughMoneyException e) { //exception appears if you have not enough money to withdraw
                    System.out.println("You have not enough money to withdraw!");
                }

                try {
                    Thread.sleep(100);// in 100 milliseconds
                } catch (InterruptedException e) { //
                    break;
                }
            }
        }
    }
}
