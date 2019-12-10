package com.ifi.tp.pokemon_type_api.controller;

import com.ifi.tp.pokemon_type_api.bo.PokemonType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokemonTypeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PokemonTypeController controller;

    @Test
    void pokemonTypeController_shouldBeInstanciated(){
        assertNotNull(controller);
    }

    @Test
    void getPokemon_withId25_ShouldReturnPikachu() throws Exception {
        var url = "http://localhost:" + port + "/pokemon-types/25";

        var pikachu = this.restTemplate.getForObject(url, PokemonType.class);

        assertNotNull(pikachu);
        assertEquals(25, pikachu.getId());
        assertEquals("pikachu", pikachu.getName());
        assertEquals(4, pikachu.getHeight());
    }

    @Test
    void getAllPokemons_ShouldReturn151Pokemons() throws Exception {
        var url = "http://localhost:" + port + "/pokemon-types/";

        var pokemons = this.restTemplate.getForObject(url, PokemonType[].class);

        assertNotNull(pokemons);
        assertEquals(151, pokemons.length);
    }

    @Test
    void getPokemon_withNamePikachu_ShouldReturnId25() throws Exception {
        var url = "http://localhost:" + port + "/pokemon-types/?name=pikachu";

        var pikachu = this.restTemplate.getForObject(url, PokemonType.class);

        assertNotNull(pikachu);
        assertEquals("pikachu", pikachu.getName());
        assertEquals(25, pikachu.getId());
        assertEquals(4, pikachu.getHeight());
    }

    @Test
    void getPokemon_withTypeElectric_ShouldReturn9Pokemons() throws Exception {
        var url = "http://localhost:" + port + "/pokemon-types/?types=electric";

        var pokemons = this.restTemplate.getForObject(url, PokemonType[].class);

        assertNotNull(pokemons);
        assertEquals(9, pokemons.length);
    }

    @Test
    void getPokemon_withTypesBugAndPoison_ShouldReturn5Pokemons() throws Exception {
        var url = "http://localhost:" + port + "/pokemon-types/?types=bug,poison";

        var pokemons = this.restTemplate.getForObject(url, PokemonType[].class);

        assertNotNull(pokemons);
        assertEquals(5, pokemons.length);
    }
}
