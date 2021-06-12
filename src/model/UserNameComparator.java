package model;

import java.util.Comparator;

public class UserNameComparator implements Comparator<User> {

    /**
     * Returns a number that represents a comparison made by String.compareTo.<br>
     *     <b>pre:</b> the params are not null
     *     <b>post:</b> a number that represents the comparison
     * @param u1 the first user to compare
     * @param u2 the user to compare
     */
    @Override
    public int compare(User u1, User u2) {
        return u1.getUserName().compareTo(u2.getUserName());
    }//End compare

}//End UserNameComparator
