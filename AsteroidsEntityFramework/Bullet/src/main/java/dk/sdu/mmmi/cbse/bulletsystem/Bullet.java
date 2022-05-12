package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;

public class Bullet extends Entity {

    public Bullet() {
        hexColor = "#b4ded5";
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.BULLET;
    }
}
