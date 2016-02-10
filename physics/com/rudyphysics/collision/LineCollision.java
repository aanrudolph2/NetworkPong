package com.rudyphysics.collision;

import java.awt.Point;

import com.rudyphysics.shapes.Line;
import com.rudyphysics.world.RudyCollisionWorld;

/**
 * Allows for box/rectangle-based collision
 * Must implement a callback function
 * @author Aaron N. Rudolph
 *
 */
public abstract class LineCollision extends CollisionObject
{
	public Line collisionBox;

	public LineCollision(RudyCollisionWorld world, Point a, Point b)
	{
		collisionBox = new Line(a, b);
		myWorld = world;
		myWorld.AddObject(this);
		id = myWorld.myObjects.size() - 1;
	}
	@Override
	public int move(int x, int y)
	{
		collisionBox.a.x += x; collisionBox.b.x += x;
		collisionBox.a.y += y; collisionBox.b.y += y;
		collisionBox.m = (float) ((collisionBox.b.getY() - collisionBox.a.getY())/(collisionBox.b.getX() - collisionBox.a.getX()));
		collisionBox.intercept = (-collisionBox.m*collisionBox.a.x + collisionBox.a.y);
		return getHitObject();
	}
	public int rotate(int theta)
	{
		return getHitObject();
	}
}