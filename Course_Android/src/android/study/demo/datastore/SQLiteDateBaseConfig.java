package android.study.demo.datastore;


public class SQLiteDateBaseConfig {
	private static final String s_DataBaseName = "Lesson7DemoDataBase";
	private static final int s_Version = 1;

	public static String GetDataBaseName() {
		return s_DataBaseName;
	}

	public static int GetVersion() {
		return s_Version;
	}
}
