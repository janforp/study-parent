package collection.coll013;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 最好在 读多写少的 场景下应用
 */
public class UseCopyOnWrite {

	public static void main(String[] args) {

		/**
		 * 多个写操作不能并发，是加锁的，读是不加锁的
		 * 再高并发的读都没事情，但是 ConcurrentMap 最多16个并发
		 */
		CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
		CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();
	}
}
