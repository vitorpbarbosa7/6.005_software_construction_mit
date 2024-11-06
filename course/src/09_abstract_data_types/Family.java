/**
 * Represents a family that lives in a household together.
 * A family always has at least one person in it.
 * Families are mutable.
 */
class Family {
    // the people in the family, sorted from oldest to youngest, with no duplicates.
    public List<Person> people;
    
    /**
     * @return a list containing all the members of the family, with no duplicates.
     * observer
     */
    public List<Person> getMembers() {
        return people;
    }
}



// client
void client1(Family f) {
    // get youngest person in the family
    // accessing a internal object method
    // assuming some internal behavior? 
    Person baby = f.people.get(f.people.size()-1);
    ...
}


// from list to a set:
/**
 * Represents a family that lives in a household together.
 * A family always has at least one person in it.
 * Families are mutable.
 */
class Family {
    // the people in the family
    public Set<Person> people;
    
    /**
     * @return a list containing all the members of the family, with no duplicates.
     */
    public List<Person> getMembers() {
        return new ArrayList<>(people);
    }
}



// Quetion 2
void client2(Family f) {
    // get size of the family
    int familySize = f.people.size();
    ...
}