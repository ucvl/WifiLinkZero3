package common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/*
 * 本程序主要是得到本项目的发布版本
 */
public class GetLastVersion {
	private static final String REPO_OWNER = "ucvl";
	private static final String REPO_NAME = "WifiLinkZero3";
	private static final String API_URL = "https://api.github.com/repos/" + REPO_OWNER + "/" + REPO_NAME
			+ "/releases/latest";

	public static String getLatestVersion() {
		try {
			URL url = new URL(API_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/vnd.github.v3+json");

			if (connection.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder content = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					content.append(inputLine);
				}
				in.close();
				connection.disconnect();

				JSONObject json = new JSONObject(content.toString());
				String latestTag = json.getString("tag_name");
				// 移除 'V' 前缀（如果存在）
				return latestTag.startsWith("V") ? latestTag.substring(1) : latestTag;
			} else {
				System.out.println("HTTP error code: " + connection.getResponseCode());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
