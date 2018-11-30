package commons;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String calcularMd5(String password) throws NoSuchAlgorithmException {
	    MessageDigest msgeDigest = MessageDigest.getInstance("MD5");
	    msgeDigest.update(password.getBytes(),0,password.length());
	    return new BigInteger(1, msgeDigest.digest()).toString(16);
	}
	
}
