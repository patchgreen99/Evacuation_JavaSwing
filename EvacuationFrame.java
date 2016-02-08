import javax.swing.JFrame;


public class EvacuationFrame extends JFrame{
	
	private EvacuationPanel myPanel;
	public Room room;
	
	public EvacuationFrame(Room a,int speed){
        room = a;
		setSize(a.getxdimension()+2*a.getWall().getThickness()+16,a.getydimension()+2*a.getWall().getThickness()+39); //extra addition for the JFrames borders
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myPanel = new EvacuationPanel(a,speed);
		getContentPane().add(myPanel);}
	
	public void Update(){
		myPanel.Update();
	}
		
}

