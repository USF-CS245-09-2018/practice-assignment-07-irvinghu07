/**
 * Development IDE: IntelliJ IDEA
 * Author: irving
 * Project Name: cs245-assignment-07
 * Date: 2018-10-25
 */
public class ArrayList implements List {
    private static final int DEFAULT_SIZE = 10;

    private int currentSize;

    private int maxSize;

    private Object[] data;

    public ArrayList(int size) {
        this.maxSize = size;
        this.data = new Object[size];
    }

    public ArrayList() {
        this(DEFAULT_SIZE);
    }

    private boolean isValidPosition(int pos) {
        return pos < this.getCurrentSize() && 0 <= pos;
    }

    private void checkingEnoughSpace() {
        if (!enoughSpace()) {
            doubleListSize();
        }
    }

    private void checkingNonNull(Object obj) {
        if (null == obj) {
            throw new NullPointerException("cannot append a null object into ArrayList");
        }
    }

    public void printArray() {
        StringBuffer stringBuffer = new StringBuffer("[");
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (null != this.getData()[i]) {
                stringBuffer.append(String.format("%s", data[i]));
                if (i + 1 != this.getCurrentSize()) {
                    stringBuffer.append(" ,");
                }
            }
        }
        stringBuffer.append("]");
        System.out.println(stringBuffer.toString());
    }


    private void doubleListSize() {
        Object[] newData = new Object[2 * maxSize];
        maxSize *= 2;
        System.arraycopy(this.getData(), 0, newData, 0, this.getCurrentSize());
        this.setData(newData);
    }

    private boolean enoughSpace() {
        return this.getCurrentSize() + 1 != this.getMaxSize();
    }

    @Override
    public void add(Object obj) {
        checkingNonNull(obj);
        checkingEnoughSpace();
        this.getData()[currentSize++] = obj;
    }


    @Override
    public void add(int pos, Object obj) {
        checkingNonNull(obj);
        checkingEnoughSpace();
        for (int i = pos; i < this.getCurrentSize(); i++) {
            data[i + 1] = data[i];
        }
        data[pos] = obj;
        this.setCurrentSize(this.getCurrentSize() + 1);
    }

    @Override
    public Object get(int pos) {
        if (!isValidPosition(pos)) {
            throw new RuntimeException("cannot insert element into array with index less than 0 or greater than arraySize");
        }
        return this.data[pos];
    }

    @Override
    public Object remove(int pos) {
        if (!isValidPosition(pos)) {
            throw new RuntimeException("cannot delete element into array with index less than 0 or greater than arraySize");
        }
        Object placeHolder = this.getData()[pos];
        for (int i = this.getCurrentSize(); i > pos; i--) {
            this.getData()[i - 1] = this.getData()[i];
        }
        this.setCurrentSize(this.getCurrentSize() - 1);
        return placeHolder;
    }

    @Override
    public int size() {
        return this.getCurrentSize();
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}


