package spacedelyeeter9001.game;

import GLOOP.GLObjekt;

public class DelyeeterObject {

	SpaceVector startpos;
	double dist;
	GLObjekt obj;
	double[] rotation;
	double startangl;
	double[] startrotation;

	public DelyeeterObject(GLObjekt obj, double xrot, double yrot, double zrot) {
		this.obj = obj;
		startpos = new SpaceVector(obj.gibPosition());
		dist = Math.sqrt(Math.pow(startpos.getX(), 2) + Math.pow(startpos.getY(), 2));
		startangl = (double) Math.round(Math.toDegrees(Math.acos(getPos().getX() / dist)));
		if (startpos.getX() < 0) {
			if (startpos.getY() < 0) {
				startangl += 90;
			} else {
				startangl += 180;
			}
		} else {
			if (startpos.getY() < 0) {
				startangl += 90;
			}
		}
		this.rotation = new double[] { xrot, yrot, zrot };
		this.startrotation = new double[] { xrot, yrot, zrot };
		this.obj.setzeDrehung(xrot, yrot, zrot);
	}

	public double getDist() {
		return this.dist;
	}

	public GLObjekt getObject() {
		return this.obj;
	}

	public SpaceVector getStartPos() {
		return this.startpos;
	}

	public DelyeeterObject setRotation(double x, double y, double z) {
		this.rotation = new double[] { x, y, z };
		this.obj.setzeDrehung(x, y, z);
		return this;
	}

	public double[] getRotation() {
		return this.rotation;
	}

	public double getStartAngle() {
		return this.startangl;
	}

	public SpaceVector getPos() {
		return new SpaceVector(obj.gibPosition());
	}

	public double[] getStartRotation() {
		return this.startrotation;
	}

	public DelyeeterObject setPos(double x, double y, double z) {
		this.obj.setzePosition(x, y, z);
		return this;
	}
	
	public DelyeeterObject setPos(SpaceVector vec) {
		setPos(vec.getX(), vec.getY(), vec.getZ());
		return this;
	}
	
	public DelyeeterObject move(SpaceVector vec) {
		move(vec.getX(), vec.getY(), vec.getZ());
		return this;
	}
	
	public DelyeeterObject move(double x, double y, double z) {
		this.setPos(getPos().clone().add(x, y, z));
		return this;
	}
	
}
