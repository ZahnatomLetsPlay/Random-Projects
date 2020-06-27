package spacedelyeeter9001.game;

import java.util.ArrayList;
import java.util.List;

import GLOOP.GLKugel;
import GLOOP.GLObjekt;
import GLOOP.GLQuader;
import GLOOP.GLTastatur;
import GLOOP.GLZylinder;
import spacedelyeeter9001.SpaceDelyeeter9001;

public class Delyeeter extends SpaceObject {

	ArrayList<DelyeeterObject> delyeeter;
	GameManager man;
	GLTastatur key;
	double angl;
	double movespeed = 5;
	double anglspeed = 3;

	public Delyeeter() {
		this.man = SpaceDelyeeter9001.getManager();
		this.delyeeter = new ArrayList<>();
		// delyeeter.add(new DelyeeterObject(new GLPrismoid(0, 0, 0, 20, 20, 4, 10), 0,
		// 0, 0));
		// delyeeter.add(new DelyeeterObject(new GLZylinder(0, -25 / 2, -15, 3, 25), 20,
		// 0, 0));
		// delyeeter.add(new DelyeeterObject(new GLQuader(0, 24, -15, 50, 15, 2), 0, 0,
		// 0));
		delyeeter.add(new DelyeeterObject(new GLZylinder(0, 0, 0, 3, 20), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLQuader(0, 0, 0, 30, 15, 1), 0, 20, 0));
		delyeeter.add(new DelyeeterObject(new GLQuader(0, 0, 0, 30, 15, 1), 0, -20, 0));
		delyeeter.add(new DelyeeterObject(new GLZylinder(Math.cos(20) * 15, Math.sin(20) * 15, 0, 3, 20), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLZylinder(Math.sin(20) * 15, -Math.cos(20) * 15, 0, 3, 20), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLZylinder(-Math.cos(20) * 15, Math.sin(20) * 15, 0, 3, 20), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLZylinder(-Math.sin(20) * 15, -Math.cos(20) * 15, 0, 3, 20), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(0, 0, 10, 3), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(Math.cos(20) * 15, Math.sin(20) * 15, 10, 3), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(Math.sin(20) * 15, -Math.cos(20) * 15, 10, 3), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(-Math.cos(20) * 15, Math.sin(20) * 15, 10, 3), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(-Math.sin(20) * 15, -Math.cos(20) * 15, 10, 3), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(0, 0, -10, 3), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(Math.cos(20) * 15, Math.sin(20) * 15, -10, 3), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(Math.sin(20) * 15, -Math.cos(20) * 15, -10, 3), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(-Math.cos(20) * 15, Math.sin(20) * 15, -10, 3), 90, 0, 0));
		delyeeter.add(new DelyeeterObject(new GLKugel(-Math.sin(20) * 15, -Math.cos(20) * 15, -10, 3), 90, 0, 0));
		this.key = new GLTastatur();
		angl = 0;
	}

	@Override
	public void update() {
		if (key.rechts() || key.istGedrueckt("d".charAt(0))) {
			if (!((((double) movespeed / 45) * angl) + (double) getPos().getX() > (double) 200)) {
				if (angl + anglspeed <= 45 && getPos().getX() < (double) 200) {
					angl += anglspeed;
				}
				move(((double) movespeed / 45) * angl, 0, 0);
			} else {
				if (angl > 0) {
					angl -= anglspeed;
					if (!((((double) movespeed / 45) * angl) + (double) getPos().getX() > (double) 200)) {
						move(((double) movespeed / 45) * angl, 0, 0);
					}
				} else if (angl < 0) {
					angl += anglspeed;
					if (!((((double) movespeed / 45) * angl) + (double) getPos().getX() < (double) -200)) {
						move(((double) movespeed / 45) * angl, 0, 0);
					}
				}
			}
		} else if (key.links() || key.istGedrueckt("a".charAt(0))) {
			if (!((((double) movespeed / 45) * angl) + (double) getPos().getX() < (double) -200)) {
				if (angl - anglspeed >= -45 && getPos().getX() > (double) -200) {
					angl -= anglspeed;
				}
				move(((double) movespeed / 45) * angl, 0, 0);
			} else {
				if (angl > 0) {
					angl -= anglspeed;
					if (!((((double) movespeed / 45) * angl) + (double) getPos().getX() > (double) 200)) {
						move(((double) movespeed / 45) * angl, 0, 0);
					}
				} else if (angl < 0) {
					angl += anglspeed;
					if (!((((double) movespeed / 45) * angl) + (double) getPos().getX() < (double) -200)) {
						move(((double) movespeed / 45) * angl, 0, 0);
					}
				}
			}
		} else {
			if (angl > 0) {
				angl -= anglspeed;
				if (!((((double) movespeed / 45) * angl) + (double) getPos().getX() > (double) 200)) {
					move(((double) movespeed / 45) * angl, 0, 0);
				}
			} else if (angl < 0) {
				angl += anglspeed;
				if (!((((double) movespeed / 45) * angl) + (double) getPos().getX() < (double) -200)) {
					move(((double) movespeed / 45) * angl, 0, 0);
				}
			}
		}
		if (this.getPos().getX() > (double) 200) {
			this.move((double) 200 - getPos().getX(), 0, 0);
		} else if (this.getPos().getX() < (double) -200) {
			this.move((double) -200 - getPos().getX(), 0, 0);
		}
		rotate(angl);

	}

	public List<DelyeeterObject> getObjects() {
		return this.delyeeter;
	}

	public void shoot() {

	}

	public void rotate(double angl) {
		for (DelyeeterObject dobj : this.delyeeter) {
			if (dobj.equals(this.delyeeter.get(0)) || dobj.getStartPos().equals(this.delyeeter.get(0).getStartPos())) {
				// dobj.getObject().setzeDrehung(0, angl, 0);
				dobj.setRotation(dobj.getRotation()[0], angl + dobj.getStartRotation()[1], dobj.getRotation()[2]);
			} else {
				double objangle = angl + dobj.getStartAngle();
				GLObjekt obj = dobj.getObject();
				dobj.setRotation(dobj.getRotation()[0], objangle + dobj.getStartRotation()[1], dobj.getRotation()[2]);
				double rads = Math.toRadians(objangle);
				obj.setzePosition(getPos().getX() + (Math.sin(rads) * dobj.getDist()), dobj.getStartPos().getZ(),
						Math.cos(rads) * dobj.getDist());
			}
		}
	}

	public void move(double x, double y, double z) {
		for (DelyeeterObject dobj : this.delyeeter) {
			GLObjekt obj = dobj.getObject();
			SpaceVector vec = new SpaceVector(obj.gibPosition());
			vec.add(x, y, z);
			obj.setzePosition(vec.toGLVektor());
		}
	}

	public void setPos(SpaceVector vec) {
		move(vec.getX() - getPos().getX(), vec.getY() - getPos().getY(), vec.getZ() - getPos().getZ());
	}

	public SpaceVector getPos() {
		return this.delyeeter.get(0).getPos();
	}

}
