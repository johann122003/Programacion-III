public class PowerRanger {
    public int ID;
    public String NombreReal;
    public String TipoDePoder;
    public int NivelDelEntrenamiento;
    public String BaseSecreta;

    public PowerRanger(int ID, String nombreReal, String tipoDePoder, int nivelDelEntrenamiento, String baseSecreta) {
        this.ID = ID;
        NombreReal = nombreReal;
        TipoDePoder = tipoDePoder;
        NivelDelEntrenamiento = nivelDelEntrenamiento;
        BaseSecreta = baseSecreta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreReal() {
        return NombreReal;
    }

    public void setNombreReal(String nombreReal) {
        NombreReal = nombreReal;
    }

    public String getTipoDePoder() {
        return TipoDePoder;
    }

    public void setTipoDePoder(String tipoDePoder) {
        TipoDePoder = tipoDePoder;
    }

    public int getNivelDelEntrenamiento() {
        return NivelDelEntrenamiento;
    }

    public void setNivelDelEntrenamiento(int nivelDelEntrenamiento) {
        NivelDelEntrenamiento = nivelDelEntrenamiento;
    }

    public String getBaseSecreta() {
        return BaseSecreta;
    }

    public void setBaseSecreta(String baseSecreta) {
        BaseSecreta = baseSecreta;
    }
}
