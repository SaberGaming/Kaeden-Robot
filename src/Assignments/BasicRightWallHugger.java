package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class BasicRightWallHugger extends IRobotAdapter {
	//Sonar sonar = new Sonar();
	int counter = 0;
	Camera cam;
	public BasicRightWallHugger(IRobotInterface iRobot) {
		super(iRobot);
	}	public static void main(String[] args) throws Exception {
		Camera cam;
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		BasicRightWallHugger rob = new BasicRightWallHugger(base);
		rob.setup();
		while(rob.loop()){}
		rob.shutDown();
	}

	
	
	private void setup() throws Exception {
		//SETUP CODE GOES HERE!!!!!
		
		cam = new Camera(50,50);
	}
	
	private boolean loop() throws Exception{
		int x = 2;
		//LOOP CODE GOES HERE!!!!!
		readSensors(100);
		int[] lightBumpReadings = getLightBumps();
		counter++;
		driveDirect(680,240);
		if(counter==100){
		cam.takeRGBPicture();
		cam.setTimeout(1000);
		counter=0;
		double red = cam.getRedPercentage(100, true);
		double green = cam.getGreenPercentage(100, true);
		
		if(red > 49) {
			driveDirect(-100, -100);
			Thread.sleep(1000);
		}
		if (green > 49){
			driveDirect(0,0);
			Thread.sleep(1000);
	
		}
}
		if(lightBumpReadings[3] > 100){
			driveDirect(-200, 250);
			sleep(100);
		}
		if(lightBumpReadings[4] > 100){
			driveDirect(-200, 250);
			sleep(100);
		}
		if(lightBumpReadings[5] > 100){
			driveDirect(-200, 250);
			sleep(100);
		}
		//System.out.println("Light " + lightBumpReadings);
	if (isBumpLeft() || isBumpRight()) {
		driveDirect(-260, -250);
		sleep(500);
	}
		return true;
	}
	
	private void sleep(int amt){
		try {
			Thread.sleep(amt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}
