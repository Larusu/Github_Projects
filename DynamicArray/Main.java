package DynamicArray;

public class Main{
    public static void main(String[] args) {

        DynamicArray example = new DynamicArray(10); // Capacity 10
 
        example.push_back("A"); 
        example.push_back("B"); 
        example.push_back("C");
        example.push_back("J"); 
        example.push_back("D"); 
        example.push_back("E"); 
        example.push_back("G"); 
        example.push_back(2);
        example.insert(6, "J"); 
        example.insert(8, "J"); 


        System.out.println("Size: " + example.size());
        System.out.println("Capacity: " + example.capacity());
        System.out.println("Find element \"G\" at index: " + example.get("G"));
        System.out.println(example + "\n");

        System.out.println("Removing: J");
        example.remove("J");
        System.out.println("Size after removal: " + example.size());
        System.out.println("Capacity after removal : " + example.capacity());
        System.out.println(example);

        example.reverse();
        System.out.println("\nReverse: " + example);

        DynamicArray newArray = new DynamicArray(example);
        
        example.clear();
        System.out.println("\nSize after clear: " + example.size());
        System.out.println(example);

        System.out.println("\nCloned new object 'New Array': " + newArray);
        newArray.reverse();
        System.out.println("Reverse 'New Array': " + newArray);
    }
}
