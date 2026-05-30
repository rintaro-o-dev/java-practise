package practise15;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// java.io.File
		// "パス" を扱うのみであり、ファイルやフォルダそのものは扱えない
		// “パス情報とファイルのメタ情報を扱うだけ” のクラスで、“ファイルの中身” は扱えない。
		Class<?> clazz = File.class;

		System.out.println("=== Methods ===");
		for (var m : clazz.getDeclaredMethods()) {
			System.out.println(m);
		}

		System.out.println("\n=== Fields ===");
		for (var f : clazz.getDeclaredFields()) {
			System.out.println(f);
		}

		System.out.println("\n=== Constructors ===");
		for (var c : clazz.getDeclaredConstructors()) {
			System.out.println(c);
		}

		// ディレクトリ内の一覧を 文字列 として取得する
		// list()
		// ディレクトリ内の一覧を File として取得する
		// listFiles()

		// java.io.Reader
		// テキストデータを読み込むためのクラス
		// java.io.FileReader : 文字を扱う（テキストファイル用）
		// java.io.BufferedReader : 文字ストリームをバッファリングして高速化

		// java.io.InputStream : バイトを扱う基本クラス、バッファを扱えない
		// java.io.BufferedInputStream : InputStream をバッファリングして高速化（写真とか）
		// java.io.BufferedInputStream
		// readNBytes() は任意のバイトずつ読み込む
		// readAllBytes() は全てのバイトを一度に読み込む

		// java.io.BufferedWriter
		// BufferedWriter は FileWriter を包む（wrap）
		// 基本の使い方
		// try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
		// bw.write("こんにちは");
		// bw.newLine(); // 改行
		// bw.write("世界");
		// }
		// flush() バッファとファイルを同期させる

		// java.io.Scanner
		// “入力をトークン（単語・数値など）として読み取るための便利クラス”。
		// Reader や InputStream を “人間が使いやすくしたラッパー”

		// scanner
		Scanner scA = new Scanner(System.in);
		Scanner scB = new Scanner("1 2 3 4 5");
		int nB = scB.nextInt();
		String sB = scB.next();
		String lineB = scB.nextLine();
		System.out.println(nB);
		System.out.println(sB);
		System.out.println(lineB);

		Scanner scC = new Scanner("1,2,3,4,5");
		scC.useDelimiter(",");
		int nC = scC.nextInt();
		String sC = scC.next();
		String lineC = scC.nextLine();
		System.out.println(nC);
		System.out.println(sC);
		System.out.println(lineC);

		File f = new File("./practise15_input01.txt");
		System.out.println("exists = " + f.exists());
		System.out.println("path   = " + f.getAbsolutePath());

		Scanner scD = null;
		try {
			scD = new Scanner(new File("./practise15_input01.txt"));
		} catch (FileNotFoundException e) {
		}
		while (scD.hasNext()) {
			System.out.println(scD.next());
		}

		// FileWriter
		// FileWriter out = new FileWriter("out.txt" , false); は「上書きモードでファイルを書き込む」設定。
		// false は “追記しない（=毎回ファイルを消して書き直す）” を意味する。
		// new FileWriter("out.txt", false); // 上書きモード
		// new FileWriter("out.txt", true); // 追記モード
		// writer で書込
		// 追記モードで書込
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("./practise15_out01.txt", true))) {
			bw.write("書込 " + LocalDateTime.now());
			bw.newLine(); // 改行
		} catch (IOException e) {
		}

		// ファイルを scanner で読込
		System.out.println("\n・ scanner で読込 → トークン扱い(空白で区切られる)");
		try {
			scD = new Scanner(new File("./practise15_out01.txt"));
		} catch (FileNotFoundException e) {
		}
		while (scD.hasNext()) { // 次があれば
			System.out.println(scD.next()); // 次のポイントの文字を取出
		}

		// ファイルを Reader で読込
		System.out.println("\n・ reader で読込 → そのまま読み込み");
		BufferedReader brD = null;
		try {
			brD = new BufferedReader(new FileReader("./practise15_out01.txt"));
		} catch (FileNotFoundException e) {
		}
		String lineD;
		try {
			while ((lineD = brD.readLine()) != null) { // null でなければ取出
				System.out.println(lineD); // 行そのまま取出
			}
		} catch (IOException e) {
		}

		// 上書きモードで書込
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("./practise15_out01.txt", false))) {
			bw.write("上書 " + LocalDateTime.now());
			bw.newLine(); // 改行
		} catch (IOException e) {
		}

		// コンソールの入力
		// java.io.Consol
		// readLine() 入力文字も表示される : 戻り値は String
		// readPassword() 入力文字は表示されない : 戻り値は char[]
		System.out.println("\n・ コンソールの入力");
		Console consoleA = System.console();
		String strA;
		char[] passA;
		try {
			strA = consoleA.readLine();
			passA = consoleA.readPassword(); // 戻り値は char[] で、入力文字は見えない
			System.out.println("readLine() : " + strA);
			System.out.println("readPassword() : " + String.valueOf(passA));
		} catch (NullPointerException e) {
			System.out.println("cmd 等では NullPointerException が発生せずに入力できるかも");
		}
		// IDE（Eclipse / IntelliJ / VSCode）ではこっち
		Scanner sc = new Scanner(System.in);
		System.out.print("入力してください: ");
		strA = sc.nextLine();
		System.out.print("パスワード（見える）: ");
		passA = sc.nextLine().toCharArray();
		System.out.println("sc.nextLine() : " + strA);
		System.out.println("sc.nextLine().toCharArray() : " + String.valueOf(passA)); // 配列を文字列で取り出す明示

		// シリアライズ
		// implements Serializable したインスタンのみシリアライズ（インスタンスの書出）ができる
		// バイナリで書きだされるので読めない
		System.out.println("\n・ シリアライズ");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./sample.ser"))) {

			Sample samA = new Sample("joi", 15);
			out.writeObject(samA);

		} catch (Exception e) {
		}
		// デシリアライズ
		// これで復元する
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("./sample.ser"))) {

			Sample samB = (Sample) in.readObject();
			System.out.println("復元: " + samB);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// カスタムシリアライズ
		System.out.println("\n・ カスタムシリアライズ");
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./sample2.ser"))) {

			Sample2 samC = new Sample2("jane", 24);
			out.writeObject(samC);

		} catch (Exception e) {
		}
		// カスタムデシリアライズ
		// これで復元する
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("./sample2.ser"))) {

			Sample2 samD = (Sample2) in.readObject();
			System.out.println("復元: " + samD.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ファイル操作
		// File → パスも扱うし、ファイル操作もする（古い）
		// Path → パスだけ扱う（新しい）
		// Files → ファイル操作を担当（新しい）
		//
		// java.nio.file.Path は「ファイルパスを表す“オブジェクト
		System.out.println("\n・ ファイル操作");
		Path p1 = Path.of("./data1.txt");
		Path p2 = Paths.get("./data2.txt"); // 古い書き方
		Path p3 = Paths.get("src", "./data3.txt");
		Path p4 = new File("./sample001").toPath(); // ディレクトリ

		System.out.println("存在チェック p1 : " + Files.exists(p1));
		System.out.println("存在チェック p2 : " + Files.exists(p2));
		System.out.println("存在チェック p3 : " + Files.exists(p3));
		System.out.println("存在チェック p4 : " + Files.exists(p4));
		// Path → パスを表すだけ
		// Files → ファイル操作を全部やる
		if (Files.exists(p1) == false) { // 存在チェック
			try {
				Files.createFile(p1);
				Files.writeString(p1, "ABCDEFG\nHIJ");
				System.out.println(Files.readString(p1));
				System.out.println(Files.readAllLines(p1));
				Files.copy(p1, p2);
				Files.move(p2, p3);
				System.out.println("存在チェック p1 : " + Files.exists(p1));
				System.out.println("存在チェック p2 : " + Files.exists(p2));
				System.out.println("存在チェック p3 : " + Files.exists(p3));
				Files.delete(p1);
				System.out.println(Files.readAllLines(p3));
				Files.delete(p3);
				System.out.println("存在チェック p1 : " + Files.exists(p1));
				System.out.println("存在チェック p2 : " + Files.exists(p2));
				System.out.println("存在チェック p3 : " + Files.exists(p3));
			} catch (IOException e) {
			}
		}

		Path p5 = Paths.get("./sample001", "./dir01");
		Path p6 = p5.resolve("data6.txt");
		System.out.println("path   = " + p5.toAbsolutePath());
		System.out.println("path   = " + p6.toAbsolutePath());
		try {
			Files.createDirectories(p5);
			System.out.println("存在チェック p5 : " + Files.exists(p5));
			Files.createFile(p6);
			System.out.println("存在チェック p6 : " + Files.exists(p6));
			try (BufferedWriter out = Files.newBufferedWriter(p6, StandardOpenOption.APPEND)) {
				out.write("AAABBBCCC\nDDDEEE\nFFF");
			}
			BufferedReader in = Files.newBufferedReader(p6);
			try (in) {
				in.lines().forEach(System.out::println);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Files.walk(p4).sorted((a, b) -> b.compareTo(a)) // 深い階層から削除
					.forEach(p -> {
						try {
							Files.delete(p);
							System.out.println("削除: " + p);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});

		} catch (IOException e) {
			e.printStackTrace();
		}

		// walkFileTree
		// ディレクトリに入る時
		// ディレクトリから出るとき
		// ファイルの処理を開始するとき
		// ファイルの処理で例外がは接したとき
		// ※ ファイルの処理が終了したときは管理しない！
	}

}
