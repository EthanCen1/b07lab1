import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Polynomial{
	double coef[];
	int exp[];

	public Polynomial(){
		this.coef = new double[1];
		this.coef[0] = 0;
		this.exp = new int[1];
		this.exp[0] = 0;
		}
	
	public Polynomial(double input_coef[], int input_exp[]){
		this.coef = new double[input_coef.length];
		this.exp = new int[input_exp.length];
		for (int i = 0; i < input_coef.length; i++){
			this.coef[i] = input_coef[i];
		}
		for (int j = 0; j < input_exp.length; j++){
			this.exp[j] = input_exp[j];
		}
	}

	public Polynomial(File f) throws FileNotFoundException{
 		Scanner scanner = new Scanner(f);
 		String line = scanner.nextLine();
 		scanner.close();
 		line = line.replace("-", "+-");
 		String terms[] = line.split("\\+");

	 	this.coef = new double[terms.length];
	    this.exp = new int[terms.length];

    	for (int i = 0; i < terms.length; i++) {
	      String[] coef_exp = terms[i].split("x");
	      this.coef[i] = Double.parseDouble(coef_exp[0]);
	      this.exp[i] = (coef_exp.length == 1) ? 0: Integer.parseInt(coef_exp[1]);
    	}
    }

    public void saveToFile(String expression) throws IOException {
    	FileWriter writer = new FileWriter(expression);
    	String line = "";
    	for (int i = 0; i < this.coef.length; i++) {
    		if (i == this.coef.length-1){
    			line += this.coef[i] + "x" + this.exp[i];
    		}else{
    		line += this.coef[i] + "x" + this.exp[i] + "+";
    		}
    	}
    	line = line.replace("+-", "-");
    	line = line.replace("x0", "");
       	writer.write(line);
    	writer.close();
    }

	public Polynomial add(Polynomial input){
		if (this.exp.length == 0){
			return new Polynomial(input.coef, input.exp);
		}
		if (input.exp.length == 0){
			return new Polynomial(this.coef, this.exp);
		}
		int max_length = this.exp.length + input.exp.length;
		double new_coef[] = new double [max_length];
		int new_exp[] = new int [max_length];
		int count = 0;

		for (int i = 0; i < this.exp.length; i++){
			boolean flag = false;
			for (int j = 0; j < input.exp.length; j++){
				if (this.exp[i] == input.exp[j]){
					double sum = this.coef[i] + input.coef[j];
					if (sum != 0){
						new_coef[count] = sum;
						new_exp[count] = this.exp[i];
						count ++;
					}
					flag = true;
					break;
				}
			}
			if (flag == false){
				new_coef[count] = this.coef[i];
				new_exp[count] = this.exp[i];
				count ++;
			}
		}

		for (int p = 0; p < input.exp.length; p++){
			 boolean flag = false;
			 for (int q = 0; q < this.exp.length; q++){
			 	if (this.exp[q] == input.exp[p]){
			 		flag = true;
			 		break;
			 	}
			 }
			 if (flag == false){
			 	new_coef[count] = input.coef[p];
			 	new_exp[count] = input.exp[p];
			 	count ++;
			 }
		}

		double return_coef[] = new double[count];
		int return_exp[] = new int[count];

		for (int i = 0; i < count; i++){
			return_exp[i] = new_exp[i];
			return_coef[i] = new_coef[i];
		}

		return new Polynomial(return_coef, return_exp);
	}

	public double evaluate(double input){
		double result = 0;

		for (int i = 0; i<this.coef.length; i++){
			result += this.coef[i] * Math.pow(input, this.exp[i]);
		}
		return result;

	}

	public boolean hasRoot(double input){
		if (this.evaluate(input) == 0){
			return true;
		}
		return false;
	}

	public Polynomial multiply(Polynomial input){
		int max_length = this.coef.length * input.coef.length;
		int new_length = 0;
		double new_coef[] = new double [max_length];
		int new_exp[] = new int [max_length];
		int count = 0;

		for (int i = 0; i < this.exp.length; i++){
			for (int j = 0; j < input.exp.length; j++){
				new_coef[count] = this.coef[i] * input.coef[j];
				new_exp[count] = this.exp[i] + input.exp[j];
				count++;
			}
		}

		for (int i = 0; i < count; i++){
			for (int j = i+1; j < count; j++){
				if (new_exp[i] == new_exp[j]){
					new_coef[i] += new_coef[j];
					new_coef[j] = 0;
					new_exp[j] = 0; 
				}
			}
		}

		for (int p = 0; p < count; p++){
			if (new_coef[p] != 0){
				new_length ++;
			}
		}

		double result_coef[] = new double[new_length];
		int result_exp[] = new int[new_length];
		int track = 0;
		for (int q = 0; q < count; q++){
			if (new_coef[q] != 0){
				result_coef[track] = new_coef[q];
				result_exp[track] = new_exp[q];
				track++;
			}
		}

		return new Polynomial(result_coef, result_exp);
	}



}