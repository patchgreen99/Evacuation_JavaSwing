import java.util.ArrayList;


public class Average {
	
	private double average;
	
	public Average(ArrayList<Integer> a){
		double sum =0;
		for(int i=0;i<a.size();i++){
			sum = sum + a.get(i);}
		average = sum/a.size() ;}
	
	
	public double getAverage(){
		return average;}
}
