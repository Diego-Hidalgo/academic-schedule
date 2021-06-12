package model;

import java.io.Serializable;

public abstract class Person implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String name;
    private String lastName;

    /**
     * Constructor of the Person class. Creates a new Person object.<br>
     *     <b>pre:</b>
     *     <b>post:</b> a new Person object has been created.
     */
    public Person() {
        name = new String();
        lastName = new String();
    }//End Constructor1

    /**
     * Constructor of the Person class. Creates a new Person object.<br>
     *     <b>pre:</b>
     *     <b>post:</b> a new Person object has been created.
     * @param name the name of the person
     * @param lastName the last name of the person
     */
    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }//End Constructor2

    /**
     * returns the name of the person.<br>
     *     <b>pre:</b>
     *     <b>post:</b> the name of the person
     */
    public String getName() {
        return this.name;
    }//End getName

    /**
     * changes the name of the person<br>
     *     <b>pre:</b>
     *     <b>post:</b> the name has been changed
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }//End setName

    /**
     * returns the last name of the person<br>
     *     <b>pre:</b>
     *     <b>post:</b> the last name of the person
     */
    public String getLastName() {
        return this.lastName;
    }//End getLastName

    /**
     * changes the last name of the person<br>
     *     <b>pre:</b>
     *     <b>post:</b> the last name has been changed
     * @param lastName the new last name of the person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }//End setLastName

}//End Person
