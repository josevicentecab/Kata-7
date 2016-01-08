package Kata7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import Kata7.application.swing.HistogramPanel;
import Kata7.application.swing.Toolbar;
import Kata7.control.CalculateCommand;
import Kata7.control.Command;
import Kata7.view.AttributeDialog;
import Kata7.view.HistogramDisplay;
import Kata7.view.PopulationDialog;

/* Autor: José Vicente Cabañas */

public class Application extends JFrame  {
    
    private Map <String, Command> commands = new HashMap<>();
    private AttributeDialog attributeDialog;
    private PopulationDialog populationDialog;
    private HistogramDisplay display;

    public static void main(String[] args) {
        new Application().setVisible(true);
    }
    public Application (){
        this.deployUI();
        this.createCommands();
    }
    private void deployUI() {
        this.setTitle("Histogram Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMaximumSize(new Dimension(500,500));
        this.setLocationRelativeTo(null);
        this.getContentPane().add(histogramPanel());
        this.getContentPane().add(toolbar(commands), BorderLayout.NORTH);
    }
    private void createCommands() {
        commands.put("calculate", new CalculateCommand(attributeDialog, populationDialog, display));
    }
    private HistogramPanel histogramPanel() {
        HistogramPanel panel = new HistogramPanel();
        this.display = panel;
        return panel;
    }
    private JPanel toolbar(Map<String, Command> commands) {
        Toolbar panel = new Toolbar(commands);
        this.attributeDialog = panel;
        this.populationDialog = panel;
        return panel;
    }
}
