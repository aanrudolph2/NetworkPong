package com.netpong;

import java.nio.ByteBuffer;

public class PacketBuilder
{
	byte[] packetData;
	public PacketBuilder(byte opcode, byte ... values)
	{
		packetData = new byte[(int) (Math.ceil((values.length + 2)/4.f))*4];
		ByteBuffer buf = ByteBuffer.wrap(packetData);
		buf.put(new byte[]{opcode, (byte) (Math.ceil((values.length + 2)/4.f))});
		buf.put(values);
	}
	
	public int[] getPacketData()
	{
		int[] output = new int[packetData.length/4];
		ByteBuffer buf = ByteBuffer.wrap(packetData);
		buf.asIntBuffer().get(output);
		return output;
	}
}
