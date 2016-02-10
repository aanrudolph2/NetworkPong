package com.rudyphysics.shapes;

import java.awt.Point;

public class Line
{
	public Point a, b;
	public float m;
	public float intercept;
	public Line(Point a, Point b)
	{
		this.a = a;
		this.b = b;
		m = (float) ((b.getY() - a.getY())/(b.getX() - a.getX()));
		intercept = (-m*a.x + a.y);
	}
	public boolean intersects(Circle collisionBox)
	{
		for(int i = 0; i < 360; i ++)
		{
			if((int)(Math.sin(i*Math.PI/180)*collisionBox.radius + collisionBox.y) == (int)(m*(Math.cos(i*Math.PI/180)*collisionBox.radius + collisionBox.x) + intercept)
				&& Math.sin(i*Math.PI/180)*collisionBox.radius + collisionBox.y <= b.y
				&& Math.cos(i*Math.PI/180)*collisionBox.radius + collisionBox.x >= a.x
				&& Math.cos(i*Math.PI/180)*collisionBox.radius + collisionBox.x <= b.x)
			{
				return true;
			}
		}
		return false;
	}
	public boolean intersects(Rectangle collisionBox)
	{
		for(int i = a.x; i < b.x; i ++)
		{
			if(i >= collisionBox.x && i <= collisionBox.x + collisionBox.width
				&& intercept + m*i >= collisionBox.y && intercept + m*i <= collisionBox.y + collisionBox.height)
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
			if(i*m + intercept == i*collisionBox.m + collisionBox.intercept)
			{
				return true;
			}
		}
		return false;
	}
}
