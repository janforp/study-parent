package com.janita.design.mode.decorator;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class CacheTest {

    public static void main(String[] args) throws InterruptedException {
        testLru();
        testSchedule();
        testMix();
    }

    private static void printAll(Cache cache) {
        System.out.println("===========================");
        for (int i = 0; i < 7; i++) {
            System.out.println("key = " + (i + 1) + " ， value= " + cache.getObject((i + 1) + ""));
        }
        System.out.println("===========================");
    }

    private static void testLru() {
        Cache cache = new PerpetualCache("PerpetualCache 缓存");
        Cache decoratorCache = new LruCache(cache);
        decoratorCache.putObject("1", "1");
        decoratorCache.putObject("2", "2");
        decoratorCache.putObject("3", "3");
        decoratorCache.putObject("4", "4");
        decoratorCache.putObject("5", "5");
        decoratorCache.putObject("6", "6");
        printAll(decoratorCache);
        decoratorCache.putObject("7", "7");
        printAll(decoratorCache);
        decoratorCache.getObject("3");
        decoratorCache.putObject("8", "8");
        printAll(decoratorCache);
    }

    private static void testSchedule() throws InterruptedException {
        Cache cache = new PerpetualCache("PerpetualCache 缓存");
        Cache decoratorCache = new ScheduledCache(cache, 5000L);
        decoratorCache.putObject("1", "1");
        decoratorCache.putObject("2", "2");
        decoratorCache.putObject("3", "3");
        decoratorCache.putObject("4", "4");
        decoratorCache.putObject("5", "5");
        decoratorCache.putObject("6", "6");
        printAll(decoratorCache);
        decoratorCache.putObject("7", "7");
        printAll(decoratorCache);
        decoratorCache.putObject("8", "8");
        printAll(decoratorCache);
        Thread.sleep(4000);
        printAll(decoratorCache);
        Thread.sleep(4000);
        printAll(decoratorCache);
    }

    private static void testMix() throws InterruptedException {
        Cache cache = new PerpetualCache("PerpetualCache 缓存");
        Cache scheduledCache = new ScheduledCache(cache, 5000L);
        Cache decoratorCache = new LruCache(scheduledCache);
        decoratorCache.putObject("1", "1");
        decoratorCache.putObject("2", "2");
        decoratorCache.putObject("3", "3");
        decoratorCache.putObject("4", "4");
        decoratorCache.putObject("5", "5");
        decoratorCache.putObject("6", "6");
        printAll(decoratorCache);
        decoratorCache.putObject("7", "7");
        printAll(decoratorCache);
        decoratorCache.putObject("8", "8");
        printAll(decoratorCache);
        Thread.sleep(4000);
        printAll(decoratorCache);
        Thread.sleep(4000);
        printAll(decoratorCache);
    }
}
