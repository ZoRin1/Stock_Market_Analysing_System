package BL;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BLSer.predictBlSer;
import DataImp.GetStockInfoDataImp;
import PO.ChangePO;
import PO.DeviationPO;
import PO.StablePO;
import PO.StockListPO;
import PO.StockOnePO;

public class predict implements predictBlSer{

	@Override
	public String predict(String code) {
		// TODO Auto-generated method stub
		DeviationPO po1 = this.getDevia(code);
		StablePO po2 = this.getPossi(code);
		String s1 = null;
		String s2 = null;
		if(po2.getInpossi()>0.5){
			s1 = "%,呈现上扬趋势";
			s2 = "增持该股";
		}
		else if(po2.getDepossi()>0.5){
			s1 = "%,呈现下降趋势";
			s2 = "抛出股票,保持观望";
		}
		else{
			s1 = "%,趋势未明显呈现上升或下降";
			s2 = "持股观望";
		}
		String s3 = new DecimalFormat("##.##").format(po2.getInpossi()*100);
		String s4 = new DecimalFormat("##.##").format(po2.getDepossi()*100);
		String s5 = new DecimalFormat("##.##").format(po1.getMin());
		String s6 = new DecimalFormat("##.##").format(po1.getMax());
		String s7 = new DecimalFormat("##.##").format(po1.getPossi()*100);
		String result = "经计算，该股上升概率约为"+s3+"%，下跌概率约为"
				+s4+"%，接下来一段时间股价最大概率处于"
				+s5+"~"+s6+"元这一范围内，处于这一范围的概率约为"
				+s7+s1+"，建议"+s2+"。";
		return result;
	}

	@Override
	public List<DeviationPO> IncreaseMore(String code) {
		// TODO Auto-generated method stub
		List<DeviationPO> result = new ArrayList<>();
		List<DeviationPO> temp = new ArrayList<>();
 		GetStockInfoDataImp get = new GetStockInfoDataImp();
		
		List<StockListPO> list = get.getStockList(code);
		int size = list.size();
		double[] t = new double[size];
		int[] a = new int[size];
		for(int i=0;i<size;i++){
			String cid = list.get(i).getCode();
			DeviationPO po = this.getDevia(cid);
			temp.add(po);
			t[i] = po.getDeviation();
			a[i] = i;
		}
		for(int i=0;i<size;i++){
			for(int m=i;m<size;m++){
				if(t[m]>t[i]){
					t[m]=t[m]+t[i];
					t[i]=t[m]-t[i];
					t[m]=t[m]-t[i];
					a[m]=a[m]+a[i];
					a[i]=a[m]-a[i];
					a[m]=a[m]-a[i];
				}
			}
		}
		for(int i=0;i<size;i++){
			result.add(temp.get(a[i]));
		}
		return result;
	}

	@Override
	public List<StablePO> Increase(String code) {
		// TODO Auto-generated method stub
		List<StablePO> result = new ArrayList<>();
		List<StablePO> temp = new ArrayList<>();
 		GetStockInfoDataImp get = new GetStockInfoDataImp();
		
		List<StockListPO> list = get.getStockList(code);
		int size = list.size();
		double[] t = new double[size];
		int[] a = new int[size];
		for(int i=0;i<size;i++){
			String cid = list.get(i).getCode();
			StablePO po = this.getPossi(cid);
			temp.add(po);
			t[i] = po.getInpossi();
			a[i] = i;
		}
		for(int i=0;i<size;i++){
			for(int m=i;m<size;m++){
				if(t[m]>t[i]){
					t[m]=t[m]+t[i];
					t[i]=t[m]-t[i];
					t[m]=t[m]-t[i];
					a[m]=a[m]+a[i];
					a[i]=a[m]-a[i];
					a[m]=a[m]-a[i];
				}
			}
		}
		for(int i=0;i<size;i++){
			result.add(temp.get(a[i]));
		}
		return result;
	}

