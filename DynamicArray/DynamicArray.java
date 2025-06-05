package DynamicArray;

public class DynamicArray{
    private int size = 0;
    private int capacity = 10;
    private Object[] array;

    DynamicArray(){
        this.array = new Object[capacity];
    }
    DynamicArray(int capacity){
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    int capacity(){ return capacity; }
    int size(){ return size; }

    // For saving memory
    void resize(){

        System.out.println("This works!");
        int newCapacity = 0;
        
        if (size >= capacity) {
            newCapacity = capacity * 2;
        } else if (size <= capacity / 3 && capacity > 1) {
            newCapacity = capacity / 2;
        }

        Object[] newArray = new Object[newCapacity];

        for(int i = 0; i < size; i++){
            newArray[i] = array[i];
        }
        capacity = newCapacity;
        array = newArray;
    }

    void push_back(Object element){

        if (size >= capacity){ resize(); }
        array[size] = element;
        size++;
    }

    // Inserts the element at certain index
    void insert(int index, Object element){

        if (size >= capacity) { resize(); }

        // Shifting element to the right
        for(int i = size - 1; i >= index; i--){
            array[i + 1] = array[i];
        } 
        array[index] = element;
        size++;
    }

    void remove(Object element){

        for(int i = 0; i < size; i++){
            if(array[i] == element){
                for(int j = i; j < size - 1; j++){
                    array[j] = array[j + 1];
                }
                size--;
                i--;
            }
        }
        if(size < (int)capacity / 3){ resize(); }
    }
    void remove(int index){ 

        for(int i = index; i < size; i++){
            array[i] = array[i + 1];
        } 
        array[size - 1] = null;
        size--;
        if(size < (int)capacity / 3){ resize(); }
    }

    int get(Object element){
        for(int i = 0; i < size; i++){
            if(element == array[i]){
                return i;
            }
        }
        return -1;
    }

    // Customizing the default string value
    @Override
    public String toString(){

        String string = "[";

        for(int i = 0; i < size; i++){
            string += array[i] + ", ";
        }
        string = string.substring(0, string.length() - 2);
        
        return string + "]";
    }

}