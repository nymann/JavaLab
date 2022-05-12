package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * Process relevant entities.
     *
     * @param gameData the not Null gameData
     * @param world the not NUll world
     *
     * Post-condition: Relevant entities have been processed and are ready for Post processing.
     *
     * Example use: Update position of entities.
     */
    void process(GameData gameData, World world);
}
