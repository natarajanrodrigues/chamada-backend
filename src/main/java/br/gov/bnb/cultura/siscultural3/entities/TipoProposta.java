package br.gov.bnb.cultura.siscultural3.entities;

public enum TipoProposta {

    MUSICAI(1, "Música - Instrumental"),
    MUSICAV(2, "Música - Vocal"),
    CENICAST(3, "Artes Cênicas - Teatro"),
    CENICASD(5, "Artes Cênicas - Dança"),
    INFTE(6, "Atividades Infantis - Teatro"),
    INFCO(7, "Atividades Infantis - Contação de História"),
    INFOF(8, "Atividades Infantis - Oficina");

    int id;
    String description;

    TipoProposta(int n, String d) {
        id = n;
        description = d;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return this.description;
    }

    public static TipoProposta parse(int id) {

        for (TipoProposta u : TipoProposta.values()) {
            if (u.getId() == id)
                return u;
        }
        return null;
    }
}
