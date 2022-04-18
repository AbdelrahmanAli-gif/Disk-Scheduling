import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
public class UICon {

    
    @FXML
    void initialize() {
        inputAlgo.getItems().addAll("FCFS", "SSTF", "LOOK", "CLOOK", "SCAN", "CSCAN", "Real Time");
        inputAlgo.setValue(inputAlgo.getItems().get(0));
    
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
    void runBtnPressed(ActionEvent event) {
        XYChart.Series<Number, Number> dataSeries1 = new XYChart.Series<Number, Number>();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data<Number, Number>(1, 567));
        dataSeries1.getData().add(new XYChart.Data<Number, Number>(5, 612));
        dataSeries1.getData().add(new XYChart.Data<Number, Number>(10, 800));
        dataSeries1.getData().add(new XYChart.Data<Number, Number>(20, 780));
        dataSeries1.getData().add(new XYChart.Data<Number, Number>(40, 810));
        dataSeries1.getData().add(new XYChart.Data<Number, Number>(80, 850));

        graph.getData().add(dataSeries1);    
    }

}
