package primitives;

public class Material {
    public Double3 kD = Double3.ZERO, kS = Double3.ZERO;
    public int nShininess = 0;

    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

}
