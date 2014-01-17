package at.inted.sensors;

import at.inted.utils.Console;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.util.ArrayList;
import java.util.List;

/**
 * User: lukas
 * Date: 17.01.14
 * Time: 16:12
 */
public class DistanceSensor extends Thread{

	public double distance = 0;

	public void run(){
		final GpioController gpio = GpioFactory.getInstance();

		GpioPinDigitalOutput trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "trigger", PinState.LOW);
		GpioPinDigitalInput echo = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);

		final List<Long> times = new ArrayList<Long>();
		echo.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				long time = System.nanoTime();
				times.add(time);
				if(times.size() >= 2){
					double distance =  (0.0000175 * (times.get(times.size()-1) - times.get(times.size()-2)));
					if(distance < 30) {
						System.out.println("zu nah" + distance);
						}else{
						System.out.println("nicht zu nah" + distance);
						}
					times.remove(0);
					times.remove(0);
				}
			}

		});



		while(true){
			try{
			trigger.high();
			Thread.sleep(10);
			trigger.low();
			Thread.sleep(10);

			}catch(InterruptedException exception){
				Console.WriteError(exception.getStackTrace());
			}
		}



	}
}
