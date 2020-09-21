package pt.joja;

import java.util.Arrays;
import java.util.Iterator;

public class RPolishRotationCalculator {
    // 方便起见空格分隔
    static String targetExpression = "( 2 + 3 ) * 4 - 6 * ( ( 7 + 8 ) / 5 - 9 )";

    public static void main(String[] args) {

        RPolishRotationCalculator calculator = new RPolishRotationCalculator();

        String rPolishRotation = calculator.toRPolishRotation(targetExpression);
        System.out.println(rPolishRotation);
        String result = calculator.calculate(rPolishRotation);
        System.out.println(result);
    }

    public int priority(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    public String toRPolishRotation(String expression) {
        Iterator<String> eles = Arrays.asList(targetExpression.split(" ")).iterator();

        if (!eles.hasNext()) {
            return "";
        }

        JojoStack<String> oprands = new JojoStack<>();
        JojoStack<String> operators = new JojoStack<>();

        String ele = null;
        while (true) {
            if (ele == null) {
                if (eles.hasNext()) {
                    // 读下一个元素
                    ele = eles.next();
                } else {
                    break;
                }
            }

            // 操作数直接入栈
            if (ele.matches("[0-9]+")) {
                oprands.push(ele);
                ele = null;
                continue;
            }

            // 左括号直接入栈
            if ("(".equals(ele)) {
                operators.push(ele);
                ele = null;
                continue;
            }

            // 操作数栈为空时直接入栈
            if (operators.isEmpty()) {
                operators.push(ele);
                ele = null;
                continue;
            }

            // 右括号
            if (")".equals(ele)) {
                // 观察操作数栈顶是不是左括号
                String operator = operators.pop();
                if (!operator.equals("(")) {
                    // 不是左括号的话进行一次结算，不读取，循环继续
                    String opRight = oprands.pop();
                    String opLeft = oprands.pop();
                    oprands.push(opLeft + " " + opRight + " " + operator);
                } else {
                    // 是左括号的话右括号结算结束
                    ele = null;
                }
                continue;
            }

            // 普通操作符
            if (priority(ele) <= priority(operators.peek())) {
                // 优先级小于或平级当前栈顶操作数时进行一次结算，不读取，循环继续
                String opRight = oprands.pop();
                String opLeft = oprands.pop();
                String operator = operators.pop();
                oprands.push(opLeft + " " + opRight + " " + operator);
            } else {
                // 优先级高于当前栈顶操作数时直接入栈
                operators.push(ele);
                ele = null;
                continue;
            }
        }

        // for (String ele : eles) {
        // ele = ele.intern();
        // if (ele.matches("[0-9]+")) {
        // oprands.push(ele);
        // } else {
        // if ("(".equals(ele)) {
        // operators.push(ele);
        // continue;
        // }
        // if (operators.isEmpty()) {
        // operators.push(ele);
        // continue;
        // }
        // if (")".equals(ele)) {
        // String operator = operators.pop();
        // while (!operator.equals("(")) {
        // String opRight = oprands.pop();
        // String opLeft = oprands.pop();
        // oprands.push(opLeft + " " + opRight + " " + operator);
        // operator = operators.pop();
        // }
        // continue;
        // }
        // while (!operators.isEmpty() && priority(ele) <= priority(operators.peek())) {
        // String opRight = oprands.pop();
        // String opLeft = oprands.pop();
        // String operator = operators.pop();
        // oprands.push(opLeft + " " + opRight + " " + operator);
        // }
        // operators.push(ele);
        // }
        // }

        while (!operators.isEmpty()) {
            String opRight = oprands.pop();
            String opLeft = oprands.pop();
            String operator = operators.pop();
            oprands.push(opLeft + " " + opRight + " " + operator);
        }

        return oprands.pop();
    }

    // 计算逆波兰表达式
    // 注意后出栈的是左操作数即可
    public String calculate(String expression) {
        String[] eles = expression.split(" ");
        JojoStack<String> stack = new JojoStack<>();
        for (String ele : eles) {
            ele = ele.intern();
            switch (ele) {
                case "+":
                    stack.push(add(stack.pop(), stack.pop()));
                    break;
                case "-":
                    stack.push(sub(stack.pop(), stack.pop()));
                    break;
                case "*":
                    stack.push(mul(stack.pop(), stack.pop()));
                    break;
                case "/":
                    stack.push(div(stack.pop(), stack.pop()));
                    break;
                default:
                    stack.push(ele);
                    break;
            }
        }
        return stack.pop();
    }

    private String add(String opRight, String opLeft) {
        return "" + (Integer.parseInt(opLeft) + Integer.parseInt(opRight));
    }

    private String sub(String opRight, String opLeft) {
        return "" + (Integer.parseInt(opLeft) - Integer.parseInt(opRight));
    }

    private String mul(String opRight, String opLeft) {
        return "" + (Integer.parseInt(opLeft) * Integer.parseInt(opRight));
    }

    private String div(String opRight, String opLeft) {
        return "" + (Integer.parseInt(opLeft) / Integer.parseInt(opRight));
    }

}