package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 类说明：UnsafeFactory
 *
 * @author zhucj
 * @since 20200423
 */
public class UnsafeFactory {

    private static Unsafe INSTANCE;

    public static Unsafe getUnsafe() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        synchronized (UnsafeFactory.class) {
            if (INSTANCE == null) {
                INSTANCE = reflectGetUnsafe();
            }
        }
        return INSTANCE;
    }

    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }
}
