package com.janita.java.base.thinkinjava._17_container;

import com.janita.java.base.thinkinjava._17_container.countries.Countries;
import com.janita.java.base.thinkinjava.util.MapEntry;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * 类说明：SimpleHashMap
 *
 * @author zhucj
 * @since 20200528
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {

    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;

    // You can't have a physical array of generics,
    // but you can upcast to one:
    //您没有物理上的泛型数组，但可以转型得到
    //该链表数组中存储了该map的值跟建
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        //取摸，保证下标在数组中
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode) % SIZE;
        if (buckets[index] == null) {
            //如果该下标中还没存数据，则初始化该下标对应的链表
            buckets[index] = new LinkedList<MapEntry<K, V>>();
        }
        //获取该下标对应的桶
        LinkedList<MapEntry<K, V>> linkedListAsBucket = buckets[index];
        //数据载体
        MapEntry<K, V> pair = new MapEntry<K, V>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> listIterator = linkedListAsBucket.listIterator();
        while (listIterator.hasNext()) {
            MapEntry<K, V> iPair = listIterator.next();
            if (iPair.getKey().equals(key)) {//看看该key是不是已经存在了
                oldValue = iPair.getValue();//如果存在，获取旧值
                listIterator.set(pair); // Replace old with new
                found = true;
                break;
            }
        }
        if (!found) {
            //如果该key之前不存在，则直接添加到链表的最后
            buckets[index].add(pair);
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        //取摸，保证下标在数组中
        int index = Math.abs(key.hashCode()) % SIZE;
        LinkedList<MapEntry<K, V>> linkedListAsBucket = buckets[index];
        if (linkedListAsBucket == null) {
            return null;
        }
        for (MapEntry<K, V> iPair : linkedListAsBucket) {
            if (iPair.getKey().equals(key)) {
                return iPair.getValue();
            }
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entryHashSet = new HashSet<Entry<K, V>>();
        for (LinkedList<MapEntry<K, V>> linkedListAsBucket : buckets) {
            if (linkedListAsBucket == null) {
                continue;
            }
            entryHashSet.addAll(linkedListAsBucket);
        }
        return entryHashSet;
    }

    public static void main(String[] args) {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<String, String>();
        Map<String, String> capitalsMap = Countries.capitals(25);
        simpleHashMap.putAll(capitalsMap);
        System.out.println(simpleHashMap);
        System.out.println(simpleHashMap.get("ERITREA"));
        System.out.println(simpleHashMap.entrySet());
    }
} /* Output:
{CAMEROON=Yaounde, CONGO=Brazzaville, CHAD=N'djamena, COTE D'IVOIR (IVORY COAST)=Yamoussoukro, CENTRAL AFRICAN REPUBLIC=Bangui, GUINEA=Conakry, BOTSWANA=Gaberone, BISSAU=Bissau, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, ERITREA=Asmara, THE GAMBIA=Banjul, KENYA=Nairobi, GABON=Libreville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, EQUATORIAL GUINEA=Malabo, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, GHANA=Accra, DJIBOUTI=Dijibouti, ETHIOPIA=Addis Ababa}
Asmara
[CAMEROON=Yaounde, CONGO=Brazzaville, CHAD=N'djamena, COTE D'IVOIR (IVORY COAST)=Yamoussoukro, CENTRAL AFRICAN REPUBLIC=Bangui, GUINEA=Conakry, BOTSWANA=Gaberone, BISSAU=Bissau, EGYPT=Cairo, ANGOLA=Luanda, BURKINA FASO=Ouagadougou, ERITREA=Asmara, THE GAMBIA=Banjul, KENYA=Nairobi, GABON=Libreville, CAPE VERDE=Praia, ALGERIA=Algiers, COMOROS=Moroni, EQUATORIAL GUINEA=Malabo, BURUNDI=Bujumbura, BENIN=Porto-Novo, BULGARIA=Sofia, GHANA=Accra, DJIBOUTI=Dijibouti, ETHIOPIA=Addis Ababa]
*///:~

class StringHashCode {

    public static void main(String[] args) {
        String[] hellos = "Hello Hello".split(" ");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());
    }
}