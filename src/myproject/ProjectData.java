package myproject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ProjectData {

    private static ProjectData projectData;
    protected BufferedImage epsilon;
    private ProjectData() throws IOException {
        addImage();
    }
    private void addImage() throws IOException {

        //epsilon
        String path = "circle.png";
        File file = new File(path);
        //  epsilon = ImageIO.read(Objects.requireNonNull(getClass().getResource("circle.png")));
    }
    public static ProjectData getProjectData() throws IOException {
        if(projectData == null){
            projectData = new ProjectData();
        }
        return projectData;
    }

    public BufferedImage getEpsilon() {
        return epsilon;
    }
}
