package com.jdevelopstation.l2ce.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author VISTALL
 * @date 2:58/27.06.2011
 */
public class Test
{
	public static void main(String... arg) throws Exception
	{
		InputStream stream = new FileInputStream("W:\\_github.com\\VISTALL\\l2ce\\dist\\dec-msconditiondata.dat");
		byte[] data = new byte[stream.available()];
		stream.read(data);
		ByteBuffer buf = ByteBuffer.wrap(data);
		buf.order(ByteOrder.LITTLE_ENDIAN);

		int k = 0;
		int val = buf.getInt();
		System.out.println("SIZE: " + val);
		for(int i = 0; i < val; i++)
		{

			//			201AEACD   PUSH Engine.20826EFC                      UNICODE "skill_id"
			//			201AEB58   PUSH Engine.20826F10                      UNICODE "level"
			//			201AEBDA   PUSH Engine.20826F5C                      UNICODE "sub_level"
			//			201AEC65   PUSH Engine.20827C78                      UNICODE "equiptype"
			//			201AECF0   PUSH Engine.20827CB8                      UNICODE "attackitemtype"
			//			201AED7B   PUSH Engine.20827D1C                      UNICODE "stattype"
			//			201AEDFD   PUSH Engine.20827D6C                      UNICODE "statpercentage"
			//			201AEE8D   PUSH Engine.20827DC4                      UNICODE "up"
			//			201AEF18   PUSH Engine.20827E04                      UNICODE "hpconsume"
			//			201AEFA3   PUSH Engine.20827E5C                      UNICODE "mpconsume1"
			//			201AF02E   PUSH Engine.20827EB0                      UNICODE "mpconsume2"
			//			201AF0B9   PUSH Engine.20827EEC                      UNICODE "itemid"
			//			201AF14D   PUSH Engine.20827F20                      UNICODE "itemnum"
			//			201AF1E1   PUSH Engine.20827F74                      UNICODE "casterpriorskilllist"
			//			201AF275   PUSH Engine.20827FC8                      UNICODE "casterpriorskilllevels"
			//			201AF309   PUSH Engine.2082804C                      UNICODE "targetpriorskilllist"
			//			201AF39D   PUSH Engine.208280D0                      UNICODE "targetpriorskilllevels"
			//			201AF431   PUSH Engine.20828150                      UNICODE "ultimateconsumegauge"
			//			201AF4C5   PUSH Engine.208276D8                      UNICODE "skill_end"

			//skill_begin	skill_name = [s_triple_slash11]	/* [트리플 슬래시] */	skill_id = 1	level = 1	operate_type = A1	magic_level = 38	self_effect = {}	effect = {{i_p_attack;517;0;1}}	operate_cond = {{equip_weapon;{dual}}}	is_magic = 0	mp_consume2 = 42	cast_range = 40	effective_range = 400	skill_hit_time = 1.733	skill_cool_time = 0.167	skill_hit_cancel_time = 0.5	reuse_delay = 3	attribute = {attr_none;0}	trait = {trait_none}	effect_point = -213	target_type = enemy	affect_scope = single	affect_limit = {0;0}	next_action = attack	ride_state = {@ride_none}	multi_class = 0	skill_end
			int skillId = buf.getInt();
			System.out.print("skillid " + skillId);
			if(skillId <= 0)
			{
				System.out.println(" error");
				break;
			}
			System.out.print(" level " + buf.get());
			System.out.print(" sub " + buf.getShort());
			System.out.print(" equip " + buf.get());

			int atkitemtypesize = buf.get();
			System.out.print(" atkitemtypesize " + atkitemtypesize);
			for(int j = 0; j < atkitemtypesize; j++)
			{
				System.out.print("  > attackitemtype " + buf.get());
			}

			System.out.print(" stat type " + buf.get());
			System.out.print(" stat percent " + buf.get());

			System.out.print(" up " + buf.get());
			System.out.print(" hp " + buf.getShort());
			System.out.print(" mp1 " + buf.getShort());
			System.out.print(" mp2 " + buf.getShort());
			System.out.print(" item-id " + buf.getInt());
			System.out.print(" item-num " + buf.get());

			System.out.print(" ultimateconsumegauge " + buf.get());
			byte casterBufIds = buf.get();
			System.out.print(" caster-bufs " + casterBufIds);
			for(int j = 0; j < casterBufIds; j++)
			{
				System.out.print(" > caster-id  " + buf.getInt());
				System.out.print(" > caster-level  " + buf.getShort());
				System.out.print(" > caster-sublevel  " + buf.getShort());
			}

			byte targetBufs = buf.get();

			System.out.print(" target-bufs " + targetBufs);
			for(int j = 0; j < targetBufs; j++)
			{
				System.out.print(" > target-id  " + buf.getInt());
				System.out.print(" > target-level  " + buf.getShort());
				System.out.print(" > target-sublevel  " + buf.getShort());
			}

			System.out.println("--- " + k);


			/*if(k == 18950)
			{
				System.out.println(printData(buf, 100));


				break;
			} */

			k++;

		}

		System.out.println("last: " + buf.remaining());
	}

