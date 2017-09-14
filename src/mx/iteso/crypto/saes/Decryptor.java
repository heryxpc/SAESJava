package mx.iteso.crypto.saes;

public class Decryptor {
	private KeyGenerator generator;

	public KeyGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(KeyGenerator generator) {
		this.generator = generator;
	}
	
	public byte[][] decrypt(byte[][] message)
	{
		byte[][] nibble = message;
		System.out.println("Cipher text nibble:");
		Util.printNibbles(nibble);
		byte[][] key3 = generator.keyNibbles(4,5);
		System.out.println("Generated key 3:");
		Util.printNibbles(key3);
		byte[][] step1 = AddRoundKey.add(nibble, key3);
		System.out.println("Result after added third key:");
		Util.printNibbles(step1);
		byte[][] step2= ShiftRows.shift(step1);
		System.out.println("Result after first shift:");
		Util.printNibbles(step2);
		byte[][] step3 = SubNibbles.invertSubstitute(step2);
		System.out.println("Result after first inverse substitution:");
		Util.printNibbles(step3);
		byte[][] key2 = generator.keyNibbles(2,3);
		System.out.println("Generated key 2:");
		Util.printNibbles(key2);
		byte[][] step4 = AddRoundKey.add(step3, key2);
		System.out.println("Result after added second key:");
		Util.printNibbles(step4);
		byte[][] step5 = MixColumns.invertMix(step4);
		System.out.println("Result after inverse mix columns:");
		Util.printNibbles(step5);
		byte[][] step6 = ShiftRows.shift(step5);
		System.out.println("Result after second shift:");
		Util.printNibbles(step6);
		byte[][] step7 = SubNibbles.invertSubstitute(step6);
		System.out.println("Result after second inverse substitution:");
		Util.printNibbles(step7);
		byte[][] key1 = generator.keyNibbles(0,1);
		System.out.println("Generated key 1:");
		Util.printNibbles(key1);
		byte[][] step8 = AddRoundKey.add(step7, key1);
		System.out.println("Result after added first key:");
		Util.printNibbles(step8);
		byte[][] cipherNibble = step8;
		System.out.println("Plain nibble:" + cipherNibble);
		return cipherNibble;
	}
	
	public static void main(String[] args) {
		Decryptor saes = new Decryptor();
//		byte[] key = {1,0,1,0, 0,1,1,1, 0,0,1,1, 1,0,1,1};
		byte[] key = { 0,1,0,0, 1,0,1,0, 1,1,1,1, 0,1,0,1};
		KeyGenerator withKey = new KeyGenerator(key);
		saes.setGenerator(withKey);
		withKey.generate(); // IMPORTANT otherwise keys won't be generated
//		byte[][] messageASNibbles = {{ Util.bitsToByte("0110"), Util.bitsToByte("0110") }, 
//									 { Util.bitsToByte("1111"), Util.bitsToByte("1011") }};
		byte[][] messageASNibbles = {{ Util.bitsToByte("0010"), Util.bitsToByte("1110") }, 
									 { Util.bitsToByte("0100"), Util.bitsToByte("1100") }};
		System.out.println("Message to decrypt: ");
		Util.printNibbles(messageASNibbles);
		byte[][] encrypted = saes.decrypt(messageASNibbles);
		System.out.println("Message decrypted: ");
		Util.printNibbles(encrypted);
	}
}
