package com.example.WorldAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping(path="/WorldAPI")
public class MainController {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountrylanguageRepository countrylanguageRepository;

    // CITY
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
    @PostMapping(path="/addCity")
    public @ResponseBody String addNewCity (@RequestParam String name
            , @RequestParam String district
            , @RequestParam Integer population){
        City city = new City();
        city.setName(name);
        city.setDistrict(district);
        city.setPopulation(population);
        cityRepository.save(city);
        return "City added";
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

    // COUNTRY
    // get (1 item)
    @GetMapping(path="/country/{code}")
    public @ResponseBody Optional<Country> getCountry(@PathVariable String code){
        return countryRepository.findById(code);
    }

    // get (all items)
    @GetMapping(path="/allCountries")
    public @ResponseBody Iterable<Country> getAllCountries(){
        return countryRepository.findAll();
    }

    // post (create)
    @PostMapping(path="/addCountry")
    public @ResponseBody String addNewCountry (@RequestParam String code
            , @RequestParam String name
            , @RequestParam String continent
            , @RequestParam String region
            , @RequestParam BigDecimal surfaceArea
            , @RequestParam Short indepYear
            , @RequestParam Integer population
            , @RequestParam BigDecimal lifeExpectancy
            , @RequestParam BigDecimal gnp
            , @RequestParam BigDecimal gNPOld
            , @RequestParam String localName
            , @RequestParam String governmentForm
            , @RequestParam String headOfState
            , @RequestParam Integer capital
            , @RequestParam String code2){

        Country country = new Country();
        country.setCode(code);
        country.setName(name);
        country.setContinent(continent);
        country.setRegion(region);
        country.setSurfaceArea(surfaceArea);
        country.setIndepYear(indepYear);
        country.setPopulation(population);
        country.setLifeExpectancy(lifeExpectancy);
        country.setGnp(gnp);
        country.setGNPOld(gNPOld);
        country.setLocalName(localName);
        country.setGovernmentForm(governmentForm);
        country.setHeadOfState(headOfState);
        country.setCapital(capital);
        country.setCode2(code2);

        countryRepository.save(country);

        return "Country added";
    }

    // put (update)
    @PutMapping(path="updateCountry/{code}")
    public @ResponseBody String updateCountry(@PathVariable String code
            , @RequestParam String name
            , @RequestParam String continent
            , @RequestParam String region
            , @RequestParam BigDecimal surfaceArea
            , @RequestParam Short indepYear
            , @RequestParam Integer population
            , @RequestParam BigDecimal lifeExpectancy
            , @RequestParam BigDecimal gnp
            , @RequestParam BigDecimal gNPOld
            , @RequestParam String localName
            , @RequestParam String governmentForm
            , @RequestParam String headOfState
            , @RequestParam Integer capital
            , @RequestParam String code2){

        Optional<Country> optionalCountry = countryRepository.findById(code);
        if(optionalCountry.isPresent()){
            Country country = optionalCountry.get();
            country.setCode(code);
            country.setName(name);
            country.setContinent(continent);
            country.setRegion(region);
            country.setSurfaceArea(surfaceArea);
            country.setIndepYear(indepYear);
            country.setPopulation(population);
            country.setLifeExpectancy(lifeExpectancy);
            country.setGnp(gnp);
            country.setGNPOld(gNPOld);
            country.setLocalName(localName);
            country.setGovernmentForm(governmentForm);
            country.setHeadOfState(headOfState);
            country.setCapital(capital);
            country.setCode2(code2);

            countryRepository.save(country);
            return "Country updated";
        }
        return "Can't update, country not found";
    }

    // delete (delete)
    @DeleteMapping(path="deleteCountry/{code}")
    public @ResponseBody String deleteCountry(@PathVariable String code){
        if(countryRepository.existsById(code)){
            countryRepository.deleteById(code);
            return "Country deleted";
        }
        return "Can't delete, country not found";
    }

    // COUNTRY LANGUAGE
    // get (1 item)
    @GetMapping(path="/country_language/{code}")
    public @ResponseBody Optional<Countrylanguage> getCountrylanguage(@PathVariable CountrylanguageId id){
        return countrylanguageRepository.findById(id);
    }

    // get (all items)
    @GetMapping(path="/allCountrylanguages")
    public @ResponseBody Iterable<Countrylanguage> getAllCountrylanguages(){
        return countrylanguageRepository.findAll();
    }

    // post (create)
    @PostMapping(path="/addCountrylanguage")
    public @ResponseBody String addNewCountrylanguage (@RequestParam CountrylanguageId id
            , @RequestParam Country countryCode
            , @RequestParam String isOfficial
            , @RequestParam BigDecimal percentage){

        Countrylanguage countryLanguage = new Countrylanguage();
        countryLanguage.setId(id);
        countryLanguage.setCountryCode(countryCode);
        countryLanguage.setIsOfficial(isOfficial);
        countryLanguage.setPercentage(percentage);

        countrylanguageRepository.save(countryLanguage);

        return "Country language added";
    }

    // put (update)
    @PutMapping(path="updateCountrylanguage/{id}")
    public @ResponseBody String updateCountrylanguage(@PathVariable CountrylanguageId id
            , @RequestParam Country countryCode
            , @RequestParam String isOfficial
            , @RequestParam BigDecimal percentage){

        Optional<Countrylanguage> optionalCountryLanguage = countrylanguageRepository.findById(id);
        if(optionalCountryLanguage.isPresent()){
            Countrylanguage countryLanguage = optionalCountryLanguage.get();
            countryLanguage.setId(id);
            countryLanguage.setCountryCode(countryCode);
            countryLanguage.setIsOfficial(isOfficial);
            countryLanguage.setPercentage(percentage);

            countrylanguageRepository.save(countryLanguage);
            return "Country language updated";
        }
        return "Can't update, country language not found";
    }

    // delete (delete)
    @DeleteMapping(path="deleteCountrylanguage/{id}")
    public @ResponseBody String deleteCountrylanguage(@PathVariable CountrylanguageId id){
        if(countrylanguageRepository.existsById(id)){
            countrylanguageRepository.deleteById(id);
            return "Country language deleted";
        }
        return "Can't delete, country language not found";
    }

}
