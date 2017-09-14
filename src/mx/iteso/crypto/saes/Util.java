package mx.iteso.crypto.saes;

public class Util {
	
	public static byte[][] createEmptyNibbles()
	{
		byte[][] cols = new byte[2][];
		cols[0] = new byte[2];
		cols[1] = new byte[2];
		return cols;
	}
	
	public static void printNibbles(byte[][] nibble)
	{
		byte[] array = nibblesToArray(nibble);
		for (int i=0; i < 2; i++)
		{
			System.out.print(' ');
			String output0 = Integer.toBinaryString(array[i]);
			if (output0.length() < 4)
			{
				for (int p = 0; p < 4 - output0.length(); p++)
				{
					System.out.print('0');
				}
			}
			System.out.print(output0);
			System.out.print(' ');
			String output1 = Integer.toBinaryString(array[i+2]);
			if (output1.length() < 4)
			{
				for (int p = 0; p < 4 - output1.length(); p++)
				{
					System.out.print('0');
				}
			}
			System.out.print(output1);
			System.out.println();
		}
	}
	
	public static byte[] nibblesToArray(byte[][] nibble)
	{
		byte[] array = new byte[4]; //Arrange by columns
		array[0] = nibble[0][0];
		array[1] = nibble[1][0];
		array[2] = nibble[0][1];
		array[3] = nibble[1][1];
		return array; 
	}
	
	public static byte[][] arrayToNibbles(byte[] array)
	{
		byte[][] nibble = createEmptyNibbles();
		nibble[0][0] = array[0];
		nibble[1][0] = array[1];
		nibble[0][1] = array[2];
		nibble[1][1] = array[3];
		return nibble;
	}
	
	public static byte bitsToByte(String bits)
	{
		try
		{
			return Byte.parseByte(bits, 2);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public static short bitsToShort(String bits)
	{
		try
		{
			return Short.parseShort(bits, 2);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		// test nibble creation
		byte[][] nibble = createEmptyNibbles();
		printNibbles(nibble); 
		nibble[0][0] = bitsToByte("0000");
		nibble[1][0] = bitsToByte("1111");
		nibble[0][1] = bitsToByte("1010");
		nibble[1][1] = bitsToByte("0101");
		printNibbles(nibble); 
		nibble[1][1] = bitsToByte("1111");
		printNibbles(nibble);
	}
	
	
}
