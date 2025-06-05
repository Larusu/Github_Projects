package DynamicArray;

public class Main{
    public static void main(String[] args) {

        DynamicArray example = new DynamicArray();
 
        example.push_back("Abcd"); 
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
        System.out.println(example);
    }
}
