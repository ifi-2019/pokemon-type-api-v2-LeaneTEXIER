package com.ifi.tp.pokemon_type_api.service;

import com.ifi.tp.pokemon_type_api.bo.PokemonType;

import java.util.List;

public interface PokemonTypeService {
    PokemonType getPokemonType(int id);
    List<PokemonType> getAllPokemonTypes();
    PokemonType getPokemonType(String name);
    List<PokemonType> getPokemonTypeByTypes(List<String> types);
}
