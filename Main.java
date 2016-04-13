import javax.swing.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
	
public class Main{
	
public static void main(String[] args){

	JTextField xField = new JTextField();
    JTextField yField = new JTextField();
    JTextField zField = new JTextField();
    Object[] message = {
    	    "doorsize:", xField,
    	    "population:", yField,
    	    "speed:", zField};
    

    int option = JOptionPane.showConfirmDialog(null, message, "Evacuation Simulation", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
    
		ArrayList<Double> exitTime = new ArrayList<Double>();
		ArrayList<Integer> doorSize = new ArrayList<Integer>();
		ArrayList<Integer> numberOfEvacuees = new ArrayList<Integer>();
		ArrayList<Integer> speeds = new ArrayList<Integer>();
		
		int doorvalue = Integer.parseInt(xField.getText());
		int noEvacuees = Integer.parseInt(yField.getText());
		int speed = Integer.parseInt(zField.getText());
			
		//for(speed = 5; speed<50 ; speed++){
			//for( noEvacuees =1; noEvacuees<101 ; noEvacuees++){
	                	ArrayList<Integer> exitTimes = new ArrayList<Integer>();
				//for(int average_counter=0;average_counter<4;average_counter++){
	
					doorSize.add(doorvalue);
					numberOfEvacuees.add(noEvacuees);
					speeds.add(speed);
					
					Room r = new Room(doorvalue,noEvacuees,speed);
					EvacuationFrame myFrame = new EvacuationFrame(r,speed);
					myFrame.setVisible(true);
	
					for(int time=0;time<10001;time++){
						
					
						r.move();
						myFrame.Update();
						if(r.getAllFree()==true){
							exitTimes.add(time);
							System.out.print("Exit time : "+time+" frame steps"+"\n");
							break;}
						if(time==10000){
							exitTimes.add(10000);
							break;}
					}
	
	                	//}
				Average a = new Average(exitTimes);
				exitTime.add(a.getAverage());
			
			//}
		//}
				
			
					
				
		//Output.outputdataSpeed(speeds);
		//Output.outputdataNumber(numberOfEvacuees);
		//Output.outputdataTime(exitTime);
			
		System.exit(0);
    
    }else{
    	
    System.exit(0);	
    }
    
	

}}

	

