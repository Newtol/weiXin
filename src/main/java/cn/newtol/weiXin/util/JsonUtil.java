package cn.newtol.weiXin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.org.apache.xml.internal.security.keys.content.KeyValue;

import java.util.List;
import java.util.Map;

/**
* @Author: REN
* @Description:
* @Date: Created in 1:46 2018/3/14
*/
public class JsonUtil {

    private static final SerializeConfig config;


    static {
        config = new SerializeConfig();
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer()); // 使用和json-lib兼容的日期输出格式
    }

    private static final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
            SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullStringAsEmpty // 字符类型字段如果为null，输出为""，而不是null
    };


    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    public static String toJSONNoFeatures(Object object) {
        return JSON.toJSONString(object, config);
    }



    public static Object toBean(String text) {
        return JSON.parse(text);
    }

    public static <T> T toBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    // 转换为数组
    public static <T> Object[] toArray(String text) {
        return toArray(text, null);
    }

    // 转换为数组
    public static <T> Object[] toArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }

    // 转换为List
    public static <T> List<T> toList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }

    /**
     * 将javabean转化为序列化的json字符串
     * @param keyvalue
     * @return
     */
    public static Object beanToJson(KeyValue keyvalue) {
        String textJson = JSON.toJSONString(keyvalue);
        Object objectJson  = JSON.parse(textJson);
        return objectJson;
    }

    /**
     * 将string转化为序列化的json字符串
     * @param keyvalue
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson  = JSON.parse(text);
        return objectJson;
    }

    /**
     * json字符串转化为map
     * @param s
     * @return
     */
    public static Map stringToCollect(String s) {
        Map m = JSONObject.parseObject(s);
        return m;
    }

    /**
     * 将map转化为string
     * @param m
     * @return
     */
    public static String collectToString(Map m) {
        String s = JSONObject.toJSONString(m);
        return s;
    }

    public static String listToJsonArray(List list){
        String s = JSONArray.toJSONString(list);
        return s;
    }

//    public static void main(String[] args) throws SQLException, JSONException {
//        JSONObject jsonObject =new JSONObject();
//        jsonObject.put("classId",2);
//        jsonObject.put("groupId",3);
//        jsonObject.put("num",5);
//        jsonObject.put("openId","aaa");
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add(jsonObject);
//        JSONObject jsonObject1 =new JSONObject();
//        jsonObject1.put("classId",2);
//        jsonObject1.put("groupId",7);
//        jsonObject1.put("num",5);
//        jsonObject1.put("openId","bbb");
//        jsonArray.add(jsonObject1);
//        String str = jsonArray.toString();
//        List<user_assistance> user_assistanceList = toList(str, user_assistance.class);
//        AssistanceService assistanceService = new AssistanceServiceImp();
//        assistanceService.updateCheerAssistance(user_assistanceList);
//        System.out.println(toJSONString(toList(str, user_assistance.class)));
//    }


}
