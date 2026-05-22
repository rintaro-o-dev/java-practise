package practise12;

public class Main {

	public static void main(String[] args) {

		// Runnable 型の処理を先に作成してから Thread作成
		Runnable rA = () -> System.out.println("thread A run");
		Thread tA = new Thread(rA);
		tA.run();
		tA.start();

		// Thread 宣言時にラムダで処理を注入
		Thread tB = new Thread(() -> {
			System.out.println("thread B run");
		});
		tB.run();
		tB.run(); // run() は start() 前なら何度も使える
		tB.start();
		try {
			tB.join(); // tB.start(); が終了するまで待つ
		} catch (InterruptedException e) { // join 利用時はこの例外処理のため try - catch 必須
			System.out.println("InterruptedException の発生");
		}
		// Thread の start() でスレッド作成実行は "1回" しかできない
		System.out.println("start() の1回目呼び出し");
		try {
			tB.start();
		} catch (IllegalThreadStateException e) {
			System.out.println("start() の2回目呼び出しで IllegalThreadStateException が発生");
		}
		System.out.println("check point A");
		tB.run(); // start() 後は thread が TERMINATED になり、Runnable を内部的に削除する挙動があることから、 run() も使えなくなる
		System.out.println("check point B");

		Runnable rB2 = () -> System.out.println("thread B2 run");
		tB = new Thread(rB2); // 再利用のため宣言しなおし
		tB.run();
		tB.start();

	}
}
