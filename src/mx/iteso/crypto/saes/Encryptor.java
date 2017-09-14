package mx.iteso.crypto.saes;

public class Encryptor {

	private KeyGenerator generator;
	
	public Encryptor() {
	}

	public KeyGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(KeyGenerator generator) {
		this.generator = generator;
	}
	
	public byte[][] encrypt(byte[][] message)
	{
		byte[][] nibble = message;
		System.out.println("PLain text nibble:");
		Util.printNibbles(nibble);
		byte[][] key1 = generator.keyNibbles(0,1);
		System.out.println("Generated key 1:");
		Util.printNibbles(key1);
		byte[][] step1 = AddRoundKey.add(nibble, key1);
		System.out.println("Result after added first key:");
		Util.printNibbles(step1);
		byte[][] step2 = SubNibbles.substitute(step1);
		System.out.println("Result after first substitution:");
		Util.printNibbles(step2);
		byte[][] step3 = ShiftRows.shift(step2);
		System.out.println("Result after first shift:");
		Util.printNibbles(step3);
		byte[][] step4 = MixColumns.mix(step3);
		System.out.println("Result after mix columns:");
		Util.printNibbles(step4);
		byte[][] key2 = generator.keyNibbles(2,3);
		System.out.println("Generated key 2:");
		Util.printNibbles(key2);
		byte[][] step5 = AddRoundKey.add(step4, key2);
		System.out.println("Result after added second key:");
		Util.printNibbles(step5);
		byte[][] step6 = SubNibbles.substitute(step5);
		System.out.println("Result after second substitution:");
		Util.printNibbles(step6);
		byte[][] step7 = ShiftRows.shift(step6);
		System.out.println("Result after second shift:");
		Util.printNibbles(step7);
		byte[][] key3 = generator.keyNibbles(4,5);
		System.out.println("Generated key 3:");
		Util.printNibbles(key3);
		byte[][] step8 = AddRoundKey.add(step7, key3);
		System.out.println("Result after added third key:");
		Util.printNibbles(step8);
		byte[][] cipherNibble = step8;
		System.out.println("Ciphered nibble:" + cipherNibble);
		return cipherNibble;
	}
	
	public static void main(String[] args) {
		Encryptor saes = new Encryptor();
//		byte[] key = {1,0,1,0, 0,1,1,1, 0,0,1,1, 1,0,1,1};
		byte[] key = { 0,1,0,0, 1,0,1,0, 1,1,1,1, 0,1,0,1};
		KeyGenerator withKey = new KeyGenerator(key);
		saes.setGenerator(withKey);
		withKey.generate(); // IMPORTANT otherwise keys won't be generated
//		byte[][] messageASNibbles = {{ Util.bitsToByte("0110"), Util.bitsToByte("0110") }, 
//									 { Util.bitsToByte("1111"), Util.bitsToByte("1011") }};
		byte[][] messageASNibbles = {{ Util.bitsToByte("1101"), Util.bitsToByte("0010") }, 
									 { Util.bitsToByte("0111"), Util.bitsToByte("1000") }};
		System.out.println("Message to encrypt: ");
		Util.printNibbles(messageASNibbles);
		byte[][] encrypted = saes.encrypt(messageASNibbles);
		System.out.println("Message encrypted: ");
		Util.printNibbles(encrypted);
	}
	
}
