package practise17;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main {

	public static void main(String[] args) {

		// ローカライズ
		// デフォルトのロケール インスタンス作成
		Locale localeDEA = Locale.getDefault();
		// 日本
		Locale localeJPA = new Locale("ja", "JP"); // 言語コードが ja , 国コードが JP
		// アメリカ
		Locale localeUSA = Locale.US; // ロケール定数
		// ビルダーで構築
		Locale localeJPB = new Locale.Builder().setLanguage("ja").setRegion("JP").setScript("Jpan").build();
		// forLanguageTag
		Locale localeUSB = Locale.forLanguageTag("en-US-x-Ivariant-POSIX");

		// プロパティファイルの読込
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		// ロケール指定
		// ResourceBundle.getBundle("messages", Locale.JAPAN);
		// この時 messages_ja_JP.properties が存在しなかった場合
		// 次は messages_ja.properties を探す

		// 年月日のロケール
		// DateTimeFormatter クラスの定数
		// BASIC_ISO_DATE

		// 数値のフォーマット
		// NumberFormat クラス
		NumberFormat numFA = NumberFormat.getInstance();		 // 通常の数値フォ－マット
		NumberFormat numFB = NumberFormat.getIntegerInstance();	 // 整数型数値
		NumberFormat numFC = NumberFormat.getCurrencyInstance(); // 通貨のフォーマット
		NumberFormat numFD = NumberFormat.getPercentInstance();	 // パーセントのフォーマット

		System.out.println(numFA.format(1000.1));
		System.out.println(numFB.format(1000.1));
		System.out.println(numFC.format(1000.1));
		System.out.println(numFD.format(0.1));


	}

}
