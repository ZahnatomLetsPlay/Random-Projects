package spacedelyeeter9001.game;

import GLOOP.GLVektor;

public class SpaceVector {

    double vectorx;
    double vectory;
    double vectorz;

    public SpaceVector(double x, double y, double z) {
        this.vectorx = x;
        this.vectory = y;
        this.vectorz = z;
    }

    public SpaceVector(GLVektor vec) {
        this.vectorx = vec.gibX();
        this.vectory = vec.gibY();
        this.vectorz = vec.gibZ();
    }

    public SpaceVector print(){
        System.out.println(this.toString());
        return this;
    }
    
    public SpaceVector add(double x, double y, double z) {
        this.vectorx += x;
        this.vectory += y;
        this.vectorz += z;
        return this;
    }

    public SpaceVector add(SpaceVector vec) {
    	this.vectorx += vec.getX();
    	this.vectory += vec.getY();
    	this.vectorz += vec.getY();
    	return this;
    }
    
    public SpaceVector sub(SpaceVector vec) {
    	this.vectorx -= vec.getX();
    	this.vectory -= vec.getY();
    	this.vectorz -= vec.getY();
    	return this;
    }
    
    public SpaceVector sub(double x, double y, double z) {
        this.vectorx -= x;
        this.vectory -= y;
        this.vectorz -= z;
        return this;
    }

    public SpaceVector set(double x, double y, double z) {
        this.vectorx = x;
        this.vectory = y;
        this.vectorz = z;
        return this;
    }

    public SpaceVector clone() {
        return new SpaceVector(this.toGLVektor());
    }

    public double getX() {
        return this.vectorx;
    }

    public double getY() {
        return this.vectory;
    }

    public double getZ() {
        return this.vectorz;
    }

    public SpaceVector setX(double x) {
        this.vectorx = x;
        return this;
    }

    public SpaceVector setY(double y) {
        this.vectory = y;
        return this;
    }

    public SpaceVector setZ(double z) {
        this.vectorz = z;
        return this;
    }

    @Override
    public String toString() {
    	return "{SpaceVector; " + this.vectorx + "; " + this.vectory + "; " + this.vectorz + "}";
    }
    
    public GLVektor toGLVektor() {
        return new GLVektor(this.vectorx, this.vectory, this.vectorz);
    }

    public boolean equals(SpaceVector vec) {
    	if(this.vectorx == vec.getX() && this.vectory == vec.getY() && this.vectorz == vec.getZ()) {
    		return true;
    	} 
    	return false;
    }
    
}
