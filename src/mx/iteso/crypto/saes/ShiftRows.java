package mx.iteso.crypto.saes;

public class ShiftRows {

	public static byte[][] shift(byte[][] nibble)
	{
		byte[][] result = Util.createEmptyNibbles();
		result[0][0] = nibble[0][0];
		result[0][1] = nibble[0][1];
		result[1][0] = nibble[1][1];
		result[1][1] = nibble[1][0];
		return result;
	}
	public static void main(String[] args) {
		byte [][] nibble = Util.createEmptyNibbles();
		nibble[0][0] = Util.bitsToByte("0000");
		nibble[1][0] = Util.bitsToByte("1010");
		nibble[0][1] = Util.bitsToByte("0101");
		nibble[1][1] = Util.bitsToByte("1111");
		Util.printNibbles(nibble);
		byte[][] shifted = shift(nibble);
		Util.printNibbles(shifted);
	}
}
