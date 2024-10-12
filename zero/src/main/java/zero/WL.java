package zero;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.exception.IOException;

import com.pi4j.io.serial.Serial;



import common.GetLastVersion;

public class WL {
    public static void main(String[] args) {
        // Initialize Pi4J context
        Context pi4j = Pi4J.newAutoContext();

        // 使用 Pi4J 创建串口配置
        Serial serial = pi4j.serial().create("/dev/ttyS5");

        // 主程序逻辑
        System.out.println(GetLastVersion.getLatestVersion());

        while (true) {
            // 模拟主程序运行
            System.out.println("ready open serial.............！");
            try {
                Thread.sleep(1000);
                // 打开串口
                serial.open();
                System.out.println("串口已打开");

                // 等待5秒钟
                Thread.sleep(5000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭串口
                if (serial.isOpen()) {
                    try {
                        serial.close();
                        System.out.println("串口已关闭");
                    } catch (IllegalStateException | IOException ex) {
                        System.out.println("关闭串口失败: " + ex.getMessage());
                    }
                }
            }
        }
    }
}
