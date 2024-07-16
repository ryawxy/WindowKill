package Model;

import Model.entity.*;
import Model.enums.ArchmireType;
import view.SettingsFrame;
import view.entityViews.barricados.BarricadosPanel;

import java.util.ArrayList;

public class Wave {

    public  ArrayList<Trigorath> wave1EasyTrigorath = new ArrayList<>();
    public  ArrayList<Squarantine> wave1EasySquarantine = new ArrayList<>();
    public  ArrayList<Necropick> wave1EasyNecropicks = new ArrayList<>();
    public  ArrayList<Omenoct> wave1EasyOmenoct = new ArrayList<>();
    public  ArrayList<Archmire> wave1EasyArchmire = new ArrayList<>();
    public  ArrayList<Barricados> wave1EasyBarricados = new ArrayList<>();

    public  ArrayList<Trigorath> wave1MediumTrigorath = new ArrayList<>();
    public  ArrayList<Squarantine> wave1MediumSquarantine = new ArrayList<>();

    public  ArrayList<Trigorath> wave1HardTrigorath = new ArrayList<>();
    public  ArrayList<Squarantine> wave1HardSquarantine = new ArrayList<>();

    public  ArrayList<Trigorath> wave2EasyTrigorath = new ArrayList<>();
    public  ArrayList<Squarantine> wave2EasySquarantine = new ArrayList<>();

    public  ArrayList<Trigorath> wave2MediumTrigorath = new ArrayList<>();
    public  ArrayList<Squarantine> wave2MediumSquarantine = new ArrayList<>();

    public  ArrayList<Trigorath> wave2HardTrigorath = new ArrayList<>();
    public  ArrayList<Squarantine> wave2HardSquarantine = new ArrayList<>();
    public ArrayList<Trigorath> wave3EasyTrigorath = new ArrayList<>();
    public static ArrayList<Squarantine> wave3EasySquarantine = new ArrayList<>();

    public ArrayList<Trigorath> wave3MediumTrigorath = new ArrayList<>();
    public ArrayList<Squarantine> wave3MediumSquarantine = new ArrayList<>();

    public ArrayList<Trigorath> wave3HardTrigorath = new ArrayList<>();
    public ArrayList<Squarantine> wave3HardSquarantine = new ArrayList<>();
    public ArrayList<BarricadosPanel> barricadosPanels = new ArrayList<>();



