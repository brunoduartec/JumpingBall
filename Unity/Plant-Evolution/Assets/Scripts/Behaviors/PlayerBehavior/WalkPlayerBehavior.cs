using TouchScript.Gestures;
using UnityEngine;
namespace Scripts.Behaviours
{
    public class WalkPlayerBehaviour : IPlayerBehaviour
    {
        #region PRIVATE
            private Vector3 direction;
            
            private enum MOVIMENT{
                IDLE,
                MOVING
            };

            private MOVIMENT playerMoviment = MOVIMENT.IDLE;

            private bool movementStarted = false;

        #endregion

        public override void handler(object sender, System.EventArgs e)
        {
            Rigidbody body = Player.playerBody;
            ScreenTransformGesture gesture = (ScreenTransformGesture)sender;

            Vector3 currentSwipe = gesture.DeltaPosition;
            Vector2 swipeDirection;

            //swipe upwards
            if (currentSwipe.x > 0 && currentSwipe.y < 0)
            {
                swipeDirection = new Vector2(1, 0);
            }
            else if (currentSwipe.x < 0 && currentSwipe.y > 0)
            {
                swipeDirection = new Vector2(-1, 0);
            }
            else if (currentSwipe.x > 0 && currentSwipe.y > 0)
            {
                swipeDirection = new Vector2(0, 1);
            }
            else
            {
                swipeDirection = new Vector2(0, -1);
            }

            direction = new Vector3(swipeDirection.x, 0, swipeDirection.y);
            Vector3 movement = direction * playerInfo.speed;


           // if (playerMoviment == MOVIMENT.IDLE)
           // {
            //    movementStarted = true;
             //   playerMoviment = MOVIMENT.MOVING;
           // }

           // if (movementStarted && body)
            {
                body.AddForce(movement, ForceMode.Impulse);
                //body.transform.position += movement;
                Quaternion rotation = Quaternion.LookRotation(direction, Vector3.up);
                body.transform.rotation = rotation;
           //     movementStarted = false;
            }
        }

        public WalkPlayerBehaviour()
        {
            this.behaviorGestureType = GESTURETYPE.TRANSFORM;
        }
    }
}