package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.IShapeRender;
import dk.sdu.mmmi.cbse.common.ShapeType;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;

public class Asteroid extends Entity {
    public Asteroid() {
        super(10, 30);
        hexColor = "#756153";
        shapeType = ShapeType.FILLED;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.ASTEROID;
    }

    @Override
    protected void drawMethod(IShapeRender sr) {
        float x = this.getShapeX()[0];
        float y = this.getShapeY()[0];
        float radius = this.getRadius();
        sr.addCircle(x, y, radius);
    }
}
