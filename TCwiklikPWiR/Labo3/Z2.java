import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Z2 {
    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    int generatedNumber = random.nextInt(10); 
                    System.out.println("Generated number: " + generatedNumber);
                    queue.put(generatedNumber); 
                    if (generatedNumber == 0) { 
                        Thread.sleep(1000); 
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("The generating thread was interrupted");
                    break;
                }
            }
        });

        Thread consumer = new Thread(() -> {
            StringBuilder currentNumber = new StringBuilder();
            while (true) {
                try {
                    Integer digit = queue.take(); 
                    if (digit == 0) {
                        if (currentNumber.length() > 0) {
                            System.out.println("Number created: " + currentNumber.append(digit));
                            currentNumber.setLength(0); 
                        }
                    } else if (!(currentNumber.length() == 0 && digit == 0)) { 
                        currentNumber.append(digit);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("The consuming thread has been interrupted");
                    break;
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
