package com.company;

import java.awt.*;

enum LetterColor {

    WHITE, GREEN, YELLOW, GRAY;

    static Color getColor(LetterColor color) {
        return switch (color) {
            case GREEN -> new Color(106, 170, 100, 255);
            case YELLOW -> new Color(201,180,88,255);
            case GRAY -> new Color(120,124,126,255);
            default -> throw new IllegalStateException("Unexpected value: " + color);
        };
    }
}
