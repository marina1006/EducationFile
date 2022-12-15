package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.CarService;

@Controller
@RequestMapping("/cars")
public class CarController {

  private CarService service;

  @Autowired
  public CarController(CarService service) {
    this.service = service;
  }

  public String getCount(@RequestParam String model, int series) {

    return "/cars";
  }
}
