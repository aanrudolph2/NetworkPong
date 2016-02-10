package com.netpong;

public final class Packet
{
	/*
	 * Client Operations
	 */
	public static final byte OPCMD_UP   = 0x1;
	public static final byte OPCMD_DOWN = 0x2;
	public static final byte OPCMD_STOP = 0x4;
	
	/*
	 * Server Operations
	 */
	
	public static final byte OPCMD_CONNECT = 0x1;
	public static final byte OPCMD_UPDATE_SCORE = 0x2;
	public static final byte OPCMD_UPDATE_SCREEN = 0x4;
	public static final byte OPCMD_DISCONNECT = 0x8;
	
	private byte d1, d2, d3, d4, numPackets, opcode;
	
	public Packet(int input)
	{
		opcode		= (byte)((input & 0xFF000000) >> 24);
		numPackets	= (byte)((input & 0x00FF0000) >> 16);
		d1			= (byte)((input & 0x0000FF00) >> 8);
		d2			= (byte)(input & 0x000000FF);
	}
	
	public Packet(int input, boolean first)
	{
		if(first)
		{
			opcode		= (byte)((input & 0xFF000000) >> 24);
			numPackets	= (byte)((input & 0x00FF0000) >> 16);
			d1			= (byte)((input & 0x0000FF00) >> 8);
			d2			= (byte)(input & 0x000000FF);
		}
		else
		{
			d1	= (byte)((input & 0xFF000000) >> 24);
			d2	= (byte)((input & 0x00FF0000) >> 16);
			d3	= (byte)((input & 0x0000FF00) >> 8);
			d4	= (byte)(input & 0x000000FF);
		}
	}
	
	public byte opcode() { return opcode; }
	public byte numPackets() { return numPackets; }
	public byte d1() { return d1; }
	public byte d2() { return d2; }
	public byte d3() { return d3; }
	public byte d4() { return d4; }
}
