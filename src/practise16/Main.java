package practise16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {

		// JDBC
		// DBMS との連携時の例外を提供
		// JDBC の URL 書式
		// jdbc:mysql://localhost/test
		try {
			// DB に接続
			Connection conA = DriverManager.getConnection("jdbc:mysql//localhost:1527/db/sample");
			System.out.println(conA);
			// DB 接続を解除
			conA.close();

		} catch (SQLException e) {
			System.out.println("local で mysql 起動してれば繋がる");
		}

		// java.sql.Statement
		// パラメータを受け取らない SQL を実行する
		// java.sql.PreparedStatement
		// パラメータを受け取る SQL を実行する

		try {
			// DB に接続
			Connection conA = DriverManager.getConnection("jdbc:mysql//localhost:1527/db/sample");
			System.out.println(conA);
			PreparedStatement psteA = conA.prepareStatement("select * from emp");

			var sqlA = "delete from SmapleTable where id = ?";
			try (var ps = conA.prepareStatement(sqlA)) {
				ps.setInt(1, 200); // 第一引数には何番目かを書くので１から （０はエラー！！）
				ps.executeUpdate(); // データの挿入や更新、削除に用いる （検索には使えないので注意！）
				ps.executeUpdate("delete from SmapleTable where id = ?"); // これは必ず実行時エラー
				ps.execute(); // これは false になる
				ps.executeBatch(); // ふくすうSQL同時実行できる
			}

			var sqlB = "select * from emp where id = ?";
			try (var ps = conA.prepareStatement(sqlB)) {
				ps.setInt(1, 200); // 必ず実行前にセット
				ResultSet rsA = ps.executeQuery(); // 検索するならこれ
				while (rsA.next()) { // カーソルをセット（行を降りていく）
					System.out.println(rsA.getInt(1) + " : "); // 列番号
					System.out.println(rsA.getString("name")); // カラム名
				}
				ps.execute(); // これは true になる
			}

			/// ストアドの呼び出しトライ
			try (var cs = conA.prepareCall("call sample_proc(?,?)")) {
				cs.setInt(1, 200); // 第一引数には何番目かを書くので１から （０はエラー！！）
				cs.execute(); // これは false になる
			}

			// DB 接続を解除
			conA.close();

		} catch (SQLException e) {
			System.out.println("local で mysql 起動してれば成功するはず");
		}

	}
}
