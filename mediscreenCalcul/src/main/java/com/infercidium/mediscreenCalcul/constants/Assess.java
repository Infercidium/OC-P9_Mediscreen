package com.infercidium.mediscreenCalcul.constants;

public final class Assess {

    private Assess() { }

    /**
     * Limit age to calculate diabetes.
     */
    public static final int AGE_MAX = 30;

    /**
     * Limited risk if 2 triggers.
     */
    public static final int BORDER = 2;

    /**
     * Danger for young men if 3 triggers.
     */
    public static final int DANGER_M_J = 3;

    /**
     * Danger for Young man if 4 triggers.
     */
    public static final int DANGER_F_J = 4;

    /**
     * Danger if 6 triggers.
     */
    public static final int DANGER = 6;

    /**
     * Early onset for young men if 5 triggers.
     */
    public static final int EARLY_M_J = 5;

    /**
     * Early onset for young men if 7 triggers.
     */
    public static final int EARLY_F_J = 7;

    /**
     * Early onset if 8 triggers.
     */
    public static final int EARLY = 8;
}
