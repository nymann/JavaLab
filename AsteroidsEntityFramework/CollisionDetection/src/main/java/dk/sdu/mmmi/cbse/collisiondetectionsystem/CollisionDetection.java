package dk.sdu.mmmi.cbse.collisiondetectionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetection implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entities = new ArrayList<>(world.getEntities());
        for (Entity a: entities) {
            for (Entity b: entities) {
                if (a.equals(b)) {
                    continue;
                }
                if (!intersects(a, b)) {
                    continue;
                }
                LifePart lifePart = a.getPart(LifePart.class);
                if (lifePart == null) {
                    world.removeEntity(a);
                } else {
                    lifePart.setAttacker(b);
                    lifePart.process(gameData, b);
                }
                break;
            }
        }
    }

    private boolean intersects(Entity a, Entity b) {
        PositionPart aPos = a.getPart(PositionPart.class);
        PositionPart bPos = b.getPart(PositionPart.class);
        float dX = bPos.getX() - aPos.getX();
        float dY = bPos.getY() - aPos.getY();
        float distance = (float) Math.sqrt(dX * dX + dY * dY);
        return distance < a.getRadius() + b.getRadius();
    }
}
