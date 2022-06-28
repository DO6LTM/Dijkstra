package main;

public class PriorityQueue {

    private Node[] queue;
    private int size;
    private int maxSize;

    public PriorityQueue(int nodes) {
        maxSize = nodes;
        size = 0;
        queue = new Node[maxSize + 1];
        queue[0] = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private int getParentIndex(int index) {return index / 2;}

    private int getLeftChildIndex(int index) {
        return (2 * index < queue.length) ? 2 * index : 1;
    }

    private int getRightChildIndex(int index) { return (2 * index + 1 < queue.length) ? 2 * index + 1 : 1; }

    private boolean isLeaf(int index) {
        if (index >= (maxSize / 2) && index <= maxSize) {
            return true;
        }
        else {
            return false;
        }
    }

    private void swap(int index1, int index2) {
        Node tmp = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = tmp;
    }

    private void heapifyDown(int index) {
        int min;
        while(getLeftChildIndex(index) <= size) {
            if(getRightChildIndex(index) > size) {
                min = getLeftChildIndex(index);
            }
            else {
                if(Node.compareWeight(queue[getLeftChildIndex(index)], queue[getRightChildIndex(index)]) < 0) {
                    min = getLeftChildIndex(index);
                }
                else {
                    min = getRightChildIndex(index);
                }
            }
            if(Node.compareWeight(queue[index], queue[min]) <= 0) {
                return;
            }
            swap(index, min);
            index = min;
        }
    }

    private void heapifyUp(int index) {
        while(index > 1 && Node.compareWeight(queue[index], queue[getParentIndex(index)]) < 0) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    public void decreaseKey(int index, int weight) {
        Node n = queue[index];
        if(n != null) {
            n.setWeight(weight);
            queue[index] = n;
            while(index > 1 && Node.compareWeight(queue[index], queue[getParentIndex(index)]) < 0) {
                swap(getParentIndex(index), index);
                index = getParentIndex(index);
            }
        }
    }

    public void insert(Node n) {
        size++;
        queue[size] = n;
        heapifyUp(size);
    }

    public Node deleteMin() {
        Node elem = queue[1];
        queue[1] = queue[size];
        queue[size] = null;
        size--;
        heapifyDown(1);
        return elem;
    }

    public void buildHeap() {
        for(int i = (size / 2); i > 0; i--) {
            heapifyDown(i);
        }
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    private int getKey(int index) {
        if(index < queue.length) {
            return (queue[index] != null) ? queue[index].getKey() : 0;
        }
        else {
            return 0;
        }
    }

    public int findKey(int key) {
        for(int i = 1; i < queue.length; i++) {
            if(queue[i] != null) {
                if(queue[i].getKey() == key) {
                    return i;
                }
            }
        }
        return -1;
    }
}
