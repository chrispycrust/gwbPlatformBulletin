package com.fdmgroup.gwbPlatformBulletin.model;

public enum Honorific {
	
	QUEEN("Queen"),
    PRINCESS("Princess"),
    LADY("Lady"),
    THE_HONORABLE("The Honorable"),
    DUCHESS("Duchess"),
    VISCOUNTESS("Viscountess"),
    MARCHIONESS("Marchioness"),
    COUNTESS("Countess"),
    Baroness("Baroness"),
    DOWAGER("Dowager"),
    MRS("Mrs"),
    MISS("Miss");
    
    private final String title;

    Honorific(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
