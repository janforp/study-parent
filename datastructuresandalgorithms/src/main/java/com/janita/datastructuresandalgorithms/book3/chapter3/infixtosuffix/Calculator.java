package com.janita.datastructuresandalgorithms.book3.chapter3.infixtosuffix;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author 木已成舟
 * @date 2020/3/13
 */
public class Calculator {

    /**
     * 计算表达式的值
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Queue<String> q = toSuffixExpression(s);
        return solve(q);
    }

    /**
     * 将中缀表达式转化成后缀表达式
     * a + b * c + (d * e + f) * g
     * 对应的后缀表达式为 a b c * + d e * f + g * +
     *
     * @param s
     * @return
     */
    private Queue<String> toSuffixExpression(String s) {
        //存操作符或者括号
        Stack<Character> operateAndBracketStack = new Stack<>();
        //后缀表达式
        Queue<String> resultSuffixQueue = new LinkedList<>();
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (isDigital(c)) {// 如果是数字，就入队列
                // 入队的时候要判断后面是否还有剩余的数字，要把整个数字入队列，而不是一个数字字符
                int p = index;
                while (p < s.length() && isDigital(s.charAt(p))) {
                    p++;
                }
                resultSuffixQueue.add(s.substring(index, p));
                index = p;
                //唯独这分支continue
                continue;
            } else if (c == '(') {// 如果是左括号，就入栈
                operateAndBracketStack.push(c);
            } else if (c == ')') {// 如果是右括号，就弹出栈中的元素，直到遇到左括号为止。左右括号均不入队列
                while ('(' != operateAndBracketStack.peek()) {
                    resultSuffixQueue.add(operateAndBracketStack.pop() + "");
                }
                // 弹出左括号
                operateAndBracketStack.pop();
            } else if (isOperator(c)) { // 如果是运算符，分下面的情况讨论
                if (operateAndBracketStack.isEmpty()) {// 如果符号栈为空，就直接压入栈
                    operateAndBracketStack.push(c);
                } else if ('(' == operateAndBracketStack.peek()) { // 如果符号栈的栈顶是左括号，则压入栈中
                    operateAndBracketStack.push(c);
                } else if (priority(c) > priority(operateAndBracketStack.peek())) {// 如果当前元素的优先级比符号栈的栈顶元素优先级高，则压入栈中
                    operateAndBracketStack.push(c);
                } else if (priority(c) <= priority(operateAndBracketStack.peek())) {// 如果此时遍历的运算符的优先级小于等于此时符号栈栈顶的运算符的优先级，
                    // 则将符号栈的栈顶元素弹出并且放到队列中，并且将正在遍历的符号压入符号栈
                    //则将符号栈的栈顶元素弹出并且放到队列中
                    resultSuffixQueue.add(operateAndBracketStack.pop() + "");
                    //并且将正在遍历的符号压入符号栈
                    operateAndBracketStack.push(c);
                }
            }
            index++;
        }
        // 遍历完后，将栈中元素全部弹出到队列中
        while (!operateAndBracketStack.isEmpty()) {
            resultSuffixQueue.add(operateAndBracketStack.pop() + "");
        }
        return resultSuffixQueue;
    }

    /**
     * 后缀表达式求值
     *
     * @param queue
     * @return
     */
    public int solve(Queue<String> queue) {
        Stack<Integer> numberStack = new Stack<>();
        while (!queue.isEmpty()) {
            // 从队列中出队
            String s = queue.remove();
            // 如果是数字，就压入栈中
            if (isDigital(s.charAt(0))) {
                numberStack.push(Integer.parseInt(s));
                // 如果是运算符，就从栈中弹出两个元素
            } else if (isOperator(s.charAt(0))) {
                char c = s.charAt(0);
                int val1 = numberStack.pop();
                int val2 = numberStack.pop();
                switch (c) {
                    case '+': {
                        numberStack.push(val2 + val1);
                        break;
                    }
                    case '-': {
                        numberStack.push(val2 - val1);
                        break;
                    }
                    case '*': {
                        numberStack.push(val2 * val1);
                        break;
                    }
                    case '/': {
                        numberStack.push(val2 / val1);
                        break;
                    }
                }
            }
        }

        return numberStack.pop();
    }

    //判断是符号
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    //判断是数字
    private boolean isDigital(char c) {
        return c >= '0' && c <= '9';
    }

    //运算符的优先级
    private int priority(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                throw new RuntimeException("Illegal operator:" + c);
        }
    }

    @Test
    public void test() {
        //1 + 6 + 26 * 7
        String expressionStr = "1 + 2 * 3 + (4 * 5 + 6) * 7";
        Queue<String> expression = toSuffixExpression(expressionStr);
        for (String s : expression) {
            System.out.println(s);
        }

        int calculate = calculate(expressionStr);
        System.out.println(calculate);
    }
}
