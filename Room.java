


public class Room{

	private int xdimension; //screen units
	private int ydimension; //screen units
	private int doorSize; //screen units
	private int numberOfEvacuees; //evacuee's per screen unit
	private Evacuee[] myEvacuee; 
	private Wall myWall;
	private boolean allFree;
	


	public Room(int doorsize, int noPeople, double SPEED){
		doorSize = doorsize;
		numberOfEvacuees = noPeople;
		ydimension=500;
		xdimension=500;
		myEvacuee = new Evacuee[numberOfEvacuees]; // split room into array 500 by 500
		myWall = new Wall(xdimension,ydimension,doorSize);
		fillRoom(SPEED);}


	public int getxdimension(){
		return xdimension;}
	public int getydimension(){
		return ydimension;}
	public int getDoorSize(){
		return doorSize;}
	public Wall getWall(){
		return myWall;}
	public Evacuee[] getEvacuees(){
		return myEvacuee;}
	public Evacuee getEvacuee(int i){
		return myEvacuee[i];}
	public boolean getAllFree(){
		return allFree;}



	private void fillRoom(double SPEED){
		for(int thisguy=0;thisguy<numberOfEvacuees;thisguy++){
			double posX=(Evacuee.getOriginalSize()/2)+Math.random()*(xdimension-Evacuee.getOriginalSize());
			double posY=(Evacuee.getOriginalSize()/2)+Math.random()*(ydimension-Evacuee.getOriginalSize()); //Just create numbers in the boundary where the evacuee is not hitting the wall
			myEvacuee[thisguy] = new Evacuee(posX,posY,SPEED);

			if(thisguy!=0){ // watch the loop in here its not quite right
				for(int neighbours=0; neighbours <= thisguy-1 ;neighbours++){
					if(Evacuee.plantingCollision(myEvacuee[thisguy],myEvacuee[neighbours])==true){ // is there a guy there, if so do it again. The fall is accounted for in the random number generator
						thisguy = thisguy -1;}
				}}}}



	//METHODS FOR SINGLE TIME STEPS TO ADJUST THE MOVEMENT VECTOR ONLY

	//set original exit vectors
	public void whereIsTheDoor(int person){
		int m = (int) Math.floor(Math.random()*((doorSize)-(myEvacuee[person].getSize())));
		MyVector point = new MyVector(-10,(ydimension/2)-(doorSize/2)+(myEvacuee[person].getSize()/2)+m);// position of the door
		myEvacuee[person].setMovement(MyVector.getDifference(point, myEvacuee[person].getPosition()));}

	//Check for wall
	public void hitWall(int person){
		if(myEvacuee[person].getPosition().gety()>(ydimension/2)-(doorSize/2)+(myEvacuee[person].getSize()/2) && myEvacuee[person].getPosition().gety() <(ydimension/2)+(doorSize/2)-(myEvacuee[person].getSize()/2)-2 ){
		}else{
		// so that they can walk through the door, -3 rounding error so they won't enter the wall
		if(myEvacuee[person].lookAhead().getx()<(myEvacuee[person].getSize()/2)||myEvacuee[person].lookAhead().getx()>xdimension-(myEvacuee[person].getSize()/2)
				||myEvacuee[person].lookAhead().gety()<(myEvacuee[person].getSize()/2)||myEvacuee[person].lookAhead().gety()>ydimension-(myEvacuee[person].getSize()/2)){
			Evacuee.bounceOfWall(person,myEvacuee,ydimension);}// -1 is for rounding errors
		}}
				
	public void HaveIHitEvacuee(int person){
		if(Evacuee.anyColliders(person, myEvacuee)==true){
			Evacuee.collisionDecision(person,myEvacuee);}
		}
	
	
	
	
	

	//MOVEMENT METHOD

	public void move(){
		Evacuee.scrambleArray(myEvacuee);
		int count = 0;
		for(int thisguy=0;thisguy<myEvacuee.length;thisguy++){
			if(myEvacuee[thisguy]!=null){			

				whereIsTheDoor(thisguy);

				myEvacuee[thisguy].resetSpeed(); // speed is higher by 1 when reset


				do{
					myEvacuee[thisguy].setSpeed(myEvacuee[thisguy].getSpeed()-1);
							HaveIHitEvacuee(thisguy);
							hitWall(thisguy);//move away from the wall the other person will move away from you
						
				}while(myEvacuee[thisguy].getMove().getx()==0 && myEvacuee[thisguy].getMove().gety()==0 && myEvacuee[thisguy].getSpeed()>1 );//Check possible moves for multiple speeds

				myEvacuee[thisguy].impatience(ydimension,doorSize);
				
				myEvacuee[thisguy].moveEvacuee();

				if(myEvacuee[thisguy].getPosition().getx()<-5){
					myEvacuee[thisguy]=null;}//extra move per iteration because the doorway is almost clear so speed doubles

			}else{count++;}
		}
		if(count==numberOfEvacuees){
			allFree = true;}
	}






}