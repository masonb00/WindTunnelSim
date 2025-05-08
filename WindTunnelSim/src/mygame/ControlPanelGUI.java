package mygame;

import com.jme3.scene.Node;
import com.jme3.renderer.Camera;
import com.simsilica.lemur.*;


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
 * “Nifty 1.3 Controls Example/Demonstration.” Vimeo, void, 6 May 2025, vimeo.com/25637085. 
 * 
 * Version/date: V1
 * 
 * Responsibilities of class:
 * To create the GUI responsible for all changes to the application and other user interaction
 */
@SuppressWarnings("return")
public class ControlPanelGUI
{
    private final Container panel;
    
    public ControlPanelGUI(Node guiNode, Camera cam)
    {
        //Define the GUI
        panel = new Container();
        guiNode.attachChild(panel);
        panel.setLocalTranslation(50, cam.getHeight() - 50, 0);
   
        //Add a Label
        panel.addChild(new Label("Choose Shape: "));
        
        //Add the Shape buttons
        Button sphereButton = new Button("Sphere");
        Button cubeButton = new Button("Cube");
        Button flatPlateButton = new Button("Flat Plate");
            
        panel.addChild(sphereButton);
        panel.addChild(cubeButton);
        panel.addChild(flatPlateButton);
            
        //Shape Button Action Logic
        sphereButton.addClickCommands(source -> System.out.println("Sphere selected"));
        cubeButton.addClickCommands(source -> System.out.println("Cube selected"));
        flatPlateButton.addClickCommands(source -> System.out.println("Flat plate selected"));
        
        //Add a slider
        Slider windSpeedSlider = new Slider();
        windSpeedSlider.setDelta(5f);
        
        panel.addChild(windSpeedSlider);
    }
    
    public Container getPanel()
    {
        return panel;
    }
}
