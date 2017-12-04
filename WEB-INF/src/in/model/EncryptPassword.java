package in.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {
	public String encryptPassword(String encryptedPassword)
			throws NoSuchAlgorithmException {
				MessageDigest md;
				md = MessageDigest.getInstance("SHA-256");
				md.update(encryptedPassword.getBytes());
				byte byteData[] = md.digest();
				StringBuffer sb = new StringBuffer();
				int length = byteData.length;
				for(int i = 0; i<length; i++){
					sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				encryptedPassword = sb.toString();
				return encryptedPassword;
	}
}