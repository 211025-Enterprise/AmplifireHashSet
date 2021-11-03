
public class HashSetAmplifier<T> {

    // java.util.HashSet;
    // buckets array
    int arrSize = 11;
    T[] arr = (T[])new Object[arrSize];
    int objCount = 0;
    int sizeCount = 0;
    double loadFactor = 0.65;
    T junk = (T)new Object();

    // add method
    public void add(T obj) {

        int hashIndex = hash(obj);

        //make sure bucket empty
        if(arr[hashIndex] == null) {
            arr[hashIndex] = obj;
            objCount++;
            sizeCount++;
            if(((new Double(objCount)/new Double(arrSize))) > loadFactor) {
                resize();
            }
        }

        //if bucket is not empty -> linear probe
        else {
            // iterate through to end of array
            for(int i = hashIndex; i < arrSize; i++) {

                // make sure not end of array
                if(i == arrSize) {
                    i = 0;
                }
                if(arr[i] != null && arr[i].hashCode() == (obj.hashCode())) {
                    break;
                }
                // bucket is empty
                if(arr[i] == null) {
                    arr[i] = obj;
                    // object is unique
                    objCount++;
                    sizeCount++;
                    if(((new Double(objCount)/new Double(arrSize))) > loadFactor) {
                        resize();
                    }
                    break;
                }
            }
        }

    }

    // remove (optional)
    public T remove(T obj) {
        T returnObj = null;
        int hashIndex = hash(obj);
        if(arr[hashIndex] == null) {
            return null;
        }

        for(int i = hashIndex; i < arrSize; i++) {
            if(i == arrSize) {
                i = 0;
            }
            if(arr[i] != null) {
                if(arr[i].hashCode()==obj.hashCode()) {
                    returnObj = arr[i];
                    arr[i] = junk;
                    sizeCount--;
                }
            }

        }
        return returnObj;
    }

    // get
    public T get(T obj) {
        int hashIndex = hash(obj);
        // not found, return null
        if(arr[hashIndex] == null) {
            return null;
        }
        // found return object
        for(int i = hashIndex; i < arrSize; i++) {
            if(i == arrSize) {
                i = 0;
            }
            if(arr[i] == null) {
                break;
            }
            if (arr[i].hashCode() == obj.hashCode()) {
                //System.out.println("found");
                return obj;
            }
        }
        //System.out.println("not found");
        return null;
    }

    // size
    public int getSize() {
        return sizeCount;
    }

    // resize array load factor
    private void resize() {
        // generate new prime number larger than current arrSize
        int newSize = (((arrSize+1)/2)*6)+1;

        T[] newArr = (T[])new Object[newSize];

        // copy all elements to bigger size array
        for(int i = 0; i < arrSize; i++) {

            // make sure non null element
            if(arr[i] != null && arr[i].hashCode() != junk.hashCode()) {

                int newHashIndex = arr[i].hashCode() % newSize;

                // in case of non-collision
                if (newArr[newHashIndex] == null) {
                    newArr[newHashIndex] = arr[i];
                } else {

                    // in case of collision, linear probing... again
                    for (int j = newHashIndex; j < arrSize; j++) {
                        if (j == newSize) {
                            j = 0;
                        }
                        if (newArr[j] == null) {
                            newArr[j] = arr[i];
                        }
                    }
                }
            }
        }

        arr = newArr;
        arrSize = newSize;


    }

    // hash an object and return bucket index to be stored in
    private int hash(T obj) {
        return obj.hashCode() % arrSize;

    }
    
    /*
    @Override
    public String toString() {
        System.out.println("HASH - ");
        return null;
    }
    */


}
