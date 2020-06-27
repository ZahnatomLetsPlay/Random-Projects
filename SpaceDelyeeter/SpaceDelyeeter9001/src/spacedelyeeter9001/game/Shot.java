package spacedelyeeter9001.game;

import GLOOP.GLZylinder;
import spacedelyeeter9001.SpaceDelyeeter9001;

public class Shot {

	DelyeeterObject shot;
	double speed = 25;

	public Shot() {
		shot = new DelyeeterObject(
				new GLZylinder(SpaceDelyeeter9001.getManager().getPlayer().getPos().clone().add(0, 0, 0).toGLVektor(),
						1, 3),
				90, 0, 0);
		shot.getObject().setzeSelbstleuchten(255, 255, 0);
	}

	public void update() {
		this.shot.move(0, speed, 0);
	}

	public void remove() {
		this.shot.getObject().loesche();
	}

	public SpaceVector getPos() {
		return shot.getPos();
	}

}
