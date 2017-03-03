package BL;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BLSer.LoginBlSer;
import DataImp.GetStockInfoDataImp;
import DataImp.LoginDataImp;
import PO.IndexPO;
import PO.RaisePO;
import PO.StockListPO;
import PO.StockOnePO;

public class login implements LoginBlSer{
	LoginDataImp login;
	
	
	public login() {
		super();
		// TODO Auto-generated constructor stub
		login = new LoginDataImp();
	}

	@Override
	public boolean signup(String cid, String password) {
		// TODO Auto-generated method stub
		int size = password.length();
		if(size>12||size<6){
			return false;
		}
		if(cid.equals(""))
			return false;
		boolean result = login.signup(cid, password);
		return result;
	}

	@Override
	public boolean login(String cid, String password) {
		// TODO Auto-generated method stub
		int size = password.length();
		if(size>12||size<6){
			return false;
		}
		if(cid.equals(""))
			return false;
		boolean result = login.login(cid, password);
		return result;
	}

	@Override
	public void raise(String cid, String sid, String date, double money) {
		// TODO Auto-generated method stub
		GetStockInfoDataImp get = new GetStockInfoDataImp();
		StockListPO po = get.getNewStockList(sid);
		login.raise(cid, sid, date, money, po.getPrice());
	}

	@Override
	public List<RaisePO> getOwnStock(String cid) {
		// TODO Auto-generated method stub
		List<RaisePO> list = new ArrayList<>();
		List<String> list1 = login.getOwnStock(cid);
		int size = list1.size();
		for(int i=0;i<size;i++){
			String sid = list1.get(i);
			GetStockInfoDataImp get = new GetStockInfoDataImp();
			StockListPO po = get.getNewStockList(sid);
			String[] tem = login.getOwnSet(cid, sid);
			double change = (po.getPrice()-po.getPre_close())*100/po.getPre_close();
			String s = new DecimalFormat("###.####").format(change);
			change=Double.parseDouble(s);
			double now = Double.parseDouble(tem[1])*(po.getPrice()/Double.parseDouble(tem[2]));
			String s1 = new DecimalFormat("############.##").format(now);
			now = Double.parseDouble(s1);
			String industry = po.getIndustry();
			double open = po.getOpen();
			double preclose = po.getPre_close();
			RaisePO po2 = new RaisePO(sid, po.getName(), industry, open, preclose, Double.parseDouble(tem[2]),
					change, now, Double.parseDouble(tem[1]), tem[0]);
			list.add(po2);
		}
		return list;
	}

	@Override
	public double[] getIndustry(String cid) {
		// TODO Auto-generated method stub
		GetStockInfoDataImp get = new GetStockInfoDataImp();
		List<String> list = login.getOwnStock(cid);
		int size = list.size();
		double[] temp = new double[10];
		for(int i=0;i<size;i++){
			String sid = list.get(i);
			StockListPO po = get.getNewStockList(sid);
			String[] tem = login.getOwnSet(cid, sid);
			
			double now = Double.parseDouble(tem[1])*(po.getPrice()/Double.parseDouble(tem[2]));
			String s1 = new DecimalFormat("############.##").format(now);
			now = Double.parseDouble(s1);
			
			switch (po.getIndustry()) {
			case "金属材料":
				temp[0]+=now;
				break;
			case "机械仪表":
				temp[1]+=now;
				break;
			case "电子":
				temp[2]+=now;
				break;
			case "综合类":
				temp[3]+=now;
				break;
			case "文化传播":
				temp[4]+=now;
				break;
			case "生物医药":
				temp[5]+=now;
				break;
			case "石油化工":
				temp[6]+=now;
				break;
			case "纺织服装":
				temp[7]+=now;
				break;
			case "造纸印刷":
				temp[8]+=now;
				break;
			case "食品饮料":
				temp[9]+=now;
				break;
			default:
				break;
			}
		}
		return temp;
	}

