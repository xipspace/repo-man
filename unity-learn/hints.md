
# Workflow

game manager :
empty object + script

ux : canvas >
button > onclick + game manager + function

scene management

	using UnityEngine.SceneManagement;
	...
	public void RestartGame(){
		SceneManager.LoadScene(SceneManager.GetActiveScene().name);
	}
	
spawn : distribute all objects before pooling

# Basic Controls

Use either the Translate method or AddForce method (if using physics) to move your character

It is actually much easier to increase the size of a list at runtime than it is for an array

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

## Easy Quit

		if (Input.GetKey(KeyCode.Escape))
		{
			Application.Quit();
		#if UNITY_EDITOR
			UnityEditor.EditorApplication.isPlaying = false;
		#endif
		}

## Object Pooling

object pooling >
https://learn.unity.com/tutorial/introduction-to-object-pooling

	if (Input.GetKeyDown(KeyCode.Space)){
		
		// No longer necessary to Instantiate prefabs
		// Instantiate(projectilePrefab, transform.position, projectilePrefab.transform.rotation);

		// Get an object object from the pool
		GameObject pooledProjectile = ObjectPooler.SharedInstance.GetPooledObject();
		
		if (pooledProjectile != null){
			pooledProjectile.SetActive(true); // activate it
			pooledProjectile.transform.position = transform.position; // position it at player
		}
	}

## Physics Best Practices

Establish what should collide with what.<br>For that you should define different Layers for each type of object.
<br>For each new layer, a new row and column is added on the Collision Matrix.
<br>This matrix is responsible for defining interactions between layers. By default, when adding a new layer, the Collision Matrix is set for that new layer to collide with every other existing one, so it’s the developer’s responsibility to access it and setup its interactions.
<br>By correctly setting layers and setting up your Collision Matrix, you will avoid unnecessary collisions and testing on collision listeners.