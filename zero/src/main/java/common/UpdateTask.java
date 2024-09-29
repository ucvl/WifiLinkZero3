package common;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class UpdateTask implements Runnable {

    private static final String GITHUB_API_URL = "https://api.github.com/repos/ucvl/WifiLinkZero3/releases/latest";
    private static String currentVersion = "1.0.0"; // 假设当前版本为1.0.0
    private static final String USERNAME = "ucvl";
    private static final String TOKEN = "";//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

    @Override
    public void run() {
        while (true) {
            try {
                // 每24小时检查一次
                System.out.println("Checking for updates...");
                Thread.sleep(10 * 1000); // 每24小时检查一次

                // 获取最新版本的Tag标签
                String latestVersion = GetLastVersion.getLatestVersion();
                System.out.println("Latest version: " + latestVersion);

                // 检查是否有新版本
                if (!latestVersion.equals(currentVersion)) {
                    System.out.println("New version available: " + latestVersion);

                    // 构建下载URL
                    String jarDownloadUrl = "https://maven.pkg.github.com/ucvl/WifiLinkZero3/ucvl/zero/" + latestVersion + "/zero-" + latestVersion + ".jar";
                    System.out.println("Download URL: " + jarDownloadUrl);

                    // 下载最新版本的JAR包
                    downloadLatestJar(jarDownloadUrl);

                    // 更新当前版本
                    currentVersion = latestVersion;

                    // 关闭当前程序
                    System.out.println("Shutting down current version...");
                    Runtime.getRuntime().exec("killall java");

                    // 启动最新版本的JAR包
                    System.out.println("Starting new version...");
                    Runtime.getRuntime().exec("java -jar zero-" + latestVersion + ".jar");
                    System.out.println("Running latest JAR: zero-" + latestVersion + ".jar");
                } else {
                    System.out.println("No new version available.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void downloadLatestJar(String jarDownloadUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(jarDownloadUrl).openConnection();
        connection.setRequestMethod("GET");

        // 设置基本身份验证头
        String auth = USERNAME + ":" + TOKEN;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

        InputStream in = connection.getInputStream();
        FileOutputStream out = new FileOutputStream("zero-latest.jar");
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.close();
        connection.disconnect();
        System.out.println("Downloaded latest JAR: zero-latest.jar");
    }
}
