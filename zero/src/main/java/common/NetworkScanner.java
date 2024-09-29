package common;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NetworkScanner {
    private String subnet;
    private int timeout;

    public NetworkScanner(String subnet, int timeout) {
        this.subnet = subnet;
        this.timeout = timeout;
    }

    public List<String> scan() throws InterruptedException {
        List<String> devices = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(100);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < 256; i++) {
            for (int j = 1; j < 256; j++) {
                final int k = i;
                final int l = j;
                futures.add(executor.submit(() -> {
                    String host = subnet + "." + k + "." + l;
                    try {
                        InetAddress inetAddress = InetAddress.getByName(host);
                        if (inetAddress.isReachable(timeout) && inetAddress.getHostName().contains("orangepizero3")) {
                            synchronized (devices) {
                                devices.add(host + " (" + inetAddress.getHostName() + ")");
                            }
                        }
                        // 动态显示进度
                        System.out.println("正在扫描: " + host);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
            }
        }

        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return devices;
    }
}