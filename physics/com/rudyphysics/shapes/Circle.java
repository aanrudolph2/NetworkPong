package com.rudyphysics.shapes;

public class Circle
{
	public int x, y;
	public int radius;
	public int theta;
	public Circle(int x, int y, int radius)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	public boolean intersects(Rectangle collisionBox)
	{
		for(int i = 0; i < 360; i ++)
		{
			if(Math.sin(i*Math.PI/180)*radius + y >= collisionBox.y
			&& Math.sin(i*Math.PI/180)*radius + y <= collisionBox.y + collisionBox.height
			&& Math.cos(i*Math.PI/180)*radius + x >= collisionBox.x
			&& Math.cos(i*Math.PI/180)*radius + x <= collisionBox.x + collisionBox.width)
			{
				return true;
			}
		}
			
		return false;
	}
	public boolean intersects(Circle collisionBox)
	{
		for(int i = 0; i < 360; i ++)
		{
			if(Math.sin(i*Math.PI/180)*radius + y >= Math.sin(i*Math.PI/180)*collisionBox.radius + collisionBox.y
			&& Math.sin(i*Math.PI/180)*radius + y <= Math.sin(i*Math.PI/180)*collisionBox.radius + collisionBox.y
			&& Math.cos(i*Math.PI/180)*radius + x >= Math.sin(i*Math.PI/180)*collisionBox.radius + collisionBox.x
			&& Math.cos(i*Math.PI/180)*radius + x <= Math.sin(i*Math.PI/180)*collisionBox.radius + collisionBox.x)
			{
				return true;
			}
		}
		return false;
	}
	public boolean intersects(Line collisionBox)
	{
		return false;
	}
}
