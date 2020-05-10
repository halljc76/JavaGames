
public class Piece implements Comparable<Piece> {

	private Integer _id;
	private String _piece;
	
	public Piece(Integer id) {
		_id = id;
		_piece = " ";
	}
	
	public Integer getId() {
		return _id;
	}
	
	public String getPiece() {
		return _piece;
	}
	
	public void setPiece(String piece) {
		if (piece == null) {
			throw new RuntimeException("Cannot set a piece to be null.");
		}
		_piece = piece;
	}

	@Override
	public int compareTo(Piece o) {
		return getPiece().equals(o.getPiece()) ? 0 : -1;
	}
	
	public int compareId(Piece o) {
		return (Math.abs(getId() - o.getId()) < 0.001) ? 0 : -1;
	}
	
	
}
