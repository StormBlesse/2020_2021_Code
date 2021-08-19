public class ExamPrep<T> {
    T[] backingArray = (T[]) new Object[9];
    int size = 0;
    public void addAtIndex(int index, T data) {
        if (size + 1 > backingArray.length) {
            T[] temp = backingArray;
            backingArray = (T[]) new Object[backingArray.length * 2];
            int t = 0;
            for (int i = 0; t < size; i++) {
                if (i == index) {
                    backingArray[i] = data;
                    i++;
                }
                backingArray[i] = temp[t];
                t++
            }


        }
    }

    public T removeAtIndex(int index) {
        int t = 0;
        T data = backingArray[index];
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return data;
    }
}
