package com.netpong.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.netpong.Packet;
import com.netpong.PacketBuilder;

public class PacketBuilderTest {

	@Test
	public void testSinglePacket()
	{
		PacketBuilder b = new PacketBuilder(Packet.OPCMD_UP, (byte)0x00, (byte)0x0F);
		
		int[] data = b.getPacketData();
		
		assertEquals(1, data.length);
		assertEquals(0x0101000F, data[0]);
	}
	@Test
	public void testExtendedPacket()
	{
		PacketBuilder b = new PacketBuilder(Packet.OPCMD_DOWN, (byte)0x00, (byte)0x0F, (byte)0x05);
		
		int[] data = b.getPacketData();
		
		assertEquals(2, data.length);
		assertEquals(0x0202000F, data[0]);
		assertEquals(0x05000000, data[1]);
	}

}
