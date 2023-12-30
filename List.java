
// the Node class
class Node<type>
{
	private type data;
	private Node<type> link;

	// constructor
	public Node()
	{
		this.data = null;
		this.link = null;
	}

	// accessor and mutator for the data component
	public type getData()
	{
		return this.data;
	}

	public void setData(type data)
	{
		this.data = data;
	}

	// accessor and mutator for the link component
	public Node<type> getLink()
	{
		return this.link;
	}

	public void setLink(Node<type> link)
	{
		this.link = link;
	}
}

// the List class
public class List<type>
{
	public static final int MAX_SIZE = 50;

	private Node<type> head;
	private Node<type> tail;
	private Node<type> curr;
	private int num_items;

	// constructor
	// remember that an empty list has a "size" of 0 and its "position" is at -1
	public List()
	{
		curr = null;
		head = null; 
		tail = null;
		num_items = 0;
	}

	// copy constructor
	// clones the list l and sets the last element as the current
	public List(List<type> l)
	{
		curr = null; 
		head = null; 
		tail = null;
		num_items = 0;
		Node<type> temp = l.head;
		while(temp!=null)
		{
			InsertAfter(temp.getData());
			temp = temp.getLink();
		}
	}

	// navigates to the beginning of the list
	public void First()
	{
		curr = head;
	}

	// navigates to the end of the list
	// the end of the list is at the last valid item in the list
	public void Last()
	{
		curr = tail;
	}

	// navigates to the specified element (0-index)
	// this should not be possible for an empty list
	// this should not be possible for invalid positions
	public void SetPos(int pos)
	{
		if(!IsEmpty() && pos<num_items) //
		{
			Node<type> temp = head;
			for(int i = 0; i< pos; i++)
			{
				temp = temp.getLink();
			}
			curr = temp;
			if(curr == null) 
				System.out.println("curr = null");
			
		}
	}

	// navigates to the previous element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Prev()
	{
		if(!IsEmpty())
		{
			if (curr != head)
			{
				Node<type> temp = head;
				while(temp!=null)
				{
					if (temp.getLink() ==curr)
					{
						curr = temp;
						break;
					}
					else
					{
						temp = temp.getLink();
					}
				}
			}
		}
	}

	// navigates to the next element
	// this should not be possible for an empty list
	// there should be no wrap-around
	public void Next()
	{
		if(!IsEmpty())
		{
			if(curr!=tail)
			{
				curr = curr.getLink();
			}
		}
	}

	// returns the location of the current element (or -1)
	public int GetPos()
	{
		if(curr == null)
		{
			return -1;
		}
		else
		{
			int pos = 0;
			Node<type> temp = new Node<type>(); 
			temp.setLink(curr.getLink());
			Node<type> temp2 = head;
			while(temp2!= null)
			{
				if(temp2.getLink() == temp.getLink())
				{
					return pos;
				}
				pos++;
				temp2 = temp2.getLink();
			}
			return pos;	
		}
	}

	// returns the value of the current element (or -1)
	public type GetValue()
	{
		if(IsEmpty())
		{
			return null;
		}
		return (type) curr.getData();
	}

	// returns the size of the list
	// size does not imply capacity
	public int GetSize()
	{
		return num_items;
	}

	// inserts an item before the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertBefore(type data)
	{
		if(!IsFull())
		{
			if (head == null)
			{
				head = new Node<type>();
				head.setData(data);
				curr = head;
				tail = curr;
				num_items++;
			}
			else if(curr == head)
			{
				Node<type> temp = new Node<type>();
				temp.setData(data);
				temp.setLink(head);
				curr = temp;
				head = temp;
				num_items++;
			}
			else
			{
				Node<type> temp = new Node<type>();
				temp.setData(data);
				temp.setLink(curr);
				Node<type> temp2 = head;
				while(temp2!=null)
				{
					if(temp2.getLink() == curr)
					{
						temp2.setLink(temp);
						break;
					}
					temp2 = temp2.getLink();
				}
				curr = temp;
				
				num_items++;
			}

		}
		
	}

