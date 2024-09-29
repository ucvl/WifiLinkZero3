package zero;

import java.util.List;
import java.util.concurrent.ExecutionException;

import common.GetLastVersion;//测试版本获取

import common.NetworkScanner;
import common.UpdateTask;

public class WL {
   
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	 // 启动更新线程
        Thread updateThread = new Thread(new UpdateTask());
        updateThread.setDaemon(true); // 设置为守护线程
        updateThread.start();

        // 主程序逻辑
        while (true) {
            // 模拟主程序运行
            System.out.println("Main program running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 替换为你的子网，例如 "172.16"
        /*
        NetworkScanner scanner = new NetworkScanner("172.16", 100);
        List<String> devices = scanner.scan();
        for (String device : devices) {
            System.out.println("设备IP地址: " + device);
        }
        */
    }


}
