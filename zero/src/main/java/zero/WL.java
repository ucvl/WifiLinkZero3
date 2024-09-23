package zero;

import common.GetLastVersion;

public class WL {
   
    public static void main(String[] args) {
        String version = GetLastVersion.getLatestVersion();
        if (version != null) {
            System.out.println("Latest version: " + version);
        } else {
            System.out.println("Failed to get latest release.");
        }
    }


}
