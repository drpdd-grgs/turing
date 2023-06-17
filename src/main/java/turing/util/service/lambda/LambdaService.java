package turing.util.service.lambda;

import turing.util.avltree.AVLTree;
import turing.machine.band.Band;
import turing.util.constant.Constants;
import turing.util.constant.Direction;
import turing.machine.head.Head;

import java.util.regex.Matcher;

// TODO: Remove. Might be used for dependency injection usage of machine.
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
                    head.addTransition(
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
