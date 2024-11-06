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