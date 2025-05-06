package HashStuff;

import java.util.LinkedList;

public class BetterHash {
    private LinkedList<String>[] data;
    private int size = 0;
    private int capacity;

    public BetterHash() {
        this.capacity = 16;
        init();
    }

    // Initializes buckets
    @SuppressWarnings("unchecked")
    private void init() {
        data = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            data[i] = new LinkedList<>();
        }
    }

    // Hash function similar to Rabin-Karp from DSA 2
    // Performs better than our DumbHash from class
    private int hash(String str) {
        int hash = 0;
        int prime = 31;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash * prime + str.charAt(i)) % capacity;
        }
        return Math.abs(hash);
    }

    // Adds a string to the table
    // Resizes if over 75% full
    public void add(String str) {
        if ((double) size / capacity >= 0.75) {
            resize();
        }
        int index = hash(str);
        if (!data[index].contains(str)) {
            data[index].add(str);
            size++;
        }
    }

    // Checks if string exists
    public boolean contains(String str) {
        int index = hash(str);
        return data[index].contains(str);
    }

    // Doubles capacity and redistributes
    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        LinkedList<String>[] oldData = data;
        init();
        size = 0;

        for (LinkedList<String> bucket : oldData) {
            for (String str : bucket) {
                add(str); // Re-hash into new buckets
            }
        }
    }
    public int getCollisions() {
        int total = 0;
        for (LinkedList<String> bucket : data) {
            if (bucket != null && bucket.size() > 1) {
                total += bucket.size() - 1;
            }
        }
        return total;
    }
    // Prints bucket collisions
    public void printCollisions() {
        System.out.println("Collisions:");
        for (int i = 0; i < capacity; i++) {
            int collisions = data[i].size() - 1;
            if (collisions > 0) {
                System.out.printf("Index %d: %d collisions\n", i, collisions);
            }
        }
    }

    // Prints Table
    public void printTable() {
        for (int i = 0; i < capacity; i++) {
            System.out.printf("Bucket %d: %s\n", i, data[i]);
        }
    }
}
