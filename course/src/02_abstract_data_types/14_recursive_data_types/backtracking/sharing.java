ImList<Integer> list1 = ImList.empty();
list1 = list1.cons(1).cons(2).cons(3); // [3, 2, 1]

ImList<Integer> list2 = list1.rest(); // [2, 1]

