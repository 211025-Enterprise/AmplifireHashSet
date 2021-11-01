public class HashSetAmplifier<T> {

    // java.util.HashSet;
    // buckets array
    int arrSize = 11;
    T[] arr = (T[])new Object[arrSize];
    int objCount = 0;
    double loadFactor = 0.65;

    // add method
    public void add(T obj) {
        int hashIndex = hash(obj);

        //make sure bucket empty
        if(arr[hashIndex] == null) {
            arr[hashIndex] = obj;
        }

        //if bucket is not empty -> linear probe
        else {
            // iterate through to end of array
            for(int i = hashIndex; i < arrSize; i++) {

                // make sure not end of array
                if(i == arrSize) {
                    i = 0;
                }
                // bucket is empty
                if(arr[i] == null) {
                    arr[i] = obj;
                    // object is unique
                    if(obj.hashCode() != arr[i].hashCode()) {
                        objCount++;
                        if(objCount/arrSize > loadFactor) {
                            resize();
                        }
                    break;
                    }
                }
            }
        }
    }

    // remove (optional)
    // public T remove(T obj) {return null;}

    // contains
    public boolean contains(T obj) {

        return false;
    }

    // get
    public T get(T obj) {
        int hashIndex = hash(obj);
        // not found, return null
        if(arr[hashIndex] == null) {
            return null;
        }
        // found return object
        int i = hashIndex;
        while(arr[i] != null) {
            if (arr[i].hashCode() == obj.hashCode()) {
                return obj;
            }
            i++;
        }
        return null;
    }

    // size
    public int getSize() {
        return objCount;
    }

    // resize array load factor
    private void resize() {
        System.out.println("RESIZE");
    }

    // hash an object and return bucket index to be stored in
    private int hash(T obj) {
        return obj.hashCode() % this.getSize();
    }

    @Override
    public String toString() {
        System.out.println("HASH - ");
        for(T t: arr) {
            //System.out.println(t.hashCode();
        }

        return null;
    }


}
