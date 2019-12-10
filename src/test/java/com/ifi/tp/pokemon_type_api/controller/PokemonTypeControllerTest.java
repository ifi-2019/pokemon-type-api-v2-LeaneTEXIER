package com.ifi.tp.pokemon_type_api.controller;

import com.ifi.tp.pokemon_type_api.bo.PokemonType;
import com.ifi.tp.pokemon_type_api.service.PokemonTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonTypeControllerTest {

    @Test
    void pokemonTypeController_shouldBeAnnotated(){
        var controllerAnnotation =
                PokemonTypeController.class.getAnnotation(RestController.class);
        assertNotNull(controllerAnnotation);

        var requestMappingAnnotation =
                PokemonTypeController.class.getAnnotation(RequestMapping.class);
        assertArrayEquals(new String[]{"/pokemon-types"}, requestMappingAnnotation.value());
    }

    @Test
    void getPokemonTypeFromId_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeFromId =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromId", int.class);
        var getMapping = getPokemonTypeFromId.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/{id}"}, getMapping.value());
    }

    @Test
    void getPokemonType_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("pikachu");
        when(service.getPokemonType(25)).thenReturn(pikachu);

        var pokemon = controller.getPokemonTypeFromId(25);
        assertEquals("pikachu", pokemon.getName());

        verify(service).getPokemonType(25);
    }

    @Test
    void getAllPokemonTypes_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllPokemonTypes =
                PokemonTypeController.class.getDeclaredMethod("getAllPokemonTypes");
        var getMapping = getAllPokemonTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }

    @Test
    void getAllPokemonTypes_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        controller.getAllPokemonTypes();

        verify(service).getAllPokemonTypes();
    }

    @Test
    void getPokemonTypeFromName_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeFromName =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeFromName", String.class);
        var getMapping = getPokemonTypeFromName.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
        assertArrayEquals(new String[]{"name"}, getMapping.params());
    }

    @Test
    void getPokemonTypeFromName_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("pikachu");
        when(service.getPokemonType("pikachu")).thenReturn(pikachu);

        var pokemon = controller.getPokemonTypeFromName("pikachu");
        assertEquals(25, pokemon.getId());

        verify(service).getPokemonType("pikachu");
    }

    @Test
    void getPokemonTypeByTypes_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeByTypes =
                PokemonTypeController.class.getDeclaredMethod("getPokemonTypeByTypes", String.class);
        var getMapping = getPokemonTypeByTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
        assertArrayEquals(new String[]{"types"}, getMapping.params());
    }

    @Test
    void getPokemonTypeByTypes_shouldCallTheService(){
        var service = mock(PokemonTypeService.class);
        var controller = new PokemonTypeController(service);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("pikachu");
        var electrode = new PokemonType();
        electrode.setId(101);
        electrode.setName("electrode");
        var types = Arrays.asList("electric");
        when(service.getPokemonTypeByTypes(types)).thenReturn(Arrays.asList(pikachu, electrode));

        var pokemons = controller.getPokemonTypeByTypes("electric");
        assertEquals(2, pokemons.size());
        assertEquals(25, pokemons.get(0).getId());
        assertEquals("pikachu", pokemons.get(0).getName());
        assertEquals(101, pokemons.get(1).getId());
        assertEquals("electrode", pokemons.get(1).getName());

        verify(service).getPokemonTypeByTypes(types);
    }

}
