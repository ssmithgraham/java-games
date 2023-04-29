package snake;

public class snakeList<T> {
	
	private Node head;
	private int size;
	
	public snakeList() {
		this.head = null;
		this.size = 0;
	}
	public int size() {
		return size;
	}
	
	public void addFirst(T v) {
		Node newNode = new Node(v);
		newNode.next = head;
		head = newNode;
		size++;
	}
	public void addLast(T v) {
		Node newNode = new Node(v);
		Node temp = head;
		for(int i = 0; i < size - 1; i++) {
			temp = temp.next;
		}
		temp.next = newNode;
		size++;
	}

	
	private class Node {
		private T value;
		private Node next;
		
		public Node(T v) {
			this.value = v;
			this.next = null;
		}
	}

}
