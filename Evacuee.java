import java.util.Random;


public class Evacuee{
	private static double agilityprobability ;
	private static double patienceprobability ;
	private static int originalSize;
	private int diameter ; //screen units
	private MyVector position ; // screen units
	private MyVector movement ; // screen units
	private double speed ; //screen units per iteration 
	private double originalSpeed ; //screen units per iteration 
	private boolean free;
	private int impatience;
	private boolean aggresive;



	public Evacuee(double x,double y,double SPEED){
		position = new MyVector(x,y);
		originalSize = 15;
		diameter = originalSize;
		agilityprobability = 0.75;
		patienceprobability = 0.5;
		aggresive = false;
		impatience = 0;
		speed = SPEED;
		originalSpeed = SPEED;}



	public MyVector getPosition(){
		return position;}
	public MyVector lookAhead(){
		return MyVector.getSum(position,movement.normalize().multiplyBy(speed));}
	public void moveEvacuee(){
		position = MyVector.getSum(position,movement.normalize().multiplyBy(speed));}
	
	public int getSize(){
		return diameter;}
	public void setSize(int size){
		diameter = size;}
	public void resetSize(){
		diameter = originalSize;}
	public static int getOriginalSize(){
		return originalSize;}

	public void setfree(boolean A){
		free = A;}
	public boolean free(){
		return free;}
	
	public boolean getAggresion(){
		return aggresive;}
	public void notAggresive(){
		aggresive = false;}
	
	public double getSpeed(){
		return speed;}
	public void setSpeed(double sp){
		speed = sp;}
	public void resetSpeed(){
		speed=originalSpeed+1;}
	
	public void setMovement(MyVector s){
		movement = s;}
	public MyVector getMove(){
		return movement;}


	
	// Impatience and aggression method
		
	public void impatience(int length, int doorsize){
		
		//impatience setter
		if(movement.getx()==0 && movement.gety()==0){
			impatience++;}
		else{impatience = 0;
		resetSize();}
	
		//size adjust when impatient
		if(impatience>10 && diameter>13){
			diameter = (this.getSize()-1);}
		if(impatience>30 && position.gety()>(length/2)-(doorsize/2) && position.gety()<(length/2)+(doorsize/2) && position.getx() < 20 ){
			diameter = (this.getSize()-1);
			aggresive =true;
			moveEvacuee();}}
	

    // Random order update method
	
	public static Evacuee[] scrambleArray(Evacuee[] myArray){
		Random rnd = new Random();
		for (int i = myArray.length - 1; i > 0; i--){
			int index = rnd.nextInt(i + 1);
			// Simple swap
			Evacuee a = myArray[index];
			myArray[index] = myArray[i];
			myArray[i] = a;
		}
		return myArray;}


	//Evacuee overlap types , in both the 1 accounts for rounding errors when deciding whether a pair has collided
	public static boolean evacueeCollision(Evacuee A, Evacuee B){
		boolean result = false;
		if(MyVector.getDifference(A.lookAhead(), B.getPosition()).getmagnitude()< (A.getSize()/2) + (B.getSize()/2)+1){
			result = true;}
		return result;}
	public static boolean plantingCollision(Evacuee A, Evacuee B){
		boolean result = false;
		if(MyVector.getDifference(A.getPosition(), B.getPosition()).getmagnitude()< (A.getSize()/2) + (B.getSize()/2)+1){
			result = true;}
		return result;}




	//Setting the movement vectors

	public void moveBack(){
		movement = movement.rotation(180);}

	public void moveRight(){
		movement = movement.rotation(270);}

	public void moveLeft(){
		movement = movement.rotation(90);}
	
	//did they collide
		public static boolean anyColliders(int person,Evacuee[] myEvacuees){
			boolean result =false;
			for(int neighbour=0; neighbour<myEvacuees.length; neighbour++){
				if(neighbour!=person){
					if(myEvacuees[neighbour]!=null){
						if(Evacuee.evacueeCollision(myEvacuees[person],myEvacuees[neighbour])==true){
							result = true;} 
					}}
			}
			return result;}

		
		//Move away from the wall
	public static void bounceOfWall(int person ,Evacuee[] myEvacuees,int myydimension){
		if(myEvacuees[person].getPosition().gety()>(myydimension/2)){
			myEvacuees[person].moveRight();
		}else{myEvacuees[person].moveLeft();}
		if(anyColliders(person,myEvacuees)==true){
			myEvacuees[person].setMovement(new MyVector(0,0));}}
	// the decision between left and right is made by deciding which direction the door is in and then consequently turning in that direction
	
	

    //move away from the person
	public static void collisionDecision(int person, Evacuee[] myEvacuees){
		MyVector originalMove = myEvacuees[person].getMove();
		if(Math.random()*100<agilityprobability*100 && myEvacuees[person].getPosition().getx()>0){
			myEvacuees[person].moveRight();}

		if(anyColliders(person,myEvacuees)==true){
			myEvacuees[person].setMovement(originalMove);
			if(Math.random()*100<agilityprobability*100 && myEvacuees[person].getPosition().getx()>0){
				myEvacuees[person].moveLeft();}

			if(anyColliders(person,myEvacuees)==true){
				myEvacuees[person].setMovement(originalMove);
				if(Math.random()*100<patienceprobability*100){
					myEvacuees[person].moveBack();}

				if(anyColliders(person,myEvacuees)==true){
					myEvacuees[person].setMovement(new MyVector(0,0));
				}
			}
		}
	}



	

}