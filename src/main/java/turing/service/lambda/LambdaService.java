package turing.service.lambda;

import turing.avltree.AVLTree;
import turing.band.Band;
import turing.constant.Constants;
import turing.constant.Direction;
import turing.head.Head;
import turing.transition.Transition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LambdaService {

    public static Lambda<Integer> getCountOfStatesLambda() {
        return new Lambda<>() {

            private int intValue;

            @Override
            public boolean conditionCheck(String value) {
                intValue = Integer.parseInt(value);
                return intValue >= 0;
            }

            @Override
            public Integer getValue() {
                return intValue;
            }

        };
    }

    public static Lambda<Integer> getStartStateNumLambda(int countOfStates) {
        return new Lambda<>() {

            private int intValue;

            @Override
            public boolean conditionCheck(String value) {
                intValue = Integer.parseInt(value);
                return intValue >= 1 || intValue <= countOfStates;
            }

            @Override
            public Integer getValue() {
                return intValue;
            }

        };
    }

    public static Lambda<String> getTransitionLambda(Head headForLambda, AVLTree<Character> alphabet) {
        return new Lambda<>() {

            private String stringValue;
            private Head head;
            private Matcher matcher;
            private int stateNumLeft;
            private int stateNumRight;

            @Override
            public boolean conditionCheck(String value) {
                stringValue = value;
                head = headForLambda;
                if (stringValue.equals("end")) {
                    return true;
                }
                matcher = Constants.PATTERN.matcher(stringValue);
                matcher.matches();
                stateNumLeft = Integer.parseInt(matcher.group(2));
                stateNumRight = Integer.parseInt(matcher.group(7));

                return stateNumLeft <= head.getStateCount()
                        && stateNumLeft > 0
                        && stateNumRight <= head.getStateCount()
                        && stateNumRight > 0
                        && alphabet.contains(matcher.group(3).charAt(0))
                        && alphabet.contains(matcher.group(8).charAt(0));
            }

            @Override
            public String getValue() {
                if (!stringValue.equals("end")) {
                    head.addTransitionRule(
                            stateNumLeft,
                            matcher.group(3).charAt(0),
                            stateNumRight,
                            matcher.group(8).charAt(0),
                            Direction.getDirectionByChar(matcher.group(9).charAt(0))
                    );
                }
                return stringValue;
            }

        };
    }

    public static Lambda<Band> getBandTransition(AVLTree<Character> alphabet) {
        return new Lambda<>() {

            private String stringValue;

            @Override
            public boolean conditionCheck(String value) {
                stringValue = value;
                return true;
            }

            @Override
            public Band getValue() {
                return new Band(alphabet, stringValue);
            }

        };
    }

}
