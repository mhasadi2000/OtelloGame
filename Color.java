package com.company;

/**to use color in the game and show in the console.**/
public enum Color {

    RESET("\033[0m"),         //RESET
    // Regular Colors. Normal color, no bold, background color etc.
    GREEN("\033[0;32m"),      //GREEN
    MAGENTA("\033[0;35m"),   // MAGENTA
    CYAN("\033[0;36m"),      // CYAN
    RED_BOLD("\033[1;31m"),   // RED
    BLUE_BOLD("\033[1;34m");  // BLUE

    private final String code;

    Color(String code) {
        this.code = code;
    }

    public String toString() {
        return code;
    }

}
