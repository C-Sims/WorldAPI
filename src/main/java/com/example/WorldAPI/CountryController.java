package com.example.WorldAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping(path="/WorldAPI")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

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

}
