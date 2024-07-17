package tracker.files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tracker.tournament.Match;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;

public class FileHandlerMatches implements FileHandler<Integer, Match>{
    private final static String matchesFile = "src/tracker/files/matches.json";

    @Override
    public HashMap<Integer, Match> uploadFromFile(){
        HashMap<Integer, Match> map = new HashMap<>();

        try(Scanner scanner = new Scanner(new FileInputStream(matchesFile))){
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNextLine()){
                builder.append(scanner.nextLine()).append("\n");
            }
            String json = builder.toString();
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                    new LocalDateTimeAdapter()).create();
            Match[] matches = gson.fromJson(json, Match[].class);

            for(Match mtc : matches){
                map.put(mtc.getID(), mtc);
            }
        }
        catch (IOException exception) {
            System.out.println("Cannot open input file");
        }
        return map;
    }
}
