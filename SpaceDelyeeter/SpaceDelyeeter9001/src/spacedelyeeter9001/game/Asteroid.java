package spacedelyeeter9001.game;

import GLOOP.GLKugel;
import spacedelyeeter9001.SpaceDelyeeter9001;

public class Asteroid extends SpaceObject {

    GLKugel asteroid;
    GameManager man;
    double startz;
    double x;
    double speed;
    int respawnnum = 0;
    boolean exists = true;

    public Asteroid() {
        man = SpaceDelyeeter9001.getManager();
        SpaceVector vec = man.getNextPos();
        this.x = vec.getX();
        asteroid = new GLKugel(vec.toGLVektor(), 5);
        startz = getPos().getZ();
        this.speed = man.getRandom(10, 5);
    }

    @Override
    public void update() {
        if (exists) {
            this.move(0, -speed, 0);
            if (this.getPos().getY() < -200) {
                destroy();
                this.asteroid.setzeSichtbarkeit(true);
            }
            switch (respawnnum) {
                case 0:
                    this.asteroid.setzeSelbstleuchten(0, 0, 0);
                    respawnnum--;
                    break;
                default:
                    respawnnum--;
                    break;
            }
        }
    }

    public void destroy() {
        this.speed = man.getRandom(10, 5);
        this.asteroid.setzeSichtbarkeit(false);
        this.asteroid.setzePosition(man.getNextPos().setY(-100).toGLVektor());
        this.asteroid.setzePosition(getPos().clone().setY(600).toGLVektor());
        this.asteroid.setzeSelbstleuchten(0, 0, 0);
    }

    public void remove(){
        destroy();
        this.exists = false;
        
    }
    
    public void respawn() {
        respawnnum = 60;
        this.asteroid.setzeSelbstleuchten(255, 0, 0);
    }

    public void move(double x, double y, double z) {
        asteroid.setzePosition((new SpaceVector(asteroid.gibPosition())).add(x, y, z).toGLVektor());
    }

    public void setPos(SpaceVector vec) {
        this.asteroid.setzePosition(vec.toGLVektor());
    }

    public SpaceVector getPos() {
        return new SpaceVector(this.asteroid.gibPosition());
    }

}
