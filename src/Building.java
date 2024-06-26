
public class Building {
    private double WaterTemp;
    private double AirTemp;
    private boolean LightStatus;
    private boolean PowerStatus;
    private boolean Open;


    public Building(){
    }

    @Override
    public String toString() {
        return (
                this.WaterTemp+";;"+ this.AirTemp+";;"+this.LightStatus+";;"+this.PowerStatus+";;"+this.Open
        );
    }

    public double getWaterTemp() {
        return WaterTemp;
    }

    public void setWaterTemp(double waterTemp) {
        WaterTemp = waterTemp;
    }

    public double getAirTemp() {
        return AirTemp;
    }

    public void setAirTemp(double airTemp) {
        AirTemp = airTemp;
    }

    public boolean getLightStatus() {
        return LightStatus;
    }

    public boolean getOpen() {
        return Open;
    }

    public void setOpen(boolean open) {
        Open = open;
    }

    public void setLightStatus(boolean lightStatus) {
        LightStatus = lightStatus;
    }

    public boolean getPowerStatus() {
        return PowerStatus;
    }

    public void setPowerStatus(boolean powerStatus) {
        PowerStatus = powerStatus;
    }



}
