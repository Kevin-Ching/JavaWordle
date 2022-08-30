package com.company;

import ucb.gui2.Pad;

import java.awt.*;

class Widget extends Pad {

    static final int SQDIM = 50;
    static final int extendedSQDIM = 55;
    static final int HSIDE = 5;
    static final int VSIDE = 6;

    private static final Color BLANK_COLOR = Color.WHITE;
    private static final Color GREEN_COLOR = Color.GREEN;
    private static final Color YELLOW_COLOR = Color.YELLOW;
    private static final Color GRAY_COLOR = Color.GRAY;
    private static final Color BLACK_COLOR = Color.BLACK;

    private static final BasicStroke LINE_STROKE = new BasicStroke(2.0f);
    private static final BasicStroke LETTER_STROKE = new BasicStroke(5.0f);

    Widget(Game game) {
        updateGame(game);
        _hdim = extendedSQDIM * HSIDE - 5;
        _vdim = extendedSQDIM * VSIDE - 5;
        setPreferredSize(_hdim, _vdim);
        setMinimumSize(_hdim, _vdim);
    }

    @Override
    public synchronized void paintComponent(Graphics2D g) {
        g.setColor(BLANK_COLOR);
        g.fillRect(0, 0, _hdim, _vdim);

        g.setColor(GRAY_COLOR);
        g.setStroke(LINE_STROKE);
        for (int y = 0; y <= _vdim; y += extendedSQDIM) {
            for (int x = 0; x <= _hdim; x += extendedSQDIM) {
                g.drawRect(x, y, SQDIM, SQDIM);
            }
        }

//        g.setColor(BLACK_COLOR);
//        g.setFont(g.getFont().deriveFont(30f));
//        g.drawString("Q", 12.5F, 36F);

//        drawLetter(g, "Q", 0, 0);
//        drawLetter(g, "W", 0, 1);
//        drawLetter(g, "E", 0, 2);

//        int x = 0;
//        for (char s : currentGuess.toCharArray()) {
//            drawLetter(g, String.valueOf(s), 0, x++);
//        }
        for (int i = 0; i < _game._currentIndex; i++) {
            String guess = _game._guesses[i];
            for (int j = 0; j < 5; j++) {
                drawLetter(g, String.valueOf(guess.charAt(j)), i, j, true);
            }
        }
        for (int i = 0; i < _game._currentGuess.length(); i++) {
            drawLetter(g, String.valueOf(_game._currentGuess.charAt(i)), _game._currentIndex, i);
        }
    }

    void drawLetter(Graphics2D g, String s, int row, int col) {
        drawLetter(g, s, row, col, false);
    }

    void drawLetter(Graphics2D g, String s, int row, int col, boolean check) {
        if (check) {
            LetterColor color = _game._colors[row][col];
//            switch (color) {
//                case GREEN -> g.setColor(GREEN_COLOR);
//                case YELLOW -> g.setColor(YELLOW_COLOR);
//                case GRAY -> g.setColor(GRAY_COLOR);
//            }
            g.setColor(LetterColor.getColor(color));
            g.fillRect(extendedSQDIM * col, extendedSQDIM * row, SQDIM, SQDIM);

            g.setColor(BLANK_COLOR);
        } else {
            g.setColor(BLACK_COLOR);
        }
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString(s, col * extendedSQDIM + 12.5F, row * extendedSQDIM + 36F);
    }

    void updateGuess(String s) {
        if (_game._currentGuess.length() < 5) {
            _game._currentGuess += s;
            repaint();
        }
    }

    void checkGuess() {
        if (_game._currentGuess.length() == 5) {
            _game.checkGuess();
            repaint();
        }
    }

    void deleteGuess() {
        if (_game._currentGuess.length() > 0) {
            _game._currentGuess = _game._currentGuess.substring(0, _game._currentGuess.length() - 1);
            repaint();
        }
    }

    void updateGame(Game game) {
        _game = game;
        repaint();
    }

    private int _hdim;
    private int _vdim;
    private Game _game;
}
