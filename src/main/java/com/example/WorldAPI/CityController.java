package com.example.WorldAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping(path="/WorldAPI")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    // get (1 item)
    @GetMapping(path="/city/{id}")
    public @ResponseBody Optional<City> getCity(@PathVariable Integer id){
        return cityRepository.findById(id);
    }

    // get (all items)
    @GetMapping(path="/allCities")
    public @ResponseBody Iterable<City> getAllCities(){
        return cityRepository.findAll();
    }

    // post (create)
    @PostMapping("/addCity")
    public ResponseEntity<String> addNewCity(
            @RequestParam("name") String name,
            @RequestParam("district") String district,
            @RequestParam("population") Integer population,
            @RequestParam("countryCode") String countryCode) {

        // Find the country by the countryCode
        Country country = countryRepository.findById(countryCode)
                .orElse(null); // If not found, return null

        if (country == null) {
            // If the country was not found, return a BAD_REQUEST response
            return new ResponseEntity<>("CountryCode does not exist in the country table", HttpStatus.BAD_REQUEST);
        }

        // Create a new City object
        City city = new City();
        city.setName(name);
        city.setDistrict(district);
        city.setPopulation(population);

        // Set the country for the city
        city.setCountry(country);

        // Save the city object to the database
        cityRepository.save(city);

        // Return a success message
        return new ResponseEntity<>("City added successfully", HttpStatus.CREATED);
    }

    // put (update)
    @PutMapping(path="updateCity/{id}")
    public @ResponseBody String updateCity(@PathVariable Integer id
            , @RequestParam String name
            , @RequestParam String district
            , @RequestParam Integer population){

        Optional<City> optionalCity = cityRepository.findById(id);
        if(optionalCity.isPresent()){
            City city = optionalCity.get();
            city.setName(name);
            city.setDistrict(district);
            city.setPopulation(population);
            cityRepository.save(city);
            return "City updated";
        }
        return "Can't update, city not found";
    }

    // delete (delete)
    @DeleteMapping(path="deleteCity/{id}")
    public @ResponseBody String deleteCity(@PathVariable Integer id){
        if(cityRepository.existsById(id)){
            cityRepository.deleteById(id);
            return "City deleted";
        }
        return "Can't delete, city not found";
    }

}
