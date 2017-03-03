package BL;

import dataApi.RaiseStock;
import blService.RaiseBlSer;

public class Raise implements RaiseBlSer{
	RaiseStock raise=null;
	public Raise (){
		raise=new RaiseStock();
	}
	public void raise(String code) {
		// TODO Auto-generated method stub
		raise.raise(code);
	}

	/* (non-Javadoc)
	 * @see blService.RaiseBlSer#checkExise(java.lang.String)
	 */
	@Override
	public boolean checkExise(String code) {
		// TODO Auto-generated method stub
		return raise.checkExist(code);
	}

	/* (non-Javadoc)
	 * @see blService.RaiseBlSer#delete(java.lang.String)
	 */
	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub
		raise.delete(code);
	}
	
	

}
