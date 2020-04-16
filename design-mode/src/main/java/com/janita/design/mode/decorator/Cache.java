package com.janita.design.mode.decorator;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public interface Cache {

    String getId();

    Object getObject(String key);

    void putObject(String key, Object o);

    void removeObject(String key);

    int getSize();

    void clear();
}
