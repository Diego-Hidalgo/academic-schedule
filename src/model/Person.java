package model;

public abstract class Person {

    private String name;
    private String lastName;

    public Person() {
        name = new String();
        lastName = new String();
    }//End Constructor1

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }//End Constructor2

    public String getName() {
        return this.name;
    }//End getName

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }//End setName

    public String getLastName() {
        return this.lastName;
    }//End getLastName

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }//End setLastName

}//End Person
