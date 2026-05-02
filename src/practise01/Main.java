package practise01;

public class Main {

	public static void main(String[] args) {

		/*
		 * プリミティブ型とオートボクシングのデモ
		 */

		// プリミティブ型 一覧
		byte numByte = 3; // 8 bit 整数型 => -128 ～ 127 ...メモリ節約やバイト節約
		short numShort = 5; // 16 bit 整数型 => -32768 ～ 32767 ...今はほぼ使わない
		int numInt = 10; // 32 bit 整数型 => -約21億 ～ 約21億 ...標準の整数
		long numLong = 100L; // 64 bit 整数型 => 最広範囲 ...intで抑えられない程大きな数値
		float numFloat = 1.23f; // 32 bit 小数型(浮動小数点) => 約7桁の精度 ...軽量化目的、"f"必須
		double numDouble = 3.14; // 64 bit 小数型(浮動小数点) => 約15桁の精度 ...標準の小数
		char valChar = 'A'; // 16 bit 文字型 => UTF-16 の1コードユニット
		boolean valBoolean = true; // サイズは仕様上未定義 真偽型

		System.out.println("プリミティブ型 ↓");
		System.out.println(numByte);
		System.out.println(numShort);
		System.out.println(numInt);
		System.out.println(numLong);
		System.out.println(numFloat);
		System.out.println(numDouble);
		System.out.println(valChar);
		System.out.println(valBoolean);

		// ラッパークラス一覧 ＆ オートボクシング
		Byte bxNumByte = numByte; // 完全対応
		Short bxNumShort = numShort; // 完全対応
		Integer bxNumInt = numInt; // 完全対応
		Long bxNumLong = numLong; // 完全対応
		Float bxNumFloat = numFloat; // 完全対応
		Double bxNumDouble = numDouble; // 完全対応
		Character bxValChar = valChar; // 完全対応
		Boolean bxValBoolean = valBoolean; // 完全対応

		System.out.println("オートボクシング後 ↓");
		System.out.println(bxNumByte);
		System.out.println(bxNumShort);
		System.out.println(bxNumInt);
		System.out.println(bxNumLong);
		System.out.println(bxNumFloat);
		System.out.println(bxNumDouble);
		System.out.println(bxValChar);
		System.out.println(bxValBoolean);

		// アンボクシング
		byte unByte = bxNumByte;
		short unShort = bxNumShort;
		int unInt = bxNumInt;
		long unLong = bxNumLong;
		float unFloat = bxNumFloat;
		double unDouble = bxNumDouble;
		char unChar = bxValChar;
		boolean unBoolean = bxValBoolean;

		System.out.println("アンボクシング ↓");
		System.out.println(unByte);
		System.out.println(unShort);
		System.out.println(unInt);
		System.out.println(unLong);
		System.out.println(unFloat);
		System.out.println(unDouble);
		System.out.println(unChar);
		System.out.println(unBoolean);

		// アンボクシングによる　NPE　リスク
		try {
			// 計算時に一時的にアンボクシングされ、ボクシングして戻す挙動
			Integer a = 1;
			Integer b = 2;
			Integer c = a + b; // この時 a と b はアンボクシングされ実値を取り出してから計算、ボクシングで元に戻している
			System.out.println("アンボクシング→ボクシングで計算成功：" + c);

			// 上記の計算上アンボクシングを挟むので Integer にnullが入っているとエラーになる
			Integer d = null;
			Integer e = 3;
			Integer f = d + e; // ← ここで NPE
			System.out.println(f);

		} catch (NullPointerException e) {
			System.out.println("null のアンボクシングで NPE 発生");
		} catch (Exception e) {
			// その他例外
		}


	}

}
