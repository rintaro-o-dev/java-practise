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

// モジュールのアクセス関係
// A : 名前付きモジュール ...module-info.java でモジュール宣言しているモジュール
// B : 自動モジュール ...モジュールパス上に配置されたモジュール
// C : 無名モジュール ...クラスパス上に配置されたモジュール
//  ↓
// 参照できるモジュール
// A -> B
// B -> A(--add-modules が必要) , C
// C -> A(--add-modules が必要) , B(--add-modules が必要)

// export されていないモジュールを一時的に公開する(緊急的な用途)
// javac ... --add-exports foo(対象モジュール)/com.test(公開したいパッケージ)=hello(利用するモジュール)

// module-info.java に設定したモジュールの設定情報を調べる
// java --describe-mojule
// jmod describe

// requires transitive の利用
// A -> B -> C の参照関係がある時、 B で requires transitive C とすると、
// A でも Cが使えるようになる (B を参照するモジュールは C が使えるようになる)

// モジュール移行アプローチ
// ボトムアップ移行 ...最も依存"されている"モジュール(末端)から名前付きモジュールに移行していく
// トップダウン移行 ...依存"している"モジュールから名前付きモジュールに移行していく
// └─無名モジュールは一時的に自動モジュールに変換しておくのが大事 (名前付きモジュールは無名モジュールを参照できない！)

// App モジュールが lib モジュールの参照をしっかり定義していれば、
// java --module-path project/module --module App(モジュール名)/Main(完全修飾クラス名)
// でいい

// java.util.ServiceLoader
// ServiceLoader（SPI）は “プラグイン方式” を実現する仕組み。
//
// SPI を実現するためのクラス -> アプリとライブラリをインターフェースで繋げることでライブラリの改修によるアプリ側への影響を抑えるモデル
// META-INF/service 内のファイルを検索する
// load メソッドには class リテラルを渡す
// クラスファイルをロードするためのクラスではない！
//
// App モジュールの module-info.java
// uses app.Hello; (app.Hello インタフェースを SPI として使う宣言)
// lib モジュールの module-info.java
// provides app.hell with lib.HelloImpl; (app.Hello インタフェースを実装しているのが lib.HelloImpl であることを宣言)

// jdeps
// jdeps は “依存関係と内部 API の検出” に使う。
//
// jdeps XX.jar
//   └─ jar が依存しているモジュールやパッケージを確認できる
// jdeps -verbose:class -cp lib/tools.jar com.sun.tools.jdeps.Main
//   └─ jar のクラスレベルの依存を確認できるが、"JDK の内部APIを含まない"
// jdeps -jdkinternals -cp lib/tools.jar com.sun.tools.jdeps.Main
//   └─ jar のクラスレベルの依存を確認でき、"JDK の内部APIを含む"
// jdeps -profile demo/jfc/XX.jar
//   └─ jar が依存しているプロファイルを検索





