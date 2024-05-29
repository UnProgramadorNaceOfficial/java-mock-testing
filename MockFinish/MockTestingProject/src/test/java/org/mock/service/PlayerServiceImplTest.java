package org.mock.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mock.persistence.entity.Player;
import org.mock.persistence.repository.PlayerRepositoryImpl;
import org.mock.persistence.service.PlayerServiceImpl;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {

    @Mock
    private PlayerRepositoryImpl playerRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    void testFindAll() {
        // When
        when(playerRepository.findAll()).thenReturn(DataProvider.playerListMock());
        List<Player> result = playerService.findAll();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Lionel Messi", result.get(0).getName());
        assertEquals("Inter Miami", result.get(0).getTeam());
        assertEquals("Delantero", result.get(0).getPosition());
        verify(playerRepository).findAll();
    }

    @Test
    void testFindById() {
        // When
        // when(playerRepository.findById(anyLong())).thenReturn(DataProvider.playerMock());

        doAnswer(invocation -> {
            Long id = invocation.getArgument(0);

            if (id <= 0) {
                fail("El argumento no puede ser cero");
            }
            return DataProvider.playerMock();
        }).when(playerRepository).findById(anyLong());

        Player result = playerService.findById(10L);

        // Then
        assertNotNull(result);
        assertEquals("Lionel Messi", result.getName());
        assertEquals("Inter Miami", result.getTeam());
        assertEquals("Delantero", result.getPosition());
        verify(playerRepository).findById(anyLong());
    }

    @Test
    void testSave(){
        // Given
        Player player = DataProvider.newPlayerMock();

        // When
        playerService.save(player);

//        doAnswer( invocation -> {
//            Player playerArgument = invocation.getArgument(0);
//
//            if(playerArgument.getId() == 6 && playerArgument.getName().equalsIgnoreCase("Mohamed Salah")){
//                return null;
//            }
//            return fail();
//        } ).when(playerRepository).save(any(Player.class));


        // Then
        ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
        verify(playerRepository).save(playerCaptor.capture());
        assertEquals(10L, playerCaptor.getValue().getId());
        assertEquals("Mohamed Salah", playerCaptor.getValue().getName());
    }

    @Test
    void testDeleteById(){
        playerService.deleteById(2L);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(playerRepository).deleteById(anyLong());
        verify(playerRepository).deleteById(idCaptor.capture());
        assertEquals(2L, idCaptor.getValue());
    }
}
