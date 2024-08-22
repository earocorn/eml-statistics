import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RPMData {

    // List of each occupancy recorded in the file, including background information
    private List<List<String>> occupanciesWithContext;
    private List<String> allContents;

    RPMData(File file) {
        List<String> rawLines;

        try {
            rawLines = Files.readAllLines(Path.of(file.getPath()));
        } catch (IOException e) {
            System.out.println("Error reading from file. Does it exist?");
            throw new RuntimeException(e);
        }

        allContents = new ArrayList<>();
        occupanciesWithContext = new ArrayList<>();
        List<String> occupancy = new ArrayList<>();

        for (int i = 0; i < rawLines.size(); i++) {
            String line = rawLines.get(i);
            if (line.length() > 30) {
                // Get rid of date at end of line
                line = line.substring(0, line.length()-13);
            }

            allContents.add(line);
            occupancy.add(line);
            if (line.startsWith("GX")) {
                occupanciesWithContext.add(new ArrayList<>(occupancy));
                occupancy.clear();
            }
        }
    }

    public List<String> getAllContents() {
        return allContents;
    }

    public List<List<String>> getOccupanciesWithContext() {
        return occupanciesWithContext;
    }

}
