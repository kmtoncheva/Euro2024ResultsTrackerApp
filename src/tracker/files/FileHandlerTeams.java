package tracker.files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tracker.tournament.Team;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FileHandlerTeams implements FileHandler<String, Team>{
    private final static String teamsFile = "src/tracker/files/newTeams.json";

    @Override
    public HashMap<String, Team> uploadFromFile(){
        HashMap<String, Team> map = new HashMap<>();

        try(Scanner scanner = new Scanner(new FileInputStream(teamsFile))){
            StringBuilder builder = new StringBuilder();
            while(scanner.hasNextLine()){
                builder.append(scanner.nextLine()).append("\n");
            }

            String json = builder.toString();
            Gson gson = new GsonBuilder().create();

            Team[] teams = gson.fromJson(json, Team[].class);

            for(Team tm : teams){
                map.put(tm.getName(), tm);
            }
        }
        catch (IOException exception) {
            System.out.println("Cannot open input file");
        }
        return map;
    }
}
