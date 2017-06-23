package Assignments;

import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class PicklesDragRace extends IRobotAdapter {
	//Sonar sonar = new Sonar();
	
	public PicklesDragRace (IRobotInterface iRobot) {
		super(iRobot);
	}
int lap=0;
	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		PicklesDragRace rob = new PicklesDragRace(base);
		rob.setup();
		while(rob.loop()){
			
		}
		rob.shutDown();		
	}

	private void setup() throws Exception {
		
	
	}
	
	private boolean loop() throws Exception{
		readSensors(100);//<--gotta read them sensors
		
		//getLightBumps() returns an array of length 6 (elements 0 - 5)
		//element 0 is the far left light sensor
		//element 5 is the far right light sensor
		//elements 1-4 are the sensors in between from left to right
		int[] lightBumpReadings = getLightBumps();
		
		//if the sensors don't detect anything, they return 0
		//otherwise they return some positive integer
		//the higher the integer, the closer the object
		driveDirect(800,800);
		
		if(lightBumpReadings[0] > 0){
			driveDirect(400,100);
			sleep(700);	
		}
		if(lightBumpReadings[1] > 0){
			driveDirect(500, 100);
			sleep(700);	
		}
		if(lightBumpReadings[2] > 0 && lap==0){
			driveDirect(360, -360);
			sleep(1000);
		lap++;
		}
		if(lightBumpReadings[3] > 0 && lap==0){
			driveDirect(360,-360);
			sleep(1000);
		lap++;
		}
		if(lightBumpReadings[4] > 0){
			driveDirect(100,400 );
			sleep(700);
			
			
		}
		if(lightBumpReadings[5] > 0){
			driveDirect(100,500);
		sleep(700);
			
			
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