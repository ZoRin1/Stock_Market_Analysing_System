package BL;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BLSer.getIndexBlSer;
import DataImp.GetStockInfoDataImp;
import PO.AtrPO;
import PO.StockOnePO;
import PO.kdjPO;
import PO.macdPO;
import PO.volPO;

public class getIndex implements getIndexBlSer{
	public GetStockInfoDataImp get;
	
	public getIndex() {
		super();
		// TODO Auto-generated constructor stub
		get = new GetStockInfoDataImp();
	}

	@Override
	public List<kdjPO> getKDJ(String code) {
		// TODO Auto-generated method stub
		List<kdjPO> list1=new ArrayList<>();
		Date now = new Date();
		Date last=new Date(now.getTime() - (long)200 * 24 * 60 * 60 * 1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(last);
		String end = dateFormat.format(now);
		List<StockOnePO> list = get.getStockOne(code, start, end);
		int size = list.size();
		
		int ones = 30;
		double coEMA12=0;
		double coEMA26=0;
		double coDEA=0;
		double dif=0;
		double k=0;
		double d=0;
		double j=0;
		double sum3=0;
		double sum4=0;
		double sum5=0;
		
		//计算初始K,D,J
		double H9=0.0;
		double L9=0.0;
		for(int a=0;a<9;a++){
			StockOnePO po=list.get(size-ones-a-14);
			if(H9<po.getClose()){
				H9=po.getClose();
			}
			if(L9==0||L9>po.getClose()){
				L9=po.getClose();
			}
		}
		double ct=list.get(size-ones).getClose();
		double rsv=(ct-L9)/(H9-L9)*100;
		k=rsv;
		d=rsv;
		j=rsv;
		//用13天的数据来减小初始值的影响
		for(int n=-13;n<0;n++){
			StockOnePO po=list.get(size-ones+n);
			double close = po.getClose();
			double h9 = 0.0;
			double l9 = 0.0;
			double CT = close;
			for(int c=0;c<9;c++){
				StockOnePO po3 = list.get(size-ones+n-c);
				if(h9<po3.getClose()){
					h9 = po3.getClose();
				}
				if(l9==0||l9 >po3.getClose()){
					l9 = po3.getClose();
				}
			}
			rsv=(CT-l9)/(h9-l9)*100;
			k=(rsv+2*k)/3;
			d=(k+2*d)/3;
			j=3*d-2*k;
		}
		
		for(int n=0;n<ones;n++){
			StockOnePO po=list.get(size-ones+n);
			double close = po.getClose();
			double h9 = 0.0;
			double l9 = 0.0;
			double CT = close;
			for(int c=0;c<9;c++){
				StockOnePO po3 = list.get(size-ones+n-c);
				if(h9<po3.getClose()){
					h9 = po3.getClose();
				}
				if(l9==0||l9 >po3.getClose()){
					l9 = po3.getClose();
				}
			}
			rsv=(CT-l9)/(h9-l9)*100;
			k=(rsv+2*k)/3;
			d=(k+2*d)/3;
			j=3*d-2*k;
			String k1 = new DecimalFormat("###.####").format(k);
			String d1 = new DecimalFormat("###.####").format(d);
			String j1 = new DecimalFormat("###.####").format(j);
			k = Double.parseDouble(k1);
			d = Double.parseDouble(d1);
			j = Double.parseDouble(j1);
			kdjPO kdj = new kdjPO(po.getDate(), k, d, j);
			list1.add(kdj);
		}
		return list1;
	}

	@Override
	public List<volPO> getVOL(String code) {
		// TODO Auto-generated method stub
		List<volPO> list1=new ArrayList<>();
		Date now = new Date();
		Date last=new Date(now.getTime() - (long)200 * 24 * 60 * 60 * 1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(last);
		String end = dateFormat.format(now);
		List<StockOnePO> list = get.getStockOne(code, start, end);
		int size=list.size();
		
		for(int i=0;i<30;i++){
			StockOnePO po = list.get(size-30+i);
			volPO po1 = new volPO(po.getDate(), po.getV_ma5(), po.getV_ma10(), po.getV_ma20()
					,po.getVolume());
			list1.add(po1);
		}
		return list1;
	}

	@Override
	public List<macdPO> getMACD(String code) {
		// TODO Auto-generated method stub
		List<macdPO> result = new ArrayList<>();
		Date now = new Date();
		Date last=new Date(now.getTime() - (long)220 * 24 * 60 * 60 * 1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(last);
		String end = dateFormat.format(now);
		List<StockOnePO> list = get.getStockOne(code, start, end);
		int size = list.size();
		int ones = 30;
		
		//sum1记录前11天收盘价之和，sum2记录前25天收盘价之和，sum3记录差值之和
		double sum1 = 0;
		double sum2 = 0;
		double sum3 = 0;
		double coDEA = 0;
		double coEMA12 = 0;
		double coEMA26 = 0;
		double dif = 0;
		for(int b=0;b<8;b++){
			for(int a=0;a<11;a++){
			 StockOnePO po = list.get(size-2-ones-a-b-14);
			 sum1+=po.getClose();
			}
			StockOnePO PO = list.get(size-ones-b-1-14);
			coEMA12=(sum1+2*PO.getClose())/13;
			for(int a=0;a<25;a++){
			 StockOnePO po = list.get(size-2-ones-a-b-14);
			 sum2+=po.getClose();
			}
			coEMA26=(sum2+2*PO.getClose())/27;
			sum3+=(coEMA12-coEMA26);
		}
		sum1 = 0;
		sum2 = 0;
		for(int a=0;a<11;a++){
			 StockOnePO po = list.get(size-1-ones-a-14);
			 sum3+=po.getClose();
		}
		StockOnePO PO=list.get(size-ones-14);
		coEMA12=(sum3+2*PO.getClose())/13;
		for(int a=0;a<25;a++){
			 StockOnePO po=list.get(size-2-ones-a-14);
			 sum2+=po.getClose();
		}
		coEMA26 = (sum2+2*PO.getClose())/27;
		dif = coEMA12-coEMA26;
		coDEA = (sum3+2*dif)/10;
		sum3 = 0;
		//通过14天缓冲来消除初始值的影响
		for(int n=-13;n<0;n++){
			StockOnePO po1 = list.get(size-ones+n);
			double endPrice = po1.getClose();
			coEMA12 = (coEMA12*11+2*endPrice)/13;
			coEMA26 = (coEMA26*25+2*endPrice)/27;
			dif = coEMA12-coEMA26;
			coDEA = (coDEA*8+dif*2)/10;
		}
		for(int n=0;n<ones;n++){
			StockOnePO po1 = list.get(size-ones+n);
			double endPrice = po1.getClose();
			coEMA12 = (coEMA12*11+2*endPrice)/13;
			coEMA26 = (coEMA26*25+2*endPrice)/27;
			dif = coEMA12-coEMA26;
			coDEA = (coDEA*8+dif*2)/10;
			String k1 = new DecimalFormat("###.####").format(dif);
			String d1 = new DecimalFormat("###.####").format(coDEA);
			dif = Double.parseDouble(k1);
			coDEA = Double.parseDouble(d1);
			macdPO po = new macdPO(po1.getDate(), dif, coDEA);
			result.add(po);
		}
		return result;
	}

	@Override
	public List<AtrPO> getATR(String code) {
		// TODO Auto-generated method stub
		Date now = new Date();
		Date last=new Date(now.getTime() - (long)200 * 24 * 60 * 60 * 1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(last);
		String end = dateFormat.format(now);
		List<StockOnePO> list = get.getStockOne(code, start, end);
		int size = list.size();
		
		int ones = 30;
		List<AtrPO> result = new ArrayList<>();
		for(int a=0;a<ones;a++){
			double sum1=0.0;
			double result1=0;
			for(int i=0;i<7;i++){
				double t=0;
				for(int m=0;m<14;m++){
					StockOnePO po1=list.get(size-i-m+a-ones);
					StockOnePO lastpo=list.get(size-1-i-m+a-ones);
					double hi=po1.getHigh();
					double li=po1.getLow();
					double ci1=lastpo.getClose();
					double li1=lastpo.getLow();
					double t1=hi-li;
					double t2=Double.max(ci1-hi, hi-ci1);
					double t3=ci1-li1;
					t+=Double.max(t1,Double.max(t2, t3));
				}
				sum1+=t/14;
			}
			result1=sum1/6;
			String s1=new DecimalFormat("##.####").format(result1);
			double m = Double.parseDouble(s1);
			StockOnePO po1 = list.get(size-ones+a);
			AtrPO po = new AtrPO(po1.getDate(), m);
			result.add(po);
		}
		return result;
	}

}
