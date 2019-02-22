package commons;

import java.util.function.IntBinaryOperator;

public class LambdaParameter {
	
	public static boolean method(OneArgComparison operator){
	    return operator.op(10);
	}
	
	public static void main(String[] args) {
		System.out.println("El resultado es: " + method((a -> a > 5)));
	}
}
