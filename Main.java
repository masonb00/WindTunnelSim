package mygame;
import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Line;
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
 * “JMonkeyEngine Docs.” jMonkeyEngine Docs, wiki.jmonkeyengine.org/docs/3.4/tutorials/beginner/hello_simpleapplication.html. Accessed 11 Apr. 2025.  
 * 
 * Version/date: V1
 * 
 * Responsibilities of class:
 * To create and govern the main method and application build
 */
public class Main extends SimpleApplication
{
    private final float xStart = -5f; //global var
    private enum ViewMode { STREAMLINES, PARTICLES}
    private ViewMode currentMode = ViewMode.STREAMLINES;
    private final Node streamlineNode = new Node("Streamlines");
    private final HashMap<Geometry, Vector3f> originalOffsets = new HashMap<>();
    private final ArrayList<Geometry> particles = new ArrayList<>();
    private final Node particleNode = new Node("Particles");
    private final int gridY = 10;
    private final int gridZ = 10;
    private final float spacing = 0.3f;
    
            
    public static void main(String[] args)
    {
        Main app = new Main();
        app.start();
    }
    
    @Override
    public void simpleInitApp()
    {    
        //Create the Materials
        MaterialProperty aluminum = new MaterialProperty("Aluminum", 0.47, ColorRGBA.Blue);
        MaterialProperty wood = new MaterialProperty("Wood", 0.82, ColorRGBA.Brown);
        MaterialProperty mesh = new MaterialProperty("Mesh", 1.6, ColorRGBA.Gray);
        MaterialProperty foam = new MaterialProperty("Foam", 1.2, ColorRGBA.Orange);
        MaterialProperty carbonFiber = new MaterialProperty("Carbon Fiber", 1.28, ColorRGBA.DarkGray);
        
        //Create the 3D sphere
        Shape sphere = new Sphere(0.1, aluminum, assetManager);
        
        //Attach the sphere to the scene
        rootNode.attachChild(sphere.getGeometry());
        
        //Attach the mode Nodes to the scene
        rootNode.attachChild(streamlineNode);
        rootNode.attachChild(particleNode);
        
        WindTunnel tunnel = new WindTunnel(10); //Create WindTunnel with speed 10 m/s
        double drag = tunnel.computeDrag(sphere);
        System.out.println("Drag Force: " + drag + " N");
        
        //Build streamlines
        Streamline.buildStreamlines(assetManager, streamlineNode, gridY, gridZ, spacing);
        
        //Build Particles
        Particle.buildParticles(assetManager, particleNode, particles, originalOffsets, xStart, gridY, gridZ, spacing);
        
        
              
        //Add lighting
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);
        
        //Input designations
        inputManager.addMapping("ToggleView", new com.jme3.input.controls.KeyTrigger(com.jme3.input.KeyInput.KEY_SPACE));
        inputManager.addListener(toggleListener, "ToggleView");
            
        viewPort.setBackgroundColor(ColorRGBA.DarkGray);       
    }
    
    //Create the toggleListener for ViewMode enum
    private final com.jme3.input.controls.ActionListener toggleListener = new com.jme3.input.controls.ActionListener() 
    {
        @Override
        public void onAction(String name, boolean isPressed, float tpf)
        {
            if (name.equals("ToggleView") && isPressed)
            {
                if (currentMode == ViewMode.STREAMLINES)
                {
                    currentMode = ViewMode.PARTICLES;
                    streamlineNode.setCullHint(Spatial.CullHint.Always);
                    particleNode.setCullHint(Spatial.CullHint.Inherit);
                } else 
                {
                    currentMode = ViewMode.STREAMLINES;
                    particleNode.setCullHint(Spatial.CullHint.Always);
                    streamlineNode.setCullHint(Spatial.CullHint.Inherit);
                }
            }
        }
    };
        
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
