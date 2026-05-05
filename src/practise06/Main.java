package practise06;

public class Main {

	public static void main(String[] args) {

		System.out.println("\n・ インターフェースとラムダ式 (アルゴリズムの切り出し)");
		// ラムダ式に使えるのは、"抽象メソッドが1つだけ" の "関数型インターフェース"
		// Algorithm1 インタフェースには、void perform(String name); 1つしかない
		// 型推論に任せて実装
		Algorithm1 algoA1 = (name) -> {
			System.out.println("algo1　: name is " + name);
		};
		// 型を宣言しても実装
		Algorithm1 algoA2 = (String name) -> {
			System.out.println("algo2 : 名前は " + name);
		};

		ServiceA sA1 = new ServiceA();
		ServiceA sA2 = new ServiceA();

		// アルゴリズムロジックを切り分け
		sA1.setLogic(algoA1);
		sA2.setLogic(algoA2);

		// それぞれのアルゴリズムを実施
		// doProcess("A") → perform("A") に渡されるだけ
		// ラムダ式の引数の型は Algorithm1 の抽象メソッドから型推論される
		sA1.doProcess("A");
		sA2.doProcess("B");

		// ラムダ式の省略パターン
		System.out.println("\n・ ラムダ式の省略パターン");
		// Algorithm2 インタフェースには、String perform(String name); 1つしかない
		// 実装が1行の時
		// 引数を宣言
		Algorithm2 algoB1 = (String name) -> {
			return "Algorithm2-1　: " + name;
		};
		// 引数を型推論
		Algorithm2 algoB2 = (name) -> {
			return "Algorithm2-2　: " + name;
		};
		// 引数が一つだけなら () を省略できる
		Algorithm2 algoB3 = name -> {
			return "Algorithm2-3　: " + name;
		};
		// 実装が1行の時 return と {} を省略できる
		Algorithm2 algoB4 = (String name) -> "Algorithm2-4　: " + name;
		// voidメソッドも同じく1行だけなら {} を省略できる
		Algorithm1 algoA3 = (String name) -> System.out.println("Algorithm1-3 : " + name);
		System.out.println(algoB1.perform("A"));
		System.out.println(algoB2.perform("B"));
		System.out.println(algoB3.perform("C"));
		System.out.println(algoB4.perform("D"));
		algoA3.perform("E");

		//  ラムダ式の引数名は、外側のスコープにある変数名と同じ名前を使えない（シャドーイング禁止）
		System.out.println("\n-- ラムダ式では外側で既に宣言された変数と同名の引数名は使えない(コンパイルエラー)");
		String str1 = "ABC";
		System.out.println("String str1 宣言 : " + str1);
		// str1 は使えないので str2 で実装
		Algorithm1 algoA4 = str2 -> System.out.println("algo1　: name is " + str2);
		System.out.println(" ↓ str1 は使えないので str2 でラムダ式実装 (str2 -> 実装)");
		algoA4.perform(str1);

		// final または effectively final であれば、ラムダ式内で外側の変数を使える
		System.out.println("\n-- final または effectively final であれば、ラムダ式内で外側の変数を使える");
		String str3 = "ABCDE";
		String str4 = "FGHIJ";
		str4 = "KLN";
		final String str5 = "MOP";
		System.out.println("String str1 宣言 : " + str1);
		// str1 は使えないので str2 で実装
		Algorithm1 algoA5 = str2 -> System.out.println("algo1　: " + str3 + " and " + str2);
		// Algorithm1 algoA6 = str2 -> System.out.println("algo1　: " + str4 + " and " + str2); <- effectively final じゃないのでできない
		Algorithm1 algoA7 = str2 -> System.out.println("algo1　: " + str5 + " and " + str2);
		algoA5.perform(str1);
		System.out.println("effectively final ではない str4 を使おうとしてもコンパイルエラー");
		algoA7.perform(str1);

		// ラムダ式が参照する外側のローカル変数は、final または effectively final でなければならない。
		// そのため、ラムダ式内でその変数を変更することはできない
		System.out.println("\n-- 参照する変数は final として扱うのでラムダ式内でも変更禁止");
		Algorithm1 algoA8 = str2 -> {
			// ここで str3 = "FGH" はできない
			System.out.println("algo1　: " + str3 + " and " + str2);
		};
		algoA8.perform(str1);

	}
}







