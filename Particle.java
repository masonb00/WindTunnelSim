package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;

/**
 * Lead Author(s):
 * @author Mason Boelter
 * @author 
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * 
 * References:
 *  
 * Version/date: V1
 * 
 * Responsibilities of class:
 * To define all fields and methods needed by subclasses
 */
public class Particle
{
	/////Fields/////
        private double xPos; //The x position of the particle
        private final Geometry geo; //The geometry of the Particle
	
	/////Constructor/////
	/**
	 * Purpose: To create a particle with an x,y position and velocity
         * @param assetManager, the asset manager of the program
         * @param startPos, the start position of the object
	 */
	public Particle(AssetManager assetManager, Vector3f startPos)
	{
            Sphere mesh = new Sphere(6, 6, 0.05f);
            geo = new Geometry("Particle", mesh);
            
            Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            mat.setColor("Color", ColorRGBA.White);
            geo.setMaterial(mat);
            geo.setLocalTranslation(startPos);
	}
	
        /////Methods/////
        
	/**
         * Purpose: To get the geometry of the Particle
         * @return Geometry, the geometry of the particle
         */
        public Geometry getGeometry()
        {
            return geo;
        }
        
        /**
         * Purpose: To get the vector of the Particle
         * @return Vector3f, the vector of the Particle
         */
        public Vector3f getPosition()
        {
            return geo.getLocalTranslation();
        }
        
        /**
         * Purpose: To set the vector of the Particle
         * @param pos, the vector to set the Particle with
         */
        public void setPosition(Vector3f pos)
        {
            geo.setLocalTranslation(pos);
        }
        
        /**
	 * Purpose: To update the particles X position based on windSpeed
	 * @param windSpeed, the windSpeed in the tunnel
	 * @param timeStep, the timeStep of the simulation
	 */
	public void updateParticleX(double windSpeed, double timeStep)
	{
		this.xPos += windSpeed * timeStep;
	}
	
}
