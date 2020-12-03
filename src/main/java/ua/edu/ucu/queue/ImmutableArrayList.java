package ua.edu.ucu.queue;


import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {
    private final Object[] array;
    private int arraySize;

    public ImmutableArrayList() {
        this.array = new Object[0];
        this.arraySize = 0;
    }

    public ImmutableArrayList(Object [] e) {
        this.array = e.clone();
        this.arraySize = e.length;
    }

    @Override
    public ImmutableList add(Object e) {
        return add(this.arraySize, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        Object [] element = {e};
        return addAll(index, element);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(this.arraySize, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newArray = new Object[array.length + c.length];
        for (int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }

        int ind = index + c.length;
        int counter = 0;
        for (int j = index; j < ind; j++) {
            newArray[j] = c[counter];
            counter += 1;
        }

        for (int k = ind; k < array.length + c.length; k++) {
            newArray[k] = array[index];
            index++;
        }

        return new ImmutableArrayList(newArray);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
        return this.array[index];
    }

    @Override
    public ImmutableList remove(int index) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }

        Object[] newArray = new Object[arraySize-1];
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != index && counter <= newArray.length) {
                newArray[counter] = array[i];
                counter ++;
            }
        }
        return new ImmutableArrayList(newArray);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index < 0 || index > array.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newArray = array.clone();
        newArray[index] = e;
        return new ImmutableArrayList(newArray);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public Object[] toArray() {
        Object [] result = this.array.clone();
        return result;
    }

    @Override
    public String toString(){
        return Arrays.toString(toArray());
    }
}