  static   Squarantine squarantine;
   static Trigorath trigorath;

public  void initWave1(){


    if(SettingsFrame.getChosenLevel()==0){
        int [] xPoints = {50,40,60};
        int [] yPoints = {20,40,40};

        int [] xPoints2 = {150,140,160};
        int [] yPoints2 = {120,140,140};

        int [] xPoints3 = {250,240,260};
        int [] yPoints3 = {220,240,240};

        int [] xPoints4 = {75,95,95,75};
        int [] yPoints4 = {75,75,95,95};

        int [] xPoints5 = {175,195,195,175};
        int [] yPoints5 = {175,175,195,195};

        int[] xPoints6 = {370,360,340,330,340,360};
        int[] yPoints6 = {350, (int) (350+20*Math.sqrt(3)/2),(int) (350+20*Math.sqrt(3)/2),350,(int) (350-20*Math.sqrt(3)/2),(int) (350-20*Math.sqrt(3)/2)};

//        Omenoct omenoct = new Omenoct(350,350);
//        omenoct.setxPoints(xPoints6);
//        omenoct.setyPoints(yPoints6);
 //       wave1EasyOmenoct.add(omenoct);
//
//        squarantine = new Squarantine(185,185);
//        squarantine.setxPoints(xPoints5);
//        squarantine.setyPoints(yPoints5);
//        wave1EasySquarantine.add(squarantine);
//
//        trigorath = new Trigorath(50,33);
//        trigorath.setxPoints(xPoints);
//        trigorath.setyPoints(yPoints);
//        wave1EasyTrigorath.add(trigorath);
//
//        trigorath = new Trigorath(150,133);
//        trigorath.setxPoints(xPoints2);
//        trigorath.setyPoints(yPoints2);
//        wave1EasyTrigorath.add(trigorath);

        trigorath = new Trigorath(250,233);
        trigorath.setxPoints(xPoints3);
        trigorath.setyPoints(yPoints3);
        trigorath.setLocalxPoints(xPoints3);
        trigorath.setLocalyPoints(yPoints3);
        wave1EasyTrigorath.add(trigorath);

//        Archmire archmire = new Archmire(50,50, ArchmireType.MINI);
     //   wave1EasyArchmire.add(archmire);

        Necropick necropick = new Necropick(300,300);
    //    wave1EasyNecropicks.add(necropick);

//        Barricados barricados = new Barricados(100,100);
//        wave1EasyBarricados.add(barricados);
    //    BarricadosPanel panel = new BarricadosPanel();
    //    barricadosPanels.add(panel);

    }
    if(SettingsFrame.getChosenLevel()==1){
        int [] xPoints = {50,40,60};
        int [] yPoints = {20,40,40};

        int [] xPoints2 = {150,140,160};
        int [] yPoints2 = {120,140,140};

        int [] xPoints3 = {250,240,260};
        int [] yPoints3 = {220,240,240};

        int [] xPoints4 = {75,95,95,75};
        int [] yPoints4 = {75,75,95,95};

        int [] xPoints5 = {675,695,695,675};
        int [] yPoints5 = {675,675,695,695};


        squarantine = new Squarantine(685,685);
        squarantine.setxPoints(xPoints5);
        squarantine.setyPoints(yPoints5);
        wave1MediumSquarantine.add(squarantine);

        squarantine = new Squarantine(85,85);
        squarantine.setxPoints(xPoints4);
        squarantine.setyPoints(yPoints4);
        wave1MediumSquarantine.add(squarantine);

        trigorath = new Trigorath(50,33);
        trigorath.setxPoints(xPoints);
        trigorath.setyPoints(yPoints);
        wave1MediumTrigorath.add(trigorath);

        trigorath = new Trigorath(150,133);
        trigorath.setxPoints(xPoints2);
        trigorath.setyPoints(yPoints2);
        wave1MediumTrigorath.add(trigorath);

        trigorath = new Trigorath(250,233);
        trigorath.setxPoints(xPoints3);
        trigorath.setyPoints(yPoints3);
        wave1MediumTrigorath.add(trigorath);
    }
    if(SettingsFrame.getChosenLevel()==2){
        int [] xPoints = {50,40,60};
        int [] yPoints = {20,40,40};

        int [] xPoints2 = {150,140,160};
        int [] yPoints2 = {120,140,140};

        int [] xPoints3 = {250,240,260};
        int [] yPoints3 = {220,240,240};

        int [] xPoints4 = {75,95,95,75};
        int [] yPoints4 = {75,75,95,95};

        int [] xPoints5 = {675,695,695,675};
        int [] yPoints5 = {675,675,695,695};

        int [] xPoints6 = {450,440,460};
        int [] yPoints6 = {420,440,440};

        squarantine = new Squarantine(685,685);
        squarantine.setxPoints(xPoints5);
        squarantine.setyPoints(yPoints5);
        wave1HardSquarantine.add(squarantine);

        squarantine = new Squarantine(85,85);
        squarantine.setxPoints(xPoints4);
        squarantine.setyPoints(yPoints4);
        wave1HardSquarantine.add(squarantine);

        trigorath = new Trigorath(50,33);
        trigorath.setxPoints(xPoints);
        trigorath.setyPoints(yPoints);
        wave1HardTrigorath.add(trigorath);

        trigorath = new Trigorath(150,133);
        trigorath.setxPoints(xPoints2);
        trigorath.setyPoints(yPoints2);
        wave1HardTrigorath.add(trigorath);

        trigorath = new Trigorath(250,233);
        trigorath.setxPoints(xPoints3);
        trigorath.setyPoints(yPoints3);
        wave1HardTrigorath.add(trigorath);

        trigorath = new Trigorath(450,433);
        trigorath.setxPoints(xPoints6);
        trigorath.setyPoints(yPoints6);
        wave1HardTrigorath.add(trigorath);
    }

}

