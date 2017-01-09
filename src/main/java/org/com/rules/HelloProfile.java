package org.com.rules;

/**
 * Created by lmahabaleshwara on 1/6/17.
 */
public class HelloProfile {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;

    public HelloProfile(){}

    public HelloProfile(String firstName){
        this.firstName=firstName;
    }

}
