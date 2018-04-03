package com.fernandocejas.android10.order.presentation.model;

import java.io.Serializable;

/**
 * Created by jerry on Feb-08-18.
 */

public class GroupMessageModel implements Serializable {

    private String idBuyer;
    private String nameBuyer;
    private String avatarBuyer;
    private String orderId;
    private String timeLastMessage;
    private String lastMessage;

    public String getNameBuyer() {
        return nameBuyer;
    }

    public void setIdBuyer(String idBuyer) {
        this.idBuyer = idBuyer;
    }

    public String getIdBuyer() {
        return idBuyer;
    }

    public void setNameBuyer(String nameBuyer) {
        this.nameBuyer = nameBuyer;
    }

    public String getAvatarBuyer() {
        return avatarBuyer;
    }

    public void setAvatarBuyer(String avatarBuyer) {
        this.avatarBuyer = avatarBuyer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTimeLastMessage() {
        return timeLastMessage;
    }

    public void setTimeLastMessage(String timeLastMessage) {
        this.timeLastMessage = timeLastMessage;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