	// inserts an item after the current element
	// the new element becomes the current
	// this should not be possible for a full list
	public void InsertAfter(type data)
	{
		if(!IsFull())
		{
			if(head == null)
			{
				head = new Node<type>();
				head.setData(data);
				curr = head;
				tail = curr;
				num_items++;
			}
			else if(curr == tail){
				Node<type> temp = new Node<type>();
				temp.setData(data);
				curr.setLink(temp);
				curr = temp;
				tail = curr;
				num_items++;
			}
			//add other cases when curr is in the middle of the list
			else
			{
				Node<type> temp = new Node<type>();
				temp.setData(data);
				temp.setLink(curr.getLink());
				curr.setLink(temp);
				curr = temp; 
				num_items++;
			}
			
		}
	}

	// removes the current element (collapsing the list)
	// this should not be possible for an empty list. If possible,
	// following element becomes new current element.
	public void Remove()
	{
		if(!IsEmpty())
		{
			
			//edge case if 1 element
			if (num_items == 1)
			{
				head = null;
				tail = null;
				curr = null;
				num_items--;
			}

			//edge case if first element 
			else if (curr == head)
			{
				head = curr.getLink();
				curr = curr.getLink();
				num_items--;
			}

			//edge case if last
			else if (curr == tail)
			{
				Node<type> temp = head;
				while(temp!=null)
				{
					if (temp.getLink() == tail)
					{
						temp.setLink(null);
						break;
					}
					else
					{
						temp = temp.getLink();
					}
				}
				curr = temp;
				tail = temp;
				num_items--;
			}

			else
			{
				Node<type> temp = head;
				while(temp!=null)
				{
					if (temp.getLink() == curr)
					{
						temp.setLink(curr.getLink());
						break;
					}
					else
					{
						temp = temp.getLink();
					}
					
				}
				
				curr = temp.getLink();
				num_items--;
			}
			
		}
	}

	// replaces the value of the current element with the specified value
	// this should not be possible for an empty list
	public void Replace(type data)
	{
		if(!IsEmpty())
		{
			curr.setData(data);
		}
	}

	// returns if the list is empty
	public boolean IsEmpty()
	{
		if(num_items == 0)
		{
			return true;
		}
		return false;
		
	}

	// returns if the list is full
	public boolean IsFull()
	{
		if (num_items==MAX_SIZE)
		{
			return true;
		}
		return false;
		
	}

	// returns if two lists are equal (by value)
	public boolean Equals(List<type> l)
	{
		Node<type> temp = head;
		Node<type> temp2 = l.head;
		if(num_items != l.num_items)
		{
			return false;
		}
		while(temp!=null && temp2!=null)
		{
				if(temp.getData() != temp2.getData())
				{
					return false;
				}
				temp2 = temp2.getLink();
				temp = temp.getLink();
				
		}
		return true;
	}

	// returns the concatenation of two lists
	// l should not be modified
	// l should be concatenated to the end of *this
	// the returned list should not exceed MAX_SIZE elements
	// the last element of the new list is the current
	public List<type> Add(List<type> l)
	{
		if(this.GetSize() == 0)
		{
			return new List<type>(l);
		}
		else if(l.GetSize() == 0)
		{
			return new List<type>(this);
		}
		
		else 
		{
			List<type> result = new List<type>(this);
			if(num_items + l.num_items <= MAX_SIZE)
			{
				Node<type> temp = l.head;
				while(temp!=null)
				{
					result.InsertAfter(temp.getData());
					temp = temp.getLink();
				}
			}
			return result;
		}
	}

	// returns a string representation of the entire list (e.g., 1 2 3 4 5)
	// the string "NULL" should be returned for an empty list
	public String toString()
	{
		if(IsEmpty())
		{
			return "NULL";
		}
		String result = "";
		Node<type> temp = head;
		while(temp != null)
		{
			result += temp.getData() + " ";
			temp = temp.getLink();
		}
		return result;
	}
}
