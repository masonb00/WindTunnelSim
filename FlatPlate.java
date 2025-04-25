package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Mason Boelter
 */
public class FlatPlate extends Shape
{
    /////Fields/////
	private final String shapeType = "FlatPlate"; //The type of shape
	
	/////Constructors/////
	/**
	 * Purpose: To create a flat plate
	 * @param newXArea, the cross sectional area of the flat plate
	 * @param newMaterial, the material property of the flat plate
         * @param assetManager, the asset manager for the flat plate
	 */
	public FlatPlate (double newXArea, MaterialProperty newMaterial, AssetManager assetManager)
	{
            super(newXArea, newMaterial);
            
            //Build the 3-D Shape
            com.jme3.scene.shape.Box mesh = new com.jme3.scene.shape.Box(1f, 0.05f, 1f);
            this.geometry = new Geometry("FlatPlateObject", mesh);
            
            this.geometry.setMaterial(material.createJmeMaterial(assetManager));
            this.geometry.setLocalTranslation(0, 0, 0);
	}
	
	/////Methods/////
	/**
	 * Purpose: To get the type of shape
	 * @return String, the type of shape
	 */
	public String getShapeType()
	{
		return shapeType;
	}
	
	/**
	 * Purpose: To define a toString method for type falt plate
	 */
        @Override
	public String toString()
	{
		return shapeType + " with " + getMaterial().getName();
	}
}
