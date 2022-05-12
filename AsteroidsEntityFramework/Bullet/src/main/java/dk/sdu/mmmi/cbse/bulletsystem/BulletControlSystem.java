package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ExpirationPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            ExpirationPart expirationPart = bullet.getPart(ExpirationPart.class);
            movingPart.setUp(true);

            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);
            expirationPart.process(gameData, bullet);

            updateShape(bullet);
        }
    }

    public static Entity shoot(Entity origin) {
        PositionPart originPos = origin.getPart(PositionPart.class);

        float r = origin.getRadius();
        float x = originPos.getX();
        float y = originPos.getY();
        float radians = originPos.getRadians();
        final int bulletRadius = 2;

        x += (float) Math.cos(radians) * r * bulletRadius;
        y += (float) Math.sin(radians) * r * bulletRadius;

        Entity bullet = new Bullet();
        bullet.setRadius(bulletRadius);
        bullet.add(new MovingPart(0, 9001, 400, 0));
        bullet.add(new PositionPart(x, y, radians));
        bullet.add(new ExpirationPart(1));

        return bullet;
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
