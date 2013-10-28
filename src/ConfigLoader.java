import java.awt.*;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;


public class ConfigLoader {
	
	private String file;
	private XMLDecoder D;
	
	private void openFile () {
		FileDialog fd = new FileDialog(new Frame(), "Charger une configuration...", FileDialog.LOAD);
		fd.setFile("SmartHouse_Config.xml");
		fd.setDirectory(".");
		fd.setVisible(true);
		file = fd.getDirectory() + fd.getFile();
	}
	
	private void decodeFile () {
		try {
			D = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
			readFile();
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		}	
	}
	
	private void readFile () {
	 
		 Vector<Piece> p = (Vector<Piece>) D.readObject(); 
		 Boolean st = (Boolean) D.readObject();
		 Boolean pl = (Boolean) D.readObject();
		 Integer h = (Integer) D.readObject();

		 
	 
		 
		 Maison.getInstance().removeAllPiece();
		 Maison.getInstance().setPiece(p);
		 Maison.getInstance().setNbPiece(p.size());
		 Maison.getInstance().setHeure(h);
		 Maison.getInstance().setPlay(pl);
		 Maison.getInstance().setStop(st);
 
		 System.out.println(Maison.getInstance());
		
		D.close();
	}
	
	public void load () {
		openFile();
		decodeFile();	
	}
}
