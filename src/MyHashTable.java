public class MyHashTable<K, V> {
    private class HashNode<K, V>{
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;
    public MyHashTable(){
        //the default constructor with the default size of 11
        chainArray = new HashNode[M];
    }
    public MyHashTable(int M){
        //the constructor takes an integer and sets M to the give value
        this.M = M;
        chainArray = new HashNode[M];
    }
    private int hash(K key){
        //calculate the hash code for a provided key and then return the index that corresponds to the resulting hash code
        int hashCode = key.hashCode();
        int index = Math.abs(hashCode) % M;
        return index;
    }
    //involves either inserting a new key-value pair into a collection or modifying the value associated
    public void put(K key, V value){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        //Determine whether the key already exist
        while(head != null){
            if(head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        //instantiate a new HashNode object and insert it at the beginning of the linked list
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }
    //describes the action of retrieving the value that is associated with a particular key in a hash table
    public V get(K key){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];

        //If the key is present in the hash table, the method will retrieve the corresponding value and return it
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }
    // remove a key-value pair from the hash table
    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> prev = null;

        //If the key is found, the associated value is removed from the linked list and returned,
        // and the size of the hash table is decreased by 1
        while(head != null) {
            if (head.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = head.next;
                }
                else{
                    prev.next = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }
    //determines whether a particular value exists within the hash table or not
    public boolean contains(V value){
        //involves traversing through every linked list that is stored in the hash table and verifying by inspecting each node
        // if the given value is present in each node of the linked list.
        for(int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if(head.value.equals(value)) { //checks if the value field of the node is equal to the given value
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }
    public K getKey(V value){
        //The method loops through all the elements in the hash table by iterating over each index in the array
        //For each index, it starts with the first node of the linked list
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) { //checks if its value is equal to the given value
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }
    public void printBucketSizes(){
        int[] sizes = new int[M];
        for (int i = 0; i < M; i++) {
            HashNode<K,V> node = chainArray[i];
            while (node != null) {
                sizes[i]++;
                node = node.next;
            }
        }
        for (int i = 0; i < M; i++) {
            System.out.println("Bucket " + i + ": " + sizes[i]);
        }
    }
    public void remove(K key, V oldvalue, V newValue) {
        int index = hash(key);
        HashNode node = chainArray[index];
        while (node != null) {
            if (node.value.equals(oldvalue)) {
                node.value = newValue;
                break;
            }
            if (!node.value.equals(oldvalue)) {
                System.err.println("it   is not value of this hashtable");
                break;
            }
            node = node.next;
        }
    }
}
