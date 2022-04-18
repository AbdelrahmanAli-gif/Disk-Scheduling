import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application{
    private FXMLLoader loader;
    Parent root;

    public void view(){
        launch();
    }

    @Override
    public void start(Stage arg0) throws Exception {
        try {
            loader = new FXMLLoader();
            File file = new File("src\\UI.fxml");
            loader.setLocation(file.toURI().toURL());
            root = loader.<AnchorPane>load();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        arg0.setScene(new Scene(root));
        arg0.show();
        
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.view();
    }

}

