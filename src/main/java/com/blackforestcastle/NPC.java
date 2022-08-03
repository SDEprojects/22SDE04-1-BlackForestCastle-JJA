package com.blackforestcastle;

class NPC extends Character {

    private String name;
    private String desc;
    private boolean isFriendly;

    //keep empty json reader will overwrite
    public NPC(){
    }
    public NPC(String theName){
        setName(theName);
    }
    public NPC(String theName, String theDesc){
        this(theName);
        setDesc(theDesc);
    }

    @Override
    public void attack(Character player) {
        int damageDone = getAttackPower() + randomNumber(10,1);
        player.setHP(player.getHP()-damageDone);
        System.out.println("The enemy did " + damageDone + " damage. Your health is now " + player.getHP());

    }
    //adds a random item to the NPC inventory
    public void addRandomItemToInventory(){
        JSONReader jsonReader = new JSONReader();
        //retrieve all items possible from json file
        Item[] allItems = jsonReader.getItems();
        //add one random item pulled from the allItems list generated based on the size of the list
        super.getInventory().add(allItems[super.randomNumber(allItems.length,0)]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
