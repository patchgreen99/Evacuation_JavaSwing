import java.io.*;
import java.util.ArrayList;

public class Output {


	public static void outputdataSpeed(ArrayList<Integer> values){


		FileWriter valuesx;
		try {
			valuesx = new FileWriter("Speed.txt");
			BufferedWriter out = new BufferedWriter(valuesx);
			String newLine = System.getProperty("line.separator");
			for(int i = 0 ; i<values.size(); i++){
				out.write(+values.get(i)+ " " +newLine);}
			out.close();
			valuesx.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void outputdataNumber(ArrayList<Integer> values){

		FileWriter valuesy;
		try {
			valuesy = new FileWriter("Number.txt");
			BufferedWriter out = new BufferedWriter(valuesy);
			String newLine = System.getProperty("line.separator");
			for(int i = 0 ; i<values.size(); i++){
				String s =  +values.get(i)+ " " +newLine ;
				out.write(s);}
			out.close();
			valuesy.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void outputdataTime(ArrayList<Double> values){


		FileWriter valuesz;
		try {
			valuesz = new FileWriter("Time.txt");
			BufferedWriter out = new BufferedWriter(valuesz);
			String newLine = System.getProperty("line.separator");
			for(int i = 0 ; i<values.size(); i++){
				out.write(+values.get(i)+ " " +newLine);}
			out.close();
			valuesz.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}



}
