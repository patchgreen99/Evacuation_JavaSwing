import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class EvacuationPanel extends JPanel {

	private Room myRoom;
	private int paintSleep;
	
	
	public EvacuationPanel(Room b, int speed){
		setBackground(Color.LIGHT_GRAY);
		myRoom=b;
		paintSleep = 5*speed;}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,2*myRoom.getWall().getThickness() + myRoom.getxdimension(),myRoom.getWall().getThickness());//top
        g.fillRect(0,myRoom.getWall().getThickness()+myRoom.getydimension(),2*myRoom.getWall().getThickness()+myRoom.getxdimension(),myRoom.getWall().getThickness());//bottom
        g.fillRect(myRoom.getWall().getThickness() + myRoom.getxdimension(),myRoom.getWall().getThickness(),myRoom.getWall().getThickness(),myRoom.getydimension());//right
        g.fillRect(0,myRoom.getWall().getThickness(),myRoom.getWall().getThickness(),(myRoom.getydimension()/2)-(myRoom.getDoorSize()/2));//top left
        g.fillRect(0,myRoom.getWall().getThickness()+(myRoom.getydimension()/2)+(myRoom.getDoorSize()/2),myRoom.getWall().getThickness(),(myRoom.getydimension()/2)-(myRoom.getDoorSize()/2));//bottom left
        
        
        for(int thisguy=0;thisguy<myRoom.getEvacuees().length;thisguy++){
        		if(myRoom.getEvacuee(thisguy)!=null ){
        			g.setColor(Color.MAGENTA);
        			if(myRoom.getEvacuee(thisguy).getSize()<15){
        				g.setColor(Color.BLUE);}
        			if(myRoom.getEvacuee(thisguy).getSize()<14){
        				g.setColor(Color.BLACK);}
        			if(myRoom.getEvacuee(thisguy).getAggresion()==true){
        				g.setColor(Color.RED);}	
        			
        					
		g.fillOval((int) (myRoom.getEvacuee(thisguy).getPosition().getx())+myRoom.getWall().getThickness()-(myRoom.getEvacuee(thisguy).getSize()/2),(int) (myRoom.getEvacuee(thisguy).getPosition().gety())+myRoom.getWall().getThickness()-(myRoom.getEvacuee(thisguy).getSize()/2),myRoom.getEvacuee(thisguy).getSize(),myRoom.getEvacuee(thisguy).getSize());//SOMETHING IS WRONG IN HERE
        		}}} // coordinates used in the room start a wall thickness into the frame, the -5 due to rounding error
	
    public void Update(){
        try {
            Thread.sleep(paintSleep);}
        catch (InterruptedException e) {
        }
        repaint();}
	
}