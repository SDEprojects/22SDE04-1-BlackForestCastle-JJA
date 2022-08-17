package com.blackforestcastle.JSON_Objects;

public class Item {

    private String name;
    private String description;
    private String type;
    private int power;
    private boolean isKey;


    public Item(String theName, String theDescription, String theType, int thePower) {
        setName(theName);
        setDescription(theDescription);
        if (theType.equalsIgnoreCase("key")) setKey(true);
        setType(theType);
        setPower(thePower);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setKey(boolean key) {
        isKey = key;
    }
}
