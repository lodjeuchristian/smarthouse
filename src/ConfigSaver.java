import java.awt.*;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Vector;


public class ConfigSaver {

	private String file;
	private XMLEncoder E;
	

	private void saveFile () {
		FileDialog fd = new FileDialog(new Frame(), "Sauver la configuration...", FileDialog.SAVE);
		fd.setFile("SmartHouse_Config.xml");
		fd.setDirectory(".");
		fd.setVisible(true);
		file = fd.getDirectory() + fd.getFile();
	}

	private void encodeFile () {
		try {
			E = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
			writeFile();
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		}	
	}
	
	private void writeFile () {
	 
	   Vector<Piece> p = Maison.getInstance().getPiece(); 
	   Boolean st = Maison.getInstance().isStop();
	   Boolean pl = Maison.getInstance().isPlay();
	   Integer h = Maison.getInstance().getHeure();
	   
		 

		  E.writeObject(p); 
		  E.writeObject(st);
		  E.writeObject(pl);
		  E.writeObject(h);
	 
		  E.close();
	}
	
	public void save () {
		saveFile();
		encodeFile();	
	}
}
