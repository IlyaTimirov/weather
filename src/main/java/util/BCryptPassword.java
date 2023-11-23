package util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPassword {
    public String getBCryptPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean correctPassword(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }
}
