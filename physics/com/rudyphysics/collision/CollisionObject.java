package com.rudyphysics.collision;

import com.rudyphysics.world.RudyCollisionWorld;

public abstract class CollisionObject
{
	RudyCollisionWorld myWorld;
	public int id;
	
	public int getHitObject()
	{
		for(int i = 0; i < myWorld.myObjects.size(); i ++)
		{
			if(!myWorld.myObjects.elementAt(i).equals(this))
			{
				if(myWorld.myObjects.elementAt(i) instanceof BoxCollision)
				{
					if(this instanceof BoxCollision)
					{
						if(((BoxCollision)myWorld.myObjects.elementAt(i)).collisionBox.intersects(((BoxCollision)this).collisionBox))
							return i;
					}
					if(this instanceof SphereCollision)
					{
						if(((BoxCollision)myWorld.myObjects.elementAt(i)).collisionBox.intersects(((SphereCollision)this).collisionBox))
							return i;
					}
					if(this instanceof LineCollision)
					{
						if(((BoxCollision)myWorld.myObjects.elementAt(i)).collisionBox.intersects(((LineCollision)this).collisionBox))
							return i;
					}
				}
				if(myWorld.myObjects.elementAt(i) instanceof SphereCollision)
				{
					if(this instanceof BoxCollision)
					{
						if(((SphereCollision)myWorld.myObjects.elementAt(i)).collisionBox.intersects(((BoxCollision)this).collisionBox))
							return i;
					}
					if(this instanceof SphereCollision)
					{
						if(((SphereCollision)myWorld.myObjects.elementAt(i)).collisionBox.intersects(((SphereCollision)this).collisionBox))
							return i;
					}
					if(this instanceof LineCollision)
					{
						if(((SphereCollision)myWorld.myObjects.elementAt(i)).collisionBox.intersects(((LineCollision)this).collisionBox))
							return i;
					}
				}
				if(myWorld.myObjects.elementAt(i) instanceof LineCollision)
				{
					if(this instanceof SphereCollision)
					{
						if(((LineCollision)myWorld.myObjects.elementAt(i)).collisionBox.intersects(((SphereCollision)this).collisionBox))
							return i;
					}
					if(this instanceof BoxCollision)
					{
						if(((LineCollision)myWorld.myObjects.elementAt(i)).collisionBox.intersects(((BoxCollision)this).collisionBox))
							return i;
					}
					if(this instanceof LineCollision)
					{
						if(((LineCollision)myWorld.myObjects.elementAt(i)).collisionBox.intersects(((LineCollision)this).collisionBox))
							return i;
					}
				}
			}
		}
		return -1;
	}
	/**
	 * Moves Object while checking for collision
	 * @param x X movement (integer, +/-)
	 * @param y Y movement (integer, +/-)
	 * @return ID of the object that was hit
	 */
	public abstract int move(int x, int y);
	/**
	 * Rotates Object while checking for collision.
	 * @param theta Angle to rotate the object
	 * @return ID of the object that was hit
	 */
	public abstract int rotate(int theta);
	/**
	 * Called with UpdateSimulation()
	 */
	public abstract void callback();
}