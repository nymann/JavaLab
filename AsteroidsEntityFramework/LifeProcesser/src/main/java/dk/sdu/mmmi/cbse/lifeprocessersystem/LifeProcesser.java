package dk.sdu.mmmi.cbse.lifeprocessersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ExpirationPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class LifeProcesser implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        world.getEntities().removeIf(entity -> {
            LifePart lifePart = entity.getPart(LifePart.class);
            ExpirationPart expirationPart = entity.getPart(ExpirationPart.class);
            return isDead(lifePart) || isExpired(expirationPart);
        });
    }

    private boolean isDead(LifePart lifePart) {
        if (null == lifePart) {
            return false;
        }
        return lifePart.getLife() <= 0;
    }

    private boolean isExpired(ExpirationPart expirationPart) {
        if (null == expirationPart) {
            return false;
        }

        return expirationPart.getExpiration() <= 0;
    }
}
