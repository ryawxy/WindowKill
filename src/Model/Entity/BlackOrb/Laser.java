package Model.Entity.BlackOrb;

public class Laser {

    BlackOrb start;
    BlackOrb end;

    public Laser(BlackOrb start, BlackOrb end) {
        this.start = start;
        this.end = end;
    }

    public BlackOrb getStart() {
        return start;
    }

    public BlackOrb getEnd() {
        return end;
    }
}
