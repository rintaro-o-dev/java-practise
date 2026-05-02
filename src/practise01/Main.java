package practise01;

import java.util.Arrays;
import java.util.List;

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

		System.out.println("・ プリミティブ型 ↓");
		System.out.println(numByte);
		System.out.println(numShort);
		System.out.println(numInt);
		System.out.println(numLong);
		System.out.println(numFloat);
		System.out.println(numDouble);
		System.out.println(valChar);
		System.out.println(valBoolean);

		// char のリテラルについて
		char exChar01 = 'A';
		char exChar02 = 66;
		char exChar03 = 0x43;
		char exChar04 = 0101;
		char exChar05 = '\u0045';
		System.out.println("\n・ char のリテラル格納パターン ↓");
		System.out.println("一文字 \'A\' : " + exChar01);
		System.out.println("10進数 66 : " + exChar02);
		System.out.println("16進数 0x43 : " + exChar03);
		System.out.println("8進数 0101 : " + exChar04);
		System.out.println("ユニコード \'\\u0045\' : " + exChar05);
		System.out.println("文字列型は NG");
		System.out.println("\"0041\" のような数値の文字列も NG");
		System.out.println("\'\\u41\'は桁不足で NG");

		// ラッパークラス一覧 ＆ オートボクシング
		Byte bxNumByte = numByte; // 完全対応
		Short bxNumShort = numShort; // 完全対応
		Integer bxNumInt = numInt; // 完全対応
		Long bxNumLong = numLong; // 完全対応
		Float bxNumFloat = numFloat; // 完全対応
		Double bxNumDouble = numDouble; // 完全対応
		Character bxValChar = valChar; // 完全対応
		Boolean bxValBoolean = valBoolean; // 完全対応

		System.out.println("\n・ オートボクシング後 ↓");
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

		System.out.println("\n・ アンボクシング ↓");
		System.out.println(unByte);
		System.out.println(unShort);
		System.out.println(unInt);
		System.out.println(unLong);
		System.out.println(unFloat);
		System.out.println(unDouble);
		System.out.println(unChar);
		System.out.println(unBoolean);

		// アンボクシングによる NPE リスク
		try {
			// 計算時に一時的にアンボクシングされ、ボクシングして戻す挙動
			Integer a = 1;
			Integer b = 2;
			Integer c = a + b; // この時 a と b はアンボクシングされ実値を取り出してから計算、ボクシングで元に戻している
			System.out.println("\nアンボクシング→ボクシングで計算成功：" + c);

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

		// 抽象型クラスのアンボクシング時のキャスト
		// 抽象クラス一覧(搬出)
		Number exNumber = Integer.valueOf(10); // 数値型の抽象クラスで、基本メソッドには parseXXX() を持つ
		Object exObject01 = Integer.valueOf(20); // オブジェクトクラス(数値)
		Object exObject02 = "abc"; // オブジェクトクラス(文字列)
		try {
			// アンボクシング時にはキャストが必須
			int a = (int) exNumber; // int a = exNumber; <- これはできない(コンパイルエラー)
			int b = (int) exObject01;
			// Object → int は直接は不可（int b = exObject01; はコンパイルエラー）
			// ただし exObject01 の実体が Integer の場合、
			//   (int) exObject01
			// → (Integer) exObject01   （キャスト）
			// → ((Integer) exObject01).intValue() （アンボクシング）
			// という流れで成功する

			System.out.println("\n・抽象型クラスのアンボクシング(キャスト必須) ↓");
			System.out.println("Number からキャストしてアンボクシング : " + a);
			System.out.println("Object からキャストしてアンボクシング() : " + b);

			// ひっかけパターン
			int c = (int) exObject02; // exObject02 の中身は文字列！
			System.out.println("Object からキャストしてアンボクシング() : " + c);

		} catch (ClassCastException e) {
			System.out.println("String を int にキャストしようとして ClassCastException が発生");
		} catch (Exception e) {
			// その他例外
		}


		/*
		 * コレクションにnullが存在するときのアンボクシングエラーパターン
		 */
		List<Integer> exList = Arrays.asList(1, 2, null, 4); // インデックス 2 に null をセット
		try {
			System.out.println("\n・ コレクション(List) に null が入っている場合のアンボクシングエラーパターン ↓");
			int ind0 = exList.get(0);
			System.out.println("index0 : " + ind0);
			int ind1 = exList.get(1);
			System.out.println("index1 : " + ind1);
			int ind2 = exList.get(2); // ここで int ind2 = null; の形になり NPE 発生
			System.out.println("index2 : " + ind2);
			int ind3 = exList.get(3);
			System.out.println("index3 : " + ind3);

		} catch (NullPointerException e) {
			System.out.println("index2 に null がセットされていたので、 NullPointerException が発生");
		} catch (Exception e) {
			// その他例外
		}
	}

}
