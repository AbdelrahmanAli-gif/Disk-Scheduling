import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
public class UICon {

    
    @FXML
    void initialize() {
        inputAlgo.getItems().addAll("FCFS", "SSTF", "LOOK", "C-LOOK", "SCAN", "C-SCAN", "REAL TIME");
        inputAlgo.setValue(inputAlgo.getItems().get(0));
        inputCount.setText(StaticData.CYLINDER_RANGE+"");
        inputHead.setText(StaticData.INITIAL_HEAD_POINTER+"");
        inputSeq.setText("98 183 37 122 14 124 65 67");
    }
    @FXML
    private LineChart<Number, Number> graph;

    @FXML
    private ComboBox<String> inputAlgo;

    @FXML
    private TextField inputCount;

    @FXML
    private TextField inputHead;

    @FXML
    private Button inputRun;

    @FXML
    private TextField inputSeq;

    @FXML
    private TextField outputHead;

    @FXML
    private TextField outputSeq;

    @FXML
    void clearBtnPressed(ActionEvent event){
        graph.getData().clear();
        outputHead.clear();
        outputSeq.clear();
    }

    @FXML
    void runBtnPressed(ActionEvent event) {
        String chosenAlgo = inputAlgo.getValue();
        String[] inputSeqString = inputSeq.getText().split(" ");
        ArrayList<Integer> requests = new ArrayList<>();
        StaticData.CYLINDER_RANGE = Integer.parseInt(inputCount.getText());
        StaticData.INITIAL_HEAD_POINTER = Integer.parseInt(inputHead.getText());
        boolean validData = true;

        for(String req: inputSeqString){
            int reqInt = Integer.parseInt(req);
            if(reqInt > StaticData.CYLINDER_RANGE){
                validData = false;
                break;
            }
            requests.add(reqInt);
        }

        if(validData){
            // TODO: wot
            Scheduler algo = null;
            switch (chosenAlgo) {
                case "FCFS":
                    algo = new FCFS(requests);
                    break;
                case "SCAN":
                    algo = new SCAN(requests);
                    break;
                case "C-SCAN":
                    algo = new C_SCAN(requests);
                    break;
                case "REAL TIME":
                    algo = new REAL_TIME(requests);
                    break;

                case "LOOK":
                    algo = new Look(requests);
                    break;

                case "C-LOOK":
                    algo = new C_Look(requests);
                    break;

                case "SSTF":
                    algo = new SSTF(requests);
                    break;
                default:
                    break;
            }
    
            algo.start();
            ArrayList<Integer> result = algo.getResultingSequence();
            int total = algo.getTotalHeadMovements();
            String resultString = "";
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(chosenAlgo);

            for(int i = 0; i < result.size(); i++){
                int head = result.get(i);
                series.getData().add(new XYChart.Data<Number,Number>(i+1, head));
                resultString += (head +"");
                if(i != result.size() - 1)
                    resultString += ", ";
            }

            graph.getData().add(series);
            outputSeq.setText(resultString);
            outputHead.setText(total + "");

        }
    }
}
