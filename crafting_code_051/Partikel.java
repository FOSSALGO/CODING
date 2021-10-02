package fosalgo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.RadialGradient;

public class Partikel {

    public static final int DEFAULT_LIFETIME = 200;
    public static final int MAX_RADIUS = 5;
    public static final int MAX_SPEED = 10;

    public boolean isAlive;
    public double lifetime;
    public double age;
    public double radius;
    public double cx, cy, vx, vy;

    public Partikel(double cx, double cy) {
        this.cx = cx;
        this.cy = cy;
        this.isAlive = true;
        this.radius = Acak.acakInt(1, MAX_RADIUS);
        this.lifetime = DEFAULT_LIFETIME;
        this.age = 0;
        this.vx = Acak.acakFloat(0, MAX_SPEED * 2) - MAX_SPEED;
        this.vy = Acak.acakFloat(0, MAX_SPEED * 2) - MAX_SPEED;
        // smoothing out the diagonal speed
        if (vx * vx + vy * vy > MAX_SPEED * MAX_SPEED) {
            vx *= 0.7;
            vy *= 0.7;
        }
    }
    
    public void update() {
        if (isAlive) {
            this.cx += this.vx;
            this.cy += this.vy;
            this.age++;
            if (age >= this.lifetime) {
                isAlive = false;
            }
        }
    }
    
    public void draw(GraphicsContext gc, RadialGradient rg) {
        if (isAlive&&gc!=null) {
            gc.setFill(rg);
            gc.fillOval(cx, cy, radius, radius);
            update();
        }
    }
    
}
