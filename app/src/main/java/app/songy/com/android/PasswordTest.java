package app.songy.com.android;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Description:
 * Created by song on 2018/11/8.
 * email：bjay20080613@qq.com
 */
public class PasswordTest {
    public static void main(String[] args)
    {
        //这是加密方式
        String hashed = BCrypt.hashpw("songy123", BCrypt.gensalt());
        System.out.println(hashed);

        //这是解密方式
        if (BCrypt.checkpw("songy123", hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }
}
