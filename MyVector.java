public class MyVector {
	private double x;
	private double y;

	public MyVector(double myX, double myY){
		x = myX;
		y = myY;}

	//Get methods
	public double getx(){
		return x;}
	public double  gety(){
		return y;}

	/*angle is the rotation anticlockwise
	because a flipped coordinate      ( cos  sin  )
	system is used rotation matrix is ( -sin cos  )
	 */

	public MyVector rotation(int angle){
		double xnew = (x*Math.cos(Math.PI*angle/180) + y*Math.sin(Math.PI*angle/180));
		double ynew = (y*Math.cos(Math.PI*angle/180) - x*Math.sin(Math.PI*angle/180));
		return new MyVector(xnew,ynew);}

	public MyVector normalize(){
		double xnew=0;
		double ynew=0;
		if(x!=0 && y!=0){
		xnew = (x/Math.sqrt(x*x+y*y));
		ynew = (y/Math.sqrt(x*x+y*y));}
		return new MyVector(xnew,ynew);}
	

	public double getmagnitude(){
		return Math.sqrt(x*x + y*y);}
	
	public static MyVector getDifference(MyVector A, MyVector B){
		return new MyVector(A.getx()-B.getx(),A.gety()-B.gety());}
	
	public static MyVector getSum(MyVector A, MyVector B){
		return new MyVector(A.getx()+B.getx(),A.gety()+B.gety());}
	
	public MyVector multiplyBy(double f){
		return new MyVector(x*f,y*f);}
	
	
}
