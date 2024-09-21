public class Polynomial{
	double coef[];

	public Polynomial(){
		coef = new double[1];
		coef[0] = 0;
		}
	
	public Polynomial(double input[]){
		coef = new double[input.length];
		for (int i = 0; i < input.length; i++){
			this.coef[i] = input[i];
		}
	}

	public Polynomial add(Polynomial input){
		int min_length;
		int max_length;
		if (this.coef.length >= input.coef.length){
			max_length = this.coef.length;
			min_length = input.coef.length;
		}else{
			max_length = input.coef.length;
			min_length = this.coef.length;
		}

		double new_coef[] = new double[max_length];

		for (int i = 0; i < max_length; i++){
			if (i < min_length){
				new_coef[i] = this.coef[i] + input.coef[i];
			}else{
				if (this.coef.length >= input.coef.length){
					new_coef[i] = this.coef[i];
				}else{
					new_coef[i] = input.coef[i];
				}
			}
		}

		Polynomial result = new Polynomial(new_coef);
		return result;
	}

	public double evaluate(double input){
		double result = 0;

		for (int i = 0; i<this.coef.length; i++){
			result += this.coef[i] * Math.pow(input, i);
		}

		return result;

	}

	public boolean hasRoot(double input){
		if (this.evaluate(input) == 0){
			return true;
		}
		return false;
	}

}