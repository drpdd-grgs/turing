package turing;

import turing.avltree.AVLTree;
import turing.band.Band;
import turing.band.Cell;
import turing.constants.Constants;
import turing.constants.Direction;
import turing.head.Head;

public class App {

    public static void main(String[] args) {
        Character[] arr = {'1', 'x', '=', 'a', '*'};
        AVLTree<Character> alphabet = new AVLTree<>(arr);

        alphabet.add(Constants.BLANK);

        Band band = new Band(alphabet, "*111x11=*");
        Head head = new Head(band, 10, 1);

        head.addTransitionRule(1, '1', 1, '1', Direction.RIGHT);
        head.addTransitionRule(1, 'x', 2, 'x', Direction.RIGHT);
        head.addTransitionRule(1, '*', 1, '*', Direction.RIGHT);

        head.addTransitionRule(2, '1', 3, 'a', Direction.RIGHT);

        head.addTransitionRule(3, '1', 3, '1', Direction.LEFT);
        head.addTransitionRule(3, 'x', 4, 'x', Direction.LEFT);
        head.addTransitionRule(3, '=', 3, '=', Direction.LEFT);
        head.addTransitionRule(3, 'a', 3, 'a', Direction.LEFT);

        head.addTransitionRule(4, '1', 5, 'a', Direction.RIGHT);
        head.addTransitionRule(4, 'a', 4, 'a', Direction.LEFT);
        head.addTransitionRule(4, '*', 7, '*', Direction.RIGHT);

        head.addTransitionRule(5, '1', 5, '1', Direction.RIGHT);
        head.addTransitionRule(5, 'x', 5, 'x', Direction.RIGHT);
        head.addTransitionRule(5, '=', 5, '=', Direction.RIGHT);
        head.addTransitionRule(5, 'a', 5, 'a', Direction.RIGHT);
        head.addTransitionRule(5, '*', 6, '1', Direction.RIGHT);

        head.addTransitionRule(6, Constants.BLANK, 3, '*', Direction.LEFT);

        head.addTransitionRule(7, 'x', 8, 'x', Direction.RIGHT);
        head.addTransitionRule(7, 'a', 7, '1', Direction.RIGHT);

        head.addTransitionRule(8, '1', 3, 'a', Direction.RIGHT);
        head.addTransitionRule(8, '=', 9, '=', Direction.LEFT);
        head.addTransitionRule(8, 'a', 8, 'a', Direction.RIGHT);

        head.addTransitionRule(9, 'x', 10, 'x', Direction.NONE);
        head.addTransitionRule(9, 'a', 9, '1', Direction.LEFT);

        head.start();
        for (Cell cell : band) {
            System.out.print(cell.getSymbol());
        }
    }

}
