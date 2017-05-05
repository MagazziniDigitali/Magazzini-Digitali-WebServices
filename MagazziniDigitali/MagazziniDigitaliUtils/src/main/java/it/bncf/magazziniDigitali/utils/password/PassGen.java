package it.bncf.magazziniDigitali.utils.password;
import java.util.Random;

public class PassGen{

    public static String generateSessionKey(){
    	return generateSessionKey (16);
    }
    
    public static String generateSessionKey (int length) {
        String pass = "";
        String dCase = "abcdefghijklmnopqrstuvwxyz";
        String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String intChar = "0123456789";
        Random r = new Random();

        while (pass.length () != length){
            int rPick = r.nextInt(3);
            if (rPick == 0){
                int spot = r.nextInt(25);
                pass += dCase.charAt(spot);
            } else if (rPick == 1) {
                int spot = r.nextInt (25);
                pass += uCase.charAt(spot);
            } else if (rPick == 2) {
                int spot = r.nextInt (9);
                pass += intChar.charAt (spot);
            }
        }
        return pass;
    }
}