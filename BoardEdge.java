
public class BoardEdge {
	
	private Piece _beginning;
	private Piece _middle;
	private Piece _end;
	
	public BoardEdge(Piece begin, Piece middle, Piece end) {
		_beginning = begin;
		_middle = middle;
		_end = end;
	}
	
	public boolean isEdgeSameType() {
		
		if ((_beginning.getPiece() == " ") || 
				(_middle.getPiece() == " ") || 
				(_end.getPiece() == " ")) {
			return false;
		}
		
		if ((_beginning.compareTo(_middle) == 0) 
				&& (_middle.compareTo(_end) == 0)
				&& (_beginning.compareTo(_end)) == 0) {
			return true;
		}
		
		return false;
	}
	
	public Piece getBeginning() {
		return _beginning;
	}
	
	public Piece getMiddle() {
		return _middle;
	}
	
	public Piece getEnd() {
		return _end;
	}
	
	public boolean containsPiece(Piece piece) {
		
		if (getBeginning().compareTo(piece) == 0
				|| getMiddle().compareTo(piece) == 0
				|| getEnd().compareTo(piece) == 0) {
			return true;
		}
		
		return false;
	}
	
}
