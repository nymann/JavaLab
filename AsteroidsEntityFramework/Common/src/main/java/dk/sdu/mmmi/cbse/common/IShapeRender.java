package dk.sdu.mmmi.cbse.common;

public interface IShapeRender {
    public void begin(ShapeType shapeType);
    public void setColor(String hex);
    public void addLine(float x0, float y0, float x1, float y1);
    public void addCircle(float x, float y, float radius);
    public void end();
}
