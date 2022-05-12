package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {
    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);

            boolean goingLeft = random.nextBoolean();
            movingPart.setLeft(goingLeft);
            movingPart.setRight(!goingLeft);
            movingPart.setUp(random.nextBoolean());
            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        int vertices = shapex.length;
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radius = entity.getRadius();
        float angle = positionPart.getRadians();

        for (int i = 0; i < vertices; i++) {
            shapex[i] = (float) (x + Math.cos(angle) * radius);
            shapey[i] = (float) (y + Math.sin(angle) * radius);
            angle += (float) (Math.PI * 2 / vertices);
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
