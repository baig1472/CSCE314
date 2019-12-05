package Data;

public class Pair <T> {
	private T m_left;
	private T m_right;
	
	Pair(T l, T r) 
	{
		m_left = l;
		m_right = r;
	}
	public T left() { return m_left; }
	public T right() {return m_right; }
	public void setLeft(T val) {m_left = val;}
	public void setRight(T val) {m_right = val;}
	public void print() {
		System.out.println("printing pair:" + m_left + " , " + m_right);
	}

}
