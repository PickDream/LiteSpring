package site.maoxin.litespring.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maoxin
 * @ClassName MessageTracker
 * @date 4/9/2019
 */
public class MessageTracker {
    private static List<String> MESSAGES = new ArrayList<String>();
    public static void addMsg(String msg){
        MESSAGES.add(msg);
    }
    public static void clearMsgs(){
        MESSAGES.clear();
    }
    public static List<String> getMsgs(){
        return MESSAGES;
    }
}
