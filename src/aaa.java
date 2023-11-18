import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class aaa {
    public static void main(String[] args) throws IOException {

        File file = new File("E:\\Desktop\\EnglishMemorySystem\\resources\\EnglishWords.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String s = null;
        Properties properties = new Properties();
        while ((s = bufferedReader.readLine()) != null) {
            Properties map = getMap();
            Set<Object> objects = map.keySet();
            for (Object object : objects) {
                String key = (String) object;
                String o = (String) map.get(object);
                s = s.replaceAll(key, o);
            }

            String[] split = s.split("\\[");

            if (split.length >= 2) {
                String s1 = split[0].strip();//单词
                String s2 = split[1].split("]")[0].strip();//英标
                String s3 = split[1].split("]")[1].strip();

                properties.put(s1,s2+"|" + s3);

            }

        }

        File file1=new File("E:\\Desktop\\EnglishMemorySystem\\resources\\EnglishWords.properties");

        properties.store(new FileWriter(file1),"EnglishWords");


    }


    public static Properties getMap() {
        Properties properties = new Properties();
        properties.put("\uF042", "ɑ");
        properties.put("\uF037", ",");
        properties.put("\uF028", "(");
        properties.put("\uF029", ")");
        properties.put("\uF03A", "ː");
        properties.put("\uF061", "a");
        properties.put("\uF043", "ɔ");
        properties.put("\uF05C", "ɜ");
        properties.put("\uF069", "i");
        properties.put("\uF075", "u");
        properties.put("\uF051", "ʌ");
        properties.put("\uF045", "ə");
        properties.put("\uF049", "ɪ");
        properties.put("\uF04A", "ʊ");
        properties.put("\uF065", "e");
        properties.put("\uF041", "æ");
        properties.put("\uF070", "p");
        properties.put("\uF074", "t");
        properties.put("\uF06B", "k");
        properties.put("\uF066", "f");
        properties.put("\uF057", "θ");
        properties.put("\uF073", "s");
        properties.put("\uF062", "b");
        properties.put("\uF064", "d");
        properties.put("\uF05E", "g");
        properties.put("\uF076", "v");
        properties.put("\uF054", "ð");
        properties.put("\uF07A", "z");
        properties.put("\uF046", "ʃ");
        properties.put("\uF068", "h");
        properties.put("\uF06A", "j");
        properties.put("\uF072", "r");
        properties.put("\uF056", "ʒ");
        properties.put("\uF077", "w");
        properties.put("\uF06D", "m");
        properties.put("\uF06E", "n");
        properties.put("\uF04E", "ŋ");
        properties.put("\uF06C", "l");
        properties.put("\uF02F", "/");
        properties.put("\uF035", "'");

        return properties;
    }
}
