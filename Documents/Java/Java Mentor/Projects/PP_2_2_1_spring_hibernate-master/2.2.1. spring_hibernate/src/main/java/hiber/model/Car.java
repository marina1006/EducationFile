package hiber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class Car {

  @Id
  @GeneratedValue
  private int id;

  @Column
  private String model;

  @Column
  private int series;

  public Car() {
  }

  public Car(String model, int series) {
    this.model = model;
    this.series = series;
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
    return model + series;
  }
}