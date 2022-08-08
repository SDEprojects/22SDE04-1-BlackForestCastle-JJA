package com.blackforestcastle;

import java.util.ArrayList;
import java.util.List;

public abstract class Character {

    private int attackPower;
    private int defendPower;
    private int HP;
    //JSONReader jsonReader = new JSONReader();
    private List<Item> inventory;

    public Character(){
        setInventory(new ArrayList<Item>());
    }
    public Character(int theAttackPower){
        this();
        setAttackPower(theAttackPower);
    }
    public Character(int theAttackPower, int theDefendPower){
    this(theAttackPower);
    setDefendPower(theDefendPower);
    }


    // true random with range
    public int randomNumber(int max, int min) {
        int result = (int) ((Math.random() * (max-min))-min);
        return result;
    }

    public abstract void attack(Character character);

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefendPower() {
        return defendPower;
    }

    public void setDefendPower(int defendPower) {
        this.defendPower = defendPower;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void addAttackPower(int attackBoost){
        this.attackPower += attackBoost;
    }

    public void addDefensePoints(int defenseBoost){
        this.defendPower += defenseBoost;
    }

    public void addHP(int hpBoost){
        this.HP += hpBoost;
    }
}
