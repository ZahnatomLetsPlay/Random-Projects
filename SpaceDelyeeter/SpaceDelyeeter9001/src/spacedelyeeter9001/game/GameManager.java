package spacedelyeeter9001.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import GLOOP.GLKamera;
import GLOOP.GLLicht;
import GLOOP.GLMaus;
import GLOOP.GLTastatur;
import GLOOP.Sys;

public class GameManager {

    GLKamera cam;
    GameState state;
    Delyeeter user;
    List<SpaceObject> obj;
    List<Shot> shots;
    GLTastatur key;
    GLMaus mouse;
    double hitasts = 0;
    double astsleft = 0;
    GLLicht light;
    double tick = 0;
    boolean shot = false;

    public GameManager() {
        this.state = GameState.STARTING;
        obj = new ArrayList<>();
        key = new GLTastatur();
        shots = new ArrayList<>();
        mouse = new GLMaus();
    }

    public GameState getState() {
        return this.state;
    }

    public void start() {
        this.cam = new GLKamera();
        this.state = GameState.PLAYING;
        this.user = new Delyeeter();
        this.obj.add(user);
        this.light = new GLLicht();
        this.light.setzeAbschwaechung(0);
        for (int i = 0; i < 25; i++) {
            this.obj.add(new Asteroid());
            astsleft++;
        }
        this.cam.setzeBlickpunkt(this.user.getPos().toGLVektor());
        this.cam.setzePosition(this.user.getPos().add(0, -200, 150).toGLVektor());
        while (!key.esc() && this.state.equals(GameState.PLAYING)) {
            tick();
            collisionCheck();
            shotCheck();
            this.cam.setzeBlickpunkt(this.user.getPos().toGLVektor());
            this.cam.setzePosition(this.user.getPos().add(0, -200, 150).toGLVektor());
            tick++;
            tick%=60;
            Sys.warte(1000 / 60);

            if ( hitasts >= 5 || astsleft == 0) {
                break;
            }

        }
        //System.out.println(hitasts);
        this.cam.zeigeFenster(false);
        System.exit(0);
    }

    private void shotCheck() {
        if (this.key.istGedrueckt(" ".charAt(0)) || this.mouse.linksklick() || this.mouse.gedruecktLinks()) {
            if (shot == false /* && tick % 4 == 0 */) {
                this.shots.add(new Shot());
                shot = true;
            }
        } else {
            shot = false;
        }
    }

    private void collisionCheck() {
        for (SpaceObject sobj : this.obj) {
            if (!sobj.equals(obj.get(0))) {
                Asteroid as = (Asteroid) sobj;
                Delyeeter del = (Delyeeter) this.obj.get(0);
                SpaceVector delpos = del.getPos();
                SpaceVector objpos = as.getPos();
                if (objpos.getX() >= delpos.getX() - 15 && objpos.getX() <= delpos.getX() + 15) {
                    if (objpos.getY() <= 10 && objpos.getY() >= -10) {
                        if (as.respawnnum <= 0) {
                            as.asteroid.setzeSelbstleuchten(0, 255, 0);
                            as.respawn();
                            hitasts += 1;
                            // System.out.println((int) hitasts + (hitasts == 1 ? " Asteroid" : "
                            // Asteroids") + " hit.");
                        }
                    }
                }
            }
        }
    }

    public List<Shot> getShots() {
        return this.shots;
    }

    public List<Asteroid> getAsteroids() {
        List<Asteroid> r = new ArrayList<>();
        for (SpaceObject sobj : this.obj) {
            if (Asteroid.class.isAssignableFrom(sobj.getClass())) {
                r.add((Asteroid) sobj);
            }
        }
        return r;
    }

    private void tick() {
        for (SpaceObject obj : this.obj) {
            obj.update();
        }
        for (Iterator<Shot> iter = shots.iterator(); iter.hasNext();) {
            Shot s = iter.next();
            s.update();
        }
        for (Iterator<Shot> iter = shots.iterator(); iter.hasNext();) {
            Shot s = iter.next();
            for (Iterator<SpaceObject> iterator = this.obj.iterator(); iterator.hasNext();) {
                SpaceObject obj = iterator.next();
                if (Asteroid.class.isAssignableFrom(obj.getClass())) {
                    Asteroid as = (Asteroid) obj;
                    SpaceVector asvec = as.getPos();
                    SpaceVector svec = s.getPos();
                    if (svec.getX() <= asvec.getX() + 5 && svec.getX() >= asvec.getX() - 5) {
                        if (svec.getY() <= asvec.getY() + s.speed && svec.getY() >= asvec.getY() - s.speed) {
                            s.remove();
                            as.remove();
                            iter.remove();
                            iterator.remove();
                            astsleft--;
                            return;
                        }
                    }
                }
            }
            if (s.getPos().getY() >= 1000) {
                s.remove();
                iter.remove();
            }
        }
       // System.out.println(getAsteroids().size() + (astsleft == 1 ? " Asteroid" : " Asteroids") + " left.");
    }

    public SpaceVector getNextPos() {
        return new SpaceVector(getRandom(200, -200), getRandom(600, 400), 0);
    }

    public double getRandom(int max, int min) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public GLTastatur getKey() {
        return this.key;
    }

    public void collide() {
        this.state = GameState.DEAD;
        this.user = null;
    }

    public Delyeeter getPlayer() {
        return this.user;
    }

    public GLKamera getCam() {
        return this.cam;
    }

}
