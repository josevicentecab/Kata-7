package Kata7.control;

import Kata7.model.Histogram;
import Kata7.view.AttributeDialog;
import Kata7.view.HistogramBuilder;
import Kata7.view.HistogramDisplay;
import Kata7.view.PopulationDialog;

public class CalculateCommand implements Command {

    private final AttributeDialog attributeDialog;
    private final PopulationDialog populationDialog;
    private final HistogramDisplay display;

    public CalculateCommand(AttributeDialog attributeDialog, PopulationDialog populationDialog, HistogramDisplay display) {
        this.attributeDialog = attributeDialog;
        this.populationDialog = populationDialog;
        this.display = display;
    }
    @Override
    public void execute() {
        Histogram histogram = new HistogramBuilder(populationDialog.population()).build(attributeDialog.attribute());
        display.show(histogram);
    }
}
