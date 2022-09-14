import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Blockchain blockchain = new Blockchain();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> new Miner().mineBlock(blockchain));
        }

        executorService.shutdown();
        executorService.awaitTermination(100L, TimeUnit.SECONDS);
        System.out.println(blockchain);
    }
}
