import gov.llnl.ernie.Analysis;
import gov.llnl.ernie.api.Results;

import java.util.List;

public class EMLStats {
    ResultCount rpmCounts;
    ResultCount emlCounts;
    String name;
    int numResults;

    EMLStats(String name, Results[] results) {
        this.name = name;
        this.numResults = results.length;

        rpmCounts = new ResultCount();
        emlCounts = new ResultCount();

        for(int i = 0; i < results.length; i++) {
            Analysis.RecommendedAction rpmResult = results[i].getRPMResult();
            Analysis.RecommendedAction emlResult = results[i].getResult();

            switch (rpmResult) {
                case NONE -> rpmCounts.nones++;
                case INVESTIGATE -> rpmCounts.investigates++;
                case RELEASE -> rpmCounts.releases++;
            }

            switch(emlResult) {
                case NONE -> emlCounts.nones++;
                case INVESTIGATE -> emlCounts.investigates++;
                case RELEASE -> emlCounts.releases++;
            }
        }
    }

    public ResultCount getRPMCounts() { return this.rpmCounts; }
    public ResultCount getEMLCounts() { return this.emlCounts; }
    public int getNumResults() { return this.numResults; }

    public static class ResultCount {
        int investigates;
        int releases;
        int nones;
        ResultCount() {
            this.investigates = 0;
            this.releases = 0;
            this.nones = 0;
        }
    }
    @Override
    public String toString() {
        String rpmString = String.format("RPM Results:\tInvestigate: %d, Release: %d, None: %d", rpmCounts.investigates, rpmCounts.releases, rpmCounts.nones);
        String emlString = String.format("EML Results:\tInvestigate: %d, Release: %d, None: %d", emlCounts.investigates, emlCounts.releases, emlCounts.nones);
        return String.format("%s\nOccupancies: %d\n%s\n%s\n", this.name, this.numResults, rpmString, emlString);
    }

    public static class SiteStats {
        private List<EMLStats> statsList;
        private String siteName;

        SiteStats(String siteName, List<EMLStats> statsList) {
            this.siteName = siteName;
            this.statsList = statsList;
        }

        public String getSiteName() {
            return siteName;
        }

        public List<EMLStats> getStatsList() {
            return statsList;
        }


        @Override
        public String toString() {
            int numTotalOccupancies = 0;
            EMLStats.ResultCount totalRPMCounts = new EMLStats.ResultCount();
            EMLStats.ResultCount totalEMLCounts = new EMLStats.ResultCount();

            for(EMLStats stats : this.statsList) {
                numTotalOccupancies += stats.numResults;

                totalRPMCounts.investigates += stats.rpmCounts.investigates;
                totalRPMCounts.releases += stats.rpmCounts.releases;
                totalRPMCounts.nones += stats.rpmCounts.nones;

                totalEMLCounts.investigates += stats.emlCounts.investigates;
                totalEMLCounts.releases += stats.emlCounts.releases;
                totalEMLCounts.nones += stats.emlCounts.nones;
            }

            String rpmString = String.format("Total RPM Results:\tInvestigate: %d, Release: %d, None: %d", totalRPMCounts.investigates, totalRPMCounts.releases, totalRPMCounts.nones);
            String emlString = String.format("Total EML Results:\tInvestigate: %d, Release: %d, None: %d", totalEMLCounts.investigates, totalEMLCounts.releases, totalEMLCounts.nones);
            return String.format("%s\nTotal Occupancies: %d\n%s\n%s\n\n", this.siteName, numTotalOccupancies, rpmString, emlString);
        }

    }

}
