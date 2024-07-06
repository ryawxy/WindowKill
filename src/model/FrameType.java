package model;

import java.awt.*;

public interface FrameType {
    boolean isometric();
    boolean solid();
    Rectangle getBound();
}
