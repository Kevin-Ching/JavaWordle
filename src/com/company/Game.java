package com.company;

import java.util.HashMap;

class Game {

    private static final int maxGuesses = 6;

    Game(String answer) {
        _answer = answer;
        for (char c : answer.toCharArray()) {
            if (!counts.containsKey(c)) {
                counts.put(c, 1);
            } else {
                counts.put(c, counts.get(c) + 1);
            }
        }
    }

    boolean inProgress() {
        if (_gameWon || _currentIndex >= maxGuesses) {
            return false;
        } else {
            return true;
        }
    }

    void checkGuess() {
        HashMap<Character, Integer> countsCopy = (HashMap<Character, Integer>) counts.clone();
        boolean[] checked = new boolean[5];
        for (int i = 0; i < 5; i++) {
            char currentLetter = _currentGuess.charAt(i);
            if (currentLetter == _answer.charAt(i)) {
                checked[i] = true;
                countsCopy.put(currentLetter, countsCopy.get(currentLetter) - 1);
            }
            _colors[_currentIndex][i] = LetterColor.GREEN;
        }
        for (int i = 0; i < 5; i++) {
            if (!checked[i]) {
                char currentLetter = _currentGuess.charAt(i);
                if (countsCopy.get(currentLetter) != null && countsCopy.get(currentLetter) > 0) {
                    countsCopy.put(currentLetter, countsCopy.get(currentLetter) - 1);
                    _colors[_currentIndex][i] = LetterColor.YELLOW;
                } else {
                    _colors[_currentIndex][i] = LetterColor.GRAY;
                }
            }
        }

        if (_currentGuess.equals(_answer)) {
            _gameWon = true;
        }
        _guesses[_currentIndex++] = _currentGuess;
        _currentGuess = "";
    }

    String _answer;
    boolean _gameWon = false;
    int _currentIndex = 0;
    String _currentGuess = "";
    String[] _guesses = new String[6];

    HashMap<Character, Integer> counts = new HashMap<>();
    LetterColor[][] _colors = new LetterColor[6][5];
}
