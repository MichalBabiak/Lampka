package pl.mb.lampka;
import java.util.concurrent.TimeUnit;

public class SensorService {

	public static int CheckDistance(Sensor sensorRead) {
		/*
		 * Sprawdza jaki jest dystans do przeszkody i go zwraca. Przyjmuje za argument
		 * wartość podaną z czujnika.
		 */
		int distance = sensorRead.getDistance();
		return distance;
	}

	public static boolean CheckForMovement(Sensor left, Sensor right) throws InterruptedException {
		/*
		 * Sprawdza czy dystans do przeszkody się zmienił. Pozwala ustalić jak duża musi
		 * być zmiana aby wywołać reakcję. Zwraca wartośc logiczną - true jeżeli wykryto
		 * zmianę odległości, false jeżeli nie.
		 */
        boolean movementDetected;
		int firstReadLeft = CheckDistance(left);
		int firstReadRight = CheckDistance(right);
		TimeUnit.MILLISECONDS.sleep(500); // Roboczo używam enum TimeUnit. Docelowo w Arduino delay.
		int secondReadLeft = CheckDistance(left);
		int secondReadRight = CheckDistance(right);
		if (Math.abs(firstReadLeft - secondReadLeft) > 1 || Math.abs(firstReadRight-secondReadRight)>1) {
			movementDetected = true;
		} else {
			movementDetected = false;
		}
		return movementDetected;
		
	}

	public static boolean HumanDetected(Sensor left, Sensor right) throws InterruptedException {
		/* Sprawdza czy w odległości 70 cm wykryto ruch. Dokonuje dalszych pomiarów jeżeli tak. 
		 * W ciągu 2s wykonuje 4 pomiary. 2 pomiary wskazujące na wykrycie ruchu powodują zwrócenie wartości true.
		 */
		int counter = 0;
		int detections = 0;
		while (CheckDistance(left) < 70||CheckDistance(right)<70 && counter <= 3) {
			if (CheckForMovement(left,right)) {
				detections++;
			}
			counter++;
		}
		if (detections >= 2) {
			return true;
		} else {
			return false;
		}

	}
	
	public static void LightControl(Sensor sensorRead, Led led) throws InterruptedException {
		//Regulacja natężenia światła za pomocą dłoni.
		while(10>sensorRead.getDistance()&&sensorRead.getDistance()>5&&led.getBrightness()>1) {
			LedService.LightDown(led);		
		}
		TimeUnit.MILLISECONDS.sleep(1000);
		while(5>sensorRead.getDistance()&&sensorRead.getDistance()>2&&led.getBrightness()<255) {
			LedService.LightUp(led);
		}
		
	}
	
	public static void EngineControl (Sensor sensorRead, Engine engine) throws InterruptedException {
		// Sterowanie silnikiem za pomocą dłoni.
		while(10>sensorRead.getDistance()&&sensorRead.getDistance()>5&&engine.limitSwitchLeft==false) {
			engine.RotateLeft();	
		}
		TimeUnit.MILLISECONDS.sleep(1000);
		while(5>sensorRead.getDistance()&&sensorRead.getDistance()>2&&engine.limitSwitchRight==false) {
			engine.RotateRight();
		}
	}
}
	

