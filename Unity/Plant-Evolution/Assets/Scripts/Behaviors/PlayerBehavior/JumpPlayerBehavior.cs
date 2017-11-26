using System;
using UnityEngine;

namespace Scripts.Behaviours
{
    public class JumpPlayerBehaviour : IPlayerBehaviour
    {
        public float jumpSpeed = 10;
        public override void handler(object sender, EventArgs e)
        {
            Rigidbody body = Player.getPlayer();
            body.AddForce(new Vector3(0, playerInfo.jumpSpeed, 0), ForceMode.Impulse);
        }

        public JumpPlayerBehaviour() 
        {
            this.behaviorGestureType = GESTURETYPE.DOUBLETAP;
        }

    }
}