package org.mock.persistence.entity.repository;

import org.mock.persistence.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepositoryImpl implements IPlayerRepository {

//    private List<Player> playerDatabase = new ArrayList<>(List.of(
//            new Player(1L, "Lionel Messi", "Inter Miami", "Delantero"),
//            new Player(2L, "Cristiano Ronaldo", "Al Nassr", "Delantero"),
//            new Player(3L, "Neymar Jr.", "Paris Saint-Germain", "Delantero"),
//            new Player(4L, "Kylian Mbappé", "Paris Saint-Germain", "Delantero"),
//            new Player(5L, "Kevin De Bruyne", "Manchester City", "Volante"),
//            new Player(6L, "Virgil van Dijk", "Liverpool", "Defensa")
//    ));

    private List<Player> playerDatabase = new ArrayList<>();

    @Override
    public List<Player> findAll() {
        System.out.println(" -> Metodo findAll real!!");
        return this.playerDatabase;
    }

    @Override
    public Player findById(Long id) {
        System.out.println(" -> Metodo findById real!!");
        return this.playerDatabase.stream()
                .filter( player -> player.getId() == id )
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void save(Player player) {
        System.out.println(" -> Metodo save real!!");
        this.playerDatabase.add(player);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println(" -> Metodo deleteById real!!");
        this.playerDatabase = this.playerDatabase.stream()
                .filter( player -> player.getId() != id )
                .toList();
    }
}
