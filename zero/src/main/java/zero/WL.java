package zero;



public class WL {

   
    public static void main(String[] args)    {

        // 主程序逻辑
        while (true) {
            // 模拟主程序运行
            System.out.println("往事如风！！");
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
