package DynamicArray;

public class DynamicArray{
    private int size = 0;
    private int capacity = 5;
    private Object[] array;
 
    DynamicArray(){
        this.array = new Object[capacity];
    }
    DynamicArray(int capacity){
        this.capacity = capacity;
        this.array = new Object[capacity];
    }
    // to Clone an array
    DynamicArray(DynamicArray other){ 
        this.capacity = other.capacity;
        this.size = other.size;
        this.array = new Object[capacity];

        for(int i = 0; i < size; i++){
            this.array[i] = other.array[i];
        }
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

    void set(int index, Object element){
        array[index] = element;
    }

    int capacity(){ return capacity; }

    int size(){ return size; }

    // For saving memory
    void resize(){

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

    void clear(){

        int tempSize = size;
        for(int i = 0; i < tempSize; i++){
            array[i] = '\n';
            size--;
        }
    }

    boolean contains(Object element){

        for(int i = 0; i < size; i++){
            if(array[i] == element){
                return true;
            }
        }
        return false;
    }

    int indexOf(Object element){
        for(int i = 0; i < size; i++){
            if(array[i] == element){
                return i;
            }
        }
        return -1;
    }

    void reverse(){

        for(int i = 0; i < size / 2; i++){
            Object temp = array[i];
            array[i] = array[size - i - 1];
            array[size - i - 1] = temp;
        }
    }

    // Customizing the default string value
    @Override
    public String toString(){

        String string = "";

        if(size == 0){
            return "Empty Array";
        }
        string += "[";

        for(int i = 0; i < size; i++){
            string += array[i] + ", ";
        }
        string = string.substring(0, string.length() - 2) + "]";
        
        return string ;
    }

}