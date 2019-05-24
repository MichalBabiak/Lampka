package pl.mb.lampka;
import java.util.concurrent.TimeUnit;

public class LedService {
	// Obsługa rozświetlania i wygaszania LED. Zarówno włączanie jak i wyłączanie trwa 3060 milisekund.
	
	public static void TurnON (Led led) throws InterruptedException {
		while(led.brightness<255) {
			led.brightness++;
			TimeUnit.MILLISECONDS.sleep(12);
		}
	}
	
	public static void TurnOff (Led led) throws InterruptedException {
		while(led.brightness>0) {
			led.brightness--;
			TimeUnit.MILLISECONDS.sleep(12);
		}
	}
	public static void LightUp (Led led) throws InterruptedException {
		led.setBrightness(led.getBrightness()+1);
		TimeUnit.MILLISECONDS.sleep(12);
	}
	public static void LightDown (Led led) throws InterruptedException {
		led.setBrightness(led.getBrightness()-1);
		TimeUnit.MILLISECONDS.sleep(12);
	}
 
}
