package dk.sdu.mmmi.cbse.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyShapeRender implements IShapeRender {
    private final ShapeRenderer shapeRenderer;

    public MyShapeRender(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void begin(ShapeType shapeType) {
        this.shapeRenderer.begin(this.convertShapeType(shapeType));
    }

    @Override
    public void setColor(String hex) {
        this.shapeRenderer.setColor(Color.valueOf(hex));

    }

    private ShapeRenderer.ShapeType convertShapeType(ShapeType shapeType) {
        switch (shapeType) {
            case LINE:
                return ShapeRenderer.ShapeType.Line;
            case POINT:
                return ShapeRenderer.ShapeType.Point;
            case FILLED:
                return ShapeRenderer.ShapeType.Filled;
            default:
                return this.shapeRenderer.getCurrentType();
        }
    }

    @Override
    public void addLine(float x0, float y0, float x1, float y1) {
        this.shapeRenderer.line(x0, y0, x1, y1);
    }

    @Override
    public void addCircle(float x, float y, float radius) {
        this.shapeRenderer.circle(x, y, radius);
    }

    @Override
    public void end() {
        this.shapeRenderer.end();
    }
}
