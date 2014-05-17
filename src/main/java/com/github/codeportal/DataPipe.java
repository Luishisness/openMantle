public interface DataPipe{

	 // ensure user data is valid
	public Query validateData(float L, float  U, String state, Index.Attrs [] wt);
	// pass data to OMParser feed Data function 
	public void tunnelData(Query Q);

}
