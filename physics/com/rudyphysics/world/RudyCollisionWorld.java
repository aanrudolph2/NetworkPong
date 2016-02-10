package com.rudyphysics.world;
import java.util.Stack;

import com.rudyphysics.collision.*;
/**
 * Collision World
 * Contains all collision objects
 * @author Rudolph.A197
 *
 */
public class RudyCollisionWorld
{
	public Stack<CollisionObject> myObjects = new Stack<CollisionObject>();
	
	/**
	 * Adds an object to the Collision World
	 * Requires a Collision Object
	 * @param o the CollisionObject to be added
	 */
	public void AddObject(CollisionObject o)
	{
		myObjects.push(o);
	}
	/**
	 * Updates Simulation
	 * Recommended this function be called each render cycle
	 */
	public void UpdateSimulation()
	{
		for(int i = 0; i < myObjects.size(); i ++)
		{
			myObjects.elementAt(i).callback();
		}
	}
}
