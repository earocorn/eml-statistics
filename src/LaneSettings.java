public class LaneSettings {

    private final String portId;
    private final int laneId;
    private final boolean isCollimated;
    private final double laneWidth;
    private final int intervals;
    private final int occupancyHoldin;

    LaneSettings(String portId, int laneId, boolean isCollimated, double laneWidth, int intervals, int occupancyHoldin) {
        this.portId = portId;
        this.laneId = laneId;
        this.isCollimated = isCollimated;
        this.laneWidth = laneWidth;
        this.intervals = intervals;
        this.occupancyHoldin = occupancyHoldin;
    }

    public String getPortId() {
        return portId;
    }

    public int getLaneId() {
        return laneId;
    }

    public boolean isCollimated() {
        return isCollimated;
    }

    public double getLaneWidth() {
        return laneWidth;
    }

    public int getIntervals() {
        return intervals;
    }

    public int getOccupancyHoldin() {
        return occupancyHoldin;
    }

}
