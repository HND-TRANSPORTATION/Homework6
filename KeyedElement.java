package Homework6;

public class KeyedElement<K> implements KeyedElementInterface<K> {

	private K key;
	
	public KeyedElement(K key) {
		this.key = key;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public KeyedElementInterface<K> copy() {
		return new KeyedElement<>(this.key);
	}

}
