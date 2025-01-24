package com.example.WorldAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping(path="/WorldAPI")
public class CountrylanguageController {

    @Autowired
    private CountrylanguageRepository countrylanguageRepository;

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
