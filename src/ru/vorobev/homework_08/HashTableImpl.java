package ru.vorobev.homework_08;

public class HashTableImpl <K, V> implements HashTable<K, V> {

    private final Item<K, V>[] data;
    private int size;

    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;
        private Item<K, V> next;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    
        private Item<K, V> getNext() {
            return next;
        }
    
        private void setNext(Item<K, V> next) {
            this.next = next;
        }
    
        @Override
        public String toString() {
            return "Item{" + "key=" + key + ", value=" + value + '}';
        }
    }

    public HashTableImpl(int initialCapacity) {
        this.data = new Item[initialCapacity];
    }

    public HashTableImpl() {
        this(16);
    }

    @Override
    public boolean put(K key, V value) {
        if (size() == data.length) {
            return false;
        }
        
        int index = hashFunc(key);
        
        if (data[index] == null) {
            data[index] = new Item<>(key, value);
        } else {
            Item<K, V> lastItem = data[index];
            while (lastItem.getNext() != null) {
                lastItem = lastItem.getNext();
            }
            
            lastItem.setNext(new Item<>(key, value));
        }
        size++;
        return true;
    }

    private boolean isKeysEquals(Item<K, V> item, K key) {
        return (item == null) ? (key == null) : (item.getKey().equals(key));
    }

    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        if (index == -1) {
            return null;
        }
        
        Item<K, V> result = data[index];
        while (result != null && !result.getKey().equals(key)) {
            result = result.getNext();
        }
        
        return result == null ? null : result.getValue();
    }

    private int indexOf(K key) {
        int index = hashFunc(key);
        
        if (isKeysEquals(data[index], key)) {
            return index;
        } else {
            return -1;
        }
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);
        if (index == -1) {
            return null;
        }

        Item<K, V> removed = data[index];
        Item<K, V> previous = data[index];
        while (!key.equals(removed.getKey())) {
            previous = removed;
            removed = removed.getNext();
            if (removed == null) {
                return null;
            }
        }
        
        V result = removed.getValue();
        
        if (removed.equals(data[index])) {
            if (removed.getNext() == null) {
                data[index] = null;
            } else {
                data[index] = removed.getNext();
                removed.setNext(null);
            }
        } else {
            previous.setNext(removed.getNext());
            removed.setNext(null);
        }
        
        size--;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Item<K, V> item;
        for (int i = 0; i < data.length; i++) {
            item = data[i];
            sb.append(String.format("%s = ", i));
            if (item == null) {
                sb.append("null");
            }
            int depth = 0;
            while (item != null) {
                sb.append(String.format("%d: [%s]", ++depth, item));
                item = item.getNext();
                if (item != null) {
                    sb.append(", ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
