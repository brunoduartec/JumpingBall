using Scripts.Behaviours;
using System;
using System.Collections.Generic;
public class PlayerInfo{
    private int energy;
    private Dictionary<String, IPlayerBehaviour> learnedBehavior = new Dictionary<String, IPlayerBehaviour>();

    public void learnBehavior(String behaviorName, IPlayerBehaviour behavior){
        learnedBehavior.Add(behaviorName, behavior);
    }



}   