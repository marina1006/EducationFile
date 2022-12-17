package web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import web.model.Car;

@Service
public class CarServiceImpl implements CarService {

  List<Car> cars = new ArrayList<>();

  {
    cars.add(new Car("model", 4, "coupe"));
    cars.add(new Car("model", 4, "concept"));
    cars.add(new Car("model", 5, "sedan"));
    cars.add(new Car("model", 5, "hybrid"));
    cars.add(new Car("model", 6, "cabrio"));
  }

  public List<Car> carList(int count) {
    return cars.stream().limit(count).collect(Collectors.toList());
  }
}
