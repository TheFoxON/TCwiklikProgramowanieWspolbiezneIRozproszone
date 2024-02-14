class Main {
    public static void main(String[] args) throws InterruptedException {
        Stopwatch stoper1 = new Stopwatch();
        Stopwatch stoper2 = new Stopwatch();

        stoper1.start();
        stoper2.start();

        Thread.sleep(2000);

        stoper1.pause();

        Thread.sleep(2000);

        stoper1.start();
        Thread.sleep(1000);
        stoper1.reset();

        stoper2.pause();
        stoper2.reset();
    }
}
