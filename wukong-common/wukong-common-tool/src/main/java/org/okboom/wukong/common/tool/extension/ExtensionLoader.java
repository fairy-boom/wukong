package org.okboom.wukong.common.tool.extension;

import org.okboom.wukong.common.tool.annotation.MySPI;
import org.okboom.wukong.common.tool.exception.WukongException;

import java.util.Objects;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * SPI loader
 * @author tookbra
 */
public class ExtensionLoader<T> {

    private Class<T> type;

    private ExtensionLoader(final Class<T> type) {
        this.type = type;
    }

    private static <T> boolean withExtensionAnnotation(final Class<T> type) {
        return type.isAnnotationPresent(MySPI.class);
    }

    public static <T> ExtensionLoader<T> getExtensionLoader(final Class<T> type) {
        if (type == null) {
            throw new WukongException("type == null");
        }
        if (!type.isInterface()) {
            throw new WukongException("Extension type(" + type + ") not interface!");
        }
        if (!withExtensionAnnotation(type)) {
            throw new WukongException("type" + type.getName() + "not exist");
        }
        return new ExtensionLoader<>(type);
    }

    public T getActivateExtension(final String value) {
        ServiceLoader<T> loader = ServiceLoader.load(type);
        return StreamSupport.stream(loader.spliterator(), false)
                .filter(e -> Objects.equals(e.getClass()
                        .getAnnotation(MySPI.class).value(), value))
                .findFirst().orElseThrow(() -> new WukongException("Please check your configuration"));
    }
}
