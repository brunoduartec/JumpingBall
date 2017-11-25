using System;
using System.Collections;
using System.Collections.Generic;

namespace Scripts.Behaviours
{

    public class BehaviourLibrary
    {
        private Dictionary<String, IPlayerBehaviour> behaviourLibrary = new Dictionary<String, IPlayerBehaviour>();

        public BehaviourLibrary()
        {
            behaviourLibrary.Add("Jump", new JumpPlayerBehaviour());
            behaviourLibrary.Add("Walk", new WalkPlayerBehaviour());
        }

        public IPlayerBehaviour getBehaviorByName(String behaviorName)
        {
            return behaviourLibrary[behaviorName];
        }

    }
}