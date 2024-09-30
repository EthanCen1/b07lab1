public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));

		double [] coef1 = {1, 2, 3};
		int [] exp1 = {0, 4, 6};
		Polynomial p1 = new Polynomial(coef1, exp1);
		double [] c2 = {3, -2 , -3};
		int [] exp2 = {1, 4, 7};
		Polynomial p2 = new Polynomial(coef1, exp2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		Polynomial product1 = p1.multiply(p2);
		System.out.println("Product of p1 and p2 evaluation at x=6: " + product1.evaluate(6));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");

		double[] coef3 = {4, 3};
		int[] exp3 = {2, 3};
		Polynomial p3 = new Polynomial(coef3, exp3);
		double[] coef4 = {1, -3};
		int[] exp4 = {0, 2};
		Polynomial p4 = new Polynomial(coef4, exp4);
		Polynomial sum34 = p3.add(p4);
		Polynomial product34 = p3.multiply(p4);
		System.out.println("sum of p3 and p4 evaluation at x=2: " + sum34.evaluate(2));
		System.out.println("Product of p3 and p4: " + product34.evaluate(1)); 


		double[] coef7 = {1, 1, 5};
		int[] exp7 = {0, 1, 7};
		Polynomial p7 = new Polynomial(coef7, exp7);
		double[] coef8 = {2, 2, 3, 4, -5};
		int[] exp8 = {0, 1, 4, 6, 7};
		Polynomial p8 = new Polynomial(coef8, exp8);
		Polynomial product78 = p7.multiply(p8);
		Polynomial sum78 = p7.add(p8);
		System.out.println("sum of p3 and p4 evaluation at x=1: " + sum78.evaluate(1));
		System.out.println("Product of p7 and p8: " + product78.evaluate(1));

	}
}