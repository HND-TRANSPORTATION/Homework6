//package Homework6_Linked_List;
//import Homework6.KeyedElement;
//import Homework6.KeyedElementInterface;

import java.util.Iterator;


public class LinkedList<I extends KeyedElementInterface<K>, K>  implements ListInterface<I, K>{

	private LinkedListNode<I> head;
	private LinkedListNode<I> tail;
	private int size;
	
	static class LinkedListNode<I> {
		LinkedListNode<I> next;
		LinkedListNode<I> prev;
		I data;
		
		public LinkedListNode() {
			this.data = null;
			this.next = null;
			this.prev = null;
		}
		
		public LinkedListNode(I data) {
			this.next = null;
			this.prev = null;
			this.data = data;
		}
		
		public I getData() {
			return data;
		}
		public void setData(I data) {
			this.data = data;
		}
		public LinkedListNode<I> getNext() {
			return next;
		}
		public void setNext(LinkedListNode<I> next) {
			this.next = next;
		}
	}
	
	@Override
	public Iterator<I> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public ListInterface<I, K> copy() {
		LinkedList<I, K> copy = new LinkedList<>();
		if (isEmpty()) return copy;
		
		LinkedListNode<I> copyCurrent = copy.head;
		LinkedListNode<I> current = this.head;
		
//		while (current != null) {
//			current = current.next;
//			copyCurrent = copyCurrent.next;
//		}
		
		copy.size = this.size();
		
		return copy;
	}

	@Override
	public I add(I data) {
		if (data == null || data.getKey() == null) return null;
		
		LinkedListNode<I> newNode = new LinkedListNode<>(data);
		
		if (isEmpty()) {
			this.head = newNode;
			this.tail = newNode;
		}
		
		else {
			K key = data.getKey();
			I element = get(key);
			if (element != null) { // Checks if the parameter data already exists in the LinkedList!
				
				I toBeRemoved = null;
				
				if (size == 1) { // if there's only one node in the LinkedList!
					toBeRemoved = this.head.getData();
					this.head = newNode;
					this.tail = newNode;
				}
				else if (isHead(key)) { // if its the head & there's more than one node in the LinkedList!
					toBeRemoved = this.head.getData();
					LinkedListNode<I> next = this.head.next;
					this.head = newNode;
					next.prev = newNode;
					newNode.next = next;
				}
				else if (isTail(key)) { // if its the tail & there's more than one node in the LinkedList!
					toBeRemoved = this.tail.getData();
					LinkedListNode<I> prev = this.tail.prev;
					this.tail = newNode;
					prev.next = newNode;
					newNode.prev = prev;
				}
				else { // if size >= 3 & it's in the middle of the LinkedList!
					LinkedListNode<I> node = findNode(key);
					toBeRemoved = node.getData();
					node.prev.next = newNode;
					newNode.prev = node.prev;
					node.next.prev = newNode;
					newNode.next = node.next;
				}
				return toBeRemoved;
			}
			else { // else if the node is not in the LinkedList yet, so we just add to the back of the LinkedList!
				if (size == 1) { // if size == 1, then change the tail pointer and the head.next pointer to the new node!
					this.head.next = newNode;
					newNode.prev = this.head;
					this.tail = newNode;
				}
				else { // if size >= 2, we just add to the back of the LinkedList & change the tail pointer to the new node!
					this.tail.next = newNode;
					newNode.prev = this.tail;
					this.tail = newNode;
				}
			}
		}
		
		this.size++;
		return null;
	}

	@Override
	public I get(K key) {
		if (key == null || isEmpty()) return null;
		
		LinkedListNode<I> currNode = head;
		
		while (currNode != null) {
			I data = currNode.getData();
			if (data == null) return null;
			K currKey = data.getKey();
			
			if (key.equals(currKey)) {
				return data;
			}
			
			currNode = currNode.next;
		}
		
		return null;
	}

	@Override
	public I replace(I data) {
		if (data == null) return null;
		
		K key = data.getKey();
		LinkedListNode<I> foundNode = findNode(key);
		
		if (foundNode == null) return null;
		
		foundNode.setData(data);
		
		return foundNode.getData();
	}

	@Override
	public I remove(K key) {
		if (key == null || isEmpty()) return null;
		
		LinkedListNode<I> foundNode = findNode(key);
		
		if (foundNode == null) return null;
		
		I foundNodeData = foundNode.getData();
		K foundNodeKey = foundNodeData.getKey();
		
		
		if (this.size == 1 && isHead(foundNodeKey)) { // if the length is one, we're dealing with the head & tail
			this.head = null;
			this.tail = null;
		}
		
		else { // if the size >= 2
			if (isHead(foundNodeKey)) { // if we're removing the head
				LinkedListNode<I> next = this.head.next;
				next.prev = null;
				this.head = next;
			}
			else if (isTail(foundNodeKey)) { // if we're removing the tail
				LinkedListNode<I> prev = this.tail.prev;
				prev.next = null;
				this.tail = prev;
			}
			else { // if we're removing any node, but the tail or node!
				LinkedListNode<I> prev = foundNode.prev;
				LinkedListNode<I> next = foundNode.next;
				prev.next = next;
				next.prev = prev;
			}
		}
		
		
		this.size --;
		
		return foundNodeData;
	}

	@Override
	public void removeAll() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	// Private Methods
	
	private boolean isHead(K key) {
		I headData = this.head.getData();
		K headKey = headData.getKey();
		return headKey == key;
	}
	
	private boolean isTail(K key) {
		I tailData = this.tail.getData();
		K tailKey = tailData.getKey();
		return tailKey == key;
	}
	
	private LinkedListNode<I> findNode(K key) {
		if (key == null || isEmpty()) return null;
		
		LinkedListNode<I> currNode = head;
		
		while (currNode != null) {
			I data = currNode.getData();
			K currKey = data.getKey();
			
			if (key == currKey) {
				return currNode;
			}
			
			currNode = currNode.next;
		}
		
		return null;
	}


//	public static void main(String[] args) {
//		LinkedList<KeyedElement<String>, String> test = new LinkedList<>();
//		KeyedElement<String> key = new KeyedElement<>("1"); 
//		KeyedElement<String> key1 = new KeyedElement<>("2");
//		KeyedElement<String> key2 = new KeyedElement<>("3");
//		KeyedElement<String> key3 = new KeyedElement<>(null);
//		
//		
//		test.add(key);
//		test.add(key1);
//		test.add(key2);
//		
//		test.add(null);
//		System.out.println(test.get(key3.getKey()));
//		System.out.println("Node 1: " + key);
//		System.out.println("Node 2: " + key1);
//		System.out.println("Node 3: "+ key2);
//		System.out.println("Node 4: " + key3);
//		System.out.println("-------");
//		
//		System.out.println("To me removed: " + test.remove(key1.getKey()));
//		System.out.println("-------");
//		
//		LinkedListNode<KeyedElement<String>> currNode = test.head;
//		
//		int count = 1;
//		while (currNode != null) {
//			System.out.print("Node " + count + " | " + "data: " + currNode.getData());
//			System.out.print(" | key: " + currNode.getData().getKey());
//			System.out.println();
//			currNode = currNode.next;
//			count++;
//		}
//	}

}
