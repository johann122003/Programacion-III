class SoldadoVirtual {
    public int ID;
    public String Alias;
    public String TecnologiaEspecial;
    public int NivelVirtualidad;
    public String DimensionOperativa;

    public SoldadoVirtual(int ID, String alias, String tecnologiaEspecial, int nivelVirtualidad, String dimensionOperativa) {
        this.ID = ID;
        this.Alias = alias;
        this.TecnologiaEspecial = tecnologiaEspecial;
        this.NivelVirtualidad = nivelVirtualidad;
        this.DimensionOperativa = dimensionOperativa;
    }
}