package com.company;

import ucb.gui2.LayoutSpec;
import ucb.gui2.TopLevel;

class GUI extends TopLevel {

    final String QWERTY1 = "QWERTYUIOP";
    final String QWERTY2 = "ASDFGHJKL";
    final String QWERTY3 = "ZXCVBNM";

    GUI(String title, Game game) {
        super(title, true);
        addMenuButton("Game->New", this::newGame);
        addMenuButton("Game->Quit", this::quit);
        addMenuButton("Info->Answer", this::showAnswer);
        _game = game;
        _widget = new Widget(game);
        add(_widget,
                new LayoutSpec("height", "1",
                        "width", "REMAINDER",
                        "ileft", 5, "itop", 5, "iright", 5,
                        "ibottom", 5));
        int x;
        // first line
        x = 0;
        for (char s : QWERTY1.toCharArray()) {
            addButton("   " + String.valueOf(s) + "   ", this::typeLetter, new LayoutSpec("width", 400, "height", 600, "x", x, "y", 100));
            x += 400;
        }
        // second line
        x = 0;
        for (char s : QWERTY2.toCharArray()) {
            addButton("   " + String.valueOf(s) + "   ", this::typeLetter, new LayoutSpec("width", 400, "height", 600, "x", x, "y", 200));
            x += 400;
        }
        // third line
        x = 0;
        addButton("ENTER", this::doEnter, new LayoutSpec("width", 400, "height", 600, "x", x, "y", 300));
        x += 400;
        for (char s : QWERTY3.toCharArray()) {
            addButton("   " + String.valueOf(s) + "   ", this::typeLetter, new LayoutSpec("width", 400, "height", 600, "x", x, "y", 300));
            x += 400;
        }
        addButton("DELETE", this::doDelete, new LayoutSpec("width", 400, "height", 600, "x", x, "y", 300));
    }

    private synchronized void typeLetter(String s) {
        if (_game.inProgress()) {
            _widget.updateGuess(s.strip());
        }
    }

    private void doEnter(String s) {
        if (_game._currentGuess.length() == 5) {
            if (!Main.allowedWords.contains(_game._currentGuess.toLowerCase())) {
                msg("Not in word list");
            } else {
                _widget.checkGuess();
                if (!_game.inProgress()) {
                    if (_game._gameWon) {
                        msg("You won in %s turns!", _game._currentIndex);
                    } else {
                        msg("You lost");
                    }
                }
            }
        }
    }

    private void doDelete(String s) {
        if (_game.inProgress()) {
            _widget.deleteGuess();
        }
    }

    public void msg(String format, Object... args) {
        showMessage(String.format(format, args), "Message", "information");
    }

    private void newGame(String s) {
        String answer = Main.generateAnswer();
        Game game = new Game(answer);
        _game = game;
        _widget.updateGame(game);
    }

    private void quit(String s) {
        System.exit(0);
    }

    private void showAnswer(String s) {
        showMessage(_game._answer, "Answer", "plain");
    }

    private Widget _widget;
    private Game _game;
}
