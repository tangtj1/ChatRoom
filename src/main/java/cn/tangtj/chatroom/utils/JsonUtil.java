package cn.tangtj.chatroom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author tang
 */
public final class JsonUtil {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public static String obj2Json(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T json2Obj(String json, Class<T> clz) {
        try {
            return objectMapper.readValue(json, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
