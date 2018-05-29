package cn.tangtj.chatroom;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tang
 */
@Data
public class AjaxBodyMap {
    private boolean isOk;
    private Map<String,Object> body;

    public AjaxBodyMap(){
        body = new HashMap<>();
    }

    public void addBody(String key,Object value){
        body.put(key,value);
    }
}
