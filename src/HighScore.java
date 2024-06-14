import java.io.*;

public class HighScore {
    private static final String FILENAME = "highscore.txt";

    public static void highscore(int score) {
        if (score > getHighScore()) {
            setHighScore(score);
        }
    }

    public static int getHighScore() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            return Integer.parseInt(br.readLine());
        } catch (IOException e) {
            return 0;
        }
    }

    private static void setHighScore(int score) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            bw.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
