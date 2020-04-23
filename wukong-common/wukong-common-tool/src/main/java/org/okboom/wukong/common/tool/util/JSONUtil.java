package org.okboom.wukong.common.tool.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * jackson util
 * @author tookbra
 */
public class JSONUtil {

    public static final ObjectMapper mapper = new ObjectMapper();


    /**
     * json to list
     * @param json
     * @param classType
     * @param <E>
     * @return
     */
    public static <E> List<E> parseList(String json, Class<E> classType) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, classType));
        } catch (IOException e) {
            return null;
        }
    }
}
