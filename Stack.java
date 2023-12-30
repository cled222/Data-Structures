//*********************************************
// Name: Caiden Ledet
// Date: 10/27/23
// Project Name: Stack.java
// Assignment: Stack Programming Assignment
//*********************************************

public class Stack<type>
{
    private List <type> stack;
    
    public Stack()
    {
        stack = new List<type>();
    }

    public Stack(Stack<type> s)
    {
        stack = new List<type>(s.stack);
        
    }

    //push
    //inserts item on top of stack
    public void Push(type data)
    {
        stack.First();
        stack.InsertBefore(data);
    }

    //pop
    //delete an item from the top of the stack (and return it)
    public type Pop()
    {
        stack.First();
        type value = stack.GetValue();
        stack.Remove();
        return value;
    }

    //peek
    //return (but do not delete) the item on top of the stack
    public type Peek()
    {
        stack.First();
        return stack.GetValue();
    }

    //Size
    //return the size of the stack
    public int Size()
    {
        return stack.GetSize();
    }

    //IsEmpty
    //return whether the stack is empty
    public boolean IsEmpty()
    {
        return stack.IsEmpty();
    }

    //IsFull
    //return whether the stack is full
    public boolean IsFull()
    {
        return stack.IsFull();
    }

    //Equals
    //compare two stacks
    public boolean Equals(Stack<type> s)
    {
        return this.stack.Equals(s.stack);
    }

    //Add
    //concatenate two stacks
    public Stack<type> Add(Stack<type> s)
    {
        Stack<type> result = new Stack<type>(this);
        result.stack = stack.Add(s.stack);
        return result;
    }

    public String toString()
    {
        return stack.toString();
		
    }
}