	public static int ReadByteCount(ByteBuffer f)
	{
		int tmp = f.get() & 0xFF;
		int len = tmp & 0x3F;
		if((tmp & 0x40) > 0)
		{
			tmp = f.get() & 0xFF;
			len += tmp << 6;
		}
		return len;
	}

	public static String ReadUnicode(ByteBuffer b)
	{
		int size = b.getInt();
		int real = size / 2;

		StringBuilder stringBuilder = new StringBuilder();

		for(int i = 0; i < real; i++)
		{
			stringBuilder.append(b.getChar());
		}

		return stringBuilder.toString();
	}

	public static String ReadASCF(ByteBuffer buff)
	{
		int size = buff.get() & 0xFF;
		if(size == 0)
		{
			return "";
		}

		if(size >= 192)
		{
			buff.position(buff.position() - 1);
			size = buff.getShort() & 0xFFFF;
		}

		if(size >= Short.MAX_VALUE)
		{
			buff.position(buff.position() + 1);
		}


		if(size >= 128)
		{
			if(buff.getChar(buff.position()) == 0)
			{
				return "";
			}

			StringBuilder b = new StringBuilder();

			//size = buff.get() & 0xFF;
			char d = 0;
			while((d = buff.getChar()) != 0)
			{
				b.append(d);
			}

			return b.toString();
		}
		else
		{
			StringBuilder b = new StringBuilder();

			//size = buff.get() & 0xFF;
			byte d = 0;
			while((d = buff.get()) != 0)
			{
				b.append((char) d);
			}

			return b.toString();
		}
	}

	public static String printData(ByteBuffer b, int l)
	{
		byte[] d = new byte[l];
		b.get(d);
		return printData(d, d.length);
	}

	public static String printData(byte[] data)
	{
		return printData(data, data.length);
	}

	public static String printData(byte[] data, int len)
	{
		StringBuilder result = new StringBuilder("\n");

		int counter = 0;

		for(int i = 0; i < len; i++)
		{
			if(counter % 16 == 0)
			{
				result.append(fillHex(i, 4) + ": ");
			}

			result.append(fillHex(data[i] & 0xff, 2) + " ");
			counter++;
			if(counter == 16)
			{
				result.append("   ");

				int charpoint = i - 15;
				for(int a = 0; a < 16; a++)
				{
					int t1 = data[charpoint++];
					if(t1 > 0x1f && t1 < 0x80)
					{
						result.append((char) t1);
					}
					else
					{
						result.append('.');
					}
				}

				result.append("\n");
				counter = 0;
			}
		}

		int rest = data.length % 16;
		if(rest > 0)
		{
			for(int i = 0; i < 17 - rest; i++)
			{
				result.append("   ");
			}

			int charpoint = data.length - rest;
			for(int a = 0; a < rest; a++)
			{
				int t1 = data[charpoint++];
				if(t1 > 0x1f && t1 < 0x80)
				{
					result.append((char) t1);
				}
				else
				{
					result.append('.');
				}
			}

			result.append("\n");
		}


		return result.toString();
	}

	public static String fillHex(int data, int digits)
	{
		String number = Integer.toHexString(data);

		for(int i = number.length(); i < digits; i++)
		{
			number = "0" + number;
		}

		return number;
	}
}
