package controller;


import model.AAIModel;
import model.HeartModel;
import model.VVIModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame implements ActionListener{
    HeartModel model=new HeartModel();
    AAIModel model1=new AAIModel();
    VVIModel model2=new VVIModel();
    public int getPP(int rate){
        return  model.getPP(rate);
    }
//    AAI
    public void setLowerRate(int rate){

        model1.setLowerRate(rate);
    }
    public int getLowerRate(){
        return model1.getLowerRate();
    }
//    VVI
    public void setLowRate(int rate){

        model2.setLowRate(rate);

    }
    public int getLowRate(){
        return model2.getLowRate();

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    public void on() {

    }
}
