package org.com.rules;

/**
 * Created by lmahabaleshwara on 1/6/17.
 */
public class Recommendation {
    private String offerId;

    public Recommendation() {
    }

    public Recommendation(String offerId){
        this.offerId = offerId;
    }
    public String getOfferId()
    {
        return this.offerId;
    }

    public void setOfferId(String offerId)
    {
        this.offerId = offerId;
    }
}
