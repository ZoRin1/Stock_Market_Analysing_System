package BL;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import BLSer.IndustryBlSer;
import DataImp.GetIndustryDataImp;
import PO.StockListPO;
import PO.industryPO;

public class getIndustry implements IndustryBlSer{
	private GetIndustryDataImp get;
	
	public getIndustry() {
		super();
		// TODO Auto-generated constructor stub
		get = new GetIndustryDataImp();
	}


	@Override
	public List<industryPO> getIndustryList(String market) {
		// TODO Auto-generated method stub
		List<industryPO> result = new ArrayList<>();
		List<industryPO> temp = new ArrayList<>();
		double[] t1 = new double[10];
		int[] t2 = {0,1,2,3,4,5,6,7,8,9};
		List<StockListPO> list1 = get.getIndustryData(market, "金属材料");
		temp.add(getIndustryPO(list1));
		t1[0] = getIndustryPO(list1).getP_change();
		List<StockListPO> list2 = get.getIndustryData(market, "机械仪表");
		temp.add(getIndustryPO(list2));
		t1[1] = getIndustryPO(list2).getP_change();
		List<StockListPO> list3 = get.getIndustryData(market, "电子");
		temp.add(getIndustryPO(list3));
		t1[2] = getIndustryPO(list3).getP_change();
		List<StockListPO> list4 = get.getIndustryData(market, "综合类");
		temp.add(getIndustryPO(list4));
		t1[3] = getIndustryPO(list4).getP_change();
		List<StockListPO> list5 = get.getIndustryData(market, "文化传播");
		temp.add(getIndustryPO(list5));
		t1[4] = getIndustryPO(list5).getP_change();
		List<StockListPO> list6 = get.getIndustryData(market, "生物医药");
		temp.add(getIndustryPO(list6));
		t1[5] = getIndustryPO(list6).getP_change();
		List<StockListPO> list7 = get.getIndustryData(market, "石油化工");
		temp.add(getIndustryPO(list7));
		t1[6] = getIndustryPO(list7).getP_change();
		List<StockListPO> list8 = get.getIndustryData(market, "纺织服装");
		temp.add(getIndustryPO(list8));
		t1[7] = getIndustryPO(list8).getP_change();
		List<StockListPO> list9 = get.getIndustryData(market, "造纸印刷");
		temp.add(getIndustryPO(list9));
		t1[8] = getIndustryPO(list9).getP_change();
		List<StockListPO> list10 = get.getIndustryData(market, "食品饮料");
		temp.add(getIndustryPO(list10));
		t1[9] = getIndustryPO(list10).getP_change();
		
		for(int i=0;i<10;i++){
			for(int m=i;m<10;m++){
				if(t1[i]<t1[m]){
					double tem = t1[i];
					t1[i] = t1[m];
					t1[m] = tem;
					int tem1 = t2[i];
					t2[i] = t2[m];
					t2[m] = tem1;
				}
			}
		}
		for(int i=0;i<10;i++){
			result.add(temp.get(t2[i]));
		}
		return result;
	}
	
	private industryPO getIndustryPO(List<StockListPO> list){
		int size = list.size();
		if(size == 0){
			return null;
		}
		int innum = 0;
		int eqnum = 0;
		int ounum = 0;
		double volume = 0;
		double p_change = 0;
		double change = -1000;
		int m = 0;
		for(int i=0;i<size;i++){
			StockListPO po1 = list.get(i);
			double temp = (po1.getPrice()-po1.getPre_close())*100/po1.getPre_close();
			if(temp == 0){
				eqnum += 1;
			}
			else if(temp < 0){
				ounum += 1;
			}
			else{
				innum += 1;
			}
			if(temp > change){
				change = temp;
				m = i;
			}
			volume += po1.getVolume();
			p_change += temp;
		}
		p_change = p_change/size;
		String s = new DecimalFormat("###.####").format(p_change);
		p_change=Double.parseDouble(s);
		industryPO po = new industryPO(list.get(0).getIndustry(), innum, eqnum, ounum, 
				list.get(m).getName(), volume, p_change);
		return po;
	}
}
