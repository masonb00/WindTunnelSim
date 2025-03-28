package mygame;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.util.ArrayList;
import java.util.HashMap;

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
public class Main extends SimpleApplication
{
    private final float xStart = -5f; //global var
    private final ArrayList<Geometry> particles = new ArrayList<>();
    private final int gridY = 10;
    private final int gridZ = 10;
    private final float spacing = 0.4f;
    private final HashMap<Geometry, Vector3f> originalOffsets = new HashMap<>();

    public static void main(String[] args)
    {
        Main app = new Main();
        app.start();
    }
        
    @Override
    public void simpleInitApp()
    {    
        //Create the Material
        MaterialProperty aluminum = new MaterialProperty("Aluminum", 0.47, ColorRGBA.Blue);
        
        //Create the 3D sphere
        Shape sphere = new Sphere(0.1, aluminum, assetManager);
        
        //Attach the sphere to the scene
        rootNode.attachChild(sphere.getGeometry());
        
        WindTunnel tunnel = new WindTunnel(10); //Create WindTunnel with speed 10 m/s
        double drag = tunnel.computeDrag(sphere);
        System.out.println("Drag Force: " + drag + " N");
        
        //Create the particle stream
        for (int y = -gridY / 2; y < gridY / 2; y++ )
        {
            for (int z = -gridZ / 2; z < gridZ / 2; z++)
            {
                Vector3f startPos = new Vector3f(xStart, y * spacing, z * spacing);
                Particle particle = new Particle(assetManager, startPos);
                
                originalOffsets.put(particle.getGeometry(), startPos);
                particles.add(particle.getGeometry());
                rootNode.attachChild(particle.getGeometry());
            }
        }
              
        //Add lighting
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
            
        viewPort.setBackgroundColor(ColorRGBA.DarkGray);
            
    }
        
    @Override
    public void simpleUpdate(float tpf)
    {
        Vector3f objectCenter = new Vector3f(0, 0, 0);
        float objectRadius = 1f;
        
        for (Geometry p : particles)
        {
            Vector3f pos = p.getLocalTranslation();
            pos.x += tpf * 2f; 
            
            if(pos.distance(objectCenter) < objectRadius + 0.5f)
            {
                pos.y += tpf * 1.5f;
            }
            
            //Reset particle after passing object
            if (pos.x > 5f) 
            {
                Vector3f original = originalOffsets.get(p);
                pos.x = xStart;
                pos.y = original.y;
                pos.z = original.z;  
            }
            
            
            
        p.setLocalTranslation(pos);
        
        }
            
    }

}
