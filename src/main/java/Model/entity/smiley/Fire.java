package Model.entity.smiley;

import Controller.Constants;
import Model.GameObjects;
import view.GlassFrame;

public class Fire extends GameObjects {
    public Fire(int x, int y) {
        super(x, y);
        setLocalFrame(GlassFrame.getINSTANCE());
        setPreviousLocalFrame(GlassFrame.getINSTANCE());
        getLocalFrames().add(GlassFrame.getINSTANCE());
    }
    @Override
    public int getWidth() {
        return Constants.fireSize();
    }

    @Override
    public int getHeight() {
        return Constants.fireSize();
    }
}
