package org.mock.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mock.persistence.entity.Player;
import org.mock.persistence.repository.PlayerRepositoryImpl;
import org.mock.service.DataProvider;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerRepositoryImplTest {

    private PlayerRepositoryImpl playerRepository;

    @BeforeEach
    public void init(){
        this.playerRepository = new PlayerRepositoryImpl();
    }

    @Test
    void testFindAll(){
        List<Player> playerList = this.playerRepository.findAll();

        assertNotNull(playerList);
        assertFalse(playerList.isEmpty());
        assertEquals("Lionel Messi", playerList.get(0).getName());
        assertEquals("Cristiano Ronaldo", playerList.get(1).getName());
    }

    @Test
    void testFindById(){
        Player player = this.playerRepository.findById(1L);

        assertNotNull(player);
        assertEquals("Lionel Messi", player.getName());
        assertEquals("Delantero", player.getPosition());
    }

    @Test
    void testFindByIdError(){
        Exception exception = assertThrows(NoSuchElementException.class, () -> this.playerRepository.findById(10L));
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    void testSave(){
        Player player = DataProvider.newPlayerMock();

        this.playerRepository.save(player);
        Player playerSaved = this.playerRepository.findById(player.getId());

        assertNotNull(playerSaved);
        assertEquals("Mohamed Salah", playerSaved.getName());
        assertEquals("Delantero", playerSaved.getPosition());
    }

    @Test
    void testDeleteById(){
        this.playerRepository.deleteById(1L);
        Exception exception = assertThrows(NoSuchElementException.class, () -> this.playerRepository.findById(1L));
        assertEquals("No value present", exception.getMessage());
    }
}
