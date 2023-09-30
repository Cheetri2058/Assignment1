
import java.util.*;
public class MyDoublyLinkedList<E> extends MyAbstractSequentialList<E> implements
        Cloneable {
    private Node<E> head = new Node<E>(null);
    /** Create a default list */
    public MyDoublyLinkedList() {
        head.next = head;
        head.previous = head;
    }
    private static class Node<E> {
        E element;
        Node<E> previous;
        Node<E> next;
        public Node(E element) {
            this.element = element;
        }
    }
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<E> current = head.next;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != head) {
                result.append(", "); // Separate two elements with a comma
            }
        }
        result.append("]"); // Insert the closing ] in the string
        return result.toString();
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        if (index < size / 2)
            for (int i = -1; i < index; i++)
                current = current.next;
        else
            for (int i = size; i > index; i--)
                current = current.previous;
        return current;
    }
    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size:" + size);
        }
        Node<E> prev = getNode(index - 1);
        Node<E> next = prev.next;
        Node<E> newNode = new Node<E>(e);
        prev.next = newNode;
        next.previous = newNode;
        newNode.previous = prev;
        newNode.next = next;
        size++;
    }
    @Override
    public void clear() {
// TODO Auto-generated method stub
    }
    @Override
    public boolean contains(E o) {
        for (Node<E> current = head.next; current != head; current =
                current.next) {
            E e = current.element;
            if (o == null ? e == null : o.equals(e))
                return true;
        }
        return false;
    }

    // Overriding the clone method from the Object class
    @Override
    protected  MyDoublyLinkedList clone() {
        MyDoublyLinkedList cloned1;
        try {
            //cloned the super object into cloned 1
            cloned1 =   (MyDoublyLinkedList)super.clone();

            // Now pointing the cloned head into new Dummy head node
            cloned1.head = new Node(null);
            cloned1.head.next = cloned1.head;
            cloned1.head.previous = cloned1.head;
            cloned1.size= 0;
            Node current =  head.next;
            while(current != head) {
                cloned1.add(current.element);
                current =  current.next;
            }
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
        return cloned1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == (obj)) {
            return true;
        }
        else if (!(obj instanceof MyList<?>)) {
            return false;
        }
        else if (this.size != ((MyDoublyLinkedList<?>) obj).size) {
            return false;
        }
        else {
            Iterator<E> thisIterator = this.iterator();
            Iterator<MyDoublyLinkedList> objIterator = (Iterator<MyDoublyLinkedList>) ((MyDoublyLinkedList<?>) obj).listIterator(0);
            while (thisIterator.hasNext() && objIterator.hasNext()) {
                E thisElement = thisIterator.next();
                MyDoublyLinkedList objElement =  objIterator.next();
                if(thisElement ==  null && objElement == null) {
                    return false;
                }
                else if (!thisElement.equals(objElement)) {
                    return false;
                }
            }

        }
        return true;
    }
    @Override
    public E get(int index) {
// TODO Auto-generated method stub
        return null;
    }
    @Override
    public int indexOf(E e) {
// TODO Auto-generated method stub
// Note: Make sure that you check the equality with == for null objects and with the equals() for others
        return 0;
    }
    @Override
    public int lastIndexOf(E e) {
// TODO Auto-generated method stub
        return 0;
    }
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        E removedElement = null;

        if (index == 0) {
            removedElement = removeFirst(); // Call the removeFirst method for removal
        } else if (index == size - 1) {
            removedElement = removeLast(); // Call the removeLast method for removal
        } else {
            // Find the node at the specified index
            Node<E> prevNode = getNode(index - 1);
            Node<E> currentNode = prevNode.next;
            Node<E> nextNode = currentNode.next;

            // Update the references to remove the current node
            prevNode.next = nextNode;
            nextNode.previous = prevNode;

            // Decrement the size of the list
            size--;

            // Return the removed element
            removedElement = currentNode.element;
        }

        return removedElement;
    }
    @Override
    public Object set(int index, E e) {
// TODO Auto-generated method stub
        return null;
    }
    @Override
    public E getFirst() {
// TODO Auto-generated method stub
        return null;
    }
    @Override
    public E getLast() {
// TODO Auto-generated method stub
        return null;
    }
    @Override
    public void addFirst(E e) {
        add(0, e);
    }
    @Override
    public void addLast(E e) {
        add(this.size,e);
    }
    @Override
    public E removeFirst() {
// TODO Auto-generated method stub
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        } else {
            Node<E> firstNode = head.next;
            E elementToRemove = firstNode.element;
            Node<E> newFirstNode = firstNode.next;

            // Update the new first node's previous reference
            newFirstNode.previous = head;

            // Update the head's next reference
            head.next = newFirstNode;

            size--;

            return elementToRemove;
        }
    }
    @Override
    public E removeLast() {
// TODO Auto-generated method stub
        if (size == 0) {
            throw new NoSuchElementException("The list is empty");
        } else if (size == 1) {
            // Only one element in the list
            E elementToRemove = head.next.element;
            head.next = head;
            head.previous = head;
            size = 0;
            return elementToRemove;
        } else {
            Node<E> lastNode = head.previous;
            E elementToRemove = lastNode.element;
            Node<E> newLastNode = lastNode.previous;

            // Update the new last node's next reference
            newLastNode.next = head;

            // Update the head's previous reference
            head.previous = newLastNode;

            size--;

            return elementToRemove;
        }

    }


    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyDoublyLinkedListIterator(index);
    }
    private static enum ITERATOR_STATE {
        CANNOT_REMOVE, CAN_REMOVE_PREV, CAN_REMOVE_CURRENT
    };
    private class MyDoublyLinkedListIterator implements ListIterator<E> {
        private Node<E> current; // node that holds the next element in the
        // iteration
        private int nextIndex; // index of current
        ITERATOR_STATE iterState = ITERATOR_STATE.CANNOT_REMOVE;
        private MyDoublyLinkedListIterator(int index) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("iterator index out of bounds");
                        current = getNode(index);
            nextIndex = index;
        }
        @Override
        public void add(E arg0) {
// TODO Auto-generated method stub
        }
        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }
        @Override
        public boolean hasPrevious() {
// TODO Auto-generated method stub
            return false;
        }
        @Override
        public E next() {
            if (nextIndex >= size)
                throw new NoSuchElementException();
            E returnVal = current.element;
            current = current.next;
            nextIndex++;
            iterState = ITERATOR_STATE.CAN_REMOVE_PREV;
            return returnVal;
        }
        @Override
        public int nextIndex() {
// TODO Auto-generated method stub
            return 0;
        }
        @Override
        public E previous() {
// TODO Auto-generated method stub
            return null;
        }
        @Override
        public int previousIndex() {
// TODO Auto-generated method stub
            return 0;
        }
        @Override
        public void remove() {
            switch (iterState) {
                case CANNOT_REMOVE:
                    throw new IllegalStateException("Cannot remove element at this position");
                case CAN_REMOVE_PREV:
                    // Remove the previous node
                    Node<E> prevNode = current.previous;
                    Node<E> nextNode = current;
                    prevNode.next = nextNode;
                    nextNode.previous = prevNode;

                    // Update the index of the next element
                    nextIndex--;

                    // Move the current pointer to the previous node
                    current = prevNode;

                    // Adjust the size
                    size--;

                    // Update the iterator state
                    iterState = ITERATOR_STATE.CANNOT_REMOVE;
                    break;

                case CAN_REMOVE_CURRENT:
                    // Remove the current node
                    Node<E> prev = current.previous;
                    Node<E> next = current.next;
                    prev.next = next;
                    next.previous = prev;
                    // Move the current pointer to the next node
                    current = next;
                    // Adjust the size
                    size--;
                    // Update the iterator state
                    iterState = ITERATOR_STATE.CANNOT_REMOVE;
                    break;
            }
        }
        @Override
        public void set(E arg0) {
// TODO Auto-generated method stub
        }
    }
}
