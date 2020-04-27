package unsafe;

/**
 * 类说明：UNSAFETest
 *
 * @author zhucj
 * @since 20200423
 */
public class UnsafeTest {

    private static final sun.misc.Unsafe UNSAFE;

    private static final long USER_AGE_OFFSET;

    private static final long USER_NAME_OFFSET;

    static {
        try {
            Class<?> clazz = User.class;
            UNSAFE = UnsafeFactory.getUnsafe();
            assert UNSAFE != null;
            USER_AGE_OFFSET = UNSAFE.objectFieldOffset(clazz.getDeclaredField("age"));
            USER_NAME_OFFSET = UNSAFE.objectFieldOffset(clazz.getDeclaredField("name"));

        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(UnsafeTest.USER_AGE_OFFSET);
        System.out.println(UnsafeTest.USER_NAME_OFFSET);
    }

    static class User {

        private int age;

        private String name;
    }
}
