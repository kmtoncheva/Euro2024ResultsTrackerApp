package tracker.files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tracker.tournament.Player;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FileHandlerPlayers implements FileHandler<String, Player>{
    private final static String playersFile = "src/tracker/files/newPlayers.json";

    @Override
    public HashMap<String, Player> uploadFromFile(){
        HashMap<String, Player> map = new HashMap<>();

        try(Scanner scanner = new Scanner(new FileInputStream(playersFile))){
           StringBuilder builder = new StringBuilder();
            while(scanner.hasNextLine()){
                builder.append(scanner.nextLine()).append("\n");
            }

            String json = builder.toString();
            Gson gson = new GsonBuilder().create();
            Player[] players = gson.fromJson(json, Player[].class);

            for(Player pl : players){
                map.put(pl.getID(), pl);
            }
        }
        catch (IOException exception) {
            System.out.println("Cannot open input file");
        }
        return map;
    }
}
