package org.mock;

import org.mock.persistence.entity.Player;

import java.util.List;

public class DataProvider {

    public static List<Player> playerListMock() {

        System.out.println(" -> Obteniendo listado Player / Mock");

        return List.of(
                new Player(1L, "Lionel Messi", "Inter Miami", "Delantero"),
                new Player(2L, "Cristiano Ronaldo", "Al Nassr", "Delantero"),
                new Player(3L, "Neymar Jr.", "Paris Saint-Germain", "Delantero"),
                new Player(4L, "Kylian Mbappé", "Paris Saint-Germain", "Delantero"),
                new Player(5L, "Kevin De Bruyne", "Manchester City", "Volante"),
                new Player(6L, "Virgil van Dijk", "Liverpool", "Defensa")
        );
    }

    public static Player playerMock(){
        return new Player(1L, "Lionel Messi", "Inter Miami", "Delantero");
    }

    public static Player newPlayerMock(){
        return new Player(10L, "Luiz Diaz", "Delantero", "Liverpool");
    }
}
