 /**
  * Represents a family that lives in a household together.
  * A family always has at least one person in it.
  * Families are mutable.
  */
 public class Family2 {
     // the people in the family, sorted from oldest to youngest, with no duplicates.
     private List<Person> people;
     
     /**
      * @return a list containing all the members of the family, with no duplicates.
      */
     public List<Person> getMembers() {
         return people;
     }
 }


 /*
  * Explanation

The specification of an ADT includes the name of the class (line 6), the Javadoc comment just before the class (lines 1-5), and the specifications of its public methods and fields (Javadoc lines 10-12 and method signature line 13). These parts are the contract that is visible to a client of the class.

The representation of an ADT consists of its fields (line 8) and any assumptions or requirements about those fields (line 7).

The implementation of an ADT consists of the method implementations that manipulate its rep (line 14).

Submit
  */