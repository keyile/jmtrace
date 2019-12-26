package test;

public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        Runnable counter = new Runnable() {
            @Override
            public void run() {
                SomeClass.staticField++;
            }
        };
        for(int i = 0; i < 5; i++) {
            new Thread(counter).start();
        }
        Thread.sleep(30);
        System.out.println(SomeClass.staticField);
    }
}
