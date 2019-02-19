public class ThreadTest {
    public static void main(String[] args) {
        threadTest1();
    }

    /**
     * 在一个单独的线程中执行任务的简单过程
     */
    private static void threadTest1() {
        int i = 0;
        // 1.建立一个Runnable的实例
        Runnable r = () -> {
            System.out.println("Runnable中定义的方法被调用。");
        };
        // 2 由Runnable创建一个Thread对象
        Thread t = new Thread(r);
        // 3. 启动线程
        t.start();
        // 不要调用Thread类或Runnable对象的run方法，这个方法只会执行同一个线程中的任务
        // 而不会启动新线程
    }
}
