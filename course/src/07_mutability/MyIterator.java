/**
* A MyIterator is a mutable object that iterates over
* the elements of an ArrayList<String>, from first to last.
* This is just an example to show how an iterator works.
* In practice, you should use the ArrayList's own iterator
* object, returned by its iterator() method.
*/
public class MyIterator {
private final ArrayList<String> list;
private int index;
// list[index] is the next element that will be returned
// by next()
// index == list.size() means no more elements to return
/**
* Make an iterator.
* @param list list to iterate over
*/
public MyIterator(ArrayList<String> list) {
this.list = list;
this.index = 0;
}
/**
* Test whether the iterator has more elements to return.
* @return true if next() will return another element,
* false if all elements have been returned
*/
public boolean hasNext() {
return index < list.size();
}
/**
* Get the next element of the list.
* Requires: hasNext() returns true.
* Modifies: this iterator to advance it to the element
* following the returned element.
* @return next element of the list
*/
public String next() {
final String element = list.get(index);
++index;
return element;
}
}
