package com.wangzhe.util;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * load rsa pbulickey/privatekey from a file or string
 * @author kunkka
 * date 2016/5/12
 */
public class keyUtil {
	private static final String PUB_KEY_START_TAG = "-----BEGIN PUBLIC KEY-----";
	private static final String PUB_KEY_END_TAG = "-----END PUBLIC KEY-----";
	private static final String PRI_KEY_START_TAG = "-----BEGIN PRIVATE KEY-----";
	private static final String PRI_KEY_END_TAG = "-----END PRIVATE KEY-----";
	
	private static final String PRI_KEY_FILE_PATH;
	private static final String PUB_KEY_FILE_PATH;
	
	static{
		if(Config.isDebug()){
			PRI_KEY_FILE_PATH = "D:\\priKey.pem";
			PUB_KEY_FILE_PATH = "D:\\pubKey.pem";
		}else {
			PRI_KEY_FILE_PATH = "/usr/wangzhe/priKey.pem";
			PUB_KEY_FILE_PATH = "/usr/wangzhe/pubKey.pem";
		}
	}
	
	private static final Logger LOGGER =
			LoggerFactory.getLogger(keyUtil.class);
	
	public static RSAPublicKey getPublicKey(){			
		try {
			String originalKeyStr = readFile(PUB_KEY_FILE_PATH);
			String keyStr = subPubKeyStr(originalKeyStr);
			RSAPublicKey rsaPublicKey = loadPublicKey(keyStr);
			return rsaPublicKey;
		} catch (LoadkeyException e) {
			LOGGER.error(e.getMessage());
		} catch (DecoderException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public static RSAPrivateKey getPrivateKey(){			
		try {
			String originalKeyStr = readFile(PRI_KEY_FILE_PATH);
			String keyStr = subPriKeyStr(originalKeyStr);
			RSAPrivateKey rsaPrivateKey = loadPrivateKey(keyStr);
			return rsaPrivateKey;
		} catch (LoadkeyException e) {
			LOGGER.error(e.getMessage());			
		} catch (IOException e) {
			LOGGER.error(e.getMessage());	
		}
		return null;
	}
	
	private static String readFile(String filePath) throws IOException{
		File file = new File(filePath);
		if(!file.exists()){
			LOGGER.error("can not find file by domain");
			throw new FileNotFoundException("can not find file by domain");
		}
		InputStream is = new DataInputStream(new FileInputStream(file));
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        int i=-1; 
        
		while((i = is.read())!=-1){ 
			baos.write(i); 
		}
		return baos.toString();	
	}
	
	
	/**
	 * Subsequence the real key from original key string
	 * @param originalKeyStr
	 * @return
	 */
	private static String subPubKeyStr(String originalKeyStr){
		int startIndex = originalKeyStr.indexOf(PUB_KEY_START_TAG) + PUB_KEY_START_TAG.length();
		int endIndex = originalKeyStr.indexOf(PUB_KEY_END_TAG);
		return originalKeyStr.substring(startIndex + 1, endIndex);
	}
	
	/**
	 * Subsequence the real key from original key string
	 * @param originalKeyStr
	 * @return
	 */
	private static String subPriKeyStr(String originalKeyStr){
		int startIndex = originalKeyStr.indexOf(PRI_KEY_START_TAG) + PRI_KEY_START_TAG.length();
		int endIndex = originalKeyStr.indexOf(PRI_KEY_END_TAG);
		return originalKeyStr.substring(startIndex + 1, endIndex);
	}
	
    public static RSAPublicKey loadPublicKey(String publicKeyStr) throws LoadkeyException, DecoderException{  
        try {  
            Base64 base64Decoder= new Base64();  
            byte[] buffer = null;
			
			buffer = (byte[]) base64Decoder.decode(publicKeyStr);
			
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);  
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);  
        } 
        catch (NoSuchAlgorithmException e) {  
            throw new LoadkeyException("no such algorithm");  
        } catch (InvalidKeySpecException e) {  
            throw new LoadkeyException("invalid key");  
        } catch (NullPointerException e) {  
            throw new LoadkeyException("nullpointer exception");  
        } 
    }  
    
    public static RSAPrivateKey loadPrivateKey(String privateKeyStr) throws LoadkeyException{  
        try {  
            Base64 base64Decoder= new Base64();  
            byte[] buffer= (byte[]) base64Decoder.decode(privateKeyStr);  
            
            PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);  
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");  
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);  
        } catch (NoSuchAlgorithmException e) {  
        	 throw new LoadkeyException("no such algorithm");   
        } catch (InvalidKeySpecException e) {  
        	throw new LoadkeyException("invalid key");  
        } catch (NullPointerException e) {  
        	throw new LoadkeyException("nullpointer exception");  
        }  
    }
    
}
