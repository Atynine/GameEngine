package engine.entity;

public abstract class Camera extends Entity implements Controllable {
    public Camera(float x, float y, float w, float h) {
        super(x, y, w, h);
    }
}
