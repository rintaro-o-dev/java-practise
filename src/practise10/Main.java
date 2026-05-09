package practise10;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		// 行列おさらい
		System.out.println("・ 行列おさらい");
		// 単配列
		int[] arrIntA = new int[10]; // 行列の大きさだけ
		int[] arrIntB = { 10, 30, 20 }; // 値だけ
		int[] arrIntC = new int[] { 1, 2, 3, 4, 5 }; // 初期化と値
		System.out.println("arrayA length : " + arrIntA.length + ", arrayA value : " + Arrays.toString(arrIntA));
		System.out.println("arrayB length : " + arrIntB.length + ", arrayB value : " + Arrays.toString(arrIntB));
		System.out.println("arrayC length : " + arrIntC.length + ", arrayC value : " + Arrays.toString(arrIntC));

		// 多次元行列
		int[][] arrIntD = new int[2][3]; // 行列の大きさだけ
		int[][] arrIntE = { { 1, 3 }, { 2, 4 }, { 7, 5 } }; // 値だけ
		int[][] arrIntF = new int[][] { { 10, 20 }, { 30, 40, 50 } }; // 初期化と値
		System.out.println("arrayD length : " + arrIntD.length + ", arrayD value : " + Arrays.deepToString(arrIntD));
		System.out.println("arrayE length : " + arrIntE.length + ", arrayE value : " + Arrays.deepToString(arrIntE));
		System.out.println("arrayF length : " + arrIntF.length + ", arrayF value : " + Arrays.deepToString(arrIntF));
		System.out.println(
				"arrayF index[0]length : " + arrIntF[0].length + ", arrayF index[1]length : " + arrIntF[1].length);

		// java.util.Arrays
		// 行列は A.length しかできないのでそれ以外の機能を提供
		System.out.println("\n・ Arrays クラス の static メソッド");
		// 文字列化
		System.out.println("Arrays.toString(arrayB) : " + Arrays.toString(arrIntB));
		System.out.println("Arrays.deepToString(arrayE) : " + Arrays.deepToString(arrIntE));
		// ソート
		Arrays.sort(arrIntB);
		System.out.println("Arrays.sort(arrayB) : " + Arrays.toString(arrIntB));
		// ソート済み行列から高速検索(インデックス)
		System.out.println("Arrays.binarySearch(arrayB, 20) : " + Arrays.binarySearch(arrIntB, 20));
		// コピー(指定サイズ)
		int[] copyArrIntB1 = Arrays.copyOf(arrIntB, 1);
		int[] copyArrIntB2 = Arrays.copyOf(arrIntB, 3);
		int[] copyArrIntB3 = Arrays.copyOf(arrIntB, 5);
		int[][] copyArrIntE1 = Arrays.copyOf(arrIntE, 3);
		System.out.println("Arrays.copyOf(arrayB, 1) : " + Arrays.toString(copyArrIntB1));
		System.out.println("Arrays.copyOf(arrayB, 3) : " + Arrays.toString(copyArrIntB2));
		System.out.println("Arrays.copyOf(arrayB, 5) : " + Arrays.toString(copyArrIntB3));
		System.out.println("Arrays.copyOf(arrayE, 3) : " + Arrays.deepToString(copyArrIntE1));
		// コピー(部分コピー)
		int[] copyArrIntC1 = Arrays.copyOfRange(arrIntC, 0, 3);
		int[] copyArrIntC2 = Arrays.copyOfRange(arrIntC, 3, 5);
		System.out.println("Arrays.copyOfRange(arrayC, 1, 3) : " + Arrays.toString(copyArrIntC1));
		System.out.println("Arrays.copyOfRange(arrayC, 4, 5) : " + Arrays.toString(copyArrIntC2));
		// 比較
		System.out.println("Arrays.equals(arrayB, arrayBcopy2) : " + Arrays.equals(arrIntB, copyArrIntB2));
		System.out.println("Arrays.equals(arrayB, arrayBcopy3) : " + Arrays.equals(arrIntB, copyArrIntB3));
		System.out.println("Arrays.deepEquals(arrayE, arrayEcopy1) : " + Arrays.deepEquals(arrIntE, copyArrIntE1));
		// 全要素一括更新(フィル)
		Arrays.fill(arrIntC, 99);
		System.out.println("Arrays.fill(arrayC,99) : " + Arrays.toString(arrIntC));
		// ラムダで全要素生成
		Arrays.setAll(arrIntA, i -> i * 2 + 2);
		System.out.println("Arrays.setAll(arrayA, i -> i * 10) : " + Arrays.toString(arrIntA));
		// stream　化
		long[] arrLongA = {100L, 200L, 300L};
		double[] arrDoubleA = {1.2, 3.4, 5.6, 7.8};
		Integer[] arrIntegerA = {5, 6, 7, 8};
		IntStream intStA = Arrays.stream(arrIntA);
		LongStream longStA = Arrays.stream(arrLongA);
		DoubleStream doubleStA = Arrays.stream(arrDoubleA);
		Stream<Integer> IntegerStA = Arrays.stream(arrIntegerA);
		System.out.print("IntStream : ");
		intStA.forEach(x -> System.out.print(x + ", "));
		System.out.print("\nLongStream : ");
		longStA.forEach(x -> System.out.print(x + ", "));
		System.out.print("\nDoubleStream : ");
		doubleStA.forEach(x -> System.out.print(x + ", "));
		System.out.print("\nStream<Integer> : ");
		IntegerStA.forEach(x -> System.out.print(x + ", "));
		System.out.println("\nStream<Integer> を再使用 ↓");
		try {
		IntegerStA.forEach(x -> System.out.print(x + ", "));
		} catch (IllegalStateException e) {
			System.out.println("stream を再度動かそうとすると IllegalStateException が発生 (消費済み)");
		}
	}

}
//
// public class Arrays {
// --- コンストラクタは private（インスタンス化禁止）
// private Arrays() {}
// --- ソート
// public static void sort(int[] a) { ... }
// public static void sort(Object[] a) { ... }
// --- 検索
// public static int binarySearch(int[] a, int key) { ... }
// public static int binarySearch(Object[] a, Object key) { ... }
// --- 比較
// public static boolean equals(int[] a, int[] b) { ... }
// public static boolean deepEquals(Object[] a, Object[] b) { ... }
// --- 文字列化
// public static String toString(int[] a) { ... }
// public static String deepToString(Object[] a) { ... }
// --- コピー
// public static int[] copyOf(int[] original, int newLength) { ... }
// public static <T> T[] copyOf(T[] original, int newLength) { ... }
// --- Stream 化
// public static LongStream stream(long[] array) { ... }
// public static IntStream stream(int[] array) { ... }
// public static DoubleStream stream(double[] array) { ... }
// public static <T> Stream<T> stream(T[] array) { ... }
// }
//
