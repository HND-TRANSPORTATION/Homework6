package Homework6_Linked_List;

import Homework6.KeyedElementInterface;

/**
 *
 *  This is the interface for creating a <b>list</b> of elements
 * 
 * @author Sameh Fakhouri
 *
 * @param <E>	This parameter is a generic for the type of <b>elements</b> that will be stored in the list.
 * 				Please note that all <b>elements</b> must implement the <b>KeyedElement</b> interface.
 * 				If the <b>elements</b>, themselves, do not implement the <b>KeyedElement</b> interface,
 * 				a super-class of <b>element</b> must implement the <b>KeyedElement</b> interface.
 * @param <K>	This parameter is a generic for the type of <b>key</b> each element must have. 
 * 				
 */


public interface ListInterface<E extends KeyedElementInterface<K>, K> extends Iterable<E>{
	
	

	/**
	 * This method is called to obtain the count of elements in the list.
	 * 
	 * @return 	Returns the numbers of <b>Element</b>s that are currently in the list.
	 */
	
	public int size();

	
	
	/**
	 * This method is called to determine if the list is empty.
	 * 
	 * @return 	Returns <b>true</b> if the list is empty, otherwise it returns <b>false</b>. 
	 */
	
	public boolean isEmpty();

	/**
	 * 
	 * This method will return a deep copy of the current instance of the LinkedList. This involves
	 * creating a new instance of the LinkedList and then duplicating all the Nodes and Elements
	 * from the current instance.
	 * 
	 * @return A copy of the current instance of the LinkedList.
	 * 
	 */
	public ListInterface<E, K> copy();
	
	/**
	 * This method is called to add the specified Element to the end of the list. If an
	 * <b>element</b> with the same <b>key</b> already exists in the list, it must be removed
	 * so that you cannot have multiple elements with the same key in the list.
	 * 
	 * @param 	element	A reference to the element to be added to the end of the list.
	 * 					All Elements being added to the list must implement the 
	 * 					<b>KeyedElement</b> interface.
	 * 
	 * @return	If this add displaces a previous <b>element</b> with the same <b>key</b>, the method
	 * 			will return the replaced <b>element</b>, otherwise, the method returns <b>null</b>
	 * 
	 * @see		KeyedElementInterface
	 */
	public E add(E element);
	
	
	
	/**
	 * This method is called to retrieve the Element at the specified index.
	 * 
	 * @param 	key		The key of the element being retrieved
	 * 
	 * @return	Returns a reference to the <b>element</b> with the given <b>key</b>, or <b>nullL</b>
	 * 			if an <b>element</b> with the given <b>key</b> is not found.
	 */
	public E get(K key);
		
	
	
	/**
	 * 
	 * This method will use the <b>key</b> of the specified <b>element</b> to find an already existing member of the
	 * <b>LinkedList</b> and will replace the existing <b>element</b> with the specified one.
	 * 
	 * @param element	A reference to the new <b>element</b> that will replace the current <b>element</b>
	 * 					in the list.
	 * 
	 * @return		If the replacement is successful, the method will return a reference to the replaced
	 * 				element. Otherwise, the method will return <b>null</b>, indicating that no replacement took place.
	 */
	public E replace(E element);

	
	
	/**
	 * This method is called to remove the Element at the specified index
	 * 
	 * @param 	key		Indicates the <b>key</b> of the <b>element</b> to remove.
	 * 
	 * @return	Returns the <b>element</b> that was successfully removed, 
	 * 			or <b>null</b> if an <b>element</b> with the specified <b>key</b> was not found.
	 */
	public E remove(K key);

	
	
	/**
	 * This method removes all Elements from the list, making the list empty.
	 */
	public void removeAll();
	
}
