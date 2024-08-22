import gov.llnl.ernie.api.Results;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        File site1 = new File("./rpmData/Site1");
        File site2 = new File("./rpmData/Site2");

        for(LaneSettings settings : Constants.SETTINGS_ARRAY) {
            try {
                writeStats(new File("./rpmData"), settings);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static EMLStats getStats(File file, EMLService lane) {
        RPMData data = new RPMData(file);

        List<Results> resultsList = new ArrayList<>();
        for (int i = 0; i < data.getOccupanciesWithContext().size(); i++) {
            var res = lane.processLines(data.getOccupanciesWithContext().get(i));
            resultsList.add(res);
        }

        Results[] resultsArray = new Results[resultsList.size()];
        for (int i = 0; i < resultsList.size(); i++) {
            resultsArray[i] = resultsList.get(i);
        }

        return new EMLStats(file.getName(), resultsArray);
    }

    public static EMLStats.SiteStats getSiteStats(File dir, EMLService lane) {
        File[] files = dir.listFiles();
        List<EMLStats> statsList = new ArrayList<>();

        // Analyze each occupancy in each file
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            var stats = getStats(files[i], lane);
            statsList.add(stats);
        }

        return new EMLStats.SiteStats(dir.getName(), statsList);
    }

    public static void writeStats(File inputDir, LaneSettings settings) throws IOException {
        // Create ERNIE lane
        EMLService eml = new EMLService(settings);
        // Create output directory
        if(!(new File("stats")).exists())
            Files.createDirectory(Path.of("stats"));

        File[] subDirs = inputDir.listFiles();

        for (int i = 0; i < subDirs.length; i++) {
            String subDirName = subDirs[i].getName();

            if(subDirName.startsWith(".DS"))
                continue;

            String outputFileName = String.format("%s_%s.txt", subDirs[i].getName(), settings.isCollimated() ? "collimated" : "uncollimated");
            File outputFile = new File("stats/" + outputFileName);

            try {
                if(!outputFile.exists())
                    outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileWriter writer = new FileWriter(outputFile.getPath());

            EMLStats.SiteStats site1Stats = getSiteStats(new File(String.format("%s/%s", inputDir.getName(), subDirName)), eml);

            writer.write(site1Stats.toString());

            for(var stats : site1Stats.getStatsList()) {
                writer.write(stats.toString());
            }

            writer.close();
        }

    }

}
