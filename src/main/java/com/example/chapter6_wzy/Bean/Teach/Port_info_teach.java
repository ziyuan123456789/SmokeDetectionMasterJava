package com.example.chapter6_wzy.Bean.Teach;



/**
 * @author wsh
 */
public class Port_info_teach {

    private int id;
    private String name;
    private double operationalDay;
    private int tideTypeID;
    private double tideRange;
    private double averageWL;
    private double desHWL;
    private double desLWL;
    private double extHWL;
    private double extLWL;
    private double waveHeight;
    private double wavePeriod;
    private int waveAngle;
    private double crossCurrentV;
    private int longitudinalCurrentV;
    private int despoit;
    private String portFile;
    private String geoFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOperationalDay() {
        return operationalDay;
    }

    public void setOperationalDay(double operationalDay) {
        this.operationalDay = operationalDay;
    }

    public int getTideTypeID() {
        return tideTypeID;
    }

    public void setTideTypeID(int tideTypeID) {
        this.tideTypeID = tideTypeID;
    }

    public double getTideRange() {
        return tideRange;
    }

    public void setTideRange(double tideRange) {
        this.tideRange = tideRange;
    }

    public double getAverageWL() {
        return averageWL;
    }

    public void setAverageWL(double averageWL) {
        this.averageWL = averageWL;
    }

    public double getDesHWL() {
        return desHWL;
    }

    public void setDesHWL(double desHWL) {
        this.desHWL = desHWL;
    }

    public double getDesLWL() {
        return desLWL;
    }

    public void setDesLWL(double desLWL) {
        this.desLWL = desLWL;
    }

    public double getExtHWL() {
        return extHWL;
    }

    public void setExtHWL(double extHWL) {
        this.extHWL = extHWL;
    }

    public double getExtLWL() {
        return extLWL;
    }

    public void setExtLWL(double extLWL) {
        this.extLWL = extLWL;
    }

    public double getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(double waveHeight) {
        this.waveHeight = waveHeight;
    }

    public double getWavePeriod() {
        return wavePeriod;
    }

    public void setWavePeriod(double wavePeriod) {
        this.wavePeriod = wavePeriod;
    }

    public int getWaveAngle() {
        return waveAngle;
    }

    public void setWaveAngle(int waveAngle) {
        this.waveAngle = waveAngle;
    }

    public double getCrossCurrentV() {
        return crossCurrentV;
    }

    public void setCrossCurrentV(double crossCurrentV) {
        this.crossCurrentV = crossCurrentV;
    }

    public int getLongitudinalCurrentV() {
        return longitudinalCurrentV;
    }

    public void setLongitudinalCurrentV(int longitudinalCurrentV) {
        this.longitudinalCurrentV = longitudinalCurrentV;
    }

    public int getDespoit() {
        return despoit;
    }

    public void setDespoit(int despoit) {
        this.despoit = despoit;
    }

    public String getPortFile() {
        return portFile;
    }

    public void setPortFile(String portFile) {
        this.portFile = portFile;
    }

    public String getGeoFile() {
        return geoFile;
    }

    public void setGeoFile(String geoFile) {
        this.geoFile = geoFile;
    }

/*    public List<BerthBean> getBerths() {
        return berths;
    }

    public void setBerths(List<BerthBean> berths) {
        this.berths = berths;
    }*/

    @Override
    public String toString() {
        return "Port [id=" + id + ", name=" + name + ", operationalDay=" + operationalDay + ", tideTypeID=" + tideTypeID
                + ", tideRange=" + tideRange + ", averageWL=" + averageWL + ", desHWL=" + desHWL + ", desLWL=" + desLWL
                + ", extHWL=" + extHWL + ", extLWL=" + extLWL + ", waveHeight=" + waveHeight + ", wavePeriod="
                + wavePeriod + ", waveAngle=" + waveAngle + ", crossCurrentV=" + crossCurrentV
                + ", longitudinalCurrentV=" + longitudinalCurrentV + ", despoit=" + despoit + ", portFile=" + portFile
                + ", geoFile=" + geoFile + ", berths="  + "]";
    }
}
