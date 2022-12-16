package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping
  public String getCount(@RequestParam(value = "count",defaultValue = "5") int count, ModelMap model) {
    model.addAttribute("cars", service.carList(count));
    return "cars";
  }
}
