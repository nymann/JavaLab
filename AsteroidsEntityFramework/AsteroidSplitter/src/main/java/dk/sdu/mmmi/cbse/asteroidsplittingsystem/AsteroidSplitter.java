package dk.sdu.mmmi.cbse.asteroidsplittingsystem;

import dk.sdu.mmmi.cbse.asteroidsystem.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Collection;

public class AsteroidSplitter implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        Collection<Entity> entities = world.getEntities(Asteroid.class);
        for (Entity entity : entities) {
            Asteroid asteroid = (Asteroid) entity;
            handleSplit(asteroid, world);
        }
    }

    private void handleSplit(Asteroid original, World world) {
        LifePart lifePart = original.getPart(LifePart.class);
        if (!lifePart.lostLifeLastCycle()) {
            return;
        }
        lifePart.lostLifeProcessed();
        world.removeEntity(original.getID());
        float newRadius = original.getRadius() / 2;
        if (newRadius < 3) {
            return;
        }
        float offset = newRadius * 6;
        world.addEntity(createPostSplitAsteroid(original, newRadius, offset));
        world.addEntity(createPostSplitAsteroid(original, newRadius, -offset));
    }

    private Asteroid createPostSplitAsteroid(Asteroid asteroidToSplit, float radius, float offset) {
        Asteroid asteroid = new Asteroid();
        asteroid.setRadius(radius);

        LifePart lifePart = asteroidToSplit.getPart(LifePart.class);
        asteroid.add(new LifePart(lifePart.getLife(), lifePart.getHealth()));

        MovingPart movingPart = asteroidToSplit.getPart(MovingPart.class);
        asteroid.add(movingPart);

        PositionPart positionPart = asteroidToSplit.getPart(PositionPart.class);
        float x = positionPart.getX() + offset;
        float y = positionPart.getY() + offset;
        positionPart.setPosition(x, y);
        asteroid.add(new PositionPart(x, y, positionPart.getRadians()));
        return asteroid;
    }
}
