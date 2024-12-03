package org.example.custom_collections;

import java.util.LinkedList;

/**
 * Why LinkedList?
 * Handles collisions easily by chaining elements in the same bucket
 * Efficient add (O(1)) and acceptable search for small buckets
 */
public class CustomHashSet<T> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private LinkedList<T>[] buckets;
    private int size;

    /**
     * Constructs an empty CustomHashSet with an initial capacity.
     */
    public CustomHashSet() {
        this.buckets = new LinkedList[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Calculates the index for a given element based on its hash code.
     *
     * @param element the element whose bucket index is to be calculated
     * @return the bucket index for the element
     */
    private int getBucketIndex(T element) {
        return Math.abs(element.hashCode() % buckets.length);
    }

    public boolean add(T element) {
        if (contains(element)) return false; // Unique elements only

        if (size + 1 > buckets.length * LOAD_FACTOR) {
            resize();
        }

        int bucketIndex = getBucketIndex(element);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new LinkedList<>();
        }

        buckets[bucketIndex].add(element);
        size++;
        return true;
    }

    /**
     * Checks if the set contains the specified element.
     *
     * @param element the element to be checked for presence in the set
     * @return true if the set contains the specified element
     */
    public boolean contains(T element) {
        int bucketIndex = getBucketIndex(element);
        LinkedList<T> bucket = buckets[bucketIndex];
        return bucket != null && bucket.contains(element);
    }

    /**
     * Removes the specified element from the set if it is present.
     *
     * @param element the element to be removed from the set
     * @return true if the set contained the specified element
     */
    public boolean remove(T element) {
        int bucketIndex = getBucketIndex(element);
        LinkedList<T> bucket = buckets[bucketIndex];
        if (bucket != null && bucket.remove(element)) {
            size--;
            return true;
        }
        return false;
    }

    private void resize() {
        LinkedList<T>[] oldBuckets = buckets;
        buckets = new LinkedList[oldBuckets.length * 2];
        size = 0;

        for (LinkedList<T> bucket : oldBuckets) {
            if (bucket != null) {
                for (T element : bucket) {
                    add(element);
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public void iterate() {
        for (LinkedList<T> bucket : buckets) {
            if (bucket != null) {
                for (T element : bucket) {
                    System.out.println(element);
                }
            }
        }
    }
}
