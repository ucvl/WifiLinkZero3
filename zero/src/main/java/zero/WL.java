package zero;

import com.pi4j.Pi4J;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.StopBits;
import com.pi4j.util.Console;

public class WL {
    public static void main(String[] args) throws InterruptedException {

    	
    	var console = new Console();
    	var pi4j = Pi4J.newAutoContext();

    	var serial = pi4j.create(Serial.newConfigBuilder(pi4j)
    	        .use_9600_N81()
    	        .dataBits_8()
    	        .parity(Parity.NONE)
    	        .stopBits(StopBits._1)
    	        .flowControl(FlowControl.NONE)
    	        .id("my-serial")
    	        .device("/dev/ttyS5")
    	        .provider("pigpio-serial")
    	        .build());
    	serial.open();

    	// Wait till the serial port is open
    	console.print("Waiting till serial port is open");
    	// Wait till the serial port is open
    	console.print("Waiting till serial port is open");
    	while (!serial.isOpen()) {
    	    Thread.sleep(250);
    	}

    	// Start a thread to handle the incoming data from the serial port
    	

    	while (serial.isOpen()) {
    	    Thread.sleep(500);
    	    System.out.println("opened....");
    	}
    	System.out.println("closed ....");
    
        // 主程序逻辑
      //  System.out.println(GetLastVersion.getLatestVersion());

      
    }
    
}