package com.janita.java.base.thinkinjava._17_container.countries;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.janita.java.base.thinkinjava._17_container.countries.CountriesConstants.DATA;

/**
 * 类说明：FlyweightMap
 *
 * @author zhucj
 * @since 20200528
 */
public class FlyweightMap extends AbstractMap<String, String> {

    private static Set<Map.Entry<String, String>> entries = new EntrySetImpl(DATA.length);

    /**
     * 实现 AbstractMap 唯一的抽象函数
     *
     * @return
     */
    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        return entries;
    }

}

class EntryImpl implements Map.Entry<String, String> {

    int index;

    EntryImpl(int index) {
        this.index = index;
    }

    public boolean equals(Object o) {
        return DATA[index][0].equals(o);
    }

    public String getKey() {
        return DATA[index][0];
    }

    public String getValue() {
        return DATA[index][1];
    }

    public String setValue(String value) {
        throw new UnsupportedOperationException();
    }

    public int hashCode() {
        return DATA[index][0].hashCode();
    }
}

/**
 * 2个抽象方法
 *
 * public abstract Iterator<E> iterator();
 *
 * public abstract int size();
 */
// Use AbstractSet by implementing size() & iterator()
class EntrySetImpl extends AbstractSet<Map.Entry<String, String>> {

    private int size;

    EntrySetImpl(int size) {
        if (size < 0) {
            this.size = 0;
        } else if (size > DATA.length) {// Can't be any bigger than the array:
            this.size = DATA.length;
        } else {
            this.size = size;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return new EntrySetImpl.Iter();
    }

    private class Iter implements Iterator<Map.Entry<String, String>> {

        // Only one Entry object per Iterator:
        private EntryImpl entry = new EntryImpl(-1);

        @Override
        public boolean hasNext() {
            return entry.index < size - 1;
        }

        @Override
        public Map.Entry<String, String> next() {
            entry.index++;
            return entry;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
