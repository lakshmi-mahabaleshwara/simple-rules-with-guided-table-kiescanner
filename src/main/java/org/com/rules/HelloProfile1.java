package org.com.rules;

/**
 * Created by lmahabaleshwara on 1/6/17.
 */
public class HelloProfile1 {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;
    private String lastName;

    public HelloProfile1(){}

    public HelloProfile1(String firstName, String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
