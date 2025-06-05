package DynamicArray;

public class DynamicArray{
    private int size;
    private int capacity;
    private Object[] array;

    DynamicArray(){
        this.array = new Object[capacity];
    }
    DynamicArray(Object array, int capacity){
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    int capacity(){ return capacity; }
    int size(){ return size; }

    void add(){

    }
    void remove(){

    }
    void insert(){

    }
    void get(){
    }
    String string(){
        String string = "hello";
        return string;
    }

}