package ru.sbt.les18_DesignPatterns.HomeWork;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class Controller implements Initializable {
    private final String IMG_FILE = "Files\\Chee.jpg";

    @FXML ImageView imgView;

    enum LoaderKind{
        RESIZE, BW, BLUR, BORDER, FLIP;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImgLoader imgLoader = new DefaultImageLoader();
        imgView.setFitHeight(-1);
        imgView.setFitWidth(-1);
        imgView.setImage(imgLoader.loadImage(IMG_FILE));

        imgView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            List<LoaderKind> generateRandomList(){
                List<LoaderKind> decorations = new ArrayList<>();
                if (ThreadLocalRandom.current().nextBoolean())
                    decorations.add(LoaderKind.BLUR);
                if (ThreadLocalRandom.current().nextBoolean())
                    decorations.add(LoaderKind.BORDER);
                if (ThreadLocalRandom.current().nextBoolean())
                    decorations.add(LoaderKind.BW);
                if (ThreadLocalRandom.current().nextBoolean())
                    decorations.add(LoaderKind.RESIZE);
                if (ThreadLocalRandom.current().nextBoolean())
                    decorations.add(LoaderKind.FLIP);
                Collections.shuffle(decorations);
                return decorations;
            }

            @Override
            public void handle(MouseEvent event) {
                ImgLoader imgLoader = new DefaultImageLoader();

                String title = "";
                List<LoaderKind> decorations = generateRandomList();
                for (LoaderKind loaderKind : decorations){
                    switch (loaderKind){
                        case BLUR:      imgLoader = new ImgLoaderBlur(imgLoader);   title += "Blur";   break;
                        case BORDER:    imgLoader = new ImgLoaderBorder(imgLoader); title += "Border"; break;
                        case BW:        imgLoader = new ImgLoaderBW(imgLoader);     title += "B/W";    break;
                        case RESIZE:    imgLoader = new ImgLoaderResize(imgLoader); title += "Resize"; break;
                        case FLIP:      imgLoader = new ImgLoaderFlip(imgLoader);   title += "Flip";   break;
                    }
                    title += ", ";
                }

                if (decorations.size()>0)
                    title = title.substring(0, title.length()-2);

                imgView.setImage(imgLoader.loadImage(IMG_FILE));
                Main.setTitle(title);
                event.consume();
            }
        });
    }
}
