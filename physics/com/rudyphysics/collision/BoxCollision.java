package com.rudyphysics.collision;

import java.awt.Point;

import com.rudyphysics.shapes.Rectangle;
import com.rudyphysics.world.RudyCollisionWorld;

/**
 * Allows for box/rectangle-based collision
 * Must implement a callback function
 * @author Aaron N. Rudolph
 *
 */
public abstract class BoxCollision extends CollisionObject
{
	public Rectangle collisionBox;
	/**
	 * @param world the Collision World into which this BoxCollision will be added
	 * @param xy the Point where this object will originate
	 * @param w the Width of the object's collision box
	 * @param h the Height of the object's collision box
	 */
	public BoxCollision(RudyCollisionWorld world, Point xy, int w, int h)
	{
		collisionBox = new Rectangle(xy);
		collisionBox.width = w;
		collisionBox.height = h;
		myWorld = world;
		myWorld.AddObject(this);
		id = myWorld.myObjects.size() - 1;
	}
	/**
	 * moves the object while checking for collision.
	 * if a collision occurs, the index of the collided object
	 * is returned.
	 * 
	 * @param x the direction and magnitude of the X movement
	 * @param y the direction and magnitude of the Y movement
	 */
	@Override
	public int move(int x, int y)
	{
		collisionBox.x += x;
		collisionBox.y += y;
		return getHitObject();
	}
	public int rotate(int theta)
	{
		return getHitObject();		
	}
}