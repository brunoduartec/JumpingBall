using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Scripts.Behaviours
{
    public abstract class IPlayerBehaviour
    {
        public enum GESTURETYPE
        {
            TRANSFORM,
            TAP,
            DOUBLETAP
        };

        public GESTURETYPE behaviorGestureType
        {
            get;
            set;
        }

        public PlayerInfo playerInfo
        {
            get;
            set;
        }

        public abstract void handler(object sender, EventArgs e);
    }
}