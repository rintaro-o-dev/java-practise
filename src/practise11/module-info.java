module practise11 {

	// 以下はサンプル
	exports com.example.p01;
	exports com.example.p01 to SampleModule;

	requires SampleModule;
	requires transitive SampleModule;
	requires static lombok;

	opens com.example.p01;
	opens com.example.p01 to SampleModule;

	uses com.example.SampleService;

	provides com.example.SampleService with com.example.impl.SampleServiceImpl;
}
// module-info.java でモジュール宣言することで初めてモジュール化する
// どの"パッケージ"を公開するか
// どの"モジュール"を利用するか
// (eclipse の場合ビルドパスにソースフォルダーとして追加すると通るかも)

// 情報隠蔽 : アプリケーションがどのようなパッケージやクラスで成り立っているかを隠蔽する
// カプセル化とは違う！（データ隠蔽は関係ない）
// 実行
// java --module-path lib -m practise11/com.emsample.p1.Hello

// module	モジュール名を宣言
// exports	パッケージを公開
// exports … to	特定モジュールにだけ公開
// requires	他モジュールを利用
// requires transitive	依存を下流にも伝える
// requires static	コンパイル時だけ依存
// opens	リフレクション公開　リフレクション＝実行時にクラスの構造を調べたり、メソッドを呼び出したりする仕組み。
//          JSON・DI・ORM など “フレームワークの魔法” は全部これで動いている。
// opens … to	特定モジュールにだけリフレクション公開
// uses	サービス利用
// provides … with	サービス実装提供