	@Override
	public List<IndexPO> getAll(String cid) {
		// TODO Auto-generated method stub
		List<IndexPO> result = new ArrayList<>();
		int Size = 0;
		double[] a = new double[7];
		String[] b = new String[7];
		
		GetStockInfoDataImp get = new GetStockInfoDataImp();
		List<String> list = login.getOwnStock(cid);
		int size = list.size();
		
		Date time = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String end = dateFormat.format(time);
		
		for(int i=0;i<size;i++){
			List<IndexPO> list1 = this.getOne(cid, list.get(i));
			int size1 = list1.size();
			if(Size<size1){
				Size = size1;
			}
			for(int m=0;m<size1;m++){
				a[7-size1+m]+=list1.get(m).getMoney();
				b[7-size1+m]=list1.get(m).getDate();
			}		
		}
		for(int i=0;i<Size;i++){
			IndexPO po = new IndexPO(a[7-Size+i], b[7-Size+i]);
			result.add(po);
		}
		return result;
	}

	@Override
	public List<IndexPO> getOne(String cid, String sid) {
		// TODO Auto-generated method stub
		List<IndexPO> result = new ArrayList<>();
		GetStockInfoDataImp get = new GetStockInfoDataImp();
		String[] tem = login.getOwnSet(cid, sid);
		IndexPO po = new IndexPO(Double.parseDouble(tem[1]), tem[0]);
		result.add(po);
		
		Date time = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String end = dateFormat.format(time);
		
		StockListPO po1 = get.getNewStockList(sid);
		double now = Double.parseDouble(tem[1])*(po1.getPrice()/Double.parseDouble(tem[2]));
		String s1 = new DecimalFormat("############.##").format(now);
		now = Double.parseDouble(s1);
		IndexPO po2 = new IndexPO(now, end);
		
		List<StockOnePO> list1 = get.getStockOne(sid, tem[0], end);
		int size = list1.size();
		
		if(tem[0].equals(end)){
			return result;
		}
		else if(size==0){
			result.add(po2);
			return result;
		}
		else if(size<=6){
			for(int i=0;i<size;i++){
				if(list1.get(i).getDate().equals(tem[0]))
					continue;
				else{
					StockOnePO po3 = list1.get(i);
					double close = po3.getClose();
					double now1 = Double.parseDouble(tem[1])*(close/Double.parseDouble(tem[2]));
					String s2 = new DecimalFormat("############.##").format(now1);
					now1 = Double.parseDouble(s2);
					IndexPO po4 = new IndexPO(now1, po3.getDate());
					result.add(po4);
				}
			}
		}
		else{
			result = new ArrayList<>();
			for(int i=0;i<7;i++){
				StockOnePO po3 = list1.get(size-7+i);
				double close = po3.getClose();
				double now1 = Double.parseDouble(tem[1])*(close/Double.parseDouble(tem[2]));
				String s2 = new DecimalFormat("############.##").format(now1);
				now1 = Double.parseDouble(s2);
				IndexPO po4 = new IndexPO(now1, po3.getDate());
				result.add(po4);
			}
		}
		return result;
	}

	@Override
	public void change(String cid, String password) {
		// TODO Auto-generated method stub
		login.changePass(cid, password);
	}

	@Override
	public void delete(String cid, String sid) {
		// TODO Auto-generated method stub
		login.delete(cid, sid);
	}

	@Override
	public boolean check(String cid, String sid) {
		// TODO Auto-generated method stub
		List<String> list1 = login.getOwnStock(cid);
		int size = list1.size();
		for(int i=0;i<size;i++){
			if(sid.equals(list1.get(i)))
				return true;
		}
		return false;
	}

	@Override
	public double[] getOneStock(String cid) {
		// TODO Auto-generated method stub
		LoginDataImp get = new LoginDataImp();
		
		List<String> list = login.getOwnStock(cid);
		int size = list.size();
		double money = 0;
		for(int i=0;i<size;i++){
			String sid = list.get(i);
			String[] list1 = get.getOwnSet(cid, sid);
			money += Double.parseDouble(list1[1]);
		}
		
		double[] result = new double[2];
		
		List<IndexPO> list2 = this.getAll(cid);
		int size1 = list2.size();
		if(size1 == 0){
			result[0] = 0;
			result[1] = 0;
			return result;
		}
		IndexPO temp = list2.get(size1-1);
		
		result[0] = money;
		result[1] = temp.getMoney();
		return result;
	}

}
