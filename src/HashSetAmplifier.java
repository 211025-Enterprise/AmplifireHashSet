import java.util.HashSet;
import java.util.Iterator;

public class HashSetAmplifier<T> implements Iterator<T> {

    // java.util.HashSet;
    // buckets array
    int arrSize = 11;
    int currCount = 0;
    int lastIndex = 0;
    int iteratorCount = 0;
    T[] arr = (T[])new Object[arrSize];
    int objCount = 0;
    int junkCount = 0;
    double loadFactor = 0.65;
    T junk = (T)new Object();


    // add method
    public void add(T obj) {

        int hashIndex = hash(obj);

        //make sure bucket empty
        if(arr[hashIndex] == null) {
            arr[hashIndex] = obj;
            objCount++;
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
                    iteratorCount++;
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
                    junkCount++;
                    iteratorCount--;
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
                System.out.println("found");
                return obj;
            }
        }
        System.out.println("not found");
        return null;
    }

    // size
    public int getSize() {
        return iteratorCount;
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
        lastIndex = 0;
        objCount = objCount - junkCount;
        junkCount = 0;


    }

    // hash an object and return bucket index to be stored in
    private int hash(T obj) {
        return obj.hashCode() % arrSize;
    }


    @Override
    public boolean hasNext() {
        int count = 0;
        for(int i = 0; i < arrSize; i++) {
            if(arr[i] != null && arr[i].hashCode() != junk.hashCode()) {
                count++;
                if(count >= iteratorCount) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T next() {
        int count = 0;
        for(int i = lastIndex; i < arrSize; i++) {
            if(arr[i] != null && arr[i].hashCode() != junk.hashCode()) {
                count++;
                if(count == (currCount+1)) {
                    currCount++;
                    lastIndex = i;

                    if(currCount >= iteratorCount) {
                        currCount = 0;
                    }
                    return arr[i];
                }
            }
        }
        return null;
    }
}
