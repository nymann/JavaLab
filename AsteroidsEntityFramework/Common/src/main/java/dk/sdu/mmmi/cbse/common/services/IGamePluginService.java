package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * Init script for your plugin.
     *
     * @param gameData the not Null gameData
     * @param world the not Null world
     *
     * Example usage: add initial entities to the world here.
     */
    void start(GameData gameData, World world);

    /**
     * Called when the game is requested to stop.
     *
     * @param gameData the not Null gameData
     * @param world the not Null world
     *
     * Example usage: Cleanup entities that you have created as part of `start`.
     */
    void stop(GameData gameData, World world);
}