	@Override
	public List<ChangePO> ChangeMax(String code) {
		// TODO Auto-generated method stub
		List<ChangePO> result = new ArrayList<>();
		List<ChangePO> temp = new ArrayList<>();
 		GetStockInfoDataImp get = new GetStockInfoDataImp();
		
		List<StockListPO> list = get.getStockList(code);
		int size = list.size();
		double[] t = new double[size];
		int[] a = new int[size];
		for(int i=0;i<size;i++){
			String cid = list.get(i).getCode();
			ChangePO po = this.getChange(cid);
			temp.add(po);
			t[i] = po.getChange();
			a[i] = i;
		}
		for(int i=0;i<size;i++){
			for(int m=i;m<size;m++){
				if(t[m]>t[i]){
					t[m]=t[m]+t[i];
					t[i]=t[m]-t[i];
					t[m]=t[m]-t[i];
					a[m]=a[m]+a[i];
					a[i]=a[m]-a[i];
					a[m]=a[m]-a[i];
				}
			}
		}
		for(int i=0;i<size;i++){
			result.add(temp.get(a[i]));
		}
		return result;
	}
	
	private DeviationPO getDevia(String code){
		Date now = new Date();
		Date last=new Date(now.getTime() - (long)80 * 24 * 60 * 60 * 1000);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(last);
		String end = dateFormat.format(now);
		GetStockInfoDataImp get = new GetStockInfoDataImp();
		List<StockOnePO> list = get.getStockOne(code, start, end);
		int size = list.size();
		double[] a = new double[32];
		for(int i=0;i<32;i++){
			a[i]=list.get(size-32+i).getClose();
		}
		double max = 0;
		double min = 100000;
		for(int i=0;i<32;i++){
			if(a[i]>max)
				max = a[i];
			if(a[i]<min)
				min = a[i];
		}
		double t1 = (max-min)/10;
		double[] b = new double[9];
		double[][] c = new double[10][10];
		int[] d = new int[32];
		b[0] = min+t1/2;
		b[1] = b[0]+t1;
		b[2] = b[1]+t1;
		b[3] = b[2]+t1;
		b[4] = b[3]+t1;
		b[5] = b[4]+t1;
		b[6] = b[5]+t1;
		b[7] = b[6]+t1;
		b[8] = b[7]+t1;
		for(int i=0;i<9;i++){
			String string = new DecimalFormat("###.###").format(b[i]);
			b[i] = Double.parseDouble(string);
		}
		for(int i=0;i<32;i++){
			if(a[i]<b[0])
				d[i] = 0;
			else if(a[i]>=b[0]&&a[i]<b[1])
				d[i] = 1;
			else if(a[i]>=b[1]&&a[i]<b[2])
				d[i] = 2;
			else if(a[i]>=b[2]&&a[i]<b[3])
				d[i] = 3;
			else 
				d[i] = 4;
		}
		for(int i=0;i<31;i++){
		c[d[i]][d[i+1]]++;
		}
		double[] e = new double[5];
		for(int i=0;i<5;i++){
		e[i] = c[i][0]+c[i][1]+c[i][2]+c[i][3]+c[i][4];
		if(e[i]==0)
			e[i]=1;
		c[i][0] = c[i][0]/e[i];
		c[i][1] = c[i][1]/e[i];
		c[i][2] = c[i][2]/e[i];
		c[i][3] = c[i][3]/e[i];
		c[i][4] = c[i][4]/e[i];
		}
		double[] r = new double[5];
		int n = d[31];
		r[n] = 1;
		for(int i=0;i<5;i++){
			double tem1 = r[0];
			double tem2 = r[1];
			double tem3 = r[2];
			double tem4 = r[3];
			double tem5 = r[4];
			for(int m=0;m<5;m++){
				r[m] = tem1*c[0][m]+tem2*c[1][m]+tem3*c[2][m]+tem4*c[3][m]+tem5*c[4][m];
			}
		}
		
		int q = 0;
		for(int i=0;i<5;i++){
			if(r[q]<r[i])
				q = i;
		}
		
		double result = min+q*t1;
		double pos = (result-a[31])*100/a[31];
		
		String s1 = new DecimalFormat("###.####").format(pos);
		pos = Double.parseDouble(s1);
		
		double min1 = min-t1/2;
		double max1 = max+t1/2;
		String string1 = new DecimalFormat("###.###").format(min1);
		min1 = Double.parseDouble(string1);
		String string2 = new DecimalFormat("###.###").format(max1);
		max1 = Double.parseDouble(string2);
		StockListPO po1 = get.getNewStockList(code);
		DeviationPO Result;
		for(int i=0;i<5;i++){
			String s = new DecimalFormat("#.##").format(r[i]);
			r[i] = Double.parseDouble(s);
		}
		if(q==0){
			Result = new DeviationPO(code, po1.getName(), po1.getIndustry(), 
					pos, r[0], min1, b[0]);
		}
		else if(q==4){
			Result = new DeviationPO(code, po1.getName(), po1.getIndustry(),
					pos, r[4], b[3], max1);
		}
		else{
			Result = new DeviationPO(code, po1.getName(), po1.getIndustry(),
					pos, r[q], b[q-1], b[q]);
		}
		return Result;
		
	}
	
