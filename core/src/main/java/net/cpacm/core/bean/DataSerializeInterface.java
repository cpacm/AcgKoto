package net.cpacm.core.bean;

import java.io.IOException;

/**
 * Created by cpacm on 2015/6/25.
 */
public interface DataSerializeInterface<T> {
    /**
     * 序列化对象
     * @return
     */
    String serialize() throws IOException;

    /**
     * 反序列化
     * @param str
     * @return
     */
    T deSerialization(String str) throws IOException, ClassNotFoundException;

}
