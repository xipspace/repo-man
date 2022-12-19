
# Basic Controls

Use either the Translate method or AddForce method (if using physics) to move your character

Throughout the course, we’ve created a lot of prototypes that instantiated and destroyed objects during gameplay, but there’s actually a more performant / efficient way to do that called Object Pooling

https://docs.unity3d.com/Manual/CollidersOverview.html

You can add colliders to a GameObject without a Rigidbody component to create floors, walls and other motionless elements of a Scene. These are referred to as static colliders. At the opposite, colliders on a GameObject that has a Rigidbody are known as dynamic colliders. Static colliders can interact with dynamic colliders but since they don't have a Rigidbody, they don't move in response to collisions

OnCollisionEnter() (for Rigidbody collisions) or OnTriggerEnter() (for trigger-based collisions)

## Trigger

https://docs.unity3d.com/ScriptReference/MonoBehaviour.OnTriggerEnter.html

A collider configured as a Trigger (using the Is Trigger property) does not behave as a solid object and will simply allow other colliders to pass through

	private void OnTriggerEnter(Collider other){
		if (other.gameObject.CompareTag("PowerUp")){
			hasPowerup = true;
			Destroy(other.gameObject);
		}
	}

## Collision

https://docs.unity3d.com/ScriptReference/MonoBehaviour.OnCollisionEnter.html

	private void OnCollisionEnter(Collision collision){
		if(collision.gameObject.CompareTag("Enemy")){
			print("hit " + collision.gameObject.name);
		}
	}

	void OnBecameInvisible(){
		Destroy(gameObject);
	}

## Force Mode

https://docs.unity3d.com/ScriptReference/ForceMode.html

Force: Add a continuous force to the rigidbody, using its mass.<br>
Acceleration: Add a continuous acceleration to the rigidbody, ignoring its mass.<br>
Impulse: Add an instant force impulse to the rigidbody, using its mass.<br>
VelocityChange: Add an instant velocity change to the rigidbody, ignoring its mass.<br>
