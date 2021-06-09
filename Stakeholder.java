
package com.mycompany.sibusisokubalo218316038;

/**
 *
 * @author Sibusiso Kubalo 218316038
 * Date 08 June 2021
 */

import java.io.Serializable;

/**
 *
 * @author BURGERR
 */
public class Stakeholder implements Serializable{
    private String stHolderId;

    public Stakeholder() {
    }
    
    public Stakeholder(String stHolderId) {
        this.stHolderId = stHolderId;
    }
    
    public String getStHolderId() {
        return stHolderId;
    }

    public void setStHolderId(String stHolderId) {
        this.stHolderId = stHolderId;
    }

    @Override
    public String toString() {
       return stHolderId;
    }

}
