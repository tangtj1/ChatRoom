package cn.tangtj.chatroom.utils;

import java.util.Random;

public final class StringUtils {

    public static String randomStr(final int length){
        String allCharacter = "qwertyuiopasdfghjklzxcvbnm1029384576";
        StringBuilder codeStr = new StringBuilder();
        Random rd = new Random();
        for (int i = 0, j = allCharacter.length(); i < length; i++) {
            codeStr.append(allCharacter.charAt(rd.nextInt(j)));
        }
        return codeStr.toString();
    }
}
