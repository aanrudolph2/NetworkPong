package com.rudyphysics.collision;

import java.awt.Point;

import com.rudyphysics.shapes.Circle;
import com.rudyphysics.world.RudyCollisionWorld;

public abstract class SphereCollision extends CollisionObject
{
	public Circle collisionBox;
	public SphereCollision(RudyCollisionWorld world, Point xy, int r)
	{
		collisionBox = new Circle(xy.x, xy.y, r);
		myWorld = world;
		world.AddObject(this);
		id = myWorld.myObjects.size() - 1;
	}
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
