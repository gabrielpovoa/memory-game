package storage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveManager {

    private static final String FILE_PATH = "scores.txt";

    public static void saveScore(int score, int timeSeconds) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH, true);

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            writer.write("Pontuação: " + score +
                    " | Tempo: " + timeSeconds + "s" +
                    " | Data: " + timestamp +
                    "\n");

            writer.close();

            System.out.println("🏆 Placar salvo com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao salvar placar: " + e.getMessage());
        }
    }
}
