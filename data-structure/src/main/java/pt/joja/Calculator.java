package pt.joja;

public class Calculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.calculate("-13-9/3+4"));
    }

    JojoStack<Integer> oprands = new JojoStack<>();

    JojoStack<Character> operators = new JojoStack<>();

    public Integer calculate(String expression) {
        char[] expressionChrs = expression.toCharArray();
        int currOprand = 0;
        for (int i = 0; i < expressionChrs.length; i++) {
            char currChar = expressionChrs[i];
            switch (currChar) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    currOprand = currOprand * 10 + (currChar - '0');
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    oprands.push(currOprand);
                    currOprand = 0;
                    // 结算左侧同优先级和高优先级的运算
                    while (!operators.isEmpty() && priority(currChar) <= priority(operators.peek())) {
                        char prevOperator = operators.pop();
                        int oprandR = oprands.pop();
                        int oprandL = oprands.pop();
                        oprands.push(calculate(oprandL, oprandR, prevOperator));
                    }
                    operators.push(currChar);
                default:
                    break;
            }
        }
        oprands.push(currOprand);

        System.out.println(oprands);
        System.out.println(operators);

        while (!operators.isEmpty()) {
            char operator = operators.pop();
            int oprandR = oprands.pop();
            int oprandL = oprands.pop();
            oprands.push(calculate(oprandL, oprandR, operator));
        }
        return oprands.pop();
    }

    private int priority(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private int calculate(int oprandL, int oprandR, char operator) {
        switch (operator) {
            case '+':
                return oprandL + oprandR;
            case '-':
                return oprandL - oprandR;
            case '*':
                return oprandL * oprandR;
            case '/':
                return oprandL / oprandR;
            default:
                throw new IllegalArgumentException("Operator is not valid: " + operator);
        }
    }
}
