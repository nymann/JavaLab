package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService  {
        /**
         * Post process relevant entities.
         *
         * @param gameData the not Null gameData
         * @param world the not Null world
         *
         * Pre-condition: Called after IEntityProcessingService processing has run for all plugins.
         * Post-condition: Entities might have been removed, or changed.
         */
        void process(GameData gameData, World world);
}
