import java.io.*;
import java.util.*;

public class snippet4 {

    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
       
        Map<String, String> wordMap = new HashMap<>();
        Map<String, Integer> replacedWordsCount = new HashMap<>();
        StringBuilder translatedText = new StringBuilder();

        try {
           
            List<String> findWords = new ArrayList<>();
            Scanner scanner = new Scanner(new File("C:\\Users\\keert\\OneDrive\\Desktop\\TranslateWords Challenge\\find_words.txt"));
            while (scanner.hasNextLine()) {
                findWords.add(scanner.nextLine().trim());
            }
            scanner.close();

            scanner = new Scanner(new File("C:\\Users\\keert\\OneDrive\\Desktop\\TranslateWords Challenge\\french_dictionary.csv"));
            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                wordMap.put(tokens[0], tokens[1]);
            }
            scanner.close();

            scanner = new Scanner(new File("C:\\Users\\keert\\OneDrive\\Desktop\\TranslateWords Challenge\\t8.shakespeare.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("(?<=[.?!])\\s+");
                for (String word : words) {
                    String originalWord = word;
                    word = word.toLowerCase();
                    if (findWords.contains(word)) {
                        String frenchWord = wordMap.get(word);
                        if (frenchWord != null) {
                            if (Character.isUpperCase(originalWord.charAt(0))) {
                                frenchWord = frenchWord.substring(0, 1).toUpperCase() + frenchWord.substring(1);
                            }
                            translatedText.append(frenchWord);
                            replacedWordsCount.put(originalWord, replacedWordsCount.getOrDefault(originalWord, 0) + 1);
                        } else {
                            translatedText.append(originalWord);
                        }
                    } else {
                        translatedText.append(originalWord);
                    }
                }
                translatedText.append("\n");
            }
            scanner.close();

           
            PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\keert\\OneDrive\\Desktop\\TranslateWords Challenge\\t8.shakespeare.translated.txt"));
            writer.write(translatedText.toString());
            writer.close();


            writer = new PrintWriter(new FileWriter("C:\\Users\\keert\\OneDrive\\Desktop\\translated_shakspeare2.csv"));
            writer.write("Word,Frequency\n");
            for (String word : replacedWordsCount.keySet()) {
                writer.write(word + "," + replacedWordsCount.get(word) + "\n");
            }
            writer.close();
            
            long endTime = System.currentTimeMillis();
            long timeTaken = (endTime - startTime);
            
            System.out.println("Time to process  " + timeTaken + "ms");
            File file=new File("C:\\Users\\keert\\OneDrive\\Desktop\\TranslateWords Challenge\\t8.shakespeare.translated.txt");
            double bytes=file.length();
            double megabytes=(bytes/(1024*1024));
            System.out.println(megabytes+"mb");
            
            PrintWriter writer2 = new PrintWriter(new FileWriter("C:\\Users\\keert\\OneDrive\\Desktop\\TranslateWords Challenge\\performance.txt"));
            writer2.write("Time to process  " + timeTaken + "ms"+"\n"+megabytes+"mb");
            writer2.close();
      

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
}