package Function;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class WebSearchWord {

    public static void main(String[] args) {
        try {
            String appId = "20231118001884397";
            String key = "CBU2lXQbUBzxg9xhmTnR";
            String inputText = "Hello";
            String salt = "1435660288";
            String sign = appId + inputText + salt + key;
            String urlStr = "http://api.fanyi.baidu.com/api/trans/vip/translate?q=" + URLEncoder.encode(inputText, "UTF-8") + "&from=en&to=zh&appid=" + appId + "&salt=" + salt + "&sign=" + md5(sign);

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println(response.toString());

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}