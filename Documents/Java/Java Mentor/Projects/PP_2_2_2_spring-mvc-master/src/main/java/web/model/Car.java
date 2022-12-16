package web.model;

public class Car {

  private String model;
  private int series;
  private String type;

  public Car() {
  }

  public Car(String model, int series, String type) {
    this.model = model;
    this.series = series;
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getSeries() {
    return series;
  }

  public void setSeries(int series) {
    this.series = series;
  }

  @Override
  public String toString() {
    return model + series + type;
  }
}
