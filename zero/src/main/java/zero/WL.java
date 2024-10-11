package zero;

import java.io.IOException;

import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;
import com.pi4j.io.serial.StopBits;

import common.GetLastVersion;

public class WL {

	public static void main(String[] args) {
		final Serial serial = SerialFactory.createInstance();

		// 主程序逻辑
		System.out.println(GetLastVersion.getLatestVersion());
		while (true) {
			// 模拟主程序运行
			System.out.println("准备开串口！");
			try {
				Thread.sleep(1000);

				try {
					// 打开串口

					serial.open("/dev/ttyS5", Baud._19200, DataBits._8, Parity.NONE, StopBits._1, FlowControl.NONE);

					System.out.println("串口已打开");

					// 等待5秒钟
					Thread.sleep(5000);

				} catch (SerialPortException | InterruptedException | IOException ex) {
					System.out.println("操作失败: " + ex.getMessage());
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

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		// 替换为你的子网，例如 "172.16"
		/*
		 * NetworkScanner scanner = new NetworkScanner("172.16", 100); List<String>
		 * devices = scanner.scan(); for (String device : devices) {
		 * System.out.println("设备IP地址: " + device); }
		 */
	}

}
