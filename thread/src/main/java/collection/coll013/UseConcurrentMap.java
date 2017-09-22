package collection.coll013;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一个 ConcurrentHashMap 有多个 Segment， 每个段相当于一个 HashTable，有自己的锁，这样锁的粒度小了，并发就高了
 */
public class  UseConcurrentMap {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
		chm.put("k1", "v1");
		chm.put("k2", "v2");
		chm.put("k3", "v3");
		chm.putIfAbsent("k4", "vvvv");
		//System.out.println(chm.get("k2"));
		//System.out.println(chm.size());
		
		for(Map.Entry<String, Object> me : chm.entrySet()){
			System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
		}
	}
}
