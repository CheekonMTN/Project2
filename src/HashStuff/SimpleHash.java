package HashStuff;
import java.util.LinkedList;

public class SimpleHash {
    // array of linked lists to store our data - each index is a bucket
    public static LinkedList<String>[] data;
    // how many buckets we have - starts at 16
    public static int capacity = 16;
    // how many items we have stored total
    public static int size = 0;

    // sets up our hash map with initial values
    public SimpleHash() {
        this.capacity = capacity;
        this.size = 0;
        init();
    }

    // creates our array of empty linked lists
    @SuppressWarnings("unchecked")
    private void init() {
        data = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            data[i] = new LinkedList<>();
        }
    }

    // super basic hash function - just uses string length
    // this means all strings of same length go to same bucket
    public int dumbHash(String str) {
        return str.length() % capacity;
    }

    // adds a string to our hash map
    // resizes if we're getting too full (75% capacity)
    public void add(String str) {
        if ((double) size / capacity >= 0.75) {
            resize();
        }
        int index = dumbHash(str);
        if (!data[index].contains(str)) {
            data[index].add(str);
            size++;
        }
    }

    // checks if a string exists in our hash map
    // uses dumbHash to find which bucket to look in
    public boolean contains(String str) {
        int index = dumbHash(str);
        return data[index].contains(str);
    }

    // doubles capacity and redistributes all items
    // this keeps our buckets from getting too full
    @SuppressWarnings("unchecked")
    public void resize() {
        capacity *= 2;
        LinkedList<String>[] oldData = data;
        data = new LinkedList[capacity];
        size = 0;
        for (int i = 0; i < capacity; i++) {
            data[i] = new LinkedList<>();
        }
        for (LinkedList<String> list : oldData) {
            for (String str : list) {
                add(str);
            }
        }
    }

    // prints out how many collisions are in each bucket
    // collision = when multiple items hash to same bucket
    public void collisions() {
        System.out.println("Collisions:");
        for(int i = 0; i < capacity; i++) {
            int collisions = data[i].size() - 1;
            if(collisions > 0) {
                System.out.printf("Index %d: %d collisions\n", i, collisions);
            }
        }
    }
}