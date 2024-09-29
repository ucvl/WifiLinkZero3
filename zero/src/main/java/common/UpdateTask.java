package common;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class UpdateTask implements Runnable {

    private static final String GITHUB_API_URL = "https://api.github.com/repos/ucvl/WifiLinkZero3/releases/tags/";
    private static String currentVersion = "V1.0.0"; // 假设当前版本为V1.0.0

    @Override
    public void run() {
        while (true) {
            try {
                // 每24小时检查一次
                System.out.println("Checking for updates...");
                Thread.sleep(60 * 1000);

                // 获取最新版本的Tag标签
                String latestVersion = GetLastVersion.getLatestVersion();
                System.out.println("Latest version: " + latestVersion);

                // 检查是否有新版本
                if (!latestVersion.equals(currentVersion)) {
                    System.out.println("New version available: " + latestVersion);

                    // 构建下载URL
                    String zipDownloadUrl = "https://github.com/ucvl/WifiLinkZero3/releases/download/" + latestVersion + "/zero-latest.zip";
                    System.out.println("Download URL: " + zipDownloadUrl);

                    // 下载最新版本的ZIP包
                    downloadLatestZip(zipDownloadUrl);

                    // 解压ZIP包
                    String jarFilePath = "zero-" + latestVersion + ".jar";
                    unzip("zero-latest.zip", jarFilePath);

                    // 更新当前版本
                    currentVersion = latestVersion;

                    // 关闭当前程序
                    System.out.println("Shutting down current version...");
                    Runtime.getRuntime().exec("pkill -f zero-" + currentVersion + ".jar");

                    // 启动最新版本的JAR包
                    System.out.println("Starting new version...");
                    Runtime.getRuntime().exec("java -jar " + jarFilePath);
                    System.out.println("Running latest JAR: " + jarFilePath);
                } else {
                    System.out.println("No new version available.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void downloadLatestZip(String zipDownloadUrl) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(zipDownloadUrl).openConnection();
        connection.setRequestMethod("GET");
        InputStream in = connection.getInputStream();
        FileOutputStream out = new FileOutputStream("zero-latest.zip");
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.close();
        connection.disconnect();
        System.out.println("Downloaded latest ZIP: zero-latest.zip");
    }

    private void unzip(String zipFilePath, String jarFilePath) throws IOException {
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            if (zipEntry.getName().endsWith(".jar")) {
                FileOutputStream fos = new FileOutputStream(jarFilePath);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
        System.out.println("Unzipped to: " + jarFilePath);
    }
}
