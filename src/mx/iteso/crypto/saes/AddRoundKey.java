package mx.iteso.crypto.saes;

public class AddRoundKey {

	
	public static byte[][] add(byte[][] nibble0, byte[][]nibble1) 
	{
		byte[] array0 = Util.nibblesToArray(nibble0);
		byte[] array1 = Util.nibblesToArray(nibble1);
		byte[] arrayResult = new byte[4];
		for (int i = 0; i < 4; i++)
		{
			arrayResult[i] = (byte)(array0[i] ^ array1[i]);
		}
		return Util.arrayToNibbles(arrayResult);
	}
	
	public static void main(String[] args) {
		byte[][] nibble0 = Util.createEmptyNibbles();
		byte[][] nibble1 = Util.createEmptyNibbles();
		nibble0[0][0] = 1;
		nibble0[1][0] = 1;
		Util.printNibbles(nibble0);
		nibble1[0][1] = 1;
		nibble1[1][1] = 1;
		Util.printNibbles(nibble1);
		byte[][] added = add(nibble0, nibble1);
		Util.printNibbles(added);
	}
}
