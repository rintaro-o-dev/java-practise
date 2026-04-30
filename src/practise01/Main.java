package practise01;

public class Main {

	public static void main(String[] args) {

		/*
		 * プリミティブ型とオートボクシングのデモ
		 */

		// プリミティブ型 一覧
		byte numByte = 3;		// 8 bit 整数型 => -128 ～ 127 ...メモリ節約やバイト節約
		short numShort = 5;		// 16 bit 整数型 => -32768 ～ 32767 ...今はほぼ使わない
		int numInt = 10;		// 32 bit 整数型 => -約21億 ～ 約21億 ...標準の整数
		long numLong = 100L;	// 64 bit 整数型 => 最広範囲 ...intで抑えられない程大きな数値
		float numFloat = 1.23f;		// 32 bit 小数型(浮動小数点) => 約7桁の精度 ...軽量化目的、"f"必須
		double numDouble = 3.14;	// 64 bit 小数型(浮動小数点) => 約15桁の精度　...標準の小数
		char valChar = 'A';		// 16 bit 文字型 => UTF-16 の1コードユニット
		boolean valBoolean = true;	// サイズは仕様上未定義 真偽型

		System.out.println("プリミティブ型 ↓");
		System.out.println(numByte);
		System.out.println(numShort);
		System.out.println(numInt);
		System.out.println(numLong);
		System.out.println(numFloat);
		System.out.println(numDouble);
		System.out.println(valChar);
		System.out.println(valBoolean);

		// オートボクシング
		Byte bxNumByte = numByte;		// 完全対応
		Short bxNumShort = numShort;	// 完全対応
		Integer bxNumInt = numInt;		// 完全対応
		Long bxNumLong = numLong;		// 完全対応
		Float bxNumFloat = numFloat;	// 完全対応
		Double bxNumDouble = numDouble;	// 完全対応
		Character bxValChar = valChar;	// 完全対応
		Boolean bxValBoolean = valBoolean;	// 完全対応

		System.out.println("オートボクシング後 ↓");
		System.out.println(bxNumByte);
		System.out.println(bxNumShort);
		System.out.println(bxNumInt);
		System.out.println(bxNumLong);
		System.out.println(bxNumFloat);
		System.out.println(bxNumDouble);
		System.out.println(bxValChar);
		System.out.println(bxValBoolean);

	}

}
