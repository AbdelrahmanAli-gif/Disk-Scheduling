import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.org.apache.regexp.internal.RE;
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






        /**
         * Console Run Main (Un-Comment First!)
         */

//        ArrayList<Integer> queue = new ArrayList<>();
//        queue.add(98);
//        queue.add(183);
//        queue.add(37);
//        queue.add(122);
//        queue.add(14);
//        queue.add(124);
//        queue.add(65);
//        queue.add(67);
//
//
//        Scanner scanner = new Scanner(System.in);
//        boolean looper = true;
//        while (looper){
//            System.out.println("Enter The Algo You want to run (Slides Example!)....." +
//                    "\n1) FCFS 2) SSTF 3) SCAN 4) LOOK 5) REAl TIME 0) EXIT");
//            int choice = scanner.nextInt();
//            switch (choice){
//                case 0:
//                    looper=false;
//                    break;
//                case 1:
//                    FCFS fcfs = new FCFS(queue);
//                    fcfs.start();
//                    break;
//                case 2:
//                    SSTF sstf = new SSTF(queue);
//                    sstf.SSTF_Scheduling();
//                    break;
//                case 3:
//                    System.out.println("1) SCAN 2) C-SCAN");
//                    int sub_choice = scanner.nextInt();
//                    switch (sub_choice){
//                        case 1:
//                            SCAN scan = new SCAN(queue);
//                            scan.SCAN_Start();
//                            break;
//
//                        case 2:
//                            C_SCAN c_scan = new C_SCAN(queue);
//                            c_scan.C_SCAN_Start();
//                            break;
//
//                        default:
//                            System.out.println("Wrong Choice!!");
//                            break;
//                    }
//                    break;
//                case 4:
//                    System.out.println("1) LOOK 2) C-LOOK");
//                    int sub_choice2 = scanner.nextInt();
//                    StaticData.INITIAL_HEAD_POINTER=53;
//
//                    switch (sub_choice2){
//                        case 1:
//                            Look look = new Look(queue);
//                            look.lookAlgo();
//                            break;
//
//                        case 2:
//                            C_Look c_look = new C_Look(queue);
//                            c_look.cLookAlgo();
//                            break;
//
//                        default:
//                            System.out.println("Wrong Choice!!");
//                            break;
//                    }
//                    StaticData.INITIAL_HEAD_POINTER=53;
//                    break;
//
//                case 5:
//                    REAL_TIME real_time = new REAL_TIME(queue);
//                    real_time.REAL_TIME_Start();
//                    break;
//
//                default:
//                    System.out.println("Wrong Choice!!");
//                    break;
//            }
//
//        }
//





    }

}

