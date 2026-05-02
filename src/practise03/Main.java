package practise03;

public class Main {

	public static void main(String[] args) {

		/*
		 * ラッパークラスの基本メソッドについて
		 */

		// ラッパークラス一覧
		Byte valByte = null;
		Short valShort = null;
		Integer valInteger = null;
		Long valLong = null;
		Float valFloat = null;
		Double valDouble = null;
		Character valChar = null;
		Boolean valBoolean = null;

		System.out.println("・ ラッパークラスには null が格納できる ↓");
		System.out.println("Byte : " + valByte);
		System.out.println("Short : " + valShort);
		System.out.println("Integer : " + valInteger);
		System.out.println("Long : " + valLong);
		System.out.println("Float : " + valFloat);
		System.out.println("Double : " + valDouble);
		System.out.println("Character : " + valChar);
		System.out.println("Boolean : " + valBoolean);

		/*
		 * ラッパークラスの基本メソッド ～ パース系 ～ valueOf(プリミティブ型の値) プリミティブ型の値をラッパー型で返す ---
		 * ボクシングの際に暗黙に呼び出される
		 */
		valByte = Byte.valueOf((byte) 1);
		valShort = Short.valueOf((short) 2);
		valInteger = Integer.valueOf(10);
		valLong = Long.valueOf((long) 123L);
		valFloat = Float.valueOf((float) 1.2);
		valDouble = Double.valueOf((double) 3.45);
		valChar = Character.valueOf((char) 'A');
		valBoolean = Boolean.valueOf((boolean) true);

		System.out.println("\n・ ラッパークラスの基本メソッド Valueof(プリミティブ型の値) ↓");
		System.out.println("Byte : " + valByte);
		System.out.println("Short : " + valShort);
		System.out.println("Integer : " + valInteger);
		System.out.println("Long : " + valLong);
		System.out.println("Float : " + valFloat);
		System.out.println("Double : " + valDouble);
		System.out.println("Character : " + valChar);
		System.out.println("Boolean : " + valBoolean);

		/*
		 * valueOf(文字列) 文字列型の値をラッパー型で返す
		 */
		try {
			Byte vosByte = Byte.valueOf("1");
			Short vosShort = Short.valueOf("2");
			Integer vosInteger = Integer.valueOf("3");
			Long vosLong = Long.valueOf("4");
			Float vosFloat = Float.valueOf("5.6");
			Double vosDouble = Double.valueOf("7.8");
			// Character vosChar = Character.valueOf("A"); <- これは存在しない
			Character vosChar = Character.valueOf('A');
			Boolean vosBoolean01 = Boolean.valueOf("true");
			Boolean vosBoolean02 = Boolean.valueOf("false");
			Boolean vosBoolean03 = Boolean.valueOf("abc");

			System.out.println("\n・ ラッパークラスの基本メソッド Valueof(文字列) ↓");
			System.out.println("Byte : " + vosByte);
			System.out.println("Short : " + vosShort);
			System.out.println("Integer : " + vosInteger);
			System.out.println("Long : " + vosLong);
			System.out.println("Float : " + vosFloat);
			System.out.println("Double : " + vosDouble);
			System.out.println("Character : " + vosChar);
			System.out.println("Character の valueOf(String) については実装なし");
			System.out.println("Boolean (true) : " + vosBoolean01);
			System.out.println("Boolean (false) : " + vosBoolean02);
			System.out.println("Boolean (abc) : " + vosBoolean03);

			// 文字列が適切でないとエラー発生
			vosFloat = Float.valueOf("123f"); // f がついていることで純粋な数値ではない
			System.out.println(vosFloat);

		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException が発生");
		} catch (Exception e) {

		}

		/*
		 * parseXXX(文字列) 文字列をプリミティブ型にして返す
		 */
		try {
			byte ex1 = Byte.parseByte("1");
			short ex2 = Short.parseShort("2");
			int ex3 = Integer.parseInt("123");
			long ex4 = Long.parseLong("123"); // L をつけるとエラー！
			float ex5 = Float.parseFloat("1.2"); // f をつけるとエラー！
			double ex6 = Double.parseDouble("3.456");
			// Character には実装なし
			boolean ex8 = Boolean.parseBoolean("true");
			boolean ex9 = Boolean.parseBoolean("false");
			boolean ex10 = Boolean.parseBoolean("abc"); // true 以外の文字列は false になる

			System.out.println("\n・ ラッパークラスの基本メソッド parseXXX(文字列) ↓");
			System.out.println("Byte : " + ex1);
			System.out.println("Short : " + ex2);
			System.out.println("Integer : " + ex3);
			System.out.println("Long : " + ex4);
			System.out.println("Float : " + ex5);
			System.out.println("Double : " + ex6);
			System.out.println("Character には実装なし");
			System.out.println("Boolean (true) : " + ex8);
			System.out.println("Boolean (false) : " + ex9);
			System.out.println("Boolean (abc) : " + ex10);

			// 文字列が適切でないとエラー発生
			ex4 = Long.parseLong("123L"); // L がついていることで純粋な数値ではない
			System.out.println(ex4);

		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException が発生");
		} catch (Exception e) {
			// その他例外
		}

	}

}
