package Calculator;

    import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Calculator extends JFrame implements ActionListener
{
    private JTextField displayText = new JTextField(30);
    private JButton[] button = new JButton[20];
    private String[] keys ={"7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+",
            ".", " ", " ", " "};
    private String numStr1 = "";
    private String numStr2 = "";
    private char op;
    private boolean firstInput = true;

    public Calculator()
    {
        setTitle("My Calculator");
        setSize(230, 250);
        Container pane = getContentPane();

        pane.setLayout(null);

        displayText.setSize(200, 30);
        displayText.setLocation(10, 10);
        pane.add(displayText);

        int x, y;

        x = 10;
        y = 40;
        for (int ind = 0; ind < 20; ind++)
        {
            button[ind] = new JButton(keys[ind]);
            button[ind].addActionListener(this);
            button[ind].setSize(50, 30);
            button[ind].setLocation(x, y);
            pane.add(button[ind]);
            x = x + 50;
            if((ind + 1) % 4 == 0)
            {
                x = 10;
                y = y + 30;
            }
        }
        this.addWindowListener(new WindowAdapter()
                               {
                                   public void windowClosing(WindowEvent e)
                                   {
                                       System.exit(0);
                                   }
                               }
                               );

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e)
    {
        String resultStr;
        String str = String.valueOf(e.getActionCommand());

        char ch = str.charAt(0);
        switch (ch)
        { case '0': case '1': case '2':
            case '3': case '4': case '5':
                case '6': case '7': case '8':
                    case '9': case '.':if (firstInput) {
                        numStr1 = numStr1 + ch;
                displayText.setText(numStr1);
                    }
                    else {
                        numStr2 = numStr2 + ch;
                        displayText.setText(numStr2);
                        }break;
                case '+': case '-': case '*':
                case '/': op = ch;
                    firstInput = false;
                    break;
                case '=': resultStr = evaluate();
                    displayText.setText(resultStr);
                    numStr1 = resultStr;
                    numStr2 = "";
                    firstInput = false;
                    break;
                case 'C': displayText.setText("");
                    numStr1 = "";
                    numStr2 = "";
                    firstInput = true;
        }
    }
    private String evaluate()
    {
        final char beep = '\u0007';

        try
        {
            double num1 = Double.parseDouble(numStr1);
            double num2 = Double.parseDouble(numStr2);
            double result = 0;
            switch (op)
            {
                case '+': result = num1 + num2;
                break;
                case '-': result = num1 - num2;
                break;
                case '*': result = num1 * num2;
                break;
                case '/': result = num1 / num2;
            }
            if ((result == Double.POSITIVE_INFINITY) || (result == Double.NEGATIVE_INFINITY))
            {
                System.out.print(beep);
                return "E R R O R: / by zero";
            }
            else
                return String.valueOf(result);
        }
        catch(ArithmeticException e)
        {
            System.out.print(beep);
            return "E R R O R:";
        }
        catch(NumberFormatException e)
        {
            System.out.print(beep);
            if (numStr1.equals(""))
                return "E R R O R: Invalid First Number " ;
            else
                return "E R R O R: Invalid Second Number" ;
        }
        catch(Exception e)
        {
            System.out.print(beep);
            return "E R R O R";
        }
    }
}

