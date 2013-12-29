package mare.Nwindow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NotificationWindow {
	
	private Stage nstage;
	
	private int showplace = 0;
	
	private int showtime = 0;
	
	static int UPPER_LEFT = 0;
	static int UPPER_RIGHT = 1;
	static int LOWER_LEFT = 2;
	static int LOWER_RIGHT = 3;
	
	NotificationWindow(Stage stage){
		nstage = stage;
	}

	public void setNstage(Stage nstage) {
		this.nstage = nstage;
	}

	public int getShowplace() {
		return showplace;
	}

	public void setShowplace(int showplace) {
		this.showplace = showplace;
	}
	
	public int getShowtime() {
		return showtime;
	}

	public void setShowtime(int showtime) {
		this.showtime = showtime;
	}
	
	public void showNotificationWindow() throws IOException{
		Rectangle2D sb = Screen.getPrimary().getVisualBounds();
		
		double X = 0;
		double Y = 0;
		
		if(showplace == 0){
			X = 10;
			Y = 10;
		}else if(showplace == 1){
			X = sb.getWidth() - nstage.getScene().getRoot().prefWidth(0) - 30; 
			Y = 10; 
		}else if(showplace == 2){
			X = 10; 
			Y = sb.getHeight() - nstage.getScene().getRoot().prefHeight(0) - 50; 
		}else if(showplace == 3){
			X = sb.getWidth() - nstage.getScene().getRoot().prefWidth(0) - 30; 
			Y = sb.getHeight() - nstage.getScene().getRoot().prefHeight(0) - 50; 
		}
		
		nstage.setX(X);
		nstage.setY(Y);
		
		File file = new File("log.txt");
		if(!file.exists()){
			file.createNewFile();
			file.canWrite();
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
		pw.println("Stagename:"+nstage.getTitle());
		pw.println("Bounds_width:"+sb.getWidth());
		pw.println("Bounds_Height:"+sb.getHeight());
		pw.println("StageWidth:"+nstage.getScene().getRoot().prefWidth(0));
		pw.println("StageHeight:"+nstage.getScene().getRoot().prefHeight(0));
		pw.println("showplace:"+showplace);
		pw.println("X:"+X+" Y:"+Y);
		pw.println("showtime:"+showtime);
		pw.println("");
		pw.close();
		
		nstage.show();
					
		if(showtime != 0){
			Timeline timeline = new Timeline(
				new KeyFrame(
					new Duration(showtime*1000),
					new EventHandler<ActionEvent>(){
						@Override
						public void handle(ActionEvent event){
							nstage.close();
						}
					}
				)
			);
			timeline.play();
		}
	}
}
