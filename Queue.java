
public class Queue<type>
{
    //instance variables
    private List<type> queue;

    public Queue()
    {
        queue = new List<type>();
    }

    public Queue(Queue<type> q)
    {
        queue = new List<type>(q.queue);
    }



    public void Enqueue(type data)
    {
        queue.Last();
        queue.InsertAfter(data);
    }

    public type Dequeue()
    {
        queue.First();
        type value = queue.GetValue();
        queue.Remove();
        return value;

    }

    public type Peek()
    {
        queue.First();
        type value = queue.GetValue();
        return value;
    }

    public int Size()
    {
        return queue.GetSize();
    }

    public boolean IsEmpty()
    {
        return queue.IsEmpty();
    }

    public boolean IsFull()
    {
        return queue.IsFull();
    }

    public boolean Equals(Queue<type> q)
    {
        return this.queue.Equals(q.queue);
    }

    public Queue<type> Add(Queue<type> q)
    {
        Queue<type> result = new Queue<type>(this);
        result.queue = queue.Add(q.queue);
        return result;

    }

    public String toString()
    {
        return queue.toString();
    }
}
