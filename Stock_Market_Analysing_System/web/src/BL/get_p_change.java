package BL;

import java.util.ArrayList;
import java.util.List;

import BLSer.p_change;
import PO.MarketonePO;
import PO.StockOnePO;
import PO.pchangePO;

public class get_p_change implements p_change{

	@Override
	public List<pchangePO> getpchange(String code) {
		// TODO Auto-generated method stub
		List<pchangePO> result = new ArrayList<>();
		List<pchangePO> temp = new ArrayList<>();
		String str = null;
		
		switch (code.charAt(0)) {
		case '6':
			str="000001";
			break;
		case '9':
			str="000001";
			break;
		case '0':
			str="399001";
			break;
		case '2':
			str="399001";
			break;
		default:
			return null;
		}
		
		getMarketInfo get1 = new getMarketInfo();
		getStockInfo get2 = new getStockInfo();
		List<MarketonePO> list1 = get1.getMarketone(str);
		List<StockOnePO> list2 = get2.getStockOne(code);
		
		int ones = 30;
		int size1 = list1.size();
		int size2 = list2.size();
		int a = size1-1;
		int b = size2-1;
		for(int i=0;i<ones;i++){
			pchangePO po = null;
			MarketonePO po1 = list1.get(a-i);
			StockOnePO po2 = list2.get(b-i);
			if(!po1.getDate().equals(po2.getDate())){
				b++;
				po = new pchangePO(po1.getDate(), po1.getP_change(), 0);
			}
			else{
				po = new pchangePO(po1.getDate(), po1.getP_change(), po2.getP_change());
			}
			temp.add(po);
		}
		
		for(int i=0;i<ones;i++){
			result.add(temp.get(ones-1-i));
		}
		return result;
	}
	
}