	private StablePO getPossi(String code){
		Date now = new Date();
		Date last=new Date(now.getTime() - (long)120 * 24 * 60 * 60 * 1000);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(last);
		String end = dateFormat.format(now);
		GetStockInfoDataImp get = new GetStockInfoDataImp();
		List<StockOnePO> list = get.getStockOne(code, start, end);
		int size = list.size();
		double[] a = new double[32];
		for(int i=0;i<32;i++){
			a[i]=list.get(size-32+i).getClose();
		}
		int[] b = new int[31];
		double[][] c = new double[3][3];
		for(int i=0;i<31;i++){
			if(a[i]<a[i+1])
				b[i]=0;
			else if(a[i]==a[i+1])
				b[i]=1;
			else
				b[i]=2;
		}
		for(int i=0;i<30;i++){
			c[b[i]][b[i+1]]++;
		}
		double[] d = new double[3];
		for(int i=0;i<3;i++){
			d[i] = c[i][0]+c[i][1]+c[i][2];
			if(d[i]==0)
				d[i]=1;
			c[i][0] = c[i][0]/d[i];
			c[i][1] = c[i][1]/d[i];
			c[i][2] = c[i][2]/d[i];
		}
		double[] r = {0,0,0};
		r[b[30]] = 1;
		for(int i=0;i<3;i++){
			double tem1 = r[0];
			double tem2 = r[1];
			double tem3 = r[2];
			for(int m=0;m<3;m++){
				r[m] = tem1*c[0][m]+tem2*c[1][m]+tem3*c[2][m];
			}
		}
		StockListPO po1 = get.getNewStockList(code);
		String s1 = new DecimalFormat("#.###").format(r[0]);
		String s2 = new DecimalFormat("#.###").format(r[1]);
		String s3 = new DecimalFormat("#.###").format(r[2]);
		r[0] = Double.parseDouble(s1);
		r[1] = Double.parseDouble(s2);
		r[2] = Double.parseDouble(s3);
		StablePO po = new StablePO(code, po1.getName(), po1.getIndustry(), r[0],r[1],r[2]);
		return po;
	}
	
	private ChangePO getChange(String code){
		double result = 0;
		Date now = new Date();
		Date last=new Date(now.getTime() - (long)60 * 24 * 60 * 60 * 1000);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(last);
		String end = dateFormat.format(now);
		GetStockInfoDataImp get = new GetStockInfoDataImp();
		List<StockOnePO> list = get.getStockOne(code, start, end);
		int size = list.size();
		
		double[] a = new double[7];
		double sum = 0;
		for(int i=0;i<7;i++){
			a[i] = list.get(size-7+i).getClose();
			sum+=a[i];
		}
		double ave = sum/7;
		double sum1 = 0;
		for(int i=0;i<7;i++){
			sum1+=(ave-a[0])*(ave-a[0]);
		}
		result = sum1/7;
		StockListPO po1 = get.getNewStockList(code);
		ChangePO po = new ChangePO(code, po1.getName(), po1.getIndustry(), result);
		return po;
	}
}
