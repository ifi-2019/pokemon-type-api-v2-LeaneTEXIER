package com.ifi.tp.pokemon_type_api.controller;

import com.ifi.tp.pokemon_type_api.bo.PokemonType;
import com.ifi.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/pokemon-types")
public class PokemonTypeController {

    private PokemonTypeService pokemonTypeService;

    public PokemonTypeController(PokemonTypeService service) {
        pokemonTypeService = service;
    }

    @GetMapping(value = "/{id}")
    public PokemonType getPokemonTypeFromId(@PathVariable int id){
        return pokemonTypeService.getPokemonType(id);
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeService.getAllPokemonTypes();
    }

    @GetMapping(value = "/", params = "name")
    public PokemonType getPokemonTypeFromName(String name){
        return pokemonTypeService.getPokemonType(name);
    }

    @GetMapping(value = "/", params = "types")
    public List<PokemonType> getPokemonTypeByTypes(String types){
        var allTypes = types.split(",");
        var listTypes = Arrays.asList(allTypes);
        return pokemonTypeService.getPokemonTypeByTypes(listTypes);
    }

}
