package practise04;

public class Main {

	public static void main(String[] args) {

		/*
		 * ラッパークラスの基本メソッドについて
		 */

		// ラッパークラス一覧

		Byte valByte1 = Byte.valueOf((byte) 10);
		Byte valByte2 = Byte.valueOf((byte) 10);
		Short valShort1 = Short.valueOf((short) 150);
		Short valShort2 = Short.valueOf((short) 150);
		Integer valInteger1 = Integer.valueOf(300);
		Integer valInteger2 = Integer.valueOf(300);
		Long valLong1 = Long.valueOf((long) 1000L);
		Long valLong2 = Long.valueOf((long) 1000L);
		Float valFloat1 = Float.valueOf((float) 1.2);
		Float valFloat2 = Float.valueOf((float) 1.2);
		Double valDouble1 = Double.valueOf((double) 1.23);
		Double valDouble2 = Double.valueOf((double) 1.23);
		Character valCharacter1 = Character.valueOf((char) '一');
		Character valCharacter2 = Character.valueOf((char) '一');
		Boolean valBoolean1 = Boolean.valueOf((boolean) true);
		Boolean valBoolean2 = Boolean.valueOf((boolean) true);
		System.out.println("\n・ ラッパークラスの変数を キャッシュ範囲外で初期化 (Byte・Boolean はキャッシュ) ↓");
		System.out.println("Byte1 : " + valByte1);
		System.out.println("Byte2 : " + valByte2);
		System.out.println("Short1 : " + valShort1);
		System.out.println("Short2 : " + valShort2);
		System.out.println("Integer1 : " + valInteger1);
		System.out.println("Integer2 : " + valInteger2);
		System.out.println("Long1 : " + valLong1);
		System.out.println("Long2 : " + valLong2);
		System.out.println("Float1 : " + valFloat1);
		System.out.println("Float2 : " + valFloat2);
		System.out.println("Double1 : " + valDouble1);
		System.out.println("Double2 : " + valDouble2);
		System.out.println("Character1 : " + valCharacter1);
		System.out.println("Character2 : " + valCharacter2);
		System.out.println("Boolean1 : " + valBoolean1);
		System.out.println("Boolean2 : " + valBoolean2);

		// ラッパークラスの基本メソッド ～ 比較系 ～
		// == 参照(アドレス)を比較する 値が同じでもオブジェクトが違えば false になる
		// (オブジェクトはそれぞれ、値を格納したメモリの参照を持つため、参照が異なる扱いとなる)
		// ただし、Byte・Short・Integer・Long・Character・Boolean には"キャッシュ"がある。(整数型は -128
		// ～127,文字型は 0 ～ 127, 真偽型は true・false)
		// └ キャッシュ =ある範囲の汎用値のオブジェクトについては、わざわざ参照先を増やさずに、同一のメモリ(キャッシュ作成)を参照させる
		// つまり、Byte (-127 ～ 127) の範囲はキャッシュされるということ。
		// == で比較
		System.out.println("\n・ ラッパークラスの変数を == で比較 (Byte・Boolean はキャッシュ内) ↓");
		System.out.println("Byte1,2 を == で比較 : " + (valByte1 == valByte2)); // Byte は全範囲キャッシュ対象(-128～127)なので、ここはtrue
		System.out.println("Short1,2 を == で比較 : " + (valShort1 == valShort2));
		System.out.println("Integer1,2 を == で比較 : " + (valInteger1 == valInteger2));
		System.out.println("Long1,2 を == で比較 : " + (valLong1 == valLong2));
		System.out.println("Float1,2 を == で比較 : " + (valFloat1 == valFloat2)); // そもそもキャッシュされないので false
		System.out.println("Double1,2 を == で比較 : " + (valDouble1 == valDouble2)); // そもそもキャッシュされないので false
		System.out.println("Character1,2 を == で比較 : " + (valCharacter1 == valCharacter2));
		System.out.println("Boolean1,2 を == で比較 : " + (valBoolean1 == valBoolean2)); // Boolean
																						// は全範囲キャッシュ対象(true・false)なので、ここはtrue

		// キャッシュ内の値の場合
		Byte valByte3 = Byte.valueOf((byte) -10);
		Byte valByte4 = Byte.valueOf((byte) -10);
		Short valShort3 = Short.valueOf((short) 127);
		Short valShort4 = Short.valueOf((short) 127);
		Integer valInteger3 = Integer.valueOf(-128);
		Integer valInteger4 = Integer.valueOf(-128);
		Long valLong3 = Long.valueOf((long) 127L);
		Long valLong4 = Long.valueOf((long) 127L);
		Float valFloat3 = Float.valueOf((float) 1.2);
		Float valFloat4 = Float.valueOf((float) 1.2);
		Double valDouble3 = Double.valueOf((double) 1.23);
		Double valDouble4 = Double.valueOf((double) 1.23);
		Character valCharacter3 = Character.valueOf((char) 'A');
		Character valCharacter4 = Character.valueOf((char) 'A');
		Boolean valBoolean3 = Boolean.valueOf((boolean) false);
		Boolean valBoolean4 = Boolean.valueOf((boolean) false);
		System.out.println("\n・ ラッパークラスの変数を キャッシュ範囲内で初期化 (Float・Double はキャッシュ外) ↓");
		System.out.println("Byte3 : " + valByte3);
		System.out.println("Byte4 : " + valByte4);
		System.out.println("Short3 : " + valShort3);
		System.out.println("Short4 : " + valShort4);
		System.out.println("Integer3 : " + valInteger3);
		System.out.println("Integer4 : " + valInteger4);
		System.out.println("Long3 : " + valLong3);
		System.out.println("Long4 : " + valLong4);
		System.out.println("Float3 : " + valFloat3);
		System.out.println("Float4 : " + valFloat4);
		System.out.println("Double3 : " + valDouble3);
		System.out.println("Double4 : " + valDouble4);
		System.out.println("Character3 : " + valCharacter3);
		System.out.println("Character4 : " + valCharacter4);
		System.out.println("Boolean3 : " + valBoolean3);
		System.out.println("Boolean4 : " + valBoolean4);

		System.out.println("\n・ ラッパークラスの変数を == で比較 (Float・Double はキャッシュ外) ↓");
		System.out.println("Byte3,4 を == で比較 : " + (valByte3 == valByte4));
		System.out.println("Short3,4 を == で比較 : " + (valShort3 == valShort4));
		System.out.println("Integer3,4 を == で比較 : " + (valInteger3 == valInteger4));
		System.out.println("Long3,4 を == で比較 : " + (valLong3 == valLong4));
		System.out.println("Float3,4 を == で比較 : " + (valFloat3 == valFloat4)); // そもそもキャッシュされないので false
		System.out.println("Double3,4 を == で比較 : " + (valDouble3 == valDouble4)); // そもそもキャッシュされないので false
		System.out.println("Character3,4 を == で比較 : " + (valCharacter3 == valCharacter4));
		System.out.println("Boolean3,4 を == で比較 : " + (valBoolean3 == valBoolean4));

		System.out.println("\n・ ラッパークラスの変数を == で null 比較可能 ↓");
		System.out.println("Byte3 と null を == で比較 : " + (valByte3 == null));
		System.out.println("Short3 と null を == で比較 : " + (valShort3 == null));
		System.out.println("Integer3 と null を == で比較 : " + (valInteger3 == null));
		System.out.println("Long3 と null を == で比較 : " + (valLong3 == null));
		System.out.println("Float3 と null を == で比較 : " + (valFloat3 == null));
		System.out.println("Double3 と null を == で比較 : " + (valDouble3 == null));
		System.out.println("Character3 と null を == で比較 : " + (valCharacter3 == null));
		System.out.println("Boolean3 と null を == で比較 : " + (valBoolean3 == null));

		/*
		 * equals 値(中身の実値)を比較 型が違うとfalseになる
		 */
		System.out.println("\n・ ラッパークラスの変数を equals で比較 (Byte・boolean 以外はキャッシュ外) ↓");
		System.out.println("Byte1,2 を equals で比較 : " + (valByte1.equals(valByte2)));
		System.out.println("Short1,2 を equals で比較 : " + (valShort1.equals(valShort2)));
		System.out.println("Integer1,2 を equals で比較 : " + (valInteger1.equals(valInteger2)));
		System.out.println("Long1,2 を equals で比較 : " + (valLong1.equals(valLong2)));
		System.out.println("Float1,2 を equals で比較 : " + (valFloat1.equals(valFloat2)));
		System.out.println("Double1,2 を equals で比較 : " + (valDouble1.equals(valDouble2)));
		System.out.println("Character1,2 を equals で比較 : " + (valCharacter1.equals(valCharacter2)));
		System.out.println("Boolean1,2 を equals で比較 : " + (valBoolean1.equals(valBoolean2)));

		System.out.println("\n・ ラッパークラスの変数を equals で比較 (型が異なる場合) ↓");
		System.out.println("Short3 : " + valShort3);
		System.out.println("Long3 : " + valLong3);
		System.out.println("Short3,Long3 を == で比較 : " + (valShort3.equals(valLong3)));

		// null 比較パターン
		Byte nullByte = null;
		Short nullShort = null;
		Integer nullInteger = null;
		Long nullLong = null;
		Float nullFloat = null;
		Double nullDouble = null;
		Character nullCharacter = null;
		Boolean nullBoolean = null;
		System.out.println("nullByte : " + nullByte);
		System.out.println("nullShort : " + nullShort);
		System.out.println("nullInteger : " + nullInteger);
		System.out.println("nullLong : " + nullLong);
		System.out.println("nullFloat : " + nullFloat);
		System.out.println("nullDouble : " + nullDouble);
		System.out.println("nullCharacter : " + nullCharacter);
		System.out.println("nullBoolean : " + nullBoolean);

		// 実値と null 比較では必ず false
		System.out.println("\n・ ラッパークラスの変数を equals で null 比較(false) ↓");
		System.out.println("Byte1 と null を equals で比較 : " + (valByte1.equals(nullByte)));
		System.out.println("Short1 と null を equals で比較 : " + (valShort1.equals(nullShort)));
		System.out.println("Integer1 と null を equals で比較 : " + (valInteger1.equals(nullInteger)));
		System.out.println("Long1 と null を equals で比較 : " + (valLong1.equals(nullLong)));
		System.out.println("Float1 と null を equals で比較 : " + (valFloat1.equals(nullFloat)));
		System.out.println("Double1 と null を equals で比較 : " + (valDouble1.equals(nullDouble)));
		System.out.println("Character1 と null を equals で比較 : " + (valCharacter1.equals(nullCharacter)));
		System.out.println("Boolean1 と null を equals で比較 : " + (valBoolean1.equals(nullBoolean)));

		// null.equals(A) はNPE 発生
		try {
			System.out.println("\n・ ラッパークラスの変数を equals で null と実値を 比較(エラー) ↓");
			System.out.println("null と Byte1 を equals で比較 : " + (nullByte.equals(valByte1)));

		} catch (NullPointerException e) {
			System.out.println("NullPointerException が発生");
		} catch (Exception e) {

		}

		// compareTo
		// Comparable のメソッドで、順序比較 A.compareTo(B) で、AがBにたいして小さい(負)、同じ(0)、大きい(正)かを返す
		// Byte・Short・Character はAがBにたいして小さい(負：-差分)、同じ(0)、大きい(正：差分)かを返す
		// Integer・Long・Float・Double はAがBにたいして小さい(負：-1)、同じ(0)、大きい(正：1)かを"まるめて"返す
		// Booleanは false：0 true:1 の順として、負(-1)、同じ(0)、正(1)を返す。ex.true → false は-1
		Byte valByte5 = Byte.valueOf((byte) 20);
		Short valShort5 = Short.valueOf((short) 300);
		Integer valInteger5 = Integer.valueOf(600);
		Long valLong5 = Long.valueOf((long) 2000L);
		Float valFloat5 = Float.valueOf((float) 3.4);
		Double valDouble5 = Double.valueOf((double) 4.56);
		Character valCharacter5 = Character.valueOf((char) '二');
		Boolean valBoolean5 = Boolean.valueOf((boolean) false);

		System.out.println("\n・ ラッパークラスの変数を 大同小 用意 ↓");
		System.out.println("Byte1 : " + valByte1);
		System.out.println("Byte2 : " + valByte2);
		System.out.println("Byte5 : " + valByte5);
		System.out.println("Short1 : " + valShort1);
		System.out.println("Short2 : " + valShort2);
		System.out.println("Short5 : " + valShort5);
		System.out.println("Integer1 : " + valInteger1);
		System.out.println("Integer2 : " + valInteger2);
		System.out.println("Integer5 : " + valInteger5);
		System.out.println("Long1 : " + valLong1);
		System.out.println("Long2 : " + valLong2);
		System.out.println("Long5 : " + valLong5);
		System.out.println("Float1 : " + valFloat1);
		System.out.println("Float2 : " + valFloat2);
		System.out.println("Float5 : " + valFloat5);
		System.out.println("Double1 : " + valDouble1);
		System.out.println("Double5 : " + valDouble5);
		System.out.println("Character1 : " + valCharacter1);
		System.out.println("Character2 : " + valCharacter2);
		System.out.println("Character5 : " + valCharacter5);
		System.out.println("Boolean1 : " + valBoolean1);
		System.out.println("Boolean1 : " + valBoolean2);
		System.out.println("Boolean5 : " + valBoolean5);

		System.out.println("\n・ ラッパークラスの変数を compareTo で比較 (XXXa,bの比較 = A.compareTo(B))↓");
		System.out.println("Byte1,2を compareTo で比較 : " + (valByte1.compareTo(valByte2)));
		System.out.println("Byte1,5を compareTo で比較 : " + (valByte1.compareTo(valByte5)));
		System.out.println("Byte5,1を compareTo で比較 : " + (valByte5.compareTo(valByte2)));
		System.out.println("Short1,2 を compareTo で比較 : " + (valShort1.compareTo(valShort2)));
		System.out.println("Short1,5 を compareTo で比較 : " + (valShort1.compareTo(valShort5)));
		System.out.println("Short5,2 を compareTo で比較 : " + (valShort5.compareTo(valShort2)));
		System.out.println("Integer1,2 を compareTo で比較 : " + (valInteger1.compareTo(valInteger2)));
		System.out.println("Integer1,5 を compareTo で比較 : " + (valInteger1.compareTo(valInteger5)));
		System.out.println("Integer5,2 を compareTo で比較 : " + (valInteger5.compareTo(valInteger2)));
		System.out.println("Long1,2 を compareTo で比較 : " + (valLong1.compareTo(valLong2)));
		System.out.println("Long1,5 を compareTo で比較 : " + (valLong1.compareTo(valLong5)));
		System.out.println("Long5,2 を compareTo で比較 : " + (valLong5.compareTo(valLong2)));
		System.out.println("Float1,2 を compareTo で比較 : " + (valFloat1.compareTo(valFloat2)));
		System.out.println("Float1,5 を compareTo で比較 : " + (valFloat1.compareTo(valFloat5)));
		System.out.println("Float5,2 を compareTo で比較 : " + (valFloat5.compareTo(valFloat2)));
		System.out.println("Double1,2 を compareTo で比較 : " + (valDouble1.compareTo(valDouble2)));
		System.out.println("Double1,5 を compareTo で比較 : " + (valDouble1.compareTo(valDouble5)));
		System.out.println("Double5,2 を compareTo で比較 : " + (valDouble5.compareTo(valDouble2)));
		System.out.println("Character1,2 を compareTo で比較 : " + (valCharacter1.compareTo(valCharacter2)));
		System.out.println("Character1,5 を compareTo で比較 : " + (valCharacter1.compareTo(valCharacter5)));
		System.out.println("Character5,2 を compareTo で比較 : " + (valCharacter5.compareTo(valCharacter2)));
		System.out.println("Boolean1,2 を compareTo で比較 : " + (valBoolean1.compareTo(valBoolean2)));
		System.out.println("Boolean1,5 を compareTo で比較 : " + (valBoolean1.compareTo(valBoolean5)));
		System.out.println("Boolean5,2 を compareTo で比較 : " + (valBoolean5.compareTo(valBoolean2)));

		// null 比較 はNPE 発生
		try {
			System.out.println("\n・ ラッパークラスの変数を compareTo で null と実値を比較 XXX.compareTo(null) (エラー) ↓");
			System.out.println("null と Byte1 を compareTo で比較 : " + (valByte1.compareTo(nullByte)));

		} catch (NullPointerException e) {
			System.out.println("NullPointerException が発生");
		} catch (Exception e) {

		}
		try {
			System.out.println("\n・ ラッパークラスの変数を compareTo で null と実値を比較 null.compareTo(XXX) (エラー) ↓");
			System.out.println("null と Byte1 を compareTo で比較 : " + (nullByte.compareTo(valByte1)));

		} catch (NullPointerException e) {
			System.out.println("NullPointerException が発生");
		} catch (Exception e) {

		}

		// compare(A,B)
		// ラッパークラスの static メソッド
		// 基本的な仕様は compareTo と同じだが、
		// "null"は扱えない！

		System.out.println("\n・ ラッパークラスの変数を 大同小 用意 ↓");
		System.out.println("Byte1 : " + valByte1);
		System.out.println("Byte2 : " + valByte2);
		System.out.println("Byte5 : " + valByte5);
		System.out.println("Short1 : " + valShort1);
		System.out.println("Short2 : " + valShort2);
		System.out.println("Short5 : " + valShort5);
		System.out.println("Integer1 : " + valInteger1);
		System.out.println("Integer2 : " + valInteger2);
		System.out.println("Integer5 : " + valInteger5);
		System.out.println("Long1 : " + valLong1);
		System.out.println("Long2 : " + valLong2);
		System.out.println("Long5 : " + valLong5);
		System.out.println("Float1 : " + valFloat1);
		System.out.println("Float2 : " + valFloat2);
		System.out.println("Float5 : " + valFloat5);
		System.out.println("Double1 : " + valDouble1);
		System.out.println("Double5 : " + valDouble5);
		System.out.println("Character1 : " + valCharacter1);
		System.out.println("Character2 : " + valCharacter2);
		System.out.println("Character5 : " + valCharacter5);
		System.out.println("Boolean1 : " + valBoolean1);
		System.out.println("Boolean1 : " + valBoolean2);
		System.out.println("Boolean5 : " + valBoolean5);

		System.out.println("\n・  staticメソッド compare で比較 (XXXa,bの比較 = XXX.compare(A,B))↓");
		System.out.println("Byte1,2を compare で比較 : " + (Byte.compare(valByte1, valByte2)));
		System.out.println("Byte1,5を compare で比較 : " + (Byte.compare(valByte1, valByte5)));
		System.out.println("Byte5,1を compare で比較 : " + (Byte.compare(valByte5, valByte2)));
		System.out.println("Short1,2 を compare で比較 : " + (Short.compare(valShort1, valShort2)));
		System.out.println("Short1,5 を compare で比較 : " + (Short.compare(valShort1, valShort5)));
		System.out.println("Short5,2 を compare で比較 : " + (Short.compare(valShort5, valShort2)));
		System.out.println("Integer1,2 を compare で比較 : " + (Integer.compare(valInteger1, valInteger2)));
		System.out.println("Integer1,5 を compare で比較 : " + (Integer.compare(valInteger1, valInteger5)));
		System.out.println("Integer5,2 を compare で比較 : " + (Integer.compare(valInteger5, valInteger2)));
		System.out.println("Long1,2 を compare で比較 : " + (Long.compare(valLong1, valLong2)));
		System.out.println("Long1,5 を compare で比較 : " + (Long.compare(valLong1, valLong5)));
		System.out.println("Long5,2 を compare で比較 : " + (Long.compare(valLong5, valLong2)));
		System.out.println("Float1,2 を compare で比較 : " + (Float.compare(valFloat1, valFloat2)));
		System.out.println("Float1,5 を compare で比較 : " + (Float.compare(valFloat1, valFloat5)));
		System.out.println("Float5,2 を compare で比較 : " + (Float.compare(valFloat5, valFloat2)));
		System.out.println("Double1,2 を compare で比較 : " + (Double.compare(valDouble1, valDouble2)));
		System.out.println("Double1,5 を compare で比較 : " + (Double.compare(valDouble1, valDouble5)));
		System.out.println("Double5,2 を compare で比較 : " + (Double.compare(valDouble5, valDouble2)));
		System.out.println("Character1,2 を compare で比較 : " + (Character.compare(valCharacter1, valCharacter2)));
		System.out.println("Character1,5 を compare で比較 : " + (Character.compare(valCharacter1, valCharacter5)));
		System.out.println("Character5,2 を compare で比較 : " + (Character.compare(valCharacter5, valCharacter2)));
		System.out.println("Boolean1,2 を compare で比較 : " + (Boolean.compare(valBoolean1, valBoolean2)));
		System.out.println("Boolean1,5 を compare で比較 : " + (Boolean.compare(valBoolean1, valBoolean5)));
		System.out.println("Boolean5,2 を compare で比較 : " + (Boolean.compare(valBoolean5, valBoolean2)));

		// null 比較はできない
		// Integer.compare(A,null) はNPE 発生
		try {
			System.out.println("\n・ staticメソッド compare で null と実値を比較(エラー) ↓");
			System.out.println("Byte1 と null を compare で比較 : " + (Byte.compare((byte) 1, nullByte)));

		} catch (NullPointerException e) {
			System.out.println("NullPointerException が発生");
		} catch (Exception e) {

		}

	}

}
