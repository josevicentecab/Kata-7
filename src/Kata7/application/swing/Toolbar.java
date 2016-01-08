package Kata7.application.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import Kata7.control.Command;
import Kata7.model.Attribute;
import Kata7.view.AttributeDialog;
import Kata7.view.PopulationDialog;

public class Toolbar extends JPanel implements AttributeDialog, PopulationDialog {

    private final Map <String, Command> commands;
    private final List<Attribute> attributes = new ArrayList<>();
    private JComboBox combo;

    public Toolbar(Map<String, Command> commands) {
        super (new FlowLayout());
        this.commands = commands;
        this.add(mailDomainAttribute());
        this.add(firstMailAttribute());
        this.add(comboBox());
        this.add(calculateButton());
    }
    @Override
    public List population() {
        try {
            return MailReader.read("emails.txt");
        } catch (IOException ex) {
            return new ArrayList();
        }
    }
    @Override
    public Attribute<Person, String> attribute() {
        return attributes.get(combo.getSelectedIndex());
    }
    private Attribute mailDomainAttribute() {
        return new Attribute<Person, String>() {

            @Override
            public String get(Person item) {
                return item.getMail().split("@")[1];
            }
        };
    }
    private Attribute firstMailAttribute() {
        return new Attribute<Person, Character>() {

            @Override
            public Character get(Person item) {
                return item.getMail().charAt(0);
            }
        };       
    }
    private JComboBox comboBox() {
        combo = new JComboBox(options ("Mail Domains","First Char"));
        return combo;
    }
    private JButton calculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("calculate").execute();
            }
        });
        return button;
    }
    private String[] options(String... options) {
        return options;
    }
    private void add(Attribute attribute){
        attributes.add(attribute);
    }
}
