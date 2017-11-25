using System;
using UnityEngine;

namespace Scripts.Behaviours
{
    public class JumpPlayerBehaviour : IPlayerBehaviour
    {
        public float jumpSpeed = 10;
        public override void handler(object sender, EventArgs e)
        {
            
   //         body.AddForce(new Vector3(0, jumpSpeed, 0), ForceMode.Impulse);
 //           isGrounded = false;
//            directionJump = true;
        }

        public JumpPlayerBehaviour() 
        {
            this.behaviorGestureType = GESTURETYPE.TAP;
        }

    }
}