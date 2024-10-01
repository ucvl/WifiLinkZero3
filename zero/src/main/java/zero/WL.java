package zero;


public class WL {

  
    public static void main(String[] args)    {

        while (true) {
            // 模拟主程序运行
            System.out.println("春眠不觉晓，处处闻啼鸟！自动更新后的输出！");
            try {
                Thread.sleep(2000);
           
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
