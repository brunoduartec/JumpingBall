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
            Rigidbody body = Player.getPlayer();
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

            body.AddForce(movement, ForceMode.Impulse);
            Quaternion rotation = Quaternion.LookRotation(direction, Vector3.up);
            body.transform.rotation = rotation;
        }

        public WalkPlayerBehaviour()
        {
            this.behaviorGestureType = GESTURETYPE.TRANSFORM;
        }
    }
}