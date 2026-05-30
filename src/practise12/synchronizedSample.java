package practise12;

public class synchronizedSample {

	private int a;
	private int i;
	private final Object lockObj = new Object(); // lockObj が null だとエラーになる！！

	public synchronized int counterA() {
		a = 1000;
		for (i = 0; i < a; i++) {
			a = a + i;
		}
		return a;
	}
	// this のモニターをロック
	// スレッドA で実行すると、スレッドAで this のモニタをロックするので、
	// スレッドB で実行しようとすると　待ち(BLOCKED)　になる。
	// スレッドA のcounterAが終了したら解放
	// (メソッド全体の排他)
	//
	// this のモニタをロック = synchronized を持つメソッド全てがロックされるので、
	// counterC も他スレッドで実行できないし、スレッドAでも同時に実行できない
	// counterB は this のロックではないので、counterA と同時に実行できる。
	// "synchronized メソッド同士の排他"

	public int counterB() {
		a = 1000;
		synchronized (lockObj) { // lock モニター（鍵）はオブジェクト型
			for (i = 0; i < a; i++) {
				a = a + i;
			}
		}
		return a;
	}
	// lockObj をロック
	// synchronized ブロック内の処理をロックしている、
	// スレッドA で実行すると、スレッドA で実行した counterB の synchronized ブロック内の処理がロックされるので
	// スレッドB で実行しようとすると メソッドの実行まっではできるが、その処理にたどり着いたときに　待ち(BLOCKED)　になる。
	// スレッドA の counterB の synchronized ブロック内の処理が終了したら解放
	// (ブロックだけ排他)
	//
	// this をロックしないので、counterA や couterC は普通に使える
	// "counterB 同士の排他"

	public synchronized int counterC() {
		a = 2000;
		for (i = 0; i < a; i++) {
			a = a + i;
		}
		return a;
	}
}
// this の鍵（インスタンスロック）
//     ├─ counterA()  ← synchronized
//     └─ counterC()  ← synchronized
// lockObj の鍵（専用ロック）
//     └─ counterB()  ← synchronized(lockObj)

// happens-before 関係
// A が終了したときに B では最新の値が保障されている
// メモリ可視性
// 次に実行するスレッドが最新更新後の値をメモリから読み取ることができる