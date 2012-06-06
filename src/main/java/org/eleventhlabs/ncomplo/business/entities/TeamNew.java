package org.eleventhlabs.ncomplo.business.entities;


public enum TeamNew {

    GERMANY("Alemania"),
    ALGERIA("Argelia"),
    ARGENTINA("Argentina"),
    AUSTRALIA("Australia"),
    BRAZIL("Brasil"),
    CAMEROON("Camer\u00FAn"),
    CHILE("Chile"),
    KOREA_DPR("Corea del Norte"),
    KOREA_REPUBLIC("Corea del Sur"),
    COTE_DIVOIRE("Costa de Marfil"),
    DENMARK("Dinamarca"),
    SLOVAKIA("Eslovaquia"),
    SLOVENIA("Eslovenia"),
    SPAIN("Espa\u00F1a"),
    UNITED_STATES("Estados Unidos"),
    FRANCE("Francia"),
    GHANA("Ghana"),
    GREECE("Grecia"),
    NETHERLANDS("Holanda"),
    HONDURAS("Honduras"),
    ENGLAND("Inglaterra"),
    ITALY("Italia"),
    JAPAN("Jap\u00F3n"),
    MEXICO("M\u00E9xico"),
    NIGERIA("Nigeria"),
    NEW_ZEALAND("Nueva Zelanda"),
    PARAGUAY("Paraguay"),
    PORTUGAL("Portugal"),
    SERBIA("Serbia"),
    SOUTH_AFRICA("Sud\u00E1frica"),
    SWITZERLAND("Suiza"),
    URUGUAY("Uruguay");

    
    public static TeamNew[] ALL_TEAMS = 
        new TeamNew[] { 
            GERMANY, ALGERIA, ARGENTINA, AUSTRALIA, BRAZIL,
            CAMEROON, CHILE, KOREA_DPR, KOREA_REPUBLIC, COTE_DIVOIRE,
            DENMARK, SLOVAKIA, SLOVENIA, SPAIN, UNITED_STATES,
            FRANCE, GHANA, GREECE, NETHERLANDS, HONDURAS,
            ENGLAND, ITALY, JAPAN, MEXICO, NIGERIA,
            NEW_ZEALAND, PARAGUAY, PORTUGAL, SERBIA, SOUTH_AFRICA,
            SWITZERLAND, URUGUAY
        };
    
    
    private final String localizedName;
    
    private TeamNew(final String localizedName) {
        this.localizedName = localizedName;
    }


    @Override
    public String toString() {
        return this.localizedName;
    }
    
}
