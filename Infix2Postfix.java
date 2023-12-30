import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Infix2Postfix<type> {
    
    public static void main(String [] args)
    {
        //this function reads the file and calls upon the toString function in order to print each line upon reading it
        readFile();
    }

    public static void readFile()
    {
        Queue<Character> infix = new Queue<>();
        int size;
        try
        {
            BufferedReader br = new BufferedReader( new InputStreamReader(System.in));

            String line;
            while((line = br.readLine()) != null)
            {
                //convert the line of the text into queue
                size = line.length();
                for(int i = 0; i< size; i++)
                {
                    infix.Enqueue(line.charAt(i));
                }
                System.out.println(toString(infix));
                for(int i = 0; i< size; i++)
                {
                    infix.Dequeue();
                }
            }
            br.close();
        }catch(Exception e) {}
    }

    public static String infixToPostfix(Queue<Character> infix)
    {
        Stack<Character> operators = new Stack<>();
        Queue<Character> postfix = new Queue<>();
        char op;
        while(!infix.IsEmpty())
        {
            char token = infix.Dequeue();
            if(!isOperator(token))
            {
                postfix.Enqueue(token);
            }
            else if( token == ')')
            {
                op = operators.Pop();
                while(op != '(')
                {
                    postfix.Enqueue(op);
                    op = operators.Pop();
                }
            }
            else
            {
                while(!operators.IsEmpty() && stack_priority(operators.Peek()) >= infix_priority(token))
                {
                    op = operators.Pop();
                    postfix.Enqueue(op);

                    if(!operators.IsEmpty() )
                    {
                        op = operators.Peek();
                    }
                }
                operators.Push(token);
            }

        }
        while(!operators.IsEmpty())
        {
            op = operators.Pop();
            postfix.Enqueue(op);
        }

        String postfix_string = queueToString(postfix);
        return postfix_string;
    }


    public static double Evaluate(String postfix)
    {
        Stack<Double> stack = new Stack<Double>();
        for(int i = 0; i<postfix.length(); i++)
        {
            if(!isOperator(postfix.charAt(i)))
            {
                stack.Push(Double.parseDouble(String.valueOf(postfix.charAt(i))));
            }
            else
            {
                double a = (double) stack.Pop();
                double b = (double) stack.Pop();
                stack.Push(eval(a,b, postfix.charAt(i) ));
            }
        }
        return stack.Pop();
    }


    private static boolean isOperator(char opp)
    {
        char operators [] = {'+', '-', '/', '*', '^', '(', ')'};
        for(char operator: operators)
        {
            if(opp == operator)
            {
                return true;
            }
        }
        return false;
    }

    private static double eval(double a, double b, char operator)
    {
        //if addition
        if (operator == '+')
        {
            return (double) b+a;
        }
        //if subtraction
        else if (operator == '-')
        {
            return (double) b-a;
        }
        //if division
        else if (operator == '/')
        {
            return (double) b/a;
        }
        //if multiplication
        else if (operator == '*')
        {
            return (double) b*a;
        }
        //if power
        else
        {
            return Math.pow(b,a);
        }
        
    }

    private static int infix_priority(char token)
    {
        if(token == '(')
        {
            return 4;
        }
        else if(token == '^')
        {
            return 3;
        }
        else if((token == '*') || (token == '/'))
        {
            return 2;
        }
        else if((token == '+') || (token == '-'))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    private static int stack_priority(char operator)
    {
        if((operator == '^') || (operator == '*') || (operator == '/'))
        {
            return 2;
        }
        else if ((operator == '+') || (operator == '-'))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    private static String queueToString(Queue<Character> queue)
    {
        return queue.toString();
    }

    public static String toString(Queue<Character> input)
    {
        String result = "";
        result += input.toString() + "\n";
        String post = infixToPostfix(input);
        result+= post + "\n";
        result += Evaluate(post) + "\n";
        return result;
    }
}
