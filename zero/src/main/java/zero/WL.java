package zero;

import java.util.List;
import java.util.concurrent.ExecutionException;

import common.GetLastVersion;//测试版本获取

import common.NetworkScanner;

public class WL {
   
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //测试版本
    	String version = GetLastVersion.getLatestVersion();
        if (version != null) {
            System.out.println("Latest version: " + version);
        } else {
            System.out.println("Failed get latest release.");
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
