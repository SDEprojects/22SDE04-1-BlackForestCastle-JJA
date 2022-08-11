package com.blackforestcastle;

class Settings
{
    private int volume =  32;
    private final int MAX_VOLUME = 100;
    private final int MIN_VOLUME = 0;

    // TODO: 8/3/22 add constructor 
    
    public int getVolume()
    {
        return volume;
    }
    public void setVolume(int volume)
    {
        if(isValidVolume(volume))
            this.volume = volume;
        else System.out.println("Volume not changed.");
    }

    private boolean isValidVolume(int volume)
    {
        if(volume <= MAX_VOLUME && volume >= MIN_VOLUME)
            return true;
        return false;
    }
}