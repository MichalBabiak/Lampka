package pl.mb.lampka;

public class MainLoop {
	public static final double versionNumber = 0.1; 
	//TODO Wyłącznik czasowy - problem ze względu na brak wielowątkowości w arduino, trzeba będzie powiązać z czasem wykonywania pętli.
    public static int timer = 0;
	public static void main(String[] args) throws InterruptedException {
	boolean isOn=true; // Włącznik
	Engine engine = new Engine(); // Silnik
	Led led = new Led(); // Lampa
	Sensor leftSensor = new Sensor(); // Sensor lewy
	Sensor rightSensor = new Sensor(); // Sensor prawy
	while(isOn) {
		if(SensorService.HumanDetected(leftSensor, rightSensor)) {
			while(timer<120) {
			SensorService.EngineControl(leftSensor, engine);
			SensorService.LightControl(rightSensor, led);
			SensorService.HumanDetected(leftSensor, rightSensor);
			}
		}
		LedService.TurnOff(led);
		
		
	}

	}

}
