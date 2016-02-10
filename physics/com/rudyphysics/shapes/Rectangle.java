package com.rudyphysics.shapes;

import java.awt.Point;

public class Rectangle
{
	public int x, y, width, height;
	public int theta;
	public Rectangle(Point xy)
	{
		x = xy.x;
		y = xy.y;
	}
	public boolean intersects(Rectangle collisionBox)
	{
		if(collisionBox.x + collisionBox.width > x && collisionBox.y + collisionBox.height > y
				&& collisionBox.x < x + width && collisionBox.y < y + height)
		{
			return true;
		}
		return false;
	}
	public boolean intersects(Circle collisionBox)
	{
		for(int i = 0; i < 360; i ++)
		{
			if(Math.sin(i*Math.PI/180)*collisionBox.radius + collisionBox.y >= y
			&& Math.sin(i*Math.PI/180)*collisionBox.radius + collisionBox.y <= y + height
			&& Math.cos(i*Math.PI/180)*collisionBox.radius + collisionBox.x >= x
			&& Math.cos(i*Math.PI/180)*collisionBox.radius + collisionBox.x <= x + width)
			{
				return true;
			}
		}
			
		return false;
	}
	public boolean intersects(Line collisionBox)
	{
		for(int i = collisionBox.a.x; i < collisionBox.b.x; i ++)
		{
			if(i >= x && i <= x + width
				&& collisionBox.intercept + collisionBox.m*i >= y && collisionBox.intercept + collisionBox.m*i <= y + height)
			{
				return true;
			}
		}
		return false;
	}
}
