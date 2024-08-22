import gov.llnl.ernie.analysis.AnalysisException;
import gov.llnl.ernie.api.ERNIE_lane;
import gov.llnl.ernie.api.Results;
import gov.llnl.utility.io.ReaderException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class EMLService {

    private final ERNIE_lane ernieLane;
    EMLService(LaneSettings settings) {
        ernieLane = new ERNIE_lane(
                settings.getPortId(),
                settings.getLaneId(),
                settings.isCollimated(),
                settings.getLaneWidth(),
                settings.getIntervals(),
                settings.getOccupancyHoldin()
        );

    }

    public Results processLines(List<String> fileContents) {
        Results results;

        String[] streamArray = new String[fileContents.size()];
        for(int i = 0; i < streamArray.length; i++) {
            streamArray[i] = fileContents.get(i);
        }
        Stream<String> stream = Stream.of(streamArray);
        try {
            results = this.ernieLane.process(stream);
        } catch (ReaderException | AnalysisException | IOException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

}
