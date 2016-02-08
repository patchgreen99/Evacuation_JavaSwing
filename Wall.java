
public class Wall{

	private int thickness; //screen units
	private int doorSize;

	//Constructor for wall
	public Wall(int roomX , int roomY, int myDoorSize){
		doorSize = myDoorSize;
		thickness = 10;}

	//get methods
	public int getThickness(){
		return thickness;}
	public int getDoorSize(){
		return doorSize;}

}