    public void initWave2(){
        if(SettingsFrame.getChosenLevel()==0){
            int [] xPoints = {50,40,60};
            int [] yPoints = {20,40,40};

            int [] xPoints2 = {150,140,160};
            int [] yPoints2 = {120,140,140};

            int [] xPoints4 = {75,95,95,75};
            int [] yPoints4 = {75,75,95,95};

            int [] xPoints5 = {275,295,295,275};
            int [] yPoints5 = {275,275,295,295};

            squarantine = new Squarantine(85,85);
            squarantine.setxPoints(xPoints4);
            squarantine.setyPoints(yPoints4);
            wave2EasySquarantine.add(squarantine);

//            squarantine = new Squarantine(285,285);
//            squarantine.setxPoints(xPoints5);
//            squarantine.setyPoints(yPoints5);
//            wave2EasySquarantine.add(squarantine);
//
//            trigorath = new Trigorath(50,33);
//            trigorath.setxPoints(xPoints);
//            trigorath.setyPoints(yPoints);
//            wave2EasyTrigorath.add(trigorath);
//
//            trigorath = new Trigorath(150,133);
//            trigorath.setxPoints(xPoints2);
//            trigorath.setyPoints(yPoints2);
//            wave2EasyTrigorath.add(trigorath);


        }
        if(SettingsFrame.getChosenLevel()==1){
            int [] xPoints = {50,40,60};
            int [] yPoints = {20,40,40};

            int [] xPoints2 = {150,140,160};
            int [] yPoints2 = {120,140,140};

            int [] xPoints4 = {75,95,95,75};
            int [] yPoints4 = {75,75,95,95};

            int [] xPoints5 = {275,295,295,275};
            int [] yPoints5 = {275,275,295,295};

            int [] xPoints6 = {575,595,595,575};
            int [] yPoints6 = {575,575,595,595};

            int [] xPoints7 = {275,295,295,275};
            int [] yPoints7 = {275,275,295,295};

            squarantine = new Squarantine(85,85);
            squarantine.setxPoints(xPoints4);
            squarantine.setyPoints(yPoints4);
            wave2MediumSquarantine.add(squarantine);

            squarantine = new Squarantine(585,585);
            squarantine.setxPoints(xPoints6);
            squarantine.setyPoints(yPoints6);
            wave2MediumSquarantine.add(squarantine);

            squarantine = new Squarantine(285,285);
            squarantine.setxPoints(xPoints5);
            squarantine.setyPoints(yPoints5);
            wave2MediumSquarantine.add(squarantine);

            trigorath = new Trigorath(50,33);
            trigorath.setxPoints(xPoints);
            trigorath.setyPoints(yPoints);
            wave2MediumTrigorath.add(trigorath);

            trigorath = new Trigorath(150,133);
            trigorath.setxPoints(xPoints2);
            trigorath.setyPoints(yPoints2);
            wave2MediumTrigorath.add(trigorath);

            trigorath = new Trigorath(250,233);
            trigorath.setxPoints(xPoints7);
            trigorath.setyPoints(yPoints7);
            wave2MediumTrigorath.add(trigorath);

        }
        if(SettingsFrame.getChosenLevel()==2){
            int [] xPoints = {50,40,60};
            int [] yPoints = {20,40,40};

            int [] xPoints2 = {150,140,160};
            int [] yPoints2 = {120,140,140};

            int [] xPoints3 = {250,240,260};
            int [] yPoints3 = {220,240,240};

            int [] xPoints4 = {75,95,95,75};
            int [] yPoints4 = {75,75,95,95};

            int [] xPoints5 = {675,695,695,675};
            int [] yPoints5 = {675,675,695,695};

            int [] xPoints6 = {450,440,460};
            int [] yPoints6 = {420,440,440};

            int [] xPoints7 = {550,540,560};
            int [] yPoints7 = {520,540,540};


            squarantine = new Squarantine(685,685);
            squarantine.setxPoints(xPoints5);
            squarantine.setyPoints(yPoints5);
            wave2HardSquarantine.add(squarantine);

            squarantine = new Squarantine(85,85);
            squarantine.setxPoints(xPoints4);
            squarantine.setyPoints(yPoints4);
            wave2HardSquarantine.add(squarantine);

//            trigorath = new Trigorath(50,33);
//            trigorath.setxPoints(xPoints);
//            trigorath.setyPoints(yPoints);
//            wave2HardTrigorath.add(trigorath);

            trigorath = new Trigorath(150,133);
            trigorath.setxPoints(xPoints2);
            trigorath.setyPoints(yPoints2);
            wave2HardTrigorath.add(trigorath);

            trigorath = new Trigorath(250,233);
            trigorath.setxPoints(xPoints3);
            trigorath.setyPoints(yPoints3);
            wave2HardTrigorath.add(trigorath);

            trigorath = new Trigorath(450,433);
            trigorath.setxPoints(xPoints6);
            trigorath.setyPoints(yPoints6);
            wave2HardTrigorath.add(trigorath);

            trigorath = new Trigorath(550,533);
            trigorath.setxPoints(xPoints7);
            trigorath.setyPoints(yPoints7);
            wave2HardTrigorath.add(trigorath);
        }
    }
    public void initWave3(){
        if(SettingsFrame.getChosenLevel()==0){
            int [] xPoints = {50,40,60};
            int [] yPoints = {20,40,40};

            int [] xPoints2 = {150,140,160};
            int [] yPoints2 = {120,140,140};

            int [] xPoints3 = {250,240,260};
            int [] yPoints3 = {220,240,240};

            int [] xPoints4 = {75,95,95,75};
            int [] yPoints4 = {75,75,95,95};

            int [] xPoints5 = {350,340,360};
            int [] yPoints5 = {320,340,340};

            int [] xPoints6 = {450,440,460};
            int [] yPoints6 = {420,440,440};

            int [] xPoints7 = {575,595,595,575};
            int [] yPoints7 = {575,575,595,595};

            squarantine = new Squarantine(85,85);
            squarantine.setxPoints(xPoints4);
            squarantine.setyPoints(yPoints4);
            wave3EasySquarantine.add(squarantine);
//
            squarantine = new Squarantine(585,585);
            squarantine.setxPoints(xPoints7);
            squarantine.setyPoints(yPoints7);
            wave3EasySquarantine.add(squarantine);

            trigorath = new Trigorath(50,33);
            trigorath.setxPoints(xPoints);
            trigorath.setyPoints(yPoints);
            wave3EasyTrigorath.add(trigorath);

            trigorath = new Trigorath(150,133);
            trigorath.setxPoints(xPoints2);
            trigorath.setyPoints(yPoints2);
            wave3EasyTrigorath.add(trigorath);

            trigorath = new Trigorath(250,233);
            trigorath.setxPoints(xPoints3);
            trigorath.setyPoints(yPoints3);
            wave3EasyTrigorath.add(trigorath);

            trigorath = new Trigorath(350,333);
            trigorath.setxPoints(xPoints5);
            trigorath.setyPoints(yPoints5);
            wave3EasyTrigorath.add(trigorath);

            trigorath = new Trigorath(450,433);
            trigorath.setxPoints(xPoints6);
            trigorath.setyPoints(yPoints6);
            wave3EasyTrigorath.add(trigorath);
        }
        if(SettingsFrame.getChosenLevel()==1){
            int [] xPoints = {50,40,60};
            int [] yPoints = {20,40,40};

            int [] xPoints2 = {150,140,160};
            int [] yPoints2 = {120,140,140};

            int [] xPoints4 = {75,95,95,75};
            int [] yPoints4 = {75,75,95,95};

            int [] xPoints5 = {275,295,295,275};
            int [] yPoints5 = {275,275,295,295};

            int [] xPoints6 = {575,595,595,575};
            int [] yPoints6 = {575,575,595,595};

            int [] xPoints7 = {275,295,295,275};
            int [] yPoints7 = {275,275,295,295};

            int [] xPoints8 = {375,395,395,375};
            int [] yPoints8 = {375,375,395,395};

            squarantine = new Squarantine(85,85);
            squarantine.setxPoints(xPoints4);
            squarantine.setyPoints(yPoints4);
            wave3MediumSquarantine.add(squarantine);

            squarantine = new Squarantine(585,585);
            squarantine.setxPoints(xPoints6);
            squarantine.setyPoints(yPoints6);
            wave3MediumSquarantine.add(squarantine);

            squarantine = new Squarantine(285,285);
            squarantine.setxPoints(xPoints5);
            squarantine.setyPoints(yPoints5);
            wave3MediumSquarantine.add(squarantine);


            squarantine = new Squarantine(385,385);
            squarantine.setxPoints(xPoints8);
            squarantine.setyPoints(yPoints8);
            wave3MediumSquarantine.add(squarantine);

            trigorath = new Trigorath(50,33);
            trigorath.setxPoints(xPoints);
            trigorath.setyPoints(yPoints);
            wave3MediumTrigorath.add(trigorath);

            trigorath = new Trigorath(150,133);
            trigorath.setxPoints(xPoints2);
            trigorath.setyPoints(yPoints2);
            wave3MediumTrigorath.add(trigorath);

            trigorath = new Trigorath(250,233);
            trigorath.setxPoints(xPoints7);
            trigorath.setyPoints(yPoints7);
            wave3MediumTrigorath.add(trigorath);
        }
        if(SettingsFrame.getChosenLevel()==2){
            int [] xPoints = {50,40,60};
            int [] yPoints = {20,40,40};

            int [] xPoints2 = {150,140,160};
            int [] yPoints2 = {120,140,140};

            int [] xPoints3 = {250,240,260};
            int [] yPoints3 = {220,240,240};

            int [] xPoints4 = {75,95,95,75};
            int [] yPoints4 = {75,75,95,95};

            int [] xPoints5 = {675,695,695,675};
            int [] yPoints5 = {675,675,695,695};

            int [] xPoints6 = {450,440,460};
            int [] yPoints6 = {420,440,440};

            int [] xPoints7 = {550,540,560};
            int [] yPoints7 = {520,540,540};

            int [] xPoints8 = {275,295,295,275};
            int [] yPoints8 = {275,275,295,295};

            squarantine = new Squarantine(685,685);
            squarantine.setxPoints(xPoints5);
            squarantine.setyPoints(yPoints5);
            wave3HardSquarantine.add(squarantine);
//
//            squarantine = new Squarantine(85,85);
//            squarantine.setxPoints(xPoints4);
//            squarantine.setyPoints(yPoints4);
//            wave3HardSquarantine.add(squarantine);

            squarantine = new Squarantine(285,285);
            squarantine.setxPoints(xPoints8);
            squarantine.setyPoints(yPoints8);
            wave3HardSquarantine.add(squarantine);

            trigorath = new Trigorath(50,33);
            trigorath.setxPoints(xPoints);
            trigorath.setyPoints(yPoints);
            wave3HardTrigorath.add(trigorath);

            trigorath = new Trigorath(150,133);
            trigorath.setxPoints(xPoints2);
            trigorath.setyPoints(yPoints2);
            wave3HardTrigorath.add(trigorath);

            trigorath = new Trigorath(250,233);
            trigorath.setxPoints(xPoints3);
            trigorath.setyPoints(yPoints3);
            wave3HardTrigorath.add(trigorath);

            trigorath = new Trigorath(450,433);
            trigorath.setxPoints(xPoints6);
            trigorath.setyPoints(yPoints6);
            wave3HardTrigorath.add(trigorath);

            trigorath = new Trigorath(550,533);
            trigorath.setxPoints(xPoints7);
            trigorath.setyPoints(yPoints7);
            wave3HardTrigorath.add(trigorath);
        }
    }


